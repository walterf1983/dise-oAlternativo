package Objects;

import java.util.ArrayList;

import DAO.DAOIndicador;

@SuppressWarnings("serial")
public class Indicador extends  IndicadorAbs{

	public Indicador(String formula, String name, ArrayList<String> nombreCuentas, ArrayList<Indicador> indicadores,DAOIndicador dao) {
		super(formula, name, nombreCuentas, indicadores,dao);
	}

	public boolean estaEn(ArrayList<Indicador> indicadores) {
		return 	indicadores.
				stream().
				filter(e->this.igualA(e)).
				findFirst().
				isPresent();
	}

	private boolean igualA(Indicador e) {
		return formula.equals(e.getFormula());
	}
	
}