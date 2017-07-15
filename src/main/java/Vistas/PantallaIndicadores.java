package Vistas;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.DAOEmpresa;
import DAO.DAOIndicador;
import Objects.Cuenta;
import Objects.Empresa;
import Objects.Indicador;
import Objects.Periodo;
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
import javax.swing.DropMode;

@SuppressWarnings("serial")
public class PantallaIndicadores extends JFrame {

	public PantallaIndicadores(JFrame fatherWindow, ArrayList<Cuenta> cuentasGlobales) {
		
		setUndecorated(true);
		PantallaIndicadores window = this;
		this.cuentasGlobales=cuentasGlobales;
		RepositorioDeEmpresas repoEmpresa=new RepositorioDeEmpresas("Empresas.json");
		repo=repoEmpresa;
		setIconImage(Toolkit.getDefaultToolkit().getImage("ima.jpg"));
		setTitle("CargadorCuentas");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-3, 280, 589, 438);
		setLocationRelativeTo(null);
	
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
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
		botonAgregar.setBounds(471, 392, 95, 23);
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
		botonVolver.setBounds(366, 392, 95, 23);
		contentPane.add(botonVolver);
		
		comboEmpresa = new JComboBox<String>();
		comboEmpresa.setBackground(Color.WHITE);
		comboEmpresa.setBounds(16, 59, 177, 25);
		contentPane.add(comboEmpresa);
		
