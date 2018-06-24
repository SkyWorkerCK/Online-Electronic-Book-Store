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
    
    <title>shopping page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap-theme.min.css" />

	<style>
		#turn{
			cursor:hand;
		}
	</style>
	
	<script type="text/javascript">
	//修改数量时，调用Ajax改变购物车内的信息
	function modify(size_str, i , bid_str){
		
		var xmlHttp;
		//根据不同浏览器初始化xmlHttp
		try{
			//IE 6+
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");				
		}catch(e){
			try{
				//FireFox
				xmlHttp = new XMLHttpRequest();		
			}catch(e){
				try{
					//IE 5.5+
					xmlHttp =  new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e){
					alert("您的浏览器不支持Ajax！");
				}
			}
		}
		var num_str = document.getElementById("nums_" + i);	
		xmlHttp.open("POST", "modify?action=modify&bid="+bid_str+"&count="+num_str.value, true);		
		xmlHttp.send(null);	
		xmlHttp.onreadystatechange = function()
		{
			if( xmlHttp.readyState == 4 )
			{
				count(size_str);
			}
		}
	}
	//计算购物车内价格信息
	function count(size_str){
		var size = parseFloat(size_str);
		var total_price = 0;
		for(var i = 1; i <= size; i++ ){			
			var num_str = document.getElementById("nums_" + i);
			var price = document.getElementById("price_" + i);
			var hidden_price = document.getElementById("hidden_book_total_price_" + i);
			var book_price_str = document.getElementById("hidden_" + i);
			var num = parseFloat(num_str.value);
			var book_price = parseFloat(book_price_str.value);
			price.innerHTML = num * book_price;
			hidden_price.value = num * book_price;
			total_price = total_price + num * book_price;
		}
		var hidden_total_price = document.getElementById("hidden_total_price");
		hidden_total_price.value = total_price;
		document.getElementById("total_price").innerHTML = total_price;
	}
	//从购物车中移除书籍
	function remove(bid_str, NO, size_str){		
		var xmlHttp;
		//根据不同浏览器初始化xmlHttp
		try{
			//IE 6+
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");				
		}catch(e){
			try{
				//FireFox
				xmlHttp = new XMLHttpRequest();		
			}catch(e){
				try{
					//IE 5.5+
					xmlHttp =  new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e){
					alert("您的浏览器不支持Ajax！");
				}
			}
		}		
		xmlHttp.open("POST", "modify?action=remove&bid="+bid_str, true);		
		xmlHttp.send(null);	
		xmlHttp.onreadystatechange = function()
		{
			if( xmlHttp.readyState == 4 )
			{
				document.getElementById("remove_" + NO).innerHTML = "<font color=\"red\">移除完毕</font>";
				document.getElementById("nums_" + NO).value = "0";
				document.getElementById("nums_" + NO).disabled="disabled";
				count(size_str, 0 ,0);
			}
		}
	}
	</script>

  </head>
  
  <body onload="count(${books_in_cart_count})">
    
    
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
        <li><a href="order">我的订单</a></li>
        <li class="active"><a href="shopping.jsp">我的购物车</a></li>
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
		<form method="post" name="shoping" action="item">
			<table class="table">
													<!-- <tr class="title">
														<th class="view">图片预览</th>
														<th>书名</th>
														<th class="nums">数量</th>
														<th class="price">价格</th>
														<th class="nums">操作</th>
													</tr> -->
				<tr class="title">
	    			<th class="view">图片预览</th>
					<th>书名</th>
					<th class="nums">数量</th>
					<th class="price">价格</th>
					<th class="nums">操作</th>
	    		</tr>
				
				<c:set value="1" var="count" />
				<c:forEach var="book" items="${books_in_cart}" >
				<tr class="success">
					<input type="hidden" id="hidden_bid_${count}" name="hidden_bid_${count}" value="${book.bid}"/>
					<td class="thumb"><img src="${book.image}" /></td>
					<td class="title">${book.bookname }</td>
					<td><input class="input-text" type="text" id="nums_${count}" name="nums_${count}" value="${book.count}"  onchange="modify(${books_in_cart_count}, ${count}, ${book.bid})"/></td>
					<input type="hidden" id="hidden_${count}" name="hidden_${count}" value="${book.bprice }"/>
					<td>￥<span id="price_${count}"></span></td>
					<input type="hidden" id="hidden_book_total_price_${count}" name="hidden_book_total_price_${count}"/>
					<td>
						<span id="remove_${count}">
							<p onclick="remove(${book.bid}, ${count}, ${books_in_cart_count})" id="turn">移除</p>
						</span>
					</td>
				</tr>
				<c:set value="${count+1}" var="count" />
				</c:forEach>
			</table>
			<input type="hidden" id="count" name="count" value="${count}"/>
			<div class="button">
				<h4 align="center">总价：￥<span id="total_price"></span>元</h4>
				<input type="hidden" id="hidden_total_price" name="hidden_total_price"/>
				<button type="submit" class="btn btn-default btn-success text-center pull-right">提交订单</button>
			</div>
		</form>
	</div>
    
    
    <script src="./bootstrap/bootstrap.min.js"></script>
	<script src="./bootstrap/jquery-2.1.3.js"></script>
  </body>
  
</html>
