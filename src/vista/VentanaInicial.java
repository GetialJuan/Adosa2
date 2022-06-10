/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private ImageIcon imgJugarShadow;
    private ImageIcon imgComoJugar;
    private ImageIcon imgComoJugarShadow;
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
        setSize(700,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa2");
    }
    
    private void iniciarComponentes() throws IOException{
        //Ruta absoluta
        rutaAbsoluta = new File("").getAbsolutePath();
        
        //Fondo
        imgFondo = establecerIcon("\\src\\imagenes\\fondo.jpg",
                700, 600);
        
        //Lbl fondo
        lblFondo= new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, 700, 600);
        
        //Botones//
        
        /*btnJugar*/
        btnJugar = new BotonImg();
        btnJugar.setBounds(220, 280, 250, 150);
            //imagenes 
        imgJugar = establecerIcon("\\src\\imagenes\\imgJugar.png",
                (int) btnJugar.getBounds().getWidth(), (int) btnJugar.
                        getBounds().getHeight());
        imgJugarShadow = establecerIcon("\\src\\imagenes\\imgJugarShadow.png",
                (int) btnJugar.getBounds().getWidth(), (int) btnJugar.
                        getBounds().getHeight());
        btnJugar.setIcon(imgJugar);
        btnJugar.setRolloverIcon(imgJugarShadow);
        
        /*btnComoJugar*/
        btnComoJugar = new BotonImg();
        btnComoJugar.setBounds(10, 280, 200, 130);
            //imagnes
        imgComoJugar = establecerIcon("\\src\\imagenes\\imgComoJugar.png",
                (int) btnComoJugar.getBounds().getWidth(), (int) btnComoJugar.
                        getBounds().getHeight());
        imgComoJugarShadow = establecerIcon("\\src\\imagenes\\imgComoJugarShadow.png",
                (int) btnComoJugar.getBounds().getWidth(), (int) btnComoJugar.
                        getBounds().getHeight());
        btnComoJugar.setIcon(imgComoJugar);
        btnComoJugar.setRolloverIcon(imgComoJugarShadow);
        
        //Contenedor Principal
        contContenedorPrincipal = getContentPane();
        contContenedorPrincipal.setLayout(null);
            //Añadiendo objetos
        contContenedorPrincipal.add(lblFondo);
        //Ñadiendo objetos al lblFondo
        lblFondo.add(btnJugar);
        lblFondo.add(btnComoJugar);
        
        ////Añadiendo listeners
        btnJugar.addMouseListener(new ManejadorDeEventos());
        
    }
    
    //Metodo que retorna una imagen con el ancho y alto recibido
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException
    {
        BufferedImage bufferedImagen = ImageIO.read
        (new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }
    
    //Clase manejadora de eventos
    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
        }
        
    }
    
    //Clase de boton sin fondo ni bordes con imagen
    private class BotonImg extends JButton{
        public BotonImg(){
            setRolloverEnabled(true);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }
    }
        

}
