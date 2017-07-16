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

@SuppressWarnings("serial")
public class LaminaSubindicadores extends JPanel {


	public LaminaSubindicadores(JFrame fatherWindow, ArrayList<Cuenta> cuentasGlobales) {
		
		LaminaSubindicadores window = this;
		this.cuentasGlobales=cuentasGlobales;
		RepositorioDeEmpresas repoEmpresa=new RepositorioDeEmpresas("Empresas.json");
		repo=repoEmpresa;
		//PantallaIndicadores pantalla=(PantallaIndicadores)fatherWindow;
		setVisible(true);
		setBackground(new Color(220, 220, 220));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		botonVolver= new JButton("VOLVER");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fatherWindow.setVisible(true);
			}
		});
		botonVolver.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonVolver.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonVolver.setBounds(469, 148, 168, 23);
		add(botonVolver);
		
		
		botonCargar = new JButton("CARGAR");
		botonCargar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		botonCargar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		botonCargar.setBounds(565, 11, 72, 131);
		add(botonCargar);
		botonCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.loadGlobalCuentas();
				if(cuentasGlobales.isEmpty()){
					JOptionPane.showMessageDialog(null, "No hay cuentas globales.", "Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				botonCargar.setEnabled(false);
				botonVolver.setEnabled(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 34, 515, 97);
		add(scrollPane);
		
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
		
		JLabel lblNewLabel = new JLabel("Empresas Disponibles");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(27, 11, 177, 23);
		add(lblNewLabel);
				
		JLabel lblPeriodo = new JLabel("Periodo\r\n:");
		lblPeriodo.setVisible(false);
		lblPeriodo.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblPeriodo.setBorder(null);
		lblPeriodo.setBounds(432, 15, 95, 14);
		add(lblPeriodo);
		
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setVisible(false);
		lblAo.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
		lblAo.setBorder(null);
		lblAo.setBounds(273, 15, 65, 14);
		add(lblAo);
			
		this.
		loadEventComboTipo(repoEmpresa,this);
		this.loadComboBoxAnio(repoEmpresa,this);
		
		
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		label.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 255), new Color(102, 205, 170), new Color(0, 0, 205), new Color(72, 209, 204)), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(128, 0, 0)));
		label.setBackground(Color.WHITE);
		label.setBounds(9, 11, 556, 131);
		add(label);
					
	}

	private ArrayList<Cuenta> cuentasGlobales;
	private JTable table;
	private RepositorioDeEmpresas repo;
	private JButton botonCargar;
	private JButton botonVolver;

	public JButton getBotonCargar() {
		return botonCargar;
	}

	public JButton getBotonVolver() {
		return botonVolver;
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

			DefaultTableModel m =(DefaultTableModel) table.getModel();
			
			//falta defenir el tipo y si va o no check
			
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

	private void loadEventComboEmpresa(RepositorioDeEmpresas empresasR,JLabel label1,JLabel label2,JLabel label3,LaminaSubindicadores laminaSubindicadores){
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

	private void loadComboBoxAnio(RepositorioDeEmpresas empresasR,LaminaSubindicadores laminaSubindicadores){
	}

	private void loadEventComboTipo(RepositorioDeEmpresas repoEmpresa, LaminaSubindicadores laminaSubindicadores) {
		
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