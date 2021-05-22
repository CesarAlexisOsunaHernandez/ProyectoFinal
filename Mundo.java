import java.awt.Color; //Color
import java.awt.event.*; //Boton
import javax.swing.*; //Interfaz grafica
import java.io.*; //File

public class Mundo extends JFrame implements KeyListener, ActionListener{
	private JLabel player, floor1, floor2, torre, banderas[] = new JLabel[8], mensaje, castillo, hechizeros[] = new JLabel[8];
	private ImageIcon PlayerL, PlayerR;
	private JMenuBar mb1;
	private JMenu m1, guardar, volumen;
	private JMenuItem slots[] = new JMenuItem[3], alto, medio, bajo, silencio, idioma, salir;
	private int x1 = 0, x2 = 1408, distanciaRecorrida = 0, i = 0;
	private static boolean cambio = true;
	private File aux = new File("IMAGENES/Banderas.png");
	private Serializadora ser = new Serializadora();
	private Personaje per = new Personaje();
	private Audio audio = new Audio();
	
	public Mundo(Personaje per){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,450);
		this.setLayout(null);
		setTitle("Heroe Matematico: Primaria");
		setIconImage(new ImageIcon(getClass().getResource("IMAGENES/icono.png")).getImage());
		this.addKeyListener(this);
		getContentPane().setBackground(new Color(0,155,255));
		
		this.per.setDistancia(per.getDistancia());
		this.per.setJefe(per.getJefe());
		this.per.setGenero(per.getGenero());
		this.per.setCastillo(per.getCastillo());
		
		for(int i = 0; i < 8; i++){
			banderas[i] = new JLabel(new ImageIcon("IMAGENES/Bandera"+(i+1)+".png"));
			banderas[i].setBounds(1850, 50, 150, 66);
			banderas[i].setVisible(false);
			this.add(banderas[i]);
			
			hechizeros[i] = new JLabel(new ImageIcon("IMAGENES/Wizard"+ (i+1) +".png"));
			hechizeros[i].setBounds(750, 100, 177, 199);
			hechizeros[i].setVisible(false);
			this.add(hechizeros[i]);
		}
		banderas[per.getJefe()].setVisible(true);
		
		mensaje = new JLabel("     Entrar");
		mensaje.setBounds(2100, 170, 65, 25);
		mensaje.setOpaque(true);
		mensaje.setVisible(false);
		this.add(mensaje);
		
		if(per.getGenero().equals("hombre")){
			PlayerL = new ImageIcon("IMAGENES/NessL1.png");
			PlayerR = new ImageIcon("IMAGENES/NessR1.png");
		}else if(per.getGenero().equals("mujer")){
			PlayerL = new ImageIcon("IMAGENES/PaulaL1.png");
			PlayerR = new ImageIcon("IMAGENES/PaulaR1.png");
		}else{
			System.out.println("Ambos falsos");
		}
		
		player = new JLabel(PlayerR);
		player.setBounds(182, 200, 68, 108);
		this.add(player);
		
		torre = new JLabel(new ImageIcon("IMAGENES/Torre.png"));
		torre.setBounds(2000, -110, 310, 420);
		this.add(torre);
		
		floor1 = new JLabel(new ImageIcon("IMAGENES/Grass2.png"));
		floor1.setBounds(x1, 275, x2, 151);
		this.add(floor1);
		
		floor2 = new JLabel(new ImageIcon("IMAGENES/Grass2.png"));
		floor2.setBounds(x1 + x2, 275, x2, 151);
		this.add(floor2);
		
		mb1 = new JMenuBar();
		setJMenuBar(mb1);
		
		m1 = new JMenu("Opciones");
		mb1.add(m1);
		
		guardar = new JMenu("Guardar");
		m1.add(guardar);
		
		for(int i = 0; i < 3; i++){
			slots[i] = new JMenuItem("Espacio " + (i+1));
			slots[i].addActionListener(this);
			guardar.add(slots[i]);
		}
		
		volumen = new JMenu("Volumen");
		m1.add(volumen);
		
		alto = new JMenuItem("Alto");
		alto.addActionListener(this);
		volumen.add(alto);
		
		medio = new JMenuItem("Medio");
		medio.addActionListener(this);
		volumen.add(medio);
		
		bajo = new JMenuItem("Bajo");
		bajo.addActionListener(this);
		volumen.add(bajo);
		
