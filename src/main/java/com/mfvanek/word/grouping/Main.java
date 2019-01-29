package com.mfvanek.word.grouping;

import com.mfvanek.word.grouping.impl.SimpleWordGroupTable;
import com.mfvanek.word.grouping.interfaces.WordGroupTable;
import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {
        final WordGroupTable table = new SimpleWordGroupTable("сапог сарай арбуз болт бокс биржа");
        System.out.println("All");
        table.forEach((firstLetter, wordsInGroup) -> System.out.println(String.format("%c=[%s]", firstLetter, StringUtils.join(wordsInGroup, ", "))));

        System.out.println("\nFor task");
        table.stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> System.out.println(String.format("%c=[%s]", e.getKey(), StringUtils.join(e.getValue(), ", "))));
    }
}
