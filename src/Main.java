import java.util.ArrayList;

/**
 * Created by moein on 3/29/2018.
 */
public class Main
{
    public static void main( String[] args ) throws Exception
    {
        ShoppingAgent shoppingAgent = new ShoppingAgent();
        shoppingAgent.runScrapper();
        ArrayList<String> data = new ArrayList<>();
        String restaurantName = "کترینگ ماندگار";
        data.add("خورشت قیمه با کته");
        data.add("خورشت قیمه با کته");
        data.add("خورشت قورمه سبزی با کته");
        shoppingAgent.addRequest(restaurantName, data);
        shoppingAgent.submitRequest();
        shoppingAgent.submit();
        shoppingAgent.submitAddress();
        shoppingAgent.goBank();
    }
}