/**
 * The <code>SongNode</code> class contains
 * <code>Song</code> objects and links of node
 * which are prev and next.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class SongNode {
    //members
    private SongNode prev;
    private SongNode next;
    private Song data;

    //======================Constructors=================//
    public SongNode() {
        prev = next = null;
        data = null;
    }

    public SongNode(SongNode newPrev, SongNode newNext, Song newData) {
        prev = newPrev;
        next = newNext;
        data = newData;
    }
    //======================getter====================//
    public SongNode getPrev() {
        return prev;
    }

    public SongNode getNext() {
        return next;
    }

    public Song getData() {
        return data;
    }

    //==============setter==========================//
    public void setPrev(SongNode newPrev) {
        prev = newPrev;
    }

    public void setNext(SongNode newNext) {
        next = newNext;
    }

    public void setData(Song newData) {
        data = newData;
    }
}
