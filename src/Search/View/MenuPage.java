package Search.View;

import Search.Controller.SnappFoodCtrl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MenuPage
{
    private final String key = "menuAPI";
    private JSONArray data;

    public void restaurantBEventHandler(String code)
    {
        SnappFoodCtrl snappController = new SnappFoodCtrl();
        snappController.setAPI(key, code);
        data = snappController.getMenu(snappController.getAPI(), code);
        JSONObject jObject;
        JSONArray jsonArray;
        for (int i = 0; i < data.size(); i++)
        {
            jsonArray = (JSONArray) data.get(i);
            JSONObject first = (JSONObject)jsonArray.get(0);
            JSONObject second = (JSONObject)jsonArray.get(1);
            System.out.println(first.keySet().toString() + "  ->  " + first.values().toString());
            System.out.println(second.keySet().toString() + "  ->  " + second.values().toString());
        }
    }
}
