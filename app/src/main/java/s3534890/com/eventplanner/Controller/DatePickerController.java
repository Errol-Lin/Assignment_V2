package s3534890.com.eventplanner.Controller;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import s3534890.com.eventplanner.View.MainActivity;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class DatePickerController implements View.OnClickListener {


    @Override
    public void onClick(View view) {
//        Calendar currentDate = Calendar.getInstance();
//        int mYear = currentDate.get(Calendar.YEAR);
//        int mMonth = currentDate.get(Calendar.MONTH);
//        int mDay = currentDate.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePicker = new DatePickerDialog(MainActivity., new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                i1 = i1 + 1;
//                MainActivity.startDate.setText(" " + i2 + "/" + i1 + "/" + i);
//            }
//        },mYear,mMonth,mDay);
//        datePicker.setTitle("Select Date");
//        datePicker.show();

        MainActivity.startDate.setText("01/01/2000");

    }
}
