<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>
</head>
<body>
<form action="/addOrder" method="post">
	<input type="hidden" name="id" value="${order.id}"/>
	<table>
		<tr>
			<td>
			名称：<input type="text" name="orderName" value="${order.orderName}"/>
			</td>
		</tr>
		<tr>
			<td>
			价格：<input type="text" name="price" value="${order.price}"/>
			</td>
		</tr>
		<tr>
			<td>
				<div style="text-align:center"><input type="submit" value="保存"/></div>
			</td>
		</tr>
	</table>
</form>

</body>
</html>