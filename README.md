# word-grouping-data-structure

[![Java CI](https://github.com/mfvanek/word-grouping-data-structure/actions/workflows/tests.yml/badge.svg)](https://github.com/mfvanek/word-grouping-data-structure/actions/workflows/tests.yml)
[![codecov](https://codecov.io/gh/mfvanek/word-grouping-data-structure/branch/master/graph/badge.svg?token=Y1C4H8O8P5)](https://codecov.io/gh/mfvanek/word-grouping-data-structure)

## Task
Есть строка, состоящая из слов. Все слова в ней разделены одним пробелом.  
Нужно преобразовать строку в такую структуру данных, которая группирует слова по первой букве в слове.  
Затем вывести только группы, содержащие более одного элемента.  

Группы должны быть отсортированы в алфавитном порядке.  
Слова внутри группы нужно сортировать по убыванию количества символов; если количество символов равное, то сортировать в алфавитном порядке.  

Пример строки: String s = «сапог сарай арбуз болт бокс биржа»  
Отсортированная строка: [б=[биржа, бокс, болт], c=[caпог, сарай]]

## Solution overview
The resulting data structure is a combination of sorted map and sorted multiset as a map value.
For more information see two main interfaces:
- [WordBag](https://github.com/mfvanek/word-grouping-data-structure/blob/master/src/main/java/com/mfvanek/word/grouping/interfaces/WordBag.java)
- [WordGroupingTable](https://github.com/mfvanek/word-grouping-data-structure/blob/master/src/main/java/com/mfvanek/word/grouping/interfaces/WordGroupingTable.java)

## Technology stack
- Java 8
- Maven
- [Google Guava](https://github.com/google/guava)
- JUnit 5
