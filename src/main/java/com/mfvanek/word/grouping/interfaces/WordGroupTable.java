package com.mfvanek.word.grouping.interfaces;

public interface WordGroupTable {

    WordGroupTable add(final String word);

    boolean contains(final String word);
}
