<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>qq号码</title>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
</head>
<body style='font-family: 微软雅黑;font-size: 14px;'>
<center>
	<h3>qq号码</h3>
	<table border="1" style='border-collapse: collapse;border-spacing: 0px;text-align:center'>
		<thead>
			<tr>
				<th scope='col' width=50>编号</th>
				<th scope='col' width=50>qq号码</th>
				<th scope='col' width=50>qq密码</th>
				<th scope='col' width=50>小学</th>
				<th scope='col' width=50>初中</th>
				<th scope='col' width=50>高中</th>
				<th scope='col' width=50>老密保</th>
				<th scope='col' width=50>创建时间</th>
				<th scope='col' width=50>修改时间</th>
			</tr>
		</thead>
		<tbody>
		<#list dataList as data>
			<tr>
				<td>${data.id!}</td>
				<td>${data.qq!}</td>
				<td>${data.pwd!}</td>
				<td>${data.xiaoxue!}</td>
				<td>${data.chuzhong!}</td>
				<td>${data.gaozhong!}</td>
				<td>${data.oldmibao!}</td>
				<td>${data.create_at!}</td>
				<td>${data.update_at!}</td>
			</tr>
		</#list>
		</tbody>
	</table>
</center>
</body>
</html>