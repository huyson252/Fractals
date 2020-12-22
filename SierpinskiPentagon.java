import java.awt.*;

public class SierpinskiPentagon{
    PolygoneRegulier sierpinskiPentagon;
    Color colour;
    
    public SierpinskiPentagon(APoint centre,APoint sommet,Color colour){
        this.colour=colour;
        sierpinskiPentagon = new  PolygoneRegulier(centre,sommet,5,colour);
    }
    
    public SierpinskiPentagon(APoint centre,APoint sommet){
        sierpinskiPentagon = new  PolygoneRegulier(centre,sommet,5,colour);
    }
    
    public void display(Graphics g,int n){
        if(n==0){
            sierpinskiPentagon.dessine(g);
        }
        recursion(sierpinskiPentagon,g,n);
        
	}
    
    public void recursion(PolygoneRegulier pentagon,Graphics g,int n){
        double cx =pentagon.centre.x;
        double cy =pentagon.centre.y;
        double vx =pentagon.vertex.x;
        double vy =pentagon.vertex.y;
        double mx =(2*(cy-vy)/3)*Math.cos(Math.toRadians(18));
        double cos72 = Math.cos(Math.toRadians(72));
        double sin72 = Math.sin(Math.toRadians(72));
        double cos18 = Math.cos(Math.toRadians(18));
        double sin18 = Math.sin(Math.toRadians(18));
        double cos36 = Math.cos(Math.toRadians(36));
        double sin36 = Math.sin(Math.toRadians(36));
        double sin54 = Math.sin(Math.toRadians(54));
        double cos54 = Math.cos(Math.toRadians(54));
        double r =(cy-vy) / (1 + (2*cos36));
        double a = (r/sin54)*sin36 ;
        
        APoint centre1 = new APoint(pentagon.points[0].x,vy + r);
        PolygoneRegulier innerPentagon1 = new PolygoneRegulier(centre1,pentagon.vertex,5,Color.red);
            
        APoint centre5 = new APoint(cx - (2*r*cos36)*cos18,cy - (2*r*cos36)*sin18);
        APoint vertex5 = new APoint(cx - (2*r*cos36)*cos18,cy - ((2*r*cos36)*sin18) - r);
        PolygoneRegulier innerPentagon5 = new PolygoneRegulier(centre5,vertex5,5,Color.blue);
            
        APoint centre2 = new APoint(cx + (2*r*cos36)*cos18,cy - (2*r*cos36)*sin18);
        APoint vertex2 = new APoint(cx + (2*r*cos36)*cos18,cy - ((2*r*cos36)*sin18) - r);
        PolygoneRegulier innerPentagon2 = new PolygoneRegulier(centre2,vertex2,5,Color.yellow);
            
        APoint centre4 = new APoint(pentagon.points[3].x + r*cos54,pentagon.points[3].y - r*sin54);
        PolygoneRegulier innerPentagon4 = new PolygoneRegulier(centre4,innerPentagon5.points[2],5,Color.green);
            
        APoint centre3 = new APoint(pentagon.points[2].x - r*cos54,pentagon.points[2].y - r*sin54);
        PolygoneRegulier innerPentagon3 = new PolygoneRegulier(centre3,innerPentagon2.points[3],5,Color.cyan);

        if(n==1){
            //g.setColor(Color.black);
           // g.fillRect(0,0,1250,850);
            innerPentagon1.dessine1(g);
            innerPentagon2.dessine1(g);
            innerPentagon3.dessine1(g);
            innerPentagon4.dessine1(g);
            innerPentagon5.dessine1(g);
        }else if(n>1){
            recursion(innerPentagon1,g,n-1);
            recursion(innerPentagon2,g,n-1);
            recursion(innerPentagon3,g,n-1);
            recursion(innerPentagon4,g,n-1);
            recursion(innerPentagon5,g,n-1);

         }
    }
}
