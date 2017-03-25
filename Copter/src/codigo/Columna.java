package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author alex
 */
public class Columna {
    //variables globales
    Rectangle2D arriba, abajo;
    int hueco = 200;
    int altura_columna = 500;
    int ancho_columna = 79;
    private int ancho_pantalla;
    Image col_abajo, col_arriba;
        //contructor al que pasamos el ancho de la columna y de la pantalla
    public Columna (int _ancho, int _anchoPantalla){       
        posicionInicialColumna(_ancho);
        ancho_pantalla = _anchoPantalla;
        precargaImagenes();
    }
    
    private void posicionInicialColumna(int _ancho){
        Random aleatorio = new Random();
        int desplazamiento = aleatorio.nextInt(200)+200;
        arriba = new Rectangle2D.Double(_ancho, 
                                        -desplazamiento - ancho_columna/2, 
                                        ancho_columna, 
                                        altura_columna);
        abajo = new Rectangle2D.Double(_ancho, 
                                      altura_columna + hueco - desplazamiento + ancho_columna/2, 
                                      ancho_columna, 
                                      altura_columna);
    }
    
    private void precargaImagenes(){
         //cargamos las imagenes de las columnas
        col_abajo = (new ImageIcon(new ImageIcon(
                getClass().getResource("/imagenes/columna.jpg"))
                .getImage().getScaledInstance(79, 500, Image.SCALE_DEFAULT)))
                .getImage();
        col_arriba = (new ImageIcon(new ImageIcon(
                getClass().getResource("/imagenes/columna.jpg"))
                .getImage().getScaledInstance(79, 500, Image.SCALE_DEFAULT)))
                .getImage();       
        
    }
    
    public boolean mueve(Graphics2D g2, Caza c){
        mueveColumna();
        g2.setColor(Color.BLUE);
        
        g2.drawImage(col_abajo, (int)abajo.getX(), (int)abajo.getY()-ancho_columna/2, null);
        g2.drawImage(col_arriba, (int)arriba.getX(), (int)arriba.getY()+ancho_columna/2, null);
       
        //si el pájaro está en la columna, subo 1 el marcador
        return (arriba.getX() == c.x);      
    }
    //aqui tenemos el movimiento de la columna tanto por el panel como cuando ddesaparece y vuelve a entrar en el juego
    private void mueveColumna(){
        
        if (arriba.getX() + ancho_columna < 0){
            posicionInicialColumna(ancho_pantalla);
        }
        else{
            arriba.setFrame(arriba.getX()-1, arriba.getY(),arriba.getWidth(), arriba.getHeight());
            abajo.setFrame(abajo.getX()-1, abajo.getY(),abajo.getWidth(), abajo.getHeight());
        }
    }
}