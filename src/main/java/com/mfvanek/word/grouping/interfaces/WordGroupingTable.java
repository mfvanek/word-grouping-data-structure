/*
 * Copyright (c) 2019-2021. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.interfaces;

import java.util.Map;
import java.util.function.Predicate;

public interface WordGroupingTable {

    /**
     * Adds given word to the table and returns reference to itself for supporting methods chaining.
     *
     * @param word The word to add
     * @return The same object (this)
     */
    WordGroupingTable add(String word);

    boolean containsLetter(char letter);

    boolean containsWord(String word);

    WordBag getWordsByLetter(char letter);

    int size();

    /**
     * Filters the table with given {@code predicate} and returns a new instance of the {@link WordGroupingTable}.
     *
     * @param predicate Predicate for filtering
     * @return New instance of the {@link WordGroupingTable}
     */
    WordGroupingTable filter(Predicate<Map.Entry<Character, WordBag>> predicate);
}
