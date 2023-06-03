/*
 * Copyright (c) 2019-2023. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek/word-grouping-data-structure
 *
 * Licensed under the Apache License 2.0
 */

package io.github.mfvanek.word.grouping.impl;

import java.io.Serializable;
import java.util.Comparator;

class WordComparator implements Comparator<String>, Serializable {

    private static final long serialVersionUID = 7157753977559928195L;

    @Override
    public int compare(final String word1, final String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 != null && word2 == null) {
            return 1;
        } else if (word1 == null) {
            return -1;
        }

        int result = Integer.compare(word2.length(), word1.length());
        if (result == 0) {
            result = word1.compareTo(word2);
        }
        return result;
    }
}
