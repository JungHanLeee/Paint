import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineMouseEx extends JFrame{
	private MyPanel panel=new MyPanel();
	NorthPanel np=new NorthPanel();
	public GraphicsDrawLineMouseEx() {
		setTitle("draw Line by Mouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		Container c=getContentPane();
		c.add(np,BorderLayout.NORTH);
		setSize(800,800);
		setVisible(true);
	}
	class NorthPanel extends JPanel{
		public NorthPanel() {
			ImageIcon[] images= {new ImageIcon("Pictures/blackIcon.png"),
					new ImageIcon("Pictures/blueIcon.jpg"),
					new ImageIcon("Pictures/redIcon.jpg")};
			JButton[] menubar= {new JButton("Áö¿ì°³")};
			JComboBox combo=new JComboBox(images);
			JLabel imgLabel=new JLabel(images[0]);
			setLayout(new FlowLayout(FlowLayout.LEFT));
			combo.setPreferredSize(new Dimension(50,30));
			add(combo); 
			for(int i=0;i<menubar.length;i++) {
				add(menubar[i]);
			}
			
		}
	}
	
	class MyPanel extends JPanel{
		
		public MyPanel() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					moveto(e.getX(),e.getY());
					requestFocus();
				}
			});
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					lineto(e.getX(),e.getY());
				}
			});
		}
		protected int last_x,last_y;
		public void moveto(int x,int y) {
			last_x=x;
			last_y=y;
		}
		public void lineto(int x,int y) {
			Graphics g=getGraphics();
			g.drawLine(last_x, last_y, x, y);
			moveto(x,y);
		}
		
	}
	public static void main(String[] args) {
		new GraphicsDrawLineMouseEx();
	}
}

