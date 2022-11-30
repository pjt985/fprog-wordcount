package wordcount;

import com.google.common.collect.ImmutableList;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

import static wordcount.utils.WordSeperator.getWordsFromString;

/*
 * Useful links to research functional programming in Java:
 * - https://www.geeksforgeeks.org/functional-programming-in-java-with-examples/
 * - https://www.geeksforgeeks.org/functional-programming-in-java-8-using-the-stream-api-with-example/?ref=rp
 * - https://www.geeksforgeeks.org/concat-method-of-stream-interface-in-java-api/?ref=rp
 * - https://www.geeksforgeeks.org/difference-between-stream-of-and-arrays-stream-method-in-java/?ref=rp
 * - https://www.geeksforgeeks.org/character-stream-vs-byte-stream-java/?ref=rp
 * - https://www.geeksforgeeks.org/how-to-prepare-for-icfp-or-international-conference-for-functional-programming/?ref=rp
 * - https://www.geeksforgeeks.org/count-occurrence-of-a-given-character-in-a-string-using-stream-api-in-java/?ref=rp
 * - https://www.geeksforgeeks.org/foreach-loop-vs-stream-foreach-vs-parallel-stream-foreach/?ref=rp
 * - https://www.geeksforgeeks.org/java-stream-api-filters/?ref=rp
 * - https://www.geeksforgeeks.org/functional-interfaces-java/?ref=rp
 * */
public class Main {
    public static void main(String[] args) {
        String startDirectory = "./src/resources";
        String filePathAndName = Paths.get(startDirectory)
                .toAbsolutePath()
                .normalize()
                .toString();
        File startFile = new File(filePathAndName);

        ImmutableList<String> directories = new ImmutableList.Builder<String>().build();
        ImmutableList<String> files = new ImmutableList.Builder<String>().build();

        directories = getAllSubFolders(startFile);
        for (String dir:
             directories) {
            files = new ImmutableList.Builder<String>()
                    .addAll(files)
                    .addAll(getAllFilesFromDirectory(dir))
                    .build();
        }

        Map<String, List<String>> librarySensitive = new HashMap<>();
        Map<String, List<String>> libraryInsensitive = new HashMap<>();
        files.stream().forEach(f -> {
            librarySensitive.put(f,getWordsFromString(readTextFromFile(f)));
        });
        libraryInsensitive.forEach((k,v) -> {
            List<String> words = new ArrayList<>();
            v.forEach(word -> words.add(word.toLowerCase()));
            libraryInsensitive.put(k,words);
        });
    }

    public static String readTextFromFile(final String f) {
        String text = "";
        String line = "";
        File file = new File(f);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                text += line;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return text;
    }


    public static ImmutableList<String> getAllSubFolders(final File startFolder) {
        for (File f:
                startFolder.listFiles()) {
            if (f.isDirectory()) {
                return new ImmutableList.Builder<String>()
                        .add(f.getAbsolutePath())
                        .addAll(getAllSubFolders(f))
                        .build();
            }
        }
        return new ImmutableList.Builder<String>().build();
    }

    private static ImmutableList<String> getAllFilesFromDirectory(final String dir) {
        File folder = new File(dir);
        ImmutableList<String> files = new ImmutableList.Builder<String>().build();
        for (File f:
                folder.listFiles()) {
            if (f.isFile()) {
                files = new ImmutableList.Builder<String>()
                        .add(f.getAbsolutePath())
                        .addAll(files)
                        .build();
            }
        }
        return files;
    }
}
