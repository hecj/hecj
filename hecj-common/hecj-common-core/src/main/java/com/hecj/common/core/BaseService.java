package com.hecj.common.core;

import java.util.List;

import com.hecj.common.exception.ServiceException;

/**
 * @ClassName: BaseService
 */
public abstract interface BaseService<T> {

	public List<T> queryList() throws ServiceException;

	public Long save(T model) throws ServiceException;
	
	public boolean delete(Long id) throws ServiceException;
	
	public boolean deleteList(List<Long> ids) throws ServiceException;
	
	public boolean update(T model) throws ServiceException;
	
	public boolean updateList(List<T> models) throws ServiceException;
	
	public T getObjectById(Long id) throws ServiceException;
	
	public List<T> getObjectsByIds(List<Long> ids) throws ServiceException;
	
	
	
	
}
