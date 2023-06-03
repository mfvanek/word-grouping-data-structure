/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordBag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleWordBagTest {

    @Test
    void empty() {
        final WordBag bag = new SimpleWordBag();
        assertThat(bag)
                .hasToString("[]")
                .satisfies(b -> {
                    assertThat(b.size()).isZero();
                    assertThat(b.contains("")).isFalse();
                    assertThat(b.contains("one")).isFalse();
                });
    }

    @Test
    void constructor() {
        final WordBag bag = SimpleWordBag.of("one");
        assertThat(bag)
                .hasToString("[one]")
                .satisfies(b -> {
                    assertThat(b.size()).isEqualTo(1);
                    assertThat(b.contains("")).isFalse();
                    assertThat(b.contains("one")).isTrue();
                });
    }

    @Test
    void constructorWithNull() {
        assertThatThrownBy(() -> SimpleWordBag.of(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("word cannot be null");
    }

    @Test
    void add() {
        final WordBag bag = new SimpleWordBag()
                .add("one")
                .add("two")
                .add("one more");
        assertThat(bag)
                .hasToString("[one more, one, two]")
                .satisfies(b -> {
                    assertThat(b.size()).isEqualTo(3);
                    assertThat(b.contains("one")).isTrue();
                    assertThat(b.contains("two")).isTrue();
                    assertThat(b.contains("one more")).isTrue();
                    assertThat(b.contains("three")).isFalse();
                });
    }

    @Test
    void addWithNull() {
        final WordBag bag = new SimpleWordBag();
        assertThatThrownBy(() -> bag.add(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("word cannot be null");
    }
}
