package s3534890.com.eventplanner.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import s3534890.com.eventplanner.Controller.RecyclerViewAdapter;
import s3534890.com.eventplanner.R;

/**
 * Created by Errol on 25/08/16.
 */
public class DetailViewDialog extends DialogFragment {

    private Button doneButton;
    private TextView title;
    private TextView startDate;
    private TextView startTime;
    private TextView endDate;
    private TextView endTime;
    private TextView venue;
    private TextView location;
    private TextView note;
    private TextView attendee;
    private int position;

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                dismiss();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_detail_dialog,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doneButton = (Button) view.findViewById(R.id.view_detail_done_button);
        title = (TextView) view.findViewById(R.id.view_detail_title);
        startDate = (TextView) view.findViewById(R.id.view_detail_start_date);
        startTime= (TextView) view.findViewById(R.id.view_detail_start_time);
        endDate= (TextView) view.findViewById(R.id.view_detail_end_date);
        endTime= (TextView) view.findViewById(R.id.view_detail_end_time);
        venue= (TextView) view.findViewById(R.id.view_detail_venue);
        location= (TextView) view.findViewById(R.id.view_detail_location);
        note= (TextView) view.findViewById(R.id.view_detail_note);
        attendee= (TextView) view.findViewById(R.id.view_detail_attendee);

        Bundle arguments = getArguments();
        if(arguments != null){
            position = arguments.getInt("POSITION");
        }

        Date date = new Date(RecyclerViewAdapter.mResults.get(position).getStartDate());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        title.setText("Title: " + RecyclerViewAdapter.mResults.get(position).getEventName().toString());
        startDate.setText("Start Date: " + df.format(date));
        startTime.setText("Start Time: " + RecyclerViewAdapter.mResults.get(position).getStartTime().toString());
        endDate.setText("End Date: " + RecyclerViewAdapter.mResults.get(position).getEndDate().toString());
        endTime.setText("End Time: " + RecyclerViewAdapter.mResults.get(position).getEndTime().toString());
        venue.setText("Venue: " + RecyclerViewAdapter.mResults.get(position).getVenue().toString());
        location.setText("Location: " + RecyclerViewAdapter.mResults.get(position).getLocation().toString());
        note.setText("Notes: " + RecyclerViewAdapter.mResults.get(position).getNotes().toString());
        attendee.setText("Attendees: " + RecyclerViewAdapter.mResults.get(position).getAttendees().toString());

        doneButton.setOnClickListener(buttonClickListener);
    }
}
