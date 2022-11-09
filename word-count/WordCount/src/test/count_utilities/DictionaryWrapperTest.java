package count_utilities;

import junit.framework.TestCase;
import org.junit.Test;

public class DictionaryWrapperTest extends TestCase {

    DictionaryWrapper wrapper= new DictionaryWrapper();
    String w1= "Apfel";
    String w2= "Birne";
    String w3= "Zacharias";

    @Test
    public void testCountWord() {

        wrapper.countWord(w3);
        wrapper.countWord(w1);
        wrapper.countWord(w2);
        wrapper.countWord(w3);

        assertEquals(3, wrapper.getSize());
        assertEquals(1,wrapper.getValueFromKey(w1));
        assertEquals(1,wrapper.getValueFromKey(w2));
        assertEquals(2,wrapper.getValueFromKey(w3));


    }
}