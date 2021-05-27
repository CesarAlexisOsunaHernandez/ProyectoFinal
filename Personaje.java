import java.io.*; //Serializable

public class Personaje implements Serializable{
	private int distancia, jefe;
	private String genero, idioma;
	private boolean castillo;
	
	public Personaje(){
		distancia = 0;
		jefe = 0;
		genero = "";
		castillo = false;
	}
	
	public void setDistancia(int distancia){
		this.distancia = distancia;
	}
	
	public void setJefe(int jefe){
		this.jefe = jefe;
	}
	
	public void setGenero(String genero){
		this.genero = genero;
	}
	
	public void setCastillo(boolean castillo){
		this.castillo = castillo;
	}
	
	public int getDistancia(){
		return distancia;
	}
	
	public int getJefe(){
		return jefe;
	}
	
	public String getGenero(){
		return genero;
	}
	
	public boolean getCastillo(){
		return castillo;
	}
}