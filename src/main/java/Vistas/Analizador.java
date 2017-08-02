package Vistas;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.DAOEmpresa;
import DAO.DAOIndicador;
import Objects.Cuenta;

public class Analizador {
   
	public static ArrayList<Cuenta>cuentasGlobales=new ArrayList<Cuenta>();
	public static ArrayList<Cuenta>indicadoresGlobales=new ArrayList<Cuenta>();
    public static int count;	
    @SuppressWarnings("unused")
	private static ServerSocket SERVER_SOCKET;
	
    public static void main(String[] args) {
	
		
		try {//PARA UE NO HAYA MAS DE UNA APLICACION ABIERTA
			SERVER_SOCKET=new ServerSocket(1334);
		} catch (IOException x) {
			JOptionPane.showMessageDialog(null,"Otra instancia de la aplicación\n está ejecutandose.","AVISO",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			}
		
		PantallaAgregarCuenta.setCountInstance(0);
		DAOEmpresa.setupINDEX("src//main//java//DB//Empresas.json");
		//DAOIndicador.setupINDEX("src//main//java//DB//Empresas.json");
		
		VentanaPrincipal ventanaP = new VentanaPrincipal();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		
		ventanaP.dispose();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		VentanaPrincipal1 ventanaW = new VentanaPrincipal1();
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
		//	e.printStackTrace();
		}
		
		ventanaW.dispose();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		//	e.printStackTrace();
		}
		
		@SuppressWarnings("unused")
		VentanaOpcion opcion = new VentanaOpcion(cuentasGlobales);
		
	}

}
