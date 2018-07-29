scrabble-solver
---------------

This service looks up English words comprised of user-supplied letters.

It is a Spring Boot project built using Maven. As such, the project can be imported via the pom file and
launched via the main() method in ScrabbleSolverApplication.java during development.


Starting the service
---------------

To start the application, simply execute the bundled jar from its local directory using command:

    java -jar scrabble-solver.jar


Implementation details
---------------

Upon application bootstrap, the WordLookupService loads the list of valid words into memory from a flat file.
This is an O(n) operation where n is a word/line of text in the file.

In findWordsFromLetters(), the WordLookupService uses the LetterUtility to sort and trim the user input for
comparison against the given word during iteration. It then iterates over the list using the Java streaming
api using a filter for "containment check" and PointValue lookup for ordering the resultant list.

The "containment check" is the containsIgnoringOrder() method of LetterUtility. It sorts the letters of the
word and those of the input (each an O(nlog(n)) operation where n is the character) and then iterates the letters
keeping an index into the letters of the word, moving it forward when the letters match. Since the letters are
sorted, a number of assumptions are made about the word and the input letters:
   - If the word has more characters than the input letters, return false.
   - If the character of letters is greater than the character of words, then the word contains a letter that
   does not exist in the word. Return false.
   - If the index of the word reaches the end of the word, then the word is contained in the letters. Return true.
   - If the letters are iterated successfully, the method returns with one last check that the word was iterated
   entirely (not true if the word contains characters of higher value than the input letters)

   Sorting the inputs: O(nlog(n)). Iterating the letters: O(n)

Finally, WordLookupService utilizes SpringBoot's caching to cache results of findWordsFromLetters() so as to
reduce the need to perform the operation when it has already been performed.



Next steps
---------------
Increase code coverage
Add Spring-ified integration tests (test context-based)
Add System tests (Rest Assured/maven-failsafe-plugin)
Add Perf tests (Gatling)
Pre-warm the cache for WordLookupService?
Profile the memory usage of the service under load
Tune the performance

Not covered in spec
---------------

The word list contains words with apostrophes. Scrabble does not allow words with special
characters, so we filter them out upon loading of the word list