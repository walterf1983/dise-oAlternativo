package Vistas;



import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import DAO.DAOEmpresa;

import javax.swing.border.BevelBorder;
import Repositorios.RepositorioDeEmpresas;


import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class PantallaAgregarCuenta extends JFrame {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PantallaAgregarCuenta(JFrame fatherWindow) {
		this.setUndecorated(true);
		PantallaAgregarCuenta wind=this;
		RepositorioDeEmpresas repoEmpresa=new RepositorioDeEmpresas("src//main//java//DB//Empresas.json");
		PantallaAgregarCuenta.setCountInstance(PantallaAgregarCuenta.getCountInstance()+1);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src//main//java//VariablesGraficas//ima.jpg"));
		setTitle("CargadorCuentas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		posicionarPantalla(fatherWindow);
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		inValor = new JTextField();
		inValor.setColumns(10);
		inValor.setBounds(94, 66, 329, 20);
		contentPane.add(inValor);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblValor.setBorder(null);
		lblValor.setBounds(23, 67, 95, 14);
		contentPane.add(lblValor);
		
		JLabel lblCuenta = new JLabel("Cuenta\r\n:");
		lblCuenta.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblCuenta.setBorder(null);
		lblCuenta.setBounds(23, 36, 95, 14);
		contentPane.add(lblCuenta);
		
		inCuenta = new JTextField();
		inCuenta.setColumns(10);
		inCuenta.setBounds(94, 35, 329, 20);
		contentPane.add(inCuenta);
		
		comboPeriodo = new JComboBox();
		comboPeriodo.setModel(new DefaultComboBoxModel(new String[] {"1S", "2S", "A"}));
		comboPeriodo.setBackground(new Color(255, 255, 255));
		comboPeriodo.setBounds(94, 181, 329, 20);
		contentPane.add(comboPeriodo);
		
		inAnio = new JTextField();
		inAnio.setColumns(10);
		inAnio.setBounds(94, 140, 329, 20);
		contentPane.add(inAnio);
		
		inEmpresa = new JTextField();
		inEmpresa.setBounds(94, 109, 329, 20);
		contentPane.add(inEmpresa);
		inEmpresa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Empresa:");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(23, 109, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPeriodo = new JLabel("Periodo\r\n:");
		lblPeriodo.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblPeriodo.setBorder(null);
		lblPeriodo.setBounds(23, 182, 95, 14);
		contentPane.add(lblPeriodo);
		
		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblAo.setBorder(null);
		lblAo.setBounds(23, 141, 65, 14);
		contentPane.add(lblAo);
		
		JLabel lblElijaUnaEmpresa = new JLabel("Complete los campos y acepte para agregarlo a la base de datos");
		lblElijaUnaEmpresa.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(178, 34, 34), new Color(188, 143, 143), new Color(178, 34, 34), new Color(205, 92, 92)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		lblElijaUnaEmpresa.setBackground(new Color(255, 255, 255));
		lblElijaUnaEmpresa.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblElijaUnaEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblElijaUnaEmpresa.setBounds(13, 11, 424, 217);
		contentPane.add(lblElijaUnaEmpresa);
		
		JButton botonAgregar = new JButton("AGREGAR");
		botonAgregar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonAgregar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonAgregar.setBounds(342, 239, 95, 23);
		contentPane.add(botonAgregar);
		
		JButton botonSalir = new JButton("SALIR");
		botonSalir.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonSalir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonSalir.setBounds(229, 239, 95, 23);
		contentPane.add(botonSalir);
		
		JLabel lblNewLabel_1 = new JLabel("Caracteres entre 0-9 y un punto decimal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(94, 84, 223, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCaracteresEntre = new JLabel("Caracteres entre 0-9");
		lblCaracteresEntre.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCaracteresEntre.setBounds(93, 161, 223, 14);
		contentPane.add(lblCaracteresEntre);
		
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PantallaAgregarCuenta.setCountInstance(PantallaAgregarCuenta.getCountInstance()-1);
				if(fatherWindow.getClass().getName().equalsIgnoreCase("vistas.pantallacuentas")){
					PantallaCuentas win=(PantallaCuentas)fatherWindow;
					win.getBotonAgregar().setEnabled(true);
					PantallaAgregarCuenta.setPosY(wind.getY());
						
					if(countInstance==0)
						win.getBotonAgregar().setEnabled(true);
						if(win.getBotonCargar().isEnabled())
							win.getBotonVolver().setEnabled(true);
					dispose();
					}else{
						PantallaCargarCuentas windN=(PantallaCargarCuentas)fatherWindow;
						PantallaAgregarCuenta.setPosY(wind.getY());
						windN.getBotonAgregar().setEnabled(true);
						windN.getBotonCancelar().setEnabled(true);
						windN.getTable().setEnabled(true);
						windN.getIngresoAnio().setEnabled(true);
						windN.getIngresoEmpresa().setEnabled(true);
						windN.getComboTipo().setEnabled(true);
						if(countInstance==0){
							windN.getFather().getBotonAgregar().setEnabled(true);
						}
						dispose();
			
					}
			}
		});
		
		
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comprobarIngresosDeUsuario()==-1){
					return;
				}
				
				int indexE=0;
				int indexA=0;
				int indexT=0;
				JComboBox comboE;
				JComboBox comboA;
				JComboBox comboT;
				
			
				if(fatherWindow.getClass().getName().equalsIgnoreCase("vistas.PantallaCuentas")){
					PantallaCuentas win=(PantallaCuentas)fatherWindow;
					comboE=win.getComboEmpresa();
					indexE=comboE.getSelectedIndex();
					comboA=win.getComboAnio();
					indexA=comboA.getSelectedIndex();
					comboT=win.getComboTipo();
					indexT=comboT.getSelectedIndex();
				}else{
					PantallaCargarCuentas win=(PantallaCargarCuentas)fatherWindow;
					comboE=win.getFather().getComboEmpresa();
					indexE=comboE.getSelectedIndex();
					comboA=win.getFather().getComboAnio();
					indexA=comboA.getSelectedIndex();
					comboT=win.getFather().getComboTipo();
					indexT=comboT.getSelectedIndex();
					}
				DAOEmpresa dao=(DAOEmpresa)repoEmpresa.getDao();
				indexE=comboE.getSelectedIndex();
				indexA=comboA.getSelectedIndex();
				indexT=comboT.getSelectedIndex();
				int agregado=dao.addCuenta(inEmpresa.getText(),inAnio.getText(),(String)comboPeriodo.getSelectedItem(),inCuenta.getText(),inValor.getText());
				if(agregado==0)
					JOptionPane.showMessageDialog(null,"Cuenta existente agregada.","Información" ,JOptionPane.INFORMATION_MESSAGE);
				else
					if(agregado==1){
						JOptionPane.showMessageDialog(null,"Cuenta agregada a empresa nueva.","Información" ,JOptionPane.INFORMATION_MESSAGE);
						comboE.insertItemAt(inEmpresa.getText(),comboE.getItemCount());
					}
					else 
						if(agregado==2)
							JOptionPane.showMessageDialog(null,"Cuenta agregada a empresa existente sin el periodo.","Información" ,JOptionPane.INFORMATION_MESSAGE);
						else 
							if(agregado==3)
								JOptionPane.showMessageDialog(null,"Cuenta agregada a empresa existente con existencia de periodo.","Información" ,JOptionPane.INFORMATION_MESSAGE);
				if(agregado==1 &&comboE.getItemCount()==1){
					comboA.insertItemAt(Integer.parseInt(inAnio.getText()),0);
					indexE=0;
					indexA=0;
					indexT=0;
					comboT.insertItemAt((String)comboPeriodo.getSelectedItem(),0);
				}
				comboE.setSelectedIndex(indexE);
				comboA.setSelectedIndex(indexA);
				comboT.setSelectedIndex(indexT);
				}});
		
		if(fatherWindow.getClass().getName().equalsIgnoreCase("vistas.PantallaCargarCuentas")){
			PantallaCargarCuentas pantalla = (PantallaCargarCuentas)fatherWindow;
			this.inCuenta.setText((String)pantalla.getTable().getValueAt(pantalla.getTable().getSelectedRow(), 1));
			this.inCuenta.setEditable(false);
			this.inValor.setText(String.valueOf(pantalla.getTable().getValueAt(pantalla.getTable().getSelectedRow(), 2)));
			this.inEmpresa.setText(pantalla.getIngresoEmpresa().getText());
			this.inEmpresa.setEditable(false);
			this.inAnio.setText(pantalla.getIngresoAnio().getText());
			this.inAnio.setEditable(false);
			this.comboPeriodo.setSelectedItem(pantalla.getComboTipo().getSelectedItem());
			this.comboPeriodo.setEnabled(false);
		}
	}


	private static int countInstance;
	private static int posY;
	
	public static int getPosY() {
		return posY;
	}

	public static void setPosY(int posY) {
		PantallaAgregarCuenta.posY = posY;
	}

	public static int getCountInstance() {
		return countInstance;
	}

	public static void setCountInstance(int countInstance) {
		PantallaAgregarCuenta.countInstance = countInstance;
	}
	

	private JPanel contentPane;
	private JTextField inEmpresa;
	private JTextField inAnio;
	private JComboBox<?> comboPeriodo;
	private JTextField inCuenta;
	private JTextField inValor;
	
	private boolean asegurarIngresoTextoUsuario(String caracteresValidos,String ingreso){
			for(int i=0;i<ingreso.length();i++){
				if(!caracteresValidos.contains(String.valueOf(ingreso.charAt(i)))){
					return true;
				};		
			}
			return false;
		}
	
		private void posicionarPantalla(JFrame fatherWindow){
			JFrame windowLocation=fatherWindow;
			if(fatherWindow.getClass().getName().equalsIgnoreCase("vistas.PantallaCargarCuentas")){
				PantallaCargarCuentas pantalla=(PantallaCargarCuentas)fatherWindow;
				windowLocation=pantalla.getFather();
			}
		
			int posRX=windowLocation.getX();
			int posRY=windowLocation.getY();
		
			if(PantallaAgregarCuenta.countInstance==1){
				this.setBounds(posRX+450,posRY,450,273);
				PantallaAgregarCuenta.setPosY(posRY+273);
			}else{
				this.setBounds(posRX+450,PantallaAgregarCuenta.getPosY(),450,273);
			}
			setVisible(true);
		}
	
		private int comprobarIngresosDeUsuario(){
			if(inEmpresa.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Complete el nombre de la empresa","Información.",JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}
			if(inAnio.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Complete el año del periodo","Información.",JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}else{
				if(this.asegurarIngresoTextoUsuario("0123456789",inAnio.getText())){
					JOptionPane.showMessageDialog(null,"Ingrese solo numeros para ingresar el año.","Información",JOptionPane.INFORMATION_MESSAGE);
					return-1;
				}
			}
		
			if(inCuenta.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Complete el nombre de la cuenta.","Información",JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}
		
			if(inValor.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Complete el valor de la cuenta.","Información",JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}else{
				if(this.asegurarIngresoTextoUsuario(".0123456789",inValor.getText())){
					JOptionPane.showMessageDialog(null,"Ingrese solo numeros, y un punto decimal\n si es necesario para el valor de la cuenta.","Información",JOptionPane.INFORMATION_MESSAGE);
					return -1;
				}else{
					if(inValor.getText().lastIndexOf('.')!=inValor.getText().indexOf('.')){
						JOptionPane.showMessageDialog(null,"Ingrese como maximo un punto decimal.","Información",JOptionPane.INFORMATION_MESSAGE);
						return -1;
					}else{
						if(inValor.getText().startsWith(".")){
							JOptionPane.showMessageDialog(null,"Ingrese la parte entera del decimal del valor de cuenta.","Información",JOptionPane.INFORMATION_MESSAGE);
							return -1;
						}
					}
				}
			}
			return 0;
		}

}