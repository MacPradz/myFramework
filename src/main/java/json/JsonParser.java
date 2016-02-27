package json;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by wp on 2016-02-27.
 */
public class JsonParser {
    private HashMap<String, String> countryDict = new HashMap<String, String>() {
        {
            put("IT", ";ITA");
            put("United Kingdom", "GBR");
            put("POR", ";PRT");
            put("ES", ";ESP");
            put("Great Britain", "UKIE");
            put("DE", ";DEU");
            put("PL", ";POL");
            put("Czech Republic", "CZE");
            put("FR", ";FRA");
            put("Scotland", "GBR.UKM");
            put("BE", ";BEL");
            put("NL", ";NLD");
            put("GB", ";GBR"); ///my idea
        }
    };
    private HashMap<String, String> techDict = new HashMap<String, String>() {
        {
            put("WN", ";Wind.Onsh;");
            put("WF", ";Wind.Offsh;");
            put("HY", ";Hydro;");
            put("BM", ";Thermal;Biomass");
        }
    };
    public static final DateTimeZone TZ = DateTimeZone.forID("UTC");
    public static final DateTimeFormatter DTF = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").withZone(TZ);

    public String parse(InputStream is) {
        String document = convertStreamToString(is);

        JSONObject mainJsonObject = (JSONObject) JSONValue.parse(document);

        DateTime valueDate = getValueDate(mainJsonObject);
        System.out.println(valueDate);


        JSONArray array = (JSONArray) mainJsonObject.get("locations");
        Set<String> set = new HashSet<>();
        for (Object o : array) {
            JSONObject jsonObject = (JSONObject) o;
            parseLocation(jsonObject, set);
        }
        for (String element : set) {
            System.out.println(element);
        }


        return "x";
    }

    private DateTime getValueDate(JSONObject mainJsonObject) {
        String update = (String) mainJsonObject.get("updatetime");
        String dayString = "2016-02-27";
        String dateString = dayString + " " + update;
        return DTF.parseDateTime(dateString);
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void parseLocation(JSONObject jsonObject, Set<String> set) {
        boolean isActive = (boolean) jsonObject.get("active");
        if (!isActive) {
            return;
        }
        String land = jsonObject.get("land").toString();
        String type = jsonObject.get("type").toString();
        set.add(type);
    }
}
