package exercise.coding.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class WordLoaderTest {

    private static final String FILE_NAME_VALID = "words.txt";
    private static final String FILE_NAME_INVALID = "foo";

    @Test
    public void loadWordsHappyPath() throws IOException {

        List<String> words = WordLoader.loadWords(FILE_NAME_VALID);
        assertThat(words).isNotEmpty();
    }

    @Test(expected = IOException.class)
    public void loadWordsInvalidFileName() throws IOException {
        WordLoader.loadWords(FILE_NAME_INVALID);
        fail("Should throw an IOException when given an invalid file name");
    }

    @Test(expected = IOException.class)
    public void loadWordsInvalidFileContent() throws IOException {

        new MockUp<BufferedReader>() {
            @Mock
            String readLine() throws IOException {
                throw new IOException("Bad content");
            }
        };

        WordLoader.loadWords(FILE_NAME_VALID);
        fail("Should propagate exceptions from FileUtils.readLines()");
    }

    @Test
    public void loadWordsFiltersApostrophe() throws IOException {

        new MockUp<BufferedReader>() {

            int i = 0;

            @Mock
            String readLine() {

                if (i == 1) {
                    return null;
                } else {
                    i += 1;
                    return "bob's";
                }
            }
        };

        List<String> result = WordLoader.loadWords(FILE_NAME_VALID);
        assertThat(result).isEmpty();
    }
}