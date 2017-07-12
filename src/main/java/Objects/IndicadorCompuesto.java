package Objects;

import java.util.ArrayList;



	
	@SuppressWarnings("serial")
	public class IndicadorCompuesto extends IndicadorCompuestoPredefinido{
		
	
	public IndicadorCompuesto(String formula, String name, ArrayList<String> nombreCuentas, ArrayList<Indicador> indicadores) {
			super(formula, name, nombreCuentas, indicadores);
			this.mutable=this.isMutable();
		}
	
	public boolean isMutable(){
		return true;
	}
}