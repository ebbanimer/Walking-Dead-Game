package com.dt181g.project;

/**
 * Interface to store values to be used throughout the program.
 * @author Ebba Nimér
 */
public interface Constants {
    int ICON_HEIGHT = 60;
    int ICON_WIDTH = 60;
    int WIDTH = 700;
    int HEIGHT = 550;

    String MASTER_ZOMBIE_PATH = "transparent-masterzombie.png";
    String DARYL_PATH = "daryl.png";
    String MICHONNE_PATH = "michonne.png";
    String RICK_PATH = "rick.png";
    String EUGENE_PATH = "eugene.png";
    String CARROT_PATH = "carrot.png";
    String SALAD_PATH = "salad.png";
    String ZOMBIE_ONE_PATH = "zombie1.png";
    String ZOMBIE_TWO_PATH = "zombie2.png";

    String PICK_CHARACTER = "Pick a character...";

    String DEAD_START_VIEW = " already got bit in a previous round, try another character!";

    String DEAD = "<html>Sorry, character got bit and is dead! <br>" +
            "Better luck next time. </html>";
    String ERROR = "Invalid character! Please choose a character before going ahead...";

    String INSTRUCTION_MESSAGE =
            "<html>Collect as much food as you can without getting caught by the zombies.<br> " +
            "You move your character with the arrow keys and you have 30 seconds on you.<br>" +
            "For each food you collect, you get one point. If you get to close to a zombie <br>" +
                    "or get bit, you die.<br>" +
            "Good luck, and don't get bit!</html>";

    String WIN_MESSAGE_1 =
            "<html>You have won! <br> " +
                    "In next level you will encounter 10 zombies, <br>" +
            "but don't get depressed. You will have 10 foods to collect! <br>" +
            "Want to proceed to next level? </html> ";

    String WIN_MESSAGE_2 =
            "<html>Congrats, you have won level 2! <br> " +
                    "I am sure Rick and his friends are happy to recruit you to their gang..</html>";


    String TIMES_UP_MESSAGE = "Times up... Better luck next time!";

}
