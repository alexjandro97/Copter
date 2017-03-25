/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
/**
 *
 * @author alex
 */
public class Suelo extends Rectangle2D.Double{
    Image imagenSuelo; 
    
    public Suelo (double _posicionX, double _posicionY){
        super();
        imagenSuelo = (new ImageIcon(getClass().getResource("/imagenes/suelo.png"))).getImage();
        this.x = _posicionX;
        this.y = _posicionY;
        this.width = imagenSuelo.getWidth(null);
        this.height = imagenSuelo.getHeight(null);
        
    }
    
    public void mueve(Graphics2D g2){
        this.x--;
        g2.drawImage(imagenSuelo,(int)this.x, (int)this.y, null);
        if (this.x <= -this.width){
            this.x = this.width;
        }
    }    
}

