package word_count_utils;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryWrapper {

    private TreeMap<String, Integer> dictionary;

    public DictionaryWrapper() {
        this.dictionary = new TreeMap<String, Integer>();
    }
    public int getValueFromKey(String key){
        try{
            return dictionary.get(key);
        }catch (NullPointerException e){
            return 0;
        }

    }

    public void countWords(String... words){
        for(String word: words){
            if(dictionary.containsKey(word)){
                dictionary.replace(word, dictionary.get(word)+1);

            }
            else{
                dictionary.put(word,1);
            }
        }
        this.dictionary.remove("");
    }

    public void countWordsCaseInsensitive(String... words){
        for(String word: words){
            word=word.toLowerCase();
            if(dictionary.containsKey(word)){
                dictionary.replace(word, dictionary.get(word)+1);
            }
            else{
                dictionary.put(word,1);
            }
        }
        this.dictionary.remove("");
    }

    public int getSize(){
        return dictionary.size();
    }

    public String toString(){
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        dictionary.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap.toString();
    }
}
