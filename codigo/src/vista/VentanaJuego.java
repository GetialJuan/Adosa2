/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Juan
 */
public class VentanaJuego extends JFrame {
    //Fondo lbl
    private JLabel lblFondo;
    
    //Botones
    private JButton btnBlanco;
    
    
    public VentanaJuego() {
        //iniciarComponentes();
        setSize(700,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa2");
    }
    
    private void iniciarComponentes(){
        //
    }
}
