package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class AlphaVantage {

    public JSONObject object;

    public String getJson(String key, String interval, String timeSer, String symbol, String size) throws Exception {

        try {

            String url = "https://www.alphavantage.co/query?function=" + timeSer + symbol + interval + size + "&apikey=0OPBQ9QM2UDDW9TD";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            object = new JSONObject(response.toString());
            return getUpdate(object, key, interval);

        } catch (Exception e) {

            System.out.println(e);

        }

        return "";
    }

    public static String getUpdate(JSONObject object, String key, String interval){

        String outp = "Showing " + key + "\n";
        JSONObject objects = object.getJSONObject("Time Series (" + interval.replace("&interval=", "") + ")");
        Iterator<String> keys = objects.keys();

        while (keys.hasNext()){

            String key1 = keys.next();

            if(objects.get(key1) instanceof JSONObject){

                JSONObject values = objects.getJSONObject(key1);

                outp += "Date: " + key1 + ": " + values.getString(key) + "\n";

            }

        }

        return outp;

    }

    }


