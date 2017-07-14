package DAO;


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import Objects.Item;

	public abstract class DAOGenerico implements DAOInterfaz {

		protected String path;
		protected Gson myGson;
		protected Type type;
		
		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}


		public DAOGenerico(String path,Type type) {
			super();
			this.path = path;
			this.myGson =new Gson();
			this.type=type;
		}
		

		public String fileToString() throws Exception{
			FileReader reader=new FileReader(this.path);
			BufferedReader buffer=new BufferedReader(reader);
			String line;
			String stringSerialized="";
			while((line=buffer.readLine())!=null){
				stringSerialized=stringSerialized+line;
			}
			
			buffer.close();
			reader.close();
			return stringSerialized;
		}
		
		public void fromStringToFile(String stringSerialized) throws Exception{
			FileWriter file=new FileWriter(this.path);
			BufferedWriter buffer = new BufferedWriter(file);
			buffer.write(stringSerialized);
			buffer.close();
			file.close();
		}
		
		public int addItem(Item item) throws Exception{
			ArrayList<Item>items=this.getAllItems();
			items.add(item);
			this.fromStringToFile(this.myGson.toJson(items));
			return 0;
		}
		
		public ArrayList<Item> getAllItems() throws Exception{
			return this.myGson.fromJson(this.fileToString(), this.type);
		
		}
		
		public boolean deleteItem(String name) throws Exception{
			ArrayList<? extends Item>items=getAllItems();
			int sizeFile=items.size();
			List <? extends Item> itemsResult = items
					.stream()
					.filter(element -> !(element.getName().equals(name)))
					.collect(Collectors.toList());
			this.fromStringToFile(this.myGson.toJson(itemsResult));
			
			return sizeFile!=itemsResult.size();
		}

		public boolean updateItem(String name,Item itemNew) throws Exception{
			if(deleteItem(name)){
				this.addItem(itemNew);
				return true;
			}
			return false;
		}
		
	}
