import com.sun.deploy.cache.BaseLocalApplicationProperties;
import processing.core.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class RunGraphicalGame extends PApplet {
    GameBoard game;
    Display display;
    Location position;
    Timer time;
    TimerTask t;
    static int sec;
    static int y = 0;

    public void settings() {
        size(640, 550);
    }

    public void setup() {
        // Create a game object
        game = new GameBoard(4, 4);

        sec = 0;


        display = new Display(this, 10, 10, 400, 400);

        //display.setColor(1, color(255, 0, 0)); // SET COLORS FOR PLAYER 1 & 2
        display.setColor(2, color(0, 225, 0));
        display.setColor(4, color(225, 255, 0));
        display.setColor(8, color(255, 192, 203));
        display.setColor(16, color(128, 0, 128));
        display.setColor(32, color(200, 0, 225));
        display.setColor(64, color(200, 0, 225));
        display.setColor(128, color(75, 196, 213));
        display.setColor(256, color(128, 175, 175));
        display.setColor(512, color(255, 255, 204));
        display.setColor(1024, color(120, 128, 0));
        display.setColor(2048, color(132, 157, 204));





//         display.setImage(1, "assets/on.png");
//         display.setImage(2, "assets/off.png");

        display.initializeWithGame(game);



    }


    @Override
    public void draw() {
        background(200);
        display.drawGrid(game.getGrid()); // display the game

        display.displayTextOnGrid(game.getGrid(), (int) 22.5);


        if (game.isGameOver()) {
            textSize(64);
            text("Game Over!", 100, 200);
            ellipse(700,y,0,0);
            timer();
        }
    }

    public void keyReleased() {

        System.out.println("hit key ");


        int keyValue = -1;
        if (key == CODED) {
            if (keyCode == UP) {
                keyValue = 0;
            }
            if (keyCode == DOWN) {
                keyValue = 1;
            }
            if (keyCode == LEFT) {
                keyValue = 2;
            }
            if (keyCode == RIGHT) {
                keyValue = 3;
            }


            try {
                game.move(keyValue);
            } catch (Exception ignored) {
                System.out.println("invalid press ");
            }


        }
    }


    public static void timer(){
        y+=10;
        try {
            Thread.sleep(4);
        }catch (InterruptedException ignored){

        }
        if(y>800) {
            sec += 1;
        }

        if(sec>5){
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        PApplet.main(new String[]{"RunGraphicalGame"});
    }
}