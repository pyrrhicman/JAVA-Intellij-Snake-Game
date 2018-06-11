package mainpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private ImageIcon titleImage;
    private static final int[  ] snakeAddress = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560, 600, 640, 680, 720, 760}; // snakeAddress[0] - 19
    private int[] snakeDATAX = new int[20];//Can be : 0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560, 600, 640, 680, 720, 760
    private int[] snakeDATAY = new int[20];//Can be : 0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560, 600, 640, 680, 720, 760

    private int snakeXaddress = 0;
    private int snakeOLDX=0;
    private int snakeOLDY=0;
    private int snakeYaddress = 0;

    private boolean leftGoing = false;
    private boolean rightGoing = false;
    private boolean upGoing = false;
    private boolean downGoing = false;

    private ImageIcon snakeHeadImage;
    private ImageIcon snakeBodyImage;
    private ImageIcon snakeEmptyImage;

    private Timer timer;

    private int delay = 100;
    private int lengthOfSnake = 3;
    private int moves = 0;





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
        if (moves == 0) {
            snakeBodyImage = new ImageIcon("Body.png");
            snakeHeadImage = new ImageIcon("Head.png");
            snakeEmptyImage = new ImageIcon("Empty.png");
            graphics.setColor(Color.WHITE);
            /*snakeXposition[2] = 0;
            snakeXposition[1] = 40;
            snakeXposition[0] = 80;

            snakeYposition[2] = 0;
            snakeYposition[1] = 0;
            snakeYposition[0] = 0;


            snakeHeadImage.paintIcon(this, graphics, snakeXposition[0], snakeYposition[0]);
            snakeBodyImage.paintIcon(this, graphics, snakeXposition[1], snakeYposition[1]);
            snakeBodyImage.paintIcon(this, graphics, snakeXposition[2], snakeYposition[2]);*/
        }
        snakeEmptyImage.paintIcon(this, graphics, snakeDATAX[snakeOLDX], snakeDATAY[snakeOLDY]);
        snakeHeadImage.paintIcon(this, graphics, snakeDATAX[snakeXaddress], snakeDATAY[snakeYaddress]);

        /*for (int a = 0; a < lengthOfSnake; a++) {
            if (a == 0) {
                if (upGoing || downGoing|| leftGoing|| rightGoing) {
                    snakeHeadImage.paintIcon(this, graphics, snakeXposition[a], snakeYposition[a]);
                }
            } else if (a != 0) {
                snakeBodyImage.paintIcon(this, graphics, snakeXposition[a], snakeYposition[a]);
            }
        }*/

        graphics.dispose();
    }





    /**************************************************************************
     Timer CALL
     **************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        /*for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                snakeDATAX[x]=25;
                snakeDATAY[y]=25;
            }
        }*/


        //snakeDATAX[snakeXaddress] = 25;
       // snakeDATAY[snakeXaddress] = 25;
        snakeOLDX = snakeXaddress;
        snakeOLDY = snakeYaddress;
        if (rightGoing) {
            System.out.println("rightGoing");
            snakeXaddress++;
            if (snakeXaddress >= 20) {
                snakeXaddress = 0;
            }
            snakeDATAX[snakeXaddress] = snakeAddress[snakeXaddress];
        }
        else if (leftGoing) {
            System.out.println("leftGoing");
            snakeXaddress--;
            if (snakeXaddress < 0) {
                snakeXaddress = 19;
            }
            snakeDATAX[snakeXaddress] = snakeAddress[snakeXaddress];
        }

        else if (downGoing) {
            System.out.println("downGoing");
            snakeYaddress++;
            if (snakeYaddress >= 20) {
                snakeYaddress = 0;
            }
            snakeDATAY[snakeYaddress] = snakeAddress[snakeYaddress];
        }
        else if (upGoing) {
            System.out.println("upGoing");
            snakeYaddress--;
            if (snakeYaddress < 0) {
                snakeYaddress = 19;
            }
            snakeDATAY[snakeYaddress] = snakeAddress[snakeYaddress];

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




    /**************************************************************************
     EMPTY
     **************************************************************************/
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
