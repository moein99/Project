package Search.Controller;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.CookieStore;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangalCtrl extends SystemCtrl
{
    private String api;
    private Requester requester;
    private ChangalAPIBuilder apiBuilder;

    ChangalCtrl()
    {
        requester = new Requester();
        apiBuilder = new ChangalAPIBuilder();
    }

    @Override
    public void setAPI(String key, String data)
    {
        this.api = apiBuilder.buildAPI(key, data);
    }

    @Override
    public JSONArray getRestuarants(String API)
    {
        String regionNumber;
        String[] temp = API.split("&");
        setAPI("getRegion", temp[1] + "&" + temp[2]);
        String wholeResponse = requester.sendGetRequest(api).toString();
        JSONObject temporary = getJson(wholeResponse);
        JSONObject object = (JSONObject)((JSONArray) temporary.get("region")).get(0);
        regionNumber = object.get("id").toString();
        wholeResponse = requester.sendGetRequest(API.replace("REGION_NUMBER", regionNumber)).toString();
        temporary = getJson(wholeResponse);
        JSONArray restaurants = (JSONArray) temporary.get("restaurants");
        return restaurants;
    }

    @Override
    public String getAPI()
    {
        return api;
    }

    @Override
    public JSONArray getMenu(String API, String code)
    {
        JSONObject temporary = getJson(requester.sendGetRequest(API.replace("RESTAURANT_NAME", code)).toString());
        JSONArray object = (JSONArray) temporary.get("food");
        return object;
    }

    public void addToBasket(String regionId, String restaurantId, JSONArray foods, String API)
    {
        JsonObjectAndCookie userDetailsAndCookie = login("09368714321", "moeincarnal77");
        JSONObject userDetails = userDetailsAndCookie.json;
        String temp;
        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie;
        boolean flag = true;
        for (String rawCookie : userDetailsAndCookie.cookies)
        {
            temp = rawCookie.split(";")[0];
            String[] nameVal = temp.split("=");
            cookie = new BasicClientCookie(nameVal[0], nameVal[1]);
            cookie.setPath("/");
            cookie.setDomain(".changal.com");
            // maybe it will be needed to set date for cookie!
            cookieStore.addCookie(cookie);
        }
        JSONObject items = new JSONObject();
        items.put("items", foods);
        items.put("region_id", regionId);
        items.put("restaurant_id", restaurantId);
        //requester.sendPostWithJson(API, items, cookieStore);
    }

    public JsonObjectAndCookie login(String username, String password)
    {
        setAPI("login", username + "&" + password);
        CookieAndResponse cookieAndResponse = requester.sendGetRequestC(api);
        JSONObject userDetails = getJson(cookieAndResponse.stringBuffer.toString());
        JsonObjectAndCookie jsonObjectAndCookie = new JsonObjectAndCookie(cookieAndResponse.cookie, userDetails);
        return jsonObjectAndCookie;
    }

    private JSONObject getJson(String html)
    {
        // extract JSON from html response
        // probably this function will be removed

        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass("prettyprint");
        Element el = elements.get(1);
        String json = el.ownText();
        JSONParser parser = new JSONParser();
        JSONObject temporary = null;
        try {
            temporary = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temporary;
    }
}


class JsonObjectAndCookie
{
    ArrayList<String> cookies;
    JSONObject json;
    JsonObjectAndCookie(ArrayList<String> cookies, JSONObject json)
    {
        this.cookies = cookies;
        this.json = json;
    }
}
