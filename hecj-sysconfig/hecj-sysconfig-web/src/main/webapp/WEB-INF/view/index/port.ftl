<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>端口分配信息</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
</head>
<body style='font-family: 微软雅黑;font-size: 14px;'>
<center>
	<h3>端口分配信息2</h3>
	<table border="1" style='border-collapse: collapse;border-spacing: 0px;text-align:center'>
		<thead>
			<tr>
				<th scope='col' width=50>编号</th>
				<th scope='col' width=80>端口</th>
				<th scope='col' width=180>名称</th>
				<th scope='col' width=400>路径</th>
				<th scope='col' width=80>状态</th>
			</tr>
		</thead>
		<tbody>
		<#list pList as p>  
			<tr>
				<td>${p.id}<#--${p_index+1}--></td>
				<td>${p.port! }</td>
				<td>${p.name! }</td>
				<td>${p.dir!}</td>
				<td>
					<font color=green>running</font>
				</td>
			</tr>
		</#list>
		</tbody>
	</table>
</center>
</body>
</html>