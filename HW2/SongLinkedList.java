/**
 * The <code>SongLinkedList</code> class contains a
 * linked list of <code>SongNode</code> objects.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SongLinkedList {
    //member
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;
    private Clip c;
    private boolean play = false;

    //constructor
    public SongLinkedList() {
        head = tail = cursor = null;
        size = 0;
    }

    public SongLinkedList(SongNode head, SongNode tail, SongNode cursor, int size) {
    }


    //==============getters and setters==========================//
    public SongNode getHead() {
        return head;
    }

    public void setHead(SongNode newHead) {
        head = newHead;
    }

    public SongNode getTail() {
        return tail;
    }

    public void setTail(SongNode newTail) {
        tail = newTail;
    }

    public SongNode getCursor() {
        return cursor;
    }

    public void setCursor(SongNode newCursor) {
        cursor = newCursor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        size = size;
    }


    /**
     * This method is to find a song in the playlist
     * based on the name of the song.
     * @param name the name of song the user is looking for
     * @return The song the user is looking for.
     */
    public Song searchSong(String name) {
        SongNode current = head;
        while (current != null) {
            if (current.getData().getName().equals(name)) {
                return current.getData();
            }
            current=current.getNext();
        }
        return null;
    }

    /**
     * This method is to play the song.
     * @param name the name of song the user will play
     * @throws IllegalArgumentException
     */
    public void play(String name) throws IllegalArgumentException {
        try {
            if (searchSong(name) != null) {
                AudioInputStream AIS = AudioSystem.getAudioInputStream(
                        new File(name+".wav"));
                if(play){
                    c.stop();
                    play = false;
                }
                c = AudioSystem.getClip();
                c.open(AIS);
                c.start();
                play = true;
            }
        } catch (Exception e) {
            System.out.println("Not Found this Song.");
        }
    }
    /**
     * This method is to move the cursor forward in the playlist.
     *
     */
    public void cursorForwards() {
        if (cursor != null && cursor != tail)
            cursor = cursor.getNext();
    }
    /**
     * This method is to move the cursor backward in the playlist.
     */
    public void cursorBackwards() {
        if (cursor != null && cursor != head)
            cursor = cursor.getPrev();
    }

    /**
     * This method is to insert the node after the cursor in the playlist.
     * @param newSong the song will be added to the playlist
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException {
        try {
            SongNode newNode;
            //playlist is not empty
            if (cursor != null) {
                //cursor at tail
                if (cursor == tail) {
                    newNode=new SongNode(cursor,cursor.getNext(),newSong);
                    tail.setNext(newNode);
                    newNode.setPrev(tail);
                    tail =newNode;
                    tail.setNext(null);
                    cursor=tail;
                }
                //cursor at the middle or head
                else {
                    newNode=new SongNode(cursor,cursor.getNext(),newSong);
                    newNode.setNext(cursor.getNext());
                    cursor.setNext(newNode);
                    newNode.setPrev(cursor);
                    newNode.getNext().setPrev(newNode);
                    cursor=newNode;
                }
            }
            //playlist is empty
            if(cursor==null) {
                newNode=new SongNode(null,null,newSong);
                head = newNode;
                tail =newNode;
                cursor = newNode;
            }
            size++;
        } catch (Exception e) {
        }

    }

    /**
     * This method is to remove the cursor node in the playlist.
     * @return the song will be removed
     */
    public Song removeCursor() {
        try {
            Song removedSong = cursor.getData();
            if (cursor != null && cursor == head) {
                System.out.println("head");
                head = head.getNext();
                cursor = head;
            } else if (cursor == tail) {
                /***
                System.out.println("tail");

                cursor = tail.getPrev();
                cursor.setNext(null);
                tail = cursor;
                 */
                tail=tail.getPrev();
                tail.setNext(null);
                cursor=tail;


            } else {
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                cursor = cursor.getNext();
            }
            size--;
            return removedSong;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    /**
     * This method is to get a random node in the playlist.
     */

    public SongNode getRandomNode() {
        int index = (int)(Math.random()* (size));//0-(size-1)
//        index = 1;

        SongNode current =head;

//            while(index>1){
//                current=current.getNext();
//                index--;
//            }
//            return current;
//        }
        /*int count = 0;
        while (count < index) {

            System.out.println(count);
            current = current.getNext();
            count++;
        }*/
        for(int i = 0; i< index; i++){
            current = current.getNext();
        }
        //ASystem.out.println(current);

        return current;
    }

    /**
     * This method is to play a random song in the playlist.
     */
    public Song random() {
        SongNode randomNode = getRandomNode();
        if (randomNode != null) {
            try {
                System.out.println(randomNode.getData());
                play(randomNode.getData().getName());
                System.out.println(randomNode.getData().getName() + " is playing.");
            } catch (Exception e) {
//                e.printStackTrace();
            }
        } else
            System.out.println("No songs to play.");
        return randomNode.getData();
    }

    /**
     * This method is a helper method to insert the random song from playlist to
     * new shuffle list.
     * @param newNode random song from playlist then will be added to new playlist
     */
    public void insertToNewPlaylist(SongNode newNode,SongLinkedList shuffledPlaylist){
        //if new playlist is empty
        if(tail==null){
            newNode.setNext(null);
            newNode.setPrev(null);
            head=tail=newNode;
        }
        //new playlist is not empty and newNode to the end
        else{
            tail.setNext(newNode);
            newNode.setPrev(tail);
            newNode.setNext(null);
            tail=newNode;
        }
        shuffledPlaylist.size++;
    }

    /**
     * This method is to shuffle the playlist.
     */
    public void shuffle() {
        SongLinkedList shufflePlaylist=new SongLinkedList();
        shufflePlaylist.size=0;
        SongNode cursorInNewPlaylist=new SongNode();
        cursorInNewPlaylist.setNext(cursor.getNext());
//        if(current !=null){
        while(size>0) {
//            SongNode randomNode = getRandomNode();
            SongNode randomNode = new SongNode();
            SongNode deleteNode = getRandomNode();

            randomNode.setData(deleteNode.getData());
            cursor = deleteNode;
            shufflePlaylist.insertToNewPlaylist(randomNode,shufflePlaylist);
            removeCursor();
//        if(current !=null){
        }
        head=shufflePlaylist.getHead();
        tail=shufflePlaylist.getTail();
        cursor=cursorInNewPlaylist;
        size =shufflePlaylist.size;
    }

    public void printPlaylist() {
        System.out.print(toString());
    }

    public void deleteAll() {
        head = tail = cursor = null;
        size = 0;
    }
    /**
     * This method is to print the playlist.
     */
    public String toString() {
        System.out.printf("%-30s", "Song");
        System.out.printf("%-30s", "Artist");
        System.out.printf("%-30s", "Album");
        System.out.printf("%-3s", "Length(s)");
        System.out.println();
        System.out.println("-------------------------------------------------" +
                "-------------------------------------------------");
        String song, artist, album;
        int length;
        SongNode current = head;
        while (current != null) {
            Song data = current.getData();
            song = data.getName();
            artist = data.getArtist();
            album = data.getAlbum();
            length = data.getLength();
            System.out.printf("%-30s", song);
            System.out.printf("%-30s", artist);
            System.out.printf("%-30s", album);
            System.out.printf("%-3s", length);
            if (current == cursor) System.out.println("<-");
            else System.out.println();
            current = current.getNext();
        }
        return "";
    }
}
