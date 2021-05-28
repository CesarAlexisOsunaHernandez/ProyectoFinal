import javax.swing.*; //Interfaz grafica
import java.awt.event.*; //ComboBox

public class ManejarContinues{
	private Serializadora ser = new Serializadora();
	
	private int contarContinues(){
		int res = 0;
		for(int i = 1; i <= 3; i++){
			if(ser.leerObjeto(i) != null){
				res++;
			}
		}
		return res;
	}
	
	public int elegirContinue(){
		String ops[] = new String[contarContinues()];
		int input = 0, op = 0;
		
		nombrarContinues(ops);
		JComboBox cb = new JComboBox(ops);
		input = JOptionPane.showConfirmDialog(null, cb, "Espacio", JOptionPane.DEFAULT_OPTION);
		
		if(input == JOptionPane.OK_OPTION){
			op = Integer.parseInt((String)cb.getSelectedItem());
			Mundo n3 = new Mundo((Personaje)ser.leerObjeto(op));
			n3.setBounds(0,0,500,450);
			n3.setVisible(true);
			n3.setResizable(false);
			n3.setLocationRelativeTo(null);
		}
		
		return input;
	}
	
	private void nombrarContinues(String ops[]){
		int i = 0;
		if(ser.leerObjeto(1) != null)
			ops[i] = "1";
			i++;
			
		if(ser.leerObjeto(2) != null)
			ops[i] = "2";
			i++;
			
		if(ser.leerObjeto(3) != null)
			ops[i] = "3";
	}
}