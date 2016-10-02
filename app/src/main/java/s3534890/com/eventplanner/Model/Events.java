package s3534890.com.eventplanner.Model;

import java.util.ArrayList;

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
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String venue;
    private String location;
    private String notes;
    private String attendees;
    private long when;
    public static ArrayList<Events> events_collection;

    public Events() {}

    public Events(String id, long added, long when, String eventName, String startDate, String startTime, String endDate, String
            endTime, String venue, String location, String attendees,String notes) {
        this.id = id;
        this.added = added;
        this.when = when;
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

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
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
