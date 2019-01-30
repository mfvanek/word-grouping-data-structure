package com.mfvanek.word.grouping;

import com.mfvanek.word.grouping.impl.SimpleWordGroupingTable;
import com.mfvanek.word.grouping.interfaces.WordGroupingTable;

public class DemoMain {

    public static void main(String[] args) {
        final WordGroupingTable table = new SimpleWordGroupingTable("сапог сарай арбуз болт бокс биржа");
        System.out.println("All elements" + System.lineSeparator() + table);

        System.out.println(System.lineSeparator() + "Filtered");
        table.stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> System.out.println(String.format("%c=%s", e.getKey(), e.getValue())));

        table.add("сапог").add("барак").add("автобус").add("астроном");
        System.out.println(System.lineSeparator() + "After adding new words" + System.lineSeparator() + table);
    }
}
