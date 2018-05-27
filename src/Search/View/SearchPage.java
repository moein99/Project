package Search.View;

import Search.Controller.SnappFoodCtrl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchPage
{
	private String location;
	private final String key = "restaurantAPI";
	private JSONArray data;

	public void SearchEventHandler(String location)
	{
		SnappFoodCtrl snappController = new SnappFoodCtrl();
		snappController.setAPI(key, location);
		data = snappController.getRestuarants(snappController.getAPI());
		JSONObject jsonObject;
		for (int i = 0; i < data.size(); i++)
		{
			jsonObject = (JSONObject) data.get(i);
			System.out.println(jsonObject.keySet().toString() + "  ->  " + jsonObject.values().toString());
		}
	}
}