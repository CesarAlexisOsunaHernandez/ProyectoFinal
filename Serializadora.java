import java.io.*; //ObjectInputStream   ObjectOutputStream

public class Serializadora{
	private ObjectInputStream leer;
	private ObjectOutputStream escribir;
	
	public void escribirObjeto(Object obj, int file){
		try{
			escribir = new ObjectOutputStream(new FileOutputStream("CONTINUES/espacio"+file+".dat"));
			escribir.writeObject(obj);
		}
		catch(Exception e){
			System.out.println("Error");
		}
		
	}
	
	public Object leerObjeto(int file){
		Object obj = null;
		try{
			leer = new ObjectInputStream(new FileInputStream("CONTINUES/espacio"+file+".dat"));
			obj = leer.readObject();
		}
		catch(Exception e){
			System.out.println("Error");
		}
		return obj;
	}
}