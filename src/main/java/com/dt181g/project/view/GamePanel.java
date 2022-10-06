package com.dt181g.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = false;
    private boolean dying = false;

    private final int BLOCK_SIZE = 50;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_ZOMBIES = 10;
    private final int CHAR_SPEED = 6;
    private final int ZOMBIE_SPEED = 6;

    private int N_ZOMBIES = 5;
    private int lives, score;
    private int[] dx, dy;   // position for zombies
    private int[] zombie_x, zombie_y, zombie_dx, zombie_dy, zombieSpeed;    // position of zombie

    private Image foodImg, zombieImg;
    private Image charImg;

    private int char_x, char_y, chard_y, chard_x;    // position and direction of char
    private int req_dx, req_dy;   // controlled by keys

    private final int validSpeeds[] = {1,2,3,4,6,8};
    private final int maxSpeed = 6;
    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    private final short levelData[] = {
            19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
            0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
            19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
            17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
            17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
            17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20,
            21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 16, 16, 16, 20,
            17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };

    public GamePanel(String path){
        loadImg(path);
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

    private void loadImg(String path){
        charImg = new ImageIcon(path).getImage();
        zombieImg = new ImageIcon("src\\main\\java\\com\\dt181g\\project\\images\\zombie.PNG").getImage();

        //this.add(new CharacterView(path));
        //this.add(new ZombieView());
    }

    private void initVariables(){
        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400,400);
        zombie_x = new int[MAX_ZOMBIES];
        zombie_dx = new int[MAX_ZOMBIES];
        zombie_y = new int[MAX_ZOMBIES];
        zombie_dy = new int[MAX_ZOMBIES];
        zombieSpeed = new int[MAX_ZOMBIES];
        dx = new int[4];
        dy = new int[4];
        timer = new Timer(1000,this);   // how often it will redraw
        timer.start();
    }

    private void showIntroScreen(Graphics2D g2d) {

        String start = "Press SPACE to start";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);
    }

    private void initGame(){
        lives = 3;
        score = 0;
        initLevel();
        N_ZOMBIES = 6;
        currentSpeed = 3;
    }

    private void initLevel(){
        int i;

        for (i=0; i<(N_BLOCKS*N_BLOCKS); i++){
            screenData[i] = levelData[i];
        }
    }

    private void playGame(Graphics2D g2d){
        if (dying){
            death();
        } else {
            moveChar();
            drawChar(g2d);
            moveZombies(g2d);
            checkMaze();
        }
    }

    private void moveChar(){
        int pos;
        short ch;

        if (char_x % BLOCK_SIZE == 0 && char_y % BLOCK_SIZE == 0){
            pos = char_x / BLOCK_SIZE + N_BLOCKS * (int) (char_y / BLOCK_SIZE);
            ch = screenData[pos];
            if ((ch & 16) != 0){
                screenData[pos] = (short) (ch & 15);
                score++;
            }
            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    chard_x = req_dx;
                    chard_y = req_dy;
                }
            }

            // Check for standstill
            if ((chard_x == -1 && chard_y == 0 && (ch & 1) != 0)
                    || (chard_x == 1 && chard_y == 0 && (ch & 4) != 0)
                    || (chard_x == 0 && chard_y == -1 && (ch & 2) != 0)
                    || (chard_x == 0 && chard_y == 1 && (ch & 8) != 0)) {
                chard_x = 0;
                chard_y = 0;
            }
        }

        // Speed
        char_x = char_x + CHAR_SPEED * chard_x;
        char_y = char_y + CHAR_SPEED * chard_y;

    }

    private void drawChar(Graphics2D g2d) {

        if (req_dx == -1) {
            g2d.drawImage(charImg, char_x + 1, char_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(charImg, char_x + 1, char_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(charImg, char_x + 1, char_y + 1, this);
        } else {
            g2d.drawImage(charImg, char_x + 1, char_y + 1, this);
        }
    }

    private void moveZombies(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < N_ZOMBIES; i++) {
            if (zombie_x[i] % BLOCK_SIZE == 0 && zombie_y[i] % BLOCK_SIZE == 0) {
                pos = zombie_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (zombie_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && zombie_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && zombie_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && zombie_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && zombie_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        zombie_dx[i] = 0;
                        zombie_dy[i] = 0;
                    } else {
                        zombie_dx[i] = -zombie_dx[i];
                        zombie_dy[i] = -zombie_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    zombie_dx[i] = dx[count];
                    zombie_dy[i] = dy[count];
                }

            }

            zombie_x[i] = zombie_x[i] + (zombie_dx[i] * zombieSpeed[i]);
            zombie_y[i] = zombie_y[i] + (zombie_dy[i] * zombieSpeed[i]);
            drawZombie(g2d, zombie_x[i] + 1, zombie_y[i] + 1);

            if (char_x > (zombie_x[i] - 12) && char_x < (zombie_x[i] + 12)
                    && char_y > (zombie_y[i] - 12) && char_y < (zombie_y[i] + 12)
                    && inGame) {

                dying = true;
            }
        }
    }

    private void drawZombie(Graphics2D g2d, int x, int y) {
        g2d.drawImage(zombieImg, x, y, this);
    }

    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i] % 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_ZOMBIES < MAX_ZOMBIES) {
                N_ZOMBIES++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() {

        lives--;

        if (lives == 0) {
            inGame = false;
        }

        continueLevel();
    }

    private void continueLevel(){
        int dx = 1;
        int random;       // random speed of ghost

        for(int i = 0; i < N_ZOMBIES; i++){
            zombie_y[i] = 4*BLOCK_SIZE;
            zombie_x[i] = 4*BLOCK_SIZE;
            zombie_dy[i] = 0;
            zombie_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed+1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            zombieSpeed[i] = validSpeeds[random];
        }

        char_x = 7 * BLOCK_SIZE;  // start positioin of char
        char_y = 11 * BLOCK_SIZE;
        chard_x = 0;
        chard_y = 0;
        req_dx = 0;
        req_dy = 0;
        dying = false;

    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));

                if ((levelData[i] == 0)) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (inGame){
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if (inGame){
                if (key == KeyEvent.VK_LEFT){
                    req_dx = -1;       // moves one x-coordinate to left
                    req_dy = 0;
                }
                else if (key == KeyEvent.VK_RIGHT){
                    req_dx = 1;       // moves one x-coordinate to right
                    req_dy = 0;
                }
                else if (key == KeyEvent.VK_UP){
                    req_dx = 0;
                    req_dy = -1;       // moves one y-coordinate up
                }
                else if (key == KeyEvent.VK_DOWN){
                    req_dx = 0;
                    req_dy = 1;     // moves one y-coordinate down
                }
                else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()){
                    inGame = false;
                }
                //else if (key == KeyEvent.VK_SPACE){
                    //shoot
                //}
            } else {
                if (key == KeyEvent.VK_SPACE){
                    inGame = true;
                    initGame();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
