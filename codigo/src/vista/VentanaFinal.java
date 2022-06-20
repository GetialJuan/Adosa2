/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

    //btn
    private JButton btnJugar;

    //labelFondo
    private JLabel lblFondo;

    //labels
    private JLabel lblImgPuntaje;
    private JLabel lblImgErrores;
    private JLabel lblImgAciertos;
    private JLabel lblFinDelJuego;

    private JLabel lblPuntaje;
    private JLabel lblErrores;
    private JLabel lblAciertos;

    //contenedor principal
    private Container contPrincipal;

    private LogicaAdosa2 logica;

    public VentanaFinal(LogicaAdosa2 logica) throws IOException {
        ancho = 700;
        largo = 500;

        this.logica = logica;

        setSize(ancho, largo);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa");
        setResizable(false);

        inciarComponentes();
    }

    private void inciarComponentes() throws IOException {
        //Ruta absoluta//
        rutaAbsoluta = new File("").getAbsolutePath();

        //lblFondo//
        lblFondo = new JLabel(establecerIcon("\\src\\imagenes\\fondo.jpg",
                ancho, largo));
        lblFondo.setBounds(0, 0, ancho, largo);

        //lbls (imgs)//
        lblFinDelJuego = new JLabel(establecerIcon("\\src\\imagenes\\imgFinDelJuego.png", ancho - 50, 90));
        lblFinDelJuego.setBounds(20, 30, ancho - 50, 90);

        lblImgAciertos = new JLabel(establecerIcon("\\src\\imagenes\\imgAciertos.png", 300, 75));
        lblImgAciertos.setBounds(100, 130, 300, 75);

        lblImgErrores = new JLabel(establecerIcon("\\src\\imagenes\\imgErrores.png", 300, 75));
        lblImgErrores.setBounds(100, 210, 300, 75);

        lblImgPuntaje = new JLabel(establecerIcon("\\src\\imagenes\\imgPuntaje.png", 300, 80));
        lblImgPuntaje.setBounds(100, 290, 300, 80);

        //lbls (texto estadisiticas)//
        lblPuntaje = new JLabel(logica.getPuntaje() + "");
        lblPuntaje.setFont(new Font("Serif", Font.PLAIN, 60));
        lblPuntaje.setBounds(420, 305, 220, 50);

        lblAciertos = new JLabel(logica.getAciertos() + "");
        lblAciertos.setFont(new Font("Serif", Font.PLAIN, 60));
        lblAciertos.setBounds(420, 145, 220, 50);

        lblErrores = new JLabel(logica.getErrores() + "");
        lblErrores.setFont(new Font("Serif", Font.PLAIN, 60));
        lblErrores.setBounds(420, 228, 220, 50);

        //btnJugar//
        btnJugar = new JButton(establecerIcon("\\src\\imagenes\\imgJugar.png", 300, 75));
        btnJugar.setBounds(200, 380, 300, 75);
        btnJugar.setRolloverEnabled(true);
        btnJugar.setFocusPainted(false);
        btnJugar.setBorderPainted(false);
        btnJugar.setContentAreaFilled(false);
        btnJugar.setRolloverIcon(establecerIcon("\\src\\imagenes\\imgJugarShadow.png", 300, 75));

        btnJugar.addActionListener(new ManejadorDeEventos());

        //contPrincipal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);
        contPrincipal.add(lblFondo);

        lblFondo.add(lblPuntaje);
        lblFondo.add(lblAciertos);
        lblFondo.add(lblErrores);

        lblFondo.add(lblFinDelJuego);
        lblFondo.add(lblImgAciertos);
        lblFondo.add(lblImgErrores);
        lblFondo.add(lblImgPuntaje);

        lblFondo.add(btnJugar);

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

    //clas amjenadorea de eventos (btnJugar)
    private class ManejadorDeEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            try {
                VentanaInicial ventanaInicial = new VentanaInicial(0);
            } catch (IOException ex) {
                Logger.getLogger(VentanaFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
