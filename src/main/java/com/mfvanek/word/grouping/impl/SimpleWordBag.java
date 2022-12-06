/*
 * Copyright (c) 2019-2021. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.impl;

import com.google.common.collect.TreeMultiset;
import com.mfvanek.word.grouping.interfaces.WordBag;

import java.util.Objects;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Simple implementation of {@link WordBag} using Guava TreeMultiset.
 *
 * @see com.google.common.collect.TreeMultiset
 */
@NotThreadSafe
public class SimpleWordBag implements WordBag {

    private final TreeMultiset<String> bag;

    public SimpleWordBag() {
        bag = TreeMultiset.create(new WordComparator());
    }

    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod") // TODO
    public SimpleWordBag(final String word) {
        this();
        add(word);
    }

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
}
