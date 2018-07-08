package Search.Controller;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Requester
{
    public Object sendGetRequest(String url)
    {
        try
        {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            int responseCode = con.getResponseCode();
            if (responseCode == 200)
            {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                in.close();
                return response;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public CookieAndResponse sendPostRequest(String url, String parameters, String linearCookie)
    {
        CookieAndResponse cookieAndResponse = null;
        try
        {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            con.setRequestMethod("POST");
            con.setRequestProperty("Cookie", linearCookie);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(parameters);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            ArrayList<String> cookies = new ArrayList<>();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String headerName = null;
                String cookie;
                for (int j = 1; (headerName = con.getHeaderFieldKey(j)) != null; j++) {
                    if (headerName.equals("Set-Cookie")) {
                        cookie = con.getHeaderField(j);
                        cookies.add(cookie);
                    }
                }
                cookieAndResponse = new CookieAndResponse(response, cookies);
            }
            return cookieAndResponse;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPostWithJson(String url, JSONObject json, BasicCookieStore cookieStore)
    {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json.toJSONString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            Desktop d = Desktop.getDesktop();
            try {
                d.browse(new URI("https://changal.com/order/restaurant/init/checkout"));

            } catch (IOException | URISyntaxException e2) {
                e2.printStackTrace();
            }
            System.out.println();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public CookieAndResponse sendGetRequestC(String url)
    {
        try
        {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            int responseCode = con.getResponseCode();
            if (responseCode == 200)
            {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }
                in.close();
                String headerName = null;
                String cookie;
                CookieAndResponse cookieAndResponse;
                ArrayList<String> cookies = new ArrayList<>();
                for (int j = 1; (headerName = con.getHeaderFieldKey(j)) != null; j++) {
                    if (headerName.equals("Set-Cookie")) {
                        cookie = con.getHeaderField(j);
                        cookies.add(cookie);
                    }
                }
                cookieAndResponse = new CookieAndResponse(response, cookies);
                return cookieAndResponse;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

class CookieAndResponse
{
    StringBuffer stringBuffer;
    ArrayList<String> cookie;
    CookieAndResponse(StringBuffer sb, ArrayList<String> cookies)
    {
        stringBuffer = sb;
        this.cookie = cookies;
    }
}
