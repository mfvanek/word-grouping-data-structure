package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordBag;
import com.mfvanek.word.grouping.interfaces.WordGroupingTable;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Simple implementation of {@link WordGroupingTable} using SortedMap
 * Case sensitive
 * Not thread-safe
 */
@NotThreadSafe
public class SimpleWordGroupingTable implements WordGroupingTable {

    private static final String delimiter = StringUtils.SPACE;

    private final SortedMap<Character, WordBag> groups;

    public SimpleWordGroupingTable(final String words) {
        this(split(words));
    }

    private SimpleWordGroupingTable(final String[] words) {
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
        for (final String word : words) {
            addWord(word);
        }
    }

    private void addWord(final String word) {
        Objects.requireNonNull(word);
        if (StringUtils.isNoneBlank(word)) {
            final Character firstLetter = word.charAt(0);
            final WordBag group = groups.get(firstLetter);
            if (group != null) {
                group.add(word);
            } else {
                groups.put(firstLetter, new SimpleWordBag(word));
            }
        }
    }

    @Override
    public WordGroupingTable add(final String word) {
        addWord(word);
        return this;
    }

    @Override
    public boolean contains(final String word) {
        Objects.requireNonNull(word);
        final Character firstLetter = word.charAt(0);
        final WordBag group = groups.get(firstLetter);
        if (group != null) {
            return group.contains(word);
        }
        return false;
    }

    @Override
    public int size() {
        return groups.size();
    }

    @Override
    public void forEach(BiConsumer<Character, WordBag> action) {
        groups.forEach(action);
    }

    @Override
    public Stream<Map.Entry<Character, WordBag>> stream() {
        return groups.entrySet().stream();
    }

    @Override
    public String toString() {
        final StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        groups.forEach((firstLetter, wordsBag) -> stringJoiner.add(String.format("%c=%s", firstLetter, wordsBag)));
        return stringJoiner.toString();
    }
}
