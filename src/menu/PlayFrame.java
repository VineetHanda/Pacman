package menu;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import levelone.PlayPanel;
import leveltwo.PlayPanelTwo;


public class PlayFrame implements KeyListener {
    public static JFrame playFrame;
    static PlayPanel playpanel;
    static PlayPanelTwo playpaneltwo;

    void showplayFrame() {
        playFrame = new JFrame();
        playpanel = new PlayPanel(); //1
        playpaneltwo = new PlayPanelTwo();

        playFrame.setUndecorated(true);
        //playFrame.setOpacity(0.90f);
        playFrame.addKeyListener(this);
        playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playFrame.setSize(700, 600);
        playFrame.setResizable(false);
        playFrame.setLocationRelativeTo(null);


        playFrame.setTitle("Game Started");
        playFrame.setLayout(null);

        //playpanel.setBackground(Color.BLACK);//2
        //playpanel.setLayout(null);//3

        playpaneltwo.setBackground(Color.black);
        playpaneltwo.setLayout(null);

        //playFrame.setLocation(280,50);

        playFrame.setContentPane(playpanel);//4
        //playFrame.setContentPane(playpaneltwo);
        Background.backgroundframe.setVisible(true);
        playFrame.setVisible(true);
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

}//class