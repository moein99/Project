import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.IOException;
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
}































    /*
    public int Login()
    {
        try {
            Connection.Response loginForm = Jsoup
                    .connect("http://snappfood.ir/")
                    .method(Connection.Method.GET)
                    .execute();
            System.out.println("\n"+"login page up"+"\n");
            Scanner input = new Scanner(System.in);
            String number;
            String pass;
            number = input.next();
            pass = input.next();
            if(number.length() != 11)
            {
                return 0;
            }
            else if (number.length() == 0)
            {
                return 0;
            }
            else if (number.length() == 0)
            {
                return 0;
            }
            Document response = Jsoup.connect("https://snappfood.ir/auth/login_check")
                    .ignoreContentType(true)
                    .data("_username", number)
                    .data("_password", pass)
                    .data("_login_method", "password")
                    .cookies(loginForm.cookies())
                    .method(Connection.Method.POST)
                    .post();
            String body;
            body = response.body().toString();
            if(body.contains("{\"status\":true}"))
            {
                System.out.println("به اسنپ فود خوش آمدید");
                return 0;
            }
            else if(body.contains("false"))
            {
                System.out.println("کلمه عبور یا شماره نا معتبر");
                return 0;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    */

/* TODO */
/*
    public int SabteSefaresh()
    {
        try
        {
            Connection.Response loginForm = Jsoup
                    .connect("http://snappfood.ir/")
                    .method(Connection.Method.GET)
                    .execute();
            System.out.println("\n"+"sabte sefaresh page up"+"\n");
            System.out.println("رستوران مورد نظر خود را وارد کنید:");
            Scanner input = new Scanner(System.in);
            String ResDirectAddress;
            String Res= "";
            String ResArr[];
            String ResName = input.nextLine() ;
            String body = loginForm.body().toString();
            String bodyArr[] = body.split("<div style=\"font-size: 13px; margin-top: 15px; direction:rtl\">");


            for (int i = 0;i< bodyArr.length;i++)
            {
                if(bodyArr[i].contains(ResName))
                {
                    Res = bodyArr[i-1];
                    break;
                }
            }

            ResArr = Res.split("<a href=");
            ResArr = ResArr[1].split(" target=");
            ResDirectAddress = "http://snappfood.ir"+ResArr[0].replace("\"","");
            System.out.println(ResDirectAddress);
            Document loginFormRes = Jsoup
                    .connect(ResDirectAddress)
                    .method(Connection.Method.GET)
                    .get();

            loginFormRes.getClass();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}

*/
