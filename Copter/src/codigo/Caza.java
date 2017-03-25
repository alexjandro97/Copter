package codigo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
/**
 *
 * @author alex
 */
public class Caza extends Ellipse2D.Double{
    //Variables globales
    int yVelocidad = -2;
    Image imagen1, imagen2;
    int rotacion = 0;
    //contructor al que le pasamos el radio
    public Caza(int _radio){
        super(100, 100, 33, 23);
        imagen1 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/xwing.png"))
                .getImage().getScaledInstance(33, 23, Image.SCALE_DEFAULT))).getImage();
        imagen2 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/xwing.png"))
                .getImage().getScaledInstance(33, 23, Image.SCALE_DEFAULT))).getImage();
    }
    //es el metodo que se ejecuta cuando presionamos el espacio
    public void sube() {
        this.yVelocidad += 9;
    }
    //el movimiento del caza, con la rotacion cuando sube y baja
    public void mueve(Graphics2D g2, int imagenCaza){
        AffineTransform trans = new AffineTransform();
        rotacion -= yVelocidad;
        if (rotacion < -45) { rotacion = -45;}//si la rotación es menor que -45 lo deja en -45
        if (rotacion > 45) { rotacion = 45; } //si la rotación es mayor que 45 lo deja en 45     
        trans.translate(x, y);  //mueve la imagen a la posición en que tiene que ser dibujada
        trans.rotate( Math.toRadians(rotacion), width/2, height/2 );  //gira la imagen tantos grados como ponga rotación
        
        this.y = this.y - yVelocidad;
        //pongo un tope para que no se salga por el techo
        if (this.y < 0) {this.y = 0;}
        if (imagenCaza < 15){
            g2.drawImage(imagen1, trans,null);}
        else{
            g2.drawImage(imagen2, trans, null);
        } 
        yVelocidad --;
        if (yVelocidad < -3){yVelocidad = -1;}  //si la velocidad es menor que -3 la deja en -1
    }
    //chequeamos la colision con el area de las columnas y del caza
    public boolean chequeaColision(Columna c){
       
        Area areaCaza = new Area(this);
        Area areaColArriba = new Area(c.arriba);
        Area areaColAbajo = new Area(c.abajo);
        
        boolean choca = true, choca2 = true;
        
        //chequeo el choque con la columna de arriba
        areaCaza.intersect(areaColArriba);
       
        if (areaCaza.isEmpty()){choca = false;}
        
        //chequeo el choque con la columna de abajo
        areaCaza = new Area(this);
        areaCaza.intersect(areaColAbajo);
        if (areaCaza.isEmpty()){choca2 = false;}
        
        return (this.intersects(c.arriba) || this.intersects(c.abajo) || choca || choca2);
    }
}
