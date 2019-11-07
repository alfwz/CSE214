/**
 * The <code>KeyTable</code> represents the key to a Playfair Cipher.
 * The KeyTable class contains a two-dimensional array of
 * char variables.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

public class KeyTable {
    //member field
    private char[][] key;
    //constructor
    public KeyTable() {
    }
    //getter
    public char[][] getKeyTable() {
        return key;
    }
    //setter
    public void setKeyTable(char[][] key5by5) {
        key = key5by5;
    }
    /**
     * find the row location of character c
     *
     * @param c
     * @return row location
     * @exception IllegalArgumentException
     */
    public int findRow(char c) throws IllegalArgumentException {
        if(Character.isLetter(c)==false){throw new IllegalArgumentException("c is not a valid letter in the key matrix");}
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == c) {
                    return i;
                }
            }
        }
        return 0;
    }
    /**
     * find the column location of character c
     *
     * @param c
     * @return column location
     * @exception IllegalArgumentException
     */
    public int findCol(char c) throws IllegalArgumentException {
        if(Character.isLetter(c)==false){throw new IllegalArgumentException("c is not a valid letter in the key matrix");}
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == c) {
                    return j;
                }
            }
        }
        return 0;
    }

    /**
     * Build the key matrix from user input
     *
     * @param keyphrase
     * @return key matrix build from keyphrase
     * @throws IllegalArgumentException
     */
    public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException {
            if (keyphrase==null || keyphrase.length()==0) {
                throw new IllegalArgumentException("Keyphrase you input is empty");
            }
            keyphrase = keyphrase.replaceAll(" ", "");
            keyphrase = keyphrase.toUpperCase();
            //remove repeated letter

            char[] keyphraseArray = keyphrase.toCharArray();
            for (int index = 0; index < keyphraseArray.length; index++) {
                for (int i = 0; i < index; i++) {
                    if (keyphraseArray[index] == keyphraseArray[i]) {
                        keyphraseArray[index] = 0;
                    } else {
                        keyphraseArray[index] = keyphraseArray[index];
                    }
                }
            }
            keyphrase = new String(keyphraseArray);
            System.out.println(keyphrase);
            keyphrase = keyphrase.replaceAll("\\u0000", "");
            keyphraseArray = keyphrase.toCharArray();

            //create 1d key table
            char[] alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ".toCharArray();
            char[] key1d = new char[25];
            for (int i = 0; i < keyphraseArray.length; i++) {
                key1d[i] = keyphraseArray[i];
            }
            for (int k = 0; k < 25 - keyphraseArray.length; k++) {  //key1d
                for (int i = 0; i < 25; i++) { //element of alphabet
                    String keyphraseArrayToString = new String(keyphraseArray);
                    String alphabetiToString = Character.toString(alphabet[i]);
                    if (keyphraseArrayToString.contains(alphabetiToString)) {
                    } else {
                        key1d[keyphraseArray.length + k] = alphabet[i];
                        k++;
                    }
                }
            }
            System.out.println(key1d);

            //convert 1d key table to 2d
            KeyTable key = new KeyTable();
            char[][] key5by5 = new char[5][5];
            int index = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    key5by5[i][j] = key1d[index++];
                    key.setKeyTable(key5by5);
                }
            }
            return key;


    }


    public String toString() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(key[i][j]+"  ");
            }
            System.out.println();
        }
        return "";
    }

}
