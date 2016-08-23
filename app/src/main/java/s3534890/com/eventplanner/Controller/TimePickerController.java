package s3534890.com.eventplanner.Controller;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import s3534890.com.eventplanner.R;
import s3534890.com.eventplanner.View.MainActivity;

/**
 * Created by Errol&BeiBei on 23/08/2016.
 */
public class TimePickerController implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        // to get the current time
        Calendar currentTime = Calendar.getInstance();
        int mHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int mMinute = currentTime.get(Calendar.MINUTE);

        switch (view.getId()){
            case R.id.dialog_view_start_time:
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        MainActivity.startTime.setText(i + ":" + i1);
                    }
                },mHour,mMinute,false);
                timePickerDialog1.setTitle("Select Time");
                timePickerDialog1.show();
                break;
            case R.id.dialog_view_end_time:
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        MainActivity.endTime.setText(i + ":" + i1);
                    }
                },mHour,mMinute,false);
                timePickerDialog2.setTitle("Select Time");
                timePickerDialog2.show();
        }
    }
}
