import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddingToBasket
{
    public static void main(String[] args)throws IOException
    {
        String address1="https://snappfood.ir/order/cart/update/";
        String txtAddress ="D:\\TahlilProject\\Jsoup\\src\\order.txt";
        ArrayList<String> numbers=new ArrayList<>();
        String vendorCode;
        String operationMode="1";
        String productId;
        String lastProductId;
        File file =new File(txtAddress);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String temp=scanner.next();
            String[] array=temp.split(":");
            numbers.add(array[1]);
        }

    }
}