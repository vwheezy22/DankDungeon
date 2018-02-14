
package dank.dungeon;


import static dank.dungeon.Projectile.numProjectiles;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
 
public class DankDungeon extends JFrame implements Runnable {
     boolean animateFirstTime = true;
    
     
    
    Image image;
    Graphics2D g;
    
    
    Image PlayerImage;
    Image PPImage;
    Image bgImage;
    
    Character player;
    
    Monster heart[] = new Monster[Monster.numMonsters];
    Image heartImage[] = new Image[Monster.numMonsters];
    
    Projectile playerProjectile[] = new Projectile[Projectile.numProjectiles];
    
    
        
    boolean gameOver;
        
    
    int timeCount;
    int currentProjectile;
    
    static DankDungeon frame1;

    public static void main(String[] args) {
        frame1 = new DankDungeon();
        frame1.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public DankDungeon() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                    int xpos = e.getX();
                    int ypos = e.getY();
                    
                    playerProjectile[currentProjectile].xpos = player.xpos;
                    playerProjectile[currentProjectile].ypos = player.ypos;
                    playerProjectile[currentProjectile].active = true;
                    currentProjectile ++;
                    if(currentProjectile == Projectile.numProjectiles)
                    {
                        currentProjectile = 0;
                    }
                    
                    //Point and click projectile code fail
//                    
//                    for(int i = 0; i < Projectile.numProjectiles; i ++)
//                    {
//                         if(xpos >= player.xpos-10 && xpos <=player.xpos +10)
//                         {
//                           if(ypos > player.ypos-50)
//                           {
//                               playerProjectile[i].ydir = 10;
//                               playerProjectile[i].xdir = 0;
//                           }
//                         }
//                        if(xpos > player.xpos)
//                        {
//                            playerProjectile[i].xdir=10;
//                        }
//                        else if (xpos < player.xpos)
//                        {
//                            playerProjectile[i].xdir = -10;
//                        }
//                        else if (xpos == player.xpos)
//                        {
//                             playerProjectile[i].xdir = 0;
//                        }
//
//                       if(ypos < player.ypos)
//                        {
//                            playerProjectile[i].ydir = 10;
//                        }
//                        else if (ypos > player.ypos)
//                        {
//                            playerProjectile[i].ydir = -10;
//                        }
//                        else if (ypos == player.ypos)
//                        {
//                             playerProjectile[i].ydir=0;
//                        }
//                       
//                      
//                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (gameOver)
                    return;
                if (e.VK_SPACE == e.getKeyCode())
                {
                   if(player.numJumps > 0) 
                   player.TelePortSkill();
                }
                
                if (e.VK_D == e.getKeyCode())
                {
                   player.moveRight();
                }
                if (e.VK_A == e.getKeyCode())
                {
                    player.moveLeft();

                }
                if (e.VK_W == e.getKeyCode())
                {
                    player.moveUp();
 
                }
                if (e.VK_S == e.getKeyCode())
                {
                   player.moveDown();
 
                }
                if (e.VK_P == e.getKeyCode())
                {
                    Window.pauseFunc();
                }
                

                repaint();
            }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
        int width = image.getWidth(this);
        int height = image.getHeight(this);

//fill background
        g.setColor(Color.black);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);

        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.white);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        Window.Draw(g, bgImage, width, height, this );
        
        player.Draw(g,PlayerImage,width,height,this);
        
        
        for(int i = 0; i < Monster.numMonsters; i++)
        {
                if(heart[i].isDrawn)
        heart[i].Draw(g, heartImage[i], width, height, this);
        }

        for(int i = 0 ; i < Projectile.numProjectiles; i++)
        {
        if(playerProjectile[i].active)
        playerProjectile[i].Draw(g, PPImage, width, height, this);
        }
        

        if(player.getDirDec() == 1)
        PlayerImage = Toolkit.getDefaultToolkit().getImage("./CatManRight.PNG");            
        if(player.getDirDec() == 2)
        PlayerImage = Toolkit.getDefaultToolkit().getImage("./CatManLeft.PNG");
        if(player.getDirDec() == 3)
        PlayerImage = Toolkit.getDefaultToolkit().getImage("./CatManBack.PNG");
        if(player.getDirDec() == 4)
        PlayerImage = Toolkit.getDefaultToolkit().getImage("./CatManFront.PNG");
       
        if(Window.pause)
        Window.pauseScreen(g);
          
        
        
       if (gameOver)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial Black",Font.PLAIN,70));
            g.drawString("Game Over",300,500); 
        }          
        gOld.drawImage(image, 0, 0, null);
    }
    
    
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            if(!Window.pause)
            animate();
            repaint();
            double seconds = 1/TimeCount.frameRate;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {    
        
        TimeCount.init();
        gameOver = false;
        
        player = new Character(10, 3);
        
        for(int i = 0; i < Projectile.numProjectiles; i ++)
        {
        playerProjectile[i] = new Projectile(player);
        playerProjectile[i].active =false;
        
        
        }
        
        timeCount=0;
        
        for(int i = 0; i < Monster.numMonsters; i++)
        heart[i] = new Monster();
        
       
    }
    
    
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            bgImage = Toolkit.getDefaultToolkit().getImage("./bgImage.GIF");
            PlayerImage = Toolkit.getDefaultToolkit().getImage("./CatManBack.PNG");
            PPImage = Toolkit.getDefaultToolkit().getImage("./Shuriken.GIF");
                    
            for(int i = 0; i < Monster.numMonsters; i++)
            heartImage[i] = Toolkit.getDefaultToolkit().getImage("./baddy.gif");
            reset();
  
        }
        

        if (gameOver)
            return;
        
        for(int i = 0; i < Projectile.numProjectiles; i ++)
        playerProjectile[i].shootFunc(player);
        
        
        for(int i = 0; i < Monster.numMonsters; i++)
        {
        heart[i].MonsterChase(player);
        }
            
            for(int i = 0; i < Monster.numMonsters; i++)
            {

            if(timeCount % 10==9)
            {
                if (player.xpos< heart[i].xpos+50 && player.xpos> heart[i].xpos-50 && player.ypos< heart[i].ypos+50 && player.ypos> heart[i].ypos-50)
                {
                    if (heart[i].isDrawn)
                    gameOver=true;
                }
            }
            
            if (playerProjectile[i].xpos< heart[i].xpos+20 &&playerProjectile[i].xpos> heart[i].xpos-20 && playerProjectile[i].ypos< heart[i].ypos+20 && playerProjectile[i].ypos> heart[i].ypos-20)
            {
                heart[i].isDrawn=false;
                playerProjectile[i].active =false;
            }
              

             
            }
            
            
            
            TimeCount.addTime();
    
    }



