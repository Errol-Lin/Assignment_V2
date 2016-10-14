package s3534890.com.eventplanner.Controller.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import s3534890.com.eventplanner.Controller.Helpers;
import s3534890.com.eventplanner.Model.Events;
import s3534890.com.eventplanner.R;
import s3534890.com.eventplanner.View.MainActivity;


public class NotificationService extends IntentService {

    private RealmResults<Events> eventList;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // get events
        Realm realm = null;
        try{
            realm = Realm.getDefaultInstance();
            eventList = realm.where(Events.class).findAll();

            for(Events events : eventList){
                if(events.getWhen() > System.currentTimeMillis()){
                    String temp = null;
                    while(temp == null){
                        temp = Helpers.getTimeOfDistanceMatrixJSON(MainActivity.currentLocation,events.getLocation());
                    }
                    if(Helpers.timeToEventInMinutes(System.currentTimeMillis(),events.getWhen()) == Long.valueOf(temp)){
                        Log.d("AAA","Send Notification");

                        // construct the notification
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentTitle("Event: " + events.getEventName());
                        builder.setContentText("Don't forget your event!");
                        builder.setSmallIcon(R.drawable.ic_action_android);
                        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This event is at " + events.getStartTime() + ", the driving time is around " + Helpers.getTimeOfDistanceMatrixJSON(MainActivity.currentLocation,events.getLocation()) + " minutes, don't miss it!"));

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(0,builder.build());
                    }
                }
            }
        }finally {
            realm.close();
        }
    }
}
