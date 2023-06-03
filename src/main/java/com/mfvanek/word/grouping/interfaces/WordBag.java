/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package com.mfvanek.word.grouping.interfaces;

public interface WordBag {

    WordBag add(String word);

    boolean contains(String word);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
