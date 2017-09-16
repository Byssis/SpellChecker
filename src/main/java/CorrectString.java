/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CorrectString {
    LinkedList<String> closestWords = null;

    int closestDistance = -1;

    int partDist(String w1, String w2, int w1len, int w2len) {
        int [][] dp = new int [w1len + 1][w2len +1];
        for(int i = 0; i <= w1len; i++){
            for(int j = 0; j <= w2len; j++){
                // If word 1 is empty
                if(i == 0)
                    dp[i][j] = j;
                // If word 2 is empty
                else if(j == 0)
                    dp[i][j] = i;

                else if(w1.charAt(i-1) == w2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]);
            }
        }

        return dp[w1len][w2len];
    }

    private int min(int insert, int remove, int replace) {
        if(insert < remove && insert < replace) return insert;
        else if(remove < insert && remove < replace) return remove;
        else return replace;
    }

    int Distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length());
    }

    public CorrectString(String w, List<String> wordList) {
        for (String s : wordList) {
            int dist = Distance(w, s);
            System.out.println("d(" + w + "," + s + ")=" + dist);
            if (dist < closestDistance || closestDistance == -1) {
                closestDistance = dist;
                closestWords = new LinkedList<String>();
                closestWords.add(s);
            }
            else if (dist == closestDistance)
                closestWords.add(s);
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
        String word;
        while ((word = stdin.readLine()) != null) {
            CorrectString closestWords = new CorrectString(word, wordList);
            System.out.print(word + " (" + closestWords.getMinDistance() + ")");
            for (String w : closestWords.getClosestWords())
                System.out.print(" " + w);
            System.out.println();
        }
        long tottime = (System.currentTimeMillis() - t1);
        System.out.println("CPU time: " + tottime + " ms");
    }
}
