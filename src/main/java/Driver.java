import java.util.ArrayList;

/**
 * Created by Albin on 2017-09-15.
 */
public class Driver {
    public static void main(String [] args){
        ArrayList<String> word = new ArrayList<String>();
        ArrayList<String> wordTest = new ArrayList<String>();
        int i = 0;
        while (!args[i].equals("#"))
            word.add(args[i++]);

        for(i += 1; i < args.length; i++)
            wordTest.add(args[i]);

        SpellChecker spellChecker = new SpellChecker(word);
        for(String w : wordTest) {
            System.out.println(w + " " + spellChecker.printCorrect(w));
        }
    }
}
