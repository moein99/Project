package Search.Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
		JSONArray menu = new JSONArray();
		String foods_class = "kk-grid-item ";
		String check_exist = "btn-product-finished";
		String price_class = "kk-price-wrapper kk-primary";
		Document document = getRestaurantMenuHtml(code, api);
		Elements foodDivs = document.getElementsByClass(foods_class);
		JSONArray food;
		JSONObject detail;
		for (Element el : foodDivs)
		{
			String checkForItemExistence = el.getElementsByClass(check_exist).text();
			if (checkForItemExistence.equals(""))
			{
				Element h4 = el.getElementsByTag("h4").first();
				Element foodNameSpan = h4.getElementsByTag("span").first();
				Element priceDiv = el.getElementsByClass(price_class).first();
				Element priceSpan = priceDiv.getElementsByTag("span").first();
				String data[] = el.getElementsByTag("a").attr("data-hashtags").split("::");
				food = new JSONArray();
				detail = new JSONObject();
				detail.put(foodNameSpan.text(), "vendorCode:" + data[0] + " " + "productId:" + data[1]);
				food.add(detail);
				detail = new JSONObject();
				detail.put(foodNameSpan.text(), "price: " + priceSpan.text());
				food.add(detail);
				menu.add(food);
			}
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

	private Document getRestaurantMenuHtml(String restaurantCode, String API)
	{
		try
		{
			URL obj = new URL(API);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			String urlParameters = "code=" + restaurantCode;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			JSONObject json = new JSONObject();
			JSONParser parser = new JSONParser();
			if (responseCode == 200)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String inputLine;
				StringBuffer response = new StringBuffer();
				String total = "";
				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}
				in.close();
				json = (JSONObject) parser.parse(response.toString());
				String html = (String) json.get("html");
				return Jsoup.parse(html);
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
}