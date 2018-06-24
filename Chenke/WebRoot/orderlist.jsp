<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String username = (String)session.getAttribute("loginuser");
	if(username == null)
		response.sendRedirect("login.jsp");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>orderlist</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap-theme.min.css" />

	<script type="text/javascript">
		function unite(id, num){
			var td_id = document.getElementById("id_" + id);
			var td_user = document.getElementById("user_" + id);
			var td_crdt = document.getElementById("crdt_" + id);
			var td_total = document.getElementById("total_" + id);
			td_id.rowSpan = num;
			td_user.rowSpan = num;
			td_crdt.rowSpan = num;
			td_total.rowSpan = num;
	}
	</script>

  </head>
  
  <body>
    
    <!-- 导航条 -->
	<nav class="navbar navbar-default">
  		<div class="container">
	    <div class="navbar-header">
    		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        		<span class="sr-only">Toggle navigation</span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
      		</button>
      		<a class="navbar-brand" href="#">陈珂电子书城</a>
    	</div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="books">首页 <span class="sr-only">(current)</span></a></li>
        <li class="active"><a href="order">我的订单</a></li>
        <li><a href="shopping.jsp">我的购物车</a></li>
        <li><a href="loginout.jsp">注销</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="#">欢迎<%=username %></a></li>
      </ul>
      
      <form class="navbar-form navbar-right">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="搜索">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
    </div>
  </div>
</nav>


	<!-- 表单页面 -->
	<div class="container">
		<form method="post" name="orderlist" action="buy">
			<table class="table">
				<tr class="title">
	    			<th class="orderId">订单编号</th>					
					<th class="userName">收货人</th>					
					<th class="createTime">下单时间</th>
					<th class="status">总价</th>
					<th>订单商品</th>
					<th>商品名称</th>
					<th>商品单价</th>
					<th class="price">商品数量</th>
	    		</tr>
	    		<c:set var="oid_count" value="0" />
				<c:set var="td_id" value="0" />	
				<c:forEach var="order" items="${orders}">
				<tr>
					<c:if test="${oid_temp != order.oid}">
						<c:set var="td_id" value="${td_id+1}" />
						<td id="id_${td_id}">${order.oid}</td>
						<td id="user_${td_id}">${order.username}</td>						
						<td id="crdt_${td_id}">${order.createdate}</td>
						<td id="total_${td_id}">${order.total_price}</td>
						<c:if test="${oid_count > 1}">
							<script type="text/javascript" >	
								unite(${td_id-1}, ${oid_count});
							</script>
						</c:if>
						<c:set var="oid_count" value="0" />
					</c:if>
					<td class="thumb"><img src="${order.image}" /></td>					
					<td>${order.bookname}</td>
					<td>${order.bprice}</td>
					<td>${order.price/order.bprice}</td>
					<c:set var="oid_count" value="${oid_count+1}" />
					<c:set var="oid_temp" value="${order.oid}"></c:set>
				</tr>
				</c:forEach>
				<c:if test="${td_id > 0}">
					<script type="text/javascript" >	
						unite(${td_id}, ${oid_count});
					</script>
				</c:if>
			</table>
		</form>
	</div>
    
    <script src="./bootstrap/bootstrap.min.js"></script>
	<script src="./bootstrap/jquery-2.1.3.js"></script>
  </body>
</html>
