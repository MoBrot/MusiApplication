package de.musicapp;

import de.musicapp.gui.GUI;
import de.musicapp.music.PlayList;

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
