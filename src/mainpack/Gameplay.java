package mainpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakePLACEMENTX = new int[400];
    private int[] snakePLACEMENTY = new int[400];
    private int delay = 200;
    private int lengthOfSnake = 2;
    private int moves = 0;
    private int randomNumX=0;
    private int randomNumY=0;
    private int borderLimitLeftX = 0;
    private int borderLimitRightX = 400;
    private int borderLimitUpY = 0;
    private int borderLimitDownY = 400;

    private boolean leftGoing = false;
    private boolean rightGoing = false;
    private boolean upGoing = false;
    private boolean downGoing = false;

    private ImageIcon snakeHeadImage;
    private ImageIcon snakeBodyImage;
    private ImageIcon snakeEmptyImage;
    private ImageIcon snakeEnemyImage;

    private Timer timer;

    Rectangle gaemBox = new Rectangle(borderLimitLeftX,borderLimitUpY,borderLimitRightX,borderLimitDownY);
    boolean setupFIRST = true;
    boolean movementIsDone = false;

    Random random = new Random();

    /**************************************************************************
     Default Things to setup for Class
     **************************************************************************/
    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }


    /**************************************************************************
     DEFINES
     **************************************************************************/
    public void paint(Graphics graphics) {

        if (setupFIRST) {
            snakeBodyImage = new ImageIcon("Body.png");
            snakeHeadImage = new ImageIcon("Head.png");
            snakeEmptyImage = new ImageIcon("Empty.png");
            snakeEnemyImage = new ImageIcon("Enemy.png");
            graphics.setColor(Color.red);
            while (!foodCreate());
            snakeEnemyImage.paintIcon(this, graphics, randomNumX, randomNumY);
            setupFIRST = false;
            graphics.drawRect(0,0,450,450);
        }



        for (int i = 0; i <= lengthOfSnake+1; i++) {
            if (i == 0) {
                snakeHeadImage.paintIcon(this, graphics, snakePLACEMENTX[i], snakePLACEMENTY[i]);
            } else if (i <= lengthOfSnake) {
                snakeBodyImage.paintIcon(this, graphics, snakePLACEMENTX[i], snakePLACEMENTY[i]);
            } else {
                snakeEmptyImage.paintIcon(this, graphics, snakePLACEMENTX[i], snakePLACEMENTY[i]);
            }
        }

        if (foodEatedCheck()) {
            while (!foodCreate());
            snakeEnemyImage.paintIcon(this, graphics, randomNumX, randomNumY);
            lengthOfSnake++;

        }




        graphics.dispose();
    }



    public boolean foodCreate() {

        randomNumY = random.nextInt(20) ;
        randomNumX = random.nextInt(20) ;
        randomNumY *= 40;
        randomNumX *= 40;

        for (int i = 0; i <= lengthOfSnake+1; i++) {
            if (snakePLACEMENTX[i] == randomNumX && snakePLACEMENTY[i] == randomNumY) {
                    return false;
            }
        }
        System.out.print("FOOD CREATED :");
        System.out.print(randomNumX);
        System.out.print(" And ");
        System.out.println(randomNumY);
        return true;
    }


    public boolean foodEatedCheck() {
        if (snakePLACEMENTX[0] == randomNumX && snakePLACEMENTY[0] == randomNumY) {
            return true;
        }
        return false;
    }


    /**************************************************************************
     Timer CALL
     **************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();


        for (int i = 399; i > 0; i--) {
            snakePLACEMENTX[i] = snakePLACEMENTX[i - 1];
            snakePLACEMENTY[i] = snakePLACEMENTY[i - 1];
        }

        if (snakePLACEMENTX[1] >= borderLimitRightX) {
            snakePLACEMENTX[0] = borderLimitLeftX;
        } else if (rightGoing) {
            snakePLACEMENTX[0] = snakePLACEMENTX[1] + 40;
        }

        if (snakePLACEMENTX[1] < borderLimitLeftX) {
            snakePLACEMENTX[0] = borderLimitRightX;
        } else if (leftGoing) {
            snakePLACEMENTX[0] = snakePLACEMENTX[1] - 40;
        }

        if (snakePLACEMENTY[1] >= borderLimitDownY) {
            snakePLACEMENTY[0] = borderLimitUpY;
        } else if (downGoing) {
            snakePLACEMENTY[0] = snakePLACEMENTY[1] + 40;
        }

        if (snakePLACEMENTY[1] < borderLimitUpY) {
            snakePLACEMENTY[0] = borderLimitDownY;
        } else if (upGoing) {
            snakePLACEMENTY[0] = snakePLACEMENTY[1] - 40;
        }
        repaint();
    }


    /**************************************************************************
     EMPTY
     **************************************************************************/
    @Override
    public void keyTyped(KeyEvent e) {

    }


    /**************************************************************************
     KEY PRESS TRIGGER
     **************************************************************************/
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

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
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

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

        else if (e.getKeyCode() == KeyEvent.VK_UP) {

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

        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

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


    /**************************************************************************
     EMPTY
     **************************************************************************/
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
