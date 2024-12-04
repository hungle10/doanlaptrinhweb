<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>

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
.alert {
    padding: 15px;
    margin: 10px 0;
    border-radius: 5px;
    font-size: 14px;
    font-weight: bold;
}

</style>


   

</head>
<body>


	<%@include file="/Components/navbar.jsp"%>
<!-- Alert Message -->
<c:if test="${not empty message}">
    <div class="message">
        <i class="fas fa-exclamation-circle"></i> ${message}
    </div>
    <c:remove var="message" scope="session" />
</c:if>
	<div class="container-fluid px-3 py-5">
		<div class="row">
			<div class="col-md-3">
				<div class="card">
					<div class="row mt-2 mb-2">
						<div class="col-md-4">
							<div class="container text-center">
							 
		   <c:if test="${sessionScope.activeUser.image != ''}">
                 
                          <c:if test ="${sessionScope.activeUser.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${sessionScope.activeUser.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${sessionScope.activeUser.image.substring(0,5) == 'https' }">
                            <c:url value="${sessionScope.activeUser.image}" var="imgUrl"></c:url>
                       </c:if>
                 </c:if>      
								<img src="${imgUrl }" style="max-width: 60px;"
									class="img-fluid">
							</div>
						</div>
						<div class="col-md-8">
							Hello, <br>
							<h5>${sessionScope.activeUser.username}</h5>
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
						<button type="button" id="profile-btn"
							class="list-group-item list-group-item-action cus-active list-btn"
							aria-current="true">Profile Information</button>
							
  
							
						<button type="button" id="wishlist-btn"
							class="list-group-item list-group-item-action list-btn">My
							Wishlist</button>
						<button type="button" id="order-btn"
							class="list-group-item list-group-item-action list-btn">My
							Orders</button>
						<button type="button" id="shops-btn"
							class="list-group-item list-group-item-action list-btn">My
							Shops</button>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="card">
					<div id="profile">
						
						<%@include file="personalInfo.jsp"%>
					</div>
					<div id="wishlist" style="display: none;">
					  <%@include file="wishlist.jsp"%> 
					</div>
					<div id="order" style="display: none;">
						<%@include file="order.jsp"%>    
					</div> 
					<div id="shops" style="display: none;">
						<%@include file="shops.jsp"%>    
					</div>   
				</div>
			</div>  
		</div>
	</div>

	<script>
	
		$(document).ready(function() {

			$('#profile-btn').click(function() {

				$('#profile').show();
				$('#wishlist').hide();
				$('#order').hide();
				$('#shops').hide();
				
				$(this).addClass('cus-active');
				$('#wishlist-btn').removeClass('cus-active');
				$('#order-btn').removeClass('cus-active');
				$('#shops-btn').removeClass('cus-active');
				

			});
			$('#shops-btn').click(function() {

				$('#shops').show();
				$('#profile').hide();
				$('#wishlist').hide();
				$('#order').hide();
				
				$(this).addClass('cus-active');
				$('#wishlist-btn').removeClass('cus-active');
				$('#order-btn').removeClass('cus-active');
				$('#profile-btn').removeClass('cus-active');
			});
			$('#wishlist-btn').click(function() {

				$('#wishlist').show();
				$('#profile').hide();
				$('#order').hide();
				$('#shops').hide();
				
				$(this).addClass('cus-active');
				$('#profile-btn').removeClass('cus-active');
				$('#order-btn').removeClass('cus-active');
				$('#shops-btn').removeClass('cus-active');
				
			});
			$('#order-btn').click(function() {

				$('#order').show();
				$('#profile').hide();
				$('#wishlist').hide();
				$('#shops').hide();
				
				$(this).addClass('cus-active');
				$('#profile-btn').removeClass('cus-active');
				$('#wishlist-btn').removeClass('cus-active');
				$('#shops-btn').removeClass('cus-active');
			});
		});
		
		 $(document).ready(function() {
	            $('#tableOrder').DataTable({
	                "paging": true,        // Bật phân trang
	                "searching": true,    // Bật tìm kiếm
	                "ordering": true,     // Bật sắp xếp cột
	                "info": true,         // Hiển thị thông tin bảng
	                "lengthMenu": [5, 10, 25, 50], // Số hàng mỗi trang
	                "language": {
	                    "zeroRecords": "Không tìm thấy dữ liệu",
	                    "infoEmpty": "Không có dữ liệu",
	                    "infoFiltered": "(lọc từ MAX dòng)",
	                    "search": "Tìm kiếm:",
	                    "paginate": {
	                        "first": "Đầu",
	                        "last": "Cuối",
	                        "next": "Tiếp",
	                        "previous": "Trước"
	                    }
	                }
	            });
	        });
		 $(document).ready(function() {
	            $('#tableShop').DataTable({
	                "paging": true,        // Bật phân trang
	                "searching": true,    // Bật tìm kiếm
	                "ordering": true,     // Bật sắp xếp cột
	                "info": true,         // Hiển thị thông tin bảng
	                "lengthMenu": [5, 10, 25, 50], // Số hàng mỗi trang
	                "language": {
	                    "zeroRecords": "Không tìm thấy dữ liệu",
	                    "infoEmpty": "Không có dữ liệu",
	                    "infoFiltered": "(lọc từ MAX dòng)",
	                    "search": "Tìm kiếm:",
	                    "paginate": {
	                        "first": "Đầu",
	                        "last": "Cuối",
	                        "next": "Tiếp",
	                        "previous": "Trước"
	                    }
	                }
	            });
	        });
	</script>
	
</body>
</html>