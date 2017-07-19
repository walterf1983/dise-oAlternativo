package Repositorios;

import java.util.ArrayList;


import java.util.stream.Collectors;
import DAO.DAOEmpresa;
import Objects.Empresa;

public class RepositorioDeEmpresas extends RepositorioGenerico {

	private int getIdPeriodo;

	public int getGetIdPeriodo() {
		return getIdPeriodo;
	}

	public RepositorioDeEmpresas(String path) {
		super(new DAOEmpresa(path));
	}
	
	public ArrayList<Empresa> getAllEmpresas() {
		ArrayList<Empresa> empresas=new ArrayList<Empresa>();
		try {
			empresas =  (ArrayList<Empresa>) this.getAll()
			.stream()
			.map(item->(Empresa)item)
			.collect(Collectors.toList());
		} catch (Exception e) {
		//	e.printStackTrace();
		}
		return empresas;
	}
	
	public Empresa getxNombreEmpresa(String nombre) throws Exception {
		return (Empresa)this.getxNombre(nombre);
	}
	
}