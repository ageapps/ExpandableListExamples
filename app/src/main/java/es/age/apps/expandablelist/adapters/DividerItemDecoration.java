package es.age.apps.expandablelist.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import es.age.apps.expandablelist.R;

/**
 * Created by adricacho on 17/9/16.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {


    private Drawable mDivider;

    public DividerItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.item_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }


    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
