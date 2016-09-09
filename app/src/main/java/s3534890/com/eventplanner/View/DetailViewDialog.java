package s3534890.com.eventplanner.View;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import io.realm.Realm;
import s3534890.com.eventplanner.Controller.DatePickerController;
import s3534890.com.eventplanner.Controller.RecyclerViewAdapter;
import s3534890.com.eventplanner.Controller.TimePickerController;
import s3534890.com.eventplanner.R;

/**
 * Created by Errol on 25/08/16.
 */
public class DetailViewDialog extends DialogFragment{

    private Button viewButton,doneButton,editButton,mapButton;
    private TextView title;
    private TextView startDate;
    private TextView startTime;
    private TextView endDate;
    private TextView endTime;
    private TextView venue;
    private TextView location;
    private TextView note;
    private TextView attendee;
    public static TextView editStartDate,editStartTime,editEndDate,editEndTime,editVenue,editLocation,editAttendee;
    private EditText editTitle,editNote;
    private int position;
    private AlertDialog.Builder builder;
    private View dialogView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_detail_dialog,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewButton = (Button) view.findViewById(R.id.view_detail_view_button);
        doneButton = (Button) view.findViewById(R.id.view_detail_done_button);
        editButton = (Button) view.findViewById(R.id.view_detail_edit_button);
        mapButton = (Button) view.findViewById(R.id.view_detail_map_button);
        title = (TextView) view.findViewById(R.id.view_detail_title);
        startDate = (TextView) view.findViewById(R.id.view_detail_start_date);
        startTime= (TextView) view.findViewById(R.id.view_detail_start_time);
        endDate= (TextView) view.findViewById(R.id.view_detail_end_date);
        endTime= (TextView) view.findViewById(R.id.view_detail_end_time);
        venue= (TextView) view.findViewById(R.id.view_detail_venue);
        location= (TextView) view.findViewById(R.id.view_detail_location);
        note= (TextView) view.findViewById(R.id.view_detail_note);
        attendee= (TextView) view.findViewById(R.id.view_detail_attendee);
        dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view,null);
        builder = new AlertDialog.Builder(view.getContext());

        // get the position of the item in the list that was clicked
        Bundle arguments = getArguments();
        if(arguments != null){
            position = arguments.getInt("POSITION");
        }

        final Date date = new Date(RecyclerViewAdapter.mResults.get(position).getStartDate());
        SimpleDateFormat df = new SimpleDateFormat("d/M/yy");
        title.setText("Title: " + RecyclerViewAdapter.mResults.get(position).getEventName());
        startDate.setText("Start Date: " + df.format(date));
        startTime.setText("Start Time: " + RecyclerViewAdapter.mResults.get(position).getStartTime());
        endDate.setText("End Date: " + RecyclerViewAdapter.mResults.get(position).getEndDate());
        endTime.setText("End Time: " + RecyclerViewAdapter.mResults.get(position).getEndTime());
        venue.setText("Venue: " + RecyclerViewAdapter.mResults.get(position).getVenue());
        location.setText("Location: " + RecyclerViewAdapter.mResults.get(position).getLocation());
        note.setText("Notes: " + RecyclerViewAdapter.mResults.get(position).getNotes());
        attendee.setText("Attendees: " + RecyclerViewAdapter.mResults.get(position).getAttendees());

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),CalendarActivity.class));
                dismiss();
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MapsActivity.class);
                intent.putExtra("location",RecyclerViewAdapter.mResults.get(position).getLocation());
                intent.putExtra("venue",RecyclerViewAdapter.mResults.get(position).getVenue());
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat df2 = new SimpleDateFormat("d/M/yyyy");
                builder.setTitle("Edit Event");
                builder.setCancelable(false);
                builder.setView(dialogView);

                editStartDate = (TextView) dialogView.findViewById(R.id.dialog_view_start_date);
                editEndDate = (TextView) dialogView.findViewById(R.id.dialog_view_end_date);
                editStartTime = (TextView) dialogView.findViewById(R.id.dialog_view_start_time);
                editEndTime = (TextView) dialogView.findViewById(R.id.dialog_view_end_time);
                editTitle = (EditText) dialogView.findViewById(R.id.dialog_view_title_text);
                editVenue = (TextView) dialogView.findViewById(R.id.dialog_view_venue);
                editLocation = (TextView) dialogView.findViewById(R.id.dialog_view_location);
                editNote = (EditText) dialogView.findViewById(R.id.dialog_view_note);
                editAttendee = (TextView) dialogView.findViewById(R.id.dialog_view_attendee);

                editStartDate.setOnClickListener(new DatePickerController());
                editEndDate.setOnClickListener(new DatePickerController());
                editStartTime.setOnClickListener(new TimePickerController());
                editEndTime.setOnClickListener(new TimePickerController());

                editTitle.setText(RecyclerViewAdapter.mResults.get(position).getEventName());
                editStartDate.setText(df2.format(date));
                editStartTime.setText(RecyclerViewAdapter.mResults.get(position).getStartTime());
                editEndDate.setText(RecyclerViewAdapter.mResults.get(position).getEndDate());
                editEndTime.setText(RecyclerViewAdapter.mResults.get(position).getEndTime());
                editVenue.setText( RecyclerViewAdapter.mResults.get(position).getVenue());
                editLocation.setText(RecyclerViewAdapter.mResults.get(position).getLocation());
                editNote.setText(RecyclerViewAdapter.mResults.get(position).getNotes());
                editAttendee.setText(RecyclerViewAdapter.mResults.get(position).getAttendees());

                editVenue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                            intentBuilder.setLatLngBounds(MainActivity.BOUNDS);
                            startActivityForResult(intentBuilder.build(getActivity()),2);
                        }catch(GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e){
                            e.printStackTrace();
                        }
                    }
                });

                editAttendee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pickContact(view);
                    }
                });

                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Realm realm = Realm.getDefaultInstance();
                        String title = editTitle.getText().toString();
                        String startD = editStartDate.getText().toString();
                        String startT = editStartTime.getText().toString();
                        String endD = editEndDate.getText().toString();
                        String endT = editEndTime.getText().toString();
                        String note = editNote.getText().toString();
                        String att = editAttendee.getText().toString();
                        String vne = editVenue.getText().toString();
                        String loc = editLocation.getText().toString();
                        Calendar calendar = Calendar.getInstance();

                        if(title.equals("") || startD.isEmpty() || startT.isEmpty() || endD.isEmpty() || endT.isEmpty() || note.isEmpty()
                                || att.isEmpty() || vne.isEmpty() || loc.isEmpty()) {
                            new AlertDialog.Builder(builder.getContext())
                                    .setTitle("Alert")
                                    .setMessage("All fields are required.")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    }).show();
                        }else{
                            StringTokenizer token = new StringTokenizer(startD,"/");
                            calendar.set(Calendar.DAY_OF_MONTH,Integer.valueOf(token.nextToken().trim()));
                            calendar.set(Calendar.MONTH,Integer.valueOf(token.nextToken())-1);
                            calendar.set(Calendar.YEAR,Integer.valueOf(token.nextToken()));

                            realm.beginTransaction();
                            RecyclerViewAdapter.mResults.get(position).setEventName(title);
                            RecyclerViewAdapter.mResults.get(position).setStartDate(calendar.getTimeInMillis());
                            RecyclerViewAdapter.mResults.get(position).setStartTime(startT);
                            RecyclerViewAdapter.mResults.get(position).setEndDate(endD);
                            RecyclerViewAdapter.mResults.get(position).setEndTime(endT);
                            RecyclerViewAdapter.mResults.get(position).setNotes(note);
                            RecyclerViewAdapter.mResults.get(position).setAttendees(att);
                            RecyclerViewAdapter.mResults.get(position).setVenue(vne);
                            RecyclerViewAdapter.mResults.get(position).setLocation(loc);
                            realm.commitTransaction();
                            dismiss();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                if(dialogView.getParent() != null){
                    ((ViewGroup)dialogView.getParent()).removeView(dialogView);
                }
                alert.show();
            }
        });


    }

    public void pickContact(View v){
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        Activity activity = getActivity();
        if(isAdded() && activity != null){
            startActivityForResult(contactPickerIntent,1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case 1:
                    contactPicked(data);
                    break;
                case 2:
                    Place place = PlacePicker.getPlace(getContext(),data);
                    CharSequence name = place.getName();
                    LatLng latLng = place.getLatLng();
                    String location = String.valueOf(latLng.latitude).substring(0,10) + "," + String.valueOf(latLng.longitude).substring(0,10);

                    editVenue.setText(name);
                    editLocation.setText(location);
                    break;
            }
        }
    }

    private void contactPicked(Intent data){
        Cursor cursor = null;
        try{
            String name = null;
            Uri uri = data.getData();
            cursor = getActivity().getContentResolver().query(uri,null,null,null,null);
            cursor.moveToFirst();

            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            name = cursor.getString(nameIndex);
            editAttendee.setText(name);
        }catch(Exception e){
            Log.d("AAA","Contact Exception");
        }
    }

}
