/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author carlos
 */
public class Baldosas {

    private ArrayList<ImageIcon> listaDeBaldosas;
    private final int altoBaldosa = 75;
    private final int anchoBaldosa = 75;

    public Baldosas() throws IOException {
        listaDeBaldosas = new ArrayList<>();
        inicializarBaldosas();
    }

    private void inicializarBaldosas() throws IOException {
        ImageIcon baldosa;
        String rutaAux = "\\src\\imagenes\\baldosas\\numero.png";

        for (int i = 1; i <= 29; i++) {
            
            String nuevaRuta = rutaAux.replace("numero",i+"");
            
            baldosa = establecerIcon(rutaAux, anchoBaldosa, altoBaldosa);
            listaDeBaldosas.add(baldosa);
        }

    }

    private ImageIcon establecerIcon(String rutaArchivo, int ancho, int alto)
            throws IOException {
        String rutaAbsoluta = new File("").getAbsolutePath();

        BufferedImage bufferedImagen = ImageIO.read(new File(rutaAbsoluta.concat(rutaArchivo)));

        Image imagen = bufferedImagen.
                getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);

        return new ImageIcon(imagen);
    }
    
    public ImageIcon getImgBaldosa(int cualBaldosa){
        return listaDeBaldosas.get(cualBaldosa);
    }

}
