package menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public abstract class Game extends JPanel implements KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public Game() {
        super();
        this.setFocusable(true);
        this.addKeyListener(this);
    }//constructor

    public abstract void Draw(Graphics2D g2d);

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        Draw(g2d);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}//class
