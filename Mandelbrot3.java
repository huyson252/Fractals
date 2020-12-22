import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;
 
public class Mandelbrot3{
 
    private int MAX_ITER = 100;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
    JPanel a;
 
    public Mandelbrot3(JPanel a,double size, double xinit, double yinit,int xMove, int yMove, int colorIter) {
        this.a = a;
        I = new BufferedImage(a.getWidth(), a.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < a.getHeight(); y++) {
            for (int x = 0; x < a.getWidth(); x++) {
                zx = zy = 0;
                cX = ((x - xinit) / size)+xMove/size;
                cY = ((y - yinit) / size)+yMove/size;
               
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << colorIter));
            }
        }
    }
 
    public void display(Graphics g) {
        g.drawImage(I, 0, 0, a);
    }
}
