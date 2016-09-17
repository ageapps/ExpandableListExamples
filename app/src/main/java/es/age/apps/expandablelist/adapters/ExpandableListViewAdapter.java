package es.age.apps.expandablelist.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.age.apps.expandablelist.dummy.DummyItem;
import es.age.apps.expandablelist.R;

/**
 * Created by adricacho on 16/9/16.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {


    private Activity context;
    private ArrayList<DummyItem> groupItems;

    public ExpandableListViewAdapter(Activity activity, ArrayList<DummyItem> groupItems) {
        this.context = activity;
        this.groupItems = groupItems;
    }

    @Override
    public int getGroupCount() {
        return groupItems.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groupItems.get(i).getSubItems().size();
    }

    @Override
    public Object getGroup(int i) {
        groupItems.get(i);
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        groupItems.get(i).getSubItems().get(i1);
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_list, viewGroup, false);
        }
        TextView item = (TextView) view.findViewById(R.id.title);
        TextView subitem = (TextView) view.findViewById(R.id.subtitle);
        View colorIndicator = view.findViewById(R.id.color_indicator);

        item.setText(groupItems.get(i).getTitle());
        subitem.setText(groupItems.get(i).getDate());
        colorIndicator.setBackgroundResource(groupItems.get(i).getIndicator());

        ImageView expandIndicator = (ImageView) view.findViewById(R.id.expand_indicator);
        if (b){
            expandIndicator.setImageResource(R.drawable.ic_chevron_right);
        } else {
            expandIndicator.setImageResource(R.drawable.ic_expand_down);
        }
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.sub_item_list, null);
        }

        TextView item = (TextView) view.findViewById(R.id.title);

        ImageView delete = (ImageView) view.findViewById(R.id.button_delete);
        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ArrayList<String> child =
                                        groupItems.get(i).getSubItems();
                                child.remove(i1);
                                notifyDataSetChanged();
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

        item.setText(groupItems.get(i).getSubItems().get(i1));
        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
