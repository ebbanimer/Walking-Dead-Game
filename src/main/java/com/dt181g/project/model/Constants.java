package com.dt181g.project.model;

public interface Constants {
    int ICON_HEIGHT = 60;
    int ICON_WIDTH = 60;
    int WIDTH = 700;
    int HEIGHT = 550;

    String DEAD_START_VIEW = " already got bit in a previous round, try another character!";

    String DEAD = "<html>Sorry, character got bit and is dead! <br>" +
            "Better luck next time. </html>";
    String ERROR = "Invalid character! Please choose a character before going ahead...";

    String INSTRUCTION_MESSAGE = "<html>Collect as much food as you can<br> without getting caught by the zombies.<br> " +
            "You have 30 seconds on you, and for each<br> food you collect, you get one point. If you<br> " +
            "get bit, you die.<br> " +
            "Good luck, and don't get bit!</html>";

    String WIN_MESSAGE = "<html>You have won! <br> In next level you will encounter" +
            "<br> 15 zombies, but don't get depressed. <br> You will have 10 foods to collect! <br>" +
            "Want to proceed to next level? </html> ";
    String TIMES_UP_MESSAGE = "Times up... Better luck next time!";
}
