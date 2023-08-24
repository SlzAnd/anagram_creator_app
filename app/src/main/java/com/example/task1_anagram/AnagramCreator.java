package com.example.task1_anagram;

import java.util.HashMap;
import java.util.Map;

public class AnagramCreator {

    public String createAnagram(String text, String filter) {
        String[] words = text.split(" ");
        StringBuilder anagram = new StringBuilder();
        String filterRegex = "[^a-zA-z]"; // matches digits and special symbols

        for(String word: words) {
            char[] chars = word.toCharArray();
            StringBuilder reversedWord = new StringBuilder();
            Map<Integer, Character> filteredChars = new HashMap<>();

            for (int i = 0; i < chars.length; i++) {
                if (filter == null ? String.valueOf(chars[i]).matches(filterRegex)
                : filter.indexOf(chars[i]) != -1) {
                    filteredChars.put(i, chars[i]);
                } else {
                    reversedWord.append(chars[i]);
                }
            }
            reversedWord.reverse();
            filteredChars.keySet().forEach(index -> {
                Character c = filteredChars.get(index);
                if (c != null) {
                    reversedWord.insert(index.intValue(), c);
                }
            });
            anagram.append(reversedWord).append(" ");
        }

        return anagram.toString().trim();
    }
}
