package levelone;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice.WindowTranslucency;

import levelone.PlayPanel;
import menu.PlayFrame;

public class Time implements Runnable {
    public static Thread t;
    static boolean lasttensec;

    public Time() {
        t = new Thread(this);
        t.start();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        //while(PlayPanel.gameState==PlayPanel.GAMESTATE.PLAYING)
        lasttensec = false;
        while (true) {
            try {
                Thread.sleep(1000);
                //System.out.println("Running");

                if ((Integer.parseInt(PlayPanel.sec)) <= 0 && Integer.parseInt(PlayPanel.min) <= 0) {
                    PlayPanel.timeup = true;
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice gd = ge.getDefaultScreenDevice();

                    //If translucent windows aren't supported, exit.
                    if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
                        //System.out.println("Translucency is not supported");
                        //System.exit(0);
                    } else
                        PlayFrame.playFrame.setOpacity(0.9f);

                    PlayPanel.gameState = PlayPanel.GAMESTATE.GAMEOVER;
                    t.stop();
                } else {
                    PlayPanel.timeup = false;
                    if ((Integer.parseInt(PlayPanel.sec)) == 0) {
                        PlayPanel.sec = "60";
                        PlayPanel.min = (new Integer(Integer.parseInt(PlayPanel.min) - 1)).toString();
                    }
                    if (Integer.parseInt(PlayPanel.sec) <= 10) {
                        PlayPanel.sec = "0" + (new Integer(Integer.parseInt(PlayPanel.sec) - 1)).toString();
                    } else
                        PlayPanel.sec = (new Integer(Integer.parseInt(PlayPanel.sec) - 1)).toString();

                    if (Integer.parseInt(PlayPanel.sec) <= 10 && (Integer.parseInt(PlayPanel.min) == 0)) {
                        lasttensec = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//while
    }//run

}
