package Repositorios;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import DAO.DAOEmpresa;
import Objects.Cuenta;
import Objects.Empresa;
import Objects.Periodo;

public class RepositorioDeEmpresas extends RepositorioGenerico {

	public RepositorioDeEmpresas(String path) {
		super(new DAOEmpresa(path));
	}
	
	public ArrayList<Empresa> getAllEmpresas() {
		ArrayList<Empresa> empresas=new ArrayList<Empresa>();
		try {
			empresas =  (ArrayList<Empresa>) this.getAll()
			.stream()
			.map(item->(Empresa)item)
			.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresas;
	}
	
	public Empresa getxNombreEmpresa(String nombre) throws Exception {
		return (Empresa)this.getxNombre(nombre);
	}
	
	public ArrayList<Integer> getAniosPorEmpresa(String empresa){
		
		ArrayList<Integer> anios=new ArrayList<Integer>();
		
		
			for(Empresa e:this.getAllEmpresas()){
				if (e.getName().equals(empresa)){
					for(Periodo p:e.getPeriodos()){
						if(!anios.contains(p.getAnio()))
							anios.add(p.getAnio());
						}
				}	
			}
		return anios;
	}
	
	public ArrayList<String> getTipoPeriodoPorEmpresa(String empresa,int anio){
		ArrayList<String> tipos=new ArrayList<String>();
			for(Empresa e:this.getAllEmpresas()){
				if (e.getName().equals(empresa)){
					for(Periodo p:e.getPeriodos()){
						if(tipos.indexOf(p.getTipo())==-1)
							if(p.getAnio()==anio){
								tipos.add(p.getTipo());
							}
						}
				}	
			}
		return tipos;
	}
	
	public ArrayList<Cuenta>getCuentasPor(String empresa, int anio, String periodo){
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		for (Empresa e:this.getAllEmpresas()){
			if(empresa.equals(e.getName())){
				for(Periodo p:e.getPeriodos()){
					if(p.getAnio()==anio&&p.getTipo().equals(periodo)){
						cuentas=p.getCuentas();
					}
				}
			}
		}
		return cuentas;
	}

	//falta recauchutarlo y agregar una cuenta ue esta repetida
	public int addCuenta(String nombreEmpresa, String anio, String tipo, String cuenta, String valor)throws Exception {
		
		Double value=Double.parseDouble(valor);
		Empresa empresa = null;	
		ArrayList<Periodo>periodos=new ArrayList<Periodo>();
		ArrayList<Cuenta> aCuentas=new ArrayList<Cuenta>();
		aCuentas.add(new Cuenta(5,cuenta,value));
				
		int u=0;
		try{	
		empresa=this.getxNombreEmpresa(nombreEmpresa);
		System.out.println("estaba la empresa");
		}catch(Exception e){
			periodos.add(new Periodo(0, Integer.parseInt(anio),tipo,aCuentas));
			this.add(new Empresa(this.getLastId(),nombreEmpresa,periodos));
		}
		periodos.addAll(empresa.getPeriodos());
		
		//por id


		boolean a=(periodos.stream().
						filter(e->e.getAnio()==Integer.parseInt(anio) && e.getTipo().equals(tipo)).
						findFirst().isPresent());

		if(!a){
			periodos.add(new Periodo(6,Integer.parseInt(anio),tipo,aCuentas));
			this.update(nombreEmpresa, new Empresa(this.getLastId(),nombreEmpresa,periodos));
		}else{
			Periodo periodo=periodos.stream().
					filter(e->e.getAnio()==Integer.parseInt(anio) && e.getTipo().equals(tipo)).
					findFirst().get();
			boolean c= periodo.getCuentas().stream().
					filter(e->e.getName().equalsIgnoreCase(cuenta)).
					findFirst().isPresent();
			if(!c){
				periodo.addCuenta(aCuentas.get(0));
				
				ArrayList<Periodo> periodos1=periodos.
						stream().
						filter(e->!e.getTipo().equals(tipo)).
						collect(Collectors.toCollection(ArrayList::new));
				System.out.println(periodos1.size());
				periodos1.add(periodo);
				this.update(nombreEmpresa, new Empresa(this.getLastId(),nombreEmpresa,periodos1));
				
			}else{
				int input = JOptionPane.showConfirmDialog(null, "La cuenta se encuentra para ese periodo\n"
			 											+ "¿Deseas agregarla de todos modos?", "Elija una opción",
			 											JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				u=input;
				if(input==0){
					aCuentas.addAll(periodo.getCuentas().stream().
							filter(e->!e.getName().equalsIgnoreCase(cuenta)).
							collect(Collectors.toCollection(ArrayList::new)));
					periodo.setCuentas(aCuentas);
					ArrayList<Periodo> periodos2=periodos.
												stream().
												filter(e->!(e.getAnio()==Integer.parseInt(anio) && e.getTipo().equals(tipo))).
												collect(Collectors.toCollection(ArrayList::new));
					periodos2.add(periodo);
					this.update(nombreEmpresa, new Empresa(this.getLastId(), nombreEmpresa, periodos2));	
				}else{
				}
			}
		}
		return u;
		}
	
}