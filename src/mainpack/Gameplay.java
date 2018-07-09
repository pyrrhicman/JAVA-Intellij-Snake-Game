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
    private int delay = 100;
    private int lengthOfSnake = 2;
    private int moves = 0;
    private int randomNumX=0;
    private int randomNumY=0;
    private int borderLimitLeftX = 0;
    private int borderLimitRightX = 760;
    private int borderLimitUpY = 0;
    private int borderLimitDownY = 760;

    private boolean leftGoing = false;
    private boolean rightGoing = false;
    private boolean upGoing = false;
    private boolean downGoing = false;
    private boolean yeahItWentOutOfSide = false;
    private boolean foodCreated = true;
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
            graphics.drawRect(-10,-10,810,810);
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

        if (itIsNotGood()) {
            for (int i = 0; i <= 399; i++) {

                for (int x = 0; x <= 399; x++) {
                snakeEmptyImage.paintIcon(this, graphics, snakePLACEMENTX[i], snakePLACEMENTY[x]);
                }
            }
            while (!foodCreate());
            snakeEnemyImage.paintIcon(this, graphics, randomNumX, randomNumY);7´s
            lengthOfSnake = 3;
            rightGoing = false;
            upGoing = false;
            downGoing = false;
            leftGoing = true;
            snakePLACEMENTX[0] = 0;
            snakePLACEMENTY[0] = 0;

        }




        graphics.dispose();
    }



    public boolean foodCreate() {

            randomNumX = random.nextInt(19) ;
            randomNumY = random.nextInt(19) ;
            randomNumX *= 40;
            randomNumY *= 40;

            for (int i = 0; i <= lengthOfSnake+1; i++) {
                if ((snakePLACEMENTX[i] == randomNumX) && (snakePLACEMENTY[i] == randomNumY)) {
                    System.out.println("Trying for food... :");
                    return true;
                }
            }
        System.out.print("FOOD CREATED :");
        System.out.print(randomNumX);
        System.out.print(" And ");
        System.out.println(randomNumY);
        return true;
    }


    public boolean foodEatedCheck() {

        for (int i = 0; i <= lengthOfSnake; i++) {
            if (snakePLACEMENTX[i] == randomNumX && snakePLACEMENTY[i] == randomNumY) {
                return true;
            }
        }
        return false;
    }

    public boolean itIsNotGood() {
        for (int i = 1; i <= lengthOfSnake; i++) {
            if (snakePLACEMENTX[0] == snakePLACEMENTX[i]) {
                if (snakePLACEMENTY[0] == snakePLACEMENTY[i]) {
                    return true;
                }
            }
            if (snakePLACEMENTY[0] == snakePLACEMENTY[i]) {
                if (snakePLACEMENTX[0] == snakePLACEMENTX[i]) {
                    return true;
                }
            }
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



        if (rightGoing) {
            snakePLACEMENTX[0] = snakePLACEMENTX[1] + 40;

        } else if (leftGoing) {
            snakePLACEMENTX[0] = snakePLACEMENTX[1] - 40;

        } else if (downGoing) {
            snakePLACEMENTY[0] = snakePLACEMENTY[1] + 40;

        } else if (upGoing) {
            snakePLACEMENTY[0] = snakePLACEMENTY[1] - 40;
        }


        if (snakePLACEMENTX[0] > borderLimitRightX) {
            snakePLACEMENTX[0] = borderLimitLeftX;
            yeahItWentOutOfSide = true;
            System.out.println("snakePLACEMENTX[1] >= borderLimitRightX");

        } else if (snakePLACEMENTX[0] < borderLimitLeftX) {
            snakePLACEMENTX[0] = borderLimitRightX;
            yeahItWentOutOfSide = true;
            System.out.println("snakePLACEMENTX[1] < borderLimitLeftX");

        } else if (snakePLACEMENTY[0] > borderLimitDownY) {
            snakePLACEMENTY[0] = borderLimitUpY;
            yeahItWentOutOfSide = true;
            System.out.println("snakePLACEMENTY[1] >= borderLimitDownY");

        } else if (snakePLACEMENTY[0] < borderLimitUpY) {
            snakePLACEMENTY[0] = borderLimitDownY;
            yeahItWentOutOfSide = true;
            System.out.println("snakePLACEMENTY[1] < borderLimitUpY");

        }



/*
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
        }*/
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
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

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
