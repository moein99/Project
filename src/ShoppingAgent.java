
import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.Scanner;
/**
 * Created by AMIR-LMPD on 4/3/2018.
 */



public class ShoppingAgent {

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
            System.out.println("شماره ی خود را وارد کنبد :");
            number = input.next();
            System.out.println("رمز عبور خود را وارد کنید :");
            pass = input.next();
            if(number.length() != 11)
            {
                System.out.println("فرمت شماره موبایل اشتباه می باشد");
                return 0;
            }
            else if (number.length() == 0)
            {
                System.out.println("شماره خود را وارد نکردید");
                return 0;
            }
            else if (number.length() == 0)
            {
                System.out.println("رمز خود را وارد نکردید");
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

/* TODO */
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

