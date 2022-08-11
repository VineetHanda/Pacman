package menu;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window_Login implements KeyListener {
    //Instance varibales
    JFrame windowFrame;
    JLabel blackback;
    Fade fade;

    public Window_Login() {
        fade = new Fade();
    }//Constructor

    //Method
    public void showWindowLogin() {
        windowFrame = new JFrame();
        windowFrame.setUndecorated(true);

        // Determine if the GraphicsDevice supports translucency.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
            //System.out.println("Translucency is not supported");
            //System.exit(0);
        } else
            windowFrame.setOpacity(0.90f);

        try {
            blackback = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/menu images/blackback_login.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }


        blackback.setBounds(0, 0, 400, 600);

        windowFrame.add(blackback);

        windowFrame.setLayout(null);
        windowFrame.getContentPane().setBackground(Color.BLACK);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(400, 600);
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setResizable(false);
        windowFrame.addKeyListener(this);

        Background.backgroundframe.setVisible(true);
        windowFrame.setVisible(true);
        //windowFrame.addKeyListener(this);

    }//showWindowLogin()

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)//back
        {
            fade.startFading(windowFrame);
            windowFrame.dispose();
            Background.backgroundframe.setVisible(true);
            Main.window.showWindowFrame();
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}//class
