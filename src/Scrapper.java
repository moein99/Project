import java.io.*;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import javax.net.ssl.HttpsURLConnection;

public class Scrapper
{

    public static String areaLink = "https://snapp-food.com/restaurant/city/tehran/area/%D9%81%D8%B1%D8%AC%D8%A7%D9%85";
    public static void main( String[] args ) throws Exception
    {
        String restaurantsClass = "kk-pp-title";
        Document doc = Jsoup.connect(areaLink).get();
        Elements restaurantDivs = doc.getElementsByClass(restaurantsClass);
        for (Element el:restaurantDivs)
        {
            Element a = el.getElementsByTag("a").first();
            String restaurantName = a.text();
            String restaurantCode = getRestaurantCode(a.attr("href"));
            printDetails(restaurantName, restaurantCode);
        }
    }


    private static String getRestaurantCode(String link)
    {
        String array[] = link.split("/");
        return array[3];                                       // Hard Code , it will be changed!
    }


    private static void printDetails(String restaurantName, String restaurantCode) throws Exception
    {
        Document document = getHtml(restaurantCode);
        Elements foodDivs = document.getElementsByClass("kk-grid-item ");
        for (Element el : foodDivs)
        {
            Element h4 = el.getElementsByTag("h4").first();
            Element foodNameSpan = h4.getElementsByTag("span").first();
            Element priceDiv = el.getElementsByClass("kk-price-wrapper kk-primary").first();
            Element priceSpan = priceDiv.getElementsByTag("span").first();
            System.out.println(priceSpan.text() + "  <----  " + foodNameSpan.text() + "  <----  " + restaurantName);
        }
    }


    private static Document getHtml(String restaurantCode) throws Exception
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
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.toString());
            String html = (String) json.get("html");
            Document document = Jsoup.parse(html);
            return document;
        }
        else
        {
            System.out.println("response code is : " + responseCode);
            return null;
        }
    }


}