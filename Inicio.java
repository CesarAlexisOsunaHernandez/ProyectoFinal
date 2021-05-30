import java.awt.Color; //Color
import javax.swing.*; //Interfaz grafica
import java.awt.event.*; //Boton ComboBox
import javax.swing.event.*; //Checkbox

public class Inicio extends JFrame implements ActionListener{
	private JLabel titulo, logo, premio;
	private JButton nuevoJuego, continuarJuego, salir, idioma;
	private JComboBox cb;
	private Serializadora ser = new Serializadora();
	private Audio audio = new Audio();
	private ManejarContinues MC = new ManejarContinues();
	
	public Inicio(Personaje per){
		setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("IMAGENES/Icono.png")).getImage());
		this.setBounds(0,0,300,300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setBackground(new Color(50,155,250));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		premio = new JLabel(new ImageIcon("IMAGENES/Premio.png"));
		premio.setBounds(20,40,50,59);
		if(per.getJefe() == 8){
			premio.setVisible(true);
		}
		else{
			premio.setVisible(false);
		}
		add(premio);
		
		logo = new JLabel(new ImageIcon("IMAGENES/Icono.png"));
		logo.setBounds(110,50,56,28);
		add(logo);
		
		titulo = new JLabel("Heroe Matematico: Primaria");
		titulo.setBounds(60,10,300,30);
		titulo.setVisible(true);
		add(titulo);
		
		nuevoJuego = new JButton("Nuevo juego");
		nuevoJuego.setBounds(85,100,110,30);
		add(nuevoJuego);
		nuevoJuego.setVisible(true);
		nuevoJuego.addActionListener(this);
		
		continuarJuego = new JButton("Continuar");
		continuarJuego.setBounds(85,150,110,30);
		continuarJuego.addActionListener(this);
		add(continuarJuego);
		
		
		salir = new JButton("Cerrar");
		salir.setBounds(85,200,110,30);
		salir.setVisible(true);
		salir.addActionListener(this);
		add(salir);
		
		
		if(ser.leerObjeto(1) == null && ser.leerObjeto(2) == null && ser.leerObjeto(3) == null){
			continuarJuego.setEnabled(false);
		}
		
		audio.Musica("Inicio");
	}
	
	public void actionPerformed(ActionEvent c){ //Botones
		
		if(c.getSource() == nuevoJuego){
			Audio.detener();
			ElijePersonaje n2 = new ElijePersonaje();
			n2.setBounds(0,0,300,300);
			n2.setVisible(true);
			n2.setResizable(false);
			n2.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(c.getSource() == continuarJuego){
			if(MC.elegirContinue() == JOptionPane.OK_OPTION){
				this.setVisible(false);
			}
		}
		else if(c.getSource() == salir){
			Audio.detener();
			System.exit(0);
		}
		
	}
	
	public static void main(String args[]){
		Inicio n1 = new Inicio(new Personaje());
		//n1.setBounds(0,0,500,500);
	}
}