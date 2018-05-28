<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head><link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"><style type="text/css">.gm-style .gm-style-cc span,.gm-style .gm-style-cc a,.gm-style .gm-style-mtc div{font-size:10px}
</style><style type="text/css">@media print {  .gm-style .gmnoprint, .gmnoprint {    display:none  }}@media screen {  .gm-style .gmnoscreen, .gmnoscreen {    display:none  }}</style><style type="text/css">.gm-style-pbc{transition:opacity ease-in-out;background-color:rgba(0,0,0,0.45);text-align:center}.gm-style-pbt{font-size:22px;color:white;font-family:Roboto,Arial,sans-serif;position:relative;margin:0;top:50%;-webkit-transform:translateY(-50%);-ms-transform:translateY(-50%);transform:translateY(-50%)}
</style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Menu</title>

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

<!-- Menu -->
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
                <h2 class="title white-text">Our Menu</h2>
            </div>

            <!-- menu nav -->
            <ul class="menu-nav">
                <li class="active"><a data-toggle="tab" href="#menu1">Foods</a></li>



            </ul>
            <!-- /menu nav -->
            <%
                String key = "menuAPI";
                String venCode = request.getParameter("code");
                SnappFoodCtrl snappController = new SnappFoodCtrl();
                snappController.setAPI(key, venCode);
                JSONArray data = snappController.getMenu(snappController.getAPI(), venCode);
                String proId;
                JSONArray jsonArray;
                int half =data.size()/2;
            %>
            <!-- menu content -->
            <div id="menu-content" class="tab-content">

                <!-- menu1 -->
                <div id="menu1" class="tab-pane fade in active">
                    <div class="col-md-6">
                        <%for (int i = 0; i < half; i++)
                        {
                            jsonArray = (JSONArray) data.get(i);
                            JSONObject first = (JSONObject)jsonArray.get(0);
                            proId = first.values().toString().split(":")[1];
                            proId= proId.substring(0,proId.length()-1);
                            JSONObject second = (JSONObject)jsonArray.get(1);%>
                        <!-- single dish -->
                        <div class="single-dish hover">
                            <div  class="single-dish-heading">
                                <h4 onclick="add(<%out.println(proId);%>)" class="name"><%out.println(first.keySet().toString());%></h4>
                                <h4 class="price"><%out.println(second.values().toString());%></h4>
                            </div>
                        </div>
                        <%}%>
                    </div>

                </div>
                <div id="menu2" class="tab-pane fade in active">
                    <div class="col-md-6">
                        <%for (int i = half; i < data.size(); i++)
                        {
                            jsonArray = (JSONArray) data.get(i);
                            JSONObject first = (JSONObject)jsonArray.get(0);
                            proId = first.values().toString().split(":")[1];
                            proId= proId.substring(0,proId.length()-1);
                            JSONObject second = (JSONObject)jsonArray.get(1);%>
                        <!-- single dish -->
                        <div class="single-dish hover">
                            <div onclick="add(<%out.println(proId);%>)" class="single-dish-heading">
                                <h4 class="name"><%out.println(first.keySet().toString());%></h4>
                                <h4 class="price"><%out.println(second.values().toString());%></h4>
                            </div>
                        </div>
                        <%}%>
                    </div>

                </div>
                <!-- /menu1 -->
                <form id="SubmitForm" action="response.jsp">
                    <input type="text" id="data" name ="data" style="display: none">
                    <input type="text" id="code" name="code" value="<%out.println(venCode);%>" style="display: none">
                    <input type="button" class="main-button" value="Done" onclick="finish()" style="margin-top: 26px; margin-left: 500px;" >
                </form>
            </div>
            <!-- /menu content -->

        </div>
        <!-- /row -->

    </div>
    <!-- /container -->

</div>

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

<script>
    var json ="";
    function add(str){
        json=json+str+":";
        alert("added");
    }
    function finish() {
        document.getElementById("data").value=json;
        document.getElementById("SubmitForm").submit();
    }
</script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/owl.carousel.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
<script type="text/javascript" src="js/google-map.js"></script>
<script type="text/javascript" src="js/main.js"></script>



</body></html>