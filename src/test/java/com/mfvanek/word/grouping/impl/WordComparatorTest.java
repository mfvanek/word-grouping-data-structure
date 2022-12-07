/*
 * Copyright (c) 2019-2021. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.impl;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("EqualsWithItself")
class WordComparatorTest {

    private static final Comparator<String> COMPARATOR = new WordComparator();

    @Test
    void compareNulls() {
        assertThat(COMPARATOR.compare(null, null))
                .isZero();
        assertThat(COMPARATOR.compare("", null))
                .isPositive();
        assertThat(COMPARATOR.compare(null, ""))
                .isNegative();
    }

    @Test
    void compareEmpty() {
        assertThat(COMPARATOR.compare("", ""))
                .isZero();
    }

    @Test
    void compareWithDifferentLength() {
        assertThat(COMPARATOR.compare("long", "short"))
                .isPositive();
        assertThat(COMPARATOR.compare("short", "long"))
                .isNegative();
    }

    @Test
    void compareWithTheSameLength() {
        assertThat(COMPARATOR.compare("one", "one"))
                .isZero();
        assertThat(COMPARATOR.compare("two", "two"))
                .isZero();
        assertThat(COMPARATOR.compare("one", "two"))
                .isNegative();
        assertThat(COMPARATOR.compare("two", "one"))
                .isPositive();
    }

    @Test
    void compareWithTheSameLengthButDifferentCase() {
        assertThat(COMPARATOR.compare("one", "One"))
                .isPositive();
        assertThat(COMPARATOR.compare("One", "one"))
                .isNegative();
    }
}
