package DAO;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;

import Objects.Empresa;
import Objects.Indicador;

public class DAOIndicador extends DAOGenerico{

	private static int IDIndicador;
	
	public DAOIndicador(String path) {
		super(path,new TypeToken<ArrayList<Indicador>>(){}.getType());
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
		
	public ArrayList<Indicador> getAllIndicadores(){
		ArrayList<Indicador> indicadores=new ArrayList<Indicador>();
		try {
			 indicadores= this.getAllItems().
							stream().
							map(e->(Indicador)e).
							collect(Collectors.toCollection(ArrayList::new));
		} catch (Exception e) {
		}
		return indicadores;
	}
	
	private void setupIndex(){
		this.setupIndexIndicador();
	}
}