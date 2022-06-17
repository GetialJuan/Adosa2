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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import logica.LogicaAdosa2;

/**
 *
 * @author Juan
 */
public class VentanaJuego extends JFrame {

    //Musica
    private Clip music = null;
    private long clipTime;
    private int contador = 0;

    // label volumen
    JLabel lblVolumen;

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
    
    //labelContador
    private JLabel lblContador;

    //Botones
    private JButton btnBlanco;

    //Label puntaje
    private JLabel lblPuntaje;

    //Labels vidas
    private ArrayList<JLabel> listaVidas;

    //Contendero principal
    private Container contPrincipal;

    //Baldosas
    private ArrayList<JLabel> listaBaldosas;

    //Imagenes
    private ImageIcon imgFondo;
    private ImageIcon iconoBtnNorm;
    private ImageIcon iconoBtnPress;
    private ImageIcon iconoBtnRoll;
    private ImageIcon volumeOn;
    private ImageIcon volumeOff;
    private ImageIcon volumeOn2;
    private ImageIcon volumeOff2;

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

        //Establecemos los iconos de volumen
        volumeOff = establecerIcon("\\src\\imagenes\\volumeOff.png", 70, 70);
        volumeOn = establecerIcon("\\src\\imagenes\\volumeOn.png", 70, 70);
        volumeOff2 = establecerIcon("\\src\\imagenes\\volumeOff2.png", 70, 70);
        volumeOn2 = establecerIcon("\\src\\imagenes\\volumeOn2.png", 70, 70);

        //Inicializamos la etiqueta Volumen
        lblVolumen = new JLabel("");
        lblVolumen.setBounds(50, 340, 70, 70);
        lblVolumen.setIcon(volumeOff);
        lblVolumen.addMouseListener(new ManejadorDeVolumen());

        lblVolumen.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolumen.setForeground(Color.WHITE);

        inicializarVolumen();

        //logica
        logica = new LogicaAdosa2();

        //baldosas (controlador)
        imgsBaldosas = new Baldosas();

        //timer//
        tiempo = new Timer(100, new ManejadorDeEventosTiempo());
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
        
        //lblContador//
        lblContador = new JLabel();
        lblContador.setBounds(300, 100, 300, 250);
        lblContador.setBorder(null);

        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(new GridLayout(1, 1));
        //Añadiendo objetos
        contPrincipal.add(lblFondo);
        
        lblFondo.add(lblPuntaje);
        lblFondo.add(lblContador);
        lblFondo.add(btnBlanco);
        //se añaden las baldosas
        for (int i = 0; i < 8; i++) {
            lblFondo.add(listaBaldosas.get(i));
        }
        for (int i = 0; i < 3; i++) {
            lblFondo.add(listaVidas.get(i));
        }

