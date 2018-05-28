package Search.View;

import Search.Controller.SnappFoodCtrl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SubmitPage
{
    private final String key = "addToBasket";
    private JSONArray data;

    public void submitEventHandler(JSONArray data)
    {
        SnappFoodCtrl snappController = new SnappFoodCtrl();
        snappController.setAPI(key, null);
        snappController.addToBasket();
    }
}
