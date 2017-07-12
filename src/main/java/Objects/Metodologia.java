package Objects;


/**
 * @author Walt
 * @version 1.0
 * @updated 02-may.-2017 10:36:01
 */
/*public class Metodologia {

	public Indcador m_Indcador;
	public Condicion m_Condicion;

	public Metodologia(){

	}

	public void finalize() throws Throwable {

	}
}//end Metodologia/* import java.util.ArrayList;

public class Metodologia {
	public int id;
	public String nombre;
	public String formula;
	
	private ArrayList<Indicador> indicadores;
	
	public ArrayList<Indicador> indicadores() {
		this.getIndicadores();
		return this.indicadores;
	}
	
	public void addIndicador(Indicador indicador) {
		DAOMetodologiaIndicador.add(this, indicador);
		this.getIndicadores();
		this.indicadores.add(indicador);
	}
	
	public void deleteIndicador(Indicador indicador) {
		DAOMetodologiaIndicador.delete(this, indicador);
		this.getIndicadores();
		this.indicadores.remove(this.indicadores.indexOf(indicador));
	}
	
	
	private void getIndicadores(){
		if (this.indicadores == null){
			this.indicadores = DAOMetodologiaIndicador.getxMetodologia(this.id);
		}
	}
}
*/