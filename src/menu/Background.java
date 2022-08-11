package menu;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Background implements Runnable {
    public static JFrame backgroundframe;
    JFrame instructionsframe;
    JLabel instructions;
    int width, height;
    Thread t;

    void showBackground() {
        backgroundframe = new JFrame();
        backgroundframe.setUndecorated(true);
        backgroundframe.getContentPane().setBackground(Color.black);
        backgroundframe.setOpacity(0.60f);
        backgroundframe.setLayout(null);
        
        
        /*Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        width=(int)screensize.getWidth();
        height=(int)screensize.getHeight();*/

        width = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
        height = (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();

        backgroundframe.setFocusableWindowState(false);

        //backgroundframe.setAlwaysOnTop(false);
        //backgroundframe.setEnabled(false);

        //instructions.setBounds(width/2,height/14,600,600);


        backgroundframe.setBounds(0, 0, width, height);
        //instructionsframe.setVisible(true);
        backgroundframe.setVisible(true);

    }

    void showInstructionsFrame() {
        if (!Window.fadeout) {
            instructionsframe = new JFrame();
            instructionsframe.setUndecorated(true);
            instructionsframe.getContentPane().setBackground(Color.black);
            instructionsframe.setOpacity(0.00f);
            instructionsframe.setLayout(null);

            try {
                instructions = new JLabel(new ImageIcon(getClass().getResource("/images/menu images/instructions.png")));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            instructionsframe.setFocusableWindowState(false);

            instructions.setBounds(120, 0, 680, 600);

            //instructionsframe.setBounds(width/2,(height/11)-2,600,600);

            instructionsframe.setSize(700, 600);
            instructionsframe.setResizable(false);
            instructionsframe.setLocationRelativeTo(null);

            instructionsframe.add(instructions);

            instructionsframe.setVisible(true);
        }//if
        instructionsframe.setVisible(true);

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        if (!Window.fadeout) {
            float o = 0.0f;
            for (; o <= 0.90f; o += .0071f) {
                instructionsframe.setOpacity(o);
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }//if
        else {
            float o = 0.90f;
            for (; o >= 0.0f; o -= .0071f) {
                instructionsframe.setOpacity(o);
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            instructionsframe.dispose();
        }//else

    }

}//class
