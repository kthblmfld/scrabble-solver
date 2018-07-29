package exercise.coding.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helper functions for the treatment of
 */
public class LetterUtility {

    /**
     * Prepare letters for word lookup. Convert them to lowercase, trim whitespace.
     *
     * @param letters A String of characters to be used to derive words from
     * @return A sorted, lower-cased, de-duplicated list of letters
     */
    public static String prepareLettersForWordExtraction(String letters) {

        String lowerCaseSorted = Stream.of(
                letters
                        .toLowerCase()
                        .split(""))
                .sorted()
                .collect(Collectors.joining());

        return lowerCaseSorted.trim();
    }

    /**
     * Given a superset of letters, determine whether letters contains the subset word.
     * For example:  letters = "dbb" and word is "bd" then return true.
     *
     * @param letters String representing a superset of letters
     * @param word    String representing the word to be determined as a subset of letters
     * @return True if letters contains one character matching every character of word
     */
    public static boolean containsIgnoringOrder(String letters, String word) {

        /*
          The letters should be a superset of the word. Thus, we should
          be able to sort both and iterate until either the word ends or we find a
          mismatch in the sorted arrays
         */

        char lettersChars[] = letters.toCharArray();
        char wordChars[] = word.toCharArray();

        // exit immediately if there are not enough letters to build the word
        if(lettersChars.length < wordChars.length){
            return false;
        }

        Arrays.sort(lettersChars);
        Arrays.sort(wordChars);

        int wordIndex = 0;

        for (char lettersChar : lettersChars) {

            if(wordIndex == wordChars.length){
                // reached the end of the word
                return true;
            }

            if (lettersChar > wordChars[wordIndex]) {

                // If we are on a higher letter than the word, don't have enough letters
                return false;

            } else if (wordChars[wordIndex] == lettersChar) {

                // the chars match, increment to next char in word
                wordIndex++;
            }
        }

        // Declare victory only if word has been iterated completely
        return wordIndex == wordChars.length;
    }
}
