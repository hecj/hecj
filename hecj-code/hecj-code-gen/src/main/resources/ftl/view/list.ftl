<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>${table.tableComment!}</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
</head>
<body style='font-family: 微软雅黑;font-size: 14px;'>
<center>
	<h3>${table.tableComment!}</h3>
	<table border="1" style='border-collapse: collapse;border-spacing: 0px;text-align:center'>
		<thead>
			<tr>
			<#list fields as field>
				<th scope='col' width=50>${field.columnComment}</th>
			</#list>
			</tr>
		</thead>
		<tbody>
		${r'<#list dataList as data>'}
			<tr>
				<#list fields as field>
				<td>${r'${data'}.${field.columnName}${r'!}'}</td>
				</#list>
			</tr>
		${r'</#list>'}
		</tbody>
	</table>
</center>
</body>
</html>