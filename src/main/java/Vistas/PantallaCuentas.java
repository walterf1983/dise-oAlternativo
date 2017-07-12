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
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Objects.Cuenta;
import Objects.Empresa;
import Repositorios.RepositorioDeEmpresas;


import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class PantallaCuentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboEmpresa;
	private JComboBox<String> comboAnio;
	private JComboBox<String> comboTipo;
	private RepositorioDeEmpresas repo;
	private JButton botonAgregar;
	private JButton botonCargar;
	private JButton botonVolver;
	ArrayList<Cuenta> cuentasGlobales;
	
	public JButton getBotonAgregar() {
		return botonAgregar;
	}

	public JButton getBotonCargar() {
		return botonCargar;
	}

	public JButton getBotonVolver() {
		return botonVolver;
	}

	public JComboBox<String> getComboAnio() {
		return comboAnio;
	}

	public JComboBox<String> getComboTipo() {
		return comboTipo;
	}

	public JComboBox<String> getComboEmpresa() {
		return comboEmpresa;
	}

	public RepositorioDeEmpresas getRepo() {
		return repo;
	}

	public PantallaCuentas(JFrame fatherWindow, ArrayList<Cuenta> cuentasGlobales) {
		
		setUndecorated(true);
		PantallaCuentas window = this;
		this.cuentasGlobales=cuentasGlobales;
		RepositorioDeEmpresas repoEmpresa=new RepositorioDeEmpresas("Empresas.json");
		repo=repoEmpresa;
		setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));
		setTitle("CargadorCuentas");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-3, 280, 450, 321);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		comboEmpresa = new JComboBox<String>();
		comboEmpresa.setBounds(16, 59, 177, 25);
		contentPane.add(comboEmpresa);
		
		JTextPane txtpnSaddaAssdaAds = new JTextPane();
		txtpnSaddaAssdaAds.setBorder(null);
		txtpnSaddaAssdaAds.setOpaque(false);
		txtpnSaddaAssdaAds.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		txtpnSaddaAssdaAds.setText("Muestra la tabla de cuentas globales del sistema.\r\nTilde las cuentas de la tabla ue desee cargarla al sistema.");
		txtpnSaddaAssdaAds.setBounds(106, 237, 323, 38);
		contentPane.add(txtpnSaddaAssdaAds);
		
		
		botonCargar = new JButton("CARGAR");
		botonCargar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonCargar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonCargar.setBounds(29, 242, 72, 23);
		contentPane.add(botonCargar);
		botonCargar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				window.loadGlobalCuentas();
				if(cuentasGlobales.isEmpty()){
					JOptionPane.showMessageDialog(null, "No hay cuentas globales.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				PantallaCargarCuentas pantallaCargarCuentas=new PantallaCargarCuentas(window, cuentasGlobales);
				botonCargar.setEnabled(false);
				botonVolver.setEnabled(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 123, 385, 108);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSize(new Dimension(0, 0));
		table.setGridColor(new Color(128, 0, 0));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 100, 0), new Color(0, 0, 0), new Color(255, 0, 0), new Color(0, 0, 0)));
		table.setFont(new Font("Consolas",Font.BOLD,14));
		scrollPane.setViewportView(table);
		

	
		
		this.loadEmpresasCombo(comboEmpresa,repoEmpresa);
		contentPane.add(comboEmpresa);
		
		JLabel lblNewLabel = new JLabel("Empresas Disponibles");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(16, 31, 177, 23);
		contentPane.add(lblNewLabel);
		
		comboTipo = new JComboBox<String>();
		comboTipo.setVisible(false);
		comboTipo.setBackground(Color.WHITE);
		comboTipo.setBounds(285, 58, 132, 26);
		contentPane.add(comboTipo);
		
		
		JLabel lblPeriodo = new JLabel("Periodo\r\n");
		lblPeriodo.setVisible(false);
		lblPeriodo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblPeriodo.setBorder(null);
		lblPeriodo.setBounds(285, 35, 95, 14);
		contentPane.add(lblPeriodo);
		
		comboAnio = new JComboBox<String>();
		comboAnio.setVisible(false);
		comboAnio.setBackground(Color.WHITE);
		comboAnio.setBounds(203, 58, 72, 26);
		contentPane.add(comboAnio);
		
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setVisible(false);
		lblAo.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblAo.setBorder(null);
		lblAo.setBounds(203, 35, 65, 14);
		contentPane.add(lblAo);
		
		JLabel lblElijaUnaEmpresa = new JLabel(" Elija una de las empresas dispomibles.");
		lblElijaUnaEmpresa.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(178, 34, 34), new Color(188, 143, 143), new Color(178, 34, 34), new Color(205, 92, 92)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		lblElijaUnaEmpresa.setBackground(new Color(255, 255, 255));
		lblElijaUnaEmpresa.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblElijaUnaEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblElijaUnaEmpresa.setBounds(10, 11, 424, 89);
		contentPane.add(lblElijaUnaEmpresa);
		
		JLabel label = new JLabel("\r\n");
		label.setAutoscrolls(true);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		label.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(178, 34, 34), new Color(188, 143, 143), new Color(178, 34, 34), new Color(205, 92, 92)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		label.setBackground(Color.WHITE);
		label.setBounds(10, 111, 424, 169);
		contentPane.add(label);
			
		this.loadEventComboTipo(repoEmpresa,this);
		this.loadEventComboEmpresa(repoEmpresa,lblElijaUnaEmpresa ,lblAo,lblPeriodo,this);
		this.loadComboBoxAnio(repoEmpresa,this);
		
		botonAgregar = new JButton("AGREGAR");
		
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonAgregar.setEnabled(false);
				@SuppressWarnings("unused")
				PantallaAgregarCuenta epantallaAgregarCuenta=new PantallaAgregarCuenta(window);
				botonVolver.setEnabled(false);
			}
		});
		botonAgregar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonAgregar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonAgregar.setBounds(339, 288, 95, 23);
		contentPane.add(botonAgregar);
		
		botonVolver= new JButton("VOLVER");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				fatherWindow.setVisible(true);
			}
		});
		botonVolver.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonVolver.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonVolver.setBounds(234, 288, 95, 23);
		contentPane.add(botonVolver);
		
	}

	public void loadEventComboTipo(RepositorioDeEmpresas repoEmpresa, JFrame ventana) {
		comboTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboAnio.getSelectedItem()!=null){
					PantallaCuentas p =(PantallaCuentas)ventana;
					p.loadTableByEvent(repoEmpresa);
				}
			
			}
		});
		
	}


	private void loadEmpresasCombo(JComboBox<String> comboBoxEmpresa, RepositorioDeEmpresas empresas){
	
		for(Empresa e:empresas.getAllEmpresas())
			comboBoxEmpresa.insertItemAt(e.getName(), comboBoxEmpresa.getItemCount());
	}

	private void loadEventComboEmpresa(RepositorioDeEmpresas empresasR,JLabel label1,JLabel label2,JLabel label3,JFrame ventana){
		
	comboEmpresa.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			comboAnio.removeAllItems();
			comboTipo.removeAllItems();
			
			String empresa =(String)comboEmpresa.getSelectedItem();
			for(int i:empresasR.getAniosPorEmpresa(empresa))	
				comboAnio.addItem(Integer.toString(i));
				
			label1.setText("Ya puedes elegir año y tipo periodo");
		
			if(!label3.isVisible()){
				label2 .setVisible(true);
				label3.setVisible(true);
				comboAnio.setVisible(true);
				comboTipo.setVisible(true);
			}
			PantallaCuentas p =(PantallaCuentas)ventana;
			p.loadTableByEvent(empresasR);
				
		}
	});
}
	
