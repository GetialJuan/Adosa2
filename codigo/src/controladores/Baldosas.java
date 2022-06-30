/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import funcionalidadesAparte.metodosUtiles;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author carlos
 */
public class Baldosas {

    private ArrayList<ImageIcon> listaDeBaldosas;
    private final int altoBaldosa = 100;
    private final int anchoBaldosa = 100;

    public Baldosas() {
        listaDeBaldosas = new ArrayList<>();
        inicializarBaldosas();
    }

    private void inicializarBaldosas() {
        ImageIcon baldosa;
        String rutaAux = "\\src\\imagenes\\baldosas\\numero.png";

        for (int i = 1; i <= 16; i++) {
            
            String nuevaRuta = rutaAux.replace("numero",i+"");
            
            baldosa = metodosUtiles.establecerIcon(nuevaRuta, anchoBaldosa, altoBaldosa);
            listaDeBaldosas.add(baldosa);
        }

    }
    
    public ImageIcon getImgBaldosa(int cualBaldosa){
        return listaDeBaldosas.get(cualBaldosa);
    }
    
    public ImageIcon getImgBaldosaAleatoria(){
        return listaDeBaldosas.get((int) (Math.random() * 
                listaDeBaldosas.size()));
    }

}
