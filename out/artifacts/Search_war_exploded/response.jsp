
<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page import="java.util.ArrayList" %>

<%
    String PIDs = request.getParameter("data");
    String venCode = request.getParameter("code");
    System.out.println(venCode);
    String userName = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("pass");
    String [] IDs = PIDs.split(":");
    ArrayList proIds = new ArrayList();
    for (String id: IDs) {
        if (id!=null&&id!="")
            proIds.add(id);
    }
    final String key = "addToBasket";
    SnappFoodCtrl snappController = new SnappFoodCtrl();
    snappController.setAPI(key, null);
    System.out.println(venCode);
    System.out.println(proIds);
    System.out.println(userName);
    System.out.println(password);
    snappController.addToBasket(venCode, proIds, snappController.getAPI(),userName ,password);
    String url ="https://snappfood.ir/order/checkout/vendor/"+request.getParameter("code");
    response.sendRedirect(url);

%>