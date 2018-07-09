package Search.Model;

import Search.Model.SystemAPIBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SnappFoodAPIBuilder extends SystemAPIBuilder {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost:3306/db";
	private final String USER = "root";
	private final String PASS = "1375109";
	private java.sql.Connection conn = null;
	private Statement statement;

	public SnappFoodAPIBuilder() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String buildAPI(String key, String data) {
		String api = getAPI(key);
		if (key.equals("getRestaurants")) {
			return createAddressUrl(data, api);
		} else if (key.equals("getMenu")) {
			return api + "?code=" + data + "&date=today&time=-1";
		} else if (key.equals("addToBasket")) {
			return api;
		} else if (key.equals("snappFoodUserAddresses")) {
			return api;
		}
		return null;
	}

	private String createAddressUrl(String location, String url) {
		String key = "Address";
		String[] loc = location.split("&");
		String lat = loc[0];
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
			String address = getAPI(key) + json.get("cityCode") + "/near/" + json.get("areaId")
					+ "?" + location;
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

	public String getAPI(String type) {
		String temp = "'%'";
		System.out.println(type);
		String sql = "select value from snappfoodapis where type = " + temp.replace("%", type);
		String api = null;
		try {
			ResultSet set = statement.executeQuery(sql);
			while (set.next()) {
				api = set.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return api;
	}
}