import javax.swing.*; //Interfaz grafica
import java.awt.Color; //Color
import java.awt.event.*; //Boton
import java.io.*; //File

public class ElijePersonaje extends JFrame implements ActionListener{
	private JLabel personaje, hom, mu;
	private JButton hombre, mujer, siguiente;
	private Personaje per = new Personaje();
	private Audio audio = new Audio();
	
	public ElijePersonaje(){
		
		setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("IMAGENES/Icono.png")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(50,155,250));
		this.setBounds(0,0,300,300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		personaje = new JLabel("Haz tu personaje");
		personaje.setBounds(80,10,300,30);
		add(personaje);
		
		mu = new JLabel(new ImageIcon("IMAGENES/Paula.png"));
		mu.setBounds(140,70,70,100);
		mu.setVisible(false);
		add(mu);
		
		hom = new JLabel(new ImageIcon("IMAGENES/Ness.png"));
		hom.setBounds(140,70,70,110);
		hom.setVisible(false);
		add(hom);
		
		hombre = new JButton("Ni\u00f1o");
		hombre.setBounds(30,80,80,30); //setBounds(x,y,width,height)
		add(hombre);
		hombre.addActionListener(this);
		
		mujer = new JButton("Ni\u00f1a");
		mujer.setBounds(30,130,80,30); //setBounds(x,y,width,height)
		add(mujer);
		mujer.addActionListener(this);
		
		siguiente = new JButton("Siguiente");
		siguiente.setBounds(30,200,100,30); //setBounds(x,y,width,height)
		siguiente.setEnabled(false);
		add(siguiente);
		siguiente.addActionListener(this);
		
		audio.Musica("EligePersonaje");
	}
	
	public void actionPerformed(ActionEvent c){
		if(c.getSource() == hombre){
			hom.setVisible(true);
			mu.setVisible(false);
			per.setGenero("hombre");
			siguiente.setEnabled(true);
		}
		else if(c.getSource() == mujer){
			hom.setVisible(false);
			mu.setVisible(true);
			per.setGenero("mujer");
			siguiente.setEnabled(true);
		}
		else if(c.getSource() == siguiente){
			Audio.detener();
			Mundo n3 = new Mundo(per, false);
			n3.setBounds(0,0,500,450);
			n3.setVisible(true);
			n3.setResizable(false);
			n3.setLocationRelativeTo(null);
			this.setVisible(false);
		}
	}
	
}