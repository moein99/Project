<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Colorlib">
    <meta name="description" content="#">
    <meta name="keywords" content="#">
    <!-- Favicons -->
    <link rel="shortcut icon" href="#">
    <!-- Page Title -->
    <title>ورود</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <!-- Simple line Icon -->
    <link rel="stylesheet" href="css/simple-line-icons.css">
    <!-- Themify Icon -->
    <link rel="stylesheet" href="css/themify-icons.css">
    <!-- Hover Effects -->
    <link rel="stylesheet" href="css/set1.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<style>
    @font-face
    {
        font-family:'p30';font-weight:400;src:url(fonts/Shabnam.eot);src:url(fonts/Shabnam.eot?#iefix)
    format("embedded-opentype"),url(fonts/Shabnam.woff) format("woff"),
    url(Shabnam.ttf) format("truetype")}
    p,h1,h2,h3,h4,h5,h6,a,ul,div,tr,td,input,body,span{font-family:"p30"; direction: rtl !important
    }
    .borderi {

        padding: 10px;
        background-color: rgba(95, 95, 95, 0.5);


    }
    .colori {

        width: 100% !important;

    }
    .slider
    {
        background:url(images/1.jpeg)!important;
    }
    input:hover
    {

        color: white;
        box-shadow: 0 0 0 2px orange;
        background-color: #7e5774;
    }
    a:hover
    {
        color: #1e7e34!important;
    }
</style>

<body style="background-color: #f5f5f5">
<!--============================= HEADER =============================-->
<div class="dark-bg sticky-top">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="HomePage.html">چاکا</a>

                </nav>
            </div>
        </div>
    </div>
</div>
<!--//END HEADER -->
<!--============================= DETAIL =============================-->
<section>

    <section class="slider d-flex align-items-center ">
        <!-- <img src="images/slider.jpg" class="img-fluid" alt="#"> -->
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-md-10   ">
                    <div class="slider-title_box">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="slider-content_wrap borderi">
                                    <h1 style="direction: rtl ;padding-bottom: 25px"> حساب کاربری</h1>
                                    <form action="HomePage.html">
                                        <input   type="text" placeholder="نام کاربری" class="btn-group2">
                                        <input   type="password" placeholder="رمز عبور" class="btn-group2">
                                        <span   style="padding-bottom: 20px">

                                            <input    type="submit" value="ورود" class="btn-form">
                                    <br>
                                            <span style="padding-bottom: 50px">
                                    </span>
                                        </span>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<footer class="main-block dark-bg">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="copyright">
                    <p >
                        <span style="font-size: 25px">چاکا</span>

                        <br>
                        سامانه انتخاب بهترین و به صرفه ترین غذا از بهترین سرویس

                    <ul>
                        <li><a href="#"><span class="ti-facebook"></span></a></li>
                        <li><a href="#"><span class="ti-twitter-alt"></span></a></li>
                        <li><a href="#"><span class="ti-instagram"></span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
<!--//END FOOTER -->




<!-- jQuery, Bootstrap JS. -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>

</html>
