<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo
        http://code.alibabatech.com/schema/dubbo/dubbo.xsd">     

	<dubbo:application name="dubbo-consumer" />
	
	<dubbo:registry  protocol="zookeeper"  address="www.hecj.top:4180" />
	
	<!-- dubbo consumer -->
	
	<#list tables as table>
	<dubbo:reference id="${table.formatName?uncap_first}Service" interface="${basePackage}.service.${table.formatName}Service" />
	
	</#list>
</beans>