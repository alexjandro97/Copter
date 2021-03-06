/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alex
 */
public class Ventanajuego extends javax.swing.JFrame {
    //Variables globales
    boolean gameOver = false;
    
    Caza miCaza = new Caza(30);

    static int ANCHOPANTALLA = 700;
    static int ALTOPANTALLA = 750;
    int ancho_columna = 79;
    int SEPARACION_COLUMNAS = ancho_columna;
    int numColumnas = 10;
    int puntuacion = 0;
    int contadorAnimacion = 0;
    //imagenes de los adornos
    Image casas, planetas;
    int posicionCasasY = 0;
    //Empiezo a crear elementos del juego
    //array de columnas
    Columna[] columnas = new Columna[numColumnas];
    //metemos los suelos
    //los dos suelos para hacer el truco de que parezca infinito
    Suelo miSuelo1 = new Suelo(0, ALTOPANTALLA * 0.60);
    Suelo miSuelo2 = new Suelo(miSuelo1.getWidth(), ALTOPANTALLA * 0.60);
    
    BufferedImage buffer = null;
    Graphics2D bufferGraphics, lienzoGraphics = null;

    //TEMPORIZADOR DEL JUEGO: AQUI SE LLAMA A LA ANIMACIÓN
    Timer temporizador = new Timer(10,new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            bucleDelJuego();
        }
    });
    
    /**
     * Creates new form Ventanajuego
     */
    public Ventanajuego() {
       initComponents();
        inicializaBuffers();
        temporizador.start();
        jDialog1.setSize(831, 586);
        for (int i=0; i<numColumnas; i++){
            columnas[i] = new Columna(ANCHOPANTALLA + i*SEPARACION_COLUMNAS, ANCHOPANTALLA);
        }
    }
    //evitamos escribir todas estas lineas de codigo cada vez que queramos meter una imagen, que son muchas.
    private Image cargaImagen(String nombreImagen, double altoImagen){
        return (new ImageIcon(new ImageIcon(getClass().getResource(nombreImagen))
                .getImage().getScaledInstance(ANCHOPANTALLA, (int) altoImagen, Image.SCALE_DEFAULT))).getImage();
    }
    //preparo los buffers para el juego
    private void inicializaBuffers(){
        lienzoGraphics = (Graphics2D) jPanel1.getGraphics();
        buffer = (BufferedImage) jPanel1.createImage(ANCHOPANTALLA, ALTOPANTALLA);
        bufferGraphics = buffer.createGraphics();
        
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA);
        //carga las imagenes de los adornos
        
        casas = cargaImagen("/imagenes/casas.png", ALTOPANTALLA*0.05);
        planetas = cargaImagen("/imagenes/Death_Star.png", ALTOPANTALLA*0.10);
        posicionCasasY = (int)(ALTOPANTALLA * 0.60)-casas.getHeight(null);
    }
    //aqui esta el verdadero funcionamiento del juego
    private void bucleDelJuego(){
        contadorAnimacion++;
        if (contadorAnimacion > 30) {contadorAnimacion = 0;}
        //limpio la pantalla
        bufferGraphics.setColor(new Color(113, 198, 205)); //el color original del flappy bird
        bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA); 
        bufferGraphics.drawImage(casas, 0,posicionCasasY, null);
        bufferGraphics.drawImage(planetas, 0,0, null);
        //dibujo el pájaro en su nueva posición
        miCaza.mueve(bufferGraphics, contadorAnimacion);
        //desplazo las columnas a la izquierda. Si alguna coincide con la posicion del pajaro, incremento en 1 el marcador
        for (int i=0; i<numColumnas; i++){
            if (columnas[i].mueve(bufferGraphics, miCaza)){
                puntuacion++;
            }
        }
        //avanza el suelo 
        miSuelo1.mueve(bufferGraphics);
        miSuelo2.mueve(bufferGraphics);
        //dibuja el marcador
        bufferGraphics.setFont(new Font("Courier New", Font.BOLD, 80)); 
        bufferGraphics.drawString(" " + puntuacion, ANCHOPANTALLA/3, 70);
        //dibuja el resultado
        lienzoGraphics.drawImage(buffer, 0,0, null);
        
        //chequea si ha chocado con alguna columna
        for (int i = 0; i < numColumnas; i++) {
            if (miCaza.chequeaColision(columnas[i])) {
                jDialog1.setVisible(true);
                temporizador.stop();
            }
        }
    }
    
  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jDialog1.getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No habrá piedad para la escoria rebelde.");
        jDialog1.getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 510, 545, 29);

        jLabel4.setFont(new java.awt.Font("DialogInput", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("¡¡ GAME OVER !!");
        jDialog1.getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 40, 435, 57);

        jButton1.setText("Salir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton1);
        jButton1.setBounds(30, 530, 62, 31);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Vader.jpg"))); // NOI18N
        jDialog1.getContentPane().add(jLabel3);
        jLabel3.setBounds(3, -1, 830, 570);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //para elevar al caza cada vez que presione la tecla del espacio.
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            miCaza.sube();
        }
    }//GEN-LAST:event_formKeyPressed
    //boton de salir que he colocado en el jDialog que sale cuando pierdes, cierra toda la aplicacion
    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        System.exit(0);
    }//GEN-LAST:event_jButton1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventanajuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventanajuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventanajuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventanajuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventanajuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
