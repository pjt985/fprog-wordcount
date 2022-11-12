package word_count_utils;

public class WordSeperator {
    public static String[] getWordsFromString(String line){
        String newLine=line.replaceAll("[^a-zA-Z0-9\\s_]", "");
        newLine= newLine.replace("\n","");
        newLine= newLine.replace("\r","");
        return newLine.split("\s");
    }
}
