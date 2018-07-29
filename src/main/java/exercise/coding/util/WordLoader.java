package exercise.coding.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Loads words from a file on the classpath given the name of the file.
 */
public class WordLoader {

    private static final Logger LOG = LoggerFactory.getLogger(WordLoader.class);

    private WordLoader() {} // defeat instantiation

    /**
     * Loads words from a given file and returns them in a List of Strings
     * with apostrophe-containing words removed.
     * Assumes file is available on the classpath and newline-delimited.
     * @throws IOException When the file does not exist, is not accessible, or cannot be read.
     * @return A List of Strings where each String represents a word
     */
    public static List<String> loadWords(String fileName) throws IOException {

        LOG.info("Loading words for file: {}", fileName);
        InputStream inputStream = new ClassPathResource(fileName).getInputStream();

        ArrayList<String> filteredLines = Lists.newArrayList();

        try (Reader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(!line.contains("'") && line.length() > 0){
                    filteredLines.add(line);
                }
            }
        }

        return filteredLines;
    }
}
