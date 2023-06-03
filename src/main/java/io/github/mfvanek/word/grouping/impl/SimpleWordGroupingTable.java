/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package io.github.mfvanek.word.grouping.impl;

import io.github.mfvanek.word.grouping.interfaces.WordBag;
import io.github.mfvanek.word.grouping.interfaces.WordGroupingTable;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Simple implementation of {@link WordGroupingTable} using SortedMap.
 * Case-sensitive
 * Not thread-safe
 */
@NotThreadSafe
public final class SimpleWordGroupingTable implements WordGroupingTable {

    private static final String DELIMITER = StringUtils.SPACE;

    private final SortedMap<Character, WordBag> groups;

    private SimpleWordGroupingTable() {
        this.groups = new TreeMap<>();
    }

    @Nonnull
    private SimpleWordGroupingTable groupify(final String... words) {
        Objects.requireNonNull(words, "words cannot be null");
        if (words.length == 0) {
            throw new IllegalArgumentException("words cannot be empty");
        }
        for (final String word : words) {
            addWord(word, false);
        }
        return this;
    }

    private void addWord(final String word, final boolean throwsOnBlankStrings) {
        validateWord(word, throwsOnBlankStrings);
        if (StringUtils.isNoneBlank(word)) {
            final Character firstLetter = word.charAt(0);
            final WordBag group = groups.get(firstLetter);
            if (group != null) {
                group.add(word);
            } else {
                groups.put(firstLetter, SimpleWordBag.of(word));
            }
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
        validateWord(word);
        final Character firstLetter = word.charAt(0);
        final WordBag group = groups.get(firstLetter);
        if (group != null) {
            return group.contains(word);
        }
        return false;
    }

    @Override
    public WordBag getWordsByLetter(final char letter) {
        final WordBag group = groups.get(letter);
        if (group != null) {
            return group;
        }
        return EmptyWordBag.empty();
    }

    @Override
    public int size() {
        return groups.size();
    }

    @Override
    public WordGroupingTable filter(final Predicate<Map.Entry<Character, WordBag>> predicate) {
        final SimpleWordGroupingTable result = new SimpleWordGroupingTable();
        this.groups.entrySet()
                .stream()
                .filter(predicate)
                .forEach(e -> result.groups.put(e.getKey(), e.getValue()));
        return result;
    }

    @Override
    public String toString() {
        final StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        groups.forEach((firstLetter, wordsBag) -> stringJoiner.add(String.format("%c=%s", firstLetter, wordsBag)));
        return stringJoiner.toString();
    }

    private static void validateWord(final String word) {
        validateWord(word, true);
    }

    private static void validateWord(final String word, final boolean throwsOnBlankStrings) {
        Objects.requireNonNull(word, "word cannot be null");
        if (StringUtils.isBlank(word) && throwsOnBlankStrings) {
            throw new IllegalArgumentException("word cannot be empty");
        }
    }

    private static String[] split(final String words) {
        Objects.requireNonNull(words, "words cannot be null");
        if (StringUtils.isBlank(words)) {
            throw new IllegalArgumentException("words cannot be empty");
        }
        return words.split(DELIMITER);
    }

    public static WordGroupingTable fromStringWithDelimiter(final String words) {
        return new SimpleWordGroupingTable()
                .groupify(split(words));
    }

    public static WordGroupingTable fromWords(final String... words) {
        return new SimpleWordGroupingTable()
                .groupify(words);
    }
}
