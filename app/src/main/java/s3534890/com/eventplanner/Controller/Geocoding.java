package s3534890.com.eventplanner.Controller;

import android.os.AsyncTask;

/**
 * Created by Errol on 2/10/16.
 */
public class Geocoding extends AsyncTask {

    private String location;

    public Geocoding(String location) {
        this.location = location;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        return Helpers.getAddressFromGoogleGeocode(location);
    }
}
