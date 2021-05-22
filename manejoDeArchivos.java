import java.io.*; //File
import java.util.Scanner; //Scanner

public class manejoDeArchivos{
	public static void guardarJuego(int distanciaRecorrida, int i){
		try {
			File datos = new File("CONTINUES/con1.txt");
			FileWriter myWriter = new FileWriter("CONTINUES/con1.txt");
			
			myWriter.write(distanciaRecorrida + " " + i);
			myWriter.close();
			
		} catch (IOException e) {
		  System.out.println("Error");
		  e.printStackTrace();
		}
	}
	
	public static String buscarJuego(){
		String info = "";
		try {
			File cont1 = new File("CONTINUES/con1.txt");
			Scanner scan = new Scanner(cont1);
			
			if(cont1.length() > 0){
				while(scan.hasNextLine()){
					info += scan.nextLine();
				}
				scan.close();
			}
			
		} catch (FileNotFoundException e) {
		  System.out.println("No hay juego guardado");
		  e.printStackTrace();
		}
		
		return info;
	}
}