package de.musicapp.gui;

import de.musicapp.Main;
import de.musicapp.music.Music;
import de.musicapp.music.PlayList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public record ActionPerformed(JButton skip, JButton add, JButton pause, JButton skipSec, JButton skipSecNeg, JList<Music> list, JButton selected, JButton delete) implements ActionListener {

    private static final JFileChooser chooser = new JFileChooser();

    @Override
    public void actionPerformed(ActionEvent e) {

        PlayList playList = Main.getCurrentPlaylist();

        if (e.getSource() == add) {

            if (chooser.showOpenDialog(null) == 0) {

                String path = chooser.getSelectedFile().getAbsolutePath();
                if (isFileAvailable(path)) {

                    playList.addMusic(new File(path));

                } else {

                    ErrorHandling.getInstance().showError(ErrorHandling.Error.UNSUPPORTED_FILE);
                    System.out.println("ERROR: Unsupported file");

                }
            }
        }

        if (Main.getCurrentPlaylist().getPlaylist().size() != 0) {

            if (e.getSource() == skip) {
                playList.skipMusic();
            }

            if (e.getSource() == pause) {


                if (playList.isPaused()) {
                    playList.getCurrentMusic().play();
                    playList.setPaused(false);
                    pause.setText("Pause");
                } else {
                    playList.getCurrentMusic().stop();
                    playList.setPaused(true);
                    pause.setText("Play");
                }
            }

            if (e.getSource() == skipSec) {
                playList.getCurrentMusic().skipSeconds();
            }

            if (e.getSource() == skipSecNeg) {
                playList.getCurrentMusic().backSeconds();
            }
        }

        if (e.getSource() == selected && isSelected()) {
            Main.getCurrentPlaylist().skipToMusic(list.getSelectedValue());
        }

        if(e.getSource() == delete && isSelected()) {
            Main.getCurrentPlaylist().removeMusic(list.getSelectedValue());
        }
    }

    private boolean isSelected() {

        if(list.getSelectedValue() == null) {

            ErrorHandling.getInstance().showError(ErrorHandling.Error.NO_FILE_SELECTED);

            System.out.println("ERROR: Nothing selected");

            return false;
        }else {
            return true;
        }

    }

    private static final String[] fileExtensions = {

            "wav"

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
