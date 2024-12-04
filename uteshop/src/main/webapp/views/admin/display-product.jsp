<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!--navbar -->
    <%@ include file="/Components/navbar.jsp" %>

    <!-- update product -->
    <div class="container mt-3">
    
        <table id="table-admin-product" class="table table-hover">
            <thead>
                <tr class="table-primary text-center" style="font-size: 20px;">
                    <th>Image</th>
                    <th>Name</th>
                    <th class="text-start">Category</th>
                    <th>Price</th>
                    <th class="text-start">Quantity</th>
                    <th class="text-start">Discount</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="prod" items="${productList}">
                    <tr class="text-center">
 
                        <td>
                         <c:if test="${prod.image != ''}">
                 
                          <c:if test ="${prod.image.substring(0,5) != 'https' }">
                         
                           <c:url value="/image?fname=${prod.image}" var="imgUrl"></c:url>
                        </c:if>

                       <c:if test ="${prod.image.substring(0,5) == 'https' }">
                            <c:url value="${prod.image}" var="imgUrl"></c:url>
                       </c:if>
                       </c:if>
                        <img src="${imgUrl}" style="width: 60px; height: 60px; width: auto;">
                        </td>
                        <td class="text-start"><c:out value="${prod.name}" /></td>
                        <td><c:out value="${prod.category_name}" /></td>
                        <td> <span class="real-price-product-admin">${prod.price}&#8363;</span>&ensp;</td>
                        <td><c:out value="${prod.quantity}" /></td>
                        <td><c:out value="${prod.discount}" />%</td>
                        <td>
                          
                             <form action="/uteshop/admin/product/delete" method="post" style="display:inline;">
                               <input type="hidden" name="pid" value="${prod.id}">
                               <button type="submit" class="btn btn-danger">Delete</button>
                           </form>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
    $(document).ready(function() {
	    $('#table-admin-product').DataTable({
	        "paging": true,        // Bật phân trang
	        "searching": true,    // Bật tìm kiếm
	        "ordering": true,     // Bật sắp xếp cột
	        "info": true,         // Hiển thị thông tin bảng
	        "lengthMenu": [5, 10, 25, 50], // Số hàng mỗi trang
	        "language": {
	            "zeroRecords": "No result found",
	            "infoEmpty": "No result",
	            "infoFiltered": "(lọc từ MAX dòng)",
	            "search": "Search",
	            "paginate": {
	                "first": "First",
	                "last": "Last",
	                "next": "Next",
	                "previous": "Previous"
	            }
	        }
	    });
	});
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
    formatPrices('real-price-product-admin');
    </script>
</body>
</html>