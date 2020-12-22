import java.awt.*;

public class PlanetSystem{
    Color c;
    public PlanetSystem(Color c){
        this.c=c;
    }
    
    public void drawCircle(Graphics g, int x,int y,int radius,int gen) {
        g.setColor(c);
        g.drawOval((int)(x/2 - radius), (int)(y/2 - radius),2* radius, 2*radius);
        if(gen > 0) {
            drawCircle(g,x-radius-(int)(radius/2),y,radius/2,gen-1);
            drawCircle(g,x+radius+(int)(radius/2),y,radius/2,gen-1);
        }
    }
    
    public void drawCircle2(Graphics g, int x,int y,int radius,int gen) {
        g.setColor(c);
        g.drawOval((int)(x/2 - radius), (int)(y/2 - radius),2* radius, 2*radius);
        if(gen > 0) {
            drawCircle2(g,x-radius-(int)(radius/2),y,radius/2,gen-1);
            drawCircle2(g,x+radius+(int)(radius/2),y,radius/2,gen-1);
            drawCircle2(g,x,y-radius-(int)(radius/2),radius/2,gen-1);
            drawCircle2(g,x,y+radius+(int)(radius/2),radius/2,gen-1);
        }
    }
}
