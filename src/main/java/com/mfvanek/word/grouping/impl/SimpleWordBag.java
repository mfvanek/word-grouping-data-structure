package com.mfvanek.word.grouping.impl;

import com.google.common.collect.TreeMultiset;
import com.mfvanek.word.grouping.interfaces.WordBag;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

/**
 * Simple implementation of {@link WordBag} using Guava TreeMultiset
 *
 * @see com.google.common.collect.TreeMultiset
 */
@NotThreadSafe
public class SimpleWordBag implements WordBag {

    private final TreeMultiset<String> bag;

    public SimpleWordBag() {
        bag = TreeMultiset.create(new WordComparator());
    }

    public SimpleWordBag(final String word) {
        this();
        add(word);
    }

    @Override
    public WordBag add(final String word) {
        Objects.requireNonNull(word);
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
