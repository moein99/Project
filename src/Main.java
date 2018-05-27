import Search.View.MenuPage;
import Search.View.SearchPage;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String location = "lat=35.739328144512&long=51.512247249484";
        String key = "restaurantAPI";
        SearchPage searchPage = new SearchPage();
        searchPage.SearchEventHandler(location);
        Scanner input = new Scanner(System.in);
        String venCode = input.nextLine();
        MenuPage menuPage = new MenuPage();
        menuPage.restaurantBEventHandler(venCode);
    }
}
