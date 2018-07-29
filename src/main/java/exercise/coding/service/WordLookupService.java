package exercise.coding.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import exercise.coding.util.LetterUtility;
import exercise.coding.util.PointValue;
import exercise.coding.util.WordLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * Performs the core function of looking up a subset of words derived from the given letters.
 */
@Service
@EnableCaching
public class WordLookupService {

    private static final Logger LOG = LoggerFactory.getLogger(WordLookupService.class);
    private static final String FILE_NAME_WORDS_LIST = "words.txt";
    private static List<String> words;

    /**
     * Find words comprised entirely of the given letters
     * @param letters The superset of letters from which to derive words
     * @return A List of resultant words derived from the given letters
     */
    @Cacheable("letterCombinations")
    public List<String> findWordsFromLetters(String letters){

        String filteredLetters = LetterUtility.prepareLettersForWordExtraction(letters);

        return
                words.stream()
                .filter(word -> LetterUtility.containsIgnoringOrder(filteredLetters, word))
                .sorted((word1, word2) -> Integer.compare(PointValue.of(word2), PointValue.of(word1)))
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void prepareWords(){

        try {
            words = WordLoader.loadWords(FILE_NAME_WORDS_LIST);
        } catch (IOException e) {
            LOG.error("Failed to load word list from file: {}", FILE_NAME_WORDS_LIST, e);
            throw new RuntimeException(e);
        }
    }
}
