/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Juan
 */
public class VentanaParaQueSirve extends JFrame {
    //ruta absoluta
    private String rutaAbsoluta;
    
    //ancho y largo de la ventana
    private int anchoV;
    private int largoV;
    
    //fondo
    private JLabel lblFondo;
    
    //contendero principal
    private Container contPrincipal;
    
    //jbuton
    private JButton btnSalir;
    
    //texto
    private JTextPane txtTexto;
    
    public VentanaParaQueSirve() throws IOException {
        iniciarComponentes();
        iniciarVentana();
    }
    
    private void iniciarVentana() {
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
        
        //ancho y largo
        anchoV = 700;
        largoV = 500;
        
        //btnSalir
        btnSalir = new BotonSinFondo();
        btnSalir.setIcon(establecerIcon
        ("\\src\\imagenes\\salir.png", 80, 80));
        btnSalir.setRolloverEnabled(true);
        btnSalir.setRolloverIcon(establecerIcon
        ("\\src\\imagenes\\salir2.png", 80, 80));
        btnSalir.setBounds(560, 20, 80, 80);
        btnSalir.addActionListener(new ManejadorDeEventos());
        
        //dfondo
        lblFondo = new JLabel(establecerIcon
        ("\\src\\imagenes\\fondoComoJugar.png", anchoV, largoV));
        
        //texto
        txtTexto = new JTextPane();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attribs, "Tahoma");
        StyleConstants.setFontSize(attribs, 30);
        txtTexto.setParagraphAttributes(attribs,true);
        txtTexto.setText("Este juego pone en accion la habilidad para comparar "
                + "patrones visuales, entrenando ademas la atencion a los "
                + "detalles y la velocidad perceptiva. Estas capacidades son "
                + "relevantes cuando hay que decidir entre estimulossemejantes "
                + "y hay que hacerlo de forma rapida, por ejemplo al reconocer "
                + "fotografias, caras, objetos cotidianos o palabras escritas.");
        txtTexto.setBounds(10, 100, 670, 300);
        txtTexto.setOpaque(false);
        txtTexto.setEditable(false);
        
        //cont prinicpal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(new GridLayout(1,1));
        
        //se añaden los objetos
        contPrincipal.add(lblFondo);
        
        lblFondo.add(btnSalir);
        lblFondo.add(txtTexto);
    }
    
    //clase manejadora de eventos
    private class ManejadorDeEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnSalir) {
                dispose();
                try {
                    VentanaInicial ventanaInicial = new VentanaInicial(0);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaParaQueSirve.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    //Metodo que retorna una imagen con el ancho y alto recibido
    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException {
        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));
        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
        return new ImageIcon(imagen);
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
