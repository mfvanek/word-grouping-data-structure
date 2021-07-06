/*
 * Copyright (c) 2019-2021. Ivan Vakhrushev. All rights reserved.
 * https://github.com/mfvanek
 */

package com.mfvanek.word.grouping.impl;

import java.util.Comparator;

class WordComparator implements Comparator<String> {

    @Override
    public int compare(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 != null && word2 == null) {
            return 1;
        } else if (word1 == null) {
            return -1;
        }

        int result = -Integer.compare(word1.length(), word2.length());
        if (result == 0) {
            result = word1.compareTo(word2);
        }
        return result;
    }
}
