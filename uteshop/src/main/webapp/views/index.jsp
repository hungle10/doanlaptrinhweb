
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<!--navbar -->
	<%@include file="/Components/navbar.jsp"%>

<div class="container-fluid px-3 py-3" style="background-color: #e3f7fc;">
    <div class="row">
        <!-- Loop through category list -->
        <c:forEach var="category" items="${sessionScope.categoryList}">
            <div class="col-md-3 text-center mb-3">
                <a href="<c:url value='/user/products'><c:param name='category' value='${category.id}'/></c:url>" 
                   style="text-decoration: none;">
                    <div class="card cus-card h-100">
                        <div class="container text-center">
                        <c:if test="${category.image != ''}">
                                        <c:choose>
                                            <c:when test="${category.image.substring(0, 5) != 'https'}">
                                                <c:url value="/image?fname=${category.image}" var="imgUrl"></c:url>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="${category.image}" var="imgUrl"></c:url>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                            <img src="${imgUrl }" class="mt-3" 
                                 style="max-width: 100%; max-height: 100px; width: auto; height: auto;">
                        </div>
                        <h6><c:out value="${category.name}"/></h6>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<!-- Carousel -->
	<div id="carouselAutoplaying"
		class="carousel slide carousel-dark mt-3 mb-3" data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="/uteshop/Images/scroll_img2.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="/uteshop/Images/scroll_img1.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="/uteshop/Images/scroll_img3.png" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselAutoplaying" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"
				style="color: black;"></span> <span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselAutoplaying" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
<!-- end of carousel --

<!-- Latest Products Listed -->
<div class="container-fluid py-3 px-3" style="background: #f2f2f2;">
    <div class="row row-cols-1 row-cols-md-4 g-3">
        <!-- Static Latest Products Heading -->
        <div class="col">
            <div class="container text-center px-5 py-5">
                <h1>Latest Products</h1>
                <img src="Images/product.png" class="card-img-top" style="max-width: 100%; max-height: 200px; width: auto;">
            </div>
        </div>

        <!-- Loop through productList -->
        <c:forEach var="product" items="${sessionScope.productList}" varStatus="status">
            <c:if test="${status.index < 3}">
                <div class="col">
                    <a href="/uteshop/view/product?pid=${product.id}" style="text-decoration: none;">
                        <div class="card h-100">
                            <div class="container text-center">
                            <c:if test="${product.image != ''}">
                                        <c:choose>
                                            <c:when test="${product.image.substring(0, 5) != 'https'}">
                                                <c:url value="/image?fname=${product.image}" var="imgUrl"></c:url>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="${product.image}" var="imgUrl"></c:url>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                <img src="${imgUrl}" class="card-img-top m-2" style="max-width: 100%; max-height: 200px; width: auto;">     
                       </div>
                         <div class="card-body">
                                <h5 class="card-title text-center">
                                    <c:out value="${product.name}" />
                                </h5>
                                <div class="container text-center">
                                    <!-- Display price, discount, and final price -->
                                    <span id="real-price" class="real-price">
                                        <c:out value="${product.price_after_discount}" />&#8363;
                                    </span>
                                    &ensp;
                                    <span id = "product-price" class="product-price">
                                        <c:out value="${product.price}" />&#8363;
                                    </span>
                                    &ensp;
                                    <span class="product-discount">
                                        <c:out value="${product.discount}" />&#37; off
                                    </span>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
<!-- end of list -->


<!-- product sold over ten -->
<div class="container-fluid py-3 px-3" style="background: #f0fffe;">
   <h3>Top sản phẩm hot nhất từ các Shop</h3>
    <div class="row row-cols-1 row-cols-md-4 g-3">
        <c:forEach var="deal" items="${sessionScope.topProductSold}" varStatus="status">
            <c:if test="${status.index < 4}">
                <div class="col">
                    <a href="/uteshop/view/product?pid=${deal.id}" style="text-decoration: none;">
                        <div class="card h-100">
                            <div class="container text-center">
                             <c:if test="${deal.image != ''}">
                                        <c:choose>
                                            <c:when test="${deal.image.substring(0, 5) != 'https'}">
                                                <c:url value="/image?fname=${deal.image}" var="imgUrl"></c:url>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="${deal.image}" var="imgUrl"></c:url>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                <img src="${imgUrl}" class="card-img-top m-2"
                                     style="max-width: 100%; max-height: 200px; width: auto;">
                            </div>
                            <div class="card-body">
                                <h5 class="card-title text-center">${deal.name}</h5>

                                <div class="container text-center">
                                    <span class="real-price">
                                        <c:out value="${deal.price_after_discount}" />
                                    </span>&#8363;
                                    &ensp;<span class="product-price">${deal.price}&#8363;</span>
                                    &ensp;<span class="product-discount">${deal.discount}&#37; off</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
