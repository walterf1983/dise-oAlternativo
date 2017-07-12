package Vistas;

import java.util.ArrayList;

import Objects.Cuenta;

public class Analizador {
   
	public static ArrayList<Cuenta>cuentasGlobales=new ArrayList<Cuenta>();
     
	public static void main(String[] args) {
		
		PantallaAgregarCuenta.setCountInstance(0);
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
		
		VentanaWBuilder ventanaW = new VentanaWBuilder();
		
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
