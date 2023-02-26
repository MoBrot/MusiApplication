package de.musicapp.gui;

import de.musicapp.Main;
import de.musicapp.music.Music;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public record ActionPerformed(JButton skip, JButton add, JButton pause, JButton skipSec, JButton skipSecNeg) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add) {

            JFileChooser chooser = new JFileChooser();

            if(chooser.showOpenDialog(null) == 0) {

                String path = chooser.getSelectedFile().getAbsolutePath();
                if(isFileAvailable(path)) {

                    Main.getCurrentPlaylist().addMusic(new Music(new File(path)));

                }else {

                    /// TODO - error box

                }
            }
        }

    }

    private static String[] fileExtensions = {

            "mp3",
            "wave"

    };

    public boolean isFileAvailable(String path) {

        for (String extension : fileExtensions) {

            if(getFileExtension(path).equals(extension))
                return true;

        }

        return false;
    }

    public String getFileExtension(String fullName) {

        if(fullName == null)
            return null;

        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
