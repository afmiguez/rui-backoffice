package pt.ufp.lpi.backofficerui.models;

import java.util.Comparator;

public class WorkComparator implements Comparator<Work> {

    @Override
    public int compare(Work o1, Work o2) {
        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }
}
