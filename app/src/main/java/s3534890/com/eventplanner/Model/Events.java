package s3534890.com.eventplanner.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class Events extends RealmObject{

    @PrimaryKey
    private String id;
    private String eventName;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String venue;
    private String location;
    private String notes;
    private String attendees;


    public Events(String id, String eventName, String startDate, String attendees, String notes, String location, String venue, String endDate, String startTime, String endTime) {
        this.id = id;
        this.eventName = eventName;
        this.startDate = startDate;
        this.attendees = attendees;
        this.notes = notes;
        this.location = location;
        this.venue = venue;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Events() {
    }
}
