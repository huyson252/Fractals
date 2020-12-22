import java.awt.Graphics;
import java.awt.Color;
public class Koch {
	Snail s;
	Graphics g;
	public Koch(Snail s,Graphics g) {
		this.s = s;
		this.g = g;
	}
	public void kochLine(double d, int gen) {
		if (gen == 0) {
			s.move(d,g);
		} else {
		kochLine(d/3,gen-1);
		s.turnRight(-60);
		kochLine(d/3,gen -1);;
		s.turnRight(120);
		kochLine(d/3,gen -1);
		s.turnRight(-60);
		kochLine(d/3,gen -1);
		}
	}
	public void kochSnow(double side, int gen) {
		s.turnRight(60);
		kochLine(side,gen);
		s.turnRight(120);
		kochLine(side,gen);
		s.turnRight(120);
		kochLine(side,gen);
	}
	public void antiKochLine(double d, int gen) {
		if (gen == 0) {
			s.move(d,g);
		} else {
		antiKochLine(d/3,gen-1);
		s.turnRight(60);
		antiKochLine(d/3,gen -1);;
		s.turnRight(-120);
		antiKochLine(d/3,gen -1);
		s.turnRight(60);
		antiKochLine(d/3,gen -1);
		}
	}
	public void antiKochSnow(double side, int gen) {
		s.turnRight(60);
		antiKochLine(side,gen);
		s.turnRight(120);
		antiKochLine(side,gen);
		s.turnRight(120);
		antiKochLine(side,gen);
	}
	
}
