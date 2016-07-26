package com.hecj.code.gen.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hecj.code.gen.config.FreemarkerUtil;
import com.hecj.code.gen.config.GenConfig;
import com.hecj.code.jdbc.vo.Field;
import com.hecj.code.jdbc.vo.Table;

public class GenUtil {
	
	private static final Log log = LogFactory.getLog(GenUtil.class);

	/**
	 * gen model
	 */
	public static void genModel(Table table,List<Field> fields,Map<String,String> dataType){
		
		log.info(" gen model begin , " +table.getTableName());
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", table);
		root.put("fields", fields);
		root.put("dataType", dataType);
		root.put("basePackage", GenConfig.basePackage);
		
		String template = "/model/model.ftl";
		String dir = GenConfig.baseDirJava+"/"+GenConfig.basePackage.replaceAll("\\.","/")+"/model/";
		String fileName = table.getFormatName()+".java";
		
		FreemarkerUtil.outFile(root, template, dir, fileName);
		
		log.info(" gen model end");
	}
	
	/**
	 * gen service
	 */
	public static void genService(Table table){
		
		log.info(" gen service begin , " + table.getTableName());
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", table);
		root.put("basePackage", GenConfig.basePackage);
		
		String template = "/service/service.ftl";
		String dir = GenConfig.baseDirJava+"/"+GenConfig.basePackage.replaceAll("\\.","/")+"/service/";
		String fileName = table.getFormatName()+"Service.java";
		
		FreemarkerUtil.outFile(root, template, dir, fileName);

		log.info(" gen service end");
	}
	
	/**
	 * gen serviceImpl
	 */
	public static void genServiceImpl(Table table){
		
		log.info(" gen serviceImpl begin , " + table.getTableName());
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", table);
		root.put("basePackage", GenConfig.basePackage);
		
		String template = "/service/impl/serviceImpl.ftl";
		String dir = GenConfig.baseDirJava+"/"+GenConfig.basePackage.replaceAll("\\.","/")+"/service/impl/";
		String fileName = table.getFormatName()+"ServiceImpl.java";

		FreemarkerUtil.outFile(root, template, dir, fileName);
		
		log.info(" gen serviceImpl end");
	}
	
	/**
	 * gen applicationContextService
	 */
	public static void genApplicationContextService(List<Table> tables){
		
		log.info(" gen applicationContextService begin ");
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("tables", tables);
		root.put("basePackage", GenConfig.basePackage);
		
		String template = "/spring/applicationContext-service.ftl";
		String dir = GenConfig.baseDirResources+"/spring/";
		String fileName = "applicationContext-service.xml";

		FreemarkerUtil.outFile(root, template, dir, fileName);
		
		log.info(" gen applicationContextService end ");
	}
	
	/**
	 * gen dubboProvider
	 */
	public static void genDubboProvider(List<Table> tables){
		
		log.info(" gen dubboProvider begin ");
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("tables", tables);
		root.put("basePackage", GenConfig.basePackage);
		
		String template = "/spring/dubbo-provider.ftl";
		String dir = GenConfig.baseDirResources+"/spring/";
		String fileName = "dubbo-provider.xml";

		FreemarkerUtil.outFile(root, template, dir, fileName);
		
		log.info(" gen dubboProvider end ");
	}
	
	/**
	 * gen dubboConsumer
	 */
	public static void genDubboConsumer(List<Table> tables){
		
		log.info(" gen dubboConsumer begin ");
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("tables", tables);
		root.put("basePackage", GenConfig.basePackage);
	
		String template = "/spring/dubbo-consumer.ftl";
		String dir = GenConfig.baseDirResources+"/spring/";
		String fileName = "dubbo-consumer.xml";

		FreemarkerUtil.outFile(root, template, dir, fileName);
		
		log.info(" gen dubboConsumer end ");
	}
	
	/**
	 * gen controller
	 */
	public static void genController(Table table,List<Field> fields){
		
		log.info(" gen controller begin , " + table.getTableName());
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", table);
		root.put("fields", fields);
		root.put("basePackage", GenConfig.basePackage);
		
		String template = "/controller/controller.ftl";
		String dir = GenConfig.baseDirJava+"/"+GenConfig.basePackage.replaceAll("\\.","/")+"/controller/";
		String fileName = table.getFormatName()+"Controller.java";
		
		FreemarkerUtil.outFile(root, template, dir, fileName);

		log.info(" gen controller end");
	}
	
	/**
	 * gen controller ViewList
	 */
	public static void genViewList(Table table,List<Field> fields){
		
		log.info(" gen controller ViewList begin , " + table.getTableName());
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("table", table);
		root.put("fields", fields);
		
		String template = "/view/list.ftl";
		String dir = GenConfig.baseDirResources+"/view/"+table.getTableName()+"/";
		String fileName ="list.ftl";
		
		FreemarkerUtil.outFile(root, template, dir, fileName);

		log.info(" gen controller ViewList end");
	}
}