		JTextPane txtpnSaddaAssdaAds = new JTextPane();
		txtpnSaddaAssdaAds.setBorder(null);
		txtpnSaddaAssdaAds.setOpaque(false);
		txtpnSaddaAssdaAds.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		txtpnSaddaAssdaAds.setText("Muestra la tabla de indicadores globales del sistema.\r\nTilde los indicadores de la tabla ue desee cargar al sistema.");
		txtpnSaddaAssdaAds.setBounds(106, 359, 308, 25);
		contentPane.add(txtpnSaddaAssdaAds);
		
		
		botonCargar = new JButton("CARGAR");
		botonCargar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonCargar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonCargar.setBounds(24, 359, 72, 23);
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
		scrollPane.setBounds(22, 123, 544, 228);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setDropMode(DropMode.INSERT);
		table.setSize(new Dimension(0, 0));
		table.setGridColor(new Color(128, 0, 0));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(0, 191, 255), new Color(0, 0, 255), new Color(64, 224, 208)));
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
		lblElijaUnaEmpresa.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(102, 205, 170), new Color(0, 0, 205), new Color(72, 209, 204)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(128, 0, 0)));
		lblElijaUnaEmpresa.setBackground(new Color(255, 255, 255));
		lblElijaUnaEmpresa.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblElijaUnaEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblElijaUnaEmpresa.setBounds(10, 11, 569, 90);
		contentPane.add(lblElijaUnaEmpresa);
			
		this.loadEventComboTipo(repoEmpresa,this);
		this.loadEventComboEmpresa(repoEmpresa,lblElijaUnaEmpresa ,lblAo,lblPeriodo,this);
		this.loadComboBoxAnio(repoEmpresa,this);
		
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		label.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(102, 205, 170), new Color(0, 0, 205), new Color(72, 209, 204)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(128, 0, 0)));
		label.setBackground(Color.WHITE);
		label.setBounds(10, 112, 569, 315);
		contentPane.add(label);
		
	}

	private ArrayList<Cuenta> cuentasGlobales;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboEmpresa;
	private JComboBox<String> comboAnio;
	private JComboBox<String> comboTipo;
	private RepositorioDeEmpresas repo;
	private JButton botonAgregar;
	private JButton botonCargar;
	private JButton botonVolver;
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

	private void loadEventComboEmpresa(RepositorioDeEmpresas empresasR,JLabel label1,JLabel label2,JLabel label3,JFrame ventana){
		comboEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboAnio.removeAllItems();
				comboTipo.removeAllItems();
				empresasR.setDao(new DAOIndicador("EmpresasGenerica.json"));
				DAOIndicador dao=(DAOIndicador)empresasR.getDao();
				String empresa =(String)comboEmpresa.getSelectedItem();
				for(int i:dao.getAniosPorEmpresa(empresa)){	
					comboAnio.addItem(Integer.toString(i));
				}
				label1.setText("Ya puedes elegir año y tipo periodo");
			
				if(!label3.isVisible()){
					label2 .setVisible(true);
					label3.setVisible(true);
					comboAnio.setVisible(true);
					comboTipo.setVisible(true);
				}
				PantallaIndicadores p =(PantallaIndicadores)ventana;
				p.loadTableByEvent(empresasR);
					
			}
		});
	}

	private void loadComboBoxAnio(RepositorioDeEmpresas empresasR,JFrame ventana){
			comboAnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empresasR.setDao(new DAOIndicador("EmpresasGenerica.json"));
				DAOIndicador dao=(DAOIndicador)empresasR.getDao();
				
				String empresa=(String) comboEmpresa.getSelectedItem();
				comboTipo.removeAllItems();
				
				if(comboAnio.getItemAt(0)!=null){
					String anioS=(String)comboAnio.getSelectedItem();
				
				int anioN=Integer.parseInt(anioS);
				
				for(String i:dao.getTipoPeriodoPorEmpresa(empresa,anioN)){
					comboTipo.addItem(i);
				}
				PantallaIndicadores p =(PantallaIndicadores)ventana;
				p.loadTableByEvent(empresasR);	
				}
			}
		});
	}

	private void loadEventComboTipo(RepositorioDeEmpresas repoEmpresa, JFrame ventana) {
		comboTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboTipo.getSelectedItem()!=null){
					PantallaIndicadores p =(PantallaIndicadores)ventana;
					p.loadTableByEvent(repoEmpresa);
				}
			
			}
		});
		
	}


	private void loadEmpresasCombo(JComboBox<String> comboBoxEmpresa, RepositorioDeEmpresas empresas){
	
		for(Empresa e:empresas.getAllEmpresas()){
			for(Periodo periodo:e.getPeriodos())
				if(!periodo.getIndicadores().isEmpty()){
					comboBoxEmpresa.insertItemAt(e.getName(), comboBoxEmpresa.getItemCount());
					break;
				}
		}
	}
	private void loadTableByEvent(RepositorioDeEmpresas empresasR){
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Nombre", "Formula", "Valor", "Tipo", "Check"
				}
			) {
				@Override
				public boolean isCellEditable(int row, int column) {
					if(column<5)
						return false;
					return true;
			}
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, Double.class, String.class, Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			table.getColumnModel().getColumn(0).setPreferredWidth(34);
			table.getColumnModel().getColumn(1).setPreferredWidth(96);
			table.getColumnModel().getColumn(2).setPreferredWidth(145);
			table.getColumnModel().getColumn(4).setPreferredWidth(142);
			table.getColumnModel().getColumn(5).setPreferredWidth(41);
		DAOIndicador dao=(DAOIndicador)empresasR.getDao();
	
	
		String empresaS=(String)comboEmpresa.getSelectedItem();	
		Empresa empresa=dao.buscarEmpresa(empresaS);
		Periodo periodo= empresa.getPeriodo(Integer.parseInt((String) comboAnio.getSelectedItem()),(String)comboTipo.getSelectedItem());
		DefaultTableModel m =(DefaultTableModel) table.getModel();
		
		//falta defenir el tipo y si va o no check
		for(Indicador c:periodo.getIndicadores()){
			Object[]row={c.getId(),c.getName(),new String(c.getFormula()),new Double(24),new String(c.getClass().getSimpleName()),new Boolean(false)};
			m.addRow(row);
		}
}

	private void loadGlobalCuentas(){
		DefaultTableModel m=(DefaultTableModel) table.getModel();
		DAOEmpresa dao=(DAOEmpresa)repo.getDao();
		for(int i=0;i<m.getRowCount();i++){
			if((boolean)m.getValueAt(i, 3)){
				int c=i;
				if(!cuentasGlobales.
					stream().
					filter(e->e.getName().equalsIgnoreCase((String)m.getValueAt(c,1))).
					findFirst().
					isPresent())
				cuentasGlobales.add(new Cuenta((String) m.getValueAt(i,1),(Double) m.getValueAt(i,2),dao));
			}
		}	
	}
}