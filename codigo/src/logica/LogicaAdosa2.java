/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class LogicaAdosa2 {
    private int numeroDeBaldosasAMostrar;//Mientras mayor el numero, mas dificil
    private ArrayList<Integer> baldosasSinMostrar;
    private ArrayList<Integer> baldosasAMostrar;
    private int vidas;
    
    public LogicaAdosa2(){
        numeroDeBaldosasAMostrar = 3;
        vidas = 3;
        
        baldosasSinMostrar = new ArrayList<>();
        for(int i = 0; i<8; i++){
            baldosasSinMostrar.add(i);
        }
        
        baldosasAMostrar = new ArrayList<>();
        for(int i = 0; i<numeroDeBaldosasAMostrar; i++){
            int cualBaldosa = (int) (Math.random() * baldosasSinMostrar.size());
            baldosasAMostrar.add(baldosasSinMostrar.get(cualBaldosa));
            baldosasSinMostrar.remove(cualBaldosa);
        }
        
    }
    
    //indica si la baldosa si se esta mostrando
    public boolean baldosaAMostrar(int cualNumero){
        return baldosasAMostrar.indexOf(cualNumero) != -1;
    }
    
    public int baldosaACambiar(){
        return baldosasAMostrar.get((int) (Math.random() *
                baldosasAMostrar.size()));
    }
    
    public ArrayList<Integer> getBaldosasAMostrar(){
        return baldosasAMostrar;
    }
    
    public void baldosasIguales(){
        vidas--;
        if(numeroDeBaldosasAMostrar > 3) {
            numeroDeBaldosasAMostrar--;
        }
    }
    
    public int getVidas() {
        return vidas;
    }
    
    public void reiniciarBaldosasSinMostrar(){
        baldosasSinMostrar.clear();
        for(int i = 0; i<8; i++){
            baldosasSinMostrar.add(i);
        }
    }
    
    public void nuevasBaldosasAMostrar() {
        reiniciarBaldosasSinMostrar();
        
        baldosasAMostrar.clear();
        for(int i = 0; i<numeroDeBaldosasAMostrar; i++){
            int cualBaldosa = (int) (Math.random() * baldosasSinMostrar.size());
            baldosasAMostrar.add(baldosasSinMostrar.get(cualBaldosa));
            baldosasSinMostrar.remove(cualBaldosa);
        }
        
    }
    
    public void aumentarBaldosasAMostrar(){
        if(numeroDeBaldosasAMostrar < 8) {
            numeroDeBaldosasAMostrar++;
        }
        
    }
}
