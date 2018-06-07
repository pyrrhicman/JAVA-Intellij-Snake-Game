package mainpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private ImageIcon titleImage;

    private static final int[  ] snakeAddress = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560, 600, 640, 680, 720, 760};
    private int[] snakeXposition = new int[20];
    private int[] snakeYposition = new int[20];

    private boolean leftGoing = false;
    private boolean rightGoing = false;
    private boolean upGoing = false;
    private boolean downGoing = false;
    private ImageIcon snakeHeadImage;
    private ImageIcon snakeBodyImage;
    private Timer timer;
    private int delay = 100;
    private int lengthOfSnake = 3;
    private int moves = 0;


    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics graphics) {

        if (moves == 0) {
            snakeXposition[2] = 0;
            snakeXposition[1] = 40;
            snakeXposition[0] = 80;

            snakeYposition[2] = 0;
            snakeYposition[1] = 0;
            snakeYposition[0] = 0;
        }
        graphics.setColor(Color.WHITE);
        snakeBodyImage = new ImageIcon("Body.png");
        snakeHeadImage = new ImageIcon("Head.png");
        snakeHeadImage.paintIcon(this, graphics, snakeXposition[0], snakeYposition[0]);
        snakeBodyImage.paintIcon(this, graphics, snakeXposition[1], snakeYposition[1]);
        snakeBodyImage.paintIcon(this, graphics, snakeXposition[2], snakeYposition[2]);

        for (int a = 0; a < lengthOfSnake; a++) {
            if (a == 0) {
                if (upGoing || downGoing|| leftGoing|| rightGoing) {
                    snakeHeadImage.paintIcon(this, graphics, snakeXposition[a], snakeYposition[a]);
                }
            } else if (a != 0) {
                snakeBodyImage.paintIcon(this, graphics, snakeXposition[a], snakeYposition[a]);
            }
        }

        graphics.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (rightGoing) {
            for (int r = lengthOfSnake - 1; r >= 0; r--) {
                snakeYposition[r + 1] = snakeYposition[r];
            }
            for (int r = lengthOfSnake; r >= 0; r--) {
                snakeYposition[r + 1] = snakeYposition[r];
                if (r == 0) {
                    snakeXposition[r] = snakeXposition[r] + 40;
                } else {
                    snakeXposition[r] = snakeXposition[r - 1];
                }
                if (snakeXposition[r] > 800) {
                    snakeXposition[r] = 0;
                }
            }
        }
        if (leftGoing) {

        }
        if (upGoing) {

        }
        if (downGoing) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves ++;
            rightGoing = true;
            if (!leftGoing) {
                rightGoing = true;
            } else {
                rightGoing = false;
                leftGoing = true;
            }
            upGoing = false;
            downGoing = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moves++;
                leftGoing = true;
                if (!rightGoing) {
                    leftGoing = true;
                } else {
                    leftGoing = false;
                    rightGoing = true;
                }
                upGoing = false;
                downGoing = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            upGoing = true;
            if (!downGoing) {
                upGoing = true;
            } else {
                upGoing = false;
                downGoing = true;
            }
            leftGoing = false;
            rightGoing = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            downGoing = true;
            if (!upGoing) {
                downGoing = true;
            } else {
                downGoing = false;
                upGoing = true;
            }
            leftGoing = false;
            rightGoing = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
