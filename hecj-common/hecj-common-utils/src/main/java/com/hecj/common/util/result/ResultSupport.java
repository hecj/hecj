package com.hecj.common.util.result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hecj.common.util.result.Pagination;
/**
 * @类功能说明：基本实现类 默认map的key为data保存基本数据集合
 * 扩展数据可放入map中
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-21 上午11:23:04
 * @版本：V1.0
 */
public class ResultSupport implements Result {
	
	private static final long serialVersionUID = 1L;

	private boolean b = false;
	
	private Map<String,Object> map = new HashMap<String,Object>();
	
	private List<?> list ;
	
	private Pagination pagination = new Pagination();
	
	public Map<String, Object> getModel() {
		return map;
	}

	public boolean isSuccess() {
		return b;
	}

	public void setResult(boolean b) {
		this.b = b;
	}

	public void setModel(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public List<?> getList() {
		return this.list;
	}

	@Override
	public Pagination getPagination() {
		return this.pagination;
	}

	@Override
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}