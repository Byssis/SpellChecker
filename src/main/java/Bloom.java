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
        int[] hashes = HashCalculator.createHashes(word.getBytes(), this.k);
        for(int hash : hashes) {
            int pos = (Math.abs(hash % m));
            if(!bitArray[pos]){
                bitArray[pos] = true;
                inSet = false;
            }
        }
        if(inSet == false) count++;
        return inSet;
    }

    public boolean test(String word){
        int[] hashes = HashCalculator.createHashes(word.getBytes(), this.k);
        for(int hash : hashes)
            if(!bitArray[(Math.abs(hash % m))]) return false;
        return true;
    }

    public int count(){
        return count;
    }
}


