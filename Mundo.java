import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Mundo extends JFrame implements KeyListener, ActionListener{
	private JLabel player, floor1, floor2, torre, banderas[] = new JLabel[8], mensaje, castillo, hechizeros[] = new JLabel[8];
	private ImageIcon PlayerL, PlayerR, Grass, Tower, Castle;
	static ElijePersonaje EP = new ElijePersonaje();
	private JMenuBar mb1;
	private JMenu m1, guardar;
	private JMenuItem slot1, slot2, slot3;
	int i = 0, x1 = 0, x2 = 1408, distanciaRecorrida = 0;
	static boolean N = true, P = false, cambio = true;
	private File aux = new File("IMAGENES/Banderas.png");
	public Inicio ini = new Inicio();
	
	public Mundo(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,450);
		this.setLayout(null);
		this.addKeyListener(this);
		getContentPane().setBackground(new Color(0,155,255));
		
		/*
		for(int i = 0; i < 8; i++){
			Flags[i] = new ImageIcon("IMAGENES/Bandera"+(i+12)+".png");
			banderas[i] = new JLabel(Flags[i]);
			banderas[i].setBounds((i+1) * 1850, 50, 150, 66);
			this.add(banderas[i]);
		}*/
		
		
		//banderas[0] = new JLabel(Sprites.obtenerImagen(aux, 1, 150, 69));
		banderas[0] = new JLabel(new ImageIcon("IMAGENES/Bandera1.png"));
		banderas[0].setBounds(1850, 50, 150, 66);
		banderas[0].setLayout(null);
		this.add(banderas[0]);
		
		mensaje = new JLabel("     Entrar");
		mensaje.setBounds(2100, 170, 65, 25);
		mensaje.setOpaque(true);
		mensaje.setVisible(false);
		this.add(mensaje);
		
		Tower = new ImageIcon("IMAGENES/Torre2.png");
		torre = new JLabel(Tower);
		if(ini.info.length() != 0 && ini.continuar == true){
			this.distanciaRecorrida = Integer.valueOf(Strings.separarString(ini.info, 1));
			System.out.println(distanciaRecorrida);
			this.i = Integer.valueOf(Strings.separarString(ini.info, 2));
			torre.setBounds(2000 - distanciaRecorrida, -110, 310, 420);
		}
		else{
			torre.setBounds(2000, -110, 310, 420);
		}
		
		
		
		Grass = new ImageIcon("IMAGENES/Grass2.png");
		
		if(EP.N == true){
			N = true;
			PlayerL = new ImageIcon("IMAGENES/NessL1.png");
			PlayerR = new ImageIcon("IMAGENES/NessR1.png");
		}else if(EP.P == true){
			P = true;
			PlayerL = new ImageIcon("IMAGENES/PaulaL1.png");
			PlayerR = new ImageIcon("IMAGENES/PaulaR1.png");
		}else{
			System.out.println("Ambos falsos");
		}
		
		player = new JLabel(PlayerL);
		player.setBounds(182, 200, 68, 108);
		this.add(player);
		
		floor1 = new JLabel(Grass);
		floor1.setBounds(x1, 275, x2, 151);
		this.add(floor1);
		
		this.add(torre);
		
		floor2 = new JLabel(Grass);
		floor2.setBounds(x1 + x2, 275, x2, 151);
		this.add(floor2);
		
		mb1 = new JMenuBar();
		setJMenuBar(mb1);
		
		m1 = new JMenu("Opciones");
		mb1.add(m1);
		
		guardar = new JMenu("Guardar");
		m1.add(guardar);
		
		slot1 = new JMenuItem("Espacio 1");
		slot1.addActionListener(this);
		guardar.add(slot1);
		
		
		hechizeros[0] = new JLabel(new ImageIcon("IMAGENES/blueWizard2.png"));
		hechizeros[0].setBounds(750, 100, 177, 199);
		hechizeros[0].setVisible(false);
		hechizeros[0].setLayout(null);
		this.add(hechizeros[0]);
		
		Castle = new ImageIcon("IMAGENES/KingsRoom3.png");
		castillo = new JLabel(Castle);
		castillo.setBounds(0, 0, 814, 403);
		castillo.setVisible(false);
		castillo.setLayout(null);
		this.add(castillo);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a){
		if(a.getSource() == slot1){
			guardarJuego("con1.txt");
		}
	}
	
	public void guardarJuego(String espacio){
		try {
			File datos = new File("CONTINUES/" + espacio);
			FileWriter myWriter = new FileWriter("CONTINUES/" + espacio);
			
			myWriter.write(distanciaRecorrida + " " + i);
			myWriter.close();
			
		} catch (IOException e) {
		  System.out.println("An error occurred.");
		  e.printStackTrace();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//keyTyped = Invoked when a key is typed. Uses KeyChar, char output
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
		int speed = 15;
		
		switch(e.getKeyCode()) {
			case 37: //Izquierda
				if(player.getX() < 20){
					System.out.print("");
				}else if(distanciaRecorrida <= 0){
					player.setLocation(player.getX()-speed, player.getY());
					distanciaRecorrida -= speed;
				}else{
					floor1.setLocation(floor1.getX()+speed, floor1.getY());
					floor2.setLocation(floor2.getX()+speed, floor2.getY());
					torre.setLocation(torre.getX()+speed, torre.getY());
					/*for(int i = 0; i < 8; i++){
						banderas[i].setLocation(banderas[i].getX()+speed, banderas[i].getY());
						this.add(banderas[i]);
					}*/
					
					banderas[0].setLocation(banderas[0].getX()+speed, banderas[0].getY());
					
					castillo.setLocation(castillo.getX()+speed, castillo.getY());
					hechizeros[0].setLocation(hechizeros[0].getX()+speed, hechizeros[0].getY());
					mensaje.setLocation(mensaje.getX()+speed, mensaje.getY());
					distanciaRecorrida -= speed;
				}
				
				if(floor2.getX() + (x2/2) > 216 && cambio == true){
					floor1.setLocation(floor2.getX() - x2, floor1.getY());
					cambio = false;
				}else if(floor1.getX() + (x2/2) > 216 && cambio == false){
					floor2.setLocation(floor1.getX() - x2, floor2.getY());
					cambio = true;
				}
				
				if(N == true){
					if(i < 8){
						PlayerL = new ImageIcon("IMAGENES/NessL1.png");
						i++;
					}else if(i < 16){
						PlayerL = new ImageIcon("IMAGENES/NessL2.png");
						i++;
					}else{
						i = 0;
					}
				}else{
					if(i < 8){
						PlayerL = new ImageIcon("IMAGENES/PaulaL1.png");
						i++;
					}else if(i < 16){
						PlayerL = new ImageIcon("IMAGENES/PaulaL2.png");
						i++;
					}else{
						i = 0;
					}
				}
				
				player.setIcon(PlayerL);
				
				break;
			case 39: //Derecha
				
				if(distanciaRecorrida <= 0 && castillo.isVisible() == true){
					player.setLocation(player.getX()+speed, player.getY());
					distanciaRecorrida += speed;
				}
				else if(castillo.isVisible() == true && distanciaRecorrida < 315){
					hechizeros[0].setLocation(hechizeros[0].getX()-speed, hechizeros[0].getY());
					castillo.setLocation(castillo.getX()-speed, castillo.getY());
					distanciaRecorrida += speed;
				}
				else if(distanciaRecorrida <= 0 && castillo.isVisible() == false){
					player.setLocation(player.getX()+speed, player.getY());
					distanciaRecorrida += speed;
				}else if(distanciaRecorrida < 2050 && castillo.isVisible() == false){
					floor1.setLocation(floor1.getX()-speed, floor1.getY());
					floor2.setLocation(floor2.getX()-speed, floor2.getY());
					torre.setLocation(torre.getX()-speed, torre.getY());
					
					/*for(int i = 0; i < 8; i++){
						banderas[i].setLocation(banderas[i].getX()-speed, banderas[i].getY());
						this.add(banderas[i]);
					}*/
					banderas[0].setLocation(banderas[0].getX()-speed, banderas[0].getY());
					
					hechizeros[0].setLocation(hechizeros[0].getX()-speed, hechizeros[0].getY());
					castillo.setLocation(castillo.getX()-speed, castillo.getY());
					mensaje.setLocation(mensaje.getX()-speed, mensaje.getY());
					distanciaRecorrida += speed;
				}
				
				if(floor2.getX() + (x2/2) < 216 && cambio == true){
					floor1.setLocation(floor2.getX() + x2, floor1.getY());
					cambio = false;
				}else if(floor1.getX() + (x2/2) < 216 && cambio == false){
					floor2.setLocation(floor1.getX() + x2, floor2.getY());
					cambio = true;
				}
				
				if(N == true){
					if(i < 8){
						PlayerR = new ImageIcon("IMAGENES/NessR1.png");
						i++;
					}else if(i < 16){
						PlayerR = new ImageIcon("IMAGENES/NessR2.png");
						i++;
					}else{
						i = 0;
					}
				}else{
					if(i < 3){
						PlayerR = new ImageIcon("IMAGENES/PaulaR1.png");
						i++;
					}else if(i < 6){
						PlayerR = new ImageIcon("IMAGENES/PaulaR2.png");
						i++;
					}else{
						i = 0;
					}
				}
				
				player.setIcon(PlayerR);
				break;
			case 38: 
				
				break;
			case 40: 
				
				break;
				
		}
		
		if(distanciaRecorrida >= 1875 && distanciaRecorrida <= 1965){
			mensaje.setVisible(true);
		}else if(distanciaRecorrida == 315 && castillo.isVisible() == true){
			mensaje.setText("     Pelear");
			mensaje.setVisible(true);
			mensaje.setLocation(220, 170);
		}
		else{
			mensaje.setVisible(false);
		}
		
		if(mensaje.isVisible() == true && e.getKeyCode() == 10 && castillo.isVisible() == false){
			floor1.setVisible(false);
			floor2.setVisible(false);
			torre.setVisible(false);
			banderas[0].setVisible(false);
			mensaje.setVisible(false);
			castillo.setVisible(true);
			castillo.setLocation(-15, 0);
			hechizeros[0].setVisible(true);
			hechizeros[0].setLocation(610, 70);
			distanciaRecorrida = 0;
		}
		else if(mensaje.isVisible() == true && e.getKeyCode() == 10 && castillo.isVisible() == true){
			Problema n4 = new Problema();
			n4.setBounds(0,0,500,450);
			n4.setVisible(true);
			n4.setResizable(false);
			n4.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		
		System.out.println(distanciaRecorrida);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//keyReleased = called whenever a button is released
		//System.out.println("You released key char: " + e.getKeyChar());
		//System.out.println("You released key code: " + e.getKeyCode());
	}
	
	public static void main(String[] args) {
		Mundo fra = new Mundo();
	}
}