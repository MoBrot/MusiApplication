package de.musicapp.music;

import java.util.ArrayList;

public class PlayList {

    private final ArrayList<Music> playlist = new ArrayList<>();

    public void addMusic(Music music) {
        playlist.add(music);
    }

    public void removeMusic(Music music) {
        playlist.remove(music);
    }

    public void skipMusic() {

    }

    public ArrayList<Music> getPlaylist() {
        return playlist;
    }

    public Music getCurrentMusic() {
        return null;
    }
}
