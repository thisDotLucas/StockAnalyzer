package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONObject;
import prog3.AlertBox;

public class AlphaVantage {

    public JSONObject object;
    //public JSONObject[] cache = new JSONObject[7];
    //public int[] usedIndexes = {9, 9, 9, 9, 9, 9, 9};


    //gets json data och konverterar det till en string och sparar det i variabeln object
    public String getJson(String key, String interval, String timeSer, String symbol, String size, int index) throws Exception {

      //  for (int i = 0; i < usedIndexes.length; i++) {

        //    if (index == usedIndexes[i]) {

               // object = cache[i];
                //return getUpdate(object, key, interval);

            //}

        //}

            try {
                String url;
                if (size.equals("&outputsize="))
                    url = "https://www.alphavantage.co/query?function=" + timeSer + symbol + "&apikey=0OPBQ9QM2UDDW9TD";
                else
                    url = "https://www.alphavantage.co/query?function=" + timeSer + symbol + interval + size + "&apikey=0OPBQ9QM2UDDW9TD";
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

                //cache
               // for (int i = 0; i < cache.length; i++) {

                   // if (i == index) {

                     //   cache[i] = object;
                       // usedIndexes[i] = i;

                    //}

                //}
                return getUpdate(object, key, interval);

            } catch (Exception e) {

                AlertBox.display("Alert", "Fill all choice boxes.");

            }

        return "";

    }


    //Returnerar den informationen som användaren vill ha från Json stringen
    public static String getUpdate(JSONObject object, String key, String interval){

        JSONObject objects = null;
        String outp = "Showing " + key + "\n";
        interval = interval.replace("&interval=", "");

        if (interval.equals("1min")||interval.equals("5min")||interval.equals("15min")||interval.equals("30min")||interval.equals("60min"))
            objects = object.getJSONObject("Time Series (" + interval + ")");
        else
        objects = object.getJSONObject(interval);

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


