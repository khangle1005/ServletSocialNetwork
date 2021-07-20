<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="./css/login.css?v=1" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <span><h1>Login</h1></span>
    </div>

    <!-- Login Form -->
    <form action="Login" method="post">
      <input type="text" class="fadeIn second" id="username" name="username" placeholder="username">
      <input type="text" class="fadeIn third" id="password" name="password" placeholder="password">
      <input type="submit" class="fadeIn fourth" value="Log In">
    </form>

    <!-- Register -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Register</a>
    </div>

  </div>
</div>
</body>
</html>