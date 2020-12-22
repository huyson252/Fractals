import java.util.LinkedList;
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JColorChooser;

public class Fractals extends JFrame implements ActionListener,ChangeListener{
        private JButton[] ctrlBtns; 
		private JPanel btnPanel;
		private Color themeColor; 
		private JPanel ctrlPanel;
		private int numIter;
		private double size;
		private double zoomIncrease;
		private int colorIter;
		private int xMove;
        private int yMove;
      
        JLabel info;
        JLabel info1;
        JLabel info2;
        JLabel author;
        JComboBox combo;
        JButton button;
        JButton colourChoice;
        JButton next;
        JButton previous;
        JSlider angle;
        GPanel panel2;
        ImagePanel panel1;
        JPanel panelMain;
        String s1;
        int gen;
        Color background;
        Color pattern;
        double treeAngle;
        Color treeColour;
        int treeGen;
        int pentaGen;
        int w;
        int h;


        public Fractals(){
            s1="";
            gen=0;
            treeGen=0;
            pentaGen=0;
            background=Color.black;
            pattern= Color.white;
            treeAngle=40;
            treeColour = pattern;
        
            this.setTitle("Fractals");
            Toolkit tk = Toolkit.getDefaultToolkit();
            w = ((int) tk.getScreenSize().getWidth());
            h = ((int) tk.getScreenSize().getHeight());
            
            size = w/4;
            numIter = h/18;
            ctrlBtns = new JButton[9];
            themeColor = new Color(150,180,200);
            zoomIncrease = h/9;
            colorIter = h/45;
            xMove=0;
            yMove = 0;
            
            this.setBounds(0,0,w,h);
            setLayout(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            
            author = new JLabel();
            author.setText("Authors: Danen, Huy, Ariven and Merwane");
            author.setBounds(w/50,h - (h/10),w/4,h/20);
            author.setForeground(Color.black);
            
            info = new JLabel();
            info.setText("Choose your pattern");
            info.setFont(new Font("Serif", Font.PLAIN, 24));
            info.setBounds(w/50,h/50,w/5,h/20);
            info.setForeground(Color.black);
            
            info1 = new JLabel();
            info1.setText("Choose your generation");
            info1.setFont(new Font("Serif", Font.PLAIN, 24));
            info1.setBounds(w/50,(h/5) - (h/30),w/5,h/20);
            info1.setForeground(Color.black);
            
            info2 = new JLabel();
            info2.setText("Choose your angle");
            info2.setFont(new Font("Serif", Font.PLAIN, 24));
            info2.setBounds(w/50,(h/2) - (h/10),w/5,h/20);
            info2.setForeground(Color.black);
            info2.setVisible(false);
            
            button= new JButton("Dark/Bright mode");
            button.setBounds(w/30,h - (h/4),w/7,h/20);
            button.addActionListener(this);
            
            colourChoice= new JButton("Choose your colour");
            colourChoice.setBounds(w/30,h/2,w/7,h/20);
            colourChoice.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        pattern = JColorChooser.showDialog(null,"Pick your colour",pattern);
                        if(pattern==null){
                            pattern = Color.white;
                        }
                        repaint();
                    }
                }
            );
            
            next = new JButton(new ImageIcon("right.png"));
            next.setBounds(w/7,h/4,w/15,h/9);
            next.setBackground(Color.cyan);
            next.addActionListener(this);
            
            previous= new JButton(new ImageIcon("left.jpg"));
            previous.setBounds(w/30,h/4,w/15,h/9);
            previous.addActionListener(this);
            
            angle = new JSlider(0,90,40);
            angle.setBounds(w/50,(h/2),w/5,h/20);
            angle.setMajorTickSpacing(10);
            angle.setMinorTickSpacing(5);
            angle.setPaintTicks(true);
            angle.setSnapToTicks(true);
            angle.addChangeListener(this);
            angle.setVisible(false);
            
            combo = new JComboBox();
            combo.setBounds(w/50,h/10,w/5,h/30);
            combo.addItem("Choose your pattern..."); 
            combo.addItem("SierpinskiTriangle"); 
            combo.addItem("Koch"); 
            combo.addItem("Anti Koch"); 
            combo.addItem("Planet System");
            combo.addItem("Cell"); 
            combo.addItem("Forever Tree"); 
            combo.addItem("SierpinskiPentagon");
            combo.addItem("Mandelbrot3");  
            combo.addActionListener (this);
            
            btnPanel = new JPanel();
			btnPanel.setBounds(h/12,w/4,h/4,h/4);
			btnPanel.setLayout(new GridLayout(3,3));
			btnPanel.setBackground(themeColor);
			ctrlBtns[1] = new JButton("up");
			ctrlBtns[7] = new JButton("down");
			ctrlBtns[3] = new JButton ("left");
			ctrlBtns[5] = new JButton("right");
			ctrlBtns[2] = new JButton("zoom");
			ctrlBtns[0] = new JButton("");
			ctrlBtns[8] = new JButton(">");
			ctrlBtns[6] = new JButton("<");
			ctrlBtns[4] = new JButton("Reset");
            for (int x = 0; x<ctrlBtns.length;x++){
            btnPanel.add(ctrlBtns[x]);
            ctrlBtns[x].addActionListener(this);
			}
			btnPanel.setVisible(false);
            
            panel2 = new GPanel();
            panel2.setLayout(null);
            panel2.setBounds(0,0,3*(w/4),h);

            
            panel1 = new ImagePanel("background.jpg");
            panel1.setLayout(null);
            panel1.add(info);
            panel1.add(info1);
            panel1.add(info2);
            panel1.add(author);
            panel1.add(button);
            panel1.add(next);
            panel1.add(previous);
            panel1.add(angle);
            panel1.add(combo);
            panel1.add(colourChoice);
            panel1.add(btnPanel);
            panel1.setBounds(3*(w/4),0,w/4,h);
            
            
            panelMain = new JPanel() ;
            panelMain.setLayout(null);
            panelMain.add(panel1);
            panelMain.add(panel2);
            panelMain.setBounds(0,0,w,h);
            this.add(panelMain);
            
            this.setVisible(true);
    }
   
    public void actionPerformed (ActionEvent e){
    
     String event = e.getActionCommand();
       
       switch (event){   
		   
        case "up":
            yMove-=h/9;
            validate();
			repaint();
            break;
        case "down":
            yMove+=h/9;
            validate();
			repaint();
            break;
        case "left":
            xMove-=h/9;
            validate();
			repaint();
            break;
        case "right":
            xMove+=h/9;
            validate();
			repaint();
            break;
        case "zoom":
            double initialZoom = size;
			size+=zoomIncrease;
			zoomIncrease*=2;
			xMove*=2;
			yMove*=2;
            validate();
			repaint();
            break;
        case ">":
            colorIter++;
            validate();
			repaint();
            break;
        case "<":
            colorIter--;
            validate();
			repaint();
            break;
		case "Reset":
		xMove=0;
		yMove=0;
		zoomIncrease=h/9;
		size=w/4;
			validate();
			repaint();
			break;
        }
    
        if (e.getSource() == button){
           if(background==Color.black){
            background=Color.white;
            pattern = Color.black;
            treeColour = new Color(150,75,0);
            }else{
                background=Color.black;
                pattern = Color.white;
                treeColour = Color.white;
            }
            
            repaint();
        }
        
        if (e.getSource() == next){
            if(treeGen<12){
                treeGen++;
                repaint();
            }
            if(gen<9){
                gen++;
                repaint();
            }
             if(pentaGen<4){
                pentaGen++;
                repaint();
            }
        }
         
        if (e.getSource() == previous){
            if(treeGen>0){
                treeGen--;
                repaint();
            }
            if(gen>0){
                gen--;
                repaint();
            } 
            if(pentaGen>0){
                pentaGen--;
                repaint();
            } 
        }
        
        String s = (String) combo.getSelectedItem();
        this.s1=s;
           if(e.getSource() == combo){
                gen=0;
                treeGen=0;
                pentaGen=0;
                treeAngle=40;
                repaint();
                if(s == "Forever Tree" || s == "SierpinskiPentagon" || s == "Mandelbrot3"){
                    colourChoice.setVisible(false);
                    if(s == "Forever Tree"){
                        btnPanel.setVisible(false);
                        button.setVisible(true);
                        info1.setVisible(true);
                        next.setVisible(true);
                        previous.setVisible(true);
                        angle.setVisible(true);
                        info2.setVisible(true);
                    }else if(s == "SierpinskiPentagon"){
                        btnPanel.setVisible(false);
                        button.setVisible(true);
                        info1.setVisible(true);
                        next.setVisible(true);
                        previous.setVisible(true);
                        angle.setVisible(false);
                        info2.setVisible(false);
                        angle.setValue(40);
                    }else if(s == "Mandelbrot3"){
                        btnPanel.setVisible(true);
                        button.setVisible(false);
                        info1.setVisible(false);
                        next.setVisible(false);
                        previous.setVisible(false);
                        angle.setVisible(false);
                        info2.setVisible(false);
                        angle.setValue(40);
                    }
                        
                }else{
                    btnPanel.setVisible(false);
                    button.setVisible(true);
                    info1.setVisible(true);
                    next.setVisible(true);
                    previous.setVisible(true);
                    colourChoice.setVisible(true);
                    info2.setVisible(false);
                    angle.setVisible(false);
                    angle.setValue(40);
                }
           }
       
    }
    
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        treeAngle = (int)source.getValue();
        repaint();
    }
    
    class GPanel extends JPanel {

            public void paint(Graphics g) {
              
                switch(s1){
                        
                    case "SierpinskiTriangle" :
                        APoint centre= new APoint(w/2.6,h/1.7);
                        APoint sommet= new APoint(w/2.6,h/18);
                        SierpinskiTriangle sierpinskiTriangle= new  SierpinskiTriangle(centre,sommet,pattern);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        sierpinskiTriangle.display(g,gen);
                        break;
                        
                    case "Koch" :
                        Snail s2 = new Snail(w/2.6,h/128.6,0,pattern);
                        Koch k = new Koch(s2,g);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        k.kochSnow(w/2.3,gen);
                        break;
                        
                    case "Anti Koch" :
                        Snail s3 = new Snail(w/2.6,h/18,0,pattern);
                        Koch k1 = new Koch(s3,g);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        k1.antiKochSnow(w/1.94,gen);
                        break;
                        
                    case "Planet System" :
                        PlanetSystem p = new PlanetSystem(pattern);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        p.drawCircle(g,this.getWidth(),this.getHeight()-(h/36),w/4,gen);
                        break;
                        
                     case "Cell" :
                        PlanetSystem p1 = new PlanetSystem(pattern);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        p1.drawCircle2(g,this.getWidth(),this.getHeight()-(h/36),w/6,gen);
                        break;
                        
                     case "Forever Tree" :
                        Tree t = new Tree(0.65,treeAngle, g);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        t.growTree(treeGen,w/2.7,h/1.05,0,h/3,4f,treeColour);
                        break;
                        
                    case "SierpinskiPentagon" :
                        APoint centre1= new APoint(w/2.6,h/2);
                        APoint sommet2= new APoint(w/2.6,h/45);
                        SierpinskiPentagon sierpinskiPentagon= new SierpinskiPentagon(centre1,sommet2,pattern);
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        sierpinskiPentagon.display(g,pentaGen);
                        break;
                        
                    case "Mandelbrot3" :
                        Mandelbrot3 m = new Mandelbrot3(this,size,w/2,h/2,xMove,yMove,colorIter); 
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                        m.display(g);
                        break;
                    
                    default:
                        g.setColor(background);
                        g.fillRect(0,0,this.getWidth(),this.getHeight());
                }
            }
        
    }   
    
    class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

    }
    
}
