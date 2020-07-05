package com.anagrams;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int numOfAnagrams = 0;
        try {
            Scanner scanner = new Scanner(Paths.get(args[0]));
            while (scanner.hasNextLine()){
                String s1 = scanner.nextLine();
                if (isAnagram(s1, args[1])){
                    numOfAnagrams++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(numOfAnagrams);
    }

    private static boolean isAnagram(String s1, String s2){
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()){
            throw  new IllegalArgumentException("Strings must not be null or empty");
        }

        //if the words are not the same length, they are not anagrams
        if (s1.length() != s2.length()){
            return false;
        }

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        Map<Character, Integer> characterFrequency = new HashMap<>();

        /* create map that has characters from s2 as keys
        and frequency of appearance of each character as values
         */
        for(int i = 0; i < s2.length(); i++){
            if (!characterFrequency.containsKey(s2.charAt(i))){
                characterFrequency.put(s2.charAt(i), 1);
            }else {
                int val = characterFrequency.get(s2.charAt(i));
                val++;
                characterFrequency.put(s2.charAt(i), val);
            }
        }

        /* iterate through s1, comparing characters with the keys from the map
        if the key is not present or the value is 0, compared words are not anagrams
        if the character is found among the keys and the value is not 0, decrement the value by 1

        if it iterates through the whole loop, compared words are anagrams
         */
        for (int i = 0; i < s1.length(); i++){
            if (!characterFrequency.containsKey(s1.charAt(i)) || characterFrequency.get(s1.charAt(i)) == 0){
                return false;
            }else{
                int val = characterFrequency.get(s1.charAt(i));
                val--;
                characterFrequency.put(s1.charAt(i), val);
            }
        }
        return true;
    }
}
