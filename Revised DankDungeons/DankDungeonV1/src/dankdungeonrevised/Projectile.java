
package dankdungeonrevised;

import java.awt.Graphics2D;


public abstract class Projectile {
    private int xPos;
    private int yPos;
    private int xDir;
    private int yDir;
    private int xToDest;
    private int yToDest;
    
    public Projectile(int _xPos, int _yPos, int _xToDest, int _yToDest)
    {
        xPos = _xPos;
        yPos = _yPos;
        xToDest = _xToDest;
        yToDest = _yToDest;
    }
    
    public void drawProjectile(Graphics2D g)
    {
        
    }
    
    public void moveProjectile()
    {
        
    }
}
