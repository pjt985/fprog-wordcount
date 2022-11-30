package wordcount.utils;

import java.util.Arrays;
import java.util.List;

public class WordSeperator {
    public static List<String> getWordsFromString(String line) {
        String newLine = line.replaceAll("[^a-zA-Z0-9\\s_]", "");
        newLine = newLine.replace("\n", "");
        newLine = newLine.replace("\r", "");
        newLine = newLine.replaceAll("\\p{Punct}","");
        return Arrays.asList(newLine.split("\s"));
    }
}
