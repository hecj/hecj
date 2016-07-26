<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans.xsd 
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-3.0.xsd
	http://code.alibabatech.com/schema/dubbo 
	http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

	<dubbo:application name="dubbo-provider" />
	
	<!-- registry zookeeper -->
	<dubbo:registry address="zookeeper://www.hecj.top:4180" />
 
	<dubbo:protocol name="dubbo" port="20881" />
	
	<!-- dubbo service -->
	
	<#list tables as table>
	<dubbo:service interface="${basePackage}.service.${table.formatName}Service" ref="${table.formatName?uncap_first}Service" />

 	</#list>

</beans>