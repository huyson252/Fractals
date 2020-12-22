import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Color;
public class Tree {
	double branchRatio;
	double bendAngle;
	Graphics g;
	public Tree(double ratio, double angle, Graphics g) {
		branchRatio = ratio; 
		bendAngle = angle;
        this.g = g;
	}

	public void growTree(int gen, double x, double y, double branchAngle, double branchLength, float width, Color c) {
		double angle = Math.toRadians(branchAngle);
        double cx = x - Math.sin(angle) * branchLength;
        double cy = y - Math.cos(angle) * branchLength;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width));
        g2.setColor(c);
        g2.draw(new Line2D.Double(x, y, cx, cy));
        if (gen == 0) {
			return;
		} else if (gen <= 3) {
			growTree(gen-1, cx, cy, branchAngle - bendAngle, branchLength * branchRatio,width-0.3f, new Color(9,136,9));
			growTree(gen-1, cx, cy, branchAngle + bendAngle, branchLength * branchRatio,width-0.3f, new Color(9,136,9));
		} else {
			growTree(gen-1, cx, cy, branchAngle - bendAngle, branchLength * branchRatio,width-0.3f, c);
			growTree(gen-1, cx, cy, branchAngle + bendAngle, branchLength * branchRatio,width-0.3f, c);
		}
    }
 }
