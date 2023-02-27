package de.musicapp.music;

import de.musicapp.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Music {

    private static final HashMap<String, Music> musicsNAME = new HashMap<>();

    public static Music getMusic(String name) {
        return musicsNAME.get(name);
    }

    public static boolean doesMusicExist(String name) {
        return musicsNAME.containsKey(name);
    }

    private final String name;
    private final File file;
    private final long length;
    private final int id;

    private AudioInputStream sound;
    private Clip clip;

    public Music(File file, int id) {

        this.name = file.getName();
        this.file = file;
        this.id = id;

        System.out.println(file);

        try {
            this.sound = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(sound);
            this.clip.stop();
            this.clip.setMicrosecondPosition(0);
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace(); //TODO show error
        }

        this.length = clip.getMicrosecondLength();

        musicsNAME.put(this.name, this);

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

    public long getLength() {
        return this.length;
    }

    public int getId() {
        return id;
    }

    public void play() {
        nullCheck();
        this.clip.start();
        logActionInConsole(MusicAction.PLAY);
    }

    public void stop() {
        nullCheck();
        this.clip.stop();
        logActionInConsole(MusicAction.STOP);
    }

    public void skipSeconds() {
        nullCheck();
        this.clip.setMicrosecondPosition(this.clip.getMicrosecondPosition() + Main.skipSeconds * 1000L);
        logActionInConsole(MusicAction.SKIP);
    }

    public void backSeconds() {
        nullCheck();
        this.clip.setMicrosecondPosition(this.clip.getMicrosecondLength() - Main.skipSeconds * 1000L);
        logActionInConsole(MusicAction.BACK);
    }

    public void restart() {
        logActionInConsole(MusicAction.RESTART);
        this.clip.setFramePosition(0);
        play();
    }

    private void nullCheck() {
        if(this.sound == null || this.clip == null || !this.file.exists()) {
            Main.getCurrentPlaylist().skipMusic();
            logActionInConsole(MusicAction.NULLCHECK);
        }
    }
    
    private void logActionInConsole(MusicAction action) {
        System.out.println(this.name + " " + action.getActionName());
    }

    @Override
    public String toString() {
        return this.name;
    }
}
