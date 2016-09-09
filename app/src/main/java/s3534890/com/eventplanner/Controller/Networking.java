package s3534890.com.eventplanner.Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Errol on 9/09/16.
 */
public class Networking extends AsyncTask {

    Context c;
    String origin,destination,distance,time;

    public Networking(Context c, String origin, String destination) {
        this.c = c;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    protected String doInBackground(Object[] ob) {
        getDistanceMatrixJSON(origin,destination);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        for(int i = 0; i < 3; i++){
            Toast.makeText(c, "The distance is " + distance + ", the driving time is " + time + ".", Toast.LENGTH_LONG).show();
        }
    }

    public void getDistanceMatrixJSON(String origin, String destination){
        HttpURLConnection connection = null;
        URL url;


        try{
            url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=AIzaSyBGe8_wStUWOMkGxvsUhPCfBDYcq_PpR80");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            Log.d("AAA","Request Sent");

            // receive data
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }

            // process JSON result
            String JSONResult = buffer.toString();
            JSONObject parentObject = new JSONObject(JSONResult);
            JSONObject distanceObject = parentObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance");
            JSONObject timeObject = parentObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration");
            distance = distanceObject.getString("text");
            time = timeObject.getString("text");

        }catch (Exception e){

        }finally {
            connection.disconnect();
        }
    }


}
