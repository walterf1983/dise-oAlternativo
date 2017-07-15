package Objects;
import java.io.Serializable;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DAO.DAOEmpresa;

@SuppressWarnings("serial")
public class Empresa extends Item implements Serializable{
	
	private static int ID;
	
	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	private int id;
	private String nombreEmpresa;
	private ArrayList<Periodo> periodos;
	
	public Empresa(String nombreEmpresa, ArrayList<Periodo> periodos,DAOEmpresa dao) {
		super();
		this.id=dao.buscarIDEmpresa(nombreEmpresa);
		this.nombreEmpresa = nombreEmpresa;
		this.periodos = periodos;
	}
	
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public void setPeriodos(ArrayList<Periodo> periodos) {
		this.periodos = periodos;
	}
	
	public void addPeriodo(Periodo periodo){
		this.periodos.add(periodo);
	}
	
	public ArrayList<Periodo> getPeriodos(){
		return periodos;
	}
	
	public int addCuenta(Cuenta cuenta, Periodo periodo){

		int a;
		
		if(!this.estaPeriodo(periodo.getAnio(),periodo.getTipo())){//no está el periodo
			this.getPeriodos().add(periodo);
			a=2;
		}else{//esta el periodo
			a=this.getPeriodo(periodo.getAnio(),periodo.getTipo()).addCuenta(cuenta);
		}
		return a;
	}
	
	public boolean estaCuenta(Cuenta cuenta,Periodo periodo) {
		return this.getPeriodo(periodo.getAnio(),periodo.getTipo()).estaCuenta(cuenta);
	}

	public Periodo getPeriodo(int anio, String tipo) {
		return (this.getPeriodos().
				stream().
				filter(e->e.getAnio()==anio&&e.getTipo().equalsIgnoreCase(tipo)).
				findFirst().
				get());
		
	}

	/* Static methods */
	

	
	public static  String[] getNombresEmpresas(ArrayList<? extends Item> empresas) {
		List<String> result = empresas.stream()
									  .map(empresa -> empresa.getName())
									  .collect(Collectors.toList());
		return result.toArray(new String[result.size()]);
	}
	
	public static Empresa getEmpresaxNombre(ArrayList<? extends Item> empresas, String nombre){
		return(Empresa) empresas.stream()
					   .filter(empresa -> empresa.getName().equals(nombre))
					   .collect(Collectors.toList())
					   .get(0);
	}

	public boolean estaPeriodo(int anio, String tipo) {
		return this.getPeriodos().
					stream().
					filter(e->e.getAnio()==anio&&e.getTipo().equalsIgnoreCase(tipo)).
					findFirst().
					isPresent();
	}
}
