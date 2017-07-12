	package DAO;

	import java.util.ArrayList;

	import com.google.gson.reflect.TypeToken;

import Objects.Empresa;

	public class DAOEmpresa extends DAOGenerico{

		public DAOEmpresa(String path) {
			super(path,new TypeToken<ArrayList<Empresa>>(){}.getType());
		}
	}