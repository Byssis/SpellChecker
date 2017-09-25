/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CorrectString {
    private LinkedList<String> closestWords = null;

    private Map<String, Integer> cache;

    private int closestDistance = -1;

    /*
        Get closet distance between two words. The distance between two word is the number of edits
        word 1 needs to be equal to word 2. Allowed edits are remove one letter from any position, add
        one letter in any position and change any letter in the word. All of the edits have the coast of 1.
        This method using dynamic programing using bottom up strategy.
        @param  w1      Word to calculate distance to word 2
        @param  w2      Word to compare to
        @param  w1len   Length of word 1
        @param  w2len   Length of word 2
        @return         Minimum distance between
     */
    int partDist(String w1, String w2, int w1len, int w2len) {
        // To remember previous state
        int [][] matrix = new int [w1len + 1][w2len +1];
        for(int i = 0; i <= w1len; i++){
            for(int j = 0; j <= w2len; j++){
                // If word 1 is empty
                if(i == 0)
                    matrix[i][j] = j;
                // If word 2 is empty
                else if(j == 0)
                    matrix[i][j] = i;

                // if the previous letter are the same
                else if(w1.charAt(i-1) == w2.charAt(j-1))
                    matrix[i][j] = matrix[i-1][j-1];
                /*
                    remove one letter matrix[i][j-1]
                    add one letter matrix[i-1][j]
                    change letter matrix[i-1][j-1]
                 */
                else
                    matrix[i][j] = 1 + min(matrix[i][j-1], matrix[i-1][j], matrix[i-1][j-1]);
            }
        }

        return matrix[w1len][w2len];
    }

    private int min(int insert, int remove, int replace) {
        if(insert < remove && insert < replace) return insert;
        else if(remove < insert && remove < replace) return remove;
        else return replace;
    }

    int Distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length());
    }

    public CorrectString(String w, List<String> wordList, Map<String, Integer> cache) {
        for (String s : wordList) {
            if (closestDistance != -1 && w.length() + closestDistance < s.length()) continue;
            String key = w+";"+s;
            int dist = (cache.containsKey(key)) ? cache.get(key) : Distance(w, s);
            //System.out.println("d(" + w + "," + s + ")=" + dist);
            if (dist < closestDistance || closestDistance == -1) {
                closestDistance = dist;
                closestWords = new LinkedList<String>();
                closestWords.add(s);
            }
            else if (dist == closestDistance)
                closestWords.add(s);
            cache.put(key, dist);
        }
    }

    int getMinDistance() {
        return closestDistance;
    }

    List<String> getClosestWords() {
        return closestWords;
    }

    public static List<String> readWordList(BufferedReader input) throws IOException {
        LinkedList<String> list = new LinkedList<String>();
        while (true) {
            String s = input.readLine();
            if (s.equals("#"))
                break;
            list.add(s);
        }
        return list;
    }

    public static void main(String args[]) throws IOException {
        long t1 = System.currentTimeMillis();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
        // standardinställning för teckenkodningen.
        List<String> wordList = readWordList(stdin);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String word;
        while ((word = stdin.readLine()) != null) {
            CorrectString closestWords = new CorrectString(word, wordList, map);
            System.out.print(word + " (" + closestWords.getMinDistance() + ")");
            for (String w : closestWords.getClosestWords())
                System.out.print(" " + w);
            System.out.println();
        }
        long tottime = (System.currentTimeMillis() - t1);
        //System.out.println("CPU time: " + tottime + " ms");
    }
}
