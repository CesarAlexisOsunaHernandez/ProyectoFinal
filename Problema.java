import javax.swing.*; //Interfaz grafica
import java.awt.Color; //Color
import java.awt.event.*; //Boton, Textbox
import java.util.*; //Random

public class Problema extends Pantalla{
	private JLabel vidas, probl, msg, vidaWizLb, hechizeros[] = new JLabel[8];
	private JButton Ans[] = new JButton[3], easyAttack, hardAttack, volver, probar, accionesFinales[] = new JButton[3];
	private JTextField res;
	private int lives = 3, A = 0, B = 0, C = 0, dificultadInicial = 1, n1 = 0, n2 = 0, vidaHechizeros = 10, aumentoDeDificultad = 1, ataqueFacil = 1, ataqueDificil = 3;
	private Serializadora ser = new Serializadora();
	private Personaje per = new Personaje();
	private Audio audio = new Audio();
	private ManejarContinues MC = new ManejarContinues();
	
	public Problema(Personaje per){
		super(500, 450);
		
		this.per.setDistancia(per.getDistancia());
		this.per.setJefe(per.getJefe());
		this.per.setGenero(per.getGenero());
		this.per.setCastillo(per.getCastillo());
		
		asignaDificultad(per.getJefe());
		
		for(int i = 0; i < 8; i++){
			hechizeros[i] = new JLabel(new ImageIcon("IMAGENES/Wizard"+(i+1)+".png"));
			hechizeros[i].setBounds(130,20,210,210);
			hechizeros[i].setVisible(false);
			add(hechizeros[i]);
		}
		hechizeros[per.getJefe()].setVisible(true);
		audio.Musica("Jefe" + (per.getJefe()+1));
		
		vidaWizLb = new JLabel("Vida: " + vidaHechizeros);
		vidaWizLb.setBounds(420,5,300,30);
		add(vidaWizLb);
		
		msg = new JLabel("Inicio");
		msg.setBounds(390,250,300,30);
		add(msg);
		
		vidas = new JLabel("Vidas: " + lives);
		vidas.setBounds(10,250,300,30);
		add(vidas);
		
		probl = new JLabel();
		probl.setBounds(200,250,300,30);
		add(probl);
		
		easyAttack = new JButton("Ataque facil");
		easyAttack.setBounds(50,300,150,50); //setBounds(x,y,width,height)
		add(easyAttack);
		easyAttack.addActionListener(this);
		
		hardAttack = new JButton("Ataque dificil");
		hardAttack.setBounds(280,300,150,50); //setBounds(x,y,width,height)
		add(hardAttack);
		hardAttack.addActionListener(this);
		
		volver = new JButton("volver");
		volver.setBounds(390,360,80,30); //setBounds(x,y,width,height)
		add(volver);
		volver.addActionListener(this);
		volver.setVisible(false);
		
		
		for(int i = 0; i < 3; i++){
			Ans[i] = new JButton();
			Ans[i].setBounds(10 + (i*190),300,100,30); //setBounds(x,y,width,height)
			add(Ans[i]);
			Ans[i].addActionListener(this);
			Ans[i].setVisible(false);
		}
		asignaValores();
		
		res = new JTextField("");
		res.setBounds(200,300,80,30);
		add(res);
		res.setVisible(false);
		
		probar = new JButton("Probar");
		probar.setBounds(300,300,80,30); //setBounds(x,y,width,height)
		add(probar);
		probar.addActionListener(this);
		probar.setVisible(false);
		
		for(int i = 0; i < 3; i++){
			accionesFinales[i] = new JButton();
			accionesFinales[i].setBounds(170,(i*50)+280,130,30);
			add(accionesFinales[i]);
			accionesFinales[i].addActionListener(this);
			accionesFinales[i].setVisible(false);
		}
		
		switch(per.getJefe()){
			case 0: //Blanco
			getContentPane().setBackground(new Color(240,240,240));
			break;
			case 1: //Amarillo
			getContentPane().setBackground(new Color(230,245,0));
			break;
			case 2: //Verde
			getContentPane().setBackground(new Color(0,220,70));
			break;
			case 3: //Azul
			getContentPane().setBackground(new Color(0,80,200));
			probl.setForeground(Color.white);
			vidas.setForeground(Color.white);
			msg.setForeground(Color.white);
			vidaWizLb.setForeground(Color.white);
			break;
			case 4: //Marron
			getContentPane().setBackground(new Color(170,130,80));
			break;
			case 5: //Rojo
			getContentPane().setBackground(new Color(240,30,0));
			break;
			case 6: //Negro
			getContentPane().setBackground(new Color(40,40,40));
			probl.setForeground(Color.white);
			vidas.setForeground(Color.white);
			msg.setForeground(Color.white);
			vidaWizLb.setForeground(Color.white);
			break;
			case 7: //???
			getContentPane().setBackground(new Color(100,0,0));
			probl.setForeground(Color.white);
			vidas.setForeground(Color.white);
			msg.setForeground(Color.white);
			vidaWizLb.setForeground(Color.white);
			break;
		}
	}
	
