package es.age.apps.expandablelist.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerview.model.ParentListItem;

import java.util.ArrayList;

import es.age.apps.expandablelist.dummy.DummyItem;
import es.age.apps.expandablelist.R;

/**
 * Created by adricacho on 17/9/16.
 */
public class ExpandableRecyclerViewAdapter extends ExpandableRecyclerAdapter<ExpandableRecyclerViewAdapter.DummyParentViewHolder, ExpandableRecyclerViewAdapter.DummyChildViewHolder> {

    private LayoutInflater mInflator;
    private ArrayList<DummyItem> items;

    public ExpandableRecyclerViewAdapter(Context context, @NonNull ArrayList<DummyItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
        items = parentItemList;
    }


    @Override
    public DummyParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup, int viewType) {
        View parentView = mInflator.inflate(R.layout.item_list, parentViewGroup, false);
        return new DummyParentViewHolder(parentView);
    }

    @Override
    public DummyChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View childView = mInflator.inflate(R.layout.sub_item_list, childViewGroup, false);
        return new DummyChildViewHolder(childView);
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(DummyParentViewHolder dummyParentViewHolder, int position, ParentListItem parentListItem) {
        DummyItem item = (DummyItem) parentListItem;
        dummyParentViewHolder.bind(item);
    }

    @Override
    public void onBindChildViewHolder(DummyChildViewHolder childViewHolder, int parentPosition, int childPosition, Object childListItem) {
        String day = (String) childListItem;
        childViewHolder.bind(day, parentPosition, childPosition);
    }


    public class DummyParentViewHolder extends ParentViewHolder {

        private final View colorIndicator;
        private TextView title, subtitle;
        private ImageView expandIndicator;

        public DummyParentViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            colorIndicator = itemView.findViewById(R.id.color_indicator);
            expandIndicator = (ImageView) itemView.findViewById(R.id.expand_indicator);
        }

        public void bind(DummyItem item) {
            title.setText(item.getTitle());
            subtitle.setText(item.getDate());
            colorIndicator.setBackgroundResource(item.getIndicator());
        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            super.onExpansionToggled(expanded);
            if (expanded) {
                expandIndicator.setImageResource(R.drawable.ic_expand_down);
            } else {
                expandIndicator.setImageResource(R.drawable.ic_chevron_right);
            }
        }
    }

    public class DummyChildViewHolder extends ChildViewHolder {

        private final ImageView delete;
        private TextView textView;

        public DummyChildViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            delete = (ImageView) itemView.findViewById(R.id.button_delete);
        }

        public void bind(String day, final int parentPosition, final int childPosition) {
            textView.setText(day);
            delete.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Do you want to remove?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ArrayList<String> child =
                                            items.get(parentPosition).getSubItems();
                                    child.remove(childPosition);
                                    notifyChildItemRemoved(parentPosition, childPosition);
                                }
                            });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
    }
}