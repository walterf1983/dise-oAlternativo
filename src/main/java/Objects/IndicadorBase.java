package Objects;

import java.util.ArrayList;
	

@SuppressWarnings("serial")
public class IndicadorBase extends IndicadorBasePredefinido{

	public IndicadorBase(String formula, String name, ArrayList<String> nombreCuentas) {
		
		super(formula, name, nombreCuentas,new ArrayList<Indicador>());
		this.mutable=this.isMutable();	
	}

	public boolean isMutable(){
		return true;
	}
}