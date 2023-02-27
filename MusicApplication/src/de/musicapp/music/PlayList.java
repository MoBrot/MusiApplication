package de.musicapp.music;

import de.musicapp.gui.ErrorHandling;
import de.musicapp.gui.GUI;

import java.io.File;
import java.util.ArrayList;

public class PlayList {

    private final ArrayList<Music> playlist = new ArrayList<>();
    private int id = 0;

    private boolean paused = true;

    public void addMusic(File file) {
        if(Music.doesMusicExist(file.getName())) {
            ErrorHandling.getInstance().showError(ErrorHandling.Error.MUSIC_NOT_AVAILABLE);
            return;
        }

        Music music = new Music(file, playlist.size());
        playlist.add(music);
        GUI.model.addElement(music);
    }

    public void removeMusic(Music music) {
        playlist.remove(music);
        GUI.model.removeElement(music);
    }

    public void skipMusic() {
        if(this.id + 1 <= playlist.size())
            return;

        skipping(this.id + 1);
    }

    public void skipToMusic(Music music) {
        skipping(music.getId());
    }

    private void skipping(int id) {
        getCurrentMusic().stop();
        this.id = id;
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
