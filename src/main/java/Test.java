import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albin on 2017-09-16.
 */
public class Test {

    public static void main(String[] args){
        /*List<String> wordList = new ArrayList<String>();
        List<String> queries = new ArrayList<String>();
        int i = 0;
        while (!args[i].equals("#"))
            wordList.add(args[i++]);

        while (++i < args.length){
            CorrectString cs = new CorrectString(args[i], wordList);
            System.out.print(args[i] + " " );
            System.out.print("(" + cs.closestDistance + ") ");
            for(String word : cs.getClosestWords()){
                System.out.print(word + " ");
            }
            System.out.println();
        }*/
        List<String> wordList = new ArrayList<String>();
        wordList.add("komplexet");
        wordList.add("komplexitet");
        wordList.add("komplext");
        CorrectString cs = new CorrectString("hej", wordList);
        System.out.println(cs.getClosestWords());
        /*for(int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++)
                System.out.print(partDist("hej", "nej", i, j) + " ");
            System.out.println();
        }*/
    }


    static int partDist(String w1, String w2, int w1len, int w2len) {
        if (w1len == 0)
            return w2len;
        if (w2len == 0)
            return w1len;
        int res = partDist(w1, w2, w1len - 1, w2len - 1) +
                (w1.charAt(w1len - 1) == w2.charAt(w2len - 1) ? 0 : 1);
        int addLetter = partDist(w1, w2, w1len - 1, w2len) + 1;
        if (addLetter < res)
            res = addLetter;
        int deleteLetter = partDist(w1, w2, w1len, w2len - 1) + 1;
        if (deleteLetter < res)
            res = deleteLetter;
        return res;
    }
}
