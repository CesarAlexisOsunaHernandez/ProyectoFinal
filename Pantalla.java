import java.awt.Color; //Color
import javax.swing.*; //Interfaz grafica
import java.awt.event.*; //Boton ComboBox
import javax.swing.event.*; //Checkbox

public class Pantalla extends JFrame implements ActionListener, KeyListener{
	
	public Pantalla(int ancho, int alto){
		setLayout(null);
		setTitle("Heroe Matematico: Primaria");
		setIconImage(new ImageIcon(getClass().getResource("IMAGENES/Icono.png")).getImage());
		setBounds(0,0,ancho, alto);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		addKeyListener(this);
		getContentPane().setBackground(new Color(50,155,250));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent c){ //Botones
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//keyTyped = Invoked when a key is typed. Uses KeyChar, char output
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}