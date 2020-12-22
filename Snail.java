import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.geom.Line2D;
import java.awt.Color;
public class Snail extends APoint {
	private double angle;// clockwise angle from the x axis
    private Color c;
	
	public Snail(double xs, double ys, double angle) {
		super(xs, ys);
		this.angle = angle;
	}
    
    public Snail(double xs, double ys, double angle, Color c) {
		super(xs, ys);
		this.angle = angle;
        this.c=c;
	}
	//rotate in the clockwise direction
	public void turnRight(double theta) {
		this.angle += theta;
	}
	//move and leave a trace (a line)
	public void move(double distance, Graphics g) { 
		double oldX = this.x;
		double oldY = this.y;
		this.x = oldX + distance * Math.cos(Math.toRadians(angle));
		this.y = oldY + distance * Math.sin(Math.toRadians(angle));
		Graphics2D g2 = (Graphics2D) g;
        g2.setColor(c);
		g2.draw(new Line2D.Double(oldX,oldY,this.x,this.y));
	} 
}
