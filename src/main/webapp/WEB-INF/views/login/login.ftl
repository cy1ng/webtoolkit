<!DOCTYPE html>

<#assign contextPath="${request.getContextPath()}">
<#assign base=contextPath?replace("(.*?//.*)/.*$","$1","r") >
<html>
<head>
	<title>登录</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	<!--bootstrap-->
	<link href="${base}/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="http://getbootstrap.com/assets/css/docs.min.css" rel="stylesheet"/>
</head>
<body>

 <div class="container">
   	    <form class="form-signin" action="login" method="post">
	        <h2 class="form-signin-heading">请登录</h2>
	        <label for="inputEmail" class="sr-only">用户名</label>
	        <input id="inputEmail" name="name" class="form-control" placeholder="用户名" required="" autofocus="" type="phone">
	        <label for="inputPassword" class="sr-only">密码</label>
	        <input id="inputPassword" name="password" class="form-control" placeholder="密码" required="" type="password">
	        <div class="checkbox">
	          <label>
	            <input value="remember-me" type="checkbox"> Remember me
	          </label>
	        </div>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
  </div>

<!--jquery-->
<script src="${base}/js/jquery-1.11.1.min.js"></script>
<!--bootstrap  js-->
<script src="${base}/js/bootstrap.js"></script>
</body>
</html>