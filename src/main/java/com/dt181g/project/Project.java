package com.dt181g.project;

import com.dt181g.project.controller.Controller;

public class Project {

    private Project() { }

    public static void main(String[] args) {

        new Controller();

        /*EventQueue.invokeLater(() -> {

            var ex = new Project();
            ex.setVisible(true);
        });*/
    }

    /*public Project() {

        initUI();
    }

    private void initUI() {

        add(new TestGame());

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }*/
}
