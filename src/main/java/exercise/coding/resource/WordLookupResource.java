package exercise.coding.resource;

import java.util.List;

import exercise.coding.service.WordLookupService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class WordLookupResource {

    private static final Logger LOG = LoggerFactory.getLogger(WordLookupResource.class);

    @Autowired
    private WordLookupService wordLookupService;

    @RequestMapping(value = "/words/{letters}", method = RequestMethod.GET)
    public ResponseEntity findWordsFromLetters(@PathVariable("letters") String letters){

        LOG.info("Received word lookup request for letters: {}", letters);
        List<String> words = wordLookupService.findWordsFromLetters(letters);
        return new ResponseEntity<>(words, HttpStatus.OK);
    }
}
