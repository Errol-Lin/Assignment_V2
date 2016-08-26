package s3534890.com.eventplanner.Controller;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Errol on 25/08/16.
 */
public class SimpleTouchCallBack extends ItemTouchHelper.Callback {

    private SwipeInterface swipeInterface;

    public SimpleTouchCallBack(SwipeInterface sInterface) {
        swipeInterface = sInterface;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // drag disabled
        return makeMovementFlags(0,ItemTouchHelper.END);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        swipeInterface.onSwipe(viewHolder.getAdapterPosition());
    }
}
