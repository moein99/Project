import java.util.ArrayList;

/**
 * Created by moein on 3/29/2018.
 */
public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Scrapper scrapper = new Scrapper();
        scrapper.run(true);
        ArrayList<String> data = new ArrayList<>();
        String restaurantName = "پیتزا پرپروک (هفت حوض)";
        data.add("مرغ سوخاری معمولی دو تکه");
        data.add("همبرگر مخصوص");
        scrapper.addRequest(restaurantName, data);
    }
}
