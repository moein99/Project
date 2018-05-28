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
    String proId;
    JSONArray jsonArray;%>
    <table class="table table-striped table-dark">
    <tr><th scope="col">productName</th><th scope="col">ProductId</th><th scope="col">Price</th></tr>
    <%for (int i = 0; i < data.size(); i++)
    {
        jsonArray = (JSONArray) data.get(i);
        JSONObject first = (JSONObject)jsonArray.get(0);
        proId = first.values().toString().split(":")[1];
        proId= proId.substring(0,proId.length()-1);
        JSONObject second = (JSONObject)jsonArray.get(1);%>
        <tr><td scope="row"><a href=""onclick="add(<%out.println(proId);%>)" ><%out.println(first.keySet().toString());%></a></td><td scope="row">
                <%out.println(proId);%></td><td scope="row">
                <%out.println(second.values().toString());%></td></tr>
    <%}%>
    </table>
<form id="SubmitForm" action="response.jsp">
    <input type="text" id="data" name ="data">
    <input type="text" id="code" name="code" value="<%out.println(venCode);%>">
    <input type="button" onclick="done()">
</form>

    <script>
        var json ="";
        function add(str){
            json=json+str+":";
            alert("added");
        }
        function done() {
            document.getElementById("data").value=json;
            document.getElementById("SubmitForm").submit();
        }
    </script>
</body>
</html>
