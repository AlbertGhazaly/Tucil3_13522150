import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class MainProgram {
    public static List<String> listVocabs = new ArrayList<String>();
    public static void main(String[] args){
        int n = 0;
        String startWord = "";
        String EndWord = "";
        Scanner scanner = new Scanner(System.in);
        while (n != 4){
            do{
                System.out.println("Pilih Opsi: ");
                System.out.println("1. Uniform Cost Search ");
                System.out.println("2. Greedy best-first search ");
                System.out.println("3. A* ");
                System.out.println("4. Quit ");
                System.out.print("Opsi: ");
                n = Integer.parseInt(scanner.nextLine());
                if (n<1 || n>4){
                    System.out.println("Opsi tidak Valid !");
                }
            }while(n<1 || n > 4);
            if (n!=4){
                boolean wordValid = false;
                do {
                    listVocabs.clear();
                    System.out.print("Masukkan Start Word: ");
                    startWord = scanner.nextLine().toUpperCase();
                    System.out.print("Masukkan End Word: ");
                    EndWord = scanner.nextLine().toUpperCase();
                    StringBuffer txtPath = new StringBuffer(); // Specify the path to your .txt file
                    txtPath.append("../data/");
                    txtPath.append(String.valueOf(startWord.length()));
                    txtPath.append(".txt");
                    try (BufferedReader br = new BufferedReader(new FileReader(txtPath.toString()))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            listVocabs.add(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (startWord.length()!=EndWord.length()){
                        System.out.println("Panjang kata masukan tidak valid !");
                        System.out.println();
                    }else if (!listVocabs.contains(startWord)){
                        System.out.println("Kata awal tidak valid !");
                        System.out.println();
                    }else if (!listVocabs.contains(EndWord)){
                        System.out.println("Kata akhir tidak valid !");
                        System.out.println();
                    }else{
                        wordValid = true;
                    }

                }while(!wordValid);
                
                switch (n) {
                    case 1:
                        // UCS
                        UCS.findUcsSolution(startWord, EndWord);
                        break;

                    case 2:
                        // Greed
                        Greed.findGreedSolution(startWord, EndWord);
                        break;

                    case 3: 
                        // A*
                        AStar.findAStarSolution(startWord, EndWord);
                        break;

                }
            }

        }

        scanner.close();   
    }
}
