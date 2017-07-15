package Objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import DAO.DAOIndicador;

@SuppressWarnings("serial")
public class IndicadorAbs extends Item implements Serializable{

	public IndicadorAbs(String formula,String name, ArrayList<String>nombreCuentas,ArrayList <Indicador>indicadores,DAOIndicador dao) {
		this.id = dao.bucarIDIndicador(formula);
		this.formula = formula;
		this.name = name;
		this.cuentasName=nombreCuentas;
		this.indicadores=indicadores;
	}
	protected int id;
	protected String formula;
	protected String name;
	protected ArrayList<String> cuentasName=new ArrayList<String>();
	protected ArrayList<Indicador> indicadores=new ArrayList<Indicador>() ;
	protected boolean mutable; 
	
	static public int ID;
	
	void add(Indicador i) {
		this.indicadores.add(i);
	}

	void delete(Indicador i) {
		this.indicadores
		.stream()
		.filter(e->e.getId()==i.getId());	
	}
		
	
	public static void setID(int iD) {
		ID = iD;
	}
	static public int nextID(){
		return ID=ID+1;
	}
	
	public static int getID() {
		return ID;
	}


	public void addCuenta(String cuenta){
		this.cuentasName.add(cuenta);
	}
	
	public double calcular(Periodo periodo) throws IOException {
		//ArrayList<Indicador2> indicadores = RepositorioDeIndicadores.getAllIndicadores();
		
		Expression expression = new Expression(this.formula);	
		
		for(String nombreCuenta : this.cuentasName) {
			Cuenta cuenta = periodo.getCuentaxNombre(nombreCuenta);
			Argument arg = new Argument(nombreCuenta + "=" +cuenta.getValor());
			expression.addDefinitions(arg);
		}
		return expression.calculate();
	}

	public int getId() {
		return id;
	}

	public String getFormula() {
		return formula;
	}

	public String getName() {
		return name;
	}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getNombreCuentas(){
		return this.cuentasName;
	}
	
	public ArrayList<Indicador> getIndicadores() {
		return indicadores;
	}
	public boolean isMutable() {
		return mutable;
	}

}

