<%--
  Created by IntelliJ IDEA.
  User: Tri
  Date: 11/22/2021
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="product" scope="request" type="com.ute.auctionwebapp.beans.Product"/>
<t:main>
    <jsp:body>
        <div class="right col-sm-9 ">
            <div class="card mt-2">
                <div class="card-header bg-warning">
                    <h4 style="cursor:pointer;" class="text-center">${product.proname}</h4>
                </div>
                <div class="card-body">
                    <div id="carouselExampleIndicators" class="carousel slide w-50 mx-auto shadow-lg" style="border-radius: 20%" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" src="https://cdn.tgdd.vn/Products/Images/42/213033/iphone-12-pro-max-xanh-duong-new-600x600-600x600.jpg" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="https://cdn.tgdd.vn/Products/Images/42/228744/iphone-12-pro-max-trang-bac-600x600-200x200.jpg" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" src="https://cdn.tgdd.vn/Products/Images/42/213033/iphone-12-pro-max-xam-new-600x600-200x200.jpg" alt="Third slide">
                            </div>
                        </div>
                    </div>
                    <h4 style="cursor:pointer;" class="text-success text-center mt-3">Giá khởi điểm: ${product.price_start}</h4>
                    <h4 style="cursor:pointer;" class="text-danger text-center mt-3">Giá mua ngay: ${product.price_now}</h4>
                    <h4 class="card-title mt-3 ml-4">Thông tin sản phẩm</h4>
                    <p class="ml-5">
                        Số lượng: ${product.quantity}
                    </p>
                    <p class="ml-5">${product.fulldes}</p>
                    <p class="ml-5">${product.start_day}</p>
                    <p class="ml-5">${product.end_day}</p>
                </div>
                <div class="card-footer text-muted bg-warning d-flex justify-content-center">
                    <a class="btn btn-primary mr-5" href="#" role="button">
                        <i class="fa fa-gavel" aria-hidden="true"></i>
                        Auction now
                    </a>
                    <a class="btn btn-danger mr-5" href="#" role="button">
                        <i class="fa fa-heart" aria-hidden="true"></i>
                        Love
                    </a>
                    <a class="btn btn-success" href="#" role="button">
                        <i class="fa fa-cart-plus" aria-hidden="true"></i>
                        Add to cart
                    </a>
                </div>
            </div>
        </div>
        <nav aria-label="Page navigation example" class=" mt-3" style="margin-left: 85%;">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </jsp:body>
</t:main>

