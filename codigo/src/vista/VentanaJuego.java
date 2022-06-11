/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Juan
 */
public class VentanaJuego extends JFrame {
    //timer
    private Timer tiempo;
    
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
    
    //Baldosas
    private ArrayList<JLabel> listaBaldosas;
    
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
        
        //timer//
        tiempo = new Timer(1000, new ManejadorDeEventosTiempo());
        tiempo.start();
        
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
        
        //Baldosas//
        listaBaldosas = new ArrayList<>();
        inicializarBaldosas();
        
        
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
        for(int i = 0; i<8; i++){
            lblFondo.add(listaBaldosas.get(i));
        }
        
        //Añadiendo listenrs//
        btnBlanco.addMouseListener(new ManejadorDeEventosMouse());
        btnBlanco.addKeyListener(new ManejadorDeEventosTeclado());
        
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
    
    //clasee manejadora de eventos del mouse
    private class ManejadorDeEventosMouse extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            //si se da click en el boton balnco
            if(e.getSource() == btnBlanco)
            {
                System.out.println("falat implementar codigo");
            }
        }
    }
    
    //clase manejadora de eventos de lteclado
    private class ManejadorDeEventosTeclado extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            //Si se oprime la barra espaciadora
            if(e.getKeyCode() == 32){
                System.out.println("falat implementar codigo");
            }
        }
    }
    
    //clase Manejadroa de eventos de tiempo
    private class ManejadorDeEventosTiempo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("action performed");
        }
    }
    
    //metodo para inciailizar las baldosas
    private void inicializarBaldosas(){
        int coordenadas[][] = { {30,170}, {160,170}, {420,170}, {550,170},
        {300,10}, {300,120}, {300,350}, {300,240} };
        
        for(int i = 0; i<8; i++){
            JLabel baldosa = new JLabel();
            baldosa.setOpaque(true);
            baldosa.setBackground(Color.red);
            baldosa.setBounds(coordenadas[i][0], coordenadas[i][1], 
                    100, 100);
            this.listaBaldosas.add(baldosa);
        }
    }
    
}
