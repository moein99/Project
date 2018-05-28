package Search.Controller;

import Search.Model.URLModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SnappFoodAPIBuilder extends SystemAPIBuilder{
	private String baseKey = "snappfood";

	public String buildAPI(String key, String data) {
		baseKey = key.split("&")[0];
		if (key.equals("restaurantAPI")) {
			return createAddressUrl(data,getURL(key));
		}
		else if(key.equals("menuAPI")){
			return getURL(key);
		}
		else if (key.equals("addToBasket"))
		{
			return getURL(key);
		}
		return null;
	}

	private String createAddressUrl(String location, String url) {
		String additionalKey = "Address";
		String[] loc = location.split("&");
		String lat  = loc[0];
		String lon = loc[1];
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			Document doc = Jsoup.connect(url).ignoreContentType(true)
					.data("lat", lat.split("=")[1])
					.data("long", lon.split("=")[1])
					.method(Connection.Method.POST)
					.post();
			json = (JSONObject) parser.parse(doc.body().text());
			String address = getURL(additionalKey) + json.get("cityCode") + "/near/" + json.get("areaId")
					+"?"+ location;
			return address;
		} catch (java.net.ConnectException e) {
			System.out.println("Connection Timed out !\n Running Again ...!");
			return createAddressUrl(lat, lon);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getURL(String key) {
		if (key.equals("restaurantAPI")) {
			return URLModel.location_link;
		}
		else if(key.equals("Address")) {
			return URLModel.address_link;
		}
		else if(key.equals("menuAPI")){
			return URLModel.menu_link;
		}
		else if(key.equals("addToBasket"))
		{
			return URLModel.addingToBasket_link;
		}
		return null;
	}


}