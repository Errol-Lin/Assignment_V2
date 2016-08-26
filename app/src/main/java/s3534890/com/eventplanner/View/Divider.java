package s3534890.com.eventplanner.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import s3534890.com.eventplanner.R;

/**
 * Created by Errol on 26/08/16.
 */
public class Divider extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation;

    public Divider(Context context, int orientation) {
        mDivider = context.getDrawable(R.drawable.divider);
        if(orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException();
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawHorizontalDivider(c,parent,state);
        }
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left, top, right, bottom;
        left = 0;
        right = parent.getWidth();
        int count = parent.getChildCount();
        for(int i = 0; i < count; i++){
            View current = parent.getChildAt(i);
            top = current.getTop();
            bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
