package Repositorios;

import java.util.ArrayList;
import java.util.stream.Collectors;

import DAO.DAOIndicador;
import Objects.Indicador;


	
	public class RepositorioDeIndicadores extends RepositorioGenerico {
		
		public RepositorioDeIndicadores(String path) {
			super(new DAOIndicador(path));
		}
		
		public ArrayList<Indicador> getAllIndicadores() throws Exception{
			return  (ArrayList<Indicador>) this.getAll()
			.stream()
			.map(item->(Indicador)item)
			.collect(Collectors.toList());
		}
		
		public Indicador getxNombreIndicador(String nombre) throws Exception {
			return (Indicador)this.getxNombre(nombre);
		}
		
	}







