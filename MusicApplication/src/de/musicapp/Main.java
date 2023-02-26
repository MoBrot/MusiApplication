package de.musicapp;

import de.musicapp.gui.GUI;
import de.musicapp.music.PlayList;

/* TODO - show up all musics in the playlist in GUI
        - remove button
        - skip to song
 */

public class Main {

    public static void main(String[] args) {

        new GUI();

    }

    private static final PlayList playList = new PlayList();

    public static int skipSeconds = 10;

    public static PlayList getCurrentPlaylist() {
        return playList;
    }
}
