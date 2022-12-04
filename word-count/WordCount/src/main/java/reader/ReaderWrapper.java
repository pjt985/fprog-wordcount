package reader;

import java.io.*;

public class ReaderWrapper {
    BufferedReader reader;

    public ReaderWrapper(String path) throws FileNotFoundException {
        this.reader= new BufferedReader(new FileReader(new File(path)));
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
