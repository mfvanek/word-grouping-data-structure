package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordBag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleWordBagTest {

    @Test
    void empty() {
        final WordBag bag = new SimpleWordBag();
        assertNotNull(bag);
        assertEquals(0, bag.size());
        assertEquals("[]", bag.toString());
        assertFalse(bag.contains(""));
        assertFalse(bag.contains("one"));
    }

    @Test
    void constructor() {
        final WordBag bag = new SimpleWordBag("one");
        assertNotNull(bag);
        assertEquals(1, bag.size());
        assertEquals("[one]", bag.toString());
        assertFalse(bag.contains(""));
        assertTrue(bag.contains("one"));
    }

    @Test
    void constructorWithNull() {
        assertThrows(NullPointerException.class, () -> new SimpleWordBag((null)));
    }

    @Test
    void add() {
        final WordBag bag = new SimpleWordBag();
        bag.add("one").add("two").add("one more");
        assertEquals(3, bag.size());
        assertEquals("[one more, one, two]", bag.toString());
        assertTrue(bag.contains("one"));
        assertTrue(bag.contains("two"));
        assertTrue(bag.contains("one more"));
        assertFalse(bag.contains("three"));
    }

    @Test
    void addWithNull() {
        final WordBag bag = new SimpleWordBag();
        assertThrows(NullPointerException.class, () -> bag.add(null));
    }
}
