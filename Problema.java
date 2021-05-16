import javax.swing.*; //Interfaz grafica
import java.awt.Color;
import java.awt.event.*; //Boton, Textbox
import java.util.*; //Random
import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class Problema extends JFrame implements ActionListener{
	private JLabel vidas, probl, msg, wiz, vidaWizLb;
	private JButton An1, An2, An3, easyAttack, hardAttack, volver, probar;
	private JTextField res;
	private int lives = 3, A = 0, B = 0, C = 0, difi = 2, n1 = 0, n2 = 0, vidaWiz = 10;
	
	public Problema(){
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().setBackground(new Color(50,255,100));
		
		ImageIcon imagen = new ImageIcon("IMAGENES/blueWizard2.png");
		wiz = new JLabel(imagen);
		wiz.setBounds(130,20,200,200);
		wiz.setVisible(true);
		add(wiz);
		
		vidaWizLb = new JLabel("Vida: " + vidaWiz);
		vidaWizLb.setBounds(420,5,300,30);
		add(vidaWizLb);
		
		msg = new JLabel("Inicio");
		msg.setBounds(390,250,300,30);
		add(msg);
		
		vidas = new JLabel("Vidas: " + lives);
		vidas.setBounds(10,250,300,30);
		add(vidas);
		
		probl = new JLabel(n1 + " + " + n2 + " = ?");
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
		
		volver = new JButton("back");
		volver.setBounds(390,360,80,30); //setBounds(x,y,width,height)
		add(volver);
		volver.addActionListener(this);
		volver.setVisible(false);
		
		An1 = new JButton(Integer.toString(A));
		An1.setBounds(10,300,80,30); //setBounds(x,y,width,height)
		add(An1);
		An1.addActionListener(this);
		An1.setVisible(false);
		
		An2 = new JButton(Integer.toString(B));
		An2.setBounds(200,300,80,30); //setBounds(x,y,width,height)
		add(An2);
		An2.addActionListener(this);
		An2.setVisible(false);
		
		An3 = new JButton(Integer.toString(C));
		An3.setBounds(390,300,80,30); //setBounds(x,y,width,height)
		add(An3);
		An3.addActionListener(this);
		An3.setVisible(false);
		
		res = new JTextField("");
		res.setBounds(200,300,80,30);
		add(res);
		res.setVisible(false);
		
		probar = new JButton("Probar");
		probar.setBounds(300,300,80,30); //setBounds(x,y,width,height)
		add(probar);
		probar.addActionListener(this);
		probar.setVisible(false);
		
		asignaValores();
	}
	
	public void actionPerformed(ActionEvent c){
		
		if(c.getSource() == easyAttack){
			easyAttack.setVisible(false);
			hardAttack.setVisible(false);
			An1.setVisible(true);
			An2.setVisible(true);
			An3.setVisible(true);
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
			An1.setVisible(false);
			An2.setVisible(false);
			An3.setVisible(false);
			volver.setVisible(false);
			probar.setVisible(false);
			res.setVisible(false);
		}
		else if(c.getSource() == An1){
			if(A == n1 + n2){
				asignaValores();
				msg.setText("Correcto");
				vidaWiz--;
				vidaWizLb.setText("Vida: " + vidaWiz);
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		else if(c.getSource() == An2){
			if(B == n1 + n2){
				asignaValores();
				msg.setText("Correcto");
				vidaWiz--;
				vidaWizLb.setText("Vida: " + vidaWiz);
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		else if(c.getSource() == An3){
			if(C == n1 + n2){
				asignaValores();
				msg.setText("Correcto");
				vidaWiz--;
				vidaWizLb.setText("Vida: " + vidaWiz);
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
				vidaWiz-=3;
				vidaWizLb.setText("Vida: " + vidaWiz);
				res.setText("");
			}
			else{
				lives--;
				vidas.setText("Vidas: " + lives);
				msg.setText("Falso");
			}
		}
		
		
		if(vidaWiz <= 0){
			vidaWizLb.setText("Vida: 0");
			probl.setText("WELL DONE");
			msg.setText("");
			easyAttack.setVisible(true);
			hardAttack.setVisible(true);
			An1.setVisible(false);
			An2.setVisible(false);
			An3.setVisible(false);
			volver.setVisible(false);
			probar.setVisible(false);
			res.setVisible(false);
			easyAttack.setEnabled(false);
			hardAttack.setEnabled(false);
		}
		
		if(lives == 0){
			probl.setText("GAME OVER");
			msg.setText("");
			An1.setEnabled(false);
			An2.setEnabled(false);
			An3.setEnabled(false);
		}
	}
	
	public void asignaValores(){
		int v1, v2;
		Random ran = new Random();
		
		difi++;
		n1 = ran.nextInt(difi) + 1;
		n2 = ran.nextInt(difi) + 1;
		v1 = ran.nextInt(difi) + 1;
		v2 = ran.nextInt(difi) + 1;
		
		A = ran.nextInt(30);
		B = ran.nextInt(30);
		C = ran.nextInt(30);
		
		//System.out.println("A: " + A + " B: " + B + " C: " + C);
		
		if(A > B && A > C){
			A = n1 + n2;
			if(B > C){
				B = n1 + v1;
				C = n1 + v2;
				//System.out.println("A: " + A + " B: " + B + " C: " + C);
			}
			else{
				C = n1 + v1;
				B = n1 + v2;
				//System.out.println("A: " + A + " B: " + B + " C: " + C);
			}
		}
		else if(B > A && B > C){
			B = n1 + n2;
			if(A > C){
				A = n1 + v1;
				C = n1 + v2;
				//System.out.println("A: " + A + " B: " + B + " C: " + C);
			}
			else{
				C = n1 + v1;
				A = n1 + v2;
				//System.out.println("A: " + A + " B: " + B + " C: " + C);
			}
		}
		else if(C > A && C > B){
			C = n1 + n2;
			if(A > B){
				A = n1 + v1;
				B = n1 + v2;
				//System.out.println("A: " + A + " B: " + B + " C: " + C);
			}
			else{
				B = n1 + v1;
				A = n1 + v2;
				//System.out.println("A: " + A + " B: " + B + " C: " + C);
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
		
		An1.setText(Integer.toString(A));
		An2.setText(Integer.toString(B));
		An3.setText(Integer.toString(C));
		probl.setText(n1 + " + " + n2 + " = ?");
	}
	
	public void setLives(int lives){
		this.lives = lives;
	}
	
	public void setDifi(int difi){
		this.difi = difi;
	}
	
	public void setVidaWiz(int vidaWiz){
		this.vidaWiz = vidaWiz;
	}
	
	public static void main(String args[]){
		Problema Ma = new Problema();
		Ma.setBounds(0,0,500,450);
		Ma.setVisible(true);
		Ma.setResizable(false);
		Ma.setLocationRelativeTo(null);
	}
}