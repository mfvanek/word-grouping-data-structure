/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package io.github.mfvanek.word.grouping;

import io.github.mfvanek.word.grouping.impl.SimpleWordGroupingTable;
import io.github.mfvanek.word.grouping.interfaces.WordGroupingTable;

@SuppressWarnings("PMD.SystemPrintln")
class DemoMain {

    public static void main(final String[] args) {
        final WordGroupingTable table = SimpleWordGroupingTable.fromStringWithDelimiter("сапог сарай арбуз болт бокс биржа");
        System.out.println("Initial collection" + System.lineSeparator() + table);

        final int limit1 = 1;
        System.out.println(System.lineSeparator() + "Filtered by size larger than " + limit1);
        System.out.println(table.filter(e -> e.getValue().size() > limit1));

        final int limit2 = 2;
        System.out.println(System.lineSeparator() + "Filtered by size larger than " + limit2);
        System.out.println(table.filter(e -> e.getValue().size() > limit2));

        table.add("сапог").add("барак").add("автобус").add("астроном").add("яблоко");
        System.out.println(System.lineSeparator() + "After adding new words" + System.lineSeparator() + table);

        final int limit3 = 3;
        System.out.println(System.lineSeparator() + "Filtered by size larger than " + limit3);
        System.out.println(table.filter(e -> e.getValue().size() > limit3));

        System.out.println(System.lineSeparator() + String.format("Filtered by size less than %d or equal", limit1));
        System.out.println(table.filter(e -> e.getValue().size() <= limit1));
    }
}
