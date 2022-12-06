/*
 * Copyright (c) 2019-2021. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.impl;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordComparatorTest {

    private static final Comparator<String> COMPARATOR = new WordComparator();

    @Test
    void compareNulls() {
        assertEquals(0, COMPARATOR.compare(null, null));
        assertEquals(1, COMPARATOR.compare("", null));
        assertEquals(-1, COMPARATOR.compare(null, ""));
    }

    @Test
    void compareEmpty() {
        assertEquals(0, COMPARATOR.compare("", ""));
    }

    @Test
    void compareWithDifferentLength() {
        assertEquals(1, COMPARATOR.compare("long", "short"));
        assertEquals(-1, COMPARATOR.compare("short", "long"));
    }

    @Test
    void compareWithTheSameLength() {
        assertEquals(0, COMPARATOR.compare("one", "one"));
        assertEquals(0, COMPARATOR.compare("two", "two"));
        assertTrue(COMPARATOR.compare("one", "two") < 0);
        assertTrue(COMPARATOR.compare("two", "one") > 0);
    }

    @Test
    void compareWithTheSameLengthButDifferentCase() {
        assertTrue(COMPARATOR.compare("one", "One") > 0);
        assertTrue(COMPARATOR.compare("One", "one") < 0);
    }
}
