package com.mfvanek.word.grouping;

import com.mfvanek.word.grouping.impl.SimpleWordGroupingTable;
import com.mfvanek.word.grouping.interfaces.WordGroupingTable;

public class DemoMain {

    public static void main(String[] args) {
        final WordGroupingTable table = new SimpleWordGroupingTable("сапог сарай арбуз болт бокс биржа");
        System.out.println("Initial collection" + System.lineSeparator() + table);

        final int limit1 = 1;
        System.out.println(System.lineSeparator() + "Filtered by size larger than " + limit1);
        System.out.println(table.filter(e -> e.getValue().size() > limit1));

        table.add("сапог").add("барак").add("автобус").add("астроном");
        System.out.println(System.lineSeparator() + "After adding new words" + System.lineSeparator() + table);

        final int limit3 = 3;
        System.out.println(System.lineSeparator() + "Filtered by size larger than " + limit3);
        System.out.println(table.filter(e -> e.getValue().size() > limit3));
    }
}
