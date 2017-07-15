package Objects;

import java.util.ArrayList;

import DAO.DAOIndicador;


@SuppressWarnings("serial")
public class IndicadorBasePredefinido extends Indicador {
	


	public IndicadorBasePredefinido(String formula, String name, ArrayList<String> nombreCuentas, ArrayList<Indicador>indicadores,DAOIndicador dao) {
		super(formula, name, nombreCuentas,indicadores,dao);
		this.mutable=this.isMutable();
	}

	@Override
	void add(Indicador i) {
		System.out.println("No se puede agregar un hijo a una hoja");
		
	}

	@Override
	void delete(Indicador i) {
		System.out.println("No se puede agregar un hijo a una hoja");
	}
	
	@Override
	public void addCuenta(String cuenta) {
		System.out.println("No se puede agregar a un predefinido");
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public void setFormula(String formula) {
		System.out.println("no se puede");
	}
	
	@Override
	public void setName(String name) {
		System.out.println("no se puede");
	}
	
}
