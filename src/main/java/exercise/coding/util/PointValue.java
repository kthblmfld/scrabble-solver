package exercise.coding.util;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Lookup for the point value of a given character, or total points
 */
public class PointValue {

    private PointValue() {
    }

    private static Map<Character, Integer> values = populateValues();

    public static Integer of(String word) {
        return IntStream.range(0, word.length()).map(i -> of(word.charAt(i))).sum();
    }

    /**
     * Gets the point value of a given character
     *
     * @param character The character having associated point value
     * @return The point value of the character
     */
    static int of(char character) {

        Integer value = values.get(character);
        return value == null ? 0: value;
    }

    private static Map<Character, Integer> populateValues() {
        values = new HashMap<>();
        values.put('a', 1);
        values.put('e', 1);
        values.put('i', 1);
        values.put('l', 1);
        values.put('n', 1);
        values.put('o', 1);
        values.put('r', 1);
        values.put('s', 1);
        values.put('t', 1);
        values.put('u', 1);
        values.put('d', 2);
        values.put('g', 2);
        values.put('b', 3);
        values.put('c', 3);
        values.put('m', 3);
        values.put('p', 3);
        values.put('f', 4);
        values.put('h', 4);
        values.put('v', 4);
        values.put('w', 4);
        values.put('y', 4);
        values.put('k', 5);
        values.put('j', 8);
        values.put('x', 8);
        values.put('q', 10);
        values.put('z', 10);
        return values;
    }
}
