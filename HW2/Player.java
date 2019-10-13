/**
 * The <code>Player</code> class uses a linked
 * list of <code>SongLinkedList</code>.
 *
 * @author Fengwei Zhang
 * e-mail: fengwei.zhang@stonybrook.edu
 * Stony Brook ID: 111252554
 * R04
 */

import java.util.Scanner;


public class Player {
    public static void main(String[] args) {
        SongLinkedList playerList = new SongLinkedList();
        int x = 2;
        while (x > 1) {

            System.out.println();
            System.out.println("Menu:");
            System.out.println("(A)  Add Song to playlist");
            System.out.println("(F)  Go to next song");
            System.out.println("(B)  Go to previous song");
            System.out.println("(R)  Remove song from playlist");
            System.out.println("(L)  Play a song");
            System.out.println("(C)  Clear the playlist");
            System.out.println("(S)  Shuffle playlist");
            System.out.println("(Z)  Random song");
            System.out.println("(P)  Print playlist");
            System.out.println("(T)  Get size");
            System.out.println("(Q)  Quit");
            System.out.println();
            System.out.print("Enter an option:");
            Scanner input = new Scanner(System.in);
            String selection = input.nextLine();

            switch (selection.toUpperCase()) {
                case "A":
                    try {
                        System.out.print("Enter song title:");
                        String songTitle = input.nextLine();
                        System.out.print("Enter artist of the song: ");
                        String artist = input.nextLine();
                        System.out.print("Enter album:");
                        String album = input.nextLine();
                        System.out.print("Enter length(in seconds):");
                        int length = input.nextInt();
                        playerList.insertAfterCursor(new Song(songTitle, artist, album, length));
                        System.out.println(songTitle + " by " + artist + " is added to your playlist.");
                    } catch (Exception e) {
                        System.out.println("Your song information input is not correct.");
                    }
                    break;
                case "F":
                    if (playerList.getTail() == playerList.getCursor())
                        System.out.println("Already at the last song.");
                    else {
                        playerList.cursorForwards();
                        System.out.println("Cursor is moved to the next song.");
                    }
                    break;
                case "B":
                    if (playerList.getHead() == playerList.getCursor())
                        System.out.println("Already at the first song.");
                    else {
                        playerList.cursorBackwards();
                        System.out.println("Cursor is moved to the previous song.");
                    }
                    break;
                case "R":
                    try {
                        Song removedSong = playerList.removeCursor();
                        System.out.println(removedSong.getName() + " has been removed.");
                    } catch (Exception e) {
                    }
                    break;
                case "L":
                    try {
                        System.out.print("Enter the name of song to play:");
                        String songTitle1 = input.nextLine();
                        playerList.play(songTitle1);
                        System.out.println(songTitle1 + " is now playing.");
                    } catch (Exception e) {
                        System.out.println("inside exception LLLLLLLLLLLLLL");
                    }
                    break;
                case "C":
                    playerList.deleteAll();
                    System.out.println("Playlist cleared.");
                    break;
                case "S":
                    playerList.shuffle();
                    System.out.println("Playlist shuffled.");
                    break;
                case "Z":
                    System.out.println("Playing a random song from your playlist...");
                    playerList.random();
                    break;
                case "P":
                    playerList.printPlaylist();
                    break;
                case "T":
                    int size = playerList.getSize();
                    if (size == 0)
                        System.out.println("Your playlist is empty.");
                    else
                        System.out.println("There are " + size + " songs in your playlist.");
                    break;
                case "Q":
                    System.out.println("Program terminated.");
                    return;
            }
        }

    }
}
