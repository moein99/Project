package Search.View;

import Search.Controller.SnappFoodCtrl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SubmitPage
{
    private final String key = "addToBasket";
    private JSONArray data;

    public void submitEventHandler(String venCode, ArrayList<String> proIds)
    {
        SnappFoodCtrl snappController = new SnappFoodCtrl();
        snappController.setAPI(key, null);
        snappController.addToBasket(venCode, proIds, snappController.getAPI());
    }
}
