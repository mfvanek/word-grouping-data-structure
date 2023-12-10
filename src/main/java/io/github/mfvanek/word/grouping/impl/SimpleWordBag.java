/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package io.github.mfvanek.word.grouping.impl;

import com.google.common.collect.TreeMultiset;
import io.github.mfvanek.word.grouping.interfaces.WordBag;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Simple implementation of {@link WordBag} using Guava TreeMultiset.
 *
 * @see com.google.common.collect.TreeMultiset
 */
@NotThreadSafe
public final class SimpleWordBag implements WordBag {

    private final TreeMultiset<String> bag;

    public SimpleWordBag() {
        bag = TreeMultiset.create(new WordComparator());
    }

    @Nonnull
    @Override
    public WordBag add(final String word) {
        Objects.requireNonNull(word, "word cannot be null");
        bag.add(word);
        return this;
    }

    @Override
    public boolean contains(final String word) {
        Objects.requireNonNull(word);
        return bag.contains(word);
    }

    @Override
    public int size() {
        return bag.size();
    }

    @Override
    public String toString() {
        return String.format("[%s]", String.join(", ", bag));
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof SimpleWordBag that)) {
            return false;
        }

        return Objects.equals(bag, that.bag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bag);
    }

    @Nonnull
    public static WordBag of(final String word) {
        return new SimpleWordBag()
                .add(word);
    }
}
