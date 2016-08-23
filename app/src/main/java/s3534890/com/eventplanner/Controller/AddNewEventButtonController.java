package s3534890.com.eventplanner.Controller;

import android.content.DialogInterface;
import android.view.View;
import java.util.UUID;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import s3534890.com.eventplanner.Model.Events;
import s3534890.com.eventplanner.View.MainActivity;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class AddNewEventButtonController implements DialogInterface.OnClickListener {

    private String eventName = MainActivity.eventTitle.toString();
    private String startDate = MainActivity.startDate.toString();
    private String startTime = MainActivity.startTime.toString();
    private String endDate = MainActivity.endDate.toString();
    private String endTime = MainActivity.endTime.toString();
    private String venue = MainActivity.venue.toString();
    private String location = MainActivity.location.toString();
    private String notes = MainActivity.eventNote.toString();
    private String attendees = MainActivity.attendees.toString();

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
//        RealmConfiguration config = new RealmConfiguration.Builder().build();
//        Realm realm = Realm.getDefaultInstance();
//        Events newEvent = new Events(UUID.randomUUID().toString().replaceAll("-",""),eventName,startDate,startTime,endDate,endTime,venue,location,notes,attendees);
    }
}
