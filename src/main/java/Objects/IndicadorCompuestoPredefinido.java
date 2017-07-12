package Objects;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class IndicadorCompuestoPredefinido extends Indicador {



	public IndicadorCompuestoPredefinido(String formula, String name, ArrayList<String> nombreCuentas,ArrayList<Indicador> indicadores) {
		super(formula, name, nombreCuentas, indicadores);
		this.mutable=this.isMutable();
		
	}

	public Indicador getIndicador(int i){
		return this.indicadores.get(i);
	}

	@Override
	void add(Indicador i) {
		System.out.println("No puede hacerlo");
		
	}

	@Override
	void delete(Indicador i) {
		System.out.println("No puede hacerlo");
		
	}
	
	@Override
	public void setFormula(String formula) {
		System.out.println("no se puede");
	}
	
	@Override
	public void setName(String name) {
		System.out.println("no se puede");
	}

	@Override
	public boolean isMutable() {
		return false;
	}
	
}
