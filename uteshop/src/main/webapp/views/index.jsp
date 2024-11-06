
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<%@include file="/Components/common_css_js.jsp"%>
<style type="text/css">
<%@include file="/CSS/style.css"%>
</style>
</head>
<body>
<%@include file="/Components/navbar.jsp"%>

<div class="container-fluid px-3 py-3" style="background-color: #e3f7fc;">
    <div class="row">
        <!-- Loop through category list -->
        <c:forEach var="category" items="${sessionScope.categoryList}">
            <div class="col-md-3 text-center mb-3">
                <a href="<c:url value='products.jsp'><c:param name='category' value='${category.id}'/></c:url>" 
                   style="text-decoration: none;">
                    <div class="card cus-card h-100">
                        <div class="container text-center">
                            <img src="<c:url value='/Images/${category.image}'/>" class="mt-3" 
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
                    <a href="viewProduct.jsp?pid=${product.id}" style="text-decoration: none;">
                        <div class="card h-100">
                            <div class="container text-center">
                                <img src="/uteshop/Images/${product.image}" class="card-img-top m-2" style="max-width: 100%; max-height: 200px; width: auto;">     
                       </div>
                         <div class="card-body">
                                <h5 class="card-title text-center">
                                    <c:out value="${product.name}" />
                                </h5>
                                <div class="container text-center">
                                    <!-- Display price, discount, and final price -->
                                    <span class="real-price">
                                        <c:out value="${product.price_after_discount}" />&#8363;
                                    </span>
                                    &ensp;
                                    <span class="product-price">
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


<!-- product with heavy deals -->
<div class="container-fluid py-3 px-3" style="background: #f0fffe;">
    <h3>Hot Deals</h3>
    <div class="row row-cols-1 row-cols-md-4 g-3">
        <c:forEach var="deal" items="${sessionScope.topDeals}" varStatus="status">
            <c:if test="${status.index < 4}">
                <div class="col">
                    <a href="viewProduct.jsp?pid=${deal.id}" style="text-decoration: none;">
                        <div class="card h-100">
                            <div class="container text-center">
                                <img src="/uteshop/Images/${deal.image}" class="card-img-top m-2"
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
  <!-- Site footer -->
    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 col-md-6">
            <h6>About</h6>
            <p class="text-justify">
    <strong>UTEShop</strong> - <i>Điểm Đến Mua Sắm Hoàn Hảo</i> dành cho những ai yêu thích sự tiện lợi và chất lượng. Tại UTEShop, chúng tôi cam kết mang đến trải nghiệm mua sắm dễ dàng và thú vị với hàng ngàn sản phẩm từ thời trang, điện tử, đồ gia dụng cho đến sức khỏe và làm đẹp. Chúng tôi luôn ưu tiên chất lượng và sự hài lòng của khách hàng, giúp bạn dễ dàng tìm thấy mọi thứ bạn cần với giá cả hợp lý. Khám phá ngay để tận hưởng một cách mua sắm mới mẻ và tiện lợi!
</p>
          </div>
          <div class="col-xs-6 col-md-3">
            <h6>Quick Links</h6>
            <ul class="footer-links">
              <li><a href="https://www.facebook.com/ThaiHung270804/?locale=vi_VN">Contact Us</a></li>
            </ul>
          </div>
        </div>
        <hr>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-sm-6 col-xs-12">
            <p class="copyright-text">Copyright &copy; 2024 All Rights Reserved by 
         <a href="#">UteShop</a>.
            </p>
          </div>
        </div>
      </div>
</footer>


</body>
</html>