import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    public static int visited_node = 0;

    public static void findAStarSolution(String wordAwal, String wordAkhir){
        AStar.visited_node = 0;
          long startTime = System.nanoTime();
        Tree root = new Tree(null,wordAwal,0,0,0);
        int i = 0;
        for (;i<MainProgram.listVocabs.size() && MainProgram.listVocabs.get(i).compareTo(wordAwal)!=0;i++){
        }
        MainProgram.listVocabs.remove(i);
        Tree result = new Tree();
        // List<Tree> temp= new ArrayList<Tree>();
        PriorityQueue<Tree> pq = new PriorityQueue<>(new Comparator<Tree>() {
            @Override
            public int compare(Tree t1, Tree t2) {
                int fnComparison = Integer.compare(t2.getFn(), t1.getFn()); // Compare "fn" values in descending order

                // If "fn" values are equal, compare based on insertion order
                if (fnComparison == 0) {
                    return Integer.compare(t1.getIdxInParent()
                    , t2.getIdxInParent());
                }

                return fnComparison;
            }
        });
        pq.offer(root);
        boolean found = false;
        while (!found && !pq.isEmpty()){
            Tree node = pq.poll();
            if (node.getWord().compareTo(wordAkhir)==0){
                found = true;
                result = node;
            }
            AStar.visited_node += 1;
            node.addChildren(wordAkhir, "A*");
            for (int n=0;n<node.getChildren().size() && !found;n++){
                pq.offer(node.getChildren().get(n));
            }
        }
        long endTime = System.nanoTime();
        long elapsedTimeInMillis = (endTime - startTime) / 1_000_000;
        if (found){
            List<String> resultList = new ArrayList<String>();
            i = 0;
            int depth = result.getDepth();
            while (result!=null){
                resultList.add(i,result.getWord());
                result = result.getParent();
            }
            System.out.print("Step: ");
            System.out.println(depth);
            System.out.println("Hasil: ");
            for (i=0;i<resultList.size();i++){
                System.out.print(i+1);
                System.out.print(". ");
                System.out.println(resultList.get(i));
            }
            System.out.println("Time execution: " + elapsedTimeInMillis + " ms");
            System.out.print("Visited Node: ");
            System.out.println(AStar.visited_node);
        }else{
            System.out.println("Gak Nemu !");
        }
    }
}
