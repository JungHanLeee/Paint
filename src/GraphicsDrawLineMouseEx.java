import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineMouseEx extends JFrame{
	private MyPanel panel=new MyPanel();
	ImageIcon[] images= {new ImageIcon("Pictures/blackIcon.png"),
			new ImageIcon("Pictures/blueIcon.jpg"),
			new ImageIcon("Pictures/redIcon.jpg")};
	JButton menubar= new JButton("지우개 실행");
	JComboBox combo=new JComboBox(images);
	JSlider slider=new JSlider(JSlider.HORIZONTAL,0,20,1);
	Graphics graphics;
	Graphics2D g;
	int thickness;
	
	NorthPanel np=new NorthPanel();
	int eraser=0;
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
			combo.setPreferredSize(new Dimension(35,20));
			add(combo); 
			add(menubar);
			add(slider);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.setPaintTrack(true);
			slider.setMajorTickSpacing(10);
			slider.setMinorTickSpacing(1);
			slider.addChangeListener(new MyChangeListener());
			menubar.addActionListener(new MyActionListener());
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
			graphics=getGraphics();
			g=(Graphics2D)graphics;
			g.setStroke(new BasicStroke(thickness));
			 if(eraser==0){
				switch(getComboIndex()) {
				case 0:g.setColor(Color.BLACK); break;
				case 1:g.setColor(Color.BLUE);	break;
				case 2:g.setColor(Color.RED);	break;
				}
			}
			 else  {
				 g.setColor(Color.WHITE);
			 }

			
			g.drawLine(last_x, last_y, x, y);
			moveto(x,y);
		}
	}
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b=(JButton)e.getSource();
			if(b.getText().equals("지우개 실행")) {
				b.setText("지우개 중지");
				eraser=1;
			}
			else {
				b.setText("지우개 실행");
				eraser=0;
			}
		}
		
	}
	private class MyChangeListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			JSlider source=(JSlider)e.getSource();
			if(!source.getValueIsAdjusting()) {
				thickness =(int)source.getValue();
			}
		}
	}
	public static void main(String[] args) {
		new GraphicsDrawLineMouseEx();
	}
	
	
}

