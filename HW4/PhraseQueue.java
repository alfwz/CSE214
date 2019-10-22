/**
 * The <code>PhraseQueue</code> class extend to LinkedList
 * and implements queue.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */


import java.util.*;


public class PhraseQueue extends LinkedList<Bigram> implements Queue<Bigram> {
    /**
     * Separate string s to bigrams and enqueue into phrase queue
     *
     * @param s
     * @return phrase queue build from string s
     */
    public static PhraseQueue buildPhraseFromString(String s) {
        PhraseQueue bigramQueue = new PhraseQueue();
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(s);
        s = s.toUpperCase();
        s = s.replaceAll("J", "I");
        while (!s.equals(null)) {
            Bigram b = new Bigram();
            if (s.length() >= 2) {
                if (s.charAt(0) == s.charAt(1)) {
                    b.setFirst(s.charAt(0));
                    b.setSecond('X');
                    bigramQueue.add(b);
                    s = s.substring(1);
                } else {
                    b.setFirst(s.charAt(0));
                    b.setSecond(s.charAt(1));
                    bigramQueue.add(b);
                    s = s.substring(2);
                }
            } else if (s.length() == 1) {
                s = s.concat("X");
                b.setFirst(s.charAt(0));
                b.setSecond('X');
                bigramQueue.add(b);
                s = "";
            } else {
                break;
            }
        }
        return bigramQueue;
    }

    /**
     * encrypt
     *
     * @param keyTable, p
     * @return encrypted phrase queue
     * @throws IllegalArgumentException
     */
    public PhraseQueue encrypt(KeyTable keyTable, PhraseQueue p) throws IllegalArgumentException {
        if (keyTable == null) {
            throw new IllegalArgumentException("key is null");
        }
        PhraseQueue e = new PhraseQueue();
        while (p.isEmpty() == false) {
            //same column
            Bigram b = new Bigram();
            if (keyTable.findCol(((p.peek())).getFirst()) == keyTable.findCol(((Bigram) (p.peek())).getSecond())) {
                //first letter
                if (keyTable.findRow(((Bigram) (p.peek())).getFirst()) == 4) {
                    b.setFirst(keyTable.getKeyTable()[0][keyTable.findCol(((Bigram) (p.peek())).getFirst())]);
                } else {
                    b.setFirst(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (p.peek())).getFirst()) + 1][keyTable.findCol(((Bigram) (p.peek())).getFirst())]);
                }
                //second letter
                if (keyTable.findRow(p.peek().getSecond()) == 4) {
                    b.setSecond(keyTable.getKeyTable()[0][keyTable.findCol(p.peek().getSecond())]);
                } else {
                    b.setSecond(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (p.peek())).getSecond()) + 1][keyTable.findCol(((Bigram) (p.peek())).getSecond())]);
                }
                p.remove();
                e.add(b);
                //同一行row
            } else if (keyTable.findRow(((Bigram) (p.peek())).getFirst()) == keyTable.findRow(((Bigram) (p.peek())).getSecond())) {
                //first letter
                if (keyTable.findCol(((Bigram) (p.peek())).getFirst()) == 4) {
                    b.setFirst(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (p.peek())).getFirst())][0]);
                } else {
                    b.setFirst(keyTable.getKeyTable()[keyTable.findRow(p.peek().getFirst())][keyTable.findCol(((Bigram) (p.peek())).getFirst()) + 1]);
                }
                //second letter
                if (keyTable.findCol(p.peek().getSecond()) == 4) {
                    b.setSecond(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (p.peek())).getSecond())][0]);
                } else {
                    b.setSecond(keyTable.getKeyTable()[keyTable.findRow(p.peek().getSecond())][keyTable.findCol(p.peek().getSecond()) + 1]);
                }
                p.remove();
                e.add(b);
                //rectangle
            } else {
                //first letter
                b.setSecond(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (p.peek())).getSecond())][keyTable.findCol(((Bigram) (p.peek())).getFirst())]);
                //second letter
                b.setFirst(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (p.peek())).getFirst())][keyTable.findCol(((Bigram) (p.peek())).getSecond())]);
                p.remove();
                e.add(b);
            }
        }
        return e;
    }

    /**
     * decrypt
     *
     * @param keyTable, p
     * @return decrypted phrase queue
     * @throws IllegalArgumentException
     */
    public PhraseQueue decrypt(KeyTable keyTable, String encryptedString) {
        if (keyTable == null) {
            throw new IllegalArgumentException("key is null");
        }
        PhraseQueue e = new PhraseQueue();
        PhraseQueue d = new PhraseQueue();
        e = e.buildPhraseFromString(encryptedString);
        while (e.isEmpty() == false) {
            //same column
            Bigram b = new Bigram();
            if (keyTable.findCol(((e.peek())).getFirst()) == keyTable.findCol(e.peek().getSecond())) {
                //first letter
                if (keyTable.findRow(((Bigram) (e.peek())).getFirst()) == 0) {
                    b.setFirst(keyTable.getKeyTable()[4][keyTable.findCol(((Bigram) (e.peek())).getFirst())]);
                } else {
                    b.setFirst(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (e.peek())).getFirst()) - 1][keyTable.findCol(((Bigram) (e.peek())).getFirst())]);
                }
                //second letter
                if (keyTable.findRow(e.peek().getSecond()) == 0) {
                    b.setSecond(keyTable.getKeyTable()[4][keyTable.findCol(e.peek().getSecond())]);
                } else {
                    b.setSecond(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (e.peek())).getSecond()) - 1][keyTable.findCol(((Bigram) (e.peek())).getSecond())]);
                }
                e.remove();
                d.add(b);
                //同一行row
            } else if (keyTable.findRow(((Bigram) (e.peek())).getFirst()) == keyTable.findRow(((Bigram) (e.peek())).getSecond())) {
                //first letter
                if (keyTable.findCol(((Bigram) (e.peek())).getFirst()) == 0) {
                    b.setFirst(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (e.peek())).getFirst())][4]);
                } else {
                    b.setFirst(keyTable.getKeyTable()[keyTable.findRow(e.peek().getFirst())][keyTable.findCol(((Bigram) (e.peek())).getFirst()) - 1]);
                }
                //second letter
                if (keyTable.findCol(e.peek().getSecond()) == 0) {
                    b.setSecond(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (e.peek())).getSecond())][4]);
                } else {
                    b.setSecond(keyTable.getKeyTable()[keyTable.findRow(e.peek().getSecond())][keyTable.findCol(e.peek().getSecond()) - 1]);
                }
                e.remove();
                d.add(b);
                //rectangle
            } else {
                //first letter
                b.setSecond(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (e.peek())).getSecond())][keyTable.findCol(((Bigram) (e.peek())).getFirst())]);
                //second letter
                b.setFirst(keyTable.getKeyTable()[keyTable.findRow(((Bigram) (e.peek())).getFirst())][keyTable.findCol(((Bigram) (e.peek())).getSecond())]);
                e.remove();
                d.add(b);
            }
        }
        return d;
    }

    public String toString(PhraseQueue queue) {
        PhraseQueue tem = new PhraseQueue();
        while (queue.isEmpty() == false) {
            System.out.print(queue.peek().toString());
            tem.add(queue.peek());
            queue.remove();
        }
        while (tem.isEmpty() == false) {
            queue.add(tem.peek());
            tem.remove();
        }

        System.out.println();

//        int size1= queue.size();
//        for(int i=0; i<size1; i++){
//            System.out.print(queue.peek().toString());
//            queue.remove();
//        }
//        System.out.println("This is phrase queue");
        return "";
    }
}
