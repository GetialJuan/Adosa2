/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controladores.Baldosas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import logica.LogicaAdosa2;

/**
 *
 * @author carlos
 */
public class ComoJugar extends JFrame {

    //Sonido
    private File archivowav;
    private Clip clip;
    private AudioInputStream audioInputStream;

    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 500;

    //Ruta absoluta
    private String rutaAbsoluta;

    //Fondo lbl
    private JLabel lblFondo;

    //Imagen Ejemplo
    private JLabel lblImagen;

    //Botones
    private JButton btnSiguiente;
    private JButton btnAtras;

    //Numero ventana
    private JLabel txtNumeroVentana;
    private int numVentana = 1;

    //Label mensaje
    private JLabel lblLinea1;
    private JLabel lblLinea2;
    private JLabel lblLinea3;
    private JLabel lblLinea4;
    private JLabel lblLinea5;

    //Label flecha
    private JLabel lblFlecha;

    //JButton salir
    private JButton btnSalir;

    //Contendero principal
    private Container contPrincipal;

    //Imagenes
    private ImageIcon imgFondo;
    private ImageIcon iconoFlecha;
    private ImageIcon iconoSalir;
    private ImageIcon iconoSalir2;
    private ImageIcon imgEjemplo;
    private ImageIcon iconoSiguiente;
    private ImageIcon iconoSiguiente2;
    private ImageIcon iconoAtras;
    private ImageIcon iconoAtras2;

    public ComoJugar() throws IOException {
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

        reproducirSonido(1);

        //Ruta absoluta
        rutaAbsoluta = new File("").getAbsolutePath();

        //Fondo //
        imgFondo = establecerIcon("\\src\\imagenes\\fondoComoJugar.png", anchoV,
                largoV);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);
        lblFondo.setLayout(null);

        //Numero Ventana
        txtNumeroVentana = new JLabel("1/4");
        txtNumeroVentana.setFont(new Font("Tahoma", Font.BOLD, 24));
        txtNumeroVentana.setBounds(35, 20, 50, 30);

        //Imagen Ejemplo
        imgEjemplo = establecerIcon("\\src\\imagenes\\comoJugar1.png", 350, 270);
        lblImagen = new JLabel(imgEjemplo);
        lblImagen.setBounds(175, 20, 350, 270);

        //JLabel Siguiente
        iconoSiguiente = establecerIcon("\\src\\imagenes\\siguiente.png", 130, 40);
        iconoSiguiente2 = establecerIcon("\\src\\imagenes\\siguiente2.png", 130, 40);

        btnSiguiente = new JButton(iconoSiguiente);
        btnSiguiente.setRolloverIcon(iconoSiguiente2);
        btnSiguiente.setPressedIcon(iconoSiguiente2);
        btnSiguiente.setBounds(536, 140, 130, 40);

        //Boton Atras
        iconoAtras = establecerIcon("\\src\\imagenes\\atras.png", 130, 40);
        iconoAtras2 = establecerIcon("\\src\\imagenes\\atras2.png", 130, 40);

        btnAtras = new JButton(iconoAtras);
        btnAtras.setRolloverIcon(iconoAtras2);
        btnAtras.setPressedIcon(iconoAtras2);
        btnAtras.setBounds(20, 140, 130, 40);
        btnAtras.setVisible(false);

        //Flecha
        iconoFlecha = establecerIcon("\\src\\imagenes\\flecha.png", 70, 70);
        lblFlecha = new JLabel(iconoFlecha);
        lblFlecha.setBounds(509, 200, 70, 70);
        lblFlecha.setVisible(false);

        //Salir
        iconoSalir = establecerIcon("\\src\\imagenes\\salir.png", 70, 70);
        iconoSalir2 = establecerIcon("\\src\\imagenes\\salir2.png", 70, 70);

        btnSalir = new JButton(iconoSalir);
        btnSalir.setRolloverIcon(iconoSalir2);
        btnSalir.setPressedIcon(iconoSalir2);
        btnSalir.setBounds(600, 20, 70, 70);

        //Mensaje
        lblLinea1 = new JLabel();
        lblLinea1.setForeground(new Color(0, 0, 0));
        lblLinea1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLinea1.setText("En Adosa2 aparecen en pantalla una serie de baldosas.");
        lblLinea1.setAlignmentX(CENTER_ALIGNMENT);
        lblLinea1.setBounds(130, 320, 700, 20);

        lblLinea2 = new JLabel();
        lblLinea2.setForeground(new Color(0, 0, 0));
        lblLinea2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLinea2.setText("Las baldosas van cambiando de 1 en 1 mostrando");
        lblLinea2.setAlignmentX(CENTER_ALIGNMENT);
        lblLinea2.setBounds(130, 345, 700, 20);

        lblLinea3 = new JLabel();
        lblLinea3.setForeground(new Color(0, 0, 0));
        lblLinea3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLinea3.setText("distintos diseños.");
        lblLinea3.setAlignmentX(CENTER_ALIGNMENT);
        lblLinea3.setBounds(130, 370, 700, 20);

        lblLinea4 = new JLabel();
        lblLinea4.setForeground(new Color(0, 0, 0));
        lblLinea4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLinea4.setText("Podrás saber qué baldosas cambia en cada momento");
        lblLinea4.setAlignmentX(CENTER_ALIGNMENT);
        lblLinea4.setBounds(130, 395, 700, 20);

