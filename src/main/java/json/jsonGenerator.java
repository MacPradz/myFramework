package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MacPradz on 2016-02-27.
 */
public class jsonGenerator {
    public static final List<String> CONTINENT_LIST = new ArrayList<String>() {{
        add("Africa");
        add("Europe");
        add("Asia");
        add("North America");
        add("South America");
        add("Australia");
        add("Antarctica");
    }};
    public static final Map<String, String> COUNTRY_CAPITOL_MAP = new HashMap<String, String>() {{
        put("Poland", "Warsaw");
        put("Germany", "Berlin");
        put("Egypt","Cairo");
    }};


    public static void main(String[] args) {
        JSONObject mainJsonObject = new JSONObject();
        JSONArray continents = new JSONArray();

        for (String continent : CONTINENT_LIST) {
            JSONObject continentJsonObject = new JSONObject();
            continentJsonObject.put("name", continent);
            continents.add(continentJsonObject);
        }

        JSONObject j = new JSONObject(COUNTRY_CAPITOL_MAP);
        System.out.println(j.toJSONString());

//        continents.add(europe);

        mainJsonObject.put("continents", continents);

        System.out.println(mainJsonObject.toJSONString());
        JSONArray continentsArray = (JSONArray) mainJsonObject.get("continents");
        for (Object o : continentsArray) {
            JSONObject obj = (JSONObject) o;
            String name = (String) obj.get("name");
            System.out.println(name);
        }
    }
}
