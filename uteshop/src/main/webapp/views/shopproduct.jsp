<div class="container-fluid px-3 py-3">
    <c:choose>
        <c:when test="${empty prodList}">
            <div class="container mt-5 mb-5 text-center">
                <img src="Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Order found</h4>
            </div>
        </c:when>

        <c:otherwise>
            <div class="container-fluid">
                <table id="table-product" class="table table-hover">
                    <thead>
                        <tr class="table-primary text-center" style="font-size: 20px;">
                            <th>Image</th>
                            <th>Name</th>
                            <th class="text-start">Category</th>
                            <th>Price</th>
                            <th class="text-start">Quantity</th>
                            <th class="text-start">Discount</th>
                            <th class="text-center">Action</th> <!-- Ensure correct column count here -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="prod" items="${requestScope.prodList}">
                            <tr class="text-center">
                                <td>
                                    <c:if test="${prod.image != ''}">
                                        <c:choose>
                                            <c:when test="${prod.image.substring(0, 5) != 'https'}">
                                                <c:url value="/image?fname=${prod.image}" var="imgUrl"></c:url>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="${prod.image}" var="imgUrl"></c:url>
                                            </c:otherwise>
                                        </c:choose>
                                        <img src="${imgUrl}" style="width: 60px; height: 60px; width: auto;">
                                    </c:if>
                                </td>
                                <td class="text-start">${prod.name}</td>
                                <td>${prod.category_name}</td>
                                <td>${prod.price}&#8377;</td>
                                <td>${prod.quantity}</td>
                                <td>${prod.discount}%</td>
                                <td>
                                    <a href="/uteshop/user/updateproduct?pid=${prod.id}" role="button" class="btn btn-secondary">Update</a>
                                    &emsp;
                                    <a href="/uteshop/user/deleteproduct?pid=${prod.id}" class="btn btn-danger" role="button">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script>
    $(document).ready(function() {
        $('#table-product').DataTable({
            "paging": true,        // Bật phân trang
            "searching": true,     // Bật tìm kiếm
            "ordering": true,      // Bật sắp xếp cột
            "info": true,          // Hiển thị thông tin bảng
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
</script>
