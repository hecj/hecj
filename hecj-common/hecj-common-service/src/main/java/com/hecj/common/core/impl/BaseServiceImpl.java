package com.hecj.common.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.hecj.common.core.tx.TxService;
import com.hecj.common.core.BaseService;
import com.hecj.common.exception.ServiceException;
import com.hecj.common.model.BaseModel;
import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private Logger log = Logger.getLogger(BaseServiceImpl.class);
	
	public abstract String getTableName();

	@Override
	public List<T> queryList() throws ServiceException {
		log.info(" queryList begin ");
		List<T> list = new ArrayList<T>();
		try {
			List<Record> records = Db.find("select * from "+getTableName()+" order by id asc ");
			for (Record r : records) {
				list.add((T) r.getColumns());
			}
			log.info(" queryList end ");
		} catch (Exception e) {
			log.error(" queryList error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	@Override
	public Long save(T model) throws ServiceException {
		
		log.info(" save table - "+getTableName()+" - begin , data : " + model);
		Long id = -1l ;
		if(model == null){
			return id;
		}
		BaseModel baseModel = (BaseModel)model;
		Record record = new Record();
		try {
			baseModel.put(BaseModel.createAt, System.currentTimeMillis());
			baseModel.put(BaseModel.updateAt, System.currentTimeMillis());
			record.setColumns(baseModel);
			boolean b = Db.save(getTableName(), record);
			if (b) {
				id =  record.get(BaseModel.id);
			}
			log.info(" save table - "+getTableName()+" - end , insert id : " + id);
		} catch (Exception e) {
			log.error(" save table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return id;
	}
	@Override
	public boolean delete(Long id) throws ServiceException {
		
		log.info(" delete table - "+getTableName()+" - begin , id : " + id);
		boolean result = false ;
		if(id == null){
			return true;
		}
		try {
			Db.deleteById(getTableName(), id);
			log.info(" delete table - "+getTableName()+" - end , delete success");
		} catch (Exception e) {
			log.error(" delete table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return result;
	}
	
	@TxService
	@Override
	public boolean deleteList(List<Long> ids) throws ServiceException{
		
		log.info(" deleteList table - "+getTableName()+" - begin , ids : " + ids);
		boolean result = false ;
		if(ids == null){
			return true;
		}
		try {
			for(Long id : ids){
				delete(id);
			}
			log.info(" deleteList table - "+getTableName()+" - end , delete success");
		} catch (Exception e) {
			log.error(" deleteList table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean update(T model) throws ServiceException {
		log.info(" update table - "+getTableName()+" - begin , data : " + model);
		boolean result = false ;
		if(model == null){
			return false;
		}
		try {
			Record record = new Record();
			record.setColumns((BaseModel) model);
			record.set(BaseModel.updateAt, System.currentTimeMillis());
			Db.update(getTableName(), record);
			log.info(" update table - "+getTableName()+" - end , update success");
		} catch (Exception e) {
			log.error(" update table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return result;
	}

	@Before(Tx.class)
	@Override
	public boolean updateList(List<T> models) throws ServiceException {
		log.info(" updateList table - "+getTableName()+" - begin , datas : " + models);
		boolean result = false ;
		if(models == null){
			return false;
		}
		try {
			for (T t : models) {
				update(t);
			}
			log.info(" updateList table - "+getTableName()+" - end , updateList success");
		} catch (Exception e) {
			log.error(" updateList table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return result;
	}

	@Override
	public T getObjectById(Long id) throws ServiceException {
		log.info(" getObjectById table - "+getTableName()+" - begin , id : " + id);
		T t = null ;
		if(id == null){
			return null;
		}
		try {
			Record record = Db.findById(getTableName(), id);
			t = (T) record.getColumns();
			log.info(" getObject table - "+getTableName()+" - end , getObject success");
		} catch (Exception e) {
			log.error(" getObject table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return t;
	}

	@Override
	public List<T> getObjectsByIds(List<Long> ids) throws ServiceException {
		
		log.info(" getObjects table - "+getTableName()+" - begin , ids : " + ids);
		List<T> results = new ArrayList<T>();
		if(ids == null){
			return null;
		}
		try {
			for (Long id : ids) {
				results.add(getObjectById(id));
			}
			log.info(" getObjects table - "+getTableName()+" - end , getObjects success");
		} catch (Exception e) {
			log.error(" getObjects table - "+getTableName()+" - error , " + e.getMessage());
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return results;
	}

}
