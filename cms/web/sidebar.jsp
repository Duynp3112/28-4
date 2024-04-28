<%-- 
    Document   : sidebar
    Created on : Apr 18, 2024, 11:14:59 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="ttr-sidebar">
            <div class="ttr-sidebar-wrapper content-scroll">
                <!-- side menu logo start -->
                <div class="ttr-sidebar-logo">
                    <div class="ttr-sidebar-toggle-button">
                        <i class="ti-arrow-left"></i>
                    </div>
                </div>
                <!-- side menu logo end -->
                <!-- sidebar menu start -->
                <nav class="ttr-sidebar-navi">
                    <c:if test="${sessionScope.acc == 'ADMIN'}">
                        <ul>
                            <li>
                                <a href="/cms/homepage" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-home"></i></span>
                                    <span class="ttr-label">Trang Chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">Khách Hàng</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="/cms/customer">Danh sách Khách Hàng</a></li>
                                    <li><a href="/cms/transaction">Quản lý giao dịch</a></li>
                                    <li><a href="/cms/contract">Hợp đồng của khách hàng</a></li>
                                    <li><a href="/cms/interaction">Lịch sử tương tác</a></li>
                                    <li><a href="/cms/appointment">Quản lý lịch hẹn</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="/cms/contract" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-book"></i></span>
                                    <span class="ttr-label">HD của khách hàng</span>
                                </a>
                            </li>
                            <li>
                                <a href="/cms/campaign" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-email"></i></span>
                                    <span class="ttr-label">Quản lý chiến dịch</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-calendar"></i></span>
                                    <span class="ttr-label">Báo Cáo</span>
                                </a>
                            </li>
                            <li class="ttr-seperate"></li>

                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">My Profile</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul>
                                    <li>
                                        <a href="/cms/userprofile?&userid=${sessionScope.uid}" class="ttr-material-button"><span class="ttr-label">User Profile</span></a>
                                    </li>

                                </ul>
                            </li>
                            <li class="ttr-seperate"></li>

                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.acc == 'EMPLOYEE' && sessionScope.pos == 'Quản lý'}">
                        <ul>
                            <li>
                                <a href="/cms/homepage" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-home"></i></span>
                                    <span class="ttr-label">Trang Chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">Khách Hàng</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="/cms/customer">Danh sách Khách Hàng</a></li>
                                    <li><a href="/cms/contract">Hợp đồng của khách hàng</a></li>
                                    <li><a href="/cms/transaction">Quản lý giao dịch</a></li>
                                    <li><a href="/cms/appointment">Quản lý lịch hẹn</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="/cms/contract" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-book"></i></span>
                                    <span class="ttr-label">HD của khách hàng</span>
                                </a>
                            </li>
                            <li>
                                <a href="/cms/campaign" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-email"></i></span>
                                    <span class="ttr-label">Quản lý chiến dịch</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-calendar"></i></span>
                                    <span class="ttr-label">Báo Cáo</span>
                                </a>
                            </li>
                            <li class="ttr-seperate"></li>

                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">My Profile</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul>
                                    <li>
                                        <a href="/cms/userprofile?&userid=${sessionScope.uid}" class="ttr-material-button"><span class="ttr-label">User Profile</span></a>
                                    </li>

                                </ul>
                            </li>
                            <li class="ttr-seperate"></li>

                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.acc == 'EMPLOYEE' && sessionScope.pos != 'Quản lý'}">
                        <ul>
                            <li>
                                <a href="/cms/homepage" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-home"></i></span>
                                    <span class="ttr-label">Trang Chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">Khách Hàng</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="/cms/customer">Danh sách Khách Hàng</a></li>
                                    <li><a href="/cms/transaction">Quản lý giao dịch</a></li>
                                    <li><a href="/cms/interaction">Lịch sử tương tác</a></li>
                                    <li><a href="#">Quản lý lịch hẹn</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="/cms/campaign" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-email"></i></span>
                                    <span class="ttr-label">Quản lý chiến dịch</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-calendar"></i></span>
                                    <span class="ttr-label">Báo Cáo</span>
                                </a>
                            </li>
                            <li class="ttr-seperate"></li>

                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">My Profile</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul>
                                    <li>
                                        <a href="/cms/userprofile?&userid=${sessionScope.uid}" class="ttr-material-button"><span class="ttr-label">User Profile</span></a>
                                    </li>

                                </ul>
                            </li>
                            <li class="ttr-seperate"></li>

                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.acc == 'CUSTOMER'}">
                        <ul>
                            <li>
                                <a href="/cms/homepage" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-home"></i></span>
                                    <span class="ttr-label">Trang Chủ</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">Khách Hàng</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="/cms/transaction">Giao dịch</a></li>
                                    <li><a href="/cms/contract">Hợp đồng của tôi</a></li>
                                    <li><a href="/cms/interaction">Lịch sử tương tác</a></li>
                                    <li><a href="#">Lịch hẹn</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="/cms/contract" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-book"></i></span>
                                    <span class="ttr-label">Hợp đồng của tôi</span>
                                </a>
                            </li>
                            <li class="ttr-seperate"></li>

                            <li>
                                <a href="#" class="ttr-material-button">
                                    <span class="ttr-icon"><i class="ti-user"></i></span>
                                    <span class="ttr-label">My Profile</span>
                                    <span class="ttr-arrow-icon"><i class="fa fa-angle-down"></i></span>
                                </a>
                                <ul>
                                    <li>
                                        <a href="/cms/userprofile?&userid=${sessionScope.uid}" class="ttr-material-button"><span class="ttr-label">User Profile</span></a>
                                    </li>

                                </ul>
                            </li>
                            <li class="ttr-seperate"></li>

                        </ul>
                    </c:if>
                    <!-- sidebar menu end -->

                </nav>
                <!-- sidebar menu end -->
            </div>
        </div>
        <!-- Left sidebar menu end -->
    </body>
</html>
