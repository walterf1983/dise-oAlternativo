package DAO;

import java.util.ArrayList;

import java.util.stream.Collectors;

import Objects.Empresa;
import Objects.Indicador;
import Objects.Periodo;

public class DAOIndicador extends DAOEmpresa{

	private static int IDIndicador;
	
	public DAOIndicador(String path) {
		super(path);
	}

	public static int getIDIndicador() {
		return IDIndicador;
	}

	public static void setIDIndicador(int iDIndicador) {
		IDIndicador = iDIndicador;
	}

	public static void setupINDEX(String path){
		DAOIndicador daoI=new DAOIndicador(path);
		daoI.setupIndex();
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

	public ArrayList<Indicador> getAllIndicadores(){
		ArrayList<Empresa> empresas=this.getAllEmpresas();
		ArrayList<Indicador>indicadores=new ArrayList<Indicador>();
		for(Empresa empresa:empresas){
			for(Periodo periodo:empresa.getPeriodos()){
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
	
}