<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/Components/common_css_js.jsp"%>
<style type="text/css">
<%@include file="/CSS/style.css"%>
</style>
</head>

<body>
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>
<div class="container text-center py-5 px-5">
		<img src="Images/empty-cart.png" style="max-width: 250px;"
			class="img-fluid">
		<h4 class="mt-5">Your cart is empty!</h4>
		<p>Add items to it now.</p>
		<a href="products.jsp" class="btn btn-primary btn-lg" role="button"
			aria-disabled="true">Shop Now</a>
	</div>
</body>
</html>