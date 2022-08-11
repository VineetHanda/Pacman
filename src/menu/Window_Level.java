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


public class Window_Level implements KeyListener, MouseListener {

    JFrame windowFrame;
    JLabel blackback;
    JLabel arrow, back, level1_lab, level2_lab, level3_lab;
    int counter;
    Fade fade;

    public Window_Level() {
        fade = new Fade();
        //window=new Window();//not possible cz it will call constructor of Window which calls
        //constructor of Window_Level so recurssive call lead to stack overflow
    }//constructor

    public void showWindowLevel() {
        windowFrame = new JFrame();

        windowFrame.setUndecorated(true);
        counter = 1;
        /*
         *
         */
        // Determine if the GraphicsDevice supports translucency.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
            //System.out.println("Translucency is not supported");
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
            blackback = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/blackback.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            back = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/back.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            level1_lab = new JLabel(new ImageIcon(getClass().getResource("/images/menu images/level1_onclick.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            level2_lab = new JLabel(new ImageIcon(getClass().getResource("/images/menu images/level2.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            level3_lab = new JLabel(new ImageIcon(getClass().getResource("/images/menu images/level3.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        back.addMouseListener(this);
        level1_lab.addMouseListener(this);
        level2_lab.addMouseListener(this);
        level3_lab.addMouseListener(this);

        windowFrame.setBackground(Color.BLACK);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setLayout(null);

        windowFrame.setSize(400, 600);
        blackback.setBounds(0, 0, 400, 600);
        back.setBounds(325, 561, 74, 28);
        arrow.setBounds(60, 140, 80, 40);
        level1_lab.setBounds(148, 136, 120, 49);
        level2_lab.setBounds(148, 216, 120, 49);
        level3_lab.setBounds(148, 296, 120, 49);

        windowFrame.setLocationRelativeTo(null);

        windowFrame.setResizable(false);

        windowFrame.addKeyListener(this);

        windowFrame.add(back);
        windowFrame.add(arrow);
        windowFrame.add(level1_lab);
        windowFrame.add(level2_lab);
        windowFrame.add(level3_lab);

        windowFrame.add(blackback);
        Background.backgroundframe.setVisible(true);
        windowFrame.setVisible(true);
    }//showWindowLevel()

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            if (counter == 1) {
                fade.startFading(windowFrame);
                windowFrame.dispose();

            }
            if (counter == 2) {
                fade.startFading(windowFrame);
            }
            if (counter == 3) {
                fade.startFading(windowFrame);
            }
            if (counter == 4)//Back
            {
                fade.startFading(windowFrame);
                windowFrame.dispose();
                Background.backgroundframe.setVisible(true);
                Main.window.showWindowFrame();
            }
        }//enter

        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            counter += 1;
            if (counter > 4)
                counter = 1;
            changePic();
        }//down

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println(counter);
            counter -= 1;
            if (counter < 1)
                counter = 4;
            changePic();
        }//up


        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)//Back
        {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            Background.backgroundframe.setVisible(true);
            Main.window.showWindowFrame();
        }//escape

    }//keyPressed()

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == back) //Back
        {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            Background.backgroundframe.setVisible(true);
            Main.window.showWindowFrame();
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }//exited

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == back) {
            counter = 4;
            changePic();
            back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        if (me.getSource() == level1_lab) {
            counter = 1;
            changePic();
            level1_lab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        if (me.getSource() == level2_lab) {
            counter = 2;
            changePic();
            level2_lab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        if (me.getSource() == level3_lab) {
            counter = 3;
            changePic();
            level3_lab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//entered

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    public void changePic() {
        if (counter == 1) {
            arrow.setBounds(60, 140, 80, 40);
            try {
                back.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/back.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level1_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level1_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level2_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level2.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level3_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level3.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//counter==1
        else if (counter == 2) {
            arrow.setBounds(60, 220, 80, 40);
            try {
                back.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/back.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level1_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level1.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level2_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level2_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level3_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level3.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//counter==2
        else if (counter == 3) {
            arrow.setBounds(60, 300, 80, 40);
            try {
                back.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/back.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level1_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level1.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level2_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level2.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level3_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level3_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//counter==3
        else if (counter == 4) {
            arrow.setBounds(240, 555, 80, 40);
            try {
                back.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/back_onclick.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level1_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level1.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level2_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level2.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                level3_lab.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/level3.png"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//counter==4

    }//changePic()

}//class
