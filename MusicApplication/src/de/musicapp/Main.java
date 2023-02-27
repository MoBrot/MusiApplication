package de.musicapp;

import de.musicapp.gui.GUI;
import de.musicapp.music.PlayList;

import java.io.File;
import java.util.Arrays;

/* TODO
        - remove button
        - skip to song
        - error label
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

    private static void sortAll(String dirName) {
        File directory = new File(dirName);

        File[] filesArray = directory.listFiles();

        //sort all files
        Arrays.sort(filesArray);

        //print the sorted values
        for (File file : filesArray) {
            if (file.isFile()) {
                System.out.println("File : " + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("Directory : " + file.getName());
            } else {
                System.out.println("Unknown : " + file.getName());
            }
        }
    }
}
