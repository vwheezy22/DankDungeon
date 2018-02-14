package dankdungeonrevised;

import java.util.*;
import java.awt.*;

class Player
{
    private int xPos;
    private int yPos;
    private ArrayList<Projectile>projectiles;
    
    public Player(int _xPos, int _yPos)
    {
        xPos = _xPos;
        yPos = _yPos;
    }
    //will draw the player with an image
    public void Draw(Graphics2D g,Image image,int width,int height,DankDungeonRevised obj)
    {
        //drawPlayer(g,Window.getX(xPos),Window.getYNormal(yPos),0,.05,.05,    image,width,height,obj);
    }
    
    public void drawPlayer(Graphics2D g, int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.setColor(Color.red);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.fillOval(-10,-10,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
    public int getXPos()
    {
        return(xPos);
    }
    
    public int getYPos()
    {
        return(yPos);
    }
    
    public ArrayList<Projectile> getProjectiles()
    {
        return(projectiles);
    }
    
}
