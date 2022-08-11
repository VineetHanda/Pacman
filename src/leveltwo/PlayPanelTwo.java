package leveltwo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import menu.Fade;
import menu.Game;
import menu.PlayFrame;
import menu.Window;

public class PlayPanelTwo extends Game implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;

    BufferedImage levelTwo;
    ImageIcon gameoverback, pacman;

    public static enum GAMESTATE {INITIALIZING, PLAYING, GAMEOVER, COMPLETED}

    ;

    public static enum GAMEZOON {zoon1, zoon2, zoon3, zoon4, zoon5, zoon6, zoon7, zoon8, zoon9, zoon10, zoon11, zoon12}

    ;
    static GAMESTATE gameState;
    GAMEZOON gameZoon;

    static String min, sec, life, score;
    static boolean timeup;
    int xCoordinate, yCoordinate;
    Thread t;
    int speed;
    boolean bite;
    Time timeref;
    boolean movingup, movingdown;
    Fade fade;
    Window window;

    public PlayPanelTwo() {
        super();
        if (true) {
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }
        timeref = new Time();
        fade = new Fade();
        window = new Window();

        gameState = GAMESTATE.INITIALIZING;
        gameZoon = GAMEZOON.zoon1;

        min = "2";
        life = "2";
        score = "0";
        sec = "01";
        speed = 2;

        this.addKeyListener(this);
        t = new Thread(this, "First");
        t.start();
    }//constructor

    public void load() {
        xCoordinate = 10;
        yCoordinate = 537;
        try {
            gameoverback = new ImageIcon(getClass().getResource("/images/backgif.gif"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }//System.out.println(gameoverback);
        try {
            URL levelOne_url = this.getClass().getResource("/images/LevelTwo.png");
            levelTwo = ImageIO.read(levelOne_url);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            pacman = new ImageIcon(getClass().getResource("/images/rightpac.gif"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        gameState = GAMESTATE.PLAYING;
    }//load()

    public void Draw(Graphics2D g2d) {
        switch (gameState) {
            case INITIALIZING:
                g2d.setColor(Color.white);
                g2d.drawString("Please Wait", 310, 200);
                break;

            case PLAYING:
                g2d.drawImage(levelTwo, 0, 0, 700, 600, null);

                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 583, 700, 600);
                //g2d.drawRect(0,577,700,600);
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Times new Roman", 1, 15));
                g2d.drawString("Score:" + "  " + score, 360, 596);
                g2d.drawString("Level :2", 8, 597);
                if (Integer.parseInt(life) < 0)
                    g2d.drawString("Life:" + "   0", 485, 596);
                else
                    g2d.drawString("Life:" + "   " + life, 485, 596);

                if (Time.lasttensec) {
                    g2d.drawString("Time:", 594, 596);
                    g2d.setColor(Color.RED);
                    g2d.drawString(min + ":" + sec, 649, 596);
                } else
                    g2d.drawString("Time:" + "    " + min + ":" + sec, 594, 596);

                //g2d.drawString(time,640,594);
                pacman.paintIcon(this, g2d, xCoordinate, yCoordinate);

                g2d.setColor(Color.WHITE);

                break;

            case GAMEOVER:
                g2d.setColor(Color.WHITE);
                g2d.drawString("Your Score was: " + score, 287, 170);
                g2d.drawString("Press ESC to exit", 596, 592);
                g2d.setFont(new Font("Arial", 1, 30));
                g2d.setColor(Color.WHITE);
                g2d.drawString("Game Over", 256, 140);
                g2d.setFont(new Font("Papyrus", 1, 20));
                g2d.setColor(Color.WHITE);
                if (timeup)
                    g2d.drawString("Time Up", 295, 90);
                gameoverback.paintIcon(this, g2d, 0, 300);
                break;

            case COMPLETED:
                g2d.setColor(Color.WHITE);
                g2d.drawString("Your Score was: " + score, 287, 170);
                g2d.drawString("Press ESC to exit", 596, 592);
                g2d.setFont(new Font("Tempus Sans ITC", 1, 26));
                g2d.setColor(Color.WHITE);
                g2d.drawString("Congratulations Level 1 Completed", 124, 140);
                gameoverback.paintIcon(this, g2d, 0, 300);
                break;
            default:
                break;
        }

    }//Draw()
    //overriding method of menu.Game Class


    @SuppressWarnings("deprecation")
    void gameLoop() {
        while (true) {
            //System.out.println("WHile "+gameState);
            if (movingup) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            switch (gameState) {
                case INITIALIZING:
                    load();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case PLAYING:
                    if (Integer.parseInt(life) < 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice gd = ge.getDefaultScreenDevice();

                        //If translucent windows aren't supported, exit.
                        if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
                            //System.out.println("Translucency is not supported");
                            //System.exit(0);
                        } else
                            PlayFrame.playFrame.setOpacity(0.9f);

                        gameState = GAMESTATE.GAMEOVER;
                        Time.t.stop();
                    }

                    //set Zoon coordinates here

                    if (yCoordinate == 537) {
                        gameZoon = GAMEZOON.zoon1;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 489) {
                        gameZoon = GAMEZOON.zoon2;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 441) {
                        gameZoon = GAMEZOON.zoon3;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 393) {
                        gameZoon = GAMEZOON.zoon4;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 345) {
                        gameZoon = GAMEZOON.zoon5;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 297) {
                        gameZoon = GAMEZOON.zoon6;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 245) {
                        gameZoon = GAMEZOON.zoon7;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 197) {
                        gameZoon = GAMEZOON.zoon8;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 149) {
                        gameZoon = GAMEZOON.zoon9;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 97) {
                        gameZoon = GAMEZOON.zoon10;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 49) {
                        gameZoon = GAMEZOON.zoon11;
                        movingdown = false;
                        movingup = false;
                    } else if (yCoordinate == 1) {
                        gameZoon = GAMEZOON.zoon12;
                        movingdown = false;
                        movingup = false;
                    } else {
                        if (movingup) {
                            yCoordinate--;
                            yCoordinate--;
                        } else if (movingdown) {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }

                    break;

                case GAMEOVER:
                    break;
                default:
                    break;
            }//switch

            repaint();
            try {
                Thread.sleep(60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//while
    }//gameLoop()

    @SuppressWarnings("deprecation")
    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            t.stop();
            Time.t.stop();
            fade.startFading(PlayFrame.playFrame);
            PlayFrame.playFrame.dispose();
            Window.windowFrame.dispose();

            menu.Background.backgroundframe.setVisible(true);
            window.showWindowFrame();
        }//esc

        if (ke.getKeyCode() == KeyEvent.VK_RIGHT && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/rightpac.gif"));
            } catch (Exception e) {
                System.out.println("Image Panga" + e);
            }

            if (!movingdown && !movingup) {
                switch (gameZoon) {
                    case zoon1:
                        xCoordinate++;
                        xCoordinate++;
                        xCoordinate++;
                        break;
                    case zoon2:
                        if (xCoordinate >= 422 && xCoordinate <= 424) {
                            xCoordinate = 424;
                        } else if (xCoordinate >= 86 && xCoordinate <= 88) {
                            xCoordinate = 86;
                        } else {
                            xCoordinate++;
                            xCoordinate++;
                            xCoordinate++;
                        }
                        break;
                    case zoon3:
                        if (xCoordinate >= 14 && xCoordinate <= 16) {
                            xCoordinate = 16;
                        } else {
                            xCoordinate++;
                            xCoordinate++;
                            xCoordinate++;
                        }
                        break;
                    case zoon4:
                        xCoordinate++;
                        xCoordinate++;
                        xCoordinate++;
                        break;
                    case zoon5:
                        xCoordinate++;
                        xCoordinate++;
                        xCoordinate++;
                        break;
                    case zoon6:
                        if (xCoordinate >= 295 && xCoordinate <= 297) {
                            xCoordinate = 295;
                        } else {
                            xCoordinate++;
                            xCoordinate++;
                            xCoordinate++;
                        }
                        break;
                    case zoon7:
                        if (xCoordinate >= 295 && xCoordinate <= 297) {
                            xCoordinate = 295;
                        } else {
                            xCoordinate++;
                            xCoordinate++;
                            xCoordinate++;
                        }
                        break;
                    case zoon8:
                        xCoordinate++;
                        xCoordinate++;
                        xCoordinate++;
                        break;
                    case zoon9:
                        if (xCoordinate >= 157 && xCoordinate <= 159) {
                        } else {
                            xCoordinate++;
                            xCoordinate++;
                            xCoordinate++;
                        }
                        break;
                    case zoon10:
                        xCoordinate++;
                        xCoordinate++;
                        xCoordinate++;
                        break;
                    case zoon11:
                        if (xCoordinate >= 398 && xCoordinate <= 400) {
                            xCoordinate = 398;
                        } else {
                            xCoordinate++;
                            xCoordinate++;
                            xCoordinate++;
                        }
                        break;
                    case zoon12:
                        xCoordinate++;
                        xCoordinate++;
                        xCoordinate++;
                        break;

                    default:
                        break;
                }//switch
            }//if(!movingdown && !movingup)
			/*if(xCoordinate>650)
			{
				try
				{
					pacman=new ImageIcon(getClass().getResource("/images/leftpac.gif"));
				}catch(Exception e) {System.out.println("Image Panga"+e);}
				xCoordinate=-25;
			}*/
            if (xCoordinate > 650) xCoordinate = 650;
        }//VK_RIGHT

        if (ke.getKeyCode() == KeyEvent.VK_LEFT && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/leftpac.gif"));
            } catch (Exception e) {
                System.out.println("Image Panga" + e);
            }
            if (!movingdown && !movingup) {
                switch (gameZoon) {
                    case zoon1:
                        xCoordinate--;
                        xCoordinate--;
                        xCoordinate--;
                        break;
                    case zoon2:
                        if (xCoordinate >= 132 && xCoordinate <= 134) {
                            xCoordinate = 134;
                        } else if (xCoordinate >= 468 && xCoordinate <= 470) {
                            xCoordinate = 470;
                        } else {
                            xCoordinate--;
                            xCoordinate--;
                            xCoordinate--;
                        }
                        break;
                    case zoon3:
                        if (xCoordinate >= 62 && xCoordinate <= 64) {
                            xCoordinate = 64;
                        } else {
                            xCoordinate--;
                            xCoordinate--;
                            xCoordinate--;
                        }
                        break;
                    case zoon4:
                        xCoordinate--;
                        xCoordinate--;
                        xCoordinate--;
                        break;
                    case zoon5:
                        xCoordinate--;
                        xCoordinate--;
                        xCoordinate--;
                        break;
                    case zoon6:
                        if (xCoordinate >= 341 && xCoordinate <= 343) {
                            xCoordinate = 343;
                        } else {
                            xCoordinate--;
                            xCoordinate--;
                            xCoordinate--;
                        }
                        break;
                    case zoon7:
                        if (xCoordinate >= 341 && xCoordinate <= 343) {
                            xCoordinate = 343;
                        } else {
                            xCoordinate--;
                            xCoordinate--;
                            xCoordinate--;
                        }
                        break;
                    case zoon8:
                        xCoordinate--;
                        xCoordinate--;
                        xCoordinate--;
                        break;
                    case zoon9:
                        if (xCoordinate >= 203 && xCoordinate <= 205) {
                            xCoordinate = 205;
                        } else {
                            xCoordinate--;
                            xCoordinate--;
                            xCoordinate--;
                        }
                        break;
                    case zoon10:
                        xCoordinate--;
                        xCoordinate--;
                        xCoordinate--;
                        break;
                    case zoon11:
                        if (xCoordinate >= 444 && xCoordinate <= 446) {
                            xCoordinate = 446;
                        } else {
                            xCoordinate--;
                            xCoordinate--;
                            xCoordinate--;
                        }
                        break;
                    case zoon12:
                        xCoordinate--;
                        xCoordinate--;
                        xCoordinate--;
                        break;
                    default:
                        break;
                }//switch
            }//if(!movingdown && !movingup)
			/*if(xCoordinate<-5)
			{
				try
				{
					pacman=new ImageIcon(getClass().getResource("/images/rightpac.gif"));
				}catch(Exception e) {System.out.println("Image Panga"+e);}
				xCoordinate=670;
			}*/
            if (xCoordinate < -5) xCoordinate = -5;
        }//VK_LEFT

        if (ke.getKeyCode() == KeyEvent.VK_DOWN && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/downpac.gif"));
            } catch (Exception e) {
                System.out.println("Image Panga" + e);
            }
            switch (gameZoon) {
                case zoon1:
                    break;
                case zoon2:
                    if ((xCoordinate >= 64 && xCoordinate <= 88) || (xCoordinate >= 400 && xCoordinate <= 424) || (xCoordinate >= 644 && xCoordinate <= 667)) {
					/*while(yCoordinate!=537)
					{
						try {
						Thread.sleep(10);}catch(Exception e) {e.printStackTrace();}
						yCoordinate++;
					}*/
					/*new Thread(this) {
						@Override
						public void run() {
							//super.run();
							yCoordinate++;
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					};*/
                        //yCoordinate=537;
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 537) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon3:
                    if (((xCoordinate >= 468 && xCoordinate <= 470)) || (xCoordinate >= 64 && xCoordinate <= 88) || (xCoordinate >= -5 && xCoordinate <= 16)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 489) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon4:
                    if (xCoordinate <= 16 || (xCoordinate >= 64 && xCoordinate <= 88) || (xCoordinate >= 285 && xCoordinate <= 292)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 441) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon5:
                    if ((xCoordinate >= 530 && xCoordinate <= 538)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 393) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon6:
                    if (xCoordinate <= -2 || (xCoordinate >= 445 && xCoordinate <= 454)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 345) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon7:
                    if ((xCoordinate >= 292 && xCoordinate <= 297) || (xCoordinate >= 343 && xCoordinate <= 349) || (xCoordinate >= 526 && xCoordinate <= 538)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 297) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon8:
                    if ((xCoordinate >= 79 && xCoordinate <= 91) || xCoordinate >= 644) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 245) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon9:
                    if ((xCoordinate >= 355 && xCoordinate <= 361) || (xCoordinate >= 205 && xCoordinate <= 211)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 197) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon10:
                    if ((xCoordinate >= 61 && xCoordinate <= 68) || (xCoordinate >= 553 && xCoordinate <= 562)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 149) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon11:
                    if ((xCoordinate >= 151 && xCoordinate <= 158) || (xCoordinate >= 391 && xCoordinate <= 398)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 97) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                case zoon12:
                    if (xCoordinate <= -4 || (xCoordinate >= 308 && xCoordinate <= 314) || (xCoordinate >= 482 && xCoordinate <= 494)) {
                        movingdown = true;
                        movingup = false;
                        if (yCoordinate == 49) {
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }
                    break;
                default:
                    break;
            }//switch
            if (yCoordinate > 537)
                yCoordinate = 537;
        }//VK_DOWN

        if (ke.getKeyCode() == KeyEvent.VK_UP && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/uppac.gif"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //yCoordinate--;
            //yCoordinate--
            switch (gameZoon) {
                case zoon1:
                    if ((xCoordinate >= 64 && xCoordinate <= 88) || (xCoordinate >= 400 && xCoordinate <= 424) || (xCoordinate >= 644 && xCoordinate <= 667)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 489) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }

                        //yCoordinate=489;
                    }//if
                    break;
                case zoon2:
                    if ((xCoordinate >= 468 && xCoordinate <= 470) || (xCoordinate >= 64 && xCoordinate <= 88) || (xCoordinate >= -5 && xCoordinate <= 16)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 441) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon3:
                    if (xCoordinate <= 88 || (xCoordinate >= 285 && xCoordinate <= 292)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 393) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon4:
                    if ((xCoordinate >= 530 && xCoordinate <= 538)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 345) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon5:
                    if (xCoordinate <= -2 || (xCoordinate >= 445 && xCoordinate <= 454)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 297) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon6:
                    if ((xCoordinate >= 292 && xCoordinate <= 297) || (xCoordinate >= 343 && xCoordinate <= 349) || (xCoordinate >= 526 && xCoordinate <= 538)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 245) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon7:
                    if ((xCoordinate >= 79 && xCoordinate <= 91) || xCoordinate >= 644) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 197) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon8:
                    if ((xCoordinate >= 355 && xCoordinate <= 361) || (xCoordinate >= 205 && xCoordinate <= 211)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 149) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon9:
                    if ((xCoordinate >= 61 && xCoordinate <= 68) || (xCoordinate >= 553 && xCoordinate <= 562)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 97) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon10:
                    if ((xCoordinate >= 151 && xCoordinate <= 158) || (xCoordinate >= 391 && xCoordinate <= 398)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 49) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon11:
                    if (xCoordinate <= -4 || (xCoordinate >= 308 && xCoordinate <= 314) || (xCoordinate >= 482 && xCoordinate <= 494)) {
                        movingup = true;
                        movingdown = false;
                        if (yCoordinate == 1) {
                        } else {
                            yCoordinate--;
                            yCoordinate--;
                        }
                    }
                    break;
                case zoon12:
                    break;
                default:
                    break;
            }//switch

            if (yCoordinate < 0)
                yCoordinate = 0;
        }//VK_UP

        if (ke.getKeyCode() == KeyEvent.VK_P) {
            System.out.println("GAMEZOON :" + gameZoon + " and Xcoordinate :" + xCoordinate + "  and Ycoordinate :  " + yCoordinate);
        }

    }//keyPressed()

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void run() {
        gameLoop();
    }

}//class
