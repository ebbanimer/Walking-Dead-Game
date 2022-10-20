package com.dt181g.project;

import com.dt181g.project.controller.StartController;

import javax.swing.*;

/**
 * Start of project. Initializing program on EDT.
 * @author Ebba Nimér
 */
public class Project {

    private Project() { }

    /**
     * Start start-controller on EDT.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartController::new);
    }
}
