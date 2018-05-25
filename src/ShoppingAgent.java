import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class ShoppingAgent {
    private Scrapper scrapper;
    private boolean scrapperStatus;
    private ArrayList<String> requests;
    private Map<String, String> cookies;


    ShoppingAgent() {
        scrapper = new Scrapper();
        scrapperStatus = false;
        requests = new ArrayList<>();
    }


    public void runScrapper() {
        scrapper.run(true);
        scrapperStatus = true;
        cookies = scrapper.getCookies();
    }


    public void addRequest(String restaurantName, ArrayList<String> productNames) {
        if (!scrapperStatus) {
            runScrapper();
        }
        Map<String, String> data = scrapper.getData();
        for (String food : productNames) {
            requests.add(data.get(restaurantName + " " + food));
        }
    }


    public void submitRequest() throws IOException
    {
        ArrayList<String> numbers=new ArrayList<>();
        String addingToBasket_link = Config.addingToBasket_link;
        String vendorCode;
        String operationMode = "1";
        String productId;
        String lastProductId;
        numbers = requestFetch(numbers);
        int counter=0;
        while (counter<numbers.size())
        {
            vendorCode= numbers.get(counter);
            productId=numbers.get(counter+1);
            if(counter>=2)
            {
                lastProductId=numbers.get(counter-1);
            }
            else{lastProductId=numbers.get(1);}
            Connection.Response execute = Jsoup.connect(addingToBasket_link).ignoreContentType(true).
                    data("vendor_code",vendorCode).
                    data("operation_mode",operationMode).
                    data("product_id",productId).
                    data("last_target_id",lastProductId).method(Connection.Method.POST).cookies(cookies)
                    .execute();

            Document doc = execute.parse();
            cookies = execute.cookies();
            if(doc.body().toString().contains("\"status\":\"1\""))
            {
                System.out.println("added to basket!");
            }
            counter+=2;
        }
    }
    public void submit() throws IOException {
        ArrayList<String> numbers=new ArrayList<>();
        String addingToBasket_link = Config.addingToBasket_link;
        String is_checkout="true";
        String operationMode = "5";
        String payment_type="online";
        String productId = "-1";
        String vendorCode;
        numbers =requestFetch(numbers);
        int counter=0;
        while (counter<numbers.size()) {
            vendorCode = numbers.get(counter);
            Connection.Response execute = Jsoup.connect(addingToBasket_link).ignoreContentType(true).
                    data("is_checkout",is_checkout).
                    data("operation_mode", operationMode).
                    data("payment_type", payment_type).
                    data("product_id", productId).
                    data("vendor_code", vendorCode).method(Connection.Method.POST).cookies(cookies)
                    .execute();

            Document doc = execute.parse();
            cookies = execute.cookies();
            if (doc.body().toString().contains("\"status\":\"1\"")) {
                System.out.println("Submited!");
            }
            counter += 2;
        }
    }
    public void submitAddress() throws IOException {
        ArrayList<String> numbers=new ArrayList<>();
        String addingToBasket_link = Config.addingToBasket_link;
        String address_id="3737635"; //Almoein must get me this number
        String is_checkout="true";
        String operationMode = "5";
        String payment_type="online";
        String productId = "-1";
        String vendorCode;
        numbers =requestFetch(numbers);
        vendorCode = numbers.get(0);
        Connection.Response execute = Jsoup.connect(addingToBasket_link).ignoreContentType(true).
                data("address_id",address_id).
                data("is_checkout",is_checkout).
                data("operation_mode", operationMode).
                data("payment_type", payment_type).
                data("product_id", productId).
                data("vendor_code", vendorCode).method(Connection.Method.POST).cookies(cookies)
                .execute();
        Document doc = execute.parse();
        cookies = execute.cookies();
        if (doc.body().toString().contains("\"status\":\"1\"")) {
            System.out.println("Address Submited!");
        }
    }
    public void goBank() throws IOException {
        ArrayList<String> numbers=new ArrayList<>();
        String bank_payment = Config.bank_payment;
        String online_pay_type="AP";
        String payment_method="online_payment";
        String SelectAddresses_addressType="select";
        String SelectAddresses_latitude="";
        String SelectAddresses_longitude="";
        String SelectAddresses_payType="ONLINE";
        String SelectAddresses_slAddress="3737635";
        String voucher_input="";
        String vendorCode;
        numbers =requestFetch(numbers);
        vendorCode=numbers.get(0);
        Connection.Response execute = Jsoup.connect(bank_payment+vendorCode).ignoreContentType(true).
                data("online_pay_type",online_pay_type).
                data("payment_method",payment_method).
                data("SelectAddresses[address_type]", SelectAddresses_addressType).
                data("SelectAddresses[latitude]", SelectAddresses_latitude).
                data("SelectAddresses[longitude]", SelectAddresses_longitude).
                data("SelectAddresses[pay_type]", SelectAddresses_payType).
                data("SelectAddresses[sladdress]", SelectAddresses_slAddress).
                data("voucher_input", voucher_input).method(Connection.Method.POST).cookies(cookies)
                .execute();
        System.out.println("banked!");
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI("https://snappfood.ir/order/checkout/vendor/"+vendorCode));
        } catch (IOException | URISyntaxException e2) {
            e2.printStackTrace();
        }
    }
    private ArrayList<String> requestFetch(ArrayList<String> numbers) {
        String[] temp;
        String[] temp1;
        for (String request : requests)
        {
            temp = request.split(" ");
            temp1 = temp[0].split(":");
            numbers.add(temp1[1]);
            temp1 = temp[1].split(":");
            numbers.add(temp1[1]);
        }
        return numbers;
    }
}

