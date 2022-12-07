package com.mfvanek.word.grouping.impl;

import com.mfvanek.word.grouping.interfaces.WordBag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmptyWordBagTest {

    @Test
    void shouldAlwaysReturnFalseOnContains() {
        assertThat(EmptyWordBag.empty())
                .isNotNull()
                .hasToString("[]")
                .satisfies(b -> {
                    assertThat(b.contains(null)).isFalse();
                    assertThat(b.contains("")).isFalse();
                    assertThat(b.contains("   ")).isFalse();
                    assertThat(b.contains("a")).isFalse();
                });
    }

    @Test
    void shouldThrowExceptionOnAdd() {
        final WordBag bag = EmptyWordBag.empty();
        assertThatThrownBy(() -> bag.add("a"))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage(null);
    }
}
