package s3534890.com.eventplanner.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.app.Application;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Errol on 25/08/16.
 */
public class Helpers extends Application {

    private static Helpers mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        mInstance = this;
    }

    public static synchronized Helpers getInstance(){
        return mInstance;
    }

    public void setConnectivityListener(InternetConnectionReceiver.ConnectivityReceiverListener listener){
        InternetConnectionReceiver.connectivityReceiverListener = listener;
    }

    public static String getTimeOfDistanceMatrixJSON(String origin, String destination){
        HttpURLConnection connection = null;
        URL url;
        String time = "";

        try{
            url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=AIzaSyAYELJeYwF5xjCD_0NHoX2dLgjEEFvMJlw");
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
            JSONObject timeObject = parentObject.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("duration");

            time =  timeObject.getString("text");
            String[] array = time.split(" ");
            if(array.length > 2){
                time = String.valueOf(Integer.valueOf(array[0]) * 60 + Integer.valueOf(array[2]));
            }else{
                time = array[0];
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        return time;
    }

    public static String getDistanceOfDistanceMatrixJSON(String origin, String destination){
        HttpURLConnection connection = null;
        URL url;
        String distance = "";

        try{
            url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=AIzaSyAYELJeYwF5xjCD_0NHoX2dLgjEEFvMJlw");
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
            distance = distanceObject.getString("text");

        }catch (Exception e){

        }finally {
            connection.disconnect();
        }
        return distance;
    }

    public static long timeToEventInMinutes(long now, long when){
        return TimeUnit.MILLISECONDS.toMinutes(when - now);
    }

    public static void showSnackbar(View v, String message){
        Snackbar snackbar = Snackbar.make(v,message,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static String getAddressFromGoogleGeocode(String location){
        HttpURLConnection connection = null;
        URL url;
        String address = "";

        try{
            url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + location + "&key=AIzaSyBVcW6s78bzUhfelTBJUkX3a3aC68_sLlo");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            Log.d("AAA","Google Geo");

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
            JSONObject addressObject = parentObject.getJSONArray("results").getJSONObject(0);
            address = addressObject.getString("formatted_address");

            String[] array = address.split(" ");
            address = array[1] + " " + array[2];

        }catch (Exception e){

        }finally {
            connection.disconnect();
        }
        return address;
    }

    public static void showAlertDialog(Context c, String title, String message, String positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setNegativeButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).show();
    }
}
