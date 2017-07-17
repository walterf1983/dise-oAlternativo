package Vistas;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import DAO.DAOEmpresa;
import DAO.DAOIndicador;
import Objects.Cuenta;
import Objects.Empresa;
import Objects.Indicador;
import Objects.Periodo;
import Repositorios.RepositorioDeEmpresas;


import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class PantallaSubindicadores extends JFrame {

	private JTabbedPane pestanias;
	private JPanel contentPane1;
	public PantallaSubindicadores(JFrame fatherWindow, ArrayList<Cuenta> cuentasGlobales) {
		
		setUndecorated(true);
		PantallaSubindicadores window = this;
		this.cuentasGlobales=cuentasGlobales;
		RepositorioDeEmpresas repoEmpresa=new RepositorioDeEmpresas("src//main//java//DB//Empresas.json");
		repo=repoEmpresa;
		PantallaIndicadores pantalla=(PantallaIndicadores)fatherWindow;
		setIconImage(Toolkit.getDefaultToolkit().getImage("src//main//java//VariablesGraficas//ima.jpg"));
		setTitle("CargadorCuentas");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setBounds(100, 280+325, 589+200, 325);
		setBounds(pantalla.getX(),pantalla.getY()-pantalla.getHeight()+140,pantalla.getWidth()+75,pantalla.getHeight()-140);
	
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		contentPane1 = new JPanel();
		contentPane1.setBackground(new Color(220, 220, 220));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(null);
		
		botonVolver= new JButton("VOLVER");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				fatherWindow.setVisible(true);
			}
		});
		botonVolver.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonVolver.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonVolver.setBounds(267, 181, 95, 23);
		contentPane1.add(botonVolver);
		
		comboEmpresa = new JComboBox<String>();
		comboEmpresa.setBackground(Color.WHITE);
		comboEmpresa.setBounds(27, 195, 177, 25);
		contentPane1.add(comboEmpresa);
		
		JTextPane txtpnSaddaAssdaAds = new JTextPane();
		txtpnSaddaAssdaAds.setBorder(null);
		txtpnSaddaAssdaAds.setOpaque(false);
		txtpnSaddaAssdaAds.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		txtpnSaddaAssdaAds.setText("Muestra la tabla de indicadores globales del sistema.\r\nTilde los indicadores de la tabla ue desee cargar al sistema.");
		txtpnSaddaAssdaAds.setBounds(243, 236, 308, 25);
		contentPane1.add(txtpnSaddaAssdaAds);
		
		
		botonCargar = new JButton("CARGAR");
		botonCargar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonCargar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonCargar.setBounds(249, 196, 72, 23);
		contentPane1.add(botonCargar);
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
		scrollPane.setBounds(29, 34, 515, 97);
		contentPane1.add(scrollPane);
		
		table = new JTable(getTableDefaultModel()){
			@Override
			public Component prepareRenderer(TableCellRenderer renderer,int rowIndex,int columnIndex) {
				Component d=super.prepareRenderer(renderer, rowIndex, columnIndex);      
				if(columnIndex ==5)
	        	d.setBackground(Color.BLUE);
			return d;
	    }};
		this.establecerColumnas(new int[]{0,3});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==3&&table.getSelectedColumn()!=5&&0==arg0.getModifiersEx())
					System.out.println("doble");
				}});
		table.setDropMode(DropMode.INSERT);
		table.setSize(new Dimension(0, 0));
		table.setGridColor(new Color(0,0,128));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(0, 191, 255), new Color(0, 0, 255), new Color(64, 224, 208)));
		table.setFont(new Font("Consolas",Font.BOLD,14));
		scrollPane.setViewportView(table);

		this.loadEmpresasCombo(comboEmpresa,repoEmpresa);
		contentPane1.add(comboEmpresa);
		
		JLabel lblNewLabel = new JLabel("Empresas Disponibles");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(27, 11, 177, 23);
		contentPane1.add(lblNewLabel);
		
		comboTipo = new JComboBox<String>();
		comboTipo.setVisible(false);
		comboTipo.setBackground(Color.WHITE);
		comboTipo.setBounds(107, 179, 132, 26);
		contentPane1.add(comboTipo);
		
		
		JLabel lblPeriodo = new JLabel("Periodo\r\n:");
		lblPeriodo.setVisible(false);
		lblPeriodo.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblPeriodo.setBorder(null);
		lblPeriodo.setBounds(432, 15, 95, 14);
		contentPane1.add(lblPeriodo);
		
		comboAnio = new JComboBox<String>();
		comboAnio.setVisible(false);
		comboAnio.setBackground(Color.WHITE);
		comboAnio.setBounds(10, 179, 72, 26);
		contentPane1.add(comboAnio);
		
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setVisible(false);
		lblAo.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblAo.setBorder(null);
		lblAo.setBounds(273, 15, 65, 14);
		contentPane1.add(lblAo);
		
		JLabel lblElijaUnaEmpresa = new JLabel("");
		lblElijaUnaEmpresa.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(102, 205, 170), new Color(0, 0, 205), new Color(72, 209, 204)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(128, 0, 0)));
		lblElijaUnaEmpresa.setBackground(new Color(255, 255, 255));
		lblElijaUnaEmpresa.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblElijaUnaEmpresa.setVerticalAlignment(SwingConstants.TOP);
		lblElijaUnaEmpresa.setBounds(10, 231, 554, 43);
		contentPane1.add(lblElijaUnaEmpresa);
			
		this.loadEventComboTipo(repoEmpresa,this);
		this.loadEventComboEmpresa(repoEmpresa,lblElijaUnaEmpresa ,lblAo,lblPeriodo,this);
		this.loadComboBoxAnio(repoEmpresa,this);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		label.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(102, 205, 170), new Color(0, 0, 205), new Color(72, 209, 204)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(128, 0, 0)));
		label.setBackground(Color.WHITE);
		label.setBounds(9, 11, 556, 130);
		contentPane1.add(label);
		
		
		pestanias=new JTabbedPane();
		getContentPane().add(pestanias);
		pestanias.addTab("Tabla1",contentPane1);
		LaminaSubindicadores t=new LaminaSubindicadores(this,cuentasGlobales);
		pestanias.addTab("Tabla2",t);
		
		
		
	}

	private ArrayList<Cuenta> cuentasGlobales;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboEmpresa;
	private JComboBox<String> comboAnio;
	private JComboBox<String> comboTipo;
	private RepositorioDeEmpresas repo;
	private JButton botonCargar;
	private JButton botonVolver;
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

	private TableModel getTableDefaultModel() {
		return new DefaultTableModel(
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
	};}

	
	private void loadTableByEvent(RepositorioDeEmpresas empresasR){
			table.setModel(getTableDefaultModel());
			this.establecerColumnas(new int[]{0,3});
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

	private void establecerColumnas(int[] columnas) {
		for(int columna:columnas){
			table.getColumnModel().getColumn(columna).setCellRenderer(new DefaultTableCellRenderer(){
				  @Override
				    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				        this.setHorizontalAlignment(SwingConstants.CENTER);
				        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			 }});
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
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
				PantallaSubindicadores p =(PantallaSubindicadores)ventana;
				p.loadTableByEvent(empresasR);
					
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
				PantallaSubindicadores p =(PantallaSubindicadores)ventana;
				p.loadTableByEvent(empresasR);	
				}
			}
		});
	}

	private void loadEventComboTipo(RepositorioDeEmpresas repoEmpresa, JFrame ventana) {
		comboTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboTipo.getSelectedItem()!=null){
					PantallaSubindicadores p =(PantallaSubindicadores)ventana;
					p.loadTableByEvent(repoEmpresa);
				}
			
			}
		});
		
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