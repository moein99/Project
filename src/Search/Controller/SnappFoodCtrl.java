package Search.Controller;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SnappFoodCtrl extends SystemCtrl
{
	private String api;

	public void setAPI(String key, String data)
	{
		SnappFoodAPIBuilder builder = new SnappFoodAPIBuilder();
		api = builder.buildAPI(key , data);
	}

	public JSONArray getRestuarants(String API)
	{
		String resClass = "kk-pp-title";
		int indexOfRestaurantCode = 3;
		JSONArray restaurants = new JSONArray();
		int pageNumber = 0;
		Document doc = getRestaurantsHtml(API, pageNumber, resClass);
		JSONObject restuarant;
		while (doc != null)
		{
			Elements restaurantDivs = doc.getElementsByClass(resClass);
			for (Element el : restaurantDivs)
			{
				Element a = el.getElementsByTag("a").first();
				String restaurantName = a.text();
				String restaurantCode = getRestaurantCode(a.attr("href"), indexOfRestaurantCode);
				restuarant = new JSONObject();
				restuarant.put(restaurantName, restaurantCode);
				restaurants.add(restuarant);
			}
			pageNumber++;
			doc = getRestaurantsHtml(API, pageNumber, resClass);
		}

		return restaurants;
	}

	private String getRestaurantCode(String link, int index)
	{
		String array[] = link.split("/");
		return array[index];
	}

	public String getAPI()
	{
		return api;
	}

	public JSONArray getMenu(String api, String code)
	{
		JSONArray menu;
		JSONObject json = getRestaurantMenuJson(code, api);
		json = (JSONObject) json.get("param");
		menu = (JSONArray) json.get("menu");
		HashMap<String, Pair<String, String>> totalFoods = new HashMap<>();
		for (Object item : menu)
		{
			JSONObject category = (JSONObject) item;
			JSONArray foods = (JSONArray) category.get("products");
			for (Object item1 : foods)
			{
				JSONObject food = (JSONObject) item1;
				totalFoods.put(food.get("id").toString(), new Pair<String, String>(food.get("title").toString(), food.get("price").toString()));
			}
		}
		JSONObject detail;
		JSONArray food;
		menu = new JSONArray();
		for (String id : totalFoods.keySet())
		{
			food = new JSONArray();
			detail = new JSONObject();
			detail.put(totalFoods.get(id).getKey(), "productId:" + id);
			food.add(detail);
			detail = new JSONObject();
			detail.put(totalFoods.get(id).getKey(), "price: " + totalFoods.get(id).getValue());
			food.add(detail);
			menu.add(food);
		}
		return menu;
	}

	private Document getRestaurantsHtml(String API, int pageNum, String resClass)
	{
		try
		{
			String url =  API + "&" + "page=" + pageNum;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			if (responseCode == 200)
			{
				StringBuffer response = new StringBuffer();
				try
				{
					BufferedReader in = new BufferedReader(
							new InputStreamReader(con.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				String html = response.toString();
				if (html.contains(resClass))
				{
					return Jsoup.parse(response.toString());
				}
				else
				{
					return null;
				}
			}
			else
			{
				System.out.println("response code is : " + responseCode);
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private JSONObject getRestaurantMenuJson(String restaurantCode, String API)
	{
		try
		{
			Map<String, String> cookies = Jsoup.connect("https://snappfood.ir/restaurant/menu/" + restaurantCode).ignoreContentType(true).execute().cookies();
			String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
			URL obj = new URL(API);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestProperty("User-Agent", USER_AGENT);
			String cookie = cookies.toString().substring(1, cookies.toString().length() - 1);
			con.setRequestProperty("Cookie", cookie);
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			if (responseCode == 200)
			{
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(response.toString());
				return json;
			}
			else
			{
				System.out.println("in method getMenuJson");
				System.out.println(responseCode);
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void addToBasket(String vendorCode,	ArrayList<String> productIds, String API)
	{
		Map<String, String> cookies = login("09368714321", "13771999");
		String operationMode = "1";
		String is_checkout = "true";
		String payment_type = "online";
		for(String productId:productIds)
		{
			try
			{
				Connection.Response execut = Jsoup.connect(API).ignoreContentType(true).
						data("vendor_code",vendorCode).
						data("operation_mode",operationMode).
						data("product_id",productId).
						data("last_target_id",productId).method(Connection.Method.POST).cookies(cookies)
						.execute();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		String productId = "-1";
		operationMode = "5";
		for(int i = 0; i < productIds.size(); i++)
		{
			try
			{
				Connection.Response execute = Jsoup.connect(API).ignoreContentType(true).
						data("is_checkout",is_checkout).
						data("operation_mode", operationMode).
						data("payment_type", payment_type).
						data("product_id", productId).
						data("vendor_code", vendorCode).method(Connection.Method.POST).cookies(cookies)
						.execute();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private Map<String, String> login(String userName, String password)
	{
		try
		{
			Map<String, String> cookies;
			JSONParser parser = new JSONParser();
			JSONObject json;
			String loginMethod = "password";
			Connection.Response baseForm = Jsoup.connect("https://snappfood.ir").method(Connection.Method.GET).execute();
			Connection.Response doc = Jsoup.connect("https://snappfood.ir/auth/login_check").ignoreContentType(true)
					.data("_username", userName)
					.data("_password", password)
					.data("_login_method",loginMethod)
					.method(Connection.Method.POST).cookies(baseForm.cookies())
					.execute();
			cookies = doc.cookies();
			json = (JSONObject)parser.parse(doc.body());
			if (json.get("status").toString().equals("true"))
			{
				return cookies;
			}
			else
				return null;
		}
		catch (java.net.ConnectException e)
		{
			System.out.println("Connection Timed out !\n Running Again ...!");
			return login(userName, password);
		}
		catch (org.json.simple.parser.ParseException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}

	}
}