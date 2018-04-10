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
        String restaurantName = "پیتزا سیب 360 (تهرانپارس)";
        data.add("همبرگر");
        data.add("پیتزا چانو (ایتالیایی)");
        shoppingAgent.addRequest(restaurantName, data);
        shoppingAgent.submitRequest();
    }
}
