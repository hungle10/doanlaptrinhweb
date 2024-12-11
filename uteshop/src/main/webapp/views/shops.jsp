
<div class="container-fluid px-3 py-3">
    <c:choose>
        <c:when test="${shopList == null || shopList.isEmpty()}">
            <div class="container mt-5 mb-5 text-center">
                <img src="/uteshop/Images/emptyshop.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Shop found</h4>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container-fluid">
                <table id="tableShop" class="table table-hover table-sm small">
                <thead>
                    <tr class="table-primary" style="font-size: 18px;">
                    <th class="text-center">Shop</th>
                    <th>Shop ID</th>
					<th>Shop name</th>
	
					<th>Address</th>
					<th>Phone number</th>
					<th>Email</th>
					<th>Status</th>
                    <th colspan="1" class="text-center">Action</th>    
                    <th colspan="1" class="text-center">Xoa</th>  
                    </tr>
                  </thead>
                   <tbody>
                    <c:forEach var="shop" items="${shopList}">

                                <tr>
                                   <form action="/uteshop/user/viewshop?shopid=${shop.id}" method="post">
                                    <td class="text-center">
                                    <c:if test="${shop.logo != ''}">
                                        <c:choose>
                                            <c:when test="${shop.logo.substring(0, 5) != 'https'}">
                                                <c:url value="/image?fname=${shop.logo}" var="imgUrl"></c:url>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="${shop.logo}" var="imgUrl"></c:url>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                        <img src="${imgUrl}" style="width: 50px; height: 50px; width: auto;">
                                    </td>
                                    <td>${shop.id}</td>
                                    <td>
                                        ${shop.name}
                                    </td>
                                 
                                    <td>${shop.address}</td>
                                    <td>${shop.phone_number}</td>
                                    <td>${shop.email}</td>
                                   <td>
    <c:choose>
        <c:when test="${shop.is_active == false}">
            <span style="color: red;">Pending</span>
        </c:when>
        <c:when test="${shop.is_active == true}">
            <span style="color: green;">Accepted</span>
        </c:when>
        <c:otherwise>
            Unknown Status
        </c:otherwise>
    </c:choose>
</td>

                                    <td>
                                       <button type="submit" class="btn btn-secondary">Watch more detail</button>
                                      
                                    </td>
                                   
                                    </form>
                                     <td>
                                     <form action="/uteshop/user/deleteshop?shopid=${shop.id}" method="post">
                                           <button type="submit" class="btn btn-secondary">Delete</button>
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
