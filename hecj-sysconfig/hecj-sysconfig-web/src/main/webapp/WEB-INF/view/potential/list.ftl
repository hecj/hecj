<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>微信用户表</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
</head>
<body style='font-family: 微软雅黑;font-size: 14px;'>
<center>
	<h3>微信用户表</h3>
	<table border="1" style='border-collapse: collapse;border-spacing: 0px;text-align:center'>
		<thead>
			<tr>
				<th scope='col' width=50>ID</th>
				<th scope='col' width=50>openId</th>
				<th scope='col' width=50>昵称</th>
				<th scope='col' width=50>性别</th>
				<th scope='col' width=50>省份</th>
				<th scope='col' width=50>城市</th>
				<th scope='col' width=50>国家</th>
				<th scope='col' width=50>时间</th>
			</tr>
		</thead>
		<tbody>
		<#list dataList as data>
			<tr>
				<td>${data.id!}</td>
				<td>${data.openid!}</td>
				<td>${data.nick_name!}</td>
				<td>${data.sex!}</td>
				<td>${data.province!}</td>
				<td>${data.city!}</td>
				<td>${data.country!}</td>
				<td>${data.create_at!}</td>
			</tr>
		</#list>
		</tbody>
	</table>
</center>
</body>
</html>