package Vistas;

import java.awt.Color;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Objects.Cuenta;

@SuppressWarnings("serial")
public class VentanaOpcion extends JFrame {

	@SuppressWarnings("unused")
	private JPanel contentPane;

	public VentanaOpcion(ArrayList<Cuenta> cuentasGlobales) {
		JFrame wind =this;
		
		getContentPane().setBackground(new Color(220, 220, 220));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));
		setUndecorated(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(498,100);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnCargacuentas = new JButton("CargaCuentas");
		btnCargacuentas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCargacuentas.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnCargacuentas.setBounds(136, 37, 105, 23);
		getContentPane().add(btnCargacuentas);
		btnCargacuentas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				PantallaCuentas pantalla=new PantallaCuentas(wind,cuentasGlobales);
				wind.setVisible(false);
		}});
		
		JButton btnCargaindicadores = new JButton("CargaIndicadores");
		btnCargaindicadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("unused")
				PantallaIndicadores pantalla=new PantallaIndicadores(wind,cuentasGlobales);
				wind.setVisible(false);
			}
		});
		btnCargaindicadores.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnCargaindicadores.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCargaindicadores.setBounds(251, 37, 105, 23);
		getContentPane().add(btnCargaindicadores);
		
		JLabel lblNewLabel = new JLabel("Elija el tipo de Operaci\u00F3n ue desea Realizar Presionando el Boton");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 12, 448, 14);
		getContentPane().add(lblNewLabel);
		this.setBackground(new Color(255, 235, 205));
		setVisible(true);
		}
}
