import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.*;
/**
 * The <code>Passage</code> that is a hash table which maps a
 * String (word) to an Integer value (occurences of that word).
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class Passage {
    //member
    private String title;
    private int wordCount;
    private HashMap<String, Double> similarTitles;
    private HashMap<String, Integer> wordsHashMap;
    private ArrayList<String> stopFileArrayList;

    //constructor
    public Passage(String title, File file, ArrayList<String> stopFileArrayList) {
        this.similarTitles = new HashMap<String, Double>();
        this.title = title;
        this.wordsHashMap = new HashMap<String, Integer>();
        this.stopFileArrayList = stopFileArrayList;
        try {
            this.parseFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //getter and setter
    public HashMap<String, Integer> getWordsHashMap() {
        return this.wordsHashMap;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public HashMap getSimilarTitles() {
        return this.similarTitles;
    }

    public void setSimilarTitles(HashMap<String, Double> similarTitles) {
        this.similarTitles = similarTitles;
    }
    /**
     * Parse the file
     * @param file
     * @throws FileNotFoundException
     */
    public void parseFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
//            str.replaceAll("\\p{Punct}", "");
            String[] words = str.split(" ");
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim();
                if (!word.equals("") && !this.stopFileArrayList.contains(word)) {
                    if (this.wordsHashMap.keySet().contains(word)) {
                        int occurence = this.wordsHashMap.get(word);
                        occurence++;
                        this.wordsHashMap.put(word, occurence);
                    } else {
                        this.wordsHashMap.put(word, 1);
                    }
                }
            }
        }
        scanner.close();
    }

    /**
     * Get the similarity value
     * @param p1
     * @param p2
     * @param frequencyTable
     * @return
     */
    public static double cosineSimilarity(Passage p1, Passage p2, FrequencyTable frequencyTable) {
        Set<String> words = new HashSet<String>();
        words.addAll(p1.getWords());
        words.addAll(p2.getWords());

        ArrayList<Double> vectorU = new ArrayList<Double>();
        ArrayList<Double> vectorV = new ArrayList<Double>();

        for (String word : words) {
            vectorU.add(frequencyTable.getFrequency(word, p1));
            vectorV.add(frequencyTable.getFrequency(word, p2));
        }


        double product = 0.0;
        double lenU = 0.0;
        double lenV = 0.0;
        for (int i = 0; i < vectorU.size(); i++) {
            product += vectorU.get(i) * vectorV.get(i);
            lenU += vectorU.get(i) * vectorU.get(i);
            lenV += vectorV.get(i) * vectorV.get(i);
        }
        double cos = product / (Math.sqrt(lenU) * Math.sqrt(lenV));
//        if(p1.getWordCount()>p2.getWordCount()){
//            for(String word:p1.getWords()){
//                if(p2.getWords().contains(word)){
//                    double up = frequencyTable.getFrequency(word, p1)*frequencyTable.getFrequency(word, p2);
//                    double down = frequencyTable.get()
//                }
//
//            }
//
//        }


        return cos;
    }

    public double getWordFrequency(String word) {
        return this.wordsHashMap.get(word);
    }

    public Set<String> getWords() {
        return this.wordsHashMap.keySet();
    }

    /**
     * Print out the similarity and suspected passages
     * @return
     */
    public String toString() {
        HashMap<String, Double> suspected = new HashMap<String, Double>();
        System.out.println();
        System.out.printf("%-30s", this.title.replaceAll(".txt", ""));
        double percentage;

        for (Map.Entry<String, Double> entry : this.similarTitles.entrySet()) {
            System.out.printf(entry.getKey().replaceAll(".txt", ""));
            percentage = entry.getValue();
            NumberFormat num = NumberFormat.getPercentInstance();
            String cos = num.format(percentage);
            System.out.print("(" + cos + ")" + ", ");


            if (percentage >= 0.6) {
                suspected = new HashMap<String, Double>();
                suspected.put(entry.getKey(), entry.getValue());
            }
        }

        System.out.println();
        if(suspected!=null) {
            System.out.printf("%-30s", "  ");
            System.out.printf("Suspected Texts With Same Authors: ");
            for (Map.Entry<String, Double> entry : suspected.entrySet()) {
                NumberFormat num = NumberFormat.getPercentInstance();
                String cos = num.format(entry.getValue());
                System.out.print(this.getTitle().replaceAll(".txt", "") + " and " + entry.getKey().replaceAll(".txt","")+
                        " may have ths same author"+"("+cos+")");
            }
        }
        System.out.println();
        return "";
    }
}
