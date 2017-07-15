package Objects;

import java.util.ArrayList;

import DAO.DAOIndicador;



	
	@SuppressWarnings("serial")
	public class IndicadorCompuesto extends IndicadorCompuestoPredefinido{
		
	
	public IndicadorCompuesto(String formula, String name, ArrayList<String> nombreCuentas, ArrayList<Indicador> indicadores,DAOIndicador dao) {
			super(formula, name, nombreCuentas, indicadores,dao);
			this.mutable=this.isMutable();
		}
	
	public boolean isMutable(){
		return true;
	}
}