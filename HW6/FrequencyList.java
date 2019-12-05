import java.util.ArrayList;
import java.util.HashMap;
/**
 * The <code>FrequencyList</code> contain a String member variable
 * corresponding to its word that it’s keeping track of, and because
 * some texts will have words that other texts don’t contain, this
 * class will only hold the values which are greater than 0 to avoid
 * taking up unnecessary space.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class FrequencyList {
    private String word;
    private ArrayList<Double> frequencies;
    private HashMap<String, Integer> passageIndices;
    private int index = 0;
    private ArrayList<Passage> passages;

    public FrequencyList(String word, ArrayList<Passage> passages){
            this.word = word;
            this.passages = passages;
            this.frequencies = new ArrayList<Double>();
            this.passageIndices = new HashMap<String, Integer>();
            this.buildFrequencyList(passages);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Build Freq List
     * @param passages
     */
    private void buildFrequencyList(ArrayList<Passage> passages){
        for(Passage p: passages){
            if(p.getWords().contains(this.word)){
                this.frequencies.add(p.getWordFrequency(this.word));
                this.passageIndices.put(p.getTitle(), frequencies.size()-1);
            }
        }
    }
    /**
     * Add passage to freq list
     * @param p
     */
    public void addPassage(Passage p) {
        this.passages.add(p);
        if(p.getWords().contains(this.word)){
            this.frequencies.add(p.getWordFrequency(this.word));
            this.passageIndices.put(p.getTitle(), frequencies.size()-1);
        }
    }
    /**
     * Get freq based on which passage is
     * @param p
     */
    public double getFrequency(Passage p) {
        if (passageIndices.keySet().contains(p.getTitle())) {
            return this.frequencies.get(passageIndices.get(p.getTitle()));
        }
        return 0.0;
    }
}


