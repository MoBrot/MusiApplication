package de.musicapp.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    private final JFrame frame = new JFrame();

    private final int sizeX = 700;
    private final int sizeY = 500;

    private final int buttonSizeX = 100;
    private final int getButtonSizeY = 50;

    private final JButton skipMusicButton = new JButton();
    private final JButton addMusicButton = new JButton();

    private final JButton pauseButton = new JButton();
    private final JButton skipSeconds = new JButton();
    private final JButton skipSecondsNegative = new JButton();

    public GUI() {

        frame.setLayout(null);
        frame.setSize(sizeX, sizeY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);



        ActionPerformed actionPerformed = new ActionPerformed(skipMusicButton, addMusicButton, pauseButton, skipSeconds, skipSecondsNegative);
        List<JButton> buttons = new ArrayList();

        buttons.add(skipMusicButton);
        buttons.add(addMusicButton);
        buttons.add(pauseButton);
        buttons.add(skipSecondsNegative);
        buttons.add(skipSeconds);

        for (JButton button : buttons) {

            button.addActionListener(actionPerformed);
            button.setSize(buttonSizeX, getButtonSizeY);
            frame.add(button);
            button.setVisible(true);

        }



    }

}
