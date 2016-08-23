package s3534890.com.eventplanner.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import s3534890.com.eventplanner.Controller.AddNewEventButtonController;
import s3534890.com.eventplanner.Controller.DatePickerController;
import s3534890.com.eventplanner.Controller.DialogViewController;
import s3534890.com.eventplanner.Controller.RecyclerViewAdapter;
import s3534890.com.eventplanner.Controller.TimePickerController;
import s3534890.com.eventplanner.R;

public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        // initialise dialog
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
        builder = new AlertDialog.Builder(this);

        // set the adapter and layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(this));
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

//            DialogViewController dialogViewController = new DialogViewController();
//            dialogViewController.show(getSupportFragmentManager(),"Add New Event");

            builder.setMessage("Add New Event");
            builder.setView(dialogView);
            builder.setPositiveButton("Add",new AddNewEventButtonController());
            builder.setNegativeButton("Cancel",null);
            AlertDialog alert = builder.create();

            if(dialogView.getParent() != null){
                ((ViewGroup)dialogView.getParent()).removeView(dialogView);
            }
            alert.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
