package menu;

import javax.swing.JFrame;

public class Fade {
    float o;

    public void startFading(JFrame frame) {
        o = 0.90f;
        while (o > 0.1f) {
            o = o - 0.09f;
            frame.setOpacity(o);
            try {
                Thread.sleep(80);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//while

    }//startFading()
}//class
