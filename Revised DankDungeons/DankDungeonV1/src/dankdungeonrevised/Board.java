/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dankdungeonrevised;

import java.awt.*;

/**
 *
 * @author vinwh
 */
public class Board {
    Player mainChar;
    
    public Board()
    {
        mainChar = new Player(Window.getX(Window.getWidth2()/2), Window.getYNormal(Window.getY(0)));
        
    }
    
    public void DrawBoard(Graphics2D g)
    {
        mainChar.drawPlayer(g, mainChar.getXPos(), mainChar.getYPos(), 0, 3, 3);
        
    }
    
    
    
    
}
