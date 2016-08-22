package s3534890.com.eventplanner.Model;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class Events {

    private String eventName;
    private String startDate;
    private String endDate;
    private String eventTime;
    private String id;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
