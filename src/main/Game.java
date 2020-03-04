/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author valmi
 */
public class Game extends Canvas implements Runnable {

    /**
     * @param args the command line arguments
     */
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH /12 * 9;
    private Thread thread;
    private boolean running = false;
    private Random r;
    
    private Handler handler;
    private HUD hud;
    
    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH, HEIGHT ,"Test Game", this);
        
        hud = new HUD();
        
        r = new Random();
        
        
        handler.addObject(new Player(WIDTH/2-32,HEIGHT / 2-32, ID.Player));
        
        handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy));
       
        
        
               
        
    }
    // what is a thread ?
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
     public synchronized void stop()
    {
       try
       {
          thread.join();
          running = false;
       }catch(Exception e)
       {
           e.printStackTrace();
       }
    }
     // algorithm to for the game to update itself
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames =0;
        while(running)
        {
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS " + frames);
                frames =0;
            }
        }
        stop();
    }
    
    private void tick()
    {
        handler.tick();
        hud.tick();
    }
    
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();// what is this ?
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        hud.render(g);
        
        g.dispose();
        bs.show();
    }
    
    public static int clamp(int var, int min, int max)
    {
       if( var >= max)
           return var=max;
       else if (var<= min)
           return var =min;
       else
           return var;
                   
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        new Game();
    }
    
}
