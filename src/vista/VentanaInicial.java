/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Juan
 */
public class VentanaInicial extends JFrame {
    //Ruta absoluta
    private String rutaAbsoluta;
    
    //Iconos(Imagenes)
    private ImageIcon imgJugar;
    private ImageIcon imgFondo;
    
    //Botones
    private JButton btnJugar;
    private JButton btnParaQueSirve;
    private JButton btnComoJugar;
    
    //Contenedor Pirncipal
    private Container contContenedorPrincipal;
    
    //Label de Fondo
    private JLabel lblFondo;
    
    public VentanaInicial() throws IOException {
        iniciarComponentes();
        setSize(700,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa2");
    }
    
    private void iniciarComponentes() throws IOException{
        //Ruta absoluta
        rutaAbsoluta = new File("").getAbsolutePath();
        
        //Icons(imagenes)
        imgJugar = establecerIcon("\\src\\imagenes\\imgJugarShadow.png",
                200, 100);
        
        imgFondo = establecerIcon("\\src\\imagenes\\fondo.jpg",
                700, 600);
        
        //Lbl fondo
        lblFondo= new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, 700, 600);
        
        //Botones
        btnJugar = new JButton(imgJugar);
        btnJugar.setFocusPainted(false);
        btnJugar.setBorderPainted(false);
        btnJugar.setContentAreaFilled(false);
        btnJugar.setBounds(250, 400, 200, 100);
        
        //Contenedor Principal
        contContenedorPrincipal = getContentPane();
        contContenedorPrincipal.setLayout(null);
            //Añadiendo objetos
        contContenedorPrincipal.add(lblFondo);
        //Ñadiendo objetos al lblFondo
        lblFondo.add(btnJugar);
    }
    
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException
    {
        BufferedImage bufferedImagen = ImageIO.read
        (new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }
        

}
