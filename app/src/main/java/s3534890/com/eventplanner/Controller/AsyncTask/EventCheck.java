package s3534890.com.eventplanner.Controller.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import s3534890.com.eventplanner.Controller.Helpers;

/**
 * Created by Errol on 29/09/16.
 */
public class EventCheck extends AsyncTask {

    private String origin,destination,time;

    public EventCheck(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        time = Helpers.getTimeOfDistanceMatrixJSON(origin,destination);
        return time;
    }

    public String getTime() {
        return time;
    }
}
