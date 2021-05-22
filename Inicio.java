import java.awt.Color; //Color
import javax.swing.*; //Interfaz grafica
import java.awt.event.*; //Boton ComboBox
import javax.swing.event.*; //Checkbox

public class Inicio extends JFrame implements ActionListener{
	private JLabel titulo;
	private JButton nuevoJuego, continuarJuego, salir, idioma;
	private JComboBox cb;
	private Serializadora ser = new Serializadora();
	private Audio audio = new Audio();
	private String idiomas[] = {"Espa\u00f1ol", "English"}, Espanol, Ingles;
	
	public Inicio(){
		setLayout(null);
		//setTitle("Heroe Matematico: Primaria");
		setIconImage(new ImageIcon(getClass().getResource("IMAGENES/icono.png")).getImage());
		this.setBounds(0,0,300,350);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setBackground(new Color(50,155,250));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		titulo = new JLabel("Heroe Matematico: Primaria");
		titulo.setBounds(60,10,300,30);
		add(titulo);
		
		nuevoJuego = new JButton("Nuevo juego");
		nuevoJuego.setBounds(85,100,110,30);
		add(nuevoJuego);
		nuevoJuego.addActionListener(this);
		
		idioma = new JButton("Idioma");
		idioma.setBounds(85,200,110,30);
		//idioma.setFont(new Font("Andale Mono", 1, 30));
		add(idioma);
		idioma.addActionListener(this);
		
		salir = new JButton("Cerrar");
		salir.setBounds(85,250,110,30);
		add(salir);
		salir.addActionListener(this);
		
		continuarJuego = new JButton("Continuar");
		continuarJuego.setBounds(85,150,110,30);
		add(continuarJuego);
		continuarJuego.addActionListener(this);
		
		for(int i = 0; i < 3 ; i++){
			if(ser.leerObjeto(i+1) == null){
				continuarJuego.setEnabled(false);
			}
		}
		
		cb = new JComboBox();
		cb.setBounds(10,10,80,20);
		cb.setVisible(false);
		add(cb);
		
		cb.addItem("Espa\u00f1ol");
		cb.addItem("English");
		/*
		cb.addItem("Francais");
		cb.addItem("\u0639\u0631\u0628");
		cb.addItemListener(this);
		*/
		
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
			Audio.detener();
			int input, op;
			String ops[] = {"1","2","3"};
			JComboBox cb = new JComboBox(ops);
			input = JOptionPane.showConfirmDialog(this, cb, "Espacio", JOptionPane.DEFAULT_OPTION);
			if(input == JOptionPane.OK_OPTION){
				op = Integer.parseInt((String)cb.getSelectedItem());
				Mundo n3 = new Mundo((Personaje)ser.leerObjeto(op));
				n3.setBounds(0,0,500,450);
				n3.setVisible(true);
				n3.setResizable(false);
				n3.setLocationRelativeTo(null);
				this.setVisible(false);
			}
		}
		else if(c.getSource() == idioma){
			JComboBox cb = new JComboBox(idiomas);
			int input = JOptionPane.showConfirmDialog(this, cb, "OK", JOptionPane.DEFAULT_OPTION);
			if(input == JOptionPane.OK_OPTION){
				String str = (String)cb.getSelectedItem();
				System.out.println(str);
			}
			
		}
		else if(c.getSource() == salir){
			Audio.detener();
			System.exit(0);
		}
		
	}
}