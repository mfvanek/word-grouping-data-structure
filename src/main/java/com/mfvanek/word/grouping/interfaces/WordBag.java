/*
 * Copyright (c) 2019-2021. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.interfaces;

public interface WordBag {

    WordBag add(final String word);

    boolean contains(final String word);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
