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
                <!--header search panel start -->
                <div class="ttr-search-bar">
                    <form class="ttr-search-form">
                        <div class="ttr-search-input-wrapper">
                            <input type="text" name="qq" placeholder="search something..." class="ttr-search-input">
                            <button type="submit" name="search" class="ttr-search-submit"><i class="ti-arrow-right"></i></button>
                        </div>
                        <span class="ttr-search-close ttr-search-toggle">
                            <i class="ti-close"></i>
                        </span>
                    </form>
                </div>
                <!--header search panel end -->
            </div>
        </header>
        <!-- header end -->
        <!-- Left sidebar menu start -->
        <%@include file="sidebar.jsp" %>

        <!--Main container start -->
        <main class="ttr-wrapper">
            <div class="container-fluid">
                <div class="db-breadcrumb">
                    <h4 class="breadcrumb-title">Thêm Chiến dịch</h4>
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
                                <form action="addcampaign" method="post" class="edit-profile m-b30">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="ml-auto">
                                            </div>
                                        </div>
                                        <div class="form-group col-12">
                                            <label class="col-form-label">Tên chiến dịch</label>
                                            <div>
                                                <input class="form-control" type="text" value="" name="name">
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Mô tả</label>
                                            <div>
                                                <textarea class="form-control" type="text" value="" name="mota"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Mục tiêu</label>
                                            <div>
                                                <textarea class="form-control" type="text" value="" name="muctieu"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Loại</label>
                                            <div>
                                                <select class="form-control" name="type">
                                                    <option value="Quảng cáo">Quảng cáo</option>
                                                    <option value="PR">PR</option>
                                                    <option value="Khuyến Mại">Khuyến Mại</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Chi phí</label>
                                            <div>
                                                <input class="form-control" type="number" value="" name="chiphi">
                                            </div>
                                        </div>
                                        <div class="form-group col-3">
                                            <label class="col-form-label">Nhân viên phụ trách</label>
                                            <div>
                                                <select class="form-control" name="employee">
                                                    <option value="Lê Thị B">Lê Thị B</option>
                                                    <option value="Trung Nguyễn">Trung Nguyễn</option>
                                                    <option value="Phương Hoàng">Phương Hoàng</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Ngày bắt đầu</label>
                                            <div>
                                                <input class="form-control" type="date" value="" name="start" required>
                                            </div>
                                        </div>
                                        <div class="form-group col-6" id="enddate">
                                            <label class="col-form-label">Ngày kết thúc</label>
                                            <div>
                                                <input class="form-control" type="date" value="" name="end">
                                            </div>
                                        </div>

                                        <div class="seperator"></div>
                                        <div class="col-12">
                                            <button type="submit" class="btn">Lưu</button>
                                            <a href="/cms/campaign" class="btn-secondry">Hủy</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>
        <!-- External JavaScripts -->
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var radioButtons = document.getElementsByName('type');
                var endDateDiv = document.getElementById('enddate'); // Chọn khối div chứa ngày kết thúc

                // Thêm event listener cho mỗi nút radio
                for (var i = 0; i < radioButtons.length; i++) {
                    radioButtons[i].addEventListener('change', function () {
                        // Nếu nút radio được chọn có id là "1", ẩn khối div ngày kết thúc
                        if (this.id === '1') {
                            endDateDiv.style.display = 'none';
                        } else {
                            // Ngược lại, hiển thị khối div ngày kết thúc
                            endDateDiv.style.display = 'block';
                        }
                    });
                }

                // Khởi tạo ẩn hoặc hiển thị khối div dựa trên nút radio được chọn mặc định
                var checkedRadioButton = document.querySelector('input[name="type"]:checked');
                if (checkedRadioButton && checkedRadioButton.id === '1') {
                    endDateDiv.style.display = 'none'; // Ẩn khối div nếu nút radio mặc định có id là "1"
                } else {
                    endDateDiv.style.display = 'block'; // Hiển thị khối div nếu không có nút radio nào được chọn hoặc nút radio có id khác "1"
                }
            });
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