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
import java.util.function.Predicate;

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
        this();
        Objects.requireNonNull(words);
        if (words.length == 0) {
            throw new IllegalArgumentException("words");
        }
        groupify(words);
    }

    private SimpleWordGroupingTable() {
        this.groups = new TreeMap<>();
    }

    private static String[] split(final String words) {
        Objects.requireNonNull(words);
        if (StringUtils.isBlank(words)) {
            throw new IllegalArgumentException("words");
        }
        return words.split(delimiter);
    }

    private void groupify(final String[] words) {
        for (final String word : words) {
            addWord(word, false);
        }
    }

    private void addWord(final String word, final boolean throwsOnBlankStrings) {
        Objects.requireNonNull(word);
        if (StringUtils.isNoneBlank(word)) {
            final Character firstLetter = word.charAt(0);
            final WordBag group = groups.get(firstLetter);
            if (group != null) {
                group.add(word);
            } else {
                groups.put(firstLetter, new SimpleWordBag(word));
            }
        } else if (throwsOnBlankStrings) {
            throw new IllegalArgumentException("word");
        }
    }

    @Override
    public WordGroupingTable add(final String word) {
        addWord(word, true);
        return this;
    }

    @Override
    public boolean containsLetter(final char letter) {
        return groups.containsKey(letter);
    }

    @Override
    public boolean containsWord(final String word) {
        Objects.requireNonNull(word);
        if (StringUtils.isBlank(word)) {
            throw new IllegalArgumentException("word");
        }
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
    public WordGroupingTable filter(Predicate<Map.Entry<Character, WordBag>> predicate) {
        final SimpleWordGroupingTable result = new SimpleWordGroupingTable();
        this.groups.entrySet().stream().filter(predicate).forEach(e -> result.groups.put(e.getKey(), e.getValue()));
        return result;
    }

    @Override
    public String toString() {
        final StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        groups.forEach((firstLetter, wordsBag) -> stringJoiner.add(String.format("%c=%s", firstLetter, wordsBag)));
        return stringJoiner.toString();
    }
}
