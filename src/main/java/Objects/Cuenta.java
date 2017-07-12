package Objects;

	public class Cuenta {
		private int idCuenta;
		private String nombre;
		private double valor;
		
		public Cuenta(int idCuenta, String nombre, double valor) {
			super();
			this.idCuenta = idCuenta;
			this.nombre = nombre;
			this.valor = valor;
		}
		

		public int getIdCuenta() {
			return idCuenta;
		}


		public void setIdCuenta(int idCuenta) {
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
			this.setIdCuenta(idCuenta);
			this.setNombre(nombre);
			this.setValor(valor);
		}


		public boolean esCuenta(Cuenta cuenta) {
			return this.getName().equalsIgnoreCase(cuenta.getName());
		}
	}


