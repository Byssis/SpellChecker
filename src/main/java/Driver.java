import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Albin on 2017-09-15.
 */
public class Driver {

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

    public static void main(String [] args) throws IOException {
        ArrayList<String> wordTest = new ArrayList<String>();
        int i = 0;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        List<String> wordList = readWordList(stdin);

        SpellChecker spellChecker = new SpellChecker(wordList);
        for(String w : wordTest) {
            System.out.println(w + " " + spellChecker.printCorrect(w));
        }
        String word;
        while ((word = stdin.readLine()) != null) {
            System.out.println(word + " " + spellChecker.printCorrect(word));
        }
    }
}
