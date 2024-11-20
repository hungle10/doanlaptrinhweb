<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Code active account</title>
<style>
label {
	font-weight: bold;
}
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>

	<div class="container-fluid ">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body px-5">

						<div class="container text-center">
							<img src="Images/forgot-password.png" style="max-width: 100px;"
								class="img-fluid">
						</div>
						<h3 class="text-center mt-3">Nhập OTP để kích hoạt tài khoản của bạn</h3>
						      <!-- Alert Message -->
<c:if test="${not empty message}">
    <div class="alert">
        <i class="fas fa-exclamation-circle"></i> ${message}
    </div>
</c:if>

						<form action="/uteshop/register/otpcode" method="post">
							<div class="mb-3">
								<label class="form-label">OTP</label> <input type="number"
									name="code" placeholder="Enter verification code" class="form-control"
									required>
							</div>
							<div class="container text-center">
								<button type="submit" class="btn btn-outline-primary me-3">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>