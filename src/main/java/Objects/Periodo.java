package Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Periodo {
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
	
	public void addCuenta(Cuenta cuenta){
		this.cuentas.add(cuenta);
	}
	

	public void deleteCuenta(Cuenta cuenta){
		this.cuentas.remove(cuenta);
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


}