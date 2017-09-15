import java.util.*;

/**
 * Created by Albin on 2017-09-15.
 */
public class SpellChecker {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzåäö";
    private Map<String, Integer> knowWords;
    private Bloom bloom;
    private final double PROBABILITY = 0.0000001;  //probability

    public SpellChecker(List<String> words) {
        bloom = new Bloom(words.size(), PROBABILITY);
        for (String word : words) {
            bloom.add(word);
        }
        knowWords = new HashMap<String, Integer>();
    }

    private int depth;

    public List<String> correctWord(String word) {
        depth = 0;
        Set<String> candidates = new HashSet<String>();
        List<String> list = edits(word);
        for (String edit : list) {
            if (bloom.test(edit) && !candidates.contains(edit))
                candidates.add(edit);
        }
        depth++;
        if (candidates.size() > 0)
            return new ArrayList<String>(candidates);
        while (candidates.size() == 0) {
            Set<String> string = new HashSet<String>();
            for (String edit : list) {
                for (String w : edits(edit)) {
                    if (bloom.test(w) && !candidates.contains(w))
                        candidates.add(w);
                    else
                        string.add(w);
                }
            }
            depth++;
            list = new ArrayList<String>(string);
        }

        return new ArrayList<String>(candidates);
    }

    private ArrayList<String> edits(String word) {
        HashSet<String> edits = new HashSet<String>();

        // Rule 1
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            edits.add(sb.toString());
        }

        // Rule 2
        for (int i = 0; i < ALPHABET.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                StringBuilder sb = new StringBuilder(word);
                sb.insert(j, ALPHABET.charAt(i));
                edits.add(sb.toString());
            }
        }
        for (int i = 0; i < ALPHABET.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.append(ALPHABET.charAt(i));
            edits.add(sb.toString());
        }

        // Rule 3
        for (int i = 0; i < ALPHABET.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(j, ALPHABET.charAt(i));
                edits.add(sb.toString());
            }
        }
        return new ArrayList<String>(edits);
    }

    public String printCorrect(String w) {
        List<String> result = this.correctWord(w);
        StringBuilder sb = new StringBuilder();
        sb.append("(" + depth + ") ");
        for (String word : result) {
            sb.append(word);
            sb.append(" ");
        }
        return sb.toString();
    }

}
