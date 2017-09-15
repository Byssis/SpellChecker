/**
 * Created by Albin on 2017-09-14.
 */
public class Bloom {

    private int count;
    private int k;
    private int m;
    private boolean [] bitArray;

    public Bloom(int n, double p){
        this.m =  (int)Math.ceil(-1*n*Math.log(p)/Math.pow(Math.log(2), 2));
        this.k = (int)Math.ceil(-Math.log(p)/Math.log(2));
        this.bitArray = new boolean[m];
    }

    public boolean add(String word){
        boolean inSet = true;
        for (int i = 0; i < this.k; i++){
            long hash = HashCalculator.hash(word, i);
            int pos = (int) (hash % m);
            if(!bitArray[pos]){
                inSet = false;
                bitArray[pos] = true;
            }
        }
        if(!inSet) count++;
        return inSet;
    }

    public boolean test(String word){
        for (int i = 0; i < this.k; i++){
            long hash = HashCalculator.hash(word, i);
            int pos = (int) (hash % m);
            if(!bitArray[pos]){
                return false;
            }
        }
        return true;
    }

    public int count(){
        return count;
    }
}


