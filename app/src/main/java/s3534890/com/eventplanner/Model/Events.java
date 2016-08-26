package s3534890.com.eventplanner.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class Events extends RealmObject{

    @PrimaryKey
    private String id;
    private long added;
    private String eventName;
    private long startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String venue;
    private String location;
    private String notes;
    private String attendees;

    public Events() {}

    public Events(String id, long added, String eventName, long startDate, String attendees, String notes, String endDate, String startTime, String endTime, String venue,String location) {
        this.id = id;
        this.added = added;
        this.eventName = eventName;
        this.startDate = startDate;
        this.attendees = attendees;
        this.notes = notes;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.location = location;
    }

    public long getAdded(){
        return added;
    }

    public void setAdded(long added){
        this.added = added;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }
}
