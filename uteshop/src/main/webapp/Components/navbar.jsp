  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
  
<style>
.navbar {
	font-weight: 500;
}

.nav-link {
	color: rgb(255 255 255/ 100%) !important;
}

.dropdown-menu {
	background-color: #ffffff !important;
	border-color: #949494;
}

.dropdown-menu li a:hover {
	background-color: #808080 !important;
	color: white;
}
</style>
<nav class="navbar navbar-expand-lg custom-color" data-bs-theme="dark">
	<!-- admin nav bar -->
	<c:if test="${not empty sessionScope.activeAdmin}">
    <div class="container">
        <a class="navbar-brand" href="/uteshop/admin/home">
            <i class="fa-sharp fa-solid fa-house" style="color: #ffffff;"></i>&ensp;UTEShop
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="container text-end">
                <ul class="navbar-nav justify-content-end">
                 	<li class="nav-item"><button type="button"
							class="btn nav-link" data-bs-toggle="modal"
							data-bs-target="#add-category"><i class="fa-solid fa-plus fa-xs"></i>Add Category</button></li>
					<li class="nav-item"><button type="button"
							class="btn nav-link" data-bs-toggle="modal"
							data-bs-target="#add-shippingunit"><i class="fa-solid fa-plus fa-xs"></i>Add Shipping Unit</button></li>
        
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="admin.jsp">
                            <c:out value="${activeAdmin.username}"/>
                        </a>
                    </li>
                   
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/uteshop/login?action=logout">
                            <i class="fa-solid fa-user-slash fa-sm" style="color: #fafafa;"></i>&nbsp;Logout
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${empty sessionScope.activeAdmin}">
    <!-- Navbar visible to all users -->
    <div class="container">
        <a class="navbar-brand" href="/uteshop/home">
            <i class="fa-sharp fa-solid fa-house" style="color: #ffffff;"></i>&ensp;UTEShop
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="/uteshop/user/products?category=0"
                    role="button" aria-expanded="false"> Products </a>
                </li>
          

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false"> Category </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/uteshop/user/products?category=0">All Products</a></li>
                        <c:forEach var="category" items="${categoryList}">
                            <li>
                                <a class="dropdown-item" href="/uteshop/user/products?category=${category.id}">
                                    <c:out value="${category.name}"/>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
            
            <form class="d-flex pe-5 p-3" role="search" action="/uteshop/user/products" method="get">
                <div class="input-group">
                    <input name="search" class="form-control me-2" size="50"
                           type="search" placeholder="Search for products" aria-label="Search"
                           style="background-color: white !important;">
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </div>
            </form>

            <!-- Display user-specific links if logged in -->
            <c:choose>
                <c:when test="${not empty sessionScope.activeUser}">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active pe-3">
                            <a class="nav-link position-relative" aria-current="page" href="/uteshop/user/cart">
                                <i class="fa-solid fa-cart-shopping" style="color: #ffffff;"></i> &nbsp;Cart
                                <span class="position-absolute top-1 start-0 translate-middle badge rounded-pill bg-danger">
                                    ${sessionScope.cartCount}
                                </span>
                            </a>
                        </li>
                        <li class="nav-item active pe-3">
                            <a class="nav-link" aria-current="page" href="/uteshop/user/profile">
                                <c:out value="${sessionScope.activeUser.username}"/>
                            </a>
                        </li>
                        <li class="nav-item">
                             <a class="nav-link chat-button" href="/uteshop/views/registerShop.jsp" role="button" aria-expanded="false">
                                 <i class="fas fa-store"></i> Register Shop
                            </a>
                        </li>
                         <li class="nav-item">
                       <a class="nav-link chat-button" href="/uteshop/user/chat" role="button" aria-expanded="false">
                           <i class="fas fa-comment-dots"></i>
                       </a>
                        </li>
                        <li class="nav-item pe-3">
                            <a class="nav-link" aria-current="page" href="/uteshop/login?action=logout">
                                <i class="fa-solid fa-user-slash" style="color: #fafafa;"></i>&nbsp;Logout
                            </a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active pe-2">
                            <a class="nav-link" aria-current="page" href="/uteshop/views/register.jsp">
                                <i class="fa-solid fa-user-plus" style="color: #ffffff;"></i>&nbsp;Register
                            </a>
                        </li>
                        <li class="nav-item pe-2">
                            <a class="nav-link" aria-current="page" href="/uteshop/login">
                                <i class="fa-solid fa-user-lock" style="color: #fafafa;"></i>&nbsp;Login
                            </a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:if>
</nav>

