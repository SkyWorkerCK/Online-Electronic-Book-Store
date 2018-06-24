<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<style>
		#content{
			margin-top: 200px;
		}
	</style>
	<script>
		
		function checkSame(){
			var sub = document.getElementById("sub");
			var text1 = document.getElementById("pass");
			var text2 = document.getElementById("firm_pass");
			var bool=true;
		
			if(text1 != text2){
				document.getElementById("information").innerHTML="<font color='red'>两次密码不相同</font>";
				bool = false;
			}else if(text1 == text2){
				bool = true;
			}
			return bool;
		}
		
		
	</script>
  </head>
  
  <body>
    <div id="content">
	    <table style="border: 0px; margin: 0 auto;">
	    	<tr>
	    		<td><img src="./images/logo.gif"/></td>
	    	</tr>
	    </table>
	    
	    <form action="regist" method="post" style="text-align:center">
  			<div class="form-group">
    			<label for="one"></label>
    			<input style="width:300px;margin: 0 auto" type="text" name="username" class="form-control" id="one" placeholder="请输入用户名" />
  			</div>
  			<div class="form-group">
    			<label for="two"></label>
    			<input style="width:300px;margin: 0 auto" type="password" name="password" class="form-control" id="two" placeholder="请输入密码" />
  			</div>
  			<div class="form-group">
    			<label for="three"></label>
    			<input style="width:300px;margin: 0 auto" type="password" name="password" class="form-control" id="three" placeholder="请确认密码" />
  			</div>
  			<div class="form-group">
    			<label for="four"></label>
    			<input style="width:300px;margin: 0 auto" type="text" name="password" class="form-control" id="four" placeholder="请输入邮箱" />
  			</div>
  			<button type="submit" id="sub" onclick="checkSame();" class="btn btn-default">注册</button>
  		</form>
    	
    	
    	<script src="./bootstrap/bootstrap.min.js"></script>
		<script src="./bootstrap/jquery-2.1.3.js"></script>
    </div>
  </body>
</html>
