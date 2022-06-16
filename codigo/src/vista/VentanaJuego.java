/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.sun.java.accessibility.util.AWTEventMonitor;
import controladores.Baldosas;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import logica.LogicaAdosa2;

/**
 *
 * @author Juan
 */
public class VentanaJuego extends JFrame {

    //baldosaCambiada
    private int baldosaCambiada = -1;

    //logica
    private LogicaAdosa2 logica;

    //controlador de baldosas(imagenes)
    private Baldosas imgsBaldosas;

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
    private ArrayList<JLabel> listaVidas;
    private JLabel lblVida1;
    private JLabel lblVida2;
    private JLabel lblVida3;

    //Contendero principal
    private Container contPrincipal;

    //Baldosas
    private ArrayList<JLabel> listaBaldosas;

    //Imagenes
    private ImageIcon imgFondo;
    private ImageIcon iconoBtnNorm;
    private ImageIcon iconoBtnPress;
    private ImageIcon iconoBtnRoll;

    public VentanaJuego() throws IOException {
        iniciarVentana();
        iniciarComponentes();
    }

    private void iniciarVentana() {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = new ImageIcon(getClass().getResource("/imagenes/iconoVentana.png")).getImage();
        setIconImage(icon);

        setTitle("Adosa2");
    }

    private void iniciarComponentes() throws IOException {
        //Ruta absoluta//
        rutaAbsoluta = new File("").getAbsolutePath();

        //logica
        logica = new LogicaAdosa2();

        //baldosas (controlador)
        imgsBaldosas = new Baldosas();

        //timer//
        tiempo = new Timer(1000, new ManejadorDeEventosTiempo());
        tiempo.start();

        //Fondo (provisonal)//
        imgFondo = establecerIcon("\\src\\imagenes\\fondo3.png", anchoV - 10,
                largoV - 38);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setLayout(null);

        //Labels punatje//
        lblPuntaje = new JLabel("Puntaje: 0000");
        lblPuntaje.setBounds(5, 0, 220, 50);
        lblPuntaje.setFont(new Font("Serif", Font.PLAIN, 40));

        //Labels de vidas// (temporales)
        listaVidas = new ArrayList<>();
        inicializarVidas();
        
        //Botn balnco//(temporral)
        btnBlanco = new JButton();
        btnBlanco.setBounds(520, 320, 100, 100);
        
        iconoBtnNorm = establecerIcon("\\src\\imagenes\\btnNorm.png", 100, 100);
        btnBlanco.setIcon(iconoBtnNorm);

        iconoBtnPress = establecerIcon("\\src\\imagenes\\btnPress.png", 100, 100);
        btnBlanco.setPressedIcon(iconoBtnPress);

        iconoBtnRoll = establecerIcon("\\src\\imagenes\\btnRoll.png", 100, 100);
        btnBlanco.setRolloverIcon(iconoBtnRoll);

        btnBlanco.setOpaque(true);
        btnBlanco.setBorder(null);

//        btnBlanco.setBackground(Color.WHITE);
        //Baldosas//
        listaBaldosas = new ArrayList<>();
        inicializarBaldosas();

        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(new GridLayout(1, 1));
        //Añadiendo objetos
        contPrincipal.add(lblFondo);

        lblFondo.add(lblPuntaje);
        lblFondo.add(btnBlanco);
        for (int i = 0; i < 8; i++) {
            lblFondo.add(listaBaldosas.get(i));
        }
        for (int i = 0; i < 3; i++) {
            lblFondo.add(listaVidas.get(i));
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
    private class ManejadorDeEventosMouse extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            //si se da click en el boton balnco
            if (e.getSource() == btnBlanco) {
                if (baldosasIguales(baldosaCambiada)) {
                    System.out.println("Btn balnco ooka");
                } else {
                    System.out.println("Btn blanco error");
                }
            }
        }
    }

