package Vistas;



import javax.swing.JFrame;



import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAOEmpresa;
import Objects.Cuenta;
import Repositorios.RepositorioDeEmpresas;


import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PantallaCargarCuentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private RepositorioDeEmpresas repo;
	private JButton botonAgregar;
	private JButton botonCancelar;
	private  ArrayList<Cuenta> cuentasGlobales;
	private PantallaCuentas father;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField inCuenta;
	private JTextField inValor;
	private JComboBox<Object> comboTipo;
	private JTextField ingresoEmpresa;
	private JTextField ingresoAnio;
	
	public JTextField getIngresoAnio() {
		return ingresoAnio;
	}



	public JTextField getIngresoEmpresa() {
		return ingresoEmpresa;
	}



	public JComboBox<Object> getComboTipo() {
		return comboTipo;
	}



	public JTable getTable() {
		return table;
	}



	public PantallaCuentas getFather() {
		return father;
	}



	public JButton getBotonCancelar() {
		return botonCancelar;
	}



	public JButton getBotonAgregar() {
		return botonAgregar;
	}



	public RepositorioDeEmpresas getRepo() {
		return repo;
	}

	public PantallaCargarCuentas(JFrame fatherWindow, ArrayList<Cuenta> cuentasGlobales) {
		setUndecorated(true);
		PantallaCargarCuentas wind= this;
		RepositorioDeEmpresas repoEmpresa=new RepositorioDeEmpresas("Empresas.json");
		repo=repoEmpresa;
		this.father=(PantallaCuentas) fatherWindow;
		this.cuentasGlobales=cuentasGlobales;
		setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));
		setTitle("CargadorCuentas");
		this.setBounds(fatherWindow.getX()-450,fatherWindow.getY(),450,375);//ubica con respecto a la ventana padre
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		inValor = new JTextField();
		inValor.setHorizontalAlignment(SwingConstants.LEFT);
		inValor.setColumns(10);
		inValor.setBounds(296, 249, 120, 20);
		contentPane.add(inValor);
		
		inCuenta = new JTextField();
		inCuenta.setHorizontalAlignment(SwingConstants.LEFT);
		inCuenta.setColumns(10);
		inCuenta.setBounds(296, 204, 120, 20);
		contentPane.add(inCuenta);
		
		JButton agregarCuenta = new JButton("AGREGAR");
		agregarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(wind.comprobacionIngresoUsuario()==-1){
					return;
				}
				
				wind.agregarCuentaGlobalATabla((DefaultTableModel) table.getModel());
			}
		});
		agregarCuenta.setFont(new Font("Times New Roman", Font.BOLD, 11));
		agregarCuenta.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		agregarCuenta.setBounds(319, 301, 74, 23);
		contentPane.add(agregarCuenta);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(93, 189, 166, 20);
		contentPane.add(textField_1);
		ingresoEmpresa=textField_1;
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(93, 261, 166, 20);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"1S", "2S", "A"}));
		contentPane.add(comboBox);
		comboTipo=comboBox;
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(93, 220, 166, 20);
		contentPane.add(textField);
		ingresoAnio=textField;
		
		JButton btnNewButton_1 = new JButton("VOLVER");
		btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_1.setBounds(368, 341, 72, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			father.getBotonCargar().setEnabled(true);
			if(PantallaAgregarCuenta.getCountInstance()==0)
				father.getBotonVolver().setEnabled(true);
			dispose();
			}
		});
		botonCancelar=btnNewButton_1;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 37, 383, 120);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSize(new Dimension(0, 0));
		table.setGridColor(new Color(128, 0, 0));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 100, 0), new Color(0, 0, 0), new Color(255, 0, 0), new Color(0, 0, 0)));
		table.setFont(new Font("Consolas",Font.BOLD,14));
		scrollPane.setViewportView(table);
		this.loadTable();
		
		JLabel lblEmpresasCargadasEn = new JLabel("Cuentas Cargadas en el Sistema");
		lblEmpresasCargadasEn.setAutoscrolls(true);
		lblEmpresasCargadasEn.setVerticalAlignment(SwingConstants.TOP);
		lblEmpresasCargadasEn.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblEmpresasCargadasEn.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(178, 34, 34), new Color(188, 143, 143), new Color(178, 34, 34), new Color(205, 92, 92)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		lblEmpresasCargadasEn.setBackground(Color.WHITE);
		lblEmpresasCargadasEn.setBounds(13, 11, 427, 155);
		contentPane.add(lblEmpresasCargadasEn);
		
		JButton btnnew = new JButton("AGREGAR");
		this.botonAgregar=btnnew;
		btnnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null,"No selecciono ninguna cuenta para agregar.","Información",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				comboTipo.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnnew.setEnabled(false);
				table.setEnabled(false);
				ingresoEmpresa.setEnabled(false);
				ingresoAnio.setEnabled(false);
				@SuppressWarnings("unused")
				PantallaAgregarCuenta epantallaAgregarCuenta=new PantallaAgregarCuenta(wind);
			}
		});
		btnnew.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnnew.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnnew.setBounds(93, 301, 95, 23);
		contentPane.add(btnnew);
		
		JLabel lblPanelParaCargar = new JLabel("Seleccion  de fila a la base de datos");
		lblPanelParaCargar.setVerticalAlignment(SwingConstants.TOP);
		lblPanelParaCargar.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblPanelParaCargar.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(178, 34, 34), new Color(188, 143, 143), new Color(178, 34, 34), new Color(205, 92, 92)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		lblPanelParaCargar.setBackground(Color.WHITE);
		lblPanelParaCargar.setAutoscrolls(true);
		lblPanelParaCargar.setBounds(13, 168, 262, 162);
		contentPane.add(lblPanelParaCargar);
		
		JLabel label = new JLabel("Empresa:");
		label.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		label.setBorder(null);
		label.setBounds(22, 189, 79, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Periodo\r\n:");
		label_1.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		label_1.setBorder(null);
		label_1.setBounds(22, 262, 95, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("A\u00F1o:");
		label_2.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		label_2.setBorder(null);
		label_2.setBounds(22, 221, 65, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Caracteres entre 0-9");
		label_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		label_3.setBounds(92, 241, 223, 14);
		contentPane.add(label_3);
		
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				
				ArrayList<Integer>index=new ArrayList<Integer>();
			    DefaultTableModel m=(DefaultTableModel)table.getModel();
			    for(int i=0;i<m.getRowCount();i++){
			    	if((Boolean)m.getValueAt(i, 3)){
			    		index.add(i);
			    	}
			    }
			    
			    Collections.reverse(index);// Para ue no desordene las posiciones
			    for(int ind:index){
			    	m.removeRow(ind);
			    	cuentasGlobales.remove(ind);
			    }
			}
		});
		btnEliminar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnEliminar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnEliminar.setBounds(13, 341, 72, 23);
		contentPane.add(btnEliminar);
		
		JLabel lblEliminaLasCuentas = new JLabel("Elimina las cuentas tildadas del sistema.");
		lblEliminaLasCuentas.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEliminaLasCuentas.setBounds(92, 345, 223, 14);
		contentPane.add(lblEliminaLasCuentas);
		
		JLabel lblAlSistema = new JLabel("Al sistema");
		lblAlSistema.setVerticalAlignment(SwingConstants.TOP);
		lblAlSistema.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblAlSistema.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(178, 34, 34), new Color(188, 143, 143), new Color(178, 34, 34), new Color(205, 92, 92)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		lblAlSistema.setBackground(Color.WHITE);
		lblAlSistema.setAutoscrolls(true);
		lblAlSistema.setBounds(281, 168, 159, 162);
		contentPane.add(lblAlSistema);
		
		JLabel lblCuenta = new JLabel("Cuenta");
		lblCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuenta.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblCuenta.setBorder(null);
		lblCuenta.setBounds(329, 191, 55, 14);
		contentPane.add(lblCuenta);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblValor.setBorder(null);
		lblValor.setBounds(327, 235, 55, 14);
		contentPane.add(lblValor);
		
	}

	private void agregarCuentaGlobalATabla(DefaultTableModel m) {
		//en caso ue no esta decrementar el contador de id de cuenta -- falta
		DAOEmpresa dao=(DAOEmpresa)repo.getDao();
		if(!this.estaCuentaGlobalEn(inCuenta.getText(),m)){
			Cuenta cuenta =new Cuenta(inCuenta.getText(),Double.parseDouble(inValor.getText()),dao);
			cuentasGlobales.add(cuenta);
			Object[]row={new Integer(cuenta.getId()),new String(inCuenta.getText()),new Double(inValor.getText()),new Boolean(false)};
			m.addRow(row);
			
		}else
			JOptionPane.showMessageDialog(null,"La cuenta global ya está cargada.","Información",JOptionPane.INFORMATION_MESSAGE);
	
	}


	private boolean estaCuentaGlobalEn(String text, DefaultTableModel m) {
		
		for(int i=0;i<m.getRowCount();i++)
			if(m.getValueAt(i, 1).equals(text))
				return true;
		
		return false;
	}



	private int comprobacionIngresoUsuario() {
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

	private void loadTable(){
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre", "Valor","Check"
			}
			) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class,String.class, Double.class, Boolean.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

	
		DefaultTableModel m =(DefaultTableModel) table.getModel();
	
		for(Cuenta c:cuentasGlobales){
			Object[]row={c.getId(),c.getName(),new Double(c.getValor()),new Boolean(false)};
			m.addRow(row);
		}
	}

	private boolean asegurarIngresoTextoUsuario(String caracteresValidos,String ingreso){
		for(int i=0;i<ingreso.length();i++){
			if(!caracteresValidos.contains(String.valueOf(ingreso.charAt(i)))){
				return true;
			};		
		}
		return false;
	}

}