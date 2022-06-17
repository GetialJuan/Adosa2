/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import logica.LogicaAdosa2;

/**
 *
 * @author Juan
 */
public class VentanaFinal extends JFrame {
    //rutaAbsoluta
    private String rutaAbsoluta;
    
    //dimensiones de la ventana
    private int ancho;
    private int largo;
    
    //labelFondo
    private JLabel lblFondo;
    
    //labels
    private JLabel lblImgPuntaje;
    private JLabel lblImgErrores;
    private JLabel lblImgAciertos;
    
    private JLabel lblPuntaje;
    private JLabel lblErrores;
    private JLabel lblAciertos;
    
    //contenedor principal
    private Container contPrincipal;
    
    private LogicaAdosa2 logica;
    
    public VentanaFinal(LogicaAdosa2 logica) throws IOException{
        ancho = 700;
        largo = 500;
        
        this.logica = logica;
        
        setSize(ancho, largo);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa");
        
        inciarComponentes();
    }
    
    private void inciarComponentes() throws IOException {
        //Ruta absoluta//
        rutaAbsoluta = new File("").getAbsolutePath();
        
        //lblFondo//
        lblFondo = new JLabel(establecerIcon("\\src\\imagenes\\fondo3.png", 
                ancho, largo));
        lblFondo.setBounds(0, 0, ancho, largo);
        
        //lbls//
        lblPuntaje = new JLabel(logica.getPuntaje()+"");
        lblPuntaje.setFont(new Font("Serif", Font.PLAIN, 40));
        lblPuntaje.setBounds(100, 100, 220, 50);
        
        lblAciertos = new JLabel(logica.getAciertos()+"");
        lblAciertos.setFont(new Font("Serif", Font.PLAIN, 40));
        lblAciertos.setBounds(100, 200, 220, 50);
        
        lblErrores = new JLabel(logica.getErrores()+"");
        lblErrores.setFont(new Font("Serif", Font.PLAIN, 40));
        lblErrores.setBounds(100, 300, 220, 50);
        
        //contPrincipal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        contPrincipal.add(lblFondo);
        
        lblFondo.add(lblPuntaje);
        lblFondo.add(lblAciertos);
        lblFondo.add(lblErrores);
        
    }
    
    //Metodo que retorna una imagen con el ancho y alto recibido
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException {
        BufferedImage bufferedImagen = ImageIO.
                read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }
}
