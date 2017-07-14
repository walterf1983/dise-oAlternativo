package Repositorios;

import java.util.ArrayList;
import java.util.stream.Collectors;
import DAO.DAOInterfaz;
import Objects.Item;

abstract public class RepositorioGenerico {
	
	protected DAOInterfaz dao; 

	public RepositorioGenerico(DAOInterfaz dao) {
		super();
		this.dao = dao;
	}
	
	public int add(Item item) throws Exception{
		return this.dao.addItem(item);
		}
	
	public DAOInterfaz getDao() {
		return dao;
	}

	public boolean delete(String item) throws Exception{
		return this.dao.deleteItem(item);
	}

	public ArrayList<? extends Item> getAll() throws Exception{
		return this.dao.getAllItems();
	}
	
	public Item getxNombre(String nombre) throws Exception{
		 return this.dao.getAllItems().stream()
				 					.filter(i -> i.getName().equals(nombre))
				 					.collect(Collectors.toList())
				 					.get(0);
	}

	
	public boolean update(String itemOld,Item itemNew) throws Exception{
		return this.dao.updateItem(itemOld,itemNew);
	}
	
	public int getLastId() throws Exception{
		return this.getAll().size() + 1; 
	}	
}
