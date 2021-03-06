/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author GORDILLO G
 */
public class Sintactico {

    private int estadosSintactico[][] = new int[18][19];
    private ArrayList<Token> ArrayTokens = new ArrayList<Token>();
    private int contador = 0;
    private int estadoActual = 0;
    private Stack pila = new Stack();

    public void incializarEstados() {

        // estados
        // [fila][columna]
        // Inicial Y  -> 0 , ESCRIBIR E --> 1 ,  REPETIR --> R --> 2 , SI C --> 3 
        // "EXPRESION" EX --> 4 ,  ASIGNACION AS --> 5 , MOSTRAR --> 6 , 
        // NUMERO --> 7 ,  VECES --> 8 CE --> 9
        // CONDICION --> 10 OP --> 11 SIGNO --> 12 OPCION --> 13 NO --> 14  SO --> 15
        // SIGNOIG --> 16
        // "alfabeto"
        // ESCRIBIR --> 1, FIN --> 2, + --> 3 
        // * --> 4, ) --> 5 , ( --> 6  , entero --> 7,
        // negativo --> 8 , INICIAR --> 9,  ENTONCES --> 10 
        // id(valor) --> 11 , literal --> 12 , = --> 13,  VERDADERO -->14
        // FALSO --> 15 , SI --> 16 , id --> 17, REPETIR --> 18,  $ --> 19
        estadosSintactico[0][1] = 1;
        estadosSintactico[0][14] = 2;
    }

    public Sintactico(ArrayList<Token> ArrayTokens) {
        System.out.println(ArrayTokens.get(0).getTipo());
        System.out.println(ArrayTokens.get(1).getTipo());
        this.ArrayTokens = ArrayTokens;
        incializarEstados();
        pila.push("$");
        pila.push("Y");
        System.out.println("------ ----- **** /// /// / /SINTACTICO");
        inciar();

    }
    public void inciar(){
        moverseEstados();
        apilar();
    }
    
    public void apilar(){
        if(estadoActual==1){
            pila.push("FIN");
            pila.push("MOSTRAR");
            pila.push("ESCRIBIR");
            
        }
        System.out.println(pila.peek());
    }
    
    

    public void moverseEstados() {

        int estadoTemporal = getSiguienteEstado(estadoActual, getIntToken(ArrayTokens.get(contador).getTipo()));
        estadoActual = estadoTemporal;
        
    }

    public int getSiguienteEstado(int estadoActualArriba, int intToken) {
        int resultado = 17;

        if (intToken >= 0 && intToken <= 17) {
            resultado = estadosSintactico[estadoActualArriba][intToken];

        }
        return resultado;
    }

    public int getIntToken(String tipo) {
        int resultado = 18;

        if (tipo.equals("ESCRIBIR")) {
            resultado = 1;
        } else if (tipo.equals("FIN")) {
            resultado = 2;
        } else if (tipo.equals("signo de operacion")) {
            resultado = 3;
        } else if (tipo.equals("signo de agrupacion")) {
            resultado = 4;
        } else if (tipo.equals("entero")) {
            resultado = 5;
        } else if (tipo.equals("negativo")) {
            resultado = 6;
        } else if (tipo.equals("INICIAR")) {
            resultado = 7;
        } else if (tipo.equals("ENTONCES")) {
            resultado = 8;
        } else if (tipo.equals("id(valor)")) {
            resultado = 9;
        } else if (tipo.equals("literal")) {
            resultado = 10;
        } else if (tipo.equals("asignacion")) {
            resultado = 11;
        } else if (tipo.equals("VERDADERO")) {
            resultado = 12;
        } else if (tipo.equals("FALSO")) {
            resultado = 13;
        } else if (tipo.equals("SI")) {
            resultado = 14;
        } else if (tipo.equals("id")) {
            resultado = 15;
        } else if (tipo.equals("REPETIR")) {
            resultado = 16;
        } else if (tipo.equals("ACEPTADO")) {
            resultado = 17;
        }

        return resultado;
    }

  
}
