/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.ArrayList;

/**
 *
 * @author GORDILLO G
 */
public class Sintactico {

    
    private static ArrayList<Token> tokens = new ArrayList<Token>();
    int contador=0;
  
    public Sintactico(String palabras) {
    
        
        //tokens.add(palabras);
        contador++;
        
    }
    public Sintactico(){
        
        int contador=0;
        
        while(contador<tokens.size()){
            System.out.println(tokens.get(contador));
            contador++;
        }    
    }
    
    public Sintactico(Token token){
        Objeto(token);
        //tokens.add(token);
    }
    
    public void limpiarTokens(){
        tokens.clear();
    }
    
    public void Objeto(Token token){
        System.out.println(token.getTipo());
        System.out.println(token.getPosicion());
        System.out.println(token.getToken());
    }
    
}
