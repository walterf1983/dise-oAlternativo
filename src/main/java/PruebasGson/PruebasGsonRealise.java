package PruebasGson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.google.gson.*;


import Objects.Empresa;
import Objects.Indicador;
import Objects.IndicadorBase;
import Objects.IndicadorCompuestoPredefinido;
import Repositorios.RepositorioDeEmpresas;
import Repositorios.RepositorioDeIndicadores;

	enum Colores{
		
		AMARILLO(100),ROJO(010),AZUL(001);
		private Colores(int rgb){
			this.rgb=rgb;
		}
		private int rgb;
	}
public class PruebasGsonRealise {
	
	static public String prueba;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		RepositorioDeEmpresas repositorioEmpresas=new RepositorioDeEmpresas("EmpresasPosta.json");
		Gson gson=new Gson();
		
		
		Empresa empresa=gson.fromJson(
		"{\"id\":1,\"nombreEmpresa\": \"Tar\",\"periodos\": [{\"idPeriodo\": 1,"+
	"\"anio\": 2016,\"tipo\": \"A\",\"cuentas\":[{\"idCuenta\": 1,\"nombre\": \"FDS\","+
	"\"valor\": 34.4},{\"idCuenta\": 2,\"nombre\": \"EBITDA\",\"valor\": 67.8}]},{\"idPeriodo\": 2,"+
	"\"anio\": 2015,\"tipo\": \"1S\",\"cuentas\":[{\"idCuenta\": 1,\"nombre\": \"FDS\",\"valor\": 32.4"+
	"},{\"idCuenta\": 2,\"nombre\": \"EBITDA\",\"valor\": 61.8}]}]}", Empresa.class);
	
		
	ArrayList<String> cuentasBase1=new ArrayList<String>();
	cuentasBase1.add("eBITDA");
	cuentasBase1.add("saldo");
	cuentasBase1.add("cRA");
	cuentasBase1.add("ar");
	cuentasBase1.add("f");
	IndicadorBase indicadorBase=new IndicadorBase("SALDO", "IndicadorBase1", cuentasBase1);
	cuentasBase1.sort(null);
	cuentasBase1.forEach(e->System.out.println(e));
	
	RepositorioDeIndicadores repo=new RepositorioDeIndicadores("IndicadoresPosta.json");
	
	ArrayList<String> cuentasBase2=new ArrayList<String>();
	cuentasBase2.add("neto");
	ArrayList<Indicador> indicadores1=new ArrayList<Indicador>();
	indicadores1.add(indicadorBase);
	IndicadorCompuestoPredefinido indicador2=new IndicadorCompuestoPredefinido("EBITDA+neto", "indicador2", cuentasBase2, indicadores1);
	ArrayList<Indicador>indicadores=repo.getAllIndicadores();
	}		
}

	
