/**
 * The <code>PlayfairEncryptionEngine</code> is the main class
 * and print the menu.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

import java.util.Scanner;

public class PlayfairEncryptionEngine {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter key phrase: ");
        String keyPhrase = input.nextLine();
        KeyTable key = new KeyTable();
//        System.out.println(keyPhrase.length());
        if ((keyPhrase != null && keyPhrase.length()>0) && keyPhrase.matches("\\d*")) {
            key = key.buildFromString("ABCDEFGHIKLMNOPQRSTUVWXYZ");
        } else {
            key = key.buildFromString(keyPhrase);
        }

        int x = 1;
        while (x == 1) {
            /**
             * print the main menu
             */
            System.out.println("(CK) - Change Key");
            System.out.println("(PK) - Print Key");
            System.out.println("(EN) - Encrypt");
            System.out.println("(DE) - Decrypt");
            System.out.println("(Q) - Quit");
            String selection = input.nextLine();
            switch (selection.toUpperCase()) {
                case "CK":
                    System.out.println("Enter the new key phrase: ");
                    keyPhrase = input.nextLine();
                    if (keyPhrase != null && keyPhrase.matches("\\d*")) {
                        key = key.buildFromString("ABCDEFGHIKLMNOPQRSTUVWXYZ");
                    } else {
                        key = key.buildFromString(keyPhrase);
                    }
                    break;
                case "PK":
                    key.toString();
                    break;
                case "EN":
                    System.out.println("Please enter a phrase to encrypt: ");
                    String s = input.nextLine();
                    PhraseQueue bigramQueue = new PhraseQueue();
                    bigramQueue = bigramQueue.buildPhraseFromString(s);
                    bigramQueue.toString(bigramQueue);
                    PhraseQueue e = new PhraseQueue();
                    e = e.encrypt(key, bigramQueue);
                    e.toString(e);
                    break;
                case "DE":
                    System.out.println("Please enter a phrase to decrypt: ");
                    String encryptedString = input.nextLine();
                    PhraseQueue d = new PhraseQueue();
                    d = d.decrypt(key, encryptedString);
                    d.toString(d);
                    break;
                case "Q":
                    return;
            }

        }

    }
}
