package es.age.apps.expandablelist.dummy;


import com.bignerdranch.expandablerecyclerview.model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import es.age.apps.expandablelist.R;

/**
 * Created by adricacho on 16/9/16.
 */
public class DummyItem implements ParentListItem {

    private String title;


    private int indicator;
    private ArrayList<String> subItems;
    private String date;
    private int[] colors = { R.color.colorAccent, R.color.colorPrimary , R.color.colorPrimaryDark};


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getSubItems() {
        return subItems;
    }

    public void setSubItems(ArrayList<String> subItems) {
        this.subItems = subItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIndicator() {

        return colors[indicator];
    }

    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }

    @Override
    public List<String> getChildItemList() {
        return subItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
