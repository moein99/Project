
<%@ page import="Search.Controller.SnappFoodCtrl" %>
<%@ page import="java.util.ArrayList" %>

<%
    String PIDs = request.getParameter("data");
    String venCode = request.getParameter("code");
    String [] IDs = PIDs.split(":");
    ArrayList proIds = new ArrayList();
    for (String id: IDs) {
        if (id!=null&&id!="")
            proIds.add(id);
    }
    final String key = "addToBasket";
    SnappFoodCtrl snappController = new SnappFoodCtrl();
    snappController.setAPI(key, null);
    snappController.addToBasket(venCode, proIds, snappController.getAPI());
    String url ="https://snappfood.ir/order/checkout/vendor/"+request.getParameter("code");
    response.sendRedirect(url);

%>