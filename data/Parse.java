package data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parse {
    // public static void main(String[] args) {
    //     parseWord("data/words.txt");
    // }

    public static void parseWord(String path){
        // Specify the path to your .txt file
        String filePath = path;

        // Create a map to store words by their length
        Map<Integer, ArrayList<String>> wordMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Remove leading and trailing whitespace
                line = line.trim();
                
                // Check if the line contains only alphabets
                if (line.matches("[a-zA-Z]+")) {
                    int length = line.length();
                    // If the length is not in the map, create a new ArrayList for that length
                    wordMap.putIfAbsent(length, new ArrayList<>());
                    // Add the word to the ArrayList corresponding to its length
                    wordMap.get(length).add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the word map
        for (Map.Entry<Integer, ArrayList<String>> entry : wordMap.entrySet()) {
            int length = entry.getKey();
            ArrayList<String> words = entry.getValue();
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
