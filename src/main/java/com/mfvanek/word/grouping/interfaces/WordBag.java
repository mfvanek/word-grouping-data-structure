package com.mfvanek.word.grouping.interfaces;

public interface WordBag {

    WordBag add(final String word);

    boolean contains(final String word);

    int size();
}
