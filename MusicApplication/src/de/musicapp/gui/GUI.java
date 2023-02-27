package de.musicapp.gui;

import de.musicapp.Main;
import de.musicapp.music.Music;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    private final JFrame frame = new JFrame();

    private final int sizeX = 700;
    private final int sizeY = 550;

    private final int buttonSizeX = 100;
    private final int buttonSizeY = 50;

    private final int largeButtonSizeX = 150;
    private final int largeButtonSizeY = buttonSizeY;

    private final int listSizeX = (int) (sizeX / 2.5);
    private final int listSizeY = 300;

    private final JButton addMusicButton = new JButton("Add music");

    private final JButton pauseButton = new JButton("Play");
    private final JButton skipSeconds = new JButton("Skip " + Main.skipSeconds + "seconds");
    private final JButton skipSecondsNegative = new JButton("Go back " + Main.skipSeconds + "seconds");
    private final JButton skipMusicButton = new JButton("Skip current music");

    public static JList<Music> playlistUI = new JList<>();
    public static DefaultListModel<Music> model = new DefaultListModel<>();
    private final JButton skipToSelectedButton = new JButton("Skip to selected");
    private final JButton deleteSelectedButton = new JButton("Remove selected");

    private final JLabel errorLabel = new JLabel("TEST 123");

    public GUI() {

        new ErrorHandling(errorLabel);

        frame.setLayout(null);
        frame.setSize(sizeX, sizeY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ImageIcon imageIcon = new ImageIcon("./MusicApplication/src/de/musicapp/gui/image.png");
        frame.setIconImage(imageIcon.getImage());

        ActionPerformed actionPerformed = new ActionPerformed(
                skipMusicButton, addMusicButton, pauseButton, skipSeconds, skipSecondsNegative, playlistUI, skipToSelectedButton, deleteSelectedButton);
        List<JButton> buttons = new ArrayList();

        buttons.add(skipMusicButton);
        buttons.add(addMusicButton);
        buttons.add(pauseButton);
        buttons.add(skipSecondsNegative);
        buttons.add(skipSeconds);
        buttons.add(skipToSelectedButton);
        buttons.add(deleteSelectedButton);

        for (JButton button : buttons) {

            button.setVisible(true);
            button.addActionListener(actionPerformed);
            button.setSize(buttonSizeX, buttonSizeY);
            frame.add(button);

        }

        // Positioning off the buttons
        addMusicButton.setLocation(10, 10);

        pauseButton.setLocation((sizeX / 2) - (buttonSizeX / 2), sizeY - (3 * buttonSizeY) - 10);

        skipSeconds.setLocation(pauseButton.getX() + 5 + buttonSizeX, pauseButton.getY());
        skipSecondsNegative.setLocation(pauseButton.getX() - 5 - largeButtonSizeX, pauseButton.getY());

        skipMusicButton.setLocation(pauseButton.getX() - buttonSizeX / 3, pauseButton.getY() + 5 + buttonSizeY);

        // Noticed that the buttons are too small for there texts
        skipSecondsNegative.setSize(largeButtonSizeX, largeButtonSizeY);
        skipSeconds.setSize(skipSecondsNegative.getSize());
        skipMusicButton.setSize(skipSeconds.getSize());

        errorLabel.setBounds(30, pauseButton.getY() - buttonSizeY, 300, buttonSizeY);
        errorLabel.setForeground(Color.RED);

        errorLabel.setVisible(false);

        frame.add(errorLabel);

        // Listing of all the musics
        playlistUI.setModel(model);
        playlistUI.setBounds(sizeX / 2, 10, listSizeX, listSizeY);

        skipToSelectedButton.setBounds(playlistUI.getX() - 2, playlistUI.getY() + listSizeY + 5, listSizeX / 2, buttonSizeY);
        deleteSelectedButton.setBounds(playlistUI.getX() + listSizeX / 2 + 2, skipToSelectedButton.getY(), skipToSelectedButton.getWidth(), skipToSelectedButton.getHeight());

        frame.add(skipToSelectedButton);
        frame.add(playlistUI);
    }
}
