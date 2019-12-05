import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The <code>TextAnalyzer</code>
 * The user should be prompted to enter the
 * directory of a folder on startup
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class TextAnalyzer {
    private FrequencyList frequencyTable;

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Passage> passages = new ArrayList<Passage>();


        Scanner input = new Scanner(System.in);
        System.out.println("Enter the address of StopWords file: ");
        String stopFileAddress = input.nextLine();
        System.out.println("Enter the directory of a folder of text files: ");
        String fileAddress = input.nextLine();

        //parse stopWord file
        File stopFile = new File(stopFileAddress);
        ArrayList<String> stopFileArrayList = new ArrayList<String>();
        Scanner scanner = new Scanner(new FileInputStream(stopFile));
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] words = str.split(" ");
            for (String i : words) {
                i = i.replaceAll("[^a-zA-Z0-9]", "");
                stopFileArrayList.add(i);
            }
        }
        scanner.close();

        //parse other text files
        File[] directoryOfFiles = new File(fileAddress).listFiles();
        for (File file : directoryOfFiles) {
            Passage passage = new Passage(file.getName(), file, stopFileArrayList);
            //add parsed file into ArrayList passages
            passages.add(passage);
        }

        //build frequencyTable
        FrequencyTable frequencyTable = FrequencyTable.buildTable(passages);

        for (Passage p1 : passages) {
            HashMap<String, Double> similarTitles = new HashMap<String, Double>();
            for (Passage p2 : passages) {
                if (!p1.getTitle().equals(p2.getTitle())) {
                    similarTitles.put(p2.getTitle(), Passage.cosineSimilarity(p1, p2, frequencyTable));
                }
            }
            p1.setSimilarTitles(similarTitles);
        }

        System.out.printf("%-30s", "Text (title)");
        System.out.printf("%-30s", "Similarities (%)");
        for(Passage p:passages){
            p.toString();
        }

    }
}
