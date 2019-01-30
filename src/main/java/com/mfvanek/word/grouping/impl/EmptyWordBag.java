/*
 * Copyright (c) 2019. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordBag;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class EmptyWordBag implements WordBag {

    private EmptyWordBag() {
    }

    @Override
    public final WordBag add(final String word) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean contains(final String word) {
        return false;
    }

    @Override
    public final int size() {
        return 0;
    }

    @Override
    public final String toString() {
        return "[]";
    }

    static WordBag makeEmpty() {
        return new EmptyWordBag();
    }
}
