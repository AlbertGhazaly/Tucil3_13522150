package data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Parse {
    public static void main(String[] args) {
        parseWord("data/words.txt");
    }

    public static void parseWord(String path){
        // Specify the path
        String filePath = path;

        // Create a map to store words by length
        Map<Integer, ArrayList<String>> wordMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove leading and trailing whitespace
                line = line.trim();
                
                // Check if the line contains only alphabets
                if (line.matches("[a-zA-Z]+")) {
                    int length = line.length();
                   
                    wordMap.putIfAbsent(length, new ArrayList<>());
                    wordMap.get(length).add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Map.Entry<Integer, ArrayList<String>> entry : wordMap.entrySet()) {
            int length = entry.getKey();
            ArrayList<String> words = entry.getValue();
            Collections.sort(words, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });
    
            StringBuffer pathout = new StringBuffer();
            pathout.append("data/");
            pathout.append(String.valueOf(length));
            pathout.append(".txt");
            try (FileWriter writer = new FileWriter(pathout.toString())) {
                for (int i=0;i<words.size()-1;i++){
                    writer.write(String.valueOf(words.get(i)).toUpperCase());
                    writer.write("\n");
                }
                writer.write(String.valueOf(words.get(words.size()-1)).toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
