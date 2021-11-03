import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineMouseEx extends JFrame{
	private MyPanel panel=new MyPanel();
	ImageIcon[] images= {new ImageIcon("Pictures/blackIcon.png"),
			new ImageIcon("Pictures/blueIcon.jpg"),
			new ImageIcon("Pictures/redIcon.jpg")};
	JButton menubar= new JButton("Áö¿ì°³");
	JComboBox combo=new JComboBox(images);
	NorthPanel np=new NorthPanel();
	public GraphicsDrawLineMouseEx() {
		setTitle("draw Line by Mouse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		Container c=getContentPane();
		c.add(np,BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		setSize(800,800);
		setVisible(true);
	}
	class NorthPanel extends JPanel{
		
		JLabel imgLabel=new JLabel(images[0]);
		public NorthPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			combo.setPreferredSize(new Dimension(50,30));
			add(combo); 
			add(menubar);
		}
		
	}
	public int getComboIndex() {	
		return combo.getSelectedIndex();
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
			 
				switch(getComboIndex()) {
				case 0:g.setColor(Color.BLACK); break;
				case 1:g.setColor(Color.BLUE);	break;
				case 2:g.setColor(Color.RED);	break;
				}

			
			g.drawLine(last_x, last_y, x, y);
			moveto(x,y);
		}
	}
	
	public static void main(String[] args) {
		new GraphicsDrawLineMouseEx();
	}
	
	
}