	public void actionPerformed(ActionEvent c){
		
		if(c.getSource() == easyAttack){
			easyAttack.setVisible(false);
			hardAttack.setVisible(false);
			for(int i = 0; i < 3; i++){
				Ans[i].setVisible(true);
			}
			volver.setVisible(true);
		}
		else if(c.getSource() == hardAttack){
			easyAttack.setVisible(false);
			hardAttack.setVisible(false);
			res.setVisible(true);
			probar.setVisible(true);
			volver.setVisible(true);
		}
		else if(c.getSource() == volver){
			easyAttack.setVisible(true);
			hardAttack.setVisible(true);
			for(int i = 0; i < 3; i++){
				Ans[i].setVisible(false);
			}
			volver.setVisible(false);
			probar.setVisible(false);
			res.setVisible(false);
		}
		else if(c.getSource() == Ans[0]){
			if(A == n1 + n2){
				asignaValores();
				msg.setText("Correcto");
				vidaHechizeros -= ataqueFacil;
				vidaWizLb.setText("Vida: " + vidaHechizeros);
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		else if(c.getSource() == Ans[1]){
			if(B == n1 + n2){
				asignaValores();
				msg.setText("Correcto");
				vidaHechizeros -= ataqueFacil;
				vidaWizLb.setText("Vida: " + vidaHechizeros);
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		else if(c.getSource() == Ans[2]){
			if(C == n1 + n2){
				asignaValores();
				msg.setText("Correcto");
				vidaHechizeros -= ataqueFacil;
				vidaWizLb.setText("Vida: " + vidaHechizeros);
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		else if(c.getSource() == probar){
			if(res.getText().equals(Integer.toString((n1 + n2)))){
				asignaValores();
				msg.setText("Correcto");
				vidaHechizeros -= ataqueDificil;
				vidaWizLb.setText("Vida: " + vidaHechizeros);
				res.setText("");
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		else if(c.getSource() == accionesFinales[0]){
			Mundo n3;
			Audio.detener();
			if(accionesFinales[0].getText().equals("Continuar")){
				per.setJefe(per.getJefe() + 1);
				
				if(per.getJefe() == 8){
					JOptionPane.showMessageDialog(null, "Completaste mi juego!!!", "Felicidades!!!", JOptionPane.PLAIN_MESSAGE);
					Inicio n1 = new Inicio(per);
					n1.setBounds(0,0,300,330);
					n1.setVisible(true);
					n1.setResizable(false);
					n1.setLocationRelativeTo(null);
					this.setVisible(false);
				}
				else{
					n3 = new Mundo(per, false);
					n3.setBounds(0,0,500,450);
					n3.setVisible(true);
					n3.setResizable(false);
					n3.setLocationRelativeTo(null);
					this.setVisible(false);
				}
			}
			else{
				if(MC.elegirContinue() == JOptionPane.OK_OPTION){
					this.setVisible(false);
				}
			}
		}
		else if(c.getSource() == accionesFinales[1]){
			Audio.detener();
			Inicio n1 = new Inicio(per);
			n1.setBounds(0,0,300,330);
			n1.setVisible(true);
			n1.setResizable(false);
			n1.setLocationRelativeTo(null);
			this.setVisible(false);
		}
		else if(c.getSource() == accionesFinales[2]){
			System.exit(0);
		}
		
		if(vidaHechizeros <= 0){
			vidaWizLb.setText("Vida: 0");
			probl.setText("FELICIDADES!");
			msg.setText("");
			easyAttack.setVisible(false);
			hardAttack.setVisible(false);
			for(int i = 0; i < 3; i++){
				Ans[i].setVisible(false);
			}
			volver.setVisible(false);
			probar.setVisible(false);
			res.setVisible(false);
			easyAttack.setEnabled(false);
			hardAttack.setEnabled(false);
			
			accionesFinales[0].setText("Continuar");
			accionesFinales[0].setVisible(true);
			if(this.isVisible()){
				Audio.detener();
				audio.Musica("Victoria");
			}
			
		}
		
		if(lives == 0){
			probl.setText("PERDISTE");
			msg.setText("");
			easyAttack.setVisible(false);
			hardAttack.setVisible(false);
			for(int i = 0; i < 3; i++){
				Ans[i].setVisible(false);
			}
			volver.setVisible(false);
			probar.setVisible(false);
			res.setVisible(false);
			easyAttack.setEnabled(false);
			hardAttack.setEnabled(false);
			
			for(int i = 0; i < 3; i++){
				switch(i){
					case 0:
					accionesFinales[i].setText("Punto guardado");
					break;
					case 1:
					accionesFinales[i].setText("Menu principal");
					break;
					case 2:
					accionesFinales[i].setText("Cerrar");
					break;
				}
				
				accionesFinales[i].setVisible(true);
			}
			
			if(this.isVisible()){
				Audio.detener();
				audio.Musica("Derrota");
			}
		}
	}
	
	public void asignaValores(){
		int v1, v2;
		Random ran = new Random();
		
		dificultadInicial += aumentoDeDificultad;
		n1 = ran.nextInt(dificultadInicial) + 1;
		n2 = ran.nextInt(dificultadInicial) + 1;
		v1 = ran.nextInt(dificultadInicial) + 1;
		v2 = ran.nextInt(dificultadInicial) + 1;
		
		A = ran.nextInt(30);
		B = ran.nextInt(30);
		C = ran.nextInt(30);
		
		if(A > B && A > C){
			A = n1 + n2;
			if(B > C){
				B = n1 + v1;
				C = n1 + v2;
			}
			else{
				C = n1 + v1;
				B = n1 + v2;
			}
		}
		else if(B > A && B > C){
			B = n1 + n2;
			if(A > C){
				A = n1 + v1;
				C = n1 + v2;
			}
			else{
				C = n1 + v1;
				A = n1 + v2;
			}
		}
		else if(C > A && C > B){
			C = n1 + n2;
			if(A > B){
				A = n1 + v1;
				B = n1 + v2;
			}
			else{
				B = n1 + v1;
				A = n1 + v2;
			}
		}
		
		if(A == B && A == C){
			B++;
			C--;
		}
		else if(A == B){
			A++;
			if(A == C){
				A++;
			}
		}
		else if(A == C){
			A++;
			if(A == B){
				A++;
			}
		}
		else if(B == C){
			B++;
			if(A == B){
				B++;
			}
		}
		
		
		for(int i = 0; i < 3; i++){
			if(i == 0)
				Ans[i].setText(Integer.toString(A));
			else if(i == 1)
				Ans[i].setText(Integer.toString(B));
			else if(i == 2)
				Ans[i].setText(Integer.toString(C));
		}
		
		probl.setText(n1 + " + " + n2 + " = ?");
	}
	
	public void asignaDificultad(int i){
		for(int j = 0; j < i; j++){
			this.dificultadInicial *= 10;
			this.aumentoDeDificultad *= 10;
			this.vidaHechizeros += 12;
			this.ataqueFacil++;
			this.ataqueDificil += 2;
		}
	}
}