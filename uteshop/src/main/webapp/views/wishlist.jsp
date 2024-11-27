
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container px-3 py-3">
	<c:choose>
        <c:when test="${empty wlist}">
	<div class="container mt-5 mb-5 text-center">
		<img src="Images/wishlist.png" style="max-width: 200px;"
			class="img-fluid">
		<h4 class="mt-3">Empty Wishlist</h4>
		You have no items in your wishlist. Start adding!
	</div>
	 </c:when>
	 <c:otherwise>
	<h4>
	My Wishlist (<c:out value="${fn:length(wlist)}" />)
	</h4>
	<hr>
  <div class="container">
                <table class="table table-hover">
                    <c:forEach var="p" items="${prodsinwishlist}">
                 
                        <tr class="text-center">
                            <td>
                                <img src="/uteshop/Images/${p.image}" style="width: 50px; height: 50px; width: auto;">
                            </td>
                            <td class="text-start">
                                <c:out value="${p.name}" />
                            </td>
                            <td>
                                &#8377;<c:out value="${p.price_after_discount}" />
                            </td>
                            <td>
                                <a href=" /uteshop/user/wishlist?uid=${sessionScope.activeUser.id}&pid=${p.id}&op=delete"
                                   class="btn btn-secondary" role="button">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>