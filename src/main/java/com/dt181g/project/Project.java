package com.dt181g.project;

import com.dt181g.project.controller.StartController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Start of project. Initializing program on EDT.
 * @author Ebba Nimér
 */
public class Project {

    private Project() { }

    /**
     * Start start-controller on EDT.
     * @param args args
     */
    public static void main(String[] args) throws IOException {
        //SwingUtilities.invokeLater(StartController::new);    // Start program on EDT

        SwingUtilities.invokeLater(() -> {
            try {
                new StartController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
