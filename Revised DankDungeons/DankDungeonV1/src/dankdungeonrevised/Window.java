
package dankdungeonrevised;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

class Window
{
    public static boolean pause = false;
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 800;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 800;
    static int xsize = -1;
    static int ysize = -1;

    public static int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public static int getY(int y) {
        return (y + YBORDER + YTITLE );
    }

    public static int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    public static int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

    public static int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    } 
    
    public static void Draw(Graphics2D g,Image image,int width,int height,DankDungeonRevised obj)
    {
     drawBackground(g,Window.getX(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/2),0,.85,.85,    image,width,height,obj);
    }
////////////////////////////////////////////////////////////////////////////
    public static void drawBackground(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale,     Image image,int width,int height,DankDungeonRevised obj)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
    //    g.fillRect(-10,-10,20,20);
        g.drawImage(image,-width/2,-height/2,width,height,obj); 
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
    public static void pauseFunc()
    {
            pause = !pause;
        
    }
    public static void pauseScreen(Graphics2D g)
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        g.setColor(Color.white);
        g.setFont(new Font("Arial Black",Font.PLAIN,70));
        g.drawString("Pause",300,500); 
    }
}