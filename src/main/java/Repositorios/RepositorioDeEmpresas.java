package Repositorios;

import java.util.ArrayList;

import java.util.stream.Collectors;
import DAO.DAOEmpresa;
import Objects.Cuenta;
import Objects.Empresa;
import Objects.Periodo;

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
			e.printStackTrace();
		}
		return empresas;
	}
	
	public Empresa getxNombreEmpresa(String nombre) throws Exception {
		return (Empresa)this.getxNombre(nombre);
	}
	
	public ArrayList<Integer> getAniosPorEmpresa(String empresa){
		
		ArrayList<Integer> anios=new ArrayList<Integer>();
		
		
			for(Empresa e:this.getAllEmpresas()){
				if (e.getName().equals(empresa)){
					for(Periodo p:e.getPeriodos()){
						if(!anios.contains(p.getAnio()))
							anios.add(p.getAnio());
						}
				}	
			}
		return anios;
	}
	
	public ArrayList<String> getTipoPeriodoPorEmpresa(String empresa,int anio){
		ArrayList<String> tipos=new ArrayList<String>();
			for(Empresa e:this.getAllEmpresas()){
				if (e.getName().equals(empresa)){
					for(Periodo p:e.getPeriodos()){
						if(tipos.indexOf(p.getTipo())==-1)
							if(p.getAnio()==anio){
								tipos.add(p.getTipo());
							}
						}
				}	
			}
		return tipos;
	}
	
	public ArrayList<Cuenta>getCuentasPor(String empresa, int anio, String periodo){
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		for (Empresa e:this.getAllEmpresas()){
			if(empresa.equals(e.getName())){
				for(Periodo p:e.getPeriodos()){
					if(p.getAnio()==anio&&p.getTipo().equals(periodo)){
						cuentas=p.getCuentas();
					}
				}
			}
		}
		return cuentas;
	}

	
	public int addCuenta(String nombreEmpresa, String anio, String tipo, String cuenta, String valor)throws Exception {
		
		Double value=Double.parseDouble(valor);
		Empresa empresa = null;	
		ArrayList<Periodo>periodos=new ArrayList<Periodo>();
		ArrayList<Cuenta> aCuentas=new ArrayList<Cuenta>();
		aCuentas.add(new Cuenta(5,cuenta,value));
				
		int u=1;
		try{//esta la empresa	
		empresa=this.getxNombreEmpresa(nombreEmpresa);
		}catch(Exception e){//no esta la empresa
			periodos.add(new Periodo(0, Integer.parseInt(anio),tipo,aCuentas));
			this.add(new Empresa(this.getLastId(),nombreEmpresa,periodos));
			e.printStackTrace();
		}
		
		u=empresa.addCuenta(aCuentas.get(0), new Periodo(this.getIdPeriodo,Integer.parseInt(anio),tipo,aCuentas));
		
		if(u==2||u==3||u==0)
			this.update(nombreEmpresa, empresa);
		return u;
		}
	
}