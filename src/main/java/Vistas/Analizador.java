package Vistas;

import java.util.ArrayList;

import DAO.DAOEmpresa;
import Objects.Cuenta;
import Repositorios.RepositorioDeEmpresas;

public class Analizador {
   
	public static ArrayList<Cuenta>cuentasGlobales=new ArrayList<Cuenta>();
     
	public static void main(String[] args) {
		
		PantallaAgregarCuenta.setCountInstance(0);
		DAOEmpresa.setupINDEX("Empresas.json");
		System.out.println(DAOEmpresa.getIDEmpresa()+" "+DAOEmpresa.getIDPeriodo()+" "+DAOEmpresa.getIDCuenta());
		
		VentanaPrincipal ventanaP = new VentanaPrincipal();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ventanaP.dispose();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		VentanaPrincipal1 ventanaW = new VentanaPrincipal1();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ventanaW.dispose();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("unused")
		VentanaOpcion opcion = new VentanaOpcion(cuentasGlobales);
	}

}
