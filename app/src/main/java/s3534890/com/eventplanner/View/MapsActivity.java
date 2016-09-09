package s3534890.com.eventplanner.View;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.StringTokenizer;

import s3534890.com.eventplanner.Controller.Networking;
import s3534890.com.eventplanner.Controller.RecyclerViewAdapter;
import s3534890.com.eventplanner.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private Criteria mCriteria;
    private LatLng eventPosition;
    private String venue;
    private String latlng;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // to get the current location
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mCriteria = new Criteria();
        location = mLocationManager.getLastKnownLocation(mLocationManager.getBestProvider(mCriteria, false));

        // to get event details
        Bundle extra = getIntent().getExtras();
        latlng = extra.getString("location");
        StringTokenizer token = new StringTokenizer(latlng,",");
        eventPosition = new LatLng(Double.valueOf(token.nextToken()),Double.valueOf(token.nextToken()));
        venue = extra.getString("venue");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 40));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))   // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera to east
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.addMarker(new MarkerOptions()
                    .position(eventPosition)
                    .title(venue)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            // send HTTP POST request for distance
            String currentLocation = String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude());
            new Networking(this,currentLocation,latlng).execute();
        }else{
            Toast.makeText(MapsActivity.this, "Null Location", Toast.LENGTH_SHORT).show();
        }
    }

}