//    if(timeCount % 30==29)
//    {
//        Monster.numMonsters+=1;
//    }


    


////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }

}



////////////////////////////////////////////////////////////////////
class Character
{
    public int xpos;
    public int ypos;
    public int speed;
    public int dirDec;
    public int numJumps;
    
     Character(int _speed,int  _numJumps)
    {
        numJumps = _numJumps;
        ypos = Window.getHeight2()/2;
        speed = _speed;
        xpos = Window.getWidth2()/2;
       
        
    }
     
    public void TelePortSkill()
    {
       {
       boolean keepLooping = true;
       while(keepLooping)
       {

       xpos=(int) (Math.random()*Window.getWidth2());
       ypos=(int) (Math.random()*Window.getHeight2());

       if(xpos >= 0 && xpos <= Window.getWidth2() && ypos >=0 && ypos <=Window.getHeight2())
           keepLooping = false;

       }
       numJumps --;
       }
    }
    
    public void moveRight()
    {
        if(xpos < Window.getWidth2()-50)           
        xpos += speed;
        dirDec=1;
        
    }
    public void moveLeft()
    {
        if(xpos > 50)
        xpos -= speed;
        dirDec=2;
    }
    public void moveUp()
    {
        if(ypos < Window.getHeight2()-50)
        ypos += speed;
        dirDec = 3;
    }
    public void moveDown()
    {
        if(ypos > 50)
        ypos -= speed;
        dirDec = 4;
    }
    
    public int getDirDec()
    {
        return(dirDec);
    }
    
    public int getXPos()
    {
        return xpos;
    }
    public int getYPos()
    {
        return ypos;
    }
    
