package count_utilities;

import java.util.HashMap;

public class DictionaryWrapper {

    private HashMap<String, Integer> dictionary;

    public DictionaryWrapper() {
        this.dictionary = new HashMap<String, Integer>();
    }
    public int getValueFromKey(String key){
        return dictionary.get(key);
    }

    public void countWord(String word){
        if(dictionary.containsKey(word)){
            dictionary.replace(word, dictionary.get(word)+1);
        }
        dictionary.put(word,1);
    }

    public int getSize(){
        return dictionary.size();
    }

    public void getOrderedList(){
        //dictionary.
    }
}
