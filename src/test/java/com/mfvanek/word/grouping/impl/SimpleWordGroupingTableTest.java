/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordGroupingTable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleWordGroupingTableTest {

    @Test
    void constructor() {
        assertThat(SimpleWordGroupingTable.fromStringWithDelimiter("a"))
                .isNotNull()
                .hasToString("[a=[a]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(1);
                    assertThat(t.containsLetter('a')).isTrue();
                    assertThat(t.containsWord("a")).isTrue();
                    assertThat(t.containsWord("b")).isFalse();
                });

        assertThat(SimpleWordGroupingTable.fromWords("a"))
                .isNotNull()
                .hasToString("[a=[a]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(1);
                    assertThat(t.containsLetter('a')).isTrue();
                    assertThat(t.containsWord("a")).isTrue();
                    assertThat(t.containsWord("b")).isFalse();
                });
    }

    @Test
    void constructorWithNull() {
        assertThatThrownBy(() -> SimpleWordGroupingTable.fromStringWithDelimiter(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("words cannot be null");

        assertThatThrownBy(() -> SimpleWordGroupingTable.fromWords((String) null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("word cannot be null");
    }

    @Test
    void constructorWithEmpty() {
        assertThatThrownBy(() -> SimpleWordGroupingTable.fromStringWithDelimiter(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("words cannot be empty");
        assertThatThrownBy(() -> SimpleWordGroupingTable.fromStringWithDelimiter("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("words cannot be empty");

        assertThat(SimpleWordGroupingTable.fromWords(""))
                .isNotNull()
                .hasToString("[]")
                .satisfies(t -> assertThat(t.size()).isZero());
        assertThat(SimpleWordGroupingTable.fromWords("   "))
                .isNotNull()
                .hasToString("[]")
                .satisfies(t -> assertThat(t.size()).isZero());

        final String[] empty = {};
        assertThatThrownBy(() -> SimpleWordGroupingTable.fromWords(empty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("words cannot be empty");
    }

    @Test
    void constructorWithMultipleSpaces() {
        assertThat(SimpleWordGroupingTable.fromStringWithDelimiter("c  a  b"))
                .isNotNull()
                .hasToString("[a=[a], b=[b], c=[c]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(3);
                    assertThat(t.containsWord("a")).isTrue();
                    assertThat(t.containsWord("b")).isTrue();
                    assertThat(t.containsWord("c")).isTrue();
                    assertThat(t.containsLetter('a')).isTrue();
                    assertThat(t.containsLetter('b')).isTrue();
                    assertThat(t.containsLetter('c')).isTrue();
                });

        assertThat(SimpleWordGroupingTable.fromWords("c  a  b"))
                .isNotNull()
                .hasToString("[c=[c  a  b]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(1);
                    assertThat(t.containsWord("a")).isFalse();
                    assertThat(t.containsWord("b")).isFalse();
                    assertThat(t.containsWord("c")).isFalse();
                    assertThat(t.containsLetter('a')).isFalse();
                    assertThat(t.containsLetter('b')).isFalse();
                    assertThat(t.containsLetter('c')).isTrue();
                });
    }

    @Test
    void addTheSameWord() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");
        assertThat(table.size()).isEqualTo(1);

        table.add("a").add("a");

        assertThat(table)
                .hasToString("[a=[a, a, a]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(1);
                    assertThat(t.containsWord("a")).isTrue();
                    assertThat(t.containsLetter('b')).isFalse();
                });
    }

    @Test
    void add() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");
        assertThat(table.size()).isEqualTo(1);

        table.add("apple").add("banana");

        assertThat(table)
                .hasToString("[a=[apple, a], b=[banana]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(2);
                    assertThat(table.containsWord("a")).isTrue();
                    assertThat(table.containsWord("apple")).isTrue();
                    assertThat(table.containsWord("banana")).isTrue();
                    assertThat(table.containsWord("b")).isFalse();
                });
    }

    @Test
    void addWithNull() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");

        assertThatThrownBy(() -> table.add(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("word cannot be null");
    }

    @Test
    void addWithEmpty() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");

        assertThatThrownBy(() -> table.add(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("word cannot be empty");
        assertThatThrownBy(() -> table.add("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("word cannot be empty");
    }

    @Test
    void addWithSeveralWordsInOneString() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");
        table.add("a b c");
        assertThat(table)
                .hasToString("[a=[a b c, a]]")
                .satisfies(t -> {
                    assertThat(t.size()).isEqualTo(1);
                    assertThat(t.containsWord("a")).isTrue();
                    assertThat(t.containsWord("a b c")).isTrue();
                    assertThat(t.containsWord("b")).isFalse();
                    assertThat(t.containsWord("c")).isFalse();
                    assertThat(t.containsLetter('a')).isTrue();
                    assertThat(t.containsLetter('b')).isFalse();
                });
    }

    @Test
    void containsWordWithNull() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");

        assertThatThrownBy(() -> table.containsWord(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("word cannot be null");
    }

    @Test
    void containsWordWithEmpty() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromWords("a");

        assertThatThrownBy(() -> table.containsWord(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("word cannot be empty");
    }

    @Test
    void getWordsByLetter() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromStringWithDelimiter("apple banana orange");
        assertThat(table.size()).isEqualTo(3);

        assertThat(table.getWordsByLetter('a'))
                .isNotNull()
                .hasToString("[apple]")
                .satisfies(b -> {
                    assertThat(b.size()).isEqualTo(1);
                    assertThat(b.isEmpty()).isFalse();
                });

        assertThat(table.getWordsByLetter('b'))
                .isNotNull()
                .hasToString("[banana]")
                .satisfies(b -> {
                    assertThat(b.size()).isEqualTo(1);
                    assertThat(b.isEmpty()).isFalse();
                });

        assertThat(table.getWordsByLetter('c'))
                .isNotNull()
                .hasToString("[]")
                .satisfies(b -> {
                    assertThat(b.size()).isZero();
                    assertThat(b.isEmpty()).isTrue();
                });
    }

    @Test
    void filterShouldWork() {
        final WordGroupingTable table = SimpleWordGroupingTable.fromStringWithDelimiter("apple banana orange");
        assertThat(table.size()).isEqualTo(3);

        assertThat(table.filter(e -> e.getKey() == 'o'))
                .isNotNull()
                .hasToString("[o=[orange]]")
                .doesNotHaveSameHashCodeAs(table)
                .isNotEqualTo(table)
                .satisfies(t -> assertThat(t.size()).isEqualTo(1));

        assertThat(table.filter(e -> e.getKey() == 'd'))
                .isNotNull()
                .hasToString("[]")
                .doesNotHaveSameHashCodeAs(table)
                .isNotEqualTo(table)
                .satisfies(t -> assertThat(t.size()).isZero());
    }
}
