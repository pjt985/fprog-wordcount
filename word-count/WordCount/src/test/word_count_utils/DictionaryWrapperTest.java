package word_count_utils;

import junit.framework.TestCase;
import org.junit.Test;

public class DictionaryWrapperTest extends TestCase {

    DictionaryWrapper wrapper= new DictionaryWrapper();
    private String w1= "Apfel";
    private String w2= "Birne";
    private String w3= "Zacharias";
    private String w4= "apfel";

    private String testString= "The Project Gutenberg EBook of War and Peace, by Leo Tolstoy\n";
    private String testString2= "(#9 in our series by Leo Tolstoy)";
    private String testString3="Apfel, apfel, APFEL, apFEL birne";

    @Test
    public void testCountWord() {

        wrapper.countWords(w3);
        wrapper.countWords(w1);
        wrapper.countWords(w2);
        wrapper.countWords(w3);

        assertEquals(3, wrapper.getSize());
        assertEquals(1,wrapper.getValueFromKey(w1));
        assertEquals(1,wrapper.getValueFromKey(w2));
        assertEquals(2,wrapper.getValueFromKey(w3));


    }

    public void testCountWordCaseInsensitive() {
        wrapper.countWordsCaseInsensitive(w1);
        wrapper.countWordsCaseInsensitive(w4);

        assertEquals(0,wrapper.getValueFromKey(w1));
        assertEquals(2,wrapper.getValueFromKey(w4));
    }

    public void testCountAll() {
        wrapper.countWords(WordSeperator.getWordsFromString(testString));
        wrapper.countWords(WordSeperator.getWordsFromString(testString2));
        System.out.println(wrapper);
        assertEquals(16, wrapper.getSize());
    }

    public void testCountAllCaseInsensitive() {
        wrapper.countWordsCaseInsensitive(WordSeperator.getWordsFromString(testString3));
        System.out.println(wrapper);
        assertEquals(2, wrapper.getSize());
    }
}