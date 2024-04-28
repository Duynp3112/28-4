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
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Chỉnh sửa thông tin</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="/cms/homepage"><i class="fa fa-home"></i>Home</a></li>
                    </ul>
                </div>	
                <div class="row">
                    <!-- Your Profile Views Chart -->
                    <div class="col-lg-1"></div>
                    <div class="col-lg-10 m-b30">
                        <div class="widget-box">
                            <div class="widget-inner">
                                <form action="updatepassword" method="post" class="">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                                <h3>II.Thông tin tài khoản</h3>
                                            </div>
                                        </div>
                                        <input type="hidden" name="userid" value="${sessionScope.uid}">
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Mật khẩu cũ</label>
                                            <div class="input-group">
                                                <input class="form-control" type="password" name="pass" id="pass">
                                                <div class="input-group-append">
                                                    <span class="input-group-text" onclick="togglePassword('pass', 'toggle-pass')">
                                                        <i id="toggle-pass" class="fa fa-eye"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Mật khẩu mới</label>
                                            <div class="input-group">
                                                <input class="form-control" type="password" name="newpass" id="newpass">
                                                <div class="input-group-append">
                                                    <span class="input-group-text" onclick="togglePassword('newpass', 'toggle-newpass')">
                                                        <i id="toggle-newpass" class="fa fa-eye"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Nhập lại mật khẩu</label>
                                            <div class="input-group">
                                                <input class="form-control" type="password" name="repass" id="repass">
                                                <div class="input-group-append">
                                                    <span class="input-group-text" onclick="togglePassword('repass', 'toggle-repass')">
                                                        <i id="toggle-repass" class="fa fa-eye"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <input type="submit" class="btn" value="Lưu"/>
                                            <a href="/cms/homepage" class="btn-secondry">Hủy</a>
                                        </div>
                                    </div>
                                    <c:if test="${not empty error}">
                                        <p style="color: red">${error}</p>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script>
            function togglePassword(fieldId, iconId) {
                var field = document.getElementById(fieldId);
                var icon = document.getElementById(iconId);

                if (field.type === "password") {
                    field.type = "text";
                    icon.className = "fa fa-eye-slash";
                } else {
                    field.type = "password";
                    icon.className = "fa fa-eye";
                }
            }
        </script>

        <!-- External JavaScripts -->
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