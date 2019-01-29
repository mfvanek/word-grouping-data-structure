package com.mfvanek.word.grouping.impl;

import com.google.common.collect.TreeMultiset;
import com.mfvanek.word.grouping.interfaces.WordGroupTable;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class SimpleWordGroupTable implements WordGroupTable {

    private static final String delimiter = " ";

    private final SortedMap<Character, TreeMultiset<String>> groups;

    public SimpleWordGroupTable(final String words) {
        this(split(words));
    }

    public SimpleWordGroupTable(final String[] words) {
        Objects.requireNonNull(words);
        if (words.length == 0) {
            throw new IllegalArgumentException("words");
        }
        this.groups = new TreeMap<>();
    }

    private static String[] split(final String words) {
        Objects.requireNonNull(words);
        return words.split(delimiter);
    }

    @Override
    public WordGroupTable add(final String word) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contains(final String word) {
        throw new IllegalArgumentException();
    }
}
