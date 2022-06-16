/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JFrame;
import logica.LogicaAdosa2;

/**
 *
 * @author Juan
 */
public class VentanaFinal extends JFrame {
    private int ancho;
    private int largo;
    
    private LogicaAdosa2 logica;
    
    public VentanaFinal(LogicaAdosa2 logica){
        ancho = 700;
        largo = 500;
        
        this.logica = logica;
        
        setSize(ancho, largo);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa");
    }
}
