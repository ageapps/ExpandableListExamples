package es.age.apps.expandablelist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by adricacho on 16/9/16.
 */
public class DummyItemGenerator {

    public static ArrayList<DummyItem> listItems = new ArrayList<>();
    private static String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private static int startIndex = 1;
    private static int daysInMonth = 30;


    public static void start(int items) {

        for (int i = 0; i < items; i++) {
            DummyItem dummy = new DummyItem();

            dummy.setTitle("Week " + i);


            dummy.setIndicator(i % 3);
            int startDate = (startIndex) < daysInMonth ? startIndex : (startIndex) - daysInMonth;
            int endDate = startDate <= (daysInMonth - weekDays.length) ? startDate + weekDays.length : (startDate + weekDays.length) - daysInMonth;
            startIndex = endDate;
            dummy.setDate(startDate + " - " + endDate);

            ArrayList<String> days = new ArrayList<>();
            Collections.addAll(days, weekDays);

            dummy.setSubItems(days);

            listItems.add(dummy);
        }


    }
}
