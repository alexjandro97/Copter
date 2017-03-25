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
public class Caza {
    int yVelocidad = -2;
    Image imagen1, imagen2;
    int rotacion = 0;
    
    public Caza(int _radio){
        super(100, 100, 33, 23);
        imagen1 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/fly1.png"))
                .getImage().getScaledInstance(33, 23, Image.SCALE_DEFAULT))).getImage();
        imagen2 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/fly2.png"))
                .getImage().getScaledInstance(33, 23, Image.SCALE_DEFAULT))).getImage();
    }
    
    public void sube(){
        this.yVelocidad += 9;
    }
    
    public void mueve(Graphics2D g2, int imagenPajaro){
        AffineTransform trans = new AffineTransform();
        rotacion -= yVelocidad;
        if (rotacion < -45) { rotacion = -45;}//si la rotación es menor que -45 lo deja en -45
        if (rotacion > 45) { rotacion = 45; } //si la rotación es mayor que 45 lo deja en 45     
        trans.translate(x, y);  //mueve la imagen a la posición en que tiene que ser dibujada
        trans.rotate( Math.toRadians(rotacion), width/2, height/2 );  //gira la imagen tantos grados como ponga rotación
        
        this.y = this.y - yVelocidad;
        //pongo un tope para que no se salga por el techo
        if (this.y < 0) {this.y = 0;}
        if (imagenPajaro < 15){
            g2.drawImage(imagen1, trans,null);}
        else{
            g2.drawImage(imagen2, trans, null);
        }
        //g2.fill(this);
        yVelocidad --;
        if (yVelocidad < -3){yVelocidad = -1;}  //si la velocidad es menor que -3 la deja en -1
    }
    
    public boolean chequeaColision(Columna c){
       
        Area areaPajaro = new Area(this);
        Area areaCirculo = new Area(c.circuloInferior);
        Area areaCirculo2 = new Area(c.circuloSuperior);
        
        boolean choca = true, choca2 = true;
        
        //chequeo el choque con el circulo de la columna superior
        areaPajaro.intersect(areaCirculo);
       
        if (areaPajaro.isEmpty()){
            choca = false;
        }
        
        //chequeo el choque con el circulo de la columna inferior
        areaPajaro = new Area(this);
        areaPajaro.intersect(areaCirculo2);
        if (areaPajaro.isEmpty()){
            choca2 = false;
        }
        
        return (this.intersects(c.capitel) || 
                this.intersects(c.base) ||
                choca || choca2
                );
    }
}
