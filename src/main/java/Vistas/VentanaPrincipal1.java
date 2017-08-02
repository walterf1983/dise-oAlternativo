package Vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.ComponentOrientation;

@SuppressWarnings("serial")
public class VentanaPrincipal1 extends JFrame {
	

	private JPanel contentPane;

	public VentanaPrincipal1() {
		setFont(new Font("Segoe Print", Font.PLAIN, 12));
		setResizable(false);
		setUndecorated(true);
		setTitle("Analizador de Negocios - Inicio");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src//main//java//VariablesGraficas//ima.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimensionWindows=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dimensionWindows.width-640)/2,(dimensionWindows.height-400)/2,640,400);
		
		contentPane=(new JPanel(){
			public void paintComponent(Graphics grafico) {	
				super.paintComponent(grafico);
				Dimension height = getSize();
				grafico.drawImage( Toolkit.getDefaultToolkit().getImage("src//main//java//VariablesGraficas//depositphotos_39618097-stock-photo-law-concept-business-man-with.jpg"), 0, 0, height.width, height.height, null);
				grafico.setFont(new Font("Consolas",Font.PLAIN,30));
				grafico.drawString("Analizador de Negocios",50,50);
				grafico.setFont(new Font("Courier",Font.BOLD,20));
				grafico.drawString("By Grupo Diseño",440,380);
				grafico.setFont(new Font("Courier",Font.BOLD,14));
				grafico.drawString("@2017- UTN - FRBA",300,120);
				grafico.drawString("CAMPUS - SISTEMAS",300,140);			
				}
		});
		contentPane.setLayout(null);
		contentPane.setOpaque(true);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIconTextGap(2);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setIcon(new ImageIcon("src//main//java//VariablesGraficas//giphy.gif"));
		lblNewLabel.setBounds(397, 195, 202, 164);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
	}
}
