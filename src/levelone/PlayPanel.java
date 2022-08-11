package levelone;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import menu.Fade;
import menu.Game;
import menu.PlayFrame;
import menu.Window;

public class PlayPanel extends Game implements Runnable, KeyListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Thread t;//
    Fade fade;
    int x, y, z, dotsfinished, speed;

    public static enum GAMESTATE {INITIALIZING, PLAYING, GAMEOVER, COMPLETED}

    ;//

    public static enum GAMEZOON {zoon1, zoon2, zoon3, zoon4, zoon5, zoon6, zoon7, zoon8, zoon9}

    ; //
    static GAMESTATE gameState;//
    GAMEZOON gameZoon;//
    GAMEZOON walkthroughgamezoon[];
    boolean movingUp;
    static int canmoverightleft;//==0 cant
    static String min, sec, life, score;//
    Random random;
    BufferedImage levelOne;//
    ImageIcon pacman, dot[][];
    boolean ate[][];
    int xCoordinate, yCoordinate;//
    static boolean stateUndefined;
    boolean bite;
    static boolean timeup;//
    Window window;
    ImageIcon gameoverback;//
    EnemyPacmanRed enemypacman;
    EnemyPacmanPurple enemypacmanyellow;
    EnemyPacmanBlue enemypacmanblue;
    EnemyPacmanGreen enemypacmangreen;
    EnemyPacmanOrange enemypacmanorange;

    public PlayPanel() {
        super();
        if (true) {
            BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
            this.setCursor(blankCursor);
        }
        new Time();//
        random = new Random();
        fade = new Fade();
        enemypacman = new EnemyPacmanRed();
        enemypacmanyellow = new EnemyPacmanPurple();
        enemypacmanblue = new EnemyPacmanBlue();
        enemypacmangreen = new EnemyPacmanGreen();
        enemypacmanorange = new EnemyPacmanOrange();
        window = new Window();
        dot = new ImageIcon[9][9];
        ate = new boolean[9][9];
        walkthroughgamezoon = new GAMEZOON[3];
        gameState = GAMESTATE.INITIALIZING;//
        gameZoon = GAMEZOON.zoon1;//
        movingUp = false;
        canmoverightleft = 1;
        min = "5";//
        life = "2";
        score = "0";
        sec = "11";
        speed = 2;//
        this.addKeyListener(this);
        t = new Thread(this);
        t.start();
        if (min.equals("1") && sec.equals("31")) {
            //System.out.println("Hmm");
            //Time.t.resume();
        }

    }//constructor

    public void load() {
        xCoordinate = 22;
        yCoordinate = 528;
        randomNumber();

        switch (x) {
            case 1:
                walkthroughgamezoon[0] = GAMEZOON.zoon1;
                break;
            case 2:
                walkthroughgamezoon[0] = GAMEZOON.zoon2;
                break;
            case 3:
                walkthroughgamezoon[0] = GAMEZOON.zoon3;
                break;
            case 4:
                walkthroughgamezoon[0] = GAMEZOON.zoon4;
                break;
            case 5:
                walkthroughgamezoon[0] = GAMEZOON.zoon5;
                break;
            case 6:
                walkthroughgamezoon[0] = GAMEZOON.zoon6;
                break;
            case 7:
                walkthroughgamezoon[0] = GAMEZOON.zoon7;
                break;
            case 8:
                walkthroughgamezoon[0] = GAMEZOON.zoon8;
                break;
            case 9:
                walkthroughgamezoon[0] = GAMEZOON.zoon9;
                break;

            default:
                break;
        }//switch x

        switch (y) {
            case 1:
                walkthroughgamezoon[1] = GAMEZOON.zoon1;
                break;
            case 2:
                walkthroughgamezoon[1] = GAMEZOON.zoon2;
                break;
            case 3:
                walkthroughgamezoon[1] = GAMEZOON.zoon3;
                break;
            case 4:
                walkthroughgamezoon[1] = GAMEZOON.zoon4;
                break;
            case 5:
                walkthroughgamezoon[1] = GAMEZOON.zoon5;
                break;
            case 6:
                walkthroughgamezoon[1] = GAMEZOON.zoon6;
                break;
            case 7:
                walkthroughgamezoon[1] = GAMEZOON.zoon7;
                break;
            case 8:
                walkthroughgamezoon[1] = GAMEZOON.zoon8;
                break;
            case 9:
                walkthroughgamezoon[1] = GAMEZOON.zoon9;
                break;

            default:
                break;
        }//switch y

        switch (z) {
            case 1:
                walkthroughgamezoon[2] = GAMEZOON.zoon1;
                break;
            case 2:
                walkthroughgamezoon[2] = GAMEZOON.zoon2;
                break;
            case 3:
                walkthroughgamezoon[2] = GAMEZOON.zoon3;
                break;
            case 4:
                walkthroughgamezoon[2] = GAMEZOON.zoon4;
                break;
            case 5:
                walkthroughgamezoon[2] = GAMEZOON.zoon5;
                break;
            case 6:
                walkthroughgamezoon[2] = GAMEZOON.zoon6;
                break;
            case 7:
                walkthroughgamezoon[2] = GAMEZOON.zoon7;
                break;
            case 8:
                walkthroughgamezoon[2] = GAMEZOON.zoon8;
                break;
            case 9:
                walkthroughgamezoon[2] = GAMEZOON.zoon9;
                break;

            default:
                break;
        }//switch z

        //System.out.println(walkthroughgamezoon[0]+"  "+walkthroughgamezoon[1]+"  "+walkthroughgamezoon[2]);

        try {
            gameoverback = new ImageIcon(getClass().getResource("/images/backgif.gif"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }//System.out.println(gameoverback);
        try {
            URL levelOne_url = this.getClass().getResource("/images/LevelOne.png");
            levelOne = ImageIO.read(levelOne_url);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            pacman = new ImageIcon(getClass().getResource("/images/rightpac.gif"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                try {
                    dot[i][j] = new ImageIcon(getClass().getResource("/images/dot.gif"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        for (int i = 0; i <= 35; i++)//35
        {
            int k, l;
            dot[k = random.nextInt(9)][l = random.nextInt(9)] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
            ate[k][l] = true;
        }

        gameState = GAMESTATE.PLAYING;
    }//load

    public void Draw(Graphics2D g2d) {
        switch (gameState) {
            case INITIALIZING:
                g2d.setColor(Color.white);
                g2d.drawString("Please Wait", 310, 200);
                break;
            case PLAYING:
                g2d.drawImage(levelOne, 0, 0, 700, 600, null);
                //j==0 to access row
                for (int i = 0; i <= 8; i++) {
                    for (int j = 0; j <= 8; j++) {
                        if (i == 0) {
                            if (j == 8 || j == 7 || j == 6 || j == 5 || j == 4 || j == 3)
                                dot[i][j].paintIcon(this, g2d, (i + 1) * 15, j * 67);
                            else if (j == 0)
                                dot[i][j].paintIcon(this, g2d, (i + 1) * 15, (j + 1) * 12);
                            else
                                dot[i][j].paintIcon(this, g2d, (i + 1) * 15, j * 72);
                        } else {
                            if (j == 8 || j == 7 || j == 6 || j == 5 || j == 4 || j == 3)
                                dot[i][j].paintIcon(this, g2d, i * 83, j * 67);
                            else if (j == 0)
                                dot[i][j].paintIcon(this, g2d, i * 83, (j + 1) * 12);
                            else
                                dot[i][j].paintIcon(this, g2d, i * 83, j * 72);
                        }
                    }
                }
                enemypacman.Draw(g2d);
                enemypacmanblue.Draw(g2d);
                enemypacmanyellow.Draw(g2d);
                enemypacmangreen.Draw(g2d);
                enemypacmanorange.Draw(g2d);
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 577, 700, 600);
                //g2d.drawRect(0,577,700,600);
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Times new Roman", 1, 15));
                g2d.drawString("Score:" + "  " + score, 360, 594);
                g2d.drawString("Level :1", 8, 595);
                if (Integer.parseInt(life) < 0)
                    g2d.drawString("Life:" + "   0", 485, 594);
                else
                    g2d.drawString("Life:" + "   " + life, 485, 594);

                if (Time.lasttensec) {
                    g2d.drawString("Time:", 594, 594);
                    g2d.setColor(Color.RED);
                    g2d.drawString(min + ":" + sec, 649, 594);
                } else
                    g2d.drawString("Time:" + "    " + min + ":" + sec, 594, 594);

                //g2d.drawString(time,640,594);
                pacman.paintIcon(this, g2d, xCoordinate, yCoordinate);

                g2d.setColor(Color.WHITE);
                //g2d.drawLine(xCoordinate+20,yCoordinate+20,xCoordinate+40,yCoordinate+20);
                //g2d.drawLine(xCoordinate+30,yCoordinate+10,xCoordinate+30,yCoordinate+30);
                //g2d.drawRect(xCoordinate+8,yCoordinate+3,40,40);
                //g2d.drawRect(xCoordinate+48,yCoordinate+44,10,10);

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
        }//switch
    }//Draw()

    @Override
    public void run() {
        gameLoop();
    }//run()

    @SuppressWarnings("deprecation")
    void gameLoop() {
        while (true) {
            //System.out.println("WHile "+gameState);
            switch (gameState) {
                case INITIALIZING:
                    load();
                    enemypacman.load();
                    enemypacmanyellow.load();
                    enemypacmanblue.load();
                    enemypacmangreen.load();
                    enemypacmanorange.load();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case PLAYING:

                    for (int i = 0; i <= 8; i++) {
                        for (int j = 0; j <= 8; j++) {
                            if (ate[i][j]) {
                                dotsfinished = 1;
                            } else {
                                dotsfinished = 0;
                                break;
                            }
                        }
                        if (dotsfinished == 0)
                            break;
                    }

                    if (dotsfinished == 1) {
                        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        GraphicsDevice gd = ge.getDefaultScreenDevice();

                        //If translucent windows aren't supported, exit.
                        if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
                            //System.out.println("Translucency is not supported");
                            //System.exit(0);
                        } else
                            PlayFrame.playFrame.setOpacity(0.9f);


                        gameState = GAMESTATE.COMPLETED;
                        Time.t.stop();
                        //System.out.println("Completed");
                    }

                    for (int i = 0; i <= 8; i++) {
                        for (int j = 0; j <= 8; j++) {
                            if (i == 0) {
                                if (j == 8 || j == 7 || j == 6 || j == 5 || j == 4 || j == 3) {
                                    if (((i + 1) * 15 >= (xCoordinate + 8)) && ((i + 1) * 15 <= (xCoordinate + 48)) && ((j * 67) >= (yCoordinate)) && ((j * 67) <= (yCoordinate + 44))) {
                                        dot[i][j] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
                                        if (!ate[i][j])
                                            score = (new Integer(Integer.parseInt(score) + 1)).toString();
                                        ate[i][j] = true;
                                    }
                                } else if (j == 0) {
                                    if (((i + 1) * 15 >= (xCoordinate + 8)) && ((i * +1) * 15 <= (xCoordinate + 48)) && (((j + 1) * 12) >= (yCoordinate)) && (((j + 1) * 12) <= (yCoordinate + 44))) {
                                        dot[i][j] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
                                        if (!ate[i][j])
                                            score = (new Integer(Integer.parseInt(score) + 1)).toString();
                                        ate[i][j] = true;
                                    }
                                } else if (j == 1 || j == 2) {
                                    if (((i + 1) * 15 >= (xCoordinate + 8)) && ((i + 1) * 15 <= (xCoordinate + 48)) && (j * 72) >= (yCoordinate - 4) && (j * 72 <= (yCoordinate + 51))) {
                                        dot[i][j] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
                                        if (!ate[i][j])
                                            score = (new Integer(Integer.parseInt(score) + 1)).toString();
                                        ate[i][j] = true;
                                    }
                                }
                            }//if first column
                            else {
                                if (j == 8 || j == 7 || j == 6 || j == 5 || j == 4 || j == 3) {
                                    if (((i * 83) >= (xCoordinate + 8)) && ((i * 83) <= (xCoordinate + 48)) && ((j * 67) >= (yCoordinate)) && ((j * 67) <= (yCoordinate + 44))) {
                                        dot[i][j] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
                                        if (!ate[i][j])
                                            score = (new Integer(Integer.parseInt(score) + 1)).toString();
                                        ate[i][j] = true;
                                    }
                                } else if (j == 0) {
                                    //i*83,(j+1)*12
                                    if (((i * 83) >= (xCoordinate + 8)) && ((i * 83) <= (xCoordinate + 48)) && (((j + 1) * 12) >= (yCoordinate)) && (((j + 1) * 12) <= (yCoordinate + 44))) {
                                        dot[i][j] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
                                        if (!ate[i][j])
                                            score = (new Integer(Integer.parseInt(score) + 1)).toString();
                                        ate[i][j] = true;
                                    }
                                } else if (j == 1 || j == 2) {
                                    //i*83,j*72
                                    if (((i * 83) >= (xCoordinate + 8)) && ((i * 83) <= (xCoordinate + 48)) && (j * 72) >= (yCoordinate - 4) && (j * 72) <= (yCoordinate + 51)) {
                                        dot[i][j] = new ImageIcon(getClass().getResource("/images/blackdot.gif"));
                                        if (!ate[i][j])
                                            score = (new Integer(Integer.parseInt(score) + 1)).toString();
                                        ate[i][j] = true;
                                    }
                                }
                            }//else  if i!=0
                        }//for j
                    }//for i

                    //**start

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

                    // **end

                    if ((EnemyPacmanBlue.enemyXCoordinate + 30 >= xCoordinate + 8) && EnemyPacmanBlue.enemyXCoordinate + 30 <= xCoordinate + 48) {
                        if ((EnemyPacmanBlue.enemyYCoordinate + 20 >= yCoordinate + 3) && EnemyPacmanBlue.enemyYCoordinate <= yCoordinate + 44)
                        //System.out.println("Collision");
                        {
                            life = (new Integer(Integer.parseInt(life) - 1)).toString();
                            bite = true;
                            try {
                                Thread.sleep(800);
                            } catch (Exception e) {
                            }
                            if (Integer.parseInt(life) >= 0) {
                                Toolkit.getDefaultToolkit().beep();
                                bite = false;
                                xCoordinate = 22;
                                yCoordinate = 528;
                            }
                        }
                    }
                    if ((EnemyPacmanGreen.enemyXCoordinate + 30 >= xCoordinate + 8) && EnemyPacmanGreen.enemyXCoordinate + 30 <= xCoordinate + 48) {
                        if ((EnemyPacmanGreen.enemyYCoordinate + 20 >= yCoordinate + 3) && EnemyPacmanGreen.enemyYCoordinate <= yCoordinate + 44)
                        //System.out.println("Collision");
                        {
                            life = (new Integer(Integer.parseInt(life) - 1)).toString();
                            bite = true;
                            try {
                                Thread.sleep(800);
                            } catch (Exception e) {
                            }
                            if (Integer.parseInt(life) >= 0) {
                                Toolkit.getDefaultToolkit().beep();
                                bite = false;
                                xCoordinate = 22;
                                yCoordinate = 528;
                            }
                        }
                    }
                    if ((EnemyPacmanOrange.enemyXCoordinate + 30 >= xCoordinate + 8) && EnemyPacmanOrange.enemyXCoordinate + 30 <= xCoordinate + 48) {
                        if ((EnemyPacmanOrange.enemyYCoordinate + 20 >= yCoordinate + 3) && EnemyPacmanOrange.enemyYCoordinate <= yCoordinate + 44)
                        //System.out.println("Collision");
                        {
                            life = (new Integer(Integer.parseInt(life) - 1)).toString();
                            bite = true;
                            try {
                                Thread.sleep(800);
                            } catch (Exception e) {
                            }
                            if (Integer.parseInt(life) >= 0) {
                                Toolkit.getDefaultToolkit().beep();
                                bite = false;
                                xCoordinate = 22;
                                yCoordinate = 528;
                            }
                        }
                    }
                    if ((EnemyPacmanPurple.enemyXCoordinate + 30 >= xCoordinate + 8) && EnemyPacmanPurple.enemyXCoordinate + 30 <= xCoordinate + 48) {
                        if ((EnemyPacmanPurple.enemyYCoordinate + 20 >= yCoordinate + 3) && EnemyPacmanPurple.enemyYCoordinate <= yCoordinate + 44)
                        //System.out.println("Collision");
                        {
                            life = (new Integer(Integer.parseInt(life) - 1)).toString();
                            bite = true;
                            try {
                                Thread.sleep(800);
                            } catch (Exception e) {
                            }
                            if (Integer.parseInt(life) >= 0) {
                                Toolkit.getDefaultToolkit().beep();
                                bite = false;
                                xCoordinate = 22;
                                yCoordinate = 528;
                            }
                        }
                    }
                    if ((EnemyPacmanRed.enemyXCoordinate + 30 >= xCoordinate + 8) && EnemyPacmanRed.enemyXCoordinate + 30 <= xCoordinate + 48) {
                        if ((EnemyPacmanRed.enemyYCoordinate + 20 >= yCoordinate + 3) && EnemyPacmanRed.enemyYCoordinate <= yCoordinate + 44)
                        //System.out.println("Collision");
                        {
                            life = (new Integer(Integer.parseInt(life) - 1)).toString();
                            bite = true;
                            try {
                                Thread.sleep(800);
                            } catch (Exception e) {
                            }
                            if (Integer.parseInt(life) >= 0) {
                                Toolkit.getDefaultToolkit().beep();
                                bite = false;
                                xCoordinate = 22;
                                yCoordinate = 528;
                            }
                        }
                    }

                    enemypacman.updateEnemyPacman();
                    enemypacmanyellow.updateEnemyPacman();
                    enemypacmanblue.updateEnemyPacman();
                    enemypacmangreen.updateEnemyPacman();
                    enemypacmanorange.updateEnemyPacman();

                    if (yCoordinate >= 522 && yCoordinate <= 530) {
                        gameZoon = GAMEZOON.zoon1;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 454 && yCoordinate <= 466) {
                        gameZoon = GAMEZOON.zoon2;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 386 && yCoordinate <= 398) {
                        gameZoon = GAMEZOON.zoon3;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 322 && yCoordinate <= 330) {
                        gameZoon = GAMEZOON.zoon4;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 254 && yCoordinate <= 266) {
                        gameZoon = GAMEZOON.zoon5;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 194 && yCoordinate <= 198) {
                        gameZoon = GAMEZOON.zoon6;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 130 && yCoordinate <= 138) {
                        gameZoon = GAMEZOON.zoon7;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 62 && yCoordinate <= 74) {
                        gameZoon = GAMEZOON.zoon8;
                        canmoverightleft = 1;
                    } else if (yCoordinate >= 0 && yCoordinate <= 6) {
                        gameZoon = GAMEZOON.zoon9;
                        canmoverightleft = 1;
                    } else {
                        canmoverightleft = 0;
                        if (movingUp) {
                            yCoordinate--;
                            yCoordinate--;
                            yCoordinate--;
                        } else {
                            yCoordinate++;
                            yCoordinate++;
                            yCoordinate++;
                        }
                    }

                    //System.out.println(EnemyPacman.enemypacgameZoon);
                    enemypacman.checkEnemyPacmanZoon();
                    enemypacmanyellow.checkEnemyPacmanZoon();
                    enemypacmanblue.checkEnemyPacmanZoon();
                    enemypacmangreen.checkEnemyPacmanZoon();
                    enemypacmanorange.checkEnemyPacmanZoon();
                    break;

                case GAMEOVER:
                    break;
                default:
                    break;
            }
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
            //PlayFrame.playpanel=null;
            t.stop();
            Time.t.stop();
            fade.startFading(PlayFrame.playFrame);
            PlayFrame.playFrame.dispose();
            Window.windowFrame.dispose();

            menu.Background.backgroundframe.setVisible(true);
            window.showWindowFrame();
            //PlayFrame.playFrame.setContentPane(new PlayPanel());
            //System.out.println(xCoordinate+"  "+yCoordinate);
            //System.out.println(EnemyPacman.enemyXCoordinate+ "  "+ EnemyPacman.enemyYCoordinate+"   "+EnemyPacman.enemypacgameZoon);
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/rightpac.gif"));
            } catch (Exception e) {
                System.out.println("Image Panga" + e);
            }
            //xCoordinate++;
            if (canmoverightleft == 1) {
                xCoordinate++;
                xCoordinate++;
                xCoordinate++;
                xCoordinate++;
                xCoordinate++;
                //if(xCoordinate>650&& (gameZoon==GAMEZOON.zoon1 || gameZoon==GAMEZOON.zoon5 ||gameZoon==GAMEZOON.zoon9) )
                if (xCoordinate > 650 && ((gameZoon == walkthroughgamezoon[0]) || (gameZoon == walkthroughgamezoon[1]) || (gameZoon == walkthroughgamezoon[2]))) {
                    try {
                        pacman = new ImageIcon(getClass().getResource("/images/leftpac.gif"));
                    } catch (Exception e) {
                        System.out.println("Image Panga" + e);
                    }
                    xCoordinate = -25;
                } else if (xCoordinate > 650)
                    xCoordinate = 650;
            }//if canmoveleftright
        }

        if (ke.getKeyCode() == KeyEvent.VK_LEFT && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/leftpac.gif"));
            } catch (Exception e) {
                System.out.println("Image Panga" + e);
            }
            //xCoordinate--;
            if (canmoverightleft == 1) {
                xCoordinate--;
                xCoordinate--;
                xCoordinate--;
                xCoordinate--;
                xCoordinate--;
                //if(xCoordinate<-5&&(gameZoon==GAMEZOON.zoon1 || gameZoon==GAMEZOON.zoon5 ||gameZoon==GAMEZOON.zoon9))
                if (xCoordinate < -5 && ((gameZoon == walkthroughgamezoon[0]) || (gameZoon == walkthroughgamezoon[1]) || (gameZoon == walkthroughgamezoon[2]))) {
                    try {
                        pacman = new ImageIcon(getClass().getResource("/images/rightpac.gif"));
                    } catch (Exception e) {
                        System.out.println("Image Panga" + e);
                    }
                    xCoordinate = 670;
                } else if (xCoordinate < -5)
                    xCoordinate = -5;
            }//if canmoveleftright
        }
			/*else if(xCoordinate)
			{
				xCoordinate=-25;
				try
				{
					pacman=new ImageIcon(getClass().getResource("/images/rightpac.gif"));
				}catch(Exception e) {System.out.println("Image Panga"+e);}
			}*/

        if (ke.getKeyCode() == KeyEvent.VK_UP && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/uppac.gif"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            movingUp = true;
            canmoverightleft = 0;
            switch (gameZoon) {
                case zoon1:
                    if (xCoordinate <= 273 && xCoordinate >= 249)//here
                    {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon2:
                    if (xCoordinate < 10) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon3:
                    if ((xCoordinate >= 186 && xCoordinate <= 208) || (xCoordinate >= 630)) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon4://(xCoordinate>=318 &&xCoordinate<=330)
                    if ((xCoordinate >= 428 && xCoordinate <= 454) || xCoordinate <= 8) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon5:
                    if (xCoordinate >= 244 && xCoordinate <= 270) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon6:
                    if ((xCoordinate >= 633) || xCoordinate <= 6) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon7:
                    if (xCoordinate >= 373 && xCoordinate <= 397) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                case zoon8:
                    if ((xCoordinate >= 565 && xCoordinate <= 589) || (xCoordinate >= 245 && xCoordinate <= 269)) {
                        //System.out.println("Done");
                        yCoordinate--;
                        yCoordinate--;
                        yCoordinate--;
                    }
                    break;

                default:
                    //yCoordinate--;
                    //yCoordinate--;
                    break;
            }//switch
            //System.out.println(xCoordinate+"  "+yCoordinate);
            //System.out.println(gameZoon);
            //yCoordinate--;
            //yCoordinate--;
            if (yCoordinate < 0)
                yCoordinate = 0;
        }

        if (ke.getKeyCode() == KeyEvent.VK_DOWN && gameState == GAMESTATE.PLAYING && !bite) {
            try {
                pacman = new ImageIcon(getClass().getResource("/images/downpac.gif"));
            } catch (Exception e) {
                System.out.println("Image Panga" + e);
            }

            movingUp = false;
            canmoverightleft = 0;
            switch (gameZoon) {
                case zoon2:
                    if (xCoordinate <= 273 && xCoordinate >= 249) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon3:
                    if (xCoordinate < 10) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon4:
                    if ((xCoordinate >= 186 && xCoordinate <= 208) || (xCoordinate >= 630)) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon5://(xCoordinate>=318 &&xCoordinate<=330)
                    if ((xCoordinate >= 428 && xCoordinate <= 454) || xCoordinate <= 8) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon6:
                    if (xCoordinate >= 244 && xCoordinate <= 270) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon7:
                    if ((xCoordinate >= 633) || xCoordinate <= 6) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon8:
                    if (xCoordinate >= 373 && xCoordinate <= 397) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                case zoon9:
                    if ((xCoordinate >= 565 && xCoordinate <= 589) || (xCoordinate >= 245 && xCoordinate <= 269)) {
                        //System.out.println("Done");
                        yCoordinate++;
                        yCoordinate++;
                        yCoordinate++;
                    }
                    break;

                default:
                    //yCoordinate--;
                    //yCoordinate--;
                    break;
            }//switch
            //System.out.println(xCoordinate+"  "+yCoordinate);

            //yCoordinate++;
            //yCoordinate++;
            if (yCoordinate > 530)
                yCoordinate = 530;
        }

    }

    public void randomNumber() {

        //x=random.nextInt(9)+1;//1 to 9
        //y=random.nextInt(9)+10; //10 to18
        //z=random.nextInt(9)+19; //19 to 27
        x = random.nextInt(9) + 1;
        y = random.nextInt(9) + 1;
        z = random.nextInt(9) + 1;
        while ((x == y) || (x == z) || (y == z))
            randomNumber();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {

        }
    }

}//class
