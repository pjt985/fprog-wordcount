import reader.ReaderWrapper;
import word_count_utils.DictionaryWrapper;
import word_count_utils.WordSeperator;

import java.io.File;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Date date= new Date();
        long oldMili= date.getTime();
        ReaderWrapper wrapper= new ReaderWrapper("C:\\FH\\Sem5\\FPROG\\fprog-wordcount\\word-count\\WordCount\\src\\beispiel.txt");
        DictionaryWrapper dictionary= new DictionaryWrapper();
        String s;
        while((s=wrapper.getLine())!=null){
            dictionary.countWords(WordSeperator.getWordsFromString(s));
        }

        System.out.println(dictionary);


        LocalDateTime newTime= LocalDateTime.now();

        date= new Date();
        long newMili= date.getTime();
        long mili= newMili-oldMili;

        System.out.println("Dauer: " + mili + " Milisekunden");
    }
}

