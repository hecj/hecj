package com.hecj.code.jdbc.vo;

import java.io.Serializable;

/**
 * table schema
 */
public class Table implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 数据库名
	 */
	private String databaseName;
	/*
	 * 表名
	 */
	private String tableName;
	/*
	 * 表类型
	 */
	private String tableType;
	/*
	 * 表注释
	 */
	private String tableComment;
	/*
	 * 引擎
	 */
	private String tableEngine;
	
	private String formatName;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getTableEngine() {
		return tableEngine;
	}

	public void setTableEngine(String tableEngine) {
		this.tableEngine = tableEngine;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	
}
