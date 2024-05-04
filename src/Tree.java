import java.util.ArrayList;
import java.util.List;
public class Tree{
    private int depth;
    private Tree parent;
    private String word;
    private int fn;
    private List<Tree> children;

    public Tree(){
        this.depth = 0;
        this.parent = null;
        this.word ="";
        this.fn = 0;
        this.children = new ArrayList<Tree>();
    }
    public Tree(Tree parent,String word,int depth,int fn){
        this.depth = depth;
        this.parent = parent;
        this.word =word;
        this.fn = fn;
        this.children = new ArrayList<Tree>();
    }
    public String getWord(){
        return this.word;
    }
    public int getFn(){
        return this.fn;
    }
    public List<Tree> getChildren(){
        return this.children;
    }
    public int getDepth(){
        return this.depth;
    }
    public void addChild(Tree newNode){
        this.children.add(this.children.size(),newNode);
    }
    public Tree getParent(){
        return this.parent;
    }
    public static int sameLetter(String curr,String target){
        int nSame = 0;
        for (int i=0;i<curr.length();i++){
            if (target.charAt(i)==curr.charAt(i)){
                nSame ++;
            }
        }
        return nSame;
    }
    public static int difLetter(String curr,String target){
        return target.length()-sameLetter(curr, target);
    }
    public boolean isChild(String childWord){
        int difference = 0;
        for (int i=0;i<this.word.length();i++){
            if (childWord.charAt(i)!=this.word.charAt(i)){
                difference ++;
            }
        }
        return difference==1;
    }

    public void addChildren(String target, String method){
        if (!MainProgram.listVocabs.isEmpty()){
            int n = MainProgram.listVocabs.size();
            int j = 0;
            for (int i=0;i<n;i++){
                if (isChild(MainProgram.listVocabs.get(j))){
                    if (method.compareTo("UCS")==0){
                        this.addChild(new Tree(this,MainProgram.listVocabs.get(j),this.depth+1,this.fn-1));
                    }else if (method.compareTo("Greed")==0){
                        this.addChild(new Tree(this,MainProgram.listVocabs.get(j),this.depth+1,sameLetter(MainProgram.listVocabs.get(j),target)));
                    }else if(method.compareTo("A*")==0){
                        this.addChild(new Tree(this,MainProgram.listVocabs.get(j),this.depth+1,sameLetter(MainProgram.listVocabs.get(j), target)-(this.depth+1)));
                    }
                    MainProgram.listVocabs.remove(j);
                    j -=1;
                }
                j+=1;
            }
        } 
       
    }
    public static void addQueueTree(List <Tree> arr,Tree node){
        int i = 0;
        while (i<arr.size() && node.fn <= arr.get(i).fn) {
            i++;
        }
        arr.add(i,node);
    }
}