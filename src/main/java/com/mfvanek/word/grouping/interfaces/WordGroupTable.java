package com.mfvanek.word.grouping.interfaces;

import com.google.common.collect.TreeMultiset;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public interface WordGroupTable {

    WordGroupTable add(final String word);

    boolean contains(final String word);

    int size();

    void forEach(BiConsumer<Character, TreeMultiset<String>> action);

    Stream<Map.Entry<Character, TreeMultiset<String>>> stream();
}
