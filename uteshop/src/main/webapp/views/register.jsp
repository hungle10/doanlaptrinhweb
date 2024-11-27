<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/Components/common_css_js.jsp" %>
<style>
label {
	font-weight: bold;
}
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>

	<div class="container-fluid mt-4">
		<div class="row g-0">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body px-5">
					<c:if test="${not empty alert}">
				<div class="message">
        <i class="fas fa-exclamation-circle"></i> ${alert}
    </div>
    </c:if>

						<div class="container text-center">
							<img src="/uteshop/Images/signUp.png" style="max-width: 80px;"
								class="img-fluid">
						</div>
						<h3 class="text-center">Create Account</h3>
				

						<!--registration-form-->
						<form id="register-form" action="/uteshop/register" method="post">
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">Your name</label> <input type="text"
										name="fullname" class="form-control"
										placeholder="First and last name" required>
								</div>
								<div class="col-md-6 mt-2">
                                       <label class="form-label">Date of Birth</label>
                                        <input type="date" name="dateOfBirth" class="form-control" required>
                                   </div>
							
								<div class="col-md-6 mt-2">
									<label class="form-label">Email</label> <input type="email"
										name="email" placeholder="Email address"
										class="form-control" required>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">Mobile number</label> <input
										type="number" name="phonenumber"
										placeholder="Mobile number" class="form-control">
								</div>
								    
							</div>
							<div class="mt-2">
								<label class="form-label">Address</label> <input type="text"
									name="address"
									placeholder="Enter Address(Area and Street))"
									class="form-control" required>
							</div>  
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">Password</label> <input
										type="password" name="password"
										placeholder="Enter Password" class="form-control" required>
								</div>
								<div class="col-md-6 mt-2">
									<label class="form-label">User name</label> <input type="text"
										name="username" class="form-control"
										placeholder="Enter your user name" required>
								</div>
							</div>

							<div id="submit-btn" class="container text-center mt-4">
								<button type="submit" class="btn btn-outline-primary me-3">Submit</button>
								<button type="reset" class="btn btn-outline-primary">Reset</button>
							</div>
							<div class="mt-3 text-center">
								<h6>
									Already have an account?<a href="/uteshop/views/login.jsp"
										style="text-decoration: none"> Sign in</a>
								</h6>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>