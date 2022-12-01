package reader;

import java.io.*;

public class ReaderWrapper {
    BufferedReader reader;

    public ReaderWrapper(String path) {
        try {
            this.reader= new BufferedReader(new FileReader(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getLine(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
