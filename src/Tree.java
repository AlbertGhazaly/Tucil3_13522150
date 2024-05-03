import java.util.ArrayList;
import java.util.List;
public class Tree{
    private Tree parent;
    private String word;
    private int fn;
    private int depth;
    private int idxInParent;
    private List<Tree> children;

    public Tree(){
        this.parent = null;
        this.word ="";
        this.fn = 0;
        this.depth = 0;
        this.idxInParent = 0;
        this.children = new ArrayList<Tree>();
    }
    public Tree(Tree parent,String word,int depth,int idxInParent,int fn){
        this.parent = parent;
        this.word =word;
        this.fn = fn;
        this.depth = depth;
        this.idxInParent = idxInParent;
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
    public int getIdxInParent(){
        return this.idxInParent;
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
                        this.addChild(new Tree(this,MainProgram.listVocabs.get(j),this.depth+1,this.children.size(),this.fn-1));
                    }else if (method.compareTo("Greed")==0){
                        this.addChild(new Tree(this,MainProgram.listVocabs.get(j),this.depth + 1,this.children.size(),sameLetter(MainProgram.listVocabs.get(j), target)));
                    }else if(method.compareTo("A*")==0){
                        this.addChild(new Tree(this,MainProgram.listVocabs.get(j),this.depth + 1,this.children.size(),sameLetter(MainProgram.listVocabs.get(j), target)-(this.depth+1)));
                    }
                    MainProgram.listVocabs.remove(j);
                    j -=1;
                }
                j+=1;
            }
        } 
       
    }
}