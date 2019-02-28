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

    public static String getJson(String key) throws Exception {

        try {

            String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=15min&outputsize=full&apikey=0OPBQ9QM2UDDW9TD";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
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

            String outp = "";
            JSONObject object = new JSONObject(response.toString());
            JSONObject objects = object.getJSONObject("Time Series (15min)");
            Iterator<String> keys = objects.keys();

            while (keys.hasNext()){

                String key1 = keys.next();

                if(objects.get(key1) instanceof JSONObject){

                    JSONObject values = objects.getJSONObject(key1);

                    outp += "Date: " + key1 + ": " + values.getString(key) + "\n";

                }

            }

            return outp;

        } catch (Exception e) {

            System.out.println(e);

        }

        return "";
    }

}
