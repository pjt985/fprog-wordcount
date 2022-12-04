import reader.ReaderWrapper;
import word_count_utils.DictionaryWrapper;
import word_count_utils.WordSeperator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Date date= new Date();
        long oldMili= date.getTime();
        ReaderWrapper wrapper= null;
        try {
            wrapper = new ReaderWrapper("src/beispiel.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        objectOriented(wrapper);


        LocalDateTime newTime= LocalDateTime.now();

        date= new Date();
        long newMili= date.getTime();
        long mili= newMili-oldMili;

        System.out.println("Dauer: " + mili + " Milisekunden");
    }

    public static void objectOriented(ReaderWrapper readerWrapper){
        DictionaryWrapper dictionaryWrapper= new DictionaryWrapper();
        String s="";
        while(true){
            try {
                if (!((s=readerWrapper.getLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            dictionaryWrapper.countWords(WordSeperator.getWordsFromString(s));
        }

        System.out.println(dictionaryWrapper);
    }
}

