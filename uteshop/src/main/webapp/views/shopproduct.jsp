<div class="container-fluid px-3 py-3">
    <c:choose>
        <c:when test="${empty prodList}">
            <div class="container mt-5 mb-5 text-center">
                <img src="/uteshop/Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Product found</h4>
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
                                <td><span class="product-shop-owner-11">${prod.price}</span>&#8363;</td>
                                <td>${prod.quantity}</td>
                                <td>${prod.discount}%</td>
                                <td>
                                   <!-- modal ở đây nè :vv -->
    <button type="button" class="btn btn-secondary"
									  data-bs-toggle="modal" data-bs-target="#edit-product-shop"
									  data-product-id="${prod.id}"
									  data-product-name="${prod.name}"
									  data-product-description="${prod.description}"
									  data-product-price="${prod.price }"
									  data-product-discount="${prod.discount }"
									  data-product-quantity="${prod.quantity }"
									  data-product-categoryid="${prod.category_id }"
							
									  data-product-image="${prod.image }"
									><small>Update</small></button>
                  
                                    &emsp;
                                     <form action="/uteshop/user/deleteproduct?shopid=${prod.shop_id }" method="post" style="display: inline;">
                                        <input type="hidden" name="pid" value="${prod.id}">
                                          <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div class="text-center mb-3">
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-product-shop">
        Add New Product
    </button>
</div>
<!-- Add product modal-->
<div class="modal fade" id="add-product-shop" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="addProductModalLabel">Add Product Here</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/uteshop/user/addproduct?shopid=${requestScope.shop.id}" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <input type="hidden" name="operation" value="addProduct">
                    <div>
                        <label class="form-label"><b>Product Name</b></label> 
                        <input type="text" name="name" placeholder="Enter product name" class="form-control" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label"><b>Product Description</b></label>
                        <textarea class="form-control" name="description" rows="4" placeholder="Enter product description"></textarea>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Unit Price</b></label> 
                            <input type="number" name="price" placeholder="Enter price" class="form-control" required>
                        </div>
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Discount Percentage</b></label> 
                            <input type="number" name="discount" onblur="validate()" placeholder="Enter discount if any!" class="form-control" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Product Quantity</b></label> 
                            <input type="number" name="quantity" placeholder="Enter product quantity" class="form-control" required>
                        </div>
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Select Category Type</b></label>
                            <select name="categoryType" class="form-control">
                     
                                <c:forEach var="c" items="${categoryList}">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-2">
                        <label class="form-label"><b>Product Image</b></label> 
                         <input type="file" name="fileimage" onchange="chooseFile11(this)" ><br>
                        <img id="imagess11" src="" width="80" height="70" /><br>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary me-3">Add Product</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit product modal-->
<div class="modal fade" id="edit-product-shop" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="addProductModalLabel">Edit Product Here</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/uteshop/user/updateproduct?shopid=${requestScope.shop.id}" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <input type="hidden" name="operation" value="addProduct">
                    <div>
                        <label class="form-label"><b>Product ID</b></label> 
                        <input type="text" name="id" placeholder="Enter product id" class="form-control" id="modalproductId" required readonly>
                    </div>
                    <div>
                        <label class="form-label"><b>Product Name</b></label> 
                        <input type="text" name="name" placeholder="Enter product name" class="form-control" id="modalproductName" required>
                    </div>
                    <div class="mb-2">
                        <label class="form-label"><b>Product Description</b></label>
                        <textarea class="form-control" name="description" rows="4" id="modalproductDescription" placeholder="Enter product description"></textarea>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Unit Price</b></label> 
                            <input type="number" name="price" placeholder="Enter price" class="form-control" id="modalproductPrice" required>
                        </div>
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Discount Percentage</b></label> 
                            <input type="number" name="discount" onblur="validate()" placeholder="Enter discount if any!" id="modalproductDiscount" class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Product Quantity</b></label> 
                            <input type="number" name="quantity" placeholder="Enter product quantity" id="modalproductQuantity" class="form-control">
                        </div>
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><b>Select Category Type</b></label>
                             <select name="categoryType" class="form-control" id="modalproductCategoryId" required>
                                <option value="0">--Select Category--</option>
                                <c:forEach var="c" items="${categoryList}">
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-2">
                        <label class="form-label"><b>Product Image</b></label> 
                         <input type="file" name="fileimage" onchange="chooseFileModal(this)" ><br>
                        <img id="modalproductImage" width="80" height="70" /><br>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary me-3">Add Product</button>
                </div>
            </form>
        </div>
    </div>
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
    function chooseFile11(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                // Update the image source to the uploaded file's data URL
                $('#imagess11').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }
    function chooseFileModal(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                // Update the image source to the uploaded file's data URL
                $('#modalproductImage').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }
    document.addEventListener('DOMContentLoaded', function () {
        const editProductModal = document.getElementById('edit-product-shop');
        editProductModal.addEventListener('show.bs.modal', function (event) {
            // Lấy nút kích hoạt modal
            const button = event.relatedTarget;

            // Lấy dữ liệu từ data-*
            const productId = button.getAttribute('data-product-id');
            const productName = button.getAttribute('data-product-name');
            const productDescription = button.getAttribute('data-product-description');
            const productPrice = button.getAttribute('data-product-price');
            const productDiscount = button.getAttribute('data-product-discount');
            const productQuantity = button.getAttribute('data-product-quantity');
            const productCategoryId = button.getAttribute('data-product-categoryid');
    
            const productImage = button.getAttribute('data-product-image');
            
            // Gán dữ liệu vào modal
            const modalproductId = editProductModal.querySelector('#modalproductId');
            const modalproductName = editProductModal.querySelector('#modalproductName');
            const modalproductDescription = editProductModal.querySelector('#modalproductDescription');
            const modalproductPrice = editProductModal.querySelector('#modalproductPrice');
            const modalproductDiscount = editProductModal.querySelector('#modalproductDiscount');
            const modalproductQuantity = editProductModal.querySelector('#modalproductQuantity');
            const modalproductCategoryId = editProductModal.querySelector('#modalproductCategoryId');

            const modalproductImage = editProductModal.querySelector('#modalproductImage');

            modalproductId.value = productId;
            modalproductName.value = productName;
            modalproductDescription.value = productDescription;
            modalproductPrice.value = productPrice;
            modalproductDiscount.value = productDiscount;
            modalproductQuantity.value = productQuantity;
            modalproductCategoryId.value = productCategoryId;
            modalproductImage.src = '/uteshop/Images/' + productImage;
  
            
        });
    });
    function formatPrices(className) {
        var elements = document.querySelectorAll('.' + className);
        elements.forEach(function(element) {
            var value = element.innerText;

            // Loại bỏ ký hiệu tiền tệ (₫) và khoảng trắng
            value = value.replace('₫', '').trim();

         
                value = Number(value).toLocaleString('vi-VN');
                element.innerText = value;
        
        });
    }

    // Gọi hàm formatPrices cho từng loại giá
    formatPrices('product-shop-owner-11');
</script>
