package com.hecj.sysconfig.service.impl;

import com.hecj.common.core.impl.BaseServiceImpl;
import com.hecj.sysconfig.core.model.Potential;
import com.hecj.sysconfig.core.service.PotentialService;
import com.jfinal.log.Logger;

public class PotentialServiceImpl<T> extends BaseServiceImpl<T> implements PotentialService<T> {

	private Logger log = Logger.getLogger(PotentialServiceImpl.class);

	@Override
	public String getTableName() {
		return Potential.tableName;
	}

}
