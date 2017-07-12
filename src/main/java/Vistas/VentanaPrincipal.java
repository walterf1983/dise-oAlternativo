package Vistas;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Toolkit;

import javax.swing.SwingConstants;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;



@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{
	
	private JPanel contentPane;

	public VentanaPrincipal() {
		setUndecorated(true);
		setResizable(true);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimensionWindows=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dimensionWindows.width-418)/2,(dimensionWindows.height-320)/2,418,320);
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIconTextGap(7);
		lblNewLabel.setIcon(new ImageIcon("E:\\Materias\\Dise\u00F1o\\PruebaPildoras\\ProgramarClaseSwing\\src\\Ventanas\\gato-18.gif"));
		lblNewLabel.setBounds(10, 101, 243, 184);
		contentPane.add(lblNewLabel);
		
		JLabel lblByGrupoDiseo = new JLabel("By Grupo Dise\u00F1o ");
		lblByGrupoDiseo.setBackground(new Color(255, 255, 255));
		lblByGrupoDiseo.setVerticalTextPosition(SwingConstants.TOP);
		lblByGrupoDiseo.setVerticalAlignment(SwingConstants.TOP);
		lblByGrupoDiseo.setOpaque(true);
		lblByGrupoDiseo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblByGrupoDiseo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblByGrupoDiseo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblByGrupoDiseo.setBounds(240, 178, 154, 25);
		contentPane.add(lblByGrupoDiseo);
		
		JLabel lblFrbaSistemas = new JLabel("FRBA - SISTEMAS - CAMPUS");
		lblFrbaSistemas.setBackground(new Color(255, 255, 255));
		lblFrbaSistemas.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblFrbaSistemas.setVerticalAlignment(SwingConstants.TOP);
		lblFrbaSistemas.setOpaque(true);
		lblFrbaSistemas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrbaSistemas.setHorizontalAlignment(SwingConstants.LEFT);
		lblFrbaSistemas.setBounds(194, 84, 186, 16);
		contentPane.add(lblFrbaSistemas);
		
		JLabel lblNewLabel_1 = new JLabel(" @2017 - UTN");
		lblNewLabel_1.setDisabledIcon(new ImageIcon("E:\\Materias\\Dise\u00F1o\\PruebaPildoras\\ProgramarClaseSwing\\src\\Ventanas\\4.jpg"));
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(204, 204, 204), new Color(204, 204, 204), new Color(0, 0, 0), new Color(0, 0, 0)));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(188, 63, 210, 184);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAnalizadorDeNegocios = new JLabel("ANALIZADOR DE NEGOCIOS");
		lblAnalizadorDeNegocios.setDisabledIcon(new ImageIcon("E:\\Materias\\Dise\u00F1o\\PruebaPildoras\\ProgramarClaseSwing\\src\\Ventanas\\ima.jpg"));
		lblAnalizadorDeNegocios.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblAnalizadorDeNegocios.setForeground(new Color(255, 255, 255));
		lblAnalizadorDeNegocios.setVerticalAlignment(SwingConstants.TOP);
		lblAnalizadorDeNegocios.setOpaque(true);
		lblAnalizadorDeNegocios.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAnalizadorDeNegocios.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnalizadorDeNegocios.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 19));
		lblAnalizadorDeNegocios.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblAnalizadorDeNegocios.setBackground(new Color(51, 51, 51));
		lblAnalizadorDeNegocios.setBounds(4, 35, 394, 25);
		contentPane.add(lblAnalizadorDeNegocios);
		this.setBackground(new Color(0,0,0,1));
		setVisible(true);
		
		
	}
}
