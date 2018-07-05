package Search.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

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
        return null;
    }

    public void addToBasket(String regionId, String restaurantId, JSONArray foods, String API)
    {
        return;
    }

    public JSONObject login(String username, String password)
    {
        return null;
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
