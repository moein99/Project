package Search.Controller;

import org.json.simple.JSONArray;

public abstract class SystemCtrl {
    public abstract void setAPI(String key, String data);
    public abstract JSONArray getRestuarants(String API);
    public abstract String getAPI();
    public abstract JSONArray getMenu(String API, String code);
}
