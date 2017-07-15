package DAO;

import java.util.ArrayList;

import java.util.stream.Collectors;

import Objects.Empresa;
import Objects.Indicador;
import Objects.Periodo;

public class DAOIndicador extends DAOEmpresa{

	public DAOIndicador(String path) {
		super(path);
	}

	private static int IDIndicador;
	
	public static int getIDIndicador() {
		return IDIndicador;
	}

	public static void setupINDEX(String path){
		DAOIndicador daoI=new DAOIndicador(path);
		daoI.setupIndex();
	}

	private static void setIDIndicador(int iDIndicador) {
		IDIndicador = iDIndicador;
	}

	private void setupIndexIndicador(){
		DAOIndicador.setIDIndicador(0);
		ArrayList<Integer>ids=this.getAllIndicadores().
								stream().
								map(e->e.getId()).
								collect(Collectors.toCollection(ArrayList::new));
		for(int e:ids){
			if(e>DAOIndicador.getIDIndicador()){
				DAOIndicador.setIDIndicador(e);
			}
		}
	}
		
	private void setupIndex(){
		this.setupIndexIndicador();
	}
	
	private void cargarIndicadoresNoRepetidosRecorriendoArbol(ArrayList <Indicador> arbol,ArrayList<Indicador> indicadoresRelleno){
		
		for(Indicador indicador:arbol){
			if(!indicador.estaEn(indicadoresRelleno)){
				indicadoresRelleno.add(indicador);
				cargarIndicadoresNoRepetidosRecorriendoArbol(indicador.getIndicadores(),indicadoresRelleno);
				}
			}
	}

	private boolean estaIndicador(String formula) {
		return this.getAllIndicadores().
				stream().
				filter(e->e.getFormula().
				equals(formula)).
				findFirst().
				isPresent();
	}

	private Indicador buscarIndicador(String formula){
		return this.getAllIndicadores().
				stream().
				filter(e->e.getFormula().
				equals(formula)).
				findFirst().
				get();
	}

	private Periodo buscarPeriodoDeEmpresa(String nombreEmpresa, int anio, String tipo) {
		Empresa empresa=this.buscarEmpresa(nombreEmpresa);
		return empresa.getPeriodo(anio, tipo);
	}

	public ArrayList<Indicador> getAllIndicadores(){
		ArrayList<Empresa> empresas=this.getAllEmpresas();
		ArrayList<Indicador>indicadores=new ArrayList<Indicador>();
		for(Empresa empresa:empresas){
			for(Periodo periodo:empresa.getPeriodos()){
				if(!periodo.getIndicadores().isEmpty())
					cargarIndicadoresNoRepetidosRecorriendoArbol(periodo.getIndicadores(),indicadores);
			}
		}
	
		return indicadores;
	}

	public int bucarIDIndicador(String formula) {
		if(!this.estaIndicador(formula)){
			IDIndicador=IDIndicador+1;
			return IDIndicador ;
		}
		return buscarIndicador(formula).getId();
	}

	public ArrayList<Indicador> getIndicadoresNoRepetidosPorEmpresa(String nombreEmpresa, int anio, String tipo) {
		Periodo periodo=this.buscarPeriodoDeEmpresa(nombreEmpresa,anio,tipo);
		periodo.getIndicadores();
		ArrayList<Indicador>indicadoresRelleno=new ArrayList<Indicador>();
		cargarIndicadoresNoRepetidosRecorriendoArbol(periodo.getIndicadores(), indicadoresRelleno);
		return indicadoresRelleno;
	}

	@Override
	public ArrayList<Integer> getAniosPorEmpresa(String empresa) {
		
		ArrayList<Integer> anios=new ArrayList<Integer>();
		
		
			for(Empresa e:this.getAllEmpresas())
				if (e.getName().equals(empresa))
					for(Periodo p:e.getPeriodos())
						if(!anios.contains(p.getAnio())&&!p.getIndicadores().isEmpty())
							anios.add(p.getAnio());
			
				
			
		return anios;
	}
	
	@Override
	public ArrayList<String> getTipoPeriodoPorEmpresa(String empresa, int anio) {
		ArrayList<String> tipos=new ArrayList<String>();
			for(Empresa e:this.getAllEmpresas())
				if (e.getName().equals(empresa))
					for(Periodo p:e.getPeriodos())
						if(tipos.indexOf(p.getTipo())==-1)
							if(p.getAnio()==anio)
								if(!p.getIndicadores().isEmpty())
									tipos.add(p.getTipo());
		return tipos;
	}
	
}
	
	
	
