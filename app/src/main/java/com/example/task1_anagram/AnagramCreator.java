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
                char currentChar = chars[i];
                if (filter.isBlank()
                        ? String.valueOf(currentChar).matches(filterRegex) // check if char matches regex
                        : filter.indexOf(currentChar) != -1 // check if char is in filters
                ) {
                    filteredChars.put(i, currentChar);
                } else {
                    reversedWord.append(currentChar);
                }
            }
            reversedWord.reverse();
            filteredChars.keySet().forEach(index -> {
                Character filteredChar = filteredChars.get(index);
                if (filteredChar != null) {
                    reversedWord.insert(index.intValue(), filteredChar);
                }
            });
            anagram.append(reversedWord).append(" ");
        }
        return anagram.toString().trim();
    }


}
