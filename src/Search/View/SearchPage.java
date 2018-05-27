package Search.View;

import Search.Controller.SnappFoodCtrl;
import org.json.simple.JSONArray;

public class SearchPage
{
	private String location;
	private final String key = "restaurantAPI";
	private JSONArray data;

	public JSONArray SearchEventHandler(String location)
	{
		SnappFoodCtrl snappController = new SnappFoodCtrl();
		snappController.setAPI(key, location);
		return data = snappController.getRestuarants(snappController.getAPI());
	}
}