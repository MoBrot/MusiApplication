package de.musicapp.music;

import de.musicapp.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Music {

    private static HashMap<String, Music> musics = new HashMap<>();

    public static Music getMusic(String name) {
        return musics.get(name);
    }

    private final String name;
    private final File file;
    private final long length;

    private AudioInputStream sound;
    private Clip clip;

    public Music(File file) {

        this.name = file.getName();
        this.file = file;

        try {
            this.sound = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(sound);
            this.clip.stop();
            this.clip.setMicrosecondPosition(0);
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        this.length = clip.getMicrosecondLength();

    }

    public String getName(){
        return this.name;
    }

    public File getFile() {
        return this.file;
    }

    public Clip getClip() {
        return this.clip;
    }

    public void play() {
        nullCheck();
        this.clip.start();
    }

    public void stop() {
        nullCheck();
        this.clip.stop();
    }

    public void skipSeconds() {
        nullCheck();
        this.clip.setMicrosecondPosition(this.clip.getMicrosecondPosition() + Main.skipSeconds * 1000L);
    }

    public void backSeconds() {
        nullCheck();
        this.clip.setMicrosecondPosition(this.clip.getMicrosecondLength() - Main.skipSeconds * 1000L);
    }

    public void restart() {
        this.clip.setFramePosition(0);
        play();
    }

    private void nullCheck() {
        if(this.sound == null || this.clip == null || !this.file.exists())
            Main.getCurrentPlaylist().skipMusic();

    }

}