		silencio = new JMenuItem("Silencio");
		silencio.addActionListener(this);
		volumen.add(silencio);
		
		idioma = new JMenuItem("Idioma");
		m1.add(idioma);
		
		salir = new JMenuItem("Salir");
		m1.add(salir);
		
		
		
		castillo = new JLabel(new ImageIcon("IMAGENES/KingsRoom.png"));
		castillo.setBounds(0, 0, 814, 403);
		castillo.setVisible(false);
		this.add(castillo);
		
		if(per.getGenero().length() != 0){
			this.distanciaRecorrida = per.getDistancia();
			torre.setLocation(2000 - distanciaRecorrida, -110);
			castillo.setLocation(-distanciaRecorrida, 0);
			mensaje.setLocation(2100 - distanciaRecorrida, 170);
			
			if(per.getCastillo()){
				castillo.setVisible(true);
			}
			
			for(int i = 0; i < 8; i++){
				banderas[i].setLocation(1850 - distanciaRecorrida, 50);
				hechizeros[i].setLocation(750 - distanciaRecorrida, 100);
			}
		}
		
		audio.Musica("Mundo");
	}
	
	public void actionPerformed(ActionEvent a){
		if(a.getSource() == slots[0]){
			per.setDistancia(distanciaRecorrida);
			ser.escribirObjeto(per, 1);
		}
		else if(a.getSource() == slots[1]){
			per.setDistancia(distanciaRecorrida);
			ser.escribirObjeto(per, 2);
		}
		else if(a.getSource() == slots[2]){
			per.setDistancia(distanciaRecorrida);
			ser.escribirObjeto(per, 3);
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
				if(distanciaRecorrida <= 0 && player.getX() >= 20){
					player.setLocation(player.getX()-speed, player.getY());
					distanciaRecorrida -= speed;
				}else if(player.getX() >= 20){
					floor1.setLocation(floor1.getX()+speed, floor1.getY());
					floor2.setLocation(floor2.getX()+speed, floor2.getY());
					torre.setLocation(torre.getX()+speed, torre.getY());
					for(int i = 0; i < 8; i++){
						banderas[i].setLocation(banderas[i].getX()+speed, banderas[i].getY());
						hechizeros[i].setLocation(hechizeros[i].getX()+speed, hechizeros[i].getY());
					}
					
					castillo.setLocation(castillo.getX()+speed, castillo.getY());
					
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
				
				if(per.getGenero().equals("hombre")){
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
					for(int i = 0; i < 8; i++){
						hechizeros[i].setLocation(hechizeros[i].getX()-speed, hechizeros[i].getY());
					}
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
					
					for(int i = 0; i < 8; i++){
						banderas[i].setLocation(banderas[i].getX()-speed, banderas[i].getY());
						hechizeros[i].setLocation(hechizeros[i].getX()-speed, hechizeros[i].getY());
					}
					
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
				
				if(per.getGenero().equals("hombre")){
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
					if(i < 8){
						PlayerR = new ImageIcon("IMAGENES/PaulaR1.png");
						i++;
					}else if(i < 16){
						PlayerR = new ImageIcon("IMAGENES/PaulaR2.png");
						i++;
					}else{
						i = 0;
					}
				}
				
				player.setIcon(PlayerR);
				break;
			/*case 38: 
				
				break;
			case 40: 
				
				break;
			*/	
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
			Audio.detener();
			floor1.setVisible(false);
			floor2.setVisible(false);
			torre.setVisible(false);
			banderas[per.getJefe()].setVisible(false);
			mensaje.setVisible(false);
			castillo.setVisible(true);
			castillo.setLocation(-15, 0);
			hechizeros[per.getJefe()].setVisible(true);
			hechizeros[per.getJefe()].setLocation(610, 70);
			distanciaRecorrida = 0;
			per.setCastillo(true);
			audio.Musica("Castillo");
		}
		else if(mensaje.isVisible() == true && e.getKeyCode() == 10 && castillo.isVisible() == true){
			Audio.detener();
			Problema n4 = new Problema(per);
			n4.setBounds(0,0,500,450);
			n4.setVisible(true);
			n4.setResizable(false);
			n4.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		
		//System.out.println(hechizeros[per.getJefe()].getX());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//keyReleased = called whenever a button is released
		//System.out.println("You released key char: " + e.getKeyChar());
		//System.out.println("You released key code: " + e.getKeyCode());
	}
}