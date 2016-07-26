package com.hecj.common.jfinal.controller;

import com.hecj.common.jfinal.injector.BaseModelInjector;
import com.hecj.common.model.BaseModel;
import com.jfinal.core.Controller;

public abstract class BaseController extends Controller {
	
	
	/**
	 * Get Map from http request.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public BaseModel getBaseModel(Class<?> modelClass) throws InstantiationException, IllegalAccessException {
		String modelName = modelClass.getSimpleName();
		return BaseModelInjector.inject(modelClass, modelName, getRequest(), false);
	}
	
	/**
	 * Get Map from http request.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public BaseModel getBaseModel(Class<?> modelClass, String modelName) throws InstantiationException, IllegalAccessException {
		return BaseModelInjector.inject(modelClass, modelName, getRequest(), false);
	}
	
	
	
}
