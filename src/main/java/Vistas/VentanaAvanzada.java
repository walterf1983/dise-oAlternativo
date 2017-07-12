package Vistas;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaAvanzada {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MyFrame2 ventana = new MyFrame2();//Instancio una ventana

		MyFrame3 ventana1 = new MyFrame3();//Instancio una ventana

		
		
	}

}
class LanzadorVentanaMosaico implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		MyFrame4 ventana2 = new MyFrame4();
	}
	
}
class MyFrameState implements WindowListener{

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("Se activó la ventana "+e.getSource().getClass().getName());
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Se cerró la ventana "+e.getSource().getClass().getName());
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("Está inactiva la ventana "+e.getSource().getClass().getName());
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("Se restauró la ventana "+e.getSource().getClass().getName());
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("Se minimizó la ventana "+e.getSource().getClass().getName());
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("Se abrio la ventana "+e.getSource().getClass().getName());
	}

	
}
class MyFrame2 extends JFrame{//Toda ventana Hereda de JFrame
	
	public MyFrame2(){
		this.setVisible(true);//Nace Oculta
		this.add(new MyLayoutBoton());
		this.add(new MyLayout2());
		this.setTitle("Una gran Ventana");//Se le agrega titulo
		this.setResizable(true);//la hace maximizable//
		Dimension tool=Toolkit.getDefaultToolkit().getScreenSize();//extraigo la dimension de la pantalla nativa
		this.setBounds((tool.width-700)/2,(tool.height-700)/2,700,700);//sale en el centro (posx,posy,largo,alto)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Cerrar appl cuando se cierre
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));//seteo el icono de la ventana convirtiendo un archivo a image con toolkit.
		this.setExtendedState(Frame.MAXIMIZED_BOTH);//Maximiza
		this.setExtendedState(Frame.NORMAL);//Lo vuelvo a como estaba
		this.addWindowListener(new MyFrameState());
		
	}
}

class MyFrame3 extends JFrame{//Toda ventana Hereda de JFrame
	
	public MyFrame3(){
		this.setVisible(true);//Nace Oculta
		this.add(new MyLayout3());
		this.setTitle("Una gran Ventana de 2D");//Se le agrega titulo
		this.setResizable(false);
		Dimension tool=Toolkit.getDefaultToolkit().getScreenSize();//extraigo la dimension de la pantalla nativa
		this.setBounds((tool.width-700)/2,(tool.height-700)/2,700,700);//sale en el centro (posx,posy,largo,alto)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Cerrar appl cuando se cierre
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));//seteo el icono de la ventana convirtiendo un archivo a image con toolkit.
		this.setExtendedState(Frame.MAXIMIZED_BOTH);//Maximiza
		this.setExtendedState(Frame.NORMAL);//Lo vuelvo a como estaba
		this.addWindowListener(new MyFrameState());
	
	}
}

class MyFrame4 extends JFrame{
	
	public MyFrame4(){
		this.setVisible(true);//Nace Oculta
		
		this.setTitle("Una gran Ventana de Mosaico");//Se le agrega titulo
		this.setResizable(true);
		Dimension tool=Toolkit.getDefaultToolkit().getScreenSize();//extraigo la dimension de la pantalla nativa
		this.setBounds((tool.width-700)/2,(tool.height-700)/2,700,700);//sale en el centro (posx,posy,largo,alto)
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Cerrar appl cuando se cierre
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));//seteo el icono de la ventana convirtiendo un archivo a image con toolkit.
		this.setExtendedState(Frame.MAXIMIZED_BOTH);//Maximiza
		this.setExtendedState(Frame.NORMAL);//Lo vuelvo a como estaba
		this.add(new MyLayout4());
		this.addWindowListener(new MyFrameState());
	}
}

class MyLayout2 extends JPanel{//hereda de la Interface JPanel

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.setFont( new Font("Comic Sans MS",Font.BOLD,34));
		g.drawString("Prueba de Lamina", 40, 40);
		g.setColor(new Color(210,10,133));
		g.drawLine(30, 40, 400, 40);//Subrrayado
		g.setColor(Color.cyan);
		g.drawRect((this.getWidth()-200)/2,(this.getHeight()-200)/2,200,200);
		this.setBackground(SystemColor.window);//pone el fondo de color de la ventana nativa del sistema a la lamina
	}
}

