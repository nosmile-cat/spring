<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%> <!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql"%> <!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%> <!--常用函数标签库-->
<%@ page isELIgnored="false"%> <!--支持EL表达式，不设的话，EL表达式不会解析-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../static/js/jquery-1.12.3.min.js"></script>
    <script src="../static/miniui/miniui.js" type="text/javascript"></script>
    <link href="../static/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <link href="../static/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
<script language='javascript'>
	function edit(id){
		 mini.open({
		    title: '编辑',
		    url: '/editOrder?id= '+id,
		    allowResize: false,
		    width: 400,height: 300,
		    ondestroy: function(){
		        //grid.reload();
		    }
		});
	}
	
</script>
</head>
<body>
<form action="/addOrder" method="post">
	<input type="hidden" name="orderId" value="${order.orderId}"/>
	<table style="border:1px solid #F00">
		<tr>
			<td>序号</td>
			<td>-名称-</td>
			<td>价格</td>
		</tr>
		<c:forEach items="${list}" var="item" varStatus="status" begin="0">
		<tr>
			<td>${status.index+1}</td>
			<td><a href="javascript:void(0);" onclick="edit('${item.orderId }')">${item.orderName }</a></td>
			<td>${item.price }</td>
		</tr>
		</c:forEach>
	</table>
</form>

</body>
</html>