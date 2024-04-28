<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
    <head>
        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- DESCRIPTION -->
        <link rel="icon" href="../error-404.html" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />
        <title>Hệ thống quản lý khách hàng</title>
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">
        <link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->
        <header class="ttr-header">
            <div class="ttr-header-wrapper">
                <!--sidebar menu toggler start -->
                <div class="ttr-toggle-sidebar ttr-material-button">
                    <i class="ti-close ttr-open-icon"></i>
                    <i class="ti-menu ttr-close-icon"></i>
                </div>
                <!--sidebar menu toggler end -->
                <!--logo start -->
                <div class="ttr-logo-box">
                    <div>
                        <a href="index.html" class="ttr-logo">
                        </a>
                    </div>
                </div>
                <!--logo end -->
                <%@include file="header.jsp" %>
            </div>
        </header>
        <!-- header end -->
        <!-- Left sidebar menu start -->
        <%@include file="sidebar.jsp" %>

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="row">
                                <div class="col-8" class="wc-title" style="display: flex">
                                    <h4 style="margin-left: 20px">Danh sách Khách Hàng</h4>

                                </div>
                                <div class="col-2">
                                    <c:if test="${not empty message}">
                                        <p style="color: green">${message}</p>
                                    </c:if>
                                </div>
                                <div class="col-2"> 
                                    <a class="btn " href="/cms/addcustomer">Thêm +</a>
                                </div>
                            </div>
                            <div class="widget-inner">
                                <table class="">
                                    <thead>
                                        <tr>
                                            <th class="border-bottom p-3" style="min-width: 10px;">#</th>
                                            <th class="border-bottom p-3" style="min-width: 100px;">Họ và tên</th>
                                            <th class="border-bottom p-3" style="min-width: 120px;">Email</th>
                                            <th class="border-bottom p-3" style="min-width: 120px;">Ngày sinh</th>
                                            <th class="border-bottom p-3" style="min-width: 150px;">Địa chỉ</th>
                                            <th class="border-bottom p-3" style="min-width: 70px;">Giới tính</th>
                                            <th class="border-bottom p-3" style="min-width: 120px;">STĐ</th>
                                            <th class="border-bottom p-3" style="min-width: 70px;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listC}" var="i">
                                            <tr>
                                                <td class="p-3">${i.getUserId()}</td>
                                                <td class="p-3">${i.getName()}</td>
                                                <td class="p-3">${i.getEmail()}</td>
                                                <td class="p-3">${i.getDob()}</td>
                                                <td class="p-3">${i.getAddress()}</td>
                                                <td class="p-3">${i.getGender()}</td>
                                                <td class="p-3">${i.getPhone()}</td>
                                                <td class="p-2">
                                                    <c:if test="${sessionScope.acc == 'ADMIN' || sessionScope.acc == 'EMPLOYEE' && sessionScope.pos == 'Quản lý'}">
                                                        <a href="/cms/updatecustomer?&userid=${i.getUserId()}" class="btn" ><i class="ti-pencil-alt"></i></a>
                                                        </c:if>
                                                    <a href="#" class="btn" onclick="deleteUser('${i.getUserId()}')"><i class="fa fa-trash-o"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <form id="delete" action="updatecustomer?action=delete" method="post">
                                    <input id="project_id" type="hidden" name="project_id">
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>
        <script>
            //delete user
            function deleteUser(x) {
                //confirm
                if (confirm('Do you want to remove with Id:' + x) === true) {
                    document.getElementById('project_id').value = x;
                    document.getElementById('delete').submit();
                }
            }
        </script>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="assets/vendors/counter/waypoints-min.js"></script>
        <script src="assets/vendors/counter/counterup.min.js"></script>
        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="assets/vendors/masonry/masonry.js"></script>
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src='assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/vendors/chart/chart.min.js"></script>
        <script src="assets/js/admin.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/add-listing.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>