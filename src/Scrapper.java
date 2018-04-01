import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import javax.net.ssl.HttpsURLConnection;

public class Scrapper
{
    JSONParser parser;
    private int indexOfRestaurantCode = 3;
    private JSONObject json;
    private Document doc;
    private Map<String, String> cookies;
    Scrapper() throws Exception {
        parser = new JSONParser();
    }


    public void run() throws Exception       // throws time out exception #fixme
    {
        String userName="09173601338";
        String password="saed.saeed";
        String lon="50.510666";
        String lat="32.739075";
        if(login(userName,password)) {
            String address[]=getAddress();
            lat = address[0];
            lon = address[1];
        }
        String restaurantPageAddress = createAddressUrl(lat,lon);
        int pageNumber = 0;
        Document doc = getRestaurantsHtml(restaurantPageAddress, pageNumber);
        while (doc != null)
        {
            Elements restaurantDivs = doc.getElementsByClass(Config.restaurants_class);
            for (Element el:restaurantDivs)
            {
                Element a = el.getElementsByTag("a").first();
                String restaurantName = a.text();
                String restaurantCode = getRestaurantCode(a.attr("href"));
                printDetails(restaurantName, restaurantCode);
            }
            pageNumber++;
            doc = getRestaurantsHtml(restaurantPageAddress, pageNumber);
        }
    }


    private boolean login(String userName,String password) throws Exception {
        String loginMethod="password";
        Connection.Response baseForm = Jsoup.connect(Config.baseUrl).method(Connection.Method.GET).execute();
        Connection.Response doc = Jsoup.connect(Config.login_link).ignoreContentType(true)
                .data("_username", userName)
                .data("_password", password)
                .data("_login_method",loginMethod)
                .method(Connection.Method.POST).cookies(baseForm.cookies())
                .execute();
        cookies=doc.cookies();
        json=(JSONObject)parser.parse(doc.body());
        if (json.get("status").toString()=="true"){
            return true;
        }
        else
            return false;
    }


    private String[] getAddress() throws Exception     // #fixme
    {
        doc = Jsoup.connect(Config.my_address_link).ignoreContentType(true).method(Connection.Method.GET).cookies(cookies).get();
        JSONArray jsonArray = (JSONArray) parser.parse(doc.body().text());
        json=(JSONObject)jsonArray.get(0);
        String address[] = new String[2];
        address[0] = json.get("latitude").toString();
        address[1] = json.get("longitude").toString();
        return address;
    }


    private String createAddressUrl(String lat,String lon) throws Exception
    {
        doc = Jsoup.connect(Config.location_link).ignoreContentType(true)
                .data("lat",lat)
                .data("long",lon)
                .method(Connection.Method.POST)
                .cookies(cookies)
                .post();
        json=(JSONObject)parser.parse(doc.body().text());
        String address = Config.address_link + json.get("cityCode")+"/near/" + json.get("areaId")
                +"?lat=" + json.get("lat") + "&long=" + json.get("long");
        return address;
    }


    private Document getRestaurantsHtml(String restaurantPageAddress, int pageNumber) throws Exception //#fixme
    {
        String url =  restaurantPageAddress + "&" + "page=" + pageNumber;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
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
            String html = response.toString();
            if (html.contains(Config.restaurants_class))
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


    private String getRestaurantCode(String link)
    {
        String array[] = link.split("/");
        return array[indexOfRestaurantCode];
    }


    private void printDetails(String restaurantName, String restaurantCode) throws Exception
    {
        Document document = getRestaurantMenuHtml(restaurantCode);
        Elements foodDivs = document.getElementsByClass(Config.foods_class);
        for (Element el : foodDivs)
        {
            Element h4 = el.getElementsByTag("h4").first();
            Element foodNameSpan = h4.getElementsByTag("span").first();
            Element priceDiv = el.getElementsByClass(Config.price_class).first();
            Element priceSpan = priceDiv.getElementsByTag("span").first();
            System.out.println(priceSpan.text() + "  <----  " + foodNameSpan.text() + "  <----  " + restaurantName);
        }
    }


    private Document getRestaurantMenuHtml(String restaurantCode) throws Exception
    {
        String url = Config.menu_link;
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        String urlParameters = "code=" + restaurantCode;
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
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


}