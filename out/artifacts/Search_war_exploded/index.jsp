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
    <!-- Page Title -->
    <title>Home | Chaka</title>
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
    .colori
    {

        width: 100% !important;
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

<body>
    <!--============================= HEADER =============================-->
    <div class="nav-menu">
        <div class="bg transition">
            <div class="container-fluid fixed">
                <div class="row">
                    <div class="col-md-12">
                        <nav class="navbar navbar-expand-lg navbar-light colori">
                            <a class="navbar-brand" href="index.html">چاکا </a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="icon-menu"></span>
                            </button>

                            <div class="collapse navbar-collapse " id="navbarNavDropdown">
                                <ul class="navbar-nav">
                                    <%
                                        String userName= (String) request.getSession().getAttribute("login");
                                        if (userName==null || userName=="") {
                                            System.out.println("not login");

                                    %>
                                    <li class="nav-item active">
                                        <a class="nav-link fonti " href="signUp.html">عضویت</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fonti" href="login.jsp">ورود</a>
                                    </li>
                                    <%
                                        }
                                        else {
                                            System.out.println("login");
                                    %>
                                    <li class="nav-item" style="color: white">
                                        <%out.println(request.getSession().getAttribute("login"));%>
                                    </li>
                                    <%}%>
                                </ul>
                            </div>

                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- SLIDER -->
    <section class="slider d-flex align-items-center ">
        <!-- <img src="images/slider.jpg" class="img-fluid" alt="#"> -->
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-md-10   ">
                    <div class="slider-title_box">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="slider-content_wrap borderi">
                                    <h1 class="fonti"><span  style="color: #ffffff">بهترین انتخاب رو داشته باش!</span></h1>
                                    <h5 class="fonti"><span style="color: #ffffff">
                                        ارزون ترین با بهترین کیفیت کدومه؟
                                    </span>
                                    </h5>
                                </div>
                            </div>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <div class="col-md-10">
                                <form class="form-wrap mt-4 submit" action="listing.jsp">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <input   type="text" placeholder="تهران" class="btn-group"><span class="glyphicon glyphicon-map-marker"></span>
                                        <input type="text" name="t1" id="t1" style="display: none;">
                                        <input onclick="validaty()" type="button" value="بگرد!  " class="btn-form"><i class="pe-7s-angle-left"></i></button>
                                    </div>
                                    <div>
                                        <p class="borderi"style="color: #ffffff;font-size: 25px">میتونی از روی نقشه هم انتخاب کنی

                                            <img src="https://i.pinimg.com/originals/f2/57/78/f25778f30e29a96c44c4f72ef645aa63.png"style="width: 27px"></p>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="col-md-12 border" id="map" style="width:100%;height: 400px"></div>

        <script>
            function validaty() {
                loc = document.getElementById("t1").value;
                if(loc == null ||loc == ""){
                    alert("select location");
                }
                else{
                    document.getElementsByClassName("submit")[0].submit();
                }
            }
            function initAutocomplete() {
                var mapCanvas = document.getElementById("map");
                var myCenter = new google.maps.LatLng(35.738593, 51.503258);
                var mapOptions = {center: myCenter, zoom: 15};
                var map = new google.maps.Map(mapCanvas, mapOptions);
                google.maps.event.addListener(map, 'click', function(event) {
                    placeMarker(map, event.latLng);
                });
                var input = document.getElementById('pac-input');
                var searchBox = new google.maps.places.SearchBox(input);
                document.getElementsByClassName("")
                map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

                map.addListener('bounds_changed', function() {
                    searchBox.setBounds(map.getBounds());
                });

                var marker;
                searchBox.addListener('places_changed', function() {
                    var places = searchBox.getPlaces();

                    if (places.length == 0) {
                        return;
                    }

                    var bounds = new google.maps.LatLngBounds();
                    places.forEach(function(place) {
                        if (!place.geometry) {
                            console.log("Returned place contains no geometry");
                            return;
                        }
                        placeMarker(map,place.geometry.location);
                        if (place.geometry.viewport) {
                            bounds.union(place.geometry.viewport);
                        } else {
                            bounds.extend(place.geometry.location);
                        }
                    });
                    map.fitBounds(bounds);
                });
                function placeMarker(map, location) {
                    if (marker) {
                        marker.setPosition(location);
                    } else {
                        marker = new google.maps.Marker({
                            position: location,
                            map: map,
                            draggable: true
                        });
                    }
                    document.getElementById("t1").value=location;
                }
            }
        </script>
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


    <!-- jQuery, Bootstrap JS. -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA5CFOTho-uF4qjXK_CN8CpA57MzgZx0Jk&libraries=places&callback=initAutocomplete"
            async defer></script>


</body>

</html>