private void loadComboBoxAnio(RepositorioDeEmpresas empresasR,JFrame ventana){
		comboAnio.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
				
			comboTipo.removeAllItems();
			if(comboAnio.getItemAt(0)!=null){
				String anioS=(String)comboAnio.getSelectedItem();
			
			int anioN=Integer.parseInt(anioS);
			String empresa=(String)comboEmpresa.getSelectedItem();
				
			for(String i:empresasR.getTipoPeriodoPorEmpresa(empresa,anioN))
				comboTipo.addItem(i);
				
			PantallaCuentas p =(PantallaCuentas)ventana;
			p.loadTableByEvent(empresasR);	
			}
		}
	});
}
	
	private void loadTableByEvent(RepositorioDeEmpresas empresasR){
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre", "Valor", "Check"
			}
			) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Boolean.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		String empresa=(String)comboEmpresa.getSelectedItem();
	
		DefaultTableModel m =(DefaultTableModel) table.getModel();
	
		for(Cuenta c:empresasR.getCuentasPor(empresa,Integer.parseInt((String)comboAnio.getSelectedItem()),(String)comboTipo.getSelectedItem())){
			Object[]row={c.getIdCuenta(),c.getName(),new Double(c.getValor()),new Boolean(false)};
			m.addRow(row);
		}
}

	private void loadGlobalCuentas(){
		DefaultTableModel m=(DefaultTableModel) table.getModel();
		for(int i=0;i<m.getRowCount();i++){
			if((boolean)m.getValueAt(i, 3)){
				int c=i;
				if(!cuentasGlobales.
					stream().
					filter(e->e.getName().equalsIgnoreCase((String)m.getValueAt(c,1))).
					findFirst().
					isPresent())
				cuentasGlobales.add(new Cuenta(new Integer((int) m.getValueAt(i,0)),(String) m.getValueAt(i,1),(Double) m.getValueAt(i,2)));
			}
		}	
	}
	
}