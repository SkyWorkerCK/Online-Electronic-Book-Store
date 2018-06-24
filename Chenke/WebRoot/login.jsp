<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="./bootstrap/bootstrap-theme.min.css" />
	<style>
		#content{
			margin-top: 200px;
		}
	</style>

  </head>
  
  <body>
    <div id="content">
	    <table style="border: 0px; margin: 0 auto;">
	    	<tr>
	    		<td><img src="./images/logo.gif"/></td>
	    	</tr>
	    </table>
	    
	    <form action="login" method="post" style="text-align:center">
  			<div class="form-group">
    			<label for="one"></label>
    			<input style="width:300px;margin: 0 auto" type="text" name="username" class="form-control" id="one" placeholder="用户名" />
  			</div>
  			<div class="form-group">
    			<label for="two"></label>
    			<input style="width:300px;margin: 0 auto" type="password" name="password" class="form-control" id="two" placeholder="密码" />
  			</div>
  			<button type="submit" class="btn btn-default">登录</button>
  			<button onclick="window.open('indexCK.jsp')" class="btn btn-default">注册</button>
  		</form>
	    	<!-- <table style="border: 0px; margin: 0 auto;">
	    		<tr>
	    			<td style="width:100px;font-size: 20px;">用&nbsp;户&nbsp;名&nbsp;：</td>
	    			<td style="width:100px;"><input type="text" name="username" /></td>
	    		</tr>
	    		<tr>
	    			<td style="width:100px;font-size: 20px;">密&nbsp;&nbsp;&nbsp;码：</td>
	    			<td style="width:100px;"><input type="password" name="password" /></td>
	    		</tr>
	    		<tr>
	    			<td style="width:100px;"><input type="submit" value="提交" /></td>
	    		</tr>
	    	</table>	 -->
    	</form>
    </div>
    
    <script src="./bootstrap/bootstrap.min.js"></script>
	<script src="./bootstrap/jquery-2.1.3.js"></script> 
  </body>
</html>
