package com.mfvanek.word.grouping.impl;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordComparatorTest {

    private static final Comparator<String> comparator = new WordComparator();

    @Test
    void compareNulls() {
        assertEquals(0, comparator.compare(null, null));
        assertEquals(1, comparator.compare("", null));
        assertEquals(-1, comparator.compare(null, ""));
    }

    @Test
    void compareEmpty() {
        assertEquals(0, comparator.compare("", ""));
    }

    @Test
    void compareWithDifferentLength() {
        assertEquals(1, comparator.compare("long", "short"));
        assertEquals(-1, comparator.compare("short", "long"));
    }

    @Test
    void compareWithSameLength() {
        assertEquals(0, comparator.compare("one", "one"));
        assertEquals(0, comparator.compare("two", "two"));
        assertTrue(comparator.compare("one", "two") < 0);
        assertTrue(comparator.compare("two", "one") > 0);
    }

    @Test
    void compareWithSameLengthButDifferentCase() {
        assertTrue(comparator.compare("one", "One") > 0);
        assertTrue(comparator.compare("One", "one") < 0);
    }
}
