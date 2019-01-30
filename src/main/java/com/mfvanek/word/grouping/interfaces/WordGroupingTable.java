package com.mfvanek.word.grouping.interfaces;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public interface WordGroupingTable {

    WordGroupingTable add(final String word);

    boolean contains(final String word);

    int size();

    void forEach(BiConsumer<Character, WordBag> action);

    Stream<Map.Entry<Character, WordBag>> stream();
}
