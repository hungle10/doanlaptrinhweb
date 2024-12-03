<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/Components/common_css_js.jsp" %>

<style>
<%@include file="/CSS/style.css"%>



</style>
</head>
<body style="background-color: #f0f0f0;">
	<!--navbar -->
	<%@include file="/Components/navbar.jsp"%> <!-- Header -->
	<!-- them if test thuoc tinh -->
	<c:if test="${not empty shopmodel}">
      <div class="shop-info">
        <div class="shop-details">
            <div class="shop-logo">
            
               <c:if test="${shopmodel.logo != ''}">
                 
                          <c:if test ="${shopmodel.logo.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${shopmodel.logo}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${shopmodel.logo.substring(0,5) == 'https' }">
                            <c:url value="${shopmodel.logo}" var="imgUrl"></c:url>
                       </c:if>
                        <img src="${imgUrl}" alt="Unilever Logo">
                 </c:if>      
               
            </div>
            <div class="shop-name">
                <h1>${shopmodel.name }</h1>
            
            </div>
           <div class="shop-actions">
               <button onclick="location.href='/uteshop/user/chat?vendorid=${shopmodel.vendor_id}'">Chat</button>
         </div>
        </div>
        <div class="shop-stats">
            <p>Tham gia: <span>${shopmodel.created_at }</span></p>
            <p>Miêu tả: <span>${shopmodel.description }</span></p>
        </div>
    </div>
</c:if>
 

    
	<!--show products-->
	<h4 class="text-center mt-2">${requestScope.message}</h4>
	<div class="container-fluid my-3 px-5">

		<div class="row row-cols-1 row-cols-md-4 g-3">
			<c:forEach var="p" items="${requestScope.prodListFiltered}">
				<div class="col">
					<div class="card h-100 px-2 py-2">
						<div class="container text-center">
						 <c:if test="${p.image != ''}">
                 
                          <c:if test ="${p.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${p.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${p.image.substring(0,5) == 'https' }">
                            <c:url value="${p.image}" var="imgUrl"></c:url>
                       </c:if>
                       </c:if>      
							<img src="${imgUrl}"
								class="card-img-top m-2"
								style="max-width: 100%; max-height: 200px; width: auto;">
							<div class="wishlist-icon">
							
							 <c:if test="${fn:contains(requestScope.prodListWish, p)}">    <!--on click ma js scipt -->
									        	<button onclick="window.open('/uteshop/user/wishlist?uid=${sessionScope.activeUser.id}&pid=${p.id}&op=remove', '_self')"
													class="btn btn-link" type="submit">
													<i class="fa-sharp fa-solid fa-heart" style="color: #ff0303;"></i>
												</button>
							 </c:if>
							  <c:if test="${fn:contains(requestScope.prodListUnWish, p)}">
							    <button onclick="window.open('/uteshop/user/wishlist?uid=${sessionScope.activeUser.id}&pid=${p.id}&op=add', '_self')"
													class="btn btn-link" type="submit">
													<i class="fa-sharp fa-solid fa-heart" style="color: #909191;"></i>
												</button>
							  </c:if>
							</div>
							<h5 class="card-title text-center">${p.name}</h5>
							<div class="container text-center">
								<span class="real-price">${p.price_after_discount}&#8363;</span>&ensp;
								<span class="product-price">${p.price}&#8363;</span>&ensp;
								<span class="product-discount">${p.discount}&#37;off</span>
							</div>
							<div class="container text-center mb-2 mt-2">
								<button type="button"
									onclick="window.open('/uteshop/view/product?pid=${p.id}', '_self')"
									class="btn btn-primary text-white">View Details</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	<div class="container d-flex justify-content-center my-3">             
  <ul class="pagination">
   <!-- Previous Button -->
    <c:if test="${requestScope.currentpage > 1}">
        <li class="page-item">
            <a class="page-link" href="/uteshop/user/products?page=${requestScope.currentpage - 1}">Previous</a>
        </li>
    </c:if>
  <c:forEach var="i" begin="1" end="${requestScope.endPage}" step="1">
       <li class="page-item"><a class="page-link" href="/uteshop/user/products?page=${i}">${i}</a></li>
    </c:forEach>
    <!-- Next Button -->
    <c:if test="${requestScope.currentpage < requestScope.endPage}">
        <li class="page-item">
            <a class="page-link" href="/uteshop/user/products?page=${requestScope.currentpage + 1}">Next</a>
        </li>
    </c:if>
  </ul>
</div>
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