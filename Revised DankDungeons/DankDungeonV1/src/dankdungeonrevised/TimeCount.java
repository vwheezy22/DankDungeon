
package dankdungeonrevised;

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
