package ${basePackage}.model;

import java.util.HashMap;
import java.util.Map;

import com.hecj.pro.common.model.BaseModel;

/**
 * @table:${table.tableComment}
 * @author:hecj
 */
public class ${table.formatName} extends BaseModel{
 
	private static final long serialVersionUID = 1L;

	public final static String tableName = "${table.tableName}";

	<#list fields as field>
	<#if field.columnName != "id" && field.columnName != "create_at" && field.columnName != "update_at" >
	/**
	 * ${field.columnName!}:${field.columnComment}
	 */
	public final static String ${field.formatName} = "${field.columnName}";
	
	</#if>
	</#list>

	public final static Map<String, Class<?>> getFieldType() {
		
		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		
		<#list fields as field>  
		fields.put(${field.formatName},${dataType['${field.dataType?upper_case}']}.class);
		</#list>
		
		return fields;
	}
	
	public Object get(String key) throws Exception {
		
		return super.get(key,getFieldType());
	}

}
