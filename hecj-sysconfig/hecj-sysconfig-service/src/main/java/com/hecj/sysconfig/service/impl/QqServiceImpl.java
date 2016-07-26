package com.hecj.sysconfig.service.impl;

import com.hecj.common.core.impl.BaseServiceImpl;
import com.hecj.sysconfig.core.model.Qq;
import com.hecj.sysconfig.core.service.QqService;
import com.jfinal.log.Logger;

public class QqServiceImpl<T> extends BaseServiceImpl<T> implements QqService<T> {

	private Logger log = Logger.getLogger(QqServiceImpl.class);

	@Override
	public String getTableName() {
		return Qq.tableName;
	}
	
}
