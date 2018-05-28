<html lang="en"><head><link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"><style type="text/css">.gm-style .gm-style-cc span,.gm-style .gm-style-cc a,.gm-style .gm-style-mtc div{font-size:10px}
</style><style type="text/css">@media print {  .gm-style .gmnoprint, .gmnoprint {    display:none  }}@media screen {  .gm-style .gmnoscreen, .gmnoscreen {    display:none  }}</style><style type="text/css">.gm-style-pbc{transition:opacity ease-in-out;background-color:rgba(0,0,0,0.45);text-align:center}.gm-style-pbt{font-size:22px;color:white;font-family:Roboto,Arial,sans-serif;position:relative;margin:0;top:50%;-webkit-transform:translateY(-50%);-ms-transform:translateY(-50%);transform:translateY(-50%)}
</style>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Food Finder</title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Quicksand:400,700%7CCabin:400%7CDancing+Script" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Owl Carousel -->
    <link type="text/css" rel="stylesheet" href="css/owl.carousel.css">
    <link type="text/css" rel="stylesheet" href="css/owl.theme.default.css">

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <style>
        #map {
            height: 90%;
        }
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #description {
            font-family: Roboto;
            font-size: 15px;
            font-weight: 300;
        }

        #infowindow-content .title {
            font-weight: bold;
        }

        #infowindow-content {
            display: none;
        }

        #map #infowindow-content {
            display: inline;
        }

        .pac-card {
            margin: 10px 10px 0 0;
            border-radius: 2px 0 0 2px;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            outline: none;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
            background-color: #fff;
            font-family: Roboto;
        }

        #pac-container {
            padding-bottom: 12px;
            margin-right: 12px;
        }

        .pac-controls {
            display: inline-block;
            padding: 5px 11px;
        }

        .pac-controls label {
            font-family: Roboto;
            font-size: 13px;
            font-weight: 300;
        }

        #pac-input {
            background-color: #fff;
            font-family: Roboto;
            font-size: 15px;
            font-weight: 300;
            margin-left: 12px;
            padding: 0 11px 0 13px;
            text-overflow: ellipsis;
            width: 400px;
        }

        #pac-input:focus {
            border-color: #4d90fe;
        }

        #title {
            color: #fff;
            background-color: #4d90fe;
            font-size: 25px;
            font-weight: 500;
            padding: 6px 12px;
        }
        #target {
            width: 345px;
        }
    </style>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/map.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/marker.js"></script><style type="text/css">.gm-style {
        font: 400 11px Roboto, Arial, sans-serif;
        text-decoration: none;
    }
    .gm-style img { max-width: none; }</style><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/onion.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/controls.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/stats.js"></script></head>
<body>

<!-- Header -->
<!-- /Header -->

<!-- Home -->
<div id="home" class="banner-area" style="padding-top: 100px;">

    <!-- Backgound Image -->
    <div class="bg-image bg-parallax overlay" style="background-image:url(./img/background02.jpg)"></div>
    <!-- /Backgound Image -->

    <div class="home-wrapper">

        <div class="col-md-10 col-md-offset-1 text-center">
            <div class="home-content">
                <h1 class="white-text">Welcome To food finder</h1>
                <button class="main-button"><a href="#search">Start Searching</a></button>
            </div>
        </div>

    </div>

</div>
<!-- /Home -->

<!-- About -->
<div id="about" class="section">

    <!-- container -->
    <div class="container">

        <!-- row -->
        <div class="row">

            <!-- section header -->
            <div class="section-header text-center">
                <h4 class="sub-title">About Us</h4>
                <h2 class="title">The food finder</h2>
            </div>
            <!-- /section header -->

            <!-- about content -->
            <div class="col-md-5">
                <h4 class="lead">Welcome to food finder. Since 2018, Offering The best choice for you.</h4>
            </div>
            <!-- /about content -->

            <!-- about content -->
            <div class="col-md-7">
                <p>food finder helps you to find what you want from any food system you like </p>
            </div>


        </div>
        <!-- /row -->

    </div>
    <!-- /container -->

</div>
<!-- /About -->
<div id="search" class="section">

    <!-- container -->
    <div class="container">

        <!-- row -->
        <div class="row">

            <!-- section header -->
            <div class="section-header text-center">
                <h4 class="sub-title">Search</h4>
            </div>
            <!-- /section header -->
            <input id="pac-input" class="controls" type="text" placeholder="Search Box">
            <div id="map"></div>
            <form id="mapform" action="SearchPage.jsp">
                <input type="text" name="t1" id="t1" style="display: none;">
                <input type="button" class="main-button" value="Search" onclick="validaty()"style="margin-top: 20px; margin-left: 496px;">
            </form>
            <!-- about content -->

        </div>
        <!-- /row -->

    </div>
    <!-- /container -->

</div>

<!-- Menu -->

<!-- /Menu -->

<!-- Reservation -->

<!-- /Reservation -->

<!-- Events -->

<!-- /Events -->

<!-- Contact -->

<!-- Contact -->

<!-- Footer -->
<footer id="footer">

    <!-- container -->
    <div class="container">

        <!-- row -->
        <div class="row">

            <!-- copyright -->
            <!-- /copyright -->

            <!-- footer nav -->
            <!-- /footer nav -->

        </div>
        <!-- /row -->

    </div>
    <!-- /container -->

</footer>
<!-- /Footer -->

<!-- Preloader -->
<div id="preloader" style="display: none;">
    <div class="preloader">
        <span></span>
        <span></span>
        <span></span>
    </div>
</div>
<!-- /Preloader -->

<!-- jQuery Plugins -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/owl.carousel.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script type="text/javascript" src="js/google-map.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script>
    function validaty() {
        loc = document.getElementById("t1").value;
        if(loc == null ||loc == ""){
            alert("select location");
        }
        else{
            document.getElementById("mapform").submit();
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
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA5CFOTho-uF4qjXK_CN8CpA57MzgZx0Jk&libraries=places&callback=initAutocomplete"
        async defer></script>


</body></html>