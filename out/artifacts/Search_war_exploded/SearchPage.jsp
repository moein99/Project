<%@ page import="Search.View.SearchPage" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<html lang="en"><head><link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"><style type="text/css">.gm-style .gm-style-cc span,.gm-style .gm-style-cc a,.gm-style .gm-style-mtc div{font-size:10px}
</style><style type="text/css">@media print {  .gm-style .gmnoprint, .gmnoprint {    display:none  }}@media screen {  .gm-style .gmnoscreen, .gmnoscreen {    display:none  }}</style><style type="text/css">.gm-style-pbc{transition:opacity ease-in-out;background-color:rgba(0,0,0,0.45);text-align:center}.gm-style-pbt{font-size:22px;color:white;font-family:Roboto,Arial,sans-serif;position:relative;margin:0;top:50%;-webkit-transform:translateY(-50%);-ms-transform:translateY(-50%);transform:translateY(-50%)}
</style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Resturants</title>

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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/common.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/util.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/map.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/marker.js"></script><style type="text/css">.gm-style {
        font: 400 11px Roboto, Arial, sans-serif;
        text-decoration: none;
    }
    .gm-style img { max-width: none; }
    .hover { cursor: pointer; }
    </style><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/onion.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/controls.js"></script><script type="text/javascript" charset="UTF-8" src="https://maps.googleapis.com/maps-api-v3/api/js/33/1/stats.js"></script></head>
<body>

<div id="menu" class="section">

    <!-- Backgound Image -->
    <div class="bg-image bg-parallax overlay" style="background-image:url(./img/background01.jpg)"></div>
    <!-- /Backgound Image -->

    <!-- container -->
    <div class="container">

        <!-- row -->
        <div class="row">

            <div class="section-header text-center">
                <h4 class="sub-title">Discover</h4>
                <h2 class="title white-text">Resturants</h2>
            </div>

            <!-- menu nav -->
            <ul class="menu-nav">
                <li class="active"><a data-toggle="tab" href="#menu1">Results</a></li>



            </ul>
            <!-- /menu nav -->
            <%
                String loc = request.getParameter("t1");
                loc = "lat="+loc.split(",")[0].substring(1)+"&long="+loc.split(",")[1].substring(0,loc.split(",")[1].length()-1);
                String key = "restaurantAPI";
                SnappFoodCtrl snappController = new SnappFoodCtrl();
                snappController.setAPI(key, loc);
                JSONArray data = snappController.getRestuarants(snappController.getAPI());
                JSONObject jsonObject;%>
            <!-- menu content -->
            <div id="menu-content" class="tab-content">

                <!-- menu1 -->
                <div id="menu1" class="tab-pane fade in active">
                    <div class="col-md-6" style="width: 100%">
                        <%String code;
                            for (int i = 0; i < data.size(); i++)
                            {
                                jsonObject = (JSONObject) data.get(i);
                                byte ptext[] = jsonObject.keySet().toString().getBytes();
                                String name = new String(ptext, "UTF-8");
                                code =jsonObject.values().toString();%>
                        <!-- single dish -->
                        <div class="single-dish hover">
                            <div class="single-dish-heading">
                                <a href="MenuPage.jsp?code=<%out.println(code.substring(1,code.length()-1));%>" style="text-decoration: none;"><h4 class="name"><%out.println(name);%></h4></a>
                            </div>
                        </div>
                        <%}%>
                    </div>

                </div>

                <!-- /menu1 -->
            </div>
            <!-- /menu content -->

        </div>
        <!-- /row -->

    </div>
    <!-- /container -->

</div>
<!-- /Menu -->

<!-- Footer -->
<footer id="footer">

    <!-- container -->
    <div class="container">

        <!-- row -->
        <div class="row">


        </div>
        <!-- /row -->

    </div>
    <!-- /container -->

</footer>

<div id="preloader" style="display: none;">
    <div class="preloader">
        <span></span>
        <span></span>
        <span></span>
    </div>
</div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/owl.carousel.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script type="text/javascript" src="js/google-map.js"></script>
<script type="text/javascript" src="js/main.js"></script>



</body></html>