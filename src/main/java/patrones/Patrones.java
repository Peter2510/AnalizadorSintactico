/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patrones;

import javax.swing.JOptionPane;

/**
 *
 * @author GORDILLO G
 */
public class Patrones {

    

    public Patrones(String textoBuscar, String textoTotal) {
        buscar(textoBuscar,textoTotal);
    }
    
    public boolean buscar(String textoBuscar, String textoTotal){
        int t;
        int contador=0;
        boolean s = false;
        String buscar = textoBuscar+" ";
        String texto = textoTotal;
        
        
        String[] separarLinea = texto.split("\n");
        
        
        for(int i =0; i<separarLinea.length;i++){
            
            for(int j=0; j<separarLinea[i].length()-buscar.length();j++){
                contador =0;
                t=j;
                for (int k = 0; k < buscar.length(); k++) {
                    
                    if(texto.charAt(t)==buscar.charAt(k)){
                        contador++;
                    }
                    t++;
                }
                
                if(contador==buscar.length()){
                    System.out.println("Enncontrado");
                    JOptionPane.showMessageDialog(null, "Se encontro la cadena " + buscar);
                    return true;
                }
                
            }
            
            
        }
        
        JOptionPane.showMessageDialog(null, "No se encontro la cadena " + buscar);
        return false;
    }
}
