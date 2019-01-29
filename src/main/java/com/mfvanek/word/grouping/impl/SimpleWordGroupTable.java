package com.mfvanek.word.grouping.impl;

import com.google.common.collect.TreeMultiset;
import com.mfvanek.word.grouping.interfaces.WordGroupTable;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Case sensitive
 * Non thread-safe
 */
public class SimpleWordGroupTable implements WordGroupTable {

    private static final String delimiter = StringUtils.SPACE;

    private final SortedMap<Character, TreeMultiset<String>> groups;

    public SimpleWordGroupTable(final String words) {
        this(split(words));
    }

    private SimpleWordGroupTable(final String[] words) {
        Objects.requireNonNull(words);
        if (words.length == 0) {
            throw new IllegalArgumentException("words");
        }
        this.groups = new TreeMap<>();
        groupify(words);
    }

    private static String[] split(final String words) {
        Objects.requireNonNull(words);
        return words.split(delimiter);
    }

    private void groupify(final String[] words) {
        final Comparator<String> comparator = new WordComparator();
        for (final String word : words) {
            if (StringUtils.isNoneBlank(word)) {
                final Character firstLetter = word.charAt(0);
                TreeMultiset<String> group = groups.get(firstLetter);
                if (group != null) {
                    group.add(word);
                } else {
                    group = TreeMultiset.create(comparator);
                    group.add(word);
                    groups.put(firstLetter, group);
                }
            }
        }
    }

    @Override
    public WordGroupTable add(final String word) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contains(final String word) {
        throw new IllegalArgumentException();
    }

    @Override
    public int size() {
        return groups.size();
    }

    @Override
    public void forEach(BiConsumer<Character, TreeMultiset<String>> action) {
        groups.forEach(action);
    }

    @Override
    public Stream<Map.Entry<Character, TreeMultiset<String>>> stream() {
        return groups.entrySet().stream();
    }
}