    public void Draw(Graphics2D g,Image image,int width,int height,DankDungeon obj)
    {
        drawPlayer(g,Window.getX(xpos),Window.getYNormal(ypos),0,.05,.05,    image,width,height,obj);
    }
    public void drawPlayer(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale,     Image image,int width,int height,DankDungeon obj)
    {
                g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
//        g.fillRect(-10,-10,20,20);
        g.drawImage(image,-width/2,-height/2,width,height,obj); 
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
}
////////////////////////////////////////////////////////////////////
class Cannon
{
    
    
}

class Projectile
{
    int xpos;
    int ypos;
    int xdir;
    int ydir;
    boolean active;
    public static int numProjectiles = 10;
    
    Projectile(Character player)
    {
        
        xpos = player.getXPos();
        ypos = player.getYPos();
        xdir = 0;
        ydir = 0;
        active =  false;
    }
    
    public void Draw(Graphics2D g,Image image,int width,int height,DankDungeon obj)
    {
          drawProjectile(g,Window.getX(xpos),Window.getYNormal(ypos),0,.05,.05,    image,width,height,obj);
    }
    public void drawProjectile(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale,     Image image,int width,int height,DankDungeon obj)
    {
                g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
//        g.fillRect(-10,-10,20,20);
        g.drawImage(image,-width/2,-height/2,width,height,obj); 
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public void shootFunc(Character player)
    {
        //comment everything besides adding of xdirs and ydirs if testing point and click projectile mouse function
        if(player.getDirDec() == 1)
        {
            xdir = 10;
            ydir = 0;
        }
        else if(player.getDirDec() == 2)
        {
            xdir = -10;
            ydir = 0;
        }
        else if(player.getDirDec() == 3)
        {
            xdir = 0;
            ydir = 10;
        }
        else if(player.getDirDec() == 4)
        {
            xdir = 0;
            ydir = -10;
        }
            
            xpos+=xdir;
            ypos+=ydir;
         
    }
    
    
    public int getXPos()
    {
        return(xpos);
    }
    public int getYPos()
    {
        return(ypos);
    }
    public int getXDir()
    {
        return(xdir);
    }
    public int getYDir()
    {
        return(ydir);
    }
    public boolean getActive()
    {
        return(active);
    }
            
}



class Monster
{
    public int xpos;
    public int ypos;
    public static int numMonsters = 10;
    public static int numMonstersAdder;
    public int Xdir;
    public int Ydir;
    public boolean isDrawn;
    
    
    Monster()
    {
        isDrawn=true;
        Xdir=0;
        Ydir=0;
        xpos = (int)(Math.random()*Window.getWidth2());
        ypos = (int)(Math.random()*Window.getHeight2());
        
    }
    
    public void Draw(Graphics2D g,Image image,int width,int height,DankDungeon obj)
    {
     drawMonster(g,Window.getX(xpos),Window.getYNormal(ypos),0,.2,.2,    image,width,height,obj);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawMonster(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale,     Image image,int width,int height,DankDungeon obj)
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
    
    public void MonsterChase(Character player)
    {
                
            if(player.xpos > xpos)
             {
                 Xdir=1;
             }
             else if (player.xpos < xpos)
             {
                 Xdir=-1;
             }
             else if (player.xpos == xpos)
             {
                  Xdir= 0;
             }
             
            if(player.ypos > ypos)
             {
                 Ydir = 1;
             }
             else if (player.ypos < ypos)
             {
                 Ydir=-1;
             }
             else if (player.ypos == ypos)
             {
                  Ydir=0;
             }
            xpos+=Xdir;
            ypos+=Ydir;
    }
    
    
        
}


class Window
{
    public static boolean pause = false;
    static final int XBORDER = 65;
    static final int YBORDER = 65;
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
    
    public static void Draw(Graphics2D g,Image image,int width,int height,DankDungeon obj)
    {
     drawBackground(g,Window.getX(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/2),0,.85,.85,    image,width,height,obj);
    }
////////////////////////////////////////////////////////////////////////////
    public static void drawBackground(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale,     Image image,int width,int height,DankDungeon obj)
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

class TimeCount
{

    public static int timeCount;
    public static double frameRate = 25.0;
    
    
    public static void addTime()
    {
        timeCount++;
    }
    
    public static boolean update(int _val)
    {
        return(timeCount % _val == _val -1);
    }
    
    public static void init()
    {
        timeCount = 0;
    }

}

