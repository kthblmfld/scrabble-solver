package exercise.coding.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;

import exercise.coding.util.LetterUtility;
import exercise.coding.util.WordLoader;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class WordLookupServiceTest {

    private static final String LETTERS = "add";

    @Tested
    private WordLookupService wordLookupService;

    @Before
    public void setUp(){
        new MockUp<WordLoader>() {
            @Mock
            List<String> loadWords(String fileName) {
                List<String> response =
                        Lists.newArrayList("a", "ad", "dad", "da"); // we want to see these
                response.addAll(Lists.newArrayList("aa", "ddd")); // but not these
                return response;
            }
        };
    }

    @Test
    public void findWordsFromLetters() {

        new MockUp<LetterUtility>() {
            @Mock
            String prepareLettersForWordExtraction(String letters) {
                return LETTERS;
            }
        };

        List<String> wordsFound =
                wordLookupService.findWordsFromLetters(LETTERS);

        assertThat(wordsFound).containsOnlyOnce("a", "ad", "dad", "da");
        assertThat(wordsFound).doesNotContain("aa", "");
    }
}