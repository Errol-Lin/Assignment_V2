package s3534890.com.eventplanner.Controller;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import s3534890.com.eventplanner.Model.Events;
import s3534890.com.eventplanner.R;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListRowViewHolder> implements SwipeInterface {

    private DetailViewListener detailListener;
    private LayoutInflater mInflater;
    public static RealmResults<Events> mResults;
    private Realm realm;

    // constructor
    public RecyclerViewAdapter(Context context,Realm realm,RealmResults<Events> results){
        // need a inflater to convert xml into java
        mInflater = LayoutInflater.from(context);
        this.realm = realm;
        update(results);

    }
    public RecyclerViewAdapter(Context context,Realm realm,RealmResults<Events> results,DetailViewListener listener){
        // need a inflater to convert xml into java
        mInflater = LayoutInflater.from(context);
        this.realm = realm;
        detailListener = listener;
        update(results);

    }

    @Override
    public ListRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // convert the xml into view
        View view = mInflater.inflate(R.layout.list_row,parent,false);

        ListRowViewHolder vh = new ListRowViewHolder(view,detailListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListRowViewHolder holder, int position) {
        Events event = mResults.get(position);
        Date date = new Date(event.getStartDate());
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        holder.eventTitle.setText(event.getEventName());
        holder.startDate.setText(df1.format(date));
        holder.startTime.setText("Start Time: " + event.getStartTime());
        holder.attendee.setText("Attendee: " + event.getAttendees());
        CalendarCollection.date_collection_arr.add(new CalendarCollection(df2.format(date),event.getEventName()));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void update(RealmResults<Events> results){
        mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public void onSwipe(int position){
        realm.beginTransaction();
        mResults.get(position).deleteFromRealm();
        realm.commitTransaction();
        notifyItemRemoved(position);
    }

    @Override
    public long getItemId(int position) {
        if(position< mResults.size()){
            return mResults.get(position).getStartDate();
        }
        return RecyclerView.NO_ID;
    }

    public static class ListRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView eventTitle;
        protected TextView startDate;
        protected TextView startTime;
        protected TextView attendee;
        DetailViewListener detailViewListener;

        public ListRowViewHolder(View itemView,DetailViewListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            detailViewListener = listener;
            eventTitle = (TextView) itemView.findViewById(R.id.list_row_event_title);
            startDate = (TextView) itemView.findViewById(R.id.list_row_start_date);
            startTime = (TextView) itemView.findViewById(R.id.list_row_start_time);
            attendee = (TextView) itemView.findViewById(R.id.list_row_attendee);
        }

        @Override
        public void onClick(View view) {
            detailViewListener.onDetailView(getAdapterPosition());
        }
    }
}