    //clase manejadora de eventos de lteclado
    private class ManejadorDeEventosTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //Si se oprime la barra espaciadora
            if (e.getKeyCode() == 32) {
                System.out.println("falat implementar codigo");
            }
        }
    }

    //clase Manejadroa de eventos de tiempo
    private class ManejadorDeEventosTiempo implements ActionListener {

        //tiempo
        private int t = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            //se aumenta el tiempo 1 segundo
            t++;

            if (t < 4) {
                //aqui debe ir la cuenta regresiva inciial
                System.out.println(t + "");
            }

            //se cambia una baldosa cada cierto tiempo
            //y verfica si hay baldosas iguales
            if (t % 2 == 0 && t > 4) {
                //se verifica si hay baldosas iguales
                if (baldosasIguales(baldosaCambiada)) {
                    System.out.println("Baldosas iguales");
                    
                    //se pone normal la baldosa anteriroemnet ressaltada
                    listaBaldosas.get(baldosaCambiada).setBorder(null);
                    
                    //se resta una vida
                    logica.baldosasIguales();
                    quitarUnaVida();
                    
                    //se estbalcen nuevas baldosas
                    logica.nuevasBaldosasAMostrar();
                    modificarBaldosas();
                    
                    baldosaCambiada = -1;
                } else {
                    //se pone normal la baldosa anteriroemnet ressaltada
                    if(baldosaCambiada != -1){
                        listaBaldosas.get(baldosaCambiada).setBorder(null);
                    }
                    

                    //se cambia la baldosa
                    int baldosaACambiar = logica.baldosaACambiar();
                    listaBaldosas.get(baldosaACambiar).
                            setIcon(imgsBaldosas.getImgBaldosaAleatoria());
                    listaBaldosas.get(baldosaACambiar).
                            setBorder(BorderFactory.
                                    createLineBorder(Color.GREEN, 3));
                    baldosaCambiada = baldosaACambiar;
                }
            }
        }
    }

    //metodo para inciailizar las baldosas
    private void inicializarBaldosas() {
        //cordenadas de cada baldosa
        int coordenadas[][] = {{30, 180}, {140, 180}, {440, 180}, {550, 180},
        {292, 7}, {292, 108}, {292, 353}, {292, 252}};

        //Se añaden 8 baldosas
        for (int i = 0; i < 8; i++) {
            JLabel baldosa = new JLabel(imgsBaldosas.getImgBaldosa(i));
            baldosa.setBounds(coordenadas[i][0], coordenadas[i][1],
                    100, 100);
            //Se ponen visibles o no visibles degun el caso
            if (logica.baldosaAMostrar(i)) {
                baldosa.setVisible(true);
            } else {
                baldosa.setVisible(false);
            }
            this.listaBaldosas.add(baldosa);
        }
    }
    
    //metodo que iniclliza las vidas
    private void inicializarVidas() {
        int coordenadas[][] = {{480, 10}, {550, 10}, {620, 10}};
        for(int i = 0; i<3; i++){
            LblVida lblVida = new LblVida();
            lblVida.setBounds(coordenadas[i][0], coordenadas[i][1], 50, 50);
            listaVidas.add(lblVida);
        }
    }
    
    //clase de las vidas
    private class LblVida extends JLabel {

        public LblVida() {
            setOpaque(true);
            setBackground(Color.GREEN);
        }
        
    }

    //metodo que verfiica baldosas iguales(con la logica)
    private boolean baldosasIguales(int baldosaCambiada) {
        
        //VAriable que indicara si hay dos baldosas iguales
        boolean hayBaldosasIguales = false;
        if(baldosaCambiada != -1) {
            //lista auxiliar del indice de las baldosas visibles
            ArrayList<Integer> baldosasEnPantalla = logica.
                    getBaldosasAMostrar();
            //Imagen de la baldosa cambiada anteriroremenet
            Icon imgBaldosaCambiada = listaBaldosas.get(baldosaCambiada).getIcon();
            //se verifica si hay dos baldosa iguales//
            for (int i = 0; i < baldosasEnPantalla.size(); i++) {
                //se verfica que no sea la misma baldosa
                if (baldosaCambiada != baldosasEnPantalla.get(i)) {
                    Icon imgBaldosa = listaBaldosas.get(baldosasEnPantalla.get(i)).getIcon();
                    //se verfica si sus imagenes son iguales
                    if (imgBaldosaCambiada == imgBaldosa) {
                        hayBaldosasIguales = true;
                    }
                }
            }
        }

        return hayBaldosasIguales;
    }
    
    //metodo que modifica las Lblvidas si se pierde una vida
    private void quitarUnaVida(){
        listaVidas.get(logica.getVidas()).setBackground(Color.red);
    }
    
    //metodo que modificala visibilidad de las badldosas segun el caso
    private void modificarBaldosas(){
        //Se recorre cda baldosa
        for (int i = 0; i < 8; i++) {
            //Se ponen visibles o no visibles degun el caso
            if (logica.baldosaAMostrar(i)) {
                listaBaldosas.get(i).setVisible(true);
            } else {
                listaBaldosas.get(i).setVisible(false);
            }
            listaBaldosas.get(i).setIcon(imgsBaldosas.getImgBaldosa(i));
        }
    }

}
