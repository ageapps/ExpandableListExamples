package es.age.apps.expandablelist.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import es.age.apps.expandablelist.dummy.DummyItemGenerator;
import es.age.apps.expandablelist.R;
import es.age.apps.expandablelist.adapters.ExpandableListViewAdapter;

/**
 * Created by adricacho on 16/9/16.
 */
public class ListViewFragment extends Fragment {


    private View view;
    private ExpandableListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_listview, container, false);


        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getActivity(), DummyItemGenerator.listItems);

        listView = (ExpandableListView) view.findViewById(R.id.expandable_list_view);

        listView.setAdapter(adapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String state = expandableListView.isGroupExpanded(i) ?   " Collapsed" : " Expanded";
                Toast.makeText(getActivity(), DummyItemGenerator.listItems.get(i).getTitle() + state, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }

        return view;
    }

}
