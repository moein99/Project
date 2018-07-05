package Search.Controller;

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
            return null;
        }
        else if (key.equals("addToBasket"))
        {
            return null;
        }
        else if (key.equals("login"))
        {
            return null;
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
            return null;
        }
        else if (key.equals("addToBasket"))
        {
            return null;
        }
        else if (key.equals("login"))
        {
            return null;
        }
        return null;
    }
}
