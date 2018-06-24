<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String username = (String)session.getAttribute("loginuser");
	if(username == null){
		response.sendRedirect("login.jsp");
	}
%>
<%
	int NO = 0;
	int total_page = 0;
	List books = (List)request.getAttribute("books");
	if(request.getAttribute("current_books_NO") != null){
		NO = (Integer)request.getAttribute("current_books_NO");
		total_page = (Integer)request.getAttribute("total_books_page");
	}
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Main Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap-theme.min.css" />
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
        <li class="active"><a href="books">首页 <span class="sr-only">(current)</span></a></li>
        <li><a href="order">我的订单</a></li>
        <li><a href="shopping.jsp">我的购物车</a></li>
        <li><a href="loginout.jsp">注销</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="#">欢迎<%=username %></a></li>
      </ul>
      
      <!-- 搜索表单  -->
      <form class="navbar-form navbar-right" method="post" name="search" action="search">
        <div class="form-group">
          <input type="text" class="form-control" name="keywords" placeholder="搜索">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
      
    </div>
  </div>
</nav>
    
    <!-- 响应式表格 -->
    <div class="container">
    	<form method="post" name="shopping" action="cart">
	    	<div class="table-responsive">
	  			<table class="table">
	    			<tr>
	    				<th>选项</th>
	    				<th>书名</th>
	    				<th>价格</th>
	    				<th>库存</th>
	    				<th>图片预览</th>
	    			</tr>
	    			<c:forEach var="book" items="${books}">
	    				<tr class="info">
	    					<td><input type="checkbox" name="bookId" value="${book.bid}" /></td>
	    					<td>${book.bookname}
	    					<input type="hidden" name="title" value="${book.bid}:${book.bookname}" /></td>
	    					<td>${book.price}
	    					<input type="hidden" name="price" value="${book.bid}:${book.bprice}" /></td>
	    					<td>${book.stock}
	    					<input type="hidden" name="stock" value="${book.bid}:${book.stock}" /></td>
	    					<td><img src="${book.image}" />
	    					<input type="hidden" name="image" value="${book.bid}:${book.image}" /></td>
	    				</tr>
	    			</c:forEach>
	  			</table>
	  			
	  			<%if(request.getAttribute("current_books_NO") != null){ %>
	  			<nav aria-label="Page navigation" class="text-center">
  					<ul class="pagination">
    					<%for(int i=1;i<=total_page;i++){ %>
						<%if(i == NO){ %>
							<li><a href="" class="current"><%=i %></a></li>
						<%continue;} %>
						<li><a href="books?current_books_NO=<%=i %>"><%=i %></a></li>
						<%} %>
  					</ul>
				</nav>
				<%} %>
	  			
	  			<%--<!-- <%if(request.getAttribute("current_books_NO") != null){ %>
				<div>
					<a href="books">首页</a>
					<%for(int i=0;i<=total_page;i++){ %>
						<%if(i == NO){ %>
							<span class="current"><%=i %></span>
						<%continue;} %>
						<a href="books?current_books_NO=<%=i %>"><%=i %></a>
				</div>
				<%} %>
					<a href="books?current_books_NO=<%=total_page %>">尾页</a>
				</div>
				<%} %> --> --%>
			<button type="submit" class="btn btn-default btn-success text-center pull-right">加入购物车</button>
			<!-- <div class="button"><input type="submit" name="submit" value="提交" /></div> -->
		</form>
    </div>
    
    
    
    <script src="./bootstrap/bootstrap.min.js"></script>
	<script src="./bootstrap/jquery-2.1.3.js"></script>
  </body>
</html>