        lblLinea5 = new JLabel();
        lblLinea5.setForeground(new Color(0, 0, 0));
        lblLinea5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLinea5.setText("gracias a un reborde de color azul.");
        lblLinea5.setAlignmentX(CENTER_ALIGNMENT);
        lblLinea5.setBounds(130, 420, 700, 20);

        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        //Añadiendo objetos
        contPrincipal.add(lblFondo);

        //Añadiendo objetos al lblFondo
        lblFondo.add(lblLinea1);
        lblFondo.add(lblLinea2);
        lblFondo.add(lblLinea3);
        lblFondo.add(lblLinea4);
        lblFondo.add(lblLinea5);
        lblFondo.add(btnSalir);
        lblFondo.add(btnSiguiente);
        lblFondo.add(btnAtras);
        lblFondo.add(txtNumeroVentana);
        lblFondo.add(lblFlecha);
        lblFondo.add(lblImagen);

        //Eventos
        btnSiguiente.addMouseListener(new ManejadorDeEventos());
        btnAtras.addMouseListener(new ManejadorDeEventos());
        btnSalir.addMouseListener(new ManejadorDeEventos());

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

    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnSiguiente) {
                switch (numVentana) {
                    case 1 ->
                        iniciarVentana2();
                    case 2 ->
                        iniciarVentana3();
                    case 3 ->
                        iniciarVentana4();
                    default -> {
                    }
                }
            } else if (e.getSource() == btnSalir) {
                dispose();
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
                try {
                    reproducirSonido(0);
                    VentanaInicial ventanaInicial = new VentanaInicial();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaInicial.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == btnAtras) {
                switch (numVentana) {
                    case 2 ->
                        iniciarVentana1();
                    case 3 ->
                        iniciarVentana2();
                    case 4 ->
                        iniciarVentana3();
                    default -> {
                    }
                }
            }
        }

    }

    private void iniciarVentana1() {
        numVentana = 1;

        txtNumeroVentana.setText("1/4");

        reproducirSonido(1);

        try {
            imgEjemplo = establecerIcon("\\src\\imagenes\\comoJugar1.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(false);

        btnAtras.setVisible(false);
        lblLinea1.setText("En Adosa2 aparecen en pantalla una serie de baldosas.");
        lblLinea2.setText("Las baldosas van cambiando de 1 en 1 mostrando");
        lblLinea3.setText("distintos diseños.");
        lblLinea4.setText("Podrás saber qué baldosas cambia en cada momento");
        lblLinea4.setVisible(true);
        lblLinea5.setText("gracias a un reborde de color azul.");
        lblLinea5.setVisible(true);
    }

    private void iniciarVentana2() {
        numVentana = 2;

        txtNumeroVentana.setText("2/4");

        reproducirSonido(2);

        try {
            imgEjemplo = establecerIcon("\\src\\imagenes\\comoJugar2.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(true);

        btnAtras.setVisible(true);

        lblLinea1.setText("En el momento en el que veas en pantalla 2 baldosas");
        lblLinea2.setText("idénticas, debes presionar rápidamente el pulsador");
        lblLinea3.setText("blanco que aparece en la zona inferior derecha");
        lblLinea4.setText("");
        lblLinea4.setVisible(false);
        lblLinea5.setText("");
        lblLinea5.setVisible(false);

    }

    private void iniciarVentana3() {
        numVentana = 3;

        txtNumeroVentana.setText("3/4");

        reproducirSonido(3);

        try {
            imgEjemplo = establecerIcon("\\src\\imagenes\\comoJugar3.png", 350, 270);
            lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(false);

        lblLinea1.setText("Hay dos formas de presionar el pulsador: ");
        lblLinea2.setText("- Pulsar directamente el botón blanco en pantalla");
        lblLinea3.setText("- Pulsar la barra de espacio del teclado");
        lblLinea4.setText("");
        lblLinea4.setVisible(false);
        lblLinea5.setText("");
        lblLinea5.setVisible(false);
    }

    private void iniciarVentana4() {
        numVentana = 4;

        txtNumeroVentana.setText("4/4");

        reproducirSonido(4);

        try {
            imgEjemplo = establecerIcon("\\src\\imagenes\\comoJugar4.png", 350, 270);
          lblImagen.setIcon(imgEjemplo);
            lblFondo.add(lblImagen);
        } catch (IOException e) {
            System.out.println("No se encontro la imagen de fondo en Como Jugar");
        }

        lblFlecha.setVisible(true);
        lblFlecha.setBounds(509, 20, 70, 70);
        lblFondo.add(lblFlecha);
        lblFondo.add(lblImagen);

        lblLinea1.setText("¡OJO! Si no pulsas a tiempo perderás vidas. A medida");
        lblLinea2.setText("que el juego avanza el ritmo al que cambian las");
        lblLinea3.setText("baldosas es mayor y tu tiempo de reaccción es menor");
        lblLinea4.setText("   ");
        lblLinea4.setVisible(true);
        lblLinea5.setText("¡Ojo avizor y encuentra tus Adosa2!");
        lblLinea5.setVisible(true);
    }

    private void iniciarSonido(String filePath) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        archivowav = new File(filePath);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(archivowav);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            System.err.println(e.getMessage());
        }
    }

    public void reproducirSonido(int ventana) {
        switch (ventana) {
            case 0 ->
                iniciarSonido("src\\sonidos\\boton.wav");
            case 1 ->
                iniciarSonido("src\\sonidos\\tuto1.wav");
            case 2 ->
                iniciarSonido("src\\sonidos\\tuto2.wav");
            case 3 ->
                iniciarSonido("src\\sonidos\\tuto3.wav");
            case 4 ->
                iniciarSonido("src\\sonidos\\tuto4.wav");
            default -> {
            }
        }
    }

}
