/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordBag;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class EmptyWordBag implements WordBag {

    private EmptyWordBag() {
    }

    @Override
    public WordBag add(final String word) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(final String word) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return "[]";
    }

    @Nonnull
    static WordBag empty() {
        return new EmptyWordBag();
    }
}
