package de.musicapp.music;

import de.musicapp.gui.GUI;

import java.io.File;
import java.util.ArrayList;

public class PlayList {

    private final ArrayList<Music> playlist = new ArrayList<>();
    private int id = 0;

    private boolean paused = false;

    public void addMusic(File file) {
        if(Music.doesMusicExist(file.getName()))
            return; // TODO error message

        Music music = new Music(file, playlist.size());
        playlist.add(music);
        GUI.model.addElement(music);
    }

    public void removeMusic(Music music) {
        playlist.remove(music);
        GUI.model.removeElement(music);
    }

    public void skipMusic() {
        id++;
        skipping();
    }

    public void skipToMusic(Music music) {
        id = music.getId();
        skipping();
    }

    private void skipping() {
        getCurrentMusic().stop();
        playlist.get(id).restart();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean flag) {
        this.paused = flag;
    }

    public ArrayList<Music> getPlaylist() {
        return playlist;
    }

    public Music getCurrentMusic() {
        return playlist.get(id);
    }
}
