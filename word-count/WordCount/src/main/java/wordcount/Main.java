package wordcount;

import com.google.common.collect.ImmutableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        System.out.println("Wait...");
        Map<String, List<String>> librarySensitive = new HashMap<>();
        Map<String, List<String>> libraryInsensitive = new HashMap<>();
        files.stream().forEach(f -> {
            librarySensitive.put(f,getWordsFromString(readTextFromFile(f)));
        });
        librarySensitive.forEach((k,v) -> {
            libraryInsensitive.put(k, v.stream().map(word -> word.toLowerCase()).collect(Collectors.toList()));
        });
        System.out.println("Wait a little bit longer...");
        Map<String, Map<String,Long>>mapSens = new HashMap<>();
        Map<String, Map<String,Long>>mapInsens = new HashMap<>();

        librarySensitive.forEach((k,v) -> {
            mapSens.put(k,reduce(v));
        });

        System.out.println("Almost there...");
        libraryInsensitive.forEach((k,v) -> {
            mapInsens.put(k,reduce(v));
        });

        System.out.println("Result: ");
        finishUp(mapSens,"sens.txt");
        finishUp(mapInsens, "insens.txt");

    }

    public static boolean finishUp(Map<String, Map<String,Long>> map, String name){
        map.forEach((k,v) -> {
            List<Map.Entry<String, Long>> entries = new ArrayList<>(v.entrySet());
            Collections.sort(entries, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
            writeResults(entries.toString(), name);
        });
        return true;
    }

    public static boolean writeResults(String content, String name){
        final Path path = Paths.get("./out/data/"+name);
        try (
                final BufferedWriter writer = Files.newBufferedWriter(path,
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        ) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static long count(String word, List<String> words){
        return words.stream().filter( w -> w.equals(word)).count();
    }

    public static Map<String, Long> reduce(List<String> list){
        Map<String, Long> tmpMap = new HashMap<>();
        Set<String> tmpSet = list.stream().collect(Collectors.toSet());
        tmpSet.forEach( word -> tmpMap.put(word, count(word, list)));

        return tmpMap;
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
