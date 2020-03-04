/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author valmi
 */
public class Player extends GameObject {
Random r = new Random();
    public Player(int x, int y, ID id) {
        super(x, y, id);
        
    
       
        
    }

    // updates the game
    public void tick() 
    {
       x+= velX;
       y+= velY;
       
         
//        if (y <=0 || y>= Game.HEIGHT-32)
//        {
//            velY*=-1;
//        }
//        if (x <=0 || x>= Game.WIDTH-16)
//        {
//            velX*=-1;
//        }
        
    x = Game.clamp(x, 0, Game.WIDTH -36);
    y = Game.clamp(y, 0, Game.HEIGHT -64);
       
    }

    // renders the players.
    public void render(Graphics g)
    {
     g.setColor(Color.WHITE);
     g.fillRect(x, y, 32, 32);
    }
    
    
}
