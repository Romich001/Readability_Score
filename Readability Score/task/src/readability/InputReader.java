package readability;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class InputReader {

    public static String getInput(String path)  {
        File file = new File(path);
        String inputText = null;
        try(FileInputStream fin = new FileInputStream(file)){
            inputText = new String(fin.readAllBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputText;
    }
}
