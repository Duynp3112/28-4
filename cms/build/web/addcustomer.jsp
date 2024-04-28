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
                                <form action="addcustomer" method="post" class="">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                                <h3>I.Thông tin cơ bản</h3>
                                            </div>
                                        </div>
                                        <input type="hidden" name="userid" value="${sessionScope.uid}">
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Họ và Tên</label>
                                            <div>
                                                <input class="form-control" type="text" value="${user.name}" name="name" required >
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Giới tính</label>
                                            <div>
                                                <select name="gender" class="form-control" >
                                                    <option>Nam</option>
                                                    <option>Nữ</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group col-4">
                                            <label class="col-form-label">Ngày Sinh</label>
                                            <div>
                                                <input class="form-control" type="date" name="date" >
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Email</label>
                                            <div>
                                                <input class="form-control" type="text"  name="email" required>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Số điện thoại</label>
                                            <div>
                                                <input class="form-control" type="text"  name="phone" required>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Địa Chỉ</label>
                                            <div>
                                                <textarea class="form-control" name="address" required></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-2">
                                            <label class="col-form-label">Tên đăng nhập</label>
                                            <div>
                                                <input class="form-control" type="text"  name="username" required>
                                            </div>
                                        </div>
                                        <div class="form-group col-2">
                                            <label class="col-form-label">Mật khẩu</label>
                                            <div class="input-group">
                                                <input class="form-control" type="password" id="pass" name="password" required>
                                                <div class="input-group-append">
                                                    <span class="input-group-text" onclick="togglePassword('pass', 'toggle-pass')">
                                                        <i id="toggle-pass" class="fa fa-eye"></i>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group col-2">
                                            <label class="col-form-label">Nhập lại mật khẩu</label>
                                            <div class="input-group">
                                                <input class="form-control" type="password" id="repass" name="repass" required>
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
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script>
            function validatePassword() {
                var password = document.getElementById("pass").value;
                var repass = document.getElementById("repass").value;

                if (password !== repass) {
                    alert("Mật khẩu không trùng!");
                    return false;
                }
                return true;
            }
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
        <div class="ttr-overlay"></div>
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