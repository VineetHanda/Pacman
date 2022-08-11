package menu;

public class Main {
    public static Window window;
    static Background background;

    public static void main(String[] args) {
        window = new Window();
        window.showWindowFrame();
        background = new Background();
        background.showBackground();
    }//main
}//class
