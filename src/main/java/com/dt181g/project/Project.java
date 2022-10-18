package com.dt181g.project;

import com.dt181g.project.controller.StartController;

public class Project {

    private Project() { }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(StartController::new);
    }
}
