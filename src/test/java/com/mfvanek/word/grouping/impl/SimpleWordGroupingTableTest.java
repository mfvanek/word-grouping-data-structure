package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordGroupingTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleWordGroupingTableTest {

    @Test
    void constructor() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertNotNull(table);
        assertEquals(1, table.size());
        assertEquals("[a=[a]]", table.toString());
        assertTrue(table.containsLetter('a'));
        assertTrue(table.containsWord("a"));
        assertFalse(table.containsWord("b"));
    }

    @Test
    void constructorWithNull() {
        assertThrows(NullPointerException.class, () -> new SimpleWordGroupingTable(null));
    }

    @Test
    void constructorWithEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleWordGroupingTable(""));
    }

    @Test
    void constructorWithMultipleSpaces() {
        final WordGroupingTable table = new SimpleWordGroupingTable("c  a  b");
        assertNotNull(table);
        assertEquals(3, table.size());
        assertEquals("[a=[a], b=[b], c=[c]]", table.toString());
        assertTrue(table.containsWord("a"));
        assertTrue(table.containsWord("b"));
        assertTrue(table.containsWord("c"));
        assertTrue(table.containsLetter('a'));
        assertTrue(table.containsLetter('b'));
        assertTrue(table.containsLetter('c'));
    }

    @Test
    void addTheSameWord() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        table.add("a").add("a");
        assertEquals(1, table.size());
        assertEquals("[a=[a, a, a]]", table.toString());
        assertTrue(table.containsWord("a"));
        assertFalse(table.containsLetter('b'));
    }

    @Test
    void add() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        table.add("apple").add("banana");
        assertEquals(2, table.size());
        assertEquals("[a=[apple, a], b=[banana]]", table.toString());
        assertTrue(table.containsWord("a"));
        assertTrue(table.containsWord("apple"));
        assertTrue(table.containsWord("banana"));
        assertFalse(table.containsWord("b"));
    }

    @Test
    void addWithNull() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        assertThrows(NullPointerException.class, () -> table.add(null));
    }

    @Test
    void addWithEmpty() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        assertThrows(IllegalArgumentException.class, () -> table.add(""));
    }

    @Test
    void addWithSeveralWordsInOneString() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        table.add("a b c");
        assertEquals(1, table.size());
        assertEquals("[a=[a b c, a]]", table.toString());
        assertTrue(table.containsWord("a"));
        assertTrue(table.containsWord("a b c"));
        assertFalse(table.containsWord("b"));
        assertFalse(table.containsWord("c"));
        assertTrue(table.containsLetter('a'));
        assertFalse(table.containsLetter('b'));
    }

    @Test
    void containsWordWithNull() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        assertThrows(NullPointerException.class, () -> table.containsWord(null));
    }

    @Test
    void containsWordWithEmpty() {
        final WordGroupingTable table = new SimpleWordGroupingTable("a");
        assertEquals(1, table.size());

        assertThrows(IllegalArgumentException.class, () -> table.containsWord(""));
    }
}
