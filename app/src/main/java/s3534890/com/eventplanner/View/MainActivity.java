package s3534890.com.eventplanner.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;
import s3534890.com.eventplanner.Controller.CalendarCollection;
import s3534890.com.eventplanner.Controller.DatePickerController;
import s3534890.com.eventplanner.Controller.DetailViewListener;
import s3534890.com.eventplanner.Controller.RecyclerViewAdapter;
import s3534890.com.eventplanner.Controller.SimpleTouchCallBack;
import s3534890.com.eventplanner.Controller.TimePickerController;
import s3534890.com.eventplanner.Model.Events;
import s3534890.com.eventplanner.R;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity{

    private AlertDialog.Builder builder;
    private View dialogView;
    public static TextView startDate;
    public static TextView endDate;
    public static TextView startTime;
    public static TextView endTime;
    public static EditText eventTitle;
    public static TextView venue;
    public static TextView location;
    public static EditText eventNote;
    public static TextView attendees;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Realm realm;
    private RecyclerViewAdapter mAdapter;
    private RealmResults<Events> mResults;
    private SimpleTouchCallBack mSimpleTouchCallBack;
    private ItemTouchHelper mItemTouchHelper;
    private static final LatLngBounds BOUNDS = new LatLngBounds(new LatLng(-37.811569,144.960870),new LatLng(-37.806972,144.965814));


    private RealmChangeListener realmChangeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            mAdapter.update(mResults);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        CalendarCollection.date_collection_arr = new ArrayList<>();

        // construct the realm db
        realm = Realm.getDefaultInstance();
        mResults = realm.where(Events.class).findAllAsync();

        // construct the recyclerView and layout manager
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        recyclerView.addItemDecoration(new Divider(this,LinearLayoutManager.VERTICAL));

        //construct the recyclerView adapter
        mAdapter = new RecyclerViewAdapter(this,realm,mResults,detailViewListener);
        mAdapter.setHasStableIds(true);

        // construct the swipe callback and helper
        mSimpleTouchCallBack = new SimpleTouchCallBack(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mSimpleTouchCallBack);

        // set the recyclerView adapter and attach swipe to recyclerView
        recyclerView.setAdapter(mAdapter);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        // construct dialog view
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
        builder = new AlertDialog.Builder(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mResults.addChangeListener(realmChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListener(realmChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_event_button) {

            startDate = (TextView) dialogView.findViewById(R.id.dialog_view_start_date);
            endDate = (TextView) dialogView.findViewById(R.id.dialog_view_end_date);
            startTime = (TextView) dialogView.findViewById(R.id.dialog_view_start_time);
            endTime = (TextView) dialogView.findViewById(R.id.dialog_view_end_time);
            eventTitle = (EditText) dialogView.findViewById(R.id.dialog_view_title_text);
            venue = (TextView) dialogView.findViewById(R.id.dialog_view_venue);
            location = (TextView) dialogView.findViewById(R.id.dialog_view_location);
            eventNote = (EditText) dialogView.findViewById(R.id.dialog_view_note);
            attendees = (TextView) dialogView.findViewById(R.id.dialog_view_attendee);

            startDate.setOnClickListener(new DatePickerController());
            endDate.setOnClickListener(new DatePickerController());
            startTime.setOnClickListener(new TimePickerController());
            endTime.setOnClickListener(new TimePickerController());

            venue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getContext());
//                    View mapview = LayoutInflater.from(view.getContext()).inflate(R.layout.map_layout,null);
//                    builder2.setView(mapview);
//                    builder2.create().show();
//
//                    Toast.makeText(view.getContext(),"venue pressed",Toast.LENGTH_LONG).show();
                    try{
                        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                        intentBuilder.setLatLngBounds(BOUNDS);
                        startActivityForResult(intentBuilder.build(MainActivity.this),2);
                    }catch(GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                        e.printStackTrace();
                    }
                }
            });

            attendees.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pickContact(view);
                }
            });

            builder.setMessage("Add New Event");
            builder.setView(dialogView);
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String title = eventTitle.getText().toString();
                    String startD = startDate.getText().toString();
                    String startT = startTime.getText().toString();
                    String endD = endDate.getText().toString();
                    String endT = endTime.getText().toString();
                    String note = eventNote.getText().toString();
                    String att = attendees.getText().toString();
                    String vne = venue.getText().toString();
                    String loc = location.getText().toString();
                    long added = System.currentTimeMillis();
                    Calendar calendar = Calendar.getInstance();
                    StringTokenizer token = new StringTokenizer(startD,"/");
                    calendar.set(Calendar.DAY_OF_MONTH,Integer.valueOf(token.nextToken().trim()));
                    calendar.set(Calendar.MONTH,Integer.valueOf(token.nextToken())-1);
                    calendar.set(Calendar.YEAR,Integer.valueOf(token.nextToken()));



                    Events newEvent = new Events(UUID.randomUUID().toString().replaceAll("-",""),added,title,calendar.getTimeInMillis(),att,note,endD,startT,endT,vne,loc);
                    realm.beginTransaction();
                    realm.copyToRealm(newEvent);
                    realm.commitTransaction();
                    clearAll();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    clearAll();
                    dialogInterface.cancel();
                }
            });
            AlertDialog alert = builder.create();

            if(dialogView.getParent() != null){
                ((ViewGroup)dialogView.getParent()).removeView(dialogView);
            }
            alert.show();
            return true;
        }else if(id == R.id.menu_sorting_asc){
            mResults = realm.where(Events.class).findAllSortedAsync("startDate");
            mResults.addChangeListener(realmChangeListener);
            return true;
        }else if(id == R.id.menu_sorting_desc){
            mResults = realm.where(Events.class).findAllSortedAsync("startDate", Sort.DESCENDING);
            mResults.addChangeListener(realmChangeListener);
            return true;
        }else if(id == R.id.menu_view_in_calendar){
            startActivity(new Intent(MainActivity.this,CalendarActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void pickContact(View v){
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    contactPicked(data);
                    break;
                case 2:
                    Place place = PlacePicker.getPlace(this,data);
                    CharSequence name = place.getName();
                    LatLng latLng = place.getLatLng();
                    String location = String.valueOf(latLng.latitude).substring(0,10) + "," + String.valueOf(latLng.longitude).substring(0,10);

                    this.venue.setText(name);
                    this.location.setText(location);
                    break;
            }
        }
    }

    private void contactPicked(Intent data){
        Cursor cursor = null;
        try{
            String name = null;
            Uri uri = data.getData();

            cursor = getContentResolver().query(uri,null,null,null,null);
            cursor.moveToFirst();

            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            name = cursor.getString(nameIndex);
            attendees.setText(name);
        }catch(Exception e){

        }
    }

    public void clearAll(){
        eventTitle.setText("");
        startDate.setText("");
        startTime.setText("");
        endDate.setText("");
        endTime.setText("");
        venue.setText("");
        location.setText("");
        eventNote.setText("");
        attendees.setText("");
    }

    private DetailViewListener detailViewListener = new DetailViewListener() {
        @Override
        public void onDetailView(int position) {
            showDetailViewDialog(position);
        }
    };

    public void showDetailViewDialog(int position){
        DetailViewDialog dialog = new DetailViewDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("POSITION",position);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(),"Detail");
    }


}
