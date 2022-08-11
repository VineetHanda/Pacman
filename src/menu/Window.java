package menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Window implements KeyListener, MouseListener, Runnable {

    public static JFrame windowFrame;
    static PlayFrame playframe;
    JLabel blackback;
    JLabel arrow;
    JLabel play, level, login, register, sound, quit, plusicon;
    int counter, moveX, moveY, initialX;
    static boolean fadeout;
    Fade fade;
    Window_Level windowlevel;
    Window_Login windowlogin;
    Window_Register windowregister;

    public Window() {
        fade = new Fade();
        windowlevel = new Window_Level();
        windowlogin = new Window_Login();
        windowregister = new Window_Register();

    }

    public void showWindowFrame() {
        windowFrame = new JFrame();
        playframe = new PlayFrame();
        counter = 1;
        windowFrame.setUndecorated(true);

        /*
         *
         */
        // Determine if the GraphicsDevice supports translucency.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
            System.out.println("Translucency is not supported");
            //System.exit(0);
        } else
            windowFrame.setOpacity(0.90f);

        //windowFrame.setDefaultLookAndFeelDecorated(true);
        /*
         *
         */

        try {
            arrow = new JLabel(new ImageIcon(getClass().getResource("/images/menu images/arrow.gif")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            plusicon = new JLabel(new ImageIcon(getClass().getResource("/images/menu images/plus.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            blackback = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/blackback.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            play = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play_onclick.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            level = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            login = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            register = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sound = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            quit = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        play.addMouseListener(this);
        level.addMouseListener(this);
        register.addMouseListener(this);
        login.addMouseListener(this);
        quit.addMouseListener(this);
        sound.addMouseListener(this);
        plusicon.addMouseListener(this);
        windowFrame.addKeyListener(this);

        windowFrame.setBackground(Color.BLACK);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setLayout(null);
        windowFrame.setAlwaysOnTop(true);

        windowFrame.setSize(400, 600);
        blackback.setBounds(0, 0, 400, 600);
        play.setBounds(148, 140, 93, 49);
        level.setBounds(148, 220, 112, 39);
        login.setBounds(148, 300, 122, 49);
        register.setBounds(148, 380, 181, 49);
        sound.setBounds(148, 460, 141, 39);
        quit.setBounds(325, 561, 67, 32);
        arrow.setBounds(60, 140, 80, 40);
        plusicon.setBounds(338, 282, 80, 80);

        plusicon.setToolTipText("Instructions");

        windowFrame.setLocationRelativeTo(null);

        windowFrame.setResizable(false);

        moveX = (int) windowFrame.getLocation().getX();
        initialX = moveX;
        moveY = (int) windowFrame.getLocation().getY();

        windowFrame.add(play);
        windowFrame.add(level);
        windowFrame.add(login);
        windowFrame.add(register);
        windowFrame.add(quit);
        windowFrame.add(sound);
        windowFrame.add(arrow);
        windowFrame.add(plusicon);

        windowFrame.add(blackback);
        windowFrame.setVisible(true);


    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            if (counter == 1) {
                fade.startFading(windowFrame);
                windowFrame.dispose();
                //Main.background.instructionsframe.dispose();
                //System.out.println("Done");
                playframe.showplayFrame();
            }
            if (counter == 2) {
                fade.startFading(windowFrame);
                windowFrame.dispose();
                windowlevel.showWindowLevel();
            }
            if (counter == 3) {
                fade.startFading(windowFrame);
                windowFrame.dispose();
                windowlogin.showWindowLogin();
            }
            if (counter == 4) {
                fade.startFading(windowFrame);
                windowFrame.dispose();
                windowregister.showWindowRegister();
            }
            if (counter == 5) {
                fade.startFading(windowFrame);
            }
            if (counter == 6) {
                fade.startFading(windowFrame);
                System.exit(1);
            }
        }//enter

        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
//			/System.out.println(counter);
            counter += 1;
            if (counter > 6)
                counter = 1;
            changePic();
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println(counter);
            counter -= 1;
            if (counter < 1)
                counter = 6;
            changePic();
        }

        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            fade.startFading(windowFrame);
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == play) {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            //System.out.println("Done");
            playframe.showplayFrame();
        }
        if (me.getSource() == level) {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            windowlevel.showWindowLevel();
        }
        if (me.getSource() == login) {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            windowlogin.showWindowLogin();
        }
        if (me.getSource() == register) {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            windowregister.showWindowRegister();
        }
        if (me.getSource() == sound) {
            fade.startFading(windowFrame);
        }
        if (me.getSource() == quit) {
            fade.startFading(windowFrame);
            System.exit(1);
        }

        if (me.getSource() == plusicon) {
            Thread t = new Thread(this);
            t.start();
        }//plusiconclicked
    }//clicked()

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == plusicon) {
            try {
                plusicon.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/plus.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//exited

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == play) {
            counter = 1;
            changePic();
            play.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (me.getSource() == level) {
            counter = 2;
            changePic();
            level.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (me.getSource() == login) {
            counter = 3;
            changePic();
            login.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (me.getSource() == register) {
            counter = 4;
            changePic();
            register.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (me.getSource() == sound) {
            counter = 5;
            changePic();
            sound.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (me.getSource() == quit) {
            counter = 6;
            changePic();
            quit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (me.getSource() == plusicon) {
            plusicon.setCursor(new Cursor(Cursor.HAND_CURSOR));
            try {
                plusicon.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/plus_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//entered

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void changePic() {
        if (counter == 1) {
            arrow.setBounds(60, 140, 80, 40);
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (counter == 2) {
            arrow.setBounds(60, 220, 80, 40);
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (counter == 3) {
            arrow.setBounds(60, 300, 80, 40);
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (counter == 4) {
            arrow.setBounds(60, 380, 80, 40);
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (counter == 5) {
            arrow.setBounds(60, 460, 80, 40);
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (counter == 6) {
            arrow.setBounds(240, 555, 80, 40);
            try {
                play.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/play.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                level.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                login.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/login.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                register.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/register.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                quit.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/quit_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                sound.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/sound.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        if (moveX <= (int) windowFrame.getLocation().getX()) {
            fadeout = false;
            Main.background.showInstructionsFrame();
            for (int i = moveX; i >= (moveX / 4); i -= 2) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                windowFrame.setLocation(i, moveY);
            }
        }//if
        else {
            fadeout = true;
            Main.background.showInstructionsFrame();
            //new Thread(new Background()).start();
            for (int i = moveX / 4; i <= (initialX); i += 2) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                windowFrame.setLocation(i, moveY);
            }
        }
    }

}//class
