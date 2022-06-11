/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Juan
 */
public class VentanaJuego extends JFrame {
    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 500;
    
    //Ruta absoluta
    private String rutaAbsoluta;
    
    //Fondo lbl
    private JLabel lblFondo;
    
    //Botones
    private JButton btnBlanco;
    
    //Label puntaje
    private JLabel lblPuntaje;
    
    //Labels vidas
    private JLabel lblVida1;
    private JLabel lblVida2;
    private JLabel lblVida3;
    
    //Contendero principal
    private Container contPrincipal;
    
    //Imagenes
    private ImageIcon imgFondo;
    
    public VentanaJuego() throws IOException {
        iniciarComponentes();
        setSize(anchoV,largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa2");
    }
    
    private void iniciarComponentes() throws IOException{
        //Ruta absoluta//
        rutaAbsoluta = new File("").getAbsolutePath();
        
        //Fondo (provisonal)//
        imgFondo = establecerIcon("\\src\\imagenes\\fondo.jpg", anchoV, largoV);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);
        
        //Labels punatje//
        lblPuntaje = new JLabel("Puntaje: 0000");
        lblPuntaje.setBounds(5, 0, 220, 50);
        lblPuntaje.setFont(new Font("Serif", Font.PLAIN, 40));
        
        //Labels de vidas// (temporales)
        lblVida1 = new JLabel();
        lblVida1.setBounds(480, 10, 50, 50);
        lblVida1.setOpaque(true);
        lblVida1.setBackground(Color.GREEN);
        
        lblVida2 = new JLabel();
        lblVida2.setBounds(550, 10, 50, 50);
        lblVida2.setOpaque(true);
        lblVida2.setBackground(Color.GREEN);
        
        lblVida3 = new JLabel();
        lblVida3.setBounds(620, 10, 50, 50);
        lblVida3.setOpaque(true);
        lblVida3.setBackground(Color.GREEN);
        
        //Botn balnco//(temporral)
        btnBlanco = new JButton();
        btnBlanco.setBounds(520, 320, 100, 100);
        btnBlanco.setOpaque(true);
        btnBlanco.setBackground(Color.WHITE);
        
        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        //Añadiendo objetos
        contPrincipal.add(lblFondo);
        
        lblFondo.add(lblPuntaje);
        lblFondo.add(lblVida1);
        lblFondo.add(lblVida2);
        lblFondo.add(lblVida3);
        lblFondo.add(btnBlanco);
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
