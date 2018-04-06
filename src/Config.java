import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Config
{
    public static String menu_link = "https://snapp-food.com/restaurant/menu/load";
    public static String restaurants_link = "https://snapp-food.com/restaurant/city/tehran/area/";
    public static String restaurants_class = "kk-pp-title";
    public static String foods_class = "kk-grid-item ";
    public static String price_class = "kk-price-wrapper kk-primary";
    public static String login_link = "https://snappfood.ir/auth/login_check";
    public static String my_address_link = "https://snappfood.ir/customer/address/get";
    public static String location_link = "https://snappfood.ir/restaurant/location";
    public static String baseUrl = "https://snappfood.ir";
    public static String address_link = "https://snappfood.ir/restaurant/city/";

    public static Document getDocument(String url)
    {
        Document document;
        try
        {
            document = Jsoup.connect(url).get();
            return document;
        }
        catch (java.net.SocketTimeoutException e)
        {
            System.out.println("Time out exception ...! \nRunning again ...!");
            return getDocument(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
