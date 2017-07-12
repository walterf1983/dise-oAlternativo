package DAO;

import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import Objects.Indicador;

public class DAOIndicador extends DAOGenerico{

	public DAOIndicador(String path) {
		super(path,new TypeToken<ArrayList<Indicador>>(){}.getType());
	}
}