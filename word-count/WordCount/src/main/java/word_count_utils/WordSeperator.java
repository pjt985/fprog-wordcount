package word_count_utils;

public class WordSeperator {
    public static String[] getWordsFromString(String line){
        String newLine=line.replaceAll(" {2,}|-{2,}|[^a-zA-Z-\\s_]", " ");
        newLine= newLine.replaceAll(" {2,}", " ");
        return newLine.split("\s");
    }
}
