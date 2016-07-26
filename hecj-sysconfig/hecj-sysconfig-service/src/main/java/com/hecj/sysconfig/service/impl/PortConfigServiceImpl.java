package com.hecj.sysconfig.service.impl;

import com.hecj.common.core.impl.BaseServiceImpl;
import com.hecj.sysconfig.core.model.PortConfig;
import com.hecj.sysconfig.core.service.PortConfigService;
import com.jfinal.log.Logger;

public class PortConfigServiceImpl<T> extends BaseServiceImpl<T> implements PortConfigService<T> {

	private Logger log = Logger.getLogger(PortConfigServiceImpl.class);

	@Override
	public String getTableName() {
		return PortConfig.tableName;
	}
	
}