        lblFondo.add(lblVolumen);

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
                    acierto();

                } else {
                    try {
                        falloCometido();
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                if (baldosasIguales(baldosaCambiada)) {
                    acierto();

                } else {
                    try {
                        falloCometido();
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void inicializarVolumen() {
        if (contador == 0) {
            try {
                music = AudioSystem.getClip();
                music.open(AudioSystem.getAudioInputStream(new File("src/sonidos/47.wav")));
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException a) {
                a.printStackTrace();
            }
        }
        music.start();
    }

    //Activar cierto sonido
    public void reproducirSonido(String cualSonido) {
        switch (cualSonido) {
            case "botonA" ->
                play("src\\sonidos\\blancoAcierto.wav");
            case "botonD" ->
                play("src\\sonidos\\blancoDesacierto.wav");
            case "perder" ->
                play("src\\sonidos\\perder.wav");

            default -> {
            }
        }
    }

    //Reproducir sonido
    void play(String filePath) {
        try {
            Clip sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File(filePath)));
            sonido.start();
            int delay2 = 3000;
            Timer timerAux;
            timerAux = new Timer(delay2, e -> {
                sonido.close();
            });
            timerAux.setRepeats(false);
            timerAux.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("" + e);
        }
    }

    //clase manejadora de volumen
    private class ManejadorDeVolumen extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
//            inicializarVolumen();
            if (lblVolumen.getIcon().equals(volumeOn2) || lblVolumen.getIcon().equals(volumeOn)) {
                lblVolumen.setIcon(volumeOff);
                music.start();
                contador++;
            } else {
                lblVolumen.setIcon(volumeOn);
                clipTime = music.getMicrosecondPosition();
                music.stop();
                music.setMicrosecondPosition(clipTime);
                contador++;
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (lblVolumen.getIcon().equals(volumeOff2)) {
                lblVolumen.setIcon(volumeOff);

            } else if (lblVolumen.getIcon().equals(volumeOn2)) {
                lblVolumen.setIcon(volumeOn);

            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (lblVolumen.getIcon().equals(volumeOff)) {
                lblVolumen.setIcon(volumeOff2);
            } else if (lblVolumen.getIcon().equals(volumeOn)) {
                lblVolumen.setIcon(volumeOn2);
                
            }

        }

    }

    //clase Manejadroa de eventos de tiempo
    private class ManejadorDeEventosTiempo implements ActionListener {

        //tiempo
        private double t = 0;

        //var que indica si se hace la cuenta regresiva
        private boolean cuentaRegresiva = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            //se aumenta el tiempo 1 segundo
            t += 0.1;
            if (cuentaRegresiva) {
                if (t < 4) {
                    if (0 <= t && t < 1) {
                        try {
                            lblContador.setIcon
                (establecerIcon("\\src\\imagenes\\imgNum1.png", 100, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaJuego.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    } else if (1 <= t && t < 2) {
                        try {
                            lblContador.setIcon
                (establecerIcon("\\src\\imagenes\\imgNum2.png", 100, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaJuego.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    } else if (2 <= t && t < 3) {
                        try {
                            lblContador.setIcon
                (establecerIcon("\\src\\imagenes\\imgNum3.png", 100, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaJuego.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    } else {
                        lblContador.setBounds(200, 100, 300, 250);
                        try {
                            lblContador.setIcon
                (establecerIcon("\\src\\imagenes\\imgYa.png", 300, 250));
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaJuego.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    //se quita el contador
                    lblContador.setVisible(false);
                    //se visibilizan las baldosas
                    modificarBaldosas();
                    cuentaRegresiva = false;
                    t = 0;
                }
            } else {
                //se cambia una baldosa cada cierto tiempo
                //y verfica si hay baldosas iguales
                if (t > logica.getTiempoDeCambio()) {
                    //se reinicia el tiempo
                    t = 0;

                    //se verifica si hay baldosas iguales
                    if (baldosasIguales(baldosaCambiada)) {
                        try {
                            falloCometido();
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        //se pone normal la baldosa anteriroemnet ressaltada
                        if (baldosaCambiada != -1) {
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
            baldosa.setVisible(false);
            this.listaBaldosas.add(baldosa);
        }
    }

    //metodo que iniclliza las vidas
    private void inicializarVidas() {
        int coordenadas[][] = {{480, 10}, {550, 10}, {620, 10}};
        for (int i = 0; i < 3; i++) {
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
        if (baldosaCambiada != -1) {
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
    private void quitarUnaVida() {
        if (logica.getVidas() > 0) {
            listaVidas.get(logica.getVidas()).setBackground(Color.red);
        }

    }

    //metodo que modificala visibilidad de las badldosas segun el caso
    private void modificarBaldosas() {
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

    //metodo que realiza las acciones correspondientes al cometer un fallo
    private void falloCometido() throws IOException {
        if (logica.getVidas() != 1) {
            reproducirSonido("botonD");
        } else {
            reproducirSonido("perder");
        }

        //se pone normal la baldosa anteriroemnet ressaltada
        if (baldosaCambiada != -1) {
            listaBaldosas.get(baldosaCambiada).setBorder(null);
        }
        baldosaCambiada = -1;

        //se resta una vida
        logica.baldosasIguales();
        quitarUnaVida();

        //se añade un error
        logica.aumentarErrores();

        //se aumenta el tiempo de cambio
        logica.aumentarTiempoDeCambio();

        //se verfica si quedan vidas
        if (logica.getVidas() > 0) {
            //se estbalcen nuevas baldosas
            logica.nuevasBaldosasAMostrar();
            modificarBaldosas();
        } else {
            tiempo.stop();
            dispose();

            VentanaFinal ventanaFinal = new VentanaFinal(this.logica);

            music.stop();
        }

    }

    //acciones a realizar cuando el jugador acierte
    private void acierto() {
        reproducirSonido("botonA");

        //se pone normal la baldosa anteriroemnet ressaltada
        listaBaldosas.get(baldosaCambiada).setBorder(null);

        //se suma el puntaje
        logica.aumentarPuntaje();
        logica.aumentarPuntajeASumar();
        lblPuntaje.setText("Puntaje: " + logica.getPuntaje());

        //se añade un acierto
        logica.aumentarAciertos();

        //se reduce el tiempo de cambio
        logica.disminuirTiempoDeCambio();

        //se estbalcen nuevas baldosas
        logica.aumentarBaldosasAMostrar();
        logica.nuevasBaldosasAMostrar();
        modificarBaldosas();

        baldosaCambiada = -1;
    }

}
