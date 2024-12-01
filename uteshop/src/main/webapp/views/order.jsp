<div class="container-fluid px-3 py-3">
    <c:choose>
      
        <c:when test="${empty orderList}">
            <div class="container mt-5 mb-5 text-center">
                <img src="/uteshop/Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Order found</h4>
                Looks like you haven't placed any order!
            </div>
        </c:when>
        <c:otherwise>
            <h4>My Order</h4>
            <hr>
            <div class="container">
                <table id="tableOrder" class="table table-hover">
                <thead>
                    <tr class="text-center table-secondary">
                        <th>Product</th>
                        <th>Order ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Date and Time</th>
                        <th>Payment status</th>
                        <th>Payment Type</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                   <tbody>
                    <c:forEach var="order" items="${orderList}">
              
                        <c:forEach var="orderProduct" items="${order.order_detail_list}">
                            <tr class="text-center">
                                <td>
                                    <img src="/uteshop/Images/${orderProduct.product.image}" style="width: 40px; height: 40px; width: auto;">
                                </td>
                                <td class="text-start">${order.id}</td>
                                <td class="text-start">${orderProduct.product.name}</td>
                                <td>${orderProduct.numberOfProducts}</td>
                                <td>${orderProduct.product.price_after_discount * orderProduct.numberOfProducts}</td>
                                <td>${order.orderdate}</td>
                                <td>${order.payment_status}</td>
                                <td>${order.payment_method}</td>
                               <td class="fw-semibold">
                                    <c:choose>
                                         <c:when test="${orderProduct.status == 'Pending'}">
                                              <span style="color: red;">${orderProduct.status}</span>
                                        </c:when>
                                        <c:when test="${orderProduct.status == 'Cancelled'}">
                                           <span style="color: red;">${orderProduct.status}</span>
                                              </c:when>
                                       <c:when test="${orderProduct.status == 'Returned-Refunded'}">
                                      <span style="color: yellow;">${orderProduct.status}</span>
                                    </c:when>
                                <c:otherwise>
                                <span style="color: green;">${orderProduct.status}</span>
                               </c:otherwise>
                          </c:choose>
                              </td>

 
                            </tr>
                        </c:forEach>
                    </c:forEach>
                      </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
    
</div>
