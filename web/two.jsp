<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: amirsaeed
  Date: 5/26/2018
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    String key = "menuAPI";
    String venCode = request.getParameter("code");
    SnappFoodCtrl snappController = new SnappFoodCtrl();
    snappController.setAPI(key, venCode);
    JSONArray data = snappController.getMenu(snappController.getAPI(), venCode);
    JSONArray jsonArray;
    out.println("<table class=\"table table-striped table-dark\">");
    out.println("<tr><th scope=\"col\">productName</th><th scope=\"col\">vendorCodeAndProductId</th><th scope=\"col\">Price</th></tr>");
    for (int i = 0; i < data.size(); i++)
    {
        out.println("<tr>");
        jsonArray = (JSONArray) data.get(i);
        JSONObject first = (JSONObject)jsonArray.get(0);
        JSONObject second = (JSONObject)jsonArray.get(1);
        out.println("<tr>"+"<td>"+first.keySet().toString()+"</td><td scope=\"row\">"
                + first.values().toString()+"</td><td scope=\"row\">"
                +second.values().toString()+"</td></tr>");
    }
    out.println("</table>");
%>
</body>
</html>
