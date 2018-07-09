package Search.Controller;

import Search.Model.SystemAPIBuilder;
import Search.Model.URLModel;

public class ChangalAPIBuilder extends SystemAPIBuilder
{
    @Override
    public String buildAPI(String key, String data)
    {
        if (key.equals("getRegion"))
        {
            return getURL(key) + "?" + data;
        }
        else if (key.equals("getRestaurants"))
        {
            return getURL(key) + "?district_id=" + "REGION_NUMBER" + "&" + data;
        }
        else if (key.equals("getMenu"))
        {
            return getURL(key);
        }
        else if (key.equals("addToBasket"))
        {
            return getURL(key);
        }
        else if (key.equals("login"))
        {
            String[] userAndPass = data.split("&");
            return getURL(key).replace("USERNAME", userAndPass[0]).replace("PASSWORD", userAndPass[1]);
        }
        return null;
    }

    private String getURL(String key)
    {
        if (key.equals("getRegion"))
        {
            return URLModel.changal_getRegion_api;
        }
        else if (key.equals("getRestaurants"))
        {
            return URLModel.changal_getRestaurants_api;
        }
        else if (key.equals("getMenu"))
        {
            return URLModel.changal_getMenu_api;
        }
        else if (key.equals("addToBasket"))
        {
            return URLModel.changal_addToBasket_api;
        }
        else if (key.equals("login"))
        {
            return URLModel.changal_login_api;
        }
        return null;
    }
}
