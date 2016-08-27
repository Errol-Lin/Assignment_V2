package s3534890.com.eventplanner.Controller;

import java.util.ArrayList;

/**
 * Created by Errol on 26/08/16.
 */
public class CalendarCollection {
    public String date;
    public String event_message;

    public static ArrayList<CalendarCollection> date_collection_arr;
    public CalendarCollection(String date,String event_message) {

        this.date = date;
        this.event_message = event_message;
    }
}
