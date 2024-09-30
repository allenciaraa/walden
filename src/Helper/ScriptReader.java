package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class ScriptReader {

    String[] scriptNames = {"twilight-script-clean", "bee-movie", "shrek-clean"};
    public String[] lines = new String[9999];

    public ScriptReader() {
        Random rand = new Random();
        int choice = rand.nextInt(scriptNames.length);
        try {
            File txt = new File("res/text/" + scriptNames[choice] + ".txt");
            Scanner r = new Scanner(txt);
            int idx = 0;
            while (r.hasNextLine()) {
                String data = r.nextLine();
                lines[idx] = data;
                idx++;
            }
            r.close();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void cleanScript() {
        try {
            FileWriter w = new FileWriter("res/text/shrek-clean.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


