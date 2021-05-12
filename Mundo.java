import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Mundo extends JFrame implements KeyListener, ActionListener{
	private JLabel player, floor1, floor2, torre;
	private ImageIcon PlayerL, PlayerR, Grass, Tower;
	static ElijePersonaje EP = new ElijePersonaje();
	private JMenuBar mb1;
	private JMenu m1, guardar;
	private JMenuItem slot1, slot2, slot3;
	int i = 0, x1 = 0, x2 = 1408, distanciaRecorrida = 0;
	static boolean N = true, P = false, cambio = true;
	public Inicio ini = new Inicio();
	
	public Mundo(){
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
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,450);
		this.setLayout(null);
		this.addKeyListener(this);
		getContentPane().setBackground(new Color(0,155,255));
		
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
		floor1.setOpaque(true);
		this.add(floor1);
		
		torre.setOpaque(true);
		this.add(torre);
		
		floor2 = new JLabel(Grass);
		floor2.setBounds(x1 + x2, 275, x2, 151);
		floor2.setOpaque(true);
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
		int speed = 30;
		
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
				
				if(distanciaRecorrida <= 0){
					player.setLocation(player.getX()+speed, player.getY());
				}else{
					floor1.setLocation(floor1.getX()-speed, floor1.getY());
					floor2.setLocation(floor2.getX()-speed, floor2.getY());
					torre.setLocation(torre.getX()-speed, torre.getY());
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
				distanciaRecorrida += speed;
				break;
			case 38: 
				
				break;
			case 40: 
				
				break;
				
		}
		System.out.println(distanciaRecorrida);
		//System.out.println("F1 X: " + floor1.getX() + " PM: " + (floor1.getX() + (x2 / 2)));
		//System.out.println("F2 X: " + floor2.getX() + "\n");
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