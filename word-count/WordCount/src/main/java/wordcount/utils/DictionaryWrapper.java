package wordcount.utils;

import java.util.TreeMap;

public class DictionaryWrapper {
/*
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

    public void countWord(String word){
        if(dictionary.containsKey(word)){
            dictionary.replace(word, dictionary.get(word)+1);
        }
        else{
            dictionary.put(word,1);
        }

    }

    public void countWordCaseInsensitive(String word){
        word=word.toLowerCase();
        if(dictionary.containsKey(word)){
            dictionary.replace(word, dictionary.get(word)+1);
        }
        else{
            dictionary.put(word,1);
        }

    }

    public void countAll(String[] words){
        for(String s: words){
            countWord(s);
        }
    }

    public void countAllCaseInsensitive(String[] words){
        for(String s: words){
            s=s.toLowerCase();
            countWord(s);
        }
    }

    public int getSize(){
        return dictionary.size();
    }

    public String toString(){
        return dictionary.toString();
    }*/
}
