package s3534890.com.eventplanner.Controller;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import s3534890.com.eventplanner.R;
import s3534890.com.eventplanner.View.MainActivity;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class DatePickerController implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        // to get the current date
        Calendar currentDate = Calendar.getInstance();
        int mYear = currentDate.get(Calendar.YEAR);
        int mMonth = currentDate.get(Calendar.MONTH);
        int mDay = currentDate.get(Calendar.DAY_OF_MONTH);

        switch (view.getId()){
            case R.id.dialog_view_start_date:
                DatePickerDialog datePicker1 = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        MainActivity.startDate.setText(" " + i2 + "/" + i1 + "/" + i);
                    }
                },mYear,mMonth,mDay);
                datePicker1.setTitle("Select Date");
                datePicker1.show();
                break;
            case R.id.dialog_view_end_date:
                DatePickerDialog datePicker2 = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        MainActivity.endDate.setText(" " + i2 + "/" + i1 + "/" + i);
                    }
                },mYear,mMonth,mDay);
                datePicker2.setTitle("Select Date");
                datePicker2.show();
        }
    }
}
