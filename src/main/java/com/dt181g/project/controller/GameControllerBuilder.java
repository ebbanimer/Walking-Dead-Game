package com.dt181g.project.controller;

import com.dt181g.project.model.Model;
import com.dt181g.project.model.characters.Character;

public class GameControllerBuilder {
    private Character newCharacter;
    private Model model;

    public GameControllerBuilder setNewCharacter(Character newCharacter) {
        this.newCharacter = newCharacter;
        return this;
    }

    public GameControllerBuilder setModel(Model model) {
        this.model = model;
        return this;
    }

    public GameController createGameController() throws InterruptedException {
        return new GameController(newCharacter, model);
    }
}