/**
 * The <code>Bigram</code> class contains a
 * two characters.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

public class Bigram {
    //member fields
    private char first;
    private char second;

    //constructor
    public Bigram(){
        first = '\u0000';
        second ='\u0000';
    }
    //getter
    public char getFirst() {
        return first;
    }
    //setter
    public void setFirst(char first) {
        this.first = first;
    }
    //getter
    public char getSecond() {
        return second;
    }
    //setter
    public void setSecond(char second) {
        this.second = second;
    }

    public String toString(){
        System.out.printf("%c%c ",first,second);
        return "";
    }

}
