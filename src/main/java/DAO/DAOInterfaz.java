package DAO;
import java.util.ArrayList;

import Objects.Item;

public interface DAOInterfaz {

	public boolean addItem(Item item) throws Exception;
	public boolean deleteItem(String name)throws Exception;
	public ArrayList<Item> getAllItems() throws Exception;
	public boolean updateItem(String name,Item itemNew) throws Exception;
}