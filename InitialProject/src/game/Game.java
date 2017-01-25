package game;

import display.Display;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    private String title;

    private Display display;
    private BufferStrategy bs;
    private Graphics g;

    private SpriteSheet sh;

    private Thread thread;
    private boolean isRunning;

    //Testing
    private int width = 95;
    private int height = 130;
    private int i = 0;


    public Game(String name) {
        this.title = name;
    }

    private void init() {
        this.display = new Display(this.title);

        this.sh = new SpriteSheet("/textures/player.png");
    }

    private void tick() {
        i++;
        if (i >= 7) {
            i = 0;
        }

    }

    private void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = this.bs.getDrawGraphics();

        g.clearRect(0, 0, Display.WIDTH, Display.HEIGHT);

        //Start drawing



            g.drawImage(this.sh.crop(i * width, 0 * height, width, height), 0, 0, null);


        //END drawing
        this.g.dispose();
        this.bs.show();

    }

    @Override
    public void run() {
        this.init();

        while (isRunning) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.tick();
           this.render();
        }

        this.stop();
    }

    public synchronized void start() {
        this.thread = new Thread(this);

        this.isRunning = true;
        this.thread.start();
    }

    public synchronized void stop() {
        try {
            this.isRunning = false;
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
