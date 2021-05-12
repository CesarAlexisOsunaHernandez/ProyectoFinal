import javax.swing.*; //Interfaz grafica
import java.awt.Color;
import java.awt.event.*; //Boton

public class ElijePersonaje extends JFrame implements ActionListener{
	private JLabel personaje, hom, mu;
	private JButton hombre, mujer, siguiente;
	static boolean N = false, P = false;
	
	public ElijePersonaje(){
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(50,155,250));
		
		personaje = new JLabel("Haz tu personaje");
		personaje.setBounds(80,10,300,30);
		add(personaje);
		
		ImageIcon imagen = new ImageIcon("IMAGENES/Paula.png");
		mu = new JLabel(imagen);
		mu.setBounds(140,70,70,100);
		mu.setVisible(false);
		add(mu);
		
		ImageIcon nino = new ImageIcon("IMAGENES/NessFront1.png");
		hom = new JLabel(nino);
		hom.setBounds(140,70,70,110);
		hom.setVisible(false);
		add(hom);
		
		hombre = new JButton("Ni\u00f1o");
		hombre.setBounds(30,80,80,30); //setBounds(x,y,width,height)
		add(hombre);
		hombre.addActionListener(this);
		//b1.setVisible(false);
		
		mujer = new JButton("Ni\u00f1a");
		mujer.setBounds(30,130,80,30); //setBounds(x,y,width,height)
		add(mujer);
		mujer.addActionListener(this);
		//b2.setVisible(false);
		
		siguiente = new JButton("Siguiente");
		siguiente.setBounds(30,200,100,30); //setBounds(x,y,width,height)
		siguiente.setEnabled(false);
		add(siguiente);
		siguiente.addActionListener(this);
		//b2.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent c){
		if(c.getSource() == hombre){
			hom.setVisible(true);
			mu.setVisible(false);
			N = true;
			P = false;
			siguiente.setEnabled(true);
		}
		else if(c.getSource() == mujer){
			hom.setVisible(false);
			mu.setVisible(true);
			N = false;
			P = true;
			siguiente.setEnabled(true);
		}
		else if(c.getSource() == siguiente){
			Mundo n3 = new Mundo();
			//Problema n3 = new Problema();
			n3.setBounds(0,0,500,450);
			n3.setVisible(true);
			n3.setResizable(false);
			n3.setLocationRelativeTo(null);
			this.setVisible(false);
		}
	}
	
	public static void main(String args[]){
		ElijePersonaje n2 = new ElijePersonaje();
		n2.setBounds(0,0,300,300);
		n2.setVisible(true);
		n2.setLocationRelativeTo(null);
	}
}