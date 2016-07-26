package com.hecj.common.jfinal.injector;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.hecj.common.jfinal.convert.TypeConverter;
import com.hecj.common.model.BaseModel;
import com.jfinal.plugin.activerecord.ActiveRecordException;

public final class BaseModelInjector {
	
	/**
	 * Method reflex
	 * @param <T>
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final <T> BaseModel inject(Class<T> modelClass, String modelName, HttpServletRequest request, boolean skipConvertError) throws InstantiationException, IllegalAccessException {
		
		BaseModel baseModel = (BaseModel) modelClass.newInstance();
		Map<String,Class<?>> typeMap = null ;
		try {
			Method method = modelClass.getMethod("getFieldType");
			typeMap = (Map<String,Class<?>>) method.invoke(modelClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if (typeMap instanceof Map)
			injectActiveRecordMap(typeMap, baseModel , modelName, request, skipConvertError);

		return baseModel;
	}
	
	/**
	 * Parameter acceptance
	 */
	@SuppressWarnings("rawtypes")
	private static final void injectActiveRecordMap(Map<String,Class<?>> typeMap, BaseModel baseModel, String modelName, HttpServletRequest request, boolean skipConvertError) {
		
		String modelNameAndDot = modelName + ".";
		
		Map<String, String[]> parasMap = request.getParameterMap();
		for (Entry<String, String[]> e : parasMap.entrySet()) {
			String paraKey = e.getKey();
			if (paraKey.startsWith(modelNameAndDot)) {
				String paraName = paraKey.substring(modelNameAndDot.length());
				Class colType = typeMap.get(paraName);
				if (colType == null)
					throw new ActiveRecordException("The model attribute " + paraKey + " is not exists.");
				String[] paraValue = e.getValue();
				try {
					// Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
					Object value = paraValue[0] != null ? TypeConverter.convert(colType, paraValue[0]) : null;
					baseModel.put(paraName, value);
				} catch (Exception ex) {
					if (skipConvertError == false)
						throw new RuntimeException("Can not convert parameter: " + modelNameAndDot + paraName, ex);
				}
			}
		}
	}
	
}
