package s3534890.com.eventplanner.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import s3534890.com.eventplanner.R;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class ListRowViewHolder extends RecyclerView.ViewHolder{


    protected TextView eventTitle;
    protected TextView startDate;

    public ListRowViewHolder(View itemView) {

        super(itemView);
        eventTitle = (TextView) itemView.findViewById(R.id.event_title_text);
    }
}
