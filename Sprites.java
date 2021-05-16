import java.awt.*; //Image
import java.io.*; //File
import javax.imageio.*; //ImageIO
import java.awt.image.*; //BufferedImage

import javax.swing.*; //Interfaz grafica
import java.awt.Color;
import java.awt.event.*; //Boton

public class Sprites{
	
	static ImageIcon obtenerImagen(File sprites, int i, int x, int y){
		int ren = 0, separacion = 3;
		ImageIcon resultado = new ImageIcon();
		try{
			if(i > 4){
				ren++;
				i -= 4;
			}
			Image src = ImageIO.read(sprites);
			BufferedImage size = ImageIO.read(sprites);
			int w = size.getWidth(), h = size.getHeight();
			
			BufferedImage dst = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
			dst.getGraphics().drawImage(src, 0, 0, x, y, (i-1) * x + ((i-1)*separacion), ren * y + (ren * separacion), i * x + ((i-1)*separacion), (ren+1) * y + (ren * separacion), null);
			
			ImageIO.write(dst, "png", new File("cropped.png"));
			
			resultado = new ImageIcon("cropped.png");
		}
		catch(Exception e){
			
		}
		return resultado;
	}
}