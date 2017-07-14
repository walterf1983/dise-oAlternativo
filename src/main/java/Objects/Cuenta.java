package Objects;

import DAO.DAOEmpresa;

public class Cuenta {
		
		private int idCuenta;
		private String nombre;
		private double valor;
		
		public Cuenta(String nombre, double valor,DAOEmpresa dao) {
			super();
			this.idCuenta =dao.buscarIDCuenta(nombre);
			this.nombre = nombre;
			this.valor = valor;
		}
		

		public int getId() {
			return idCuenta;
		}


		public void setId(int idCuenta) {
			this.idCuenta = idCuenta;
		}


		public String getName() {
			return nombre;
		}
		
		public double getValor() {
			return valor;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}
		public void actualizate(int idCuenta, String nombre, double valor){
			this.setId(idCuenta);
			this.setNombre(nombre);
			this.setValor(valor);
		}


		public boolean esCuenta(Cuenta cuenta) {
			return this.getName().equalsIgnoreCase(cuenta.getName());
		}


	}


