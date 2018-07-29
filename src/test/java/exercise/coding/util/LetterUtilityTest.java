package exercise.coding.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LetterUtilityTest {

    @Test
    public void prepareLettersInputNotSorted() {

        String expectedOutput = "cdeklmnoprsv";
        String letters = "peorlskdmcnv";

        assertThat(LetterUtility.prepareLettersForWordExtraction(letters))
                .isEqualTo(expectedOutput);
    }

    @Test
    public void prepareLettersInputUppercase() {

        String expectedOutput = "abcdefghij";
        String letters = "ABCDEFGHIJ";

        assertThat(LetterUtility.prepareLettersForWordExtraction(letters))
                .isEqualTo(expectedOutput);
    }

    @Test
    public void prepareLettersInputContainsWhitespace() {

        String expectedOutput = "abcd";
        String letters = "  a b c d  ";

        assertThat(LetterUtility.prepareLettersForWordExtraction(letters))
                .isEqualTo(expectedOutput);
    }

    @Test
    public void containsIgnoringOrderUnordered(){

        String word = "dad";
        String letters = "dda";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isTrue();
    }

    @Test
    public void containsIgnoringOrderFalseCase(){

        String word = "bitty";
        String letters = "aht";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isFalse();
    }

    @Test
    public void containsIgnoringOrderLength(){

        String word = "abandoner";
        String letters = "hat";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isFalse();
    }

    @Test
    public void containsIgnoringOrderLettersGreater(){

        String word = "aep";
        String letters = "aht";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isFalse();
    }

    @Test
    public void containsIgnoringOrderWordGreater(){

        String word = "axx";
        String letters = "aaattt";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isFalse();
    }

    @Test
    public void containsIgnoringOrderSmallWord(){

        String word = "a";
        String letters = "hta";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isTrue();
    }

    @Test
    public void containsIgnoringOrderSameLengthWordGreater(){

        String word = "xxv";
        String letters = "hat";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isFalse();
    }

    @Test
    public void containsIgnoringOrderSameLengthWordLess(){

        String word = "rah";
        String letters = "hat";

        assertThat(LetterUtility.containsIgnoringOrder(letters,word)).isFalse();
    }
}