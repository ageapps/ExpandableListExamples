package es.age.apps.expandablelist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import es.age.apps.expandablelist.adapters.DividerItemDecoration;
import es.age.apps.expandablelist.dummy.DummyItemGenerator;
import es.age.apps.expandablelist.R;
import es.age.apps.expandablelist.adapters.ExpandableRecyclerViewAdapter;

/**
 * Created by adricacho on 17/9/16.
 */
public class RecyclerViewFragment extends Fragment {


    private View view;
    private RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        ExpandableRecyclerViewAdapter adapter = new ExpandableRecyclerViewAdapter(getActivity(), DummyItemGenerator.listItems);

        recycler = (RecyclerView) view.findViewById(R.id.expandable_recycler_view);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycler.setAdapter(adapter);

        recycler.setHasFixedSize(true);

        recycler.addItemDecoration(new DividerItemDecoration(getActivity()));

        adapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                Toast.makeText(getActivity(), DummyItemGenerator.listItems.get(position).getTitle() + " Expanded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                Toast.makeText(getActivity(), DummyItemGenerator.listItems.get(position).getTitle() + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
