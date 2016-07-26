package com.hecj.code.gen.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.hecj.common.utils.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {

	public static Configuration cfg = null;
	static {
		cfg = new Configuration(Configuration.VERSION_2_3_23);
		try {
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/ftl"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param root 数据源
	 * @param template 模版文件
	 * @param dir 输出目录
	 * @param fileName 文件名
	 */
	public static void outFile(Map<String,Object> root,String template, String dir, String fileName){
		
		FileWriter out = null;
		try {
			Template temp = FreemarkerUtil.cfg.getTemplate(template);
			File springDir = new File(dir);
			FileUtils.mkdirs(springDir);
			out = new FileWriter(new File(springDir.getPath(),fileName));
	        temp.process(root, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
