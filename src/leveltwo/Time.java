package leveltwo;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice.WindowTranslucency;

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
        //while(PlayPanelTwo.gameState==PlayPanelTwo.GAMESTATE.PLAYING)
        lasttensec = false;
        while (true) {
            try {
                Thread.sleep(1000);
                //System.out.println("Running");

                if ((Integer.parseInt(PlayPanelTwo.sec)) <= 0 && Integer.parseInt(PlayPanelTwo.min) <= 0) {
                    PlayPanelTwo.timeup = true;
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice gd = ge.getDefaultScreenDevice();

                    //If translucent windows aren't supported, exit.
                    if (!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT)) {
                        //System.out.println("Translucency is not supported");
                        //System.exit(0);
                    } else
                        PlayFrame.playFrame.setOpacity(0.9f);

                    PlayPanelTwo.gameState = PlayPanelTwo.GAMESTATE.GAMEOVER;
                    t.stop();
                } else {
                    PlayPanelTwo.timeup = false;
                    if ((Integer.parseInt(PlayPanelTwo.sec)) == 0) {
                        PlayPanelTwo.sec = "60";
                        PlayPanelTwo.min = (new Integer(Integer.parseInt(PlayPanelTwo.min) - 1)).toString();
                    }
                    if (Integer.parseInt(PlayPanelTwo.sec) <= 10) {
                        PlayPanelTwo.sec = "0" + (new Integer(Integer.parseInt(PlayPanelTwo.sec) - 1)).toString();
                    } else
                        PlayPanelTwo.sec = (new Integer(Integer.parseInt(PlayPanelTwo.sec) - 1)).toString();

                    if (Integer.parseInt(PlayPanelTwo.sec) <= 10 && (Integer.parseInt(PlayPanelTwo.min) == 0)) {
                        lasttensec = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//while
    }//run

}
