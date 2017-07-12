package Objects;
import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public Empresa(int id, String nombreEmpresa, ArrayList<Periodo> periodos) {
		super();
		this.id = id;
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
		
		if(!this.estaPeriodo(periodo)){//no está el periodo
			this.getPeriodos().add(periodo);
			a=2;
		}else{//esta el periodo
			a=this.getPeriodo(periodo).addCuenta(cuenta);
		}
		return a;
	}
	
	public boolean estaCuenta(Cuenta cuenta,Periodo periodo) {
		return this.getPeriodo(periodo).estaCuenta(cuenta);
	}

	public Periodo getPeriodo(Periodo periodo) {
		return (this.getPeriodos().
				stream().
				filter(e->e.esPeriodo(periodo)).
				findFirst().
				get());
		
	}

	public boolean estaPeriodo(Periodo periodo) {
		return (this.getPeriodos().
				stream().
				filter(e->e.esPeriodo(periodo)).
				findFirst().
				isPresent());
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
}
