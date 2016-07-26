package ${basePackage}.service.impl;

import com.hecj.pro.common.core.impl.BaseServiceImpl;
import ${basePackage}.model.${table.formatName};
import ${basePackage}.service.${table.formatName}Service;
import com.jfinal.log.Logger;

public class ${table.formatName}ServiceImpl<T> extends BaseServiceImpl<T> implements ${table.formatName}Service<T> {

	private Logger log = Logger.getLogger(${table.formatName}ServiceImpl.class);

	@Override
	public String getTableName() {
		return ${table.formatName}.tableName;
	}
	
}
