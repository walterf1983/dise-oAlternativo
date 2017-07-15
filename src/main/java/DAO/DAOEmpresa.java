	package DAO;

	import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;

import Objects.Cuenta;
import Objects.Empresa;
import Objects.Indicador;
import Objects.Periodo;

	public class DAOEmpresa extends DAOGenerico{

		public DAOEmpresa(String path) {
			super(path,new TypeToken<ArrayList<Empresa>>(){}.getType());
		}

		private static int IDEmpresa;
		private static int IDCuenta;
		private static int IDPeriodo;
		
		
		
		private static void setIDEmpresa(int ID){
			IDEmpresa=ID;
		}

		private static void  setIDCuenta(int iDCuenta) {
			IDCuenta=iDCuenta;
		}

		private static void setIDPeriodo(int iDPeriodo) {
			IDPeriodo = iDPeriodo;
		}

		public static int getIDEmpresa() {
			return IDEmpresa;
		}

		public static int getIDCuenta() {
			return IDCuenta;
		}

		public static int getIDPeriodo() {
			return IDPeriodo;
		}

		public static void setupINDEX(String path){
			DAOEmpresa daoE=new DAOEmpresa(path);
			daoE.setupIndex();
		}

		private void setupIndexEmpresa(){
			DAOEmpresa.setIDEmpresa(0);
			ArrayList<Integer>ids=this.getAllEmpresas().
									stream().
									map(e->e.getId()).
									collect(Collectors.toCollection(ArrayList::new));
			for(int e:ids){
				if(e>DAOEmpresa.getIDEmpresa()){
					DAOEmpresa.setIDEmpresa(e);
				}
			}
		}

		private void setupIndexPeriodo(){
			DAOEmpresa.setIDPeriodo(0);
			ArrayList<Periodo>arrayPeriodos=new ArrayList<Periodo>();
			this.getAllEmpresas().forEach(e->arrayPeriodos.addAll(e.getPeriodos()));
		
			for(Periodo e:arrayPeriodos){
					if(e.getId()>DAOEmpresa.getIDPeriodo())
					DAOEmpresa.setIDPeriodo(e.getId());	
			}
		}

		private void setupIndexCuenta(){
			DAOEmpresa.setIDCuenta(0);
			for(Empresa e: this.getAllEmpresas())
				for(Periodo p:e.getPeriodos())
					for(Cuenta c:p.getCuentas()){
						if(c.getId()>DAOEmpresa.getIDCuenta())
							DAOEmpresa.setIDCuenta(c.getId());
				}
		}

		private void setupIndex(){
			this.setupIndexEmpresa();
			this.setupIndexPeriodo();
			this.setupIndexCuenta();
		}

		private boolean estaEmpresa(String nombreEmpresa){
			return this.getAllEmpresas().
						stream().
						filter(e->e.getName().equalsIgnoreCase(nombreEmpresa)).
						findFirst().
						isPresent();
		}

		private boolean estaPeriodo(int anio,String tipo){
			boolean esta=false;
			for(Empresa e:this.getAllEmpresas()){
				for(Periodo p:e.getPeriodos()){
					if(p.getTipo().equalsIgnoreCase(tipo)&&p.getAnio()==anio){
						esta=true;
						return esta;
					}
				}
			}
			return esta;
		}

		private boolean estaCuenta(String nombreCuenta){
			boolean esta=false;
			for(Empresa e:this.getAllEmpresas()){
				for(Periodo p:e.getPeriodos()){
					for(Cuenta c:p.getCuentas()){
						if(c.getName().equals(nombreCuenta)){
						return esta=true;
						}
					}
				}
			}
			return esta;
		}

		protected  Empresa buscarEmpresa(String empresa){
			return this.getAllEmpresas().
					stream().
					filter(e->e.getName().
					equalsIgnoreCase(empresa)).
					findFirst().
					get();
		
		}

		protected Periodo buscarPeriodo(int anio, String tipo){
			ArrayList<Periodo> periodos=new ArrayList<Periodo>();
			for(ArrayList<Periodo>p:this.getAllEmpresas().
					stream().
					map(e->e.getPeriodos()).
					collect(Collectors.toCollection(ArrayList::new))){
				periodos.addAll(p);
			}
			for(Periodo pe:periodos){
				if(pe.getAnio()==anio&&pe.getTipo().equalsIgnoreCase(tipo)){
					return pe;
				}
			}
			return null;
		}

		private Cuenta buscarCuenta(String nombreCuenta){
			for(Empresa e:this.getAllEmpresas()){
				for(Periodo p:e.getPeriodos()){
					for(Cuenta c:p.getCuentas()){
						if(c.getName().equals(nombreCuenta))
							return c;
				}
			}
		}
			return null;
		}

		public int buscarIDEmpresa(String nombreEmpresa) {
			if(!this.estaEmpresa(nombreEmpresa)){
				IDEmpresa=IDEmpresa+1;
				return IDEmpresa ;
			}
			return buscarEmpresa(nombreEmpresa).getId();	
		}

		public int buscarIDPeriodo(int anio,String tipo) {
			if(!this.estaPeriodo(anio,tipo)){
				IDPeriodo=IDPeriodo+1;
				return IDPeriodo ;
			}	
			return this.buscarPeriodo(anio,tipo).getId();	
		}
		
		public int buscarIDCuenta(String nombreCuenta) {
			if(!this.estaCuenta(nombreCuenta)){
				IDCuenta=IDCuenta+1;
				return IDCuenta ;
			}
				
			return this.buscarCuenta(nombreCuenta).getId();	
		}

		public ArrayList<Empresa> getAllEmpresas(){
			ArrayList<Empresa> empresas=new ArrayList<Empresa>();
			
			try {
				 empresas= this.getAllItems().
								stream().
								map(e->(Empresa)e).
								collect(Collectors.toCollection(ArrayList::new));
			} catch (Exception e) {
			}
		
			return empresas;
		}
		
		public int addCuenta(String nombreEmpresa, String anio, String tipo, String cuenta, String valor){
		
			int u=-1;
			Double value=Double.parseDouble(valor);
			ArrayList<Periodo>periodos=new ArrayList<Periodo>();
			ArrayList<Indicador>indicadores=new ArrayList<Indicador>();
			ArrayList<Cuenta>aCuentas=new ArrayList<Cuenta>();
			aCuentas.add(new Cuenta(cuenta,value,this));
			Empresa empresa;
			if(!this.estaEmpresa(nombreEmpresa)){
				periodos.add(new Periodo(Integer.parseInt(anio),tipo,aCuentas,indicadores,this));
			
					try {
						this.addItem(new Empresa(nombreEmpresa,periodos,this));
					} catch (Exception e) {
						e.printStackTrace();
					}
			
				return 1;
			}else{
				empresa=this.buscarEmpresa(nombreEmpresa);
				if(!empresa.estaPeriodo(Integer.parseInt(anio),tipo)){
					u=empresa.addCuenta(aCuentas.get(0), new Periodo(Integer.parseInt(anio),tipo,aCuentas,indicadores,this));
				}else{
				Periodo periodo=empresa.getPeriodo(Integer.parseInt(anio), tipo);
				u=empresa.addCuenta(aCuentas.get(0),periodo);
		
				if(u==2||u==3||u==0)
					try {
						this.updateItem(nombreEmpresa, empresa);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return u;
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
				if(empresa.equalsIgnoreCase(e.getName())){
					for(Periodo p:e.getPeriodos()){
						if(p.getAnio()==anio&&p.getTipo().equalsIgnoreCase(periodo)){
							cuentas=p.getCuentas();
						}
					}
				}
			}
			return cuentas;
		}
	}