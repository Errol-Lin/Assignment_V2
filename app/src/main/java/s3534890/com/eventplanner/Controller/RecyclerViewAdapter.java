package s3534890.com.eventplanner.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

import s3534890.com.eventplanner.R;

/**
 * Created by Errol&BeiBei on 22/08/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ListRowViewHolder> {

    private LayoutInflater mInflater;
    private static ArrayList<String> mItems;

    public RecyclerViewAdapter(Context context){

        // need a inflater to convert xml into java
        mInflater = LayoutInflater.from(context);
        generateValue();
    }

    @Override
    public ListRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // convert the xml into view
        View view = mInflater.inflate(R.layout.list_row,parent,false);

        ListRowViewHolder vh = new ListRowViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListRowViewHolder holder, int position) {
        holder.eventTitle.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    // testing
    public static ArrayList<String> generateValue(){
        mItems = new ArrayList<String>();
        for(int i = 0; i < 101; i++){
            mItems.add("Item " + i);
        }
        return mItems;
    }
}
