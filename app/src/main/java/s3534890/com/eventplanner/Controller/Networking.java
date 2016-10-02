package s3534890.com.eventplanner.Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Errol on 9/09/16.
 */
public class Networking extends AsyncTask {

    private Context c;
    private String origin,destination,distance,time;

    public Networking(Context c, String origin, String destination) {
        this.c = c;
        this.origin = origin;
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    @Override
    protected String doInBackground(Object[] ob) {
        distance = Helpers.getDistanceOfDistanceMatrixJSON(origin,destination);
        time = Helpers.getTimeOfDistanceMatrixJSON(origin,destination);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        for(int i = 0; i < 2; i++){
            Toast.makeText(c, "The distance is " + distance + ", the driving time is " + time + " minutes.", Toast.LENGTH_LONG).show();
        }
    }
}
