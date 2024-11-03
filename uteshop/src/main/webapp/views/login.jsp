<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@include file="Components/common_css_js.jsp"%>
<style>
#login-btn .btn-outline-primary {
    background-color: #007bff; /* Choose your desired color */
    color: white;
    border-color: #007bff; /* Match the border with the background color */
}

#login-btn .btn-outline-primary:hover {
    background-color: #0056b3; /* Darker shade on hover */
    color: white;
    border-color: #0056b3;
}

label {
    font-weight: bold;
}
.btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    padding: 10px 20px;
    border-radius: 5px;
    text-decoration: none;
    color: white;
    font-weight: bold;
    transition: background-color 0.3s;
}

.btn-facebook {
    background-color: #3b5998; /* Màu nền Facebook */
}

.btn-google {
    background-color: #db4437; /* Màu nền Google */
}

.btn:hover {
    opacity: 0.8; /* Hiệu ứng hover */
}

.btn i {
    margin-right: 8px; /* Khoảng cách giữa biểu tượng và văn bản */
}

</style>
</head>
<body>
<div class="container-fluid">
    <div class="row mt-5">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body px-5">
                    <div class="container text-center">
                        <img src="Images/login.png" style="max-width: 100px;" class="img-fluid">
                    </div>
                    <h3 class="text-center">Sign-In</h3>
                    <!-- login-form -->
                    <form id="login-form" action="login" method="post">
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
                    
                    <div class="text-center mt-3">
    <form action="${pageContext.request.contextPath}/login-facebook" method="POST" style="display: inline;">
        <button type="submit" class="btn btn-facebook">
            <i class="fab fa-facebook-f"></i> Login with Facebook
        </button>
    </form>
</div>

</div>
<div class="text-center mt-3">
    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/uteshop/login-google&response_type=code&client_id=313316268160-nj8kfng0k5fh4b3aib5ev041a06agf0q.apps.googleusercontent.com&approval_prompt=force" class="btn btn-google">
        <i class="fab fa-google"></i> Login with Google
    </a>
</div>
                    <div class="mt-3 text-center">
                        <h6><a href="views/forgot_password.jsp" style="text-decoration: none">Forgot Password?</a></h6>
                        <h6>
                            Don't have an account? <a href="views/register.jsp" style="text-decoration: none">Sign Up</a>
                        </h6>
                    </div>
                </div>  
            </div>
        </div>
    </div>
</div>
</body>
</html>
