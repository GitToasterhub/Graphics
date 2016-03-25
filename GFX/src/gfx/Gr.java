package gfx;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
public class Gr extends JPanel {
	private JFrame frmGraphics;
	private JTextField textField;
	JPanel panel;
	File f;double k;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gr window = new Gr();
					window.frmGraphics.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Gr() {
		initialize();
	}
	private void initialize() {
		frmGraphics = new JFrame();
		
		frmGraphics.getContentPane().setBackground(new Color(192, 192, 192));
		frmGraphics.getContentPane().setLayout(null);
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setBounds(80, 665, 278, 20);
		frmGraphics.getContentPane().add(textField);
		textField.setColumns(10);
		JLabel lblFx = new JLabel(" F(x)=");
		lblFx.setBounds(52, 667, 31, 17);
		frmGraphics.getContentPane().add(lblFx);
		frmGraphics.setBackground(new Color(128, 128, 128));
		frmGraphics.setResizable(false);
		frmGraphics.setTitle("Graphics");
		frmGraphics.setBounds(150, 50, 1024, 750);
		frmGraphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setName("panel");
		panel.setBackground(Color.WHITE);
		panel.setBounds(50, 40, 800, 600);
		
		frmGraphics.getContentPane().add(panel);
		JButton btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent arg0) {  //button press method
				clear(panel.getGraphics());
				printComponent(panel.getGraphics());
		    	ScriptEngineManager factory = new ScriptEngineManager();
				ScriptEngine engine = factory.getEngineByName("groovy");
				String fact = "import static java.lang.Math.*\n" +"def func(x) { return x }";
				String Fx=textField.getText();
				fact=fact.replace("return x" ,"return "+Fx );
				Object result ;
				k=400/4;
				try
				{
				engine.eval(fact);
				Invocable inv = (Invocable) engine;		    
				result = inv.invokeFunction("func",2);
				for(int i=0;i<=800;i+=1)
				{
					double x1=i-400;
					double x2=x1+1;
					double y1,y2;
					y1=((Double)inv.invokeFunction("func",x1/k))*100;
					y2=((Double)inv.invokeFunction("func",x2/k))*100;
					y1=300-y1;
					y2=300-y2;
					drawline(panel.getGraphics(),(int)i,(int)y1,(int)i+1,(int)y2);
					
				}
				
				}
				catch(Exception e)
				{
					textField.setText("Something went wrong. :(");
					System.out.print(e);
				}
				
			}
		});
		btnDraw.setBounds(368, 664, 89, 23);
		frmGraphics.getContentPane().add(btnDraw);
	    }
	    @Override
	    public void paintComponent(Graphics g)
	    {
	    	 super.paintComponent(g);
	         g.setColor(Color.blue);
	         g.drawLine(400, 0, 400,600);
	         g.drawLine(0, 300, 800, 300);
	    }
	    public void drawline(Graphics g,int x1,int y1,int x2,int y2)
	    {
	    	 
	    	 g.setColor(Color.red);
	    	 g.drawLine(x1, y1, x2, y2);
	    	
	    }
	    public void clear(Graphics g)
	    {
	    	g.setColor(Color.white);
	    	g.fillRect(0, 0,800,600);
	    }
}