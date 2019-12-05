import java.util.ArrayList;
import java.util.HashMap;
/**
 * The <code>FrequencyTable</code> will be stored in an encompassing
 * FrequencyTable class which extends a Collection of your choice.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class FrequencyTable extends HashMap<String, FrequencyList> {

    /**
     * Build Frequency Table
     * @param passages
     */
    public static FrequencyTable buildTable(ArrayList<Passage> passages) {
        FrequencyTable frequencyTable = new FrequencyTable();
        //iterate each passage
        for (Passage p:passages) {
            //iterate each word
            for (String word: p.getWords()) {
                if(!frequencyTable.containsKey(word)){
                    frequencyTable.put(word, new FrequencyList(word, passages));
                }
            }
        }
        return frequencyTable;
    }
    /**
     * Add passage to Frequency Table
     * @param p
     */
    public void addPassage(Passage p) throws IllegalArgumentException {
        for(String word: p.getWords()){
            if(this.containsKey(word)){
                this.get(word).addPassage(p);
            }else{
                ArrayList<Passage> passages = new ArrayList<Passage>();
                passages.add(p);
                this.put(word,new FrequencyList(word, passages));
            }
        }
    }
    /**
     * Get word freq based on word name and which passage is
     * @param p
     * @param word
     */
    public double getFrequency(String word, Passage p) throws IllegalArgumentException {
        if(this.containsKey(word)){
            return this.get(word).getFrequency(p);
        }
        return 0;
    }

}