<!-- end -->
 
<!-- confirmation message for successful order -->
<c:if test="${not empty sessionScope.order}">
    <script type="text/javascript">
        console.log("testing..4...");
        Swal.fire({
          icon : 'success',
          title: 'Order Placed, Thank you!',
          text: 'Confirmation will be sent to ${user.email}',
          width: 600,
          padding: '3em',
          showConfirmButton : false,
          timer : 3500,
          backdrop: `
            rgba(0,0,123,0.4)
          `
        });
    </script>
    <!-- Xóa giá trị của "order" trong session -->
    <c:remove var="order" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.orderfail}">
    <script type="text/javascript">
        console.log("testing..4...");
        Swal.fire({
          icon : 'error',
          title: 'Order fail, There was some error!',
          text: 'Please try again, your order is not done',
          width: 600,
          padding: '3em',
          showConfirmButton : false,
          timer : 3500,
          backdrop: `
            rgba(0,0,123,0.4)
          `
        });
    </script>
    <!-- Xóa giá trị của "order" trong session -->
    <c:remove var="orderfail" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.orderpaymentcancel}">
    <script type="text/javascript">
        console.log("testing..4...");
        Swal.fire({
          icon : 'error',
          title: 'Order is canceled!',
          text: 'Your order is canceled',
          width: 600,
          padding: '3em',
          showConfirmButton : false,
          timer : 3500,
          backdrop: `
            rgba(0,0,123,0.4)
          `
        });
    </script>
    <!-- Xóa giá trị của "order" trong session -->
    <c:remove var="orderpaymentcancel" scope="session"/>
</c:if>
<!-- end of message -->
<c:if test="${not empty sessionScope.shopregistersuccess}">
    <script type="text/javascript">
        console.log("Shop registration notification...");
        Swal.fire({
            icon: 'success',
            title: 'Registration Successful!',
            text: '${sessionScope.shopregistersuccess}',
            text: 'Confirmation will be sent to ${user.email}',
            width: 600,
            padding: '3em',
            showConfirmButton: false,
            timer: 3500,
            backdrop: `
                rgba(0,0,123,0.4)
            `
        });
    </script>
    <!-- Xóa giá trị của "shopregistersuccess" trong session -->
    <c:remove var="shopregistersuccess" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.shopregisterfail}">
    <script type="text/javascript">
        console.log("Shop registration failed notification...");
        Swal.fire({
            icon: 'error',
            title: 'Registration Failed!',
            text: '${sessionScope.shopregisterfail}',
            width: 600,
            padding: '3em',
            showConfirmButton: false,
            timer: 3500,
            backdrop: `
                rgba(255,0,0,0.4)
            `
        });
    </script>
    <!-- Xóa giá trị của "shopregisterfail" trong session -->
    <c:remove var="shopregisterfail" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.shopeditfail}">
    <script type="text/javascript">
        console.log("Shop edit failed notification...");
        Swal.fire({
            icon: 'error',
            title: 'Registration Failed!',
            text: '${sessionScope.shopeditfail}',
            width: 600,
            padding: '3em',
            showConfirmButton: false,
            timer: 3500,
            backdrop: `
                rgba(255,0,0,0.4)
            `
        });
    </script>
    <!-- Xóa giá trị của "shopregisterfail" trong session -->
    <c:remove var="shopeditfail" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.shopeditsuccess}">
    <script type="text/javascript">
        console.log("Shop registration notification...");
        Swal.fire({
            icon: 'success',
            title: 'Shop has been edited successfully!',
            text: '${sessionScope.shopeditsuccess}',
            width: 600,
            padding: '3em',
            showConfirmButton: false,
            timer: 3500,
            backdrop: `
                rgba(0,0,123,0.4)
            `
        });
    </script>
    <!-- Xóa giá trị của "shopregistersuccess" trong session -->
    <c:remove var="shopeditsuccess" scope="session"/>
</c:if>
<script>
function formatPrices(className) {
    var elements = document.querySelectorAll('.' + className);
    elements.forEach(function(element) {
        var value = element.innerText;

        // Loại bỏ ký hiệu tiền tệ (₫) và khoảng trắng
        value = value.replace('₫', '').trim();

     
            value = Number(value).toLocaleString('vi-VN') + '₫';
            element.innerText = value;
    
    });
}

// Gọi hàm formatPrices cho từng loại giá
formatPrices('real-price');
formatPrices('product-price');

</script>

</body>
</html>