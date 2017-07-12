package Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

	public class Periodo {
	
	private static int ID;
	
	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	
	private int id;
	private int anio;
	private String tipo;
	private ArrayList<Cuenta> cuentas;

	
	public Periodo(int id, int anio, String tipo) {
		super();
		this.id = id;
		this.anio = anio;
		this.tipo = tipo;
		this.cuentas = new ArrayList<Cuenta>();
	}
	
	public Periodo(int id, int anio, String tipo,ArrayList<Cuenta>cuentas) {
		super();
		this.id = id;
		this.anio = anio;
		this.tipo = tipo;
		this.cuentas = cuentas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	
	public Cuenta getCuentaxNombre(String nombre) {
		List<Cuenta> filters =  this.getCuentas().stream()
												 .filter(c -> c.getName().equals(nombre))
												 .collect(Collectors.toList());
		if(filters.size() > 0) {
			return filters.get(0);
		}
		return null;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	public int addCuenta(Cuenta cuenta){
		int a;
		if(!this.estaCuenta(cuenta)){//no esta la cuenta
			this.cuentas.add(cuenta);
			a=3;
		}else{//esta la cuenta
			int input = JOptionPane.showConfirmDialog(null, "La cuenta se encuentra para ese periodo\n"
						+ "¿Deseas agregarla de todos modos?", "Elija una opción",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (input==0){//modifica
				this.deleteCuenta(cuenta);
				this.getCuentas().add(cuenta);
				a=0;
			}else{//noModifica
				a=4;
			}
		}
		return a;
	}
	

	public void deleteCuenta(Cuenta cuenta){
		this.setCuentas(this.getCuentas().
							stream().
							filter(e->!	e.esCuenta(cuenta)).
							collect(Collectors.toCollection(ArrayList::new)));
	}


	public void actualizarCuenta(Cuenta cuenta, int idCuenta, String nombre, double valor){
		cuenta.actualizate(idCuenta,nombre,valor);
	}
	
	public String getNombre(){
		return this.tipo + " - " + this.anio;
	}
	
	/* Static methods */
	
	public static String[] getNombresPeriodos(ArrayList<Periodo> periodos) {
		List<String> result = periodos.stream()
									  .map(periodo -> periodo.getNombre())
									  .collect(Collectors.toList());
		return result.toArray(new String[result.size()]);
	}
	
	public static Periodo getPeriodoxNombre(ArrayList<Periodo> periodos, String nombre){
		return periodos
				.stream()
				.filter(periodo -> periodo.getNombre().equals(nombre))
				.collect(Collectors.toList())
				.get(0);
	}
	
	public static int getNextId(Empresa empresa){
		ArrayList<Periodo> periodos = empresa.getPeriodos();
		return periodos.size() + 1;
	}

	public boolean esPeriodo(Periodo periodo) {
		return (this.anio==periodo.anio&&this.tipo.equalsIgnoreCase(periodo.tipo));
		
	}

	public boolean estaCuenta(Cuenta cuenta) {
		return (this.getCuentas().
				stream().
				filter(e->e.esCuenta(cuenta)).
				findFirst().
				isPresent());
	}


}