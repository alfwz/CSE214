/**
 * The <code>Song</code> class is one the
 * elements of <code>SongNode</code>.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */
public class Song {
    private String name;
    private String artist;
    private String album;
    private int length;

    //======================Constructors=================//
    public Song() {
        name = artist = album = "";
        length = 0;
    }

    public Song(String newName, String newArtist, String newAlbum, int newLength) {
        name=newName;
        artist=newArtist;
        album=newAlbum;
        length=newLength;
    }

    //======================getter====================//
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getLength() {
        return length;
    }

    //==============setter==========================//
    public void setName(String name) {
        this.name = name;
    }

    public void setArtist() {
        this.artist = artist;
    }

    public void setAlbum() {
        this.album = album;
    }

    public void setLength() {
        this.length = length;
    }
}
