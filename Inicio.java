import java.io.*;
import java.awt.Color;
import javax.swing.*; //Interfaz grafica
import java.awt.event.*; //Boton
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.*; //Interfaz grafica
import java.awt.event.*; //Boton ComboBox
import javax.swing.event.*; //Checkbox
import java.awt.*; //Color

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Inicio extends JFrame implements ActionListener, ItemListener{
	private JLabel titulo;
	private JButton nuevoJuego, continuarJuego, salir, idioma;
	private JComboBox cb;
	public static String info = "";
	static boolean continuar = false;
	
	public Inicio(){
		setLayout(null);
		getContentPane().setBackground(new Color(50,155,250));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		info = "";
		
		titulo = new JLabel("Heroe Matematico: Primaria");
		titulo.setBounds(60,10,300,30);
		add(titulo);
		
		nuevoJuego = new JButton("Nuevo juego");
		nuevoJuego.setBounds(85,100,110,30); //setBounds(x,y,width,height)
		add(nuevoJuego);
		nuevoJuego.addActionListener(this);
		
		continuarJuego = new JButton("Continuar");
		continuarJuego.setBounds(85,150,110,30); //setBounds(x,y,width,height)
		add(continuarJuego);
		continuarJuego.addActionListener(this);
		buscarContinue();
		
		/*
		idioma = new JButton("Idioma");
		idioma.setBounds(85,200,110,30); //setBounds(x,y,width,height)
		//idioma.setFont(new Font("Andale Mono", 1, 30));
		add(idioma);
		idioma.addActionListener(this);
		
		cb = new JComboBox();
		cb.setBounds(10,10,80,20);
		cb.setVisible(false);
		add(cb);
		
		cb.addItem("Espa\u00f1ol");
		cb.addItem("English");
		cb.addItem("Francais");
		cb.addItem("\u0639\u0631\u0628");
		cb.addItemListener(this);
		*/
		
		
		salir = new JButton("Cerrar");
		salir.setBounds(85,200,110,30); //setBounds(x,y,width,height)
		add(salir);
		salir.addActionListener(this);
		
		
				
		
	}
	
	public void actionPerformed(ActionEvent c){
		if(c.getSource() == nuevoJuego){
			ElijePersonaje n2 = new ElijePersonaje();
			n2.setBounds(0,0,300,300);
			n2.setVisible(true);
			n2.setResizable(false);
			n2.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(c.getSource() == continuarJuego){
			this.continuar = true;
			Mundo n3 = new Mundo();
			n3.setBounds(0,0,500,450);
			n3.setVisible(true);
			n3.setResizable(false);
			n3.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(c.getSource() == idioma){
			titulo.setVisible(false);
			nuevoJuego.setVisible(false);
			continuarJuego.setVisible(false);
			salir.setVisible(false);
			idioma.setVisible(false);
			cb.setVisible(true);
			
		}
		else if(c.getSource() == salir){
			System.exit(0);
		}
	}
	
	public void buscarContinue(){
		try {
			File cont1 = new File("CONTINUES/con1.txt");
			Scanner scan = new Scanner(cont1);
			
			if(cont1.length() > 0){
				while(scan.hasNextLine()){
					info += scan.nextLine();
				}
				scan.close();
			}
			else{
				continuarJuego.setEnabled(false);
			}
		} catch (FileNotFoundException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	
	public void itemStateChanged(ItemEvent c){
		String aux;
		if(c.getSource() == cb){
			aux = cb.getSelectedItem().toString();
			if(aux == "Espa\u00f1ol"){
				titulo.setText("Heroe Matematico: Primaria");
				nuevoJuego.setText("Nuevo juego");
				continuarJuego.setText("Continuar");
				salir.setText("Salir");
				idioma.setText("Idioma");
			}
			else if(aux == "English"){
				titulo.setText("Math Hero: Elementary");
				nuevoJuego.setText("New game");
				continuarJuego.setText("Continue");
				salir.setText("Exit");
				idioma.setText("Language");
			}
			else if(aux == "\u0639\u0631\u0628"){
				
				titulo.setText("\u0627\u0644\u0631\u062C\u0644 \u0627\u0644\u062E\u0627\u0631\u0642 \u0627\u0644\u0634\u062E\u0635 \u0627\u0644\u0630\u0643\u064A: ");
				
				nuevoJuego.setText("\u0627\u0644\u0644\u0645\u0628 \u0628\u0627\u0627\u064A \u0633\u062A\u064A\u0634\u0646");
				continuarJuego.setText("\u0627\u0643\u0645\u0627\u0644 \u0627\u0644\u0644\u0639\u0628\u0629");
				idioma.setText("\u0627\u0644\u0644\u063A\u0629");
				salir.setText("\u0627\u0644\u062E\u0631\u0648\u062C \u0645\u0646 \u0627\u0644\u0644\u0639\u0628\u0629");
			}
			
			titulo.setVisible(true);
			nuevoJuego.setVisible(true);
			continuarJuego.setVisible(true);
			salir.setVisible(true);
			idioma.setVisible(true);
			cb.setVisible(false);
		}
	}
	
	public static void main(String args[]){
		Inicio n1 = new Inicio();
		n1.setBounds(0,0,300,330);
		n1.setVisible(true);
		n1.setLocationRelativeTo(null);
		
		
		com.sun.javafx.application.PlatformImpl.startup(()->{});
			
		//final String NOMBRE_ARCHIVO = "MUSICA/1.mp3";
		File archivo = new File("MUSICA/1.mp3");
				
		Media audio = new Media(archivo.toURI().toString());
		MediaPlayer reproductor = new MediaPlayer(audio);
		
		int i=0;
		while (i<20000*20){ //20,000 = 1s
			reproductor.play();
			i++;
			//System.out.println(i);
		}
	}
}