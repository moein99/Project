<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.net.URI" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URISyntaxException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String key = "getMenu";
    String venCode = request.getParameter("code");
    SnappFoodCtrl snappController = new SnappFoodCtrl();
    snappController.setAPI(key, venCode);
    JSONArray data = snappController.getMenu(snappController.getAPI(), venCode);
    String proId;
    JSONObject food;
    Desktop d = Desktop.getDesktop();
    try {
        d.browse(new URI("https://snappfood.ir/restaurant/"+venCode));

    } catch (IOException | URISyntaxException e2) {
        e2.printStackTrace();
    }
%>
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
    <title>نام رستوران</title>
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
        background-color: rgba(70, 204, 255, 0.5) !important;


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
<div class="dark-bg sticky-top">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="index.jsp">چاکا</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="icon-menu"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                            <%
                                String userName= (String) request.getSession().getAttribute("login");
                                if (userName==null || userName=="") {
                                    System.out.println("not login");

                            %>
                            <li class="nav-item dropdown">
                                <a class="nav-link" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    صفحه اصلی
                                </a>
                            </li>
                            <%
                            }
                            else {
                                System.out.println("login");
                            %>
                            <li class="nav-item dropdown" style="color: white">
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
<!--//END HEADER -->
<!--============================= DETAIL =============================-->
<section style="background-color: #e8e8e8">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-1 responsive-wrap"></div>
            <div class="col-md-8 responsive-wrap">
                <div class="row light-bg detail-options-wrap">
                    <%for (int i = 0; i < data.size(); i++)
                    {
                        food = (JSONObject) data.get(i);
                        System.out.println(food);
                        String name = food.get("title").toString();
                        proId = food.get("id").toString();
                        String price = food.get("price").toString();
                    %>
                    <div class="col-sm-6 col-lg-12 col-xl-6 featured-responsive">
                        <div class="featured-place-wrap">
                                <img src="static/images/main.jpg" class="img-fluid" alt="#">
                                <div class="featured-title-box">
                                    <h6  class="name"><%out.println(name);%></h6>
                                    <p><span style="font-size: 20px"><%out.println(price);%></span>تومان </p>
                                    </ul>
                                    <br>
                                    <button onclick="add(<%out.println(proId);%>)" class="btn btn-outline-primary" >میخوامش !</button>
                                </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
            <div class="col-md-2 responsive-wrap">
                <div class="row light-bg detail-options-wrap">
                        <div class=" col-sm-12 col-lg-12 col-xl-12 featured-responsive"><center><img style="width: 100px;height: 100px;padding-right: 10px" src="static/images/basket.png" class="img-fluid" alt="#">
                        </center></div>
                        <center>
                            <div class="col-sm-12 col-lg-12 col-xl-12 featured-responsive">
                                <center><form id="SubmitForm" action="response.jsp">
                                    <input type="text" id="data" name ="data" style="display: none">
                                    <input type="text" id="code" name="code" value="<%out.println(venCode);%>" style="display: none">
                                </form>
                                    <button class="borderi rounded" id="basket" value="<%if (request.getSession().getAttribute("login")==null) out.print("false"); else out.print("true"); ;%>"onclick="finish()" >سبد خرید</button></center>
                            </div>
                            <div class="col-sm-12 col-lg-12 col-xl-12 featured-responsive">
                                <div class="featured-place-wrap">
                                    <div class="featured-title-box">
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                        </center>
                </div>
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
    var json ="";
    function add(str){
        json=json+str+":";
        alert("added");
    }
    function finish() {
        document.getElementById("data").value=json;
        if (document.getElementById("basket").value=="true")
        document.getElementById("SubmitForm").submit();
    }
</script>

</body>

</html>
<%--<input type="button" class="main-button" value="Done" onclick="finish()" style="margin-top: 26px; margin-left: 500px;" >--%>