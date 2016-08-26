package s3534890.com.eventplanner.Controller;

import java.util.ArrayList;

/**
 * Created by Errol on 26/08/16.
 */
public class CalendarCollection {
    public String date="1";
    public String event_message="11";

    public static ArrayList<CalendarCollection> date_collection_arr = new ArrayList<>(1);
    public CalendarCollection(String date,String event_message) {

        this.date = date;
        this.event_message = event_message;
    }
}
