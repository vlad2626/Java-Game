/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author valmi
 */
public class BasicEnemy  extends GameObject{

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        
        velX =5;
        velY = 5;
    }

    
    public void tick() {
        x += velX;
        y += velY;
        
        if (y <=0 || y>= Game.HEIGHT-32)
        {
            velY*=-1;
        }
        if (x <=0 || x>= Game.WIDTH-16)
        {
            velX*=-1;
        }
       
    }

    
    public void render(Graphics g) 
    {
     g.setColor(Color.red);
     g.fillRect(x, y, 16, 16);
    }
    
}