class MyLayout3 extends JPanel{//hereda de la Interface JPanel
	private JButton buton=new JButton("Mosaico");
	 
	public MyLayout3(){
		 	this.setLayout(null);//Le saca la alineación por defecto de la lamina	
		 	buton.setBounds(100,100,200,300);
			this.add(buton);
			buton.addActionListener(new LanzadorVentanaMosaico());
	 }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;//Casteo a 2D
		g2.setPaint(new GradientPaint(50f, 50f,   Color.cyan, 500,300,Color.BLACK));//relleno degradado
		g2.fill(new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight()));
		g2.setColor(Color.BLUE);
		g2.setFont( new Font("Comic Sans MS",Font.BOLD,34));
		g2.drawString("Prueba de Lamina Graphics2D", 40, 40);
		g2.setPaint(Color.BLUE);//cambia el color
		g2.draw(new Rectangle2D.Double((this.getWidth()-200)/2,(this.getHeight()-200)/2,200,200));
		g2.setPaint(new Color(210,10,133));
		g2.draw(new Line2D.Double(30, 40, 600, 40));//Subrrayado
		g2.setPaint(Color.cyan);
		Rectangle2D rect=new Rectangle2D.Double((this.getWidth()-200)/2,(this.getHeight()-200)/2,200,200);
		g2.draw(rect);
		g2.setPaint(Color.red);
		g2.draw(new Line2D.Double(rect.getX(),rect.getY(),rect.getX()+rect.getWidth(),rect.getY()+rect.getHeight()));
		Ellipse2D elip = new Ellipse2D.Double();
		elip.setFrame(rect);//Elipse Inscripta en un rectangulo
		g2.setPaint(new Color(255,1,1,100));//Con factor Alpha de Transparencia
		g2.fill(elip);//Rellena
		
	
		double ladoRectoMitad=rect.getHeight()/2;
		double c=rect.getWidth()/2;
		double a=(ladoRectoMitad+Math.sqrt(Math.pow(ladoRectoMitad,2)-4*-Math.pow(c,2)))/2;
		double b=Math.sqrt(ladoRectoMitad*a);
		g2.setPaint(Color.red);
		BasicStroke stroke=new BasicStroke(4f);//Cambio grosor de Trazo (Otras Opciones en Constr)
		g2.setStroke(stroke);
		Ellipse2D elip1=new Ellipse2D.Double();
		elip1.setFrameFromCenter(rect.getCenterX(), rect.getCenterY(),rect.getCenterX()+a, rect.getCenterY()+b);
		this.EllipseCircuns(g2, rect);
		
	}
	private void EllipseCircuns(Graphics2D g,Rectangle2D rect){
		double ladoRectoMitad=rect.getHeight()/2;
		double c=rect.getWidth()/2;
		double a=(ladoRectoMitad+Math.sqrt(Math.pow(ladoRectoMitad,2)-4*-Math.pow(c,2)))/2;
		double b=Math.sqrt(ladoRectoMitad*a);
		g.setPaint(Color.red);
		g.setStroke(new BasicStroke(4f));//Cambio grosor de Trazo (Otras Opciones en Constr)
		Ellipse2D elip1=new Ellipse2D.Double();
		elip1.setFrameFromCenter(rect.getCenterX(), rect.getCenterY(),rect.getCenterX()+a, rect.getCenterY()+b);
		g.draw(elip1);
		g.setStroke(new BasicStroke(1f));//lo regreso a como estaba
		
	}
}

class MyLayout4 extends JPanel{//hereda de la Interface JPanel

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image image = null;
		try{
		image=ImageIO.read(new File("Wrinkled_Paper.gif"));
		}
		catch(IOException e){
			System.out.println("No esta");
		}
		g.drawImage(image, 0,0,this);
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
		g.copyArea(0, 0, 256,256,256*j,256*i);
		
	}
}

class MyLayoutBoton extends JPanel{
	public MyLayoutBoton(){
		this.setLayout(null);
	
		JButton buton=new JButton("Mosaico");
		buton.setBounds(100,100,50,50);
		this.add(buton);
		buton.addActionListener(new LanzadorVentanaMosaico());
	}

	
}