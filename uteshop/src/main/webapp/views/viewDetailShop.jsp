<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View detail shop</title>
<style>
.cus-active {
	background-color: #e6eefa !important;
	width: 100%;
}

.list-btn {
	font-size: 20px !important;
}

.list-btn:hover {
	color: #2874f0 !important;
}

</style>
</head>
<body>
<%@include file="/Components/navbar.jsp"%>
<div class="container-fluid px-3 py-5">
		<div class="row">
			<div class="col-md-3">
				<div class="card">
					<div class="row mt-2 mb-2">
						<div class="col-md-4">
							<div class="container text-center">
							 
		   <c:if test="${requestScope.shop.logo != ''}">
                 
                          <c:if test ="${requestScope.shop.logo.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${requestScope.shop.logo}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${requestScope.shop.logo.substring(0,5) == 'https' }">
                            <c:url value="${requestScope.shop.logo}" var="imgUrl"></c:url>
                       </c:if>
                 </c:if>      
								<img src="${imgUrl }" style="max-width: 60px;"
									class="img-fluid">
							</div>
						</div>
						<div class="col-md-8">
							<h5>${requestScope.shop.name}</h5>
						</div>
					</div>  
				</div>
				<c:if test="${not empty message1}">
				<div class="message">
        <i class="fas fa-exclamation-circle"></i> ${message1}
    </div>
    </c:if>
    
				<div class="card mt-3">
					<div class="list-group">
						<button type="button" id="shopinf-btn"
							class="list-group-item list-group-item-action cus-active list-btn"
							aria-current="true">Shop Information</button>
							
  
							
						<button type="button" id="shoporder-btn"
							class="list-group-item list-group-item-action list-btn">Shop
							Orders</button>
						<button type="button" id="shopproduct-btn"
							class="list-group-item list-group-item-action list-btn">Shop
							Products</button>
						<button type="button" id="shoprevenue-btn"
							class="list-group-item list-group-item-action list-btn">Shop
							Revenue</button>
					</div>
				</div>
			</div>
		
			<div class="col-md-9">
				<div class="card">
					<div id="shopinf">
						<%@include file="shopInfo.jsp"%>
					</div>
					<div id="shoporder" style="display: none;">
					  <%@include file="shoporder.jsp"%> 
					</div>
					<div id="shopproduct" style="display: none;">
						<%@include file="shopproduct.jsp"%>    
					</div> 
					<div id="shoprevenue" style="display: none;" >
						<%@include file="shoprevenue.jsp"%>    
					</div>   
				</div>
			</div>  
			
		</div>
	</div>

	<script>
	
		$(document).ready(function() {

			$('#shopinf-btn').click(function() {

				$('#shopinf').show();
				$('#shoporder').hide();
				$('#shopproduct').hide();
				$('#shoprevenue').hide();
				
				$(this).addClass('cus-active');
				$('#shoporder-btn').removeClass('cus-active');
				$('#shopproduct-btn').removeClass('cus-active');
				$('#shoprevenue-btn').removeClass('cus-active');
				

			});
			$('#shoporder-btn').click(function() {

				$('#shoporder').show();
				$('#shopinf').hide();
				$('#shopproduct').hide();
				$('#shoprevenue').hide();
				
				$(this).addClass('cus-active');
				$('#shopinf-btn').removeClass('cus-active');
				$('#shopproduct-btn').removeClass('cus-active');
				$('#shoprevenue-btn').removeClass('cus-active');
			});
			$('#shopproduct-btn').click(function() {

				$('#shopproduct').show();
				$('#shoporder').hide();
				$('#shopinf').hide();
				$('#shoprevenue').hide();
				
				$(this).addClass('cus-active');
				$('#shoporder-btn').removeClass('cus-active');
				$('#shopinf-btn').removeClass('cus-active');
				$('#shoprevenue-btn').removeClass('cus-active');
				
			});
			$('#shoprevenue-btn').click(function() {

				$('#shoprevenue').show();
				$('#shopproduct').hide();
				$('#shoporder').hide();
				$('#shopinf').hide();
				
				$(this).addClass('cus-active');
				$('#shoporder-btn').removeClass('cus-active');
				$('#shopinf-btn').removeClass('cus-active');
				$('#shopproduct-btn').removeClass('cus-active');
			});
		});
		
		
	</script>
	
</body>
</html>