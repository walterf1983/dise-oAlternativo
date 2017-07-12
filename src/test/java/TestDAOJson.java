import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Objects.Cuenta;
import Objects.Empresa;
import Objects.Periodo;
import Repositorios.RepositorioDeEmpresas;

public class TestDAOJson {
	private ArrayList<Empresa> empresas;
	private RepositorioDeEmpresas repositorioEmpresas;
	

	@Before
	public void init(){
		this.repositorioEmpresas = new RepositorioDeEmpresas();
	}
	
	@Test
	public void nombreDePrimerasDosEmpresasCorrectos(){
		try {
			this.empresas = repositorioEmpresas.getAllEmpresas();
			Assert.assertEquals("Facebook", empresas.get(0).getNombreEmpresa());
			Assert.assertEquals("Twitter", empresas.get(1).getNombreEmpresa());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		@Test 
		 public void agregoEmpresaAJsonYComprueboSiEsta() throws Exception{
		  Empresa empresa1 = this.empresaDePrueba(this.repositorioEmpresas);
		  try {
		    this.repositorioEmpresas.add(empresa1);
		    this.empresas= repositorioEmpresas.getAllEmpresas();
		    Assert.assertEquals("hola",empresas.get(empresas.size()-1).getNombreEmpresa());
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		 @Test 
		 public void eliminoEmpresaAJsonYComprueboSiEsta() throws Exception{
		  Empresa empresa2 = this.empresaDePrueba2(this.repositorioEmpresas); 
		  try { this.repositorioEmpresas.add(empresa2);
		    this.repositorioEmpresas.delete(empresa2);
		    this.empresas= repositorioEmpresas.getAllEmpresas();
		    Assert.assertFalse(empresas.contains(empresa2));
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		 @Test 
		 public void actualizoEmpresaYchequeoQueNoSeaLaMismaLista() throws Exception{
		  Empresa empresaUpdate = this.empresaDePruebaUpdate(this.repositorioEmpresas);
		  try {
		   this.repositorioEmpresas.update(empresaUpdate);
		   this.empresas= repositorioEmpresas.getAllEmpresas();
		   Assert.assertNotEquals(empresaUpdate.getNombreEmpresa(),"jason");
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();}
		 }
		 
		 
		  private static Empresa empresaDePrueba(RepositorioDeEmpresas repositorioEmpresas) throws Exception{
		  
		  ArrayList <Periodo> periodos  = new ArrayList<Periodo>();
		  ArrayList <Cuenta> cuentas = new ArrayList<Cuenta>();
		  cuentas.add(new Cuenta (7,"FRB",32));
		  cuentas.add(new Cuenta (8,"FRJ",44));
		  periodos.add(new Periodo(6,2014,"A"));
		  Empresa empresa1 = new Empresa(repositorioEmpresas.getNextId() ,"hola"); 
		  return empresa1;
		}
		 private static Empresa empresaDePruebaUpdate(RepositorioDeEmpresas repositorioEmpresas) throws Exception{
		  
		  ArrayList <Periodo> periodos  = new ArrayList<Periodo>();
		  ArrayList <Cuenta> cuentas = new ArrayList<Cuenta>();
		  cuentas.add(new Cuenta (7,"ACTUALIZADO",32));
		  cuentas.add(new Cuenta (8,"ACTUALIZADO",44));
		  periodos.add(new Periodo(6,2014,"A"));
		  Empresa empresa1 = new Empresa(repositorioEmpresas.getNextId(),"ACTUALIZADO"); 
		  return empresa1;}
		 private static Empresa empresaDePrueba2(RepositorioDeEmpresas repositorioEmpresas) throws Exception{
		  
		  ArrayList <Periodo> periodos  = new ArrayList<Periodo>();
		  ArrayList <Cuenta> cuentas = new ArrayList<Cuenta>();
		  cuentas.add(new Cuenta (9,"FRB",22));
		  cuentas.add(new Cuenta (11,"FRJ",12));
		  periodos.add(new Periodo(6,2014,"1S"));
		  Empresa empresa2 = new Empresa(repositorioEmpresas.getNextId(),"pepe"); 
		  return empresa2;
		} 
		 }

