<%@ page import="Search.View.SearchPage" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="Search.Controller.SnappFoodCtrl" %>
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
    <title>Listing &amp; Directory Website Template</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <!-- Simple line Icon -->
    <link rel="stylesheet" href="static/css/simple-line-icons.css">
    <!-- Themify Icon -->
    <link rel="stylesheet" href="static/css/themify-icons.css">
    <!-- Hover Effects -->
    <link rel="stylesheet" href="static/css/set1.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="static/css/style.css">
</head>
<style>
    @font-face
    {
        font-family:'p30';font-weight:400;src:url(static/fonts/Shabnam.eot);src:url(static/fonts/Shabnam.eot?#iefix)
    format("embedded-opentype"),url(static/fonts/Shabnam.woff) format("woff"),
    url(static/fonts/Shabnam.ttf) format("truetype")}
    p,h1,h2,h3,h4,h5,h6,a,ul,div,tr,td,input,body,span{font-family:"p30"; direction: rtl !important
    }
    .borderi {

        padding: 10px;
        background-color: rgba(95, 95, 95, 0.5);


    }
    .colori {

        width: 100% !important;

    }

</style>

<body style="background-color: #f5f5f5">
    <!--============================= HEADER =============================-->
    <div class="dark-bg sticky-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <a class="navbar-brand" href="index.html">چاکا</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="icon-menu"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content" id="navbarNavDropdown">
                            <ul class="navbar-nav">
                                <li class="nav-item dropdown">
                                    <a class="nav-link" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        صفحه اصلی
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
                        <!--//END HEADER -->
    <!--============================= DETAIL =============================-->
    <section>
        <div class="row d-flex justify-content-center">
            <div class="col-md-6">
                <form class="form-wrap mt-4 border">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <input   type="text" placeholder="تهران" class="btn-group2"><span class="glyphicon glyphicon-map-marker"></span>
                        <input   type="text" placeholder=" حدود منطقه" class="btn-group2">
                        <input   type="text" placeholder=" حدود قیمت" class="btn-group1">
                        <button  type="submit" class="btn-form"><span class=" search-icon"></span>بگرد!<i class="pe-7s-angle-left"></i></button>
                    </div>
                </form></div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 responsive-wrap">

                </div>
                <div class="col-md-8 responsive-wrap">
                    <div class="row detail-filter-wrap">
                        <div class="col-md-4 featured-responsive">
                            <div class="detail-filter-text">
                                <p>نتیجه 34 رستوران</p>
                            </div>
                        </div>
                        <div class="col-md-8 featured-responsive">
                            <div class="detail-filter">
                                <p>فیلتر بر اساس</p>
                                <form class="filter-dropdown">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="inlineFormCustomSelect">
                  <option selected>بهترین فیلتر</option>
                  <option value="1">یک </option>
                  <option value="2">دو</option>
                  <option value="3">سه</option>
                </select>
                                </form>
                                <form class="filter-dropdown">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="inlineFormCustomSelect1">
                  <option selected>رستوران ها</option>
                  <option value="1">یک</option>
                  <option value="2">دو</option>
                  <option value="3">سه</option>
                </select>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="row detail-checkbox-wrap">
                        <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">

                            <label class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input">
                <span class="custom-control-indicator"></span>
                <span class="custom-control-description">فست فود </span>
              </label>
                        </div>
                        <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
                            <label class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input">
                <span class="custom-control-indicator"></span>
                <span class="custom-control-description">هندی </span>
              </label>
                        </div>

                        <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">

                            <label class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input">
                <span class="custom-control-indicator"></span>
                <span class="custom-control-description">کبابی </span>
              </label>
                        </div>
                    </div>
                    <div class="row light-bg detail-options-wrap">
                        <%
                            String loc = request.getParameter("t1");
                            loc = "lat="+loc.split(",")[0].substring(1)+"&long="+loc.split(",")[1].substring(0,loc.split(",")[1].length()-1);
                            String key = "getRestaurants";
                            SnappFoodCtrl snappController = new SnappFoodCtrl();
                            snappController.setAPI(key, loc);
                            JSONArray data = snappController.getRestuarants(snappController.getAPI());
                            JSONObject jsonObject;
                            String code;
                            for (int i = 0; i < data.size(); i++)
                            {
                                jsonObject = (JSONObject) data.get(i);
                                byte ptext[] = jsonObject.keySet().toString().getBytes();
                                String name = new String(ptext, "UTF-8");
                                ;%>
                        <div class="col-sm-6 col-lg-12 col-xl-6 featured-responsive">
                            <div class="featured-place-wrap">
                                <a href="MenuPage.jsp?code=<%out.println(jsonObject.get("restaurantCode"));%>">
                                    <img src="static/images/featured<%out.println(i%3+1);%>.jpg" class="img-fluid" alt="#">
                                    <span class="featured-rating-orange ">6.5</span>
                                    <div class="featured-title-box">ظ
                                        <h6><%out.println(jsonObject.get("restaurantName"));%></h6>
                                        <p>Restaurant </p> <span>• </span>
                                        <p>3 Reviews</p> <span> • </span>
                                        <p><span>$$$</span>$$</p>
                                        <ul>
                                            <li><span class="icon-location-pin"></span>
                                                <p>1301 Avenue, Brooklyn, NY 11230</p>
                                            </li>
                                            <li><span class="icon-screen-smartphone"></span>
                                                <p>+44 20 7336 8898</p>
                                            </li>
                                            <li><span class="icon-link"></span>
                                                <p>https://burgerandlobster.com</p>
                                            </li>

                                        </ul>
                                        <div class="bottom-icons">
                                            <div class="closed-now">CLOSED NOW</div>
                                            <span class="ti-heart"></span>
                                            <span class="ti-bookmark"></span>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>
                <div class="col-md-2 responsive-wrap">

                </div>
            </div>
        </div>
    </section>
    <!--//END DETAIL -->
    <!--============================= FOOTER =============================-->
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


    <script>
        $(".map-icon").click(function() {
            $(".map-fix").toggle();
        });
    </script>
    <script>
        // Want to customize colors? go to snazzymaps.com
        function myMap() {
            var maplat = $('#map').data('lat');
            var maplon = $('#map').data('lon');
            var mapzoom = $('#map').data('zoom');
            // Styles a map in night mode.
            var map = new google.maps.Map(document.getElementById('map'), {
                center: {
                    lat: maplat,
                    lng: maplon
                },
                zoom: mapzoom,
                scrollwheel: false
            });
            var marker = new google.maps.Marker({
                position: {
                    lat: maplat,
                    lng: maplon
                },
                map: map,
                title: 'We are here!'
            });
        }
    </script>
    <!-- Map JS (Please change the API key below. Read documentation for more info) -->
    <script src="https://maps.googleapis.com/maps/api/js?callback=myMap&key=AIzaSyDMTUkJAmi1ahsx9uCGSgmcSmqDTBF9ygg"></script>
</body>

</html>
