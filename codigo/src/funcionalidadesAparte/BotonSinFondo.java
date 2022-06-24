/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionalidadesAparte;

import javax.swing.JButton;

/**
 *
 * @author Juan
 */
public class BotonSinFondo extends JButton {
    
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
