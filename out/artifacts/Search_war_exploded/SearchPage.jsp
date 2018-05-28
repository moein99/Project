<%@ page import="Search.View.SearchPage" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Resturants</title>
</head>
<body>


<%
    String loc = request.getParameter("t1");
    loc = "lat="+loc.split(",")[0].substring(1)+"&long="+loc.split(",")[1].substring(0,loc.split(",")[1].length()-1);
    String key = "restaurantAPI";
    SnappFoodCtrl snappController = new SnappFoodCtrl();
    snappController.setAPI(key, loc);
    JSONArray data = snappController.getRestuarants(snappController.getAPI());
    JSONObject jsonObject;%>
    <table class="table table-striped table-dark">
    <tr><th scope="col">ResturantName</th><th scope=col">Code</th></tr>
    <%String code;
    for (int i = 0; i < data.size(); i++)
    {
        jsonObject = (JSONObject) data.get(i);
        byte ptext[] = jsonObject.keySet().toString().getBytes();
        String name = new String(ptext, "UTF-8");
        code =jsonObject.values().toString();%>
    <tr><td scope="row"><a href="MenuPage.jsp?code=<%out.println(code.substring(1,code.length()-1));%>"><%out.println(name);%></a></td>
        <td scope="row"><%out.println(code);%></td></tr>
        <%}%>
    </table>


</body>
</html>
