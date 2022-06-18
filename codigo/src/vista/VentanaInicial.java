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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Juan
 */
public class VentanaInicial extends JFrame {
    //frame
    
     //Sonido
    private File archivowav;
    private Clip clip;
    private AudioInputStream audioInputStream;


    //Ruta absoluta
    private String rutaAbsoluta;

    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 500;

    //Iconos(Imagenes)
    private ImageIcon imgJugar;
    private ImageIcon imgJugarShadow;
    private ImageIcon imgComoJugar;
    private ImageIcon imgComoJugarShadow;
    private ImageIcon imgParaQueSirve;
    private ImageIcon imgParaQueSirveShadow;
    private ImageIcon imgFondo;

    //Botones
    private JButton btnJugar;
    private JButton btnParaQueSirve;
    private JButton btnComoJugar;

    //Contenedor Principal
    private Container contPrincipal;

    //Label de Fondo
    private JLabel lblFondo;

    public VentanaInicial() throws IOException {
        iniciarComponentes();
        iniciarVentana();
    }

    private void iniciarVentana() throws IOException {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa");

        Image icon = new ImageIcon(getClass().getResource("/imagenes/iconoVentana.png")).getImage();
        setIconImage(icon);

        setResizable(false);
    }

    private void iniciarComponentes() throws IOException {
        //Ruta absoluta
        rutaAbsoluta = new File("").getAbsolutePath();

        //Sonido
        reproducirSonido("inicio");
        
        //Fondo
        imgFondo = establecerIcon("\\src\\imagenes\\fondo2.png",
                anchoV, largoV);

        //Lbl fondo
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);

        //Botones//
        /*btnJugar*/
        btnJugar = new BotonSinFondo();
        btnJugar.setBounds(220, 280, 250, 150);
        //imagenes 
        imgJugar = establecerIcon("\\src\\imagenes\\imgJugar.png",
                obtenerAnchoBoton(btnJugar), obtenerAltoBoton(btnJugar));

        imgJugarShadow = establecerIcon("\\src\\imagenes\\imgJugarShadow.png",
                obtenerAnchoBoton(btnJugar), obtenerAltoBoton(btnJugar));

        btnJugar.setIcon(imgJugar);
        btnJugar.setRolloverIcon(imgJugarShadow);

        /*btnComoJugar*/
        btnComoJugar = new BotonSinFondo();
        btnComoJugar.setBounds(10, 280, 200, 130);
        //imagnes
        imgComoJugar = establecerIcon("\\src\\imagenes\\imgComoJugar.png",
                obtenerAnchoBoton(btnComoJugar), obtenerAltoBoton(btnComoJugar));
        imgComoJugarShadow = establecerIcon("\\src\\imagenes\\imgComoJugarShadow.png",
                obtenerAnchoBoton(btnComoJugar), obtenerAltoBoton(btnComoJugar));
        btnComoJugar.setIcon(imgComoJugar);
        btnComoJugar.setRolloverIcon(imgComoJugarShadow);

        /*btnParaQueSirve*/
        btnParaQueSirve = new BotonSinFondo();
        btnParaQueSirve.setBounds(470, 280, 200, 130);
        //imagens
        imgParaQueSirve = establecerIcon("\\src\\imagenes\\imgParaQueSirve.png",
                obtenerAnchoBoton(btnParaQueSirve), obtenerAltoBoton(btnParaQueSirve));
        imgParaQueSirveShadow = establecerIcon("\\src\\imagenes\\imgParaQueSirveShadow.png", obtenerAnchoBoton(btnParaQueSirve), obtenerAltoBoton(btnParaQueSirve));
        btnParaQueSirve.setIcon(imgParaQueSirve);
        btnParaQueSirve.setRolloverIcon(imgParaQueSirveShadow);

        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        //Añadiendo objetos
        contPrincipal.add(lblFondo);
        //Aadiendo objetos al lblFondo
        lblFondo.add(btnJugar);
        lblFondo.add(btnComoJugar);
        lblFondo.add(btnParaQueSirve);

        ////Añadiendo listeners
        btnJugar.addMouseListener(new ManejadorDeEventos());
        
        btnComoJugar.addMouseListener(new ManejadorDeEventos());
        
        btnParaQueSirve.addMouseListener(new ManejadorDeEventos());

    }

    //Sonido
    //Activar sonido de cierto boton
    public void reproducirSonido(String cualSonido) {
        switch (cualSonido) {
            case "boton" ->
                play("src\\sonidos\\boton.wav");
            case "inicio" ->
                play("src\\sonidos\\theLast.wav");
            default -> {
            }
        }
    }

    void play(String filePath) {
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

    //Metodos para obtener el ancho y alto de un boton en el contenedor
    private int obtenerAnchoBoton(JButton btn) {
        int ancho = (int) btn.getBounds().getWidth();
        return ancho;
    }

    private int obtenerAltoBoton(JButton btn) {
        int alto = (int) btn.getBounds().getHeight();
        return alto;
    }

    //Metodo que retorna una imagen con el ancho y alto recibido
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException {
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
    }

    //Clase manejadora de eventos
    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnJugar) {
                dispose();
                //Se abre la ventana del juego
                try {
                    reproducirSonido("boton");
                    VentanaJuego ventanaJuego = new VentanaJuego();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaInicial.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == btnComoJugar) {
                dispose();
                //Se abre la ventana del juego
                try {
                    reproducirSonido("boton");
                    ComoJugar ventanaComoJugar = new ComoJugar();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaInicial.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == btnParaQueSirve) {
                reproducirSonido("boton");
            }
        }

    }

    //Clase de boton sin fondo ni bordes
    private class BotonSinFondo extends JButton {

        public BotonSinFondo() {
            inicializar();
        }

        private void inicializar() {
            setRolloverEnabled(true);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }
    }

}
