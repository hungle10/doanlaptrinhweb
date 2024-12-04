<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="/Components/common_css_js.jsp" %>
<style>
<%@include file="/CSS/style.css"%>
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>
<div class="container-fluid">
    <div class="row mt-5">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body px-5">
                    <div class="container text-center">
                        <img src="/uteshop/Images/login.png" style="max-width: 100px;" class="img-fluid">
                    </div>
                    <h3 class="text-center">Sign-In</h3>
                     <!-- Alert Message -->
<c:if test="${not empty alert}">
    <div class="alert">
        <i class="fas fa-exclamation-circle"></i> ${alert}
    </div>
       <c:remove var="alert" scope="session" />
</c:if>
                     <!-- Alert Message -->
<c:if test="${not empty message}">
    <div class="message">
        <i class="fas fa-exclamation-circle"></i> ${message}
    </div>
       <c:remove var="alert" scope="session" />
    
</c:if>
                    <!-- login-form -->
                    <form id="login-form" action="/uteshop/login" method="post">
                        <input type="hidden" name="login" value="user">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" name="username" placeholder="User name" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" placeholder="Enter your password" class="form-control" required>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" name="remember" class="form-check-input" id="rememberMe">
                            <label class="form-check-label checkbox-label" for="rememberMe">Remember Me</label>
                        </div>
                        <div id="login-btn" class="container text-center">
                            <button type="submit" class="btn btn-outline-primary">Login</button>
                        </div>
                    </form>
             </div>
                    <div class="mt-3 text-center">
                        <h6><a href="/uteshop/views/forgot_password.jsp" style="text-decoration: none">Forgot Password?</a></h6>
                        <h6>
                            Don't have an account? <a href="/uteshop/views/register.jsp" style="text-decoration: none">Sign Up</a>
                        </h6>
                    </div>
                </div>  
            </div>
        </div>
    </div>
</body>
</html>
