package Objects;

import java.util.ArrayList;

import DAO.DAOIndicador;
	

@SuppressWarnings("serial")
public class IndicadorBase extends IndicadorBasePredefinido{

	public IndicadorBase(String formula, String name, ArrayList<String> nombreCuentas,DAOIndicador dao) {
		
		super(formula, name, nombreCuentas,new ArrayList<Indicador>(),dao);
		this.mutable=this.isMutable();	
	}

	public boolean isMutable(){
		return true;
	}
}