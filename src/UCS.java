import java.util.ArrayList;
import java.util.List;

public class UCS {
    public static int visited_node = 0;
    public static void findUcsSolution( String wordAwal, String wordAkhir){
        UCS.visited_node = 0;
        long startTime = System.nanoTime();
        Tree root = new Tree(null,wordAwal,0,0);
        int i = 0;
        for (;i<MainProgram.listVocabs.size() && MainProgram.listVocabs.get(i).compareTo(wordAwal)!=0;i++){
        }
        MainProgram.listVocabs.remove(i);
        Tree result = new Tree();
        // PriorityQueue<Tree> pq = new PriorityQueue<>(Comparator.comparingInt(Tree::getFn).reversed());
        // List<Tree> temp= new ArrayList<Tree>();

        List<Tree> pq = new ArrayList<>();
        Tree.addQueueTree(pq,root);
        boolean found = false;
        while (!found && !pq.isEmpty()){
            Tree node = pq.remove(0);
            if (node.getWord().compareTo(wordAkhir)==0){
                found = true;
                result = node;
            }
            UCS.visited_node += 1;
            node.addChildren(wordAkhir, "UCS");
            for (int n=0;n<node.getChildren().size() && !found;n++){
                Tree.addQueueTree(pq,node.getChildren().get(n));
            }
        }
        long endTime = System.nanoTime();
        long elapsedTimeInMillis = (endTime - startTime) / 1_000_000;
        if (found){
            List<String> resultList = new ArrayList<String>();
            i = 0;
            while (result!=null){
                
                resultList.add(0,result.getWord());
                result = result.getParent();
                i++;
            }
            System.out.print("Step: ");
            System.out.println(i-1);
            System.out.println("Hasil: ");
            for (i=0;i<resultList.size();i++){
                System.out.print(i+1);
                System.out.print(". ");
                System.out.println(resultList.get(i));
            }
            System.out.println("Time execution: " + elapsedTimeInMillis + " ms");
            System.out.print("Visited Node: ");
            System.out.println(UCS.visited_node);
        }else{
            System.out.println("Gak Nemu !");
            System.out.println("Time execution: " + elapsedTimeInMillis + " ms");
            System.out.print("Visited Node: ");
            System.out.println(UCS.visited_node);
        }
        // return temp;
    }
}
