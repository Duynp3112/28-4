<%-- 
    Document   : login
    Created on : Apr 18, 2024, 9:12:52 AM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>Hệ thống quản lý khách hàng</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">

    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(assets/images/background/bg2.jpg);">
                    <a href="index.html"><img src="assets/images/logo-white-2.png" alt=""></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Đăng ký <span>Ngay bây giờ</span></h2>
                            <p><a href="/cms/login">Đăng nhập tài khoản của bạn</a></p>
                        </div>	
                        <form action="register" method="post" class="contact-bx" onsubmit="return validatePassword()">
                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <label>Tên Đăng Nhập</label>
                                            <input name="username" type="text" required="" class="form-control" autocomplete="off">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Mật Khẩu</label>
                                            <input id="password" name="password" type="password" class="form-control" required="" autocomplete="off">
                                            <div class="input-group-append">
                                                <span class="input-group-text" onclick="togglePassword('password', 'toggle-pass')">
                                                    <i id="toggle-password" class="fa fa-eye"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Nhập lại mật khẩu</label>
                                            <input id="repass" name="repass" type="password" class="form-control" required="" autocomplete="off">
                                            <div class="input-group-append">
                                                <span class="input-group-text" onclick="togglePassword('repass', 'toggle-pass')">
                                                    <i id="toggle-repass" class="fa fa-eye"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button name="submit" type="submit" value="Submit" class="btn button-md">Đăng ký</button>
                            </div>
                        </form>
                        <div>
                            <c:if test="${not empty message}">
                                <p>${message}</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var repass = document.getElementById("repass").value;

                if (password !== repass) {
                    alert("Re_password phải trùng với password");
                    return false;
                }
                return true;
            }
        </script>
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
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
    </body>
</html>
