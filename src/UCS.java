import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UCS {
    public static void findUcsSolution( String pathAwal, String PathAkhir){
        
        long startTime = System.nanoTime();
        Tree root = new Tree(null,pathAwal,0,0);
        int i = 0;
        for (;i<MainProgram.listVocabs.size() && MainProgram.listVocabs.get(i).compareTo(pathAwal)!=0;i++){
        }
        MainProgram.listVocabs.remove(i);
        Tree result = new Tree();
        // List<Tree> temp= new ArrayList<Tree>();
        PriorityQueue<Tree> pq = new PriorityQueue<>(Comparator.comparingInt(Tree::getFn).reversed());
        pq.offer(root);
        boolean found = false;
        if (pathAwal.compareTo(PathAkhir)==0){
            found = true;
            result = root;
        }
        while (!found && !pq.isEmpty()){
            Tree node = pq.poll();
            node.addChildren(PathAkhir, "UCS");
            for (int n=0;n<node.getChildren().size() && !found;n++){
                if (node.getChildren().get(n).getWord().compareTo(PathAkhir)==0){
                    found = true;
                    result = node.getChildren().get(n);
                }
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
        }else{
            System.out.println("Gak Nemu !");
        }
        // return temp;
    }
}
