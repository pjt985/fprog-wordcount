package word_count_utils;

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

    public TreeMap<String, Integer> countWords(String... words){
        for(String word: words){
            if(dictionary.containsKey(word)){
                dictionary.replace(word, dictionary.get(word)+1);

            }
            else{
                dictionary.put(word,1);
            }
        }
        this.dictionary.remove("");
        return this.dictionary;
    }

    public TreeMap<String, Integer> countWordsCaseInsensitive(String... words){
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
        return this.dictionary;
    }

    public int getSize(){
        return dictionary.size();
    }

    public String toString(){
        return dictionary.toString();
    }
}
