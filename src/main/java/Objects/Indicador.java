package Objects;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Indicador extends  IndicadorAbs{

	public Indicador(String formula, String name, ArrayList<String> nombreCuentas, ArrayList<Indicador> indicadores) {
		super(formula, name, nombreCuentas, indicadores);
	}
	
}