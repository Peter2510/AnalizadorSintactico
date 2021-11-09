/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.Stack;

/**
 *
 * @author GORDILLO G
 */
public class Sintact {

    private int estadosSintactico[][] = new int[21][21];
    private Token token;
    private static Stack pila = new Stack();
    private int estadoTemporal;
    private static int estadoActual = 0;

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
        estadosSintactico[0][4] = 2;
        estadosSintactico[0][6] = 2;
        estadosSintactico[0][7] = 2;
        estadosSintactico[0][15] = 3;
        estadosSintactico[0][16] = 4;
        estadosSintactico[0][17] = 5;
        estadosSintactico[0][19] = 6;
    }

    public Sintact(Token token) {
        
        System.out.println();
        
        this.token = token;
        incializarEstados();
        System.out.println("Entro" + token.getToken());
        System.out.println(token.getTipo());
        estadoActual=0;
        evaluar();
    }

    public void evaluar() {
        estadoActual=0;
        moverseEstados();

        if (token.getTipo().equals("ESCRIBIR") && estadoActual == 1) {
            System.out.println("ESTADO 1");
        } else if ((token.getTipo().equals("entero") || token.getTipo().equals("negativo")) && estadoActual == 2) {
            System.out.println("ESTADO 2--");
        }else if((token.getTipo()==null)&&estadoActual==6){
            System.out.println("error");
        }
    }

    public void moverseEstados() {

        int estadoTemporal = getSiguienteEstado(estadoActual, getIntToken(token.getTipo()));
        estadoActual = estadoTemporal;

    }

    public int getSiguienteEstado(int estadoActualArriba, int intToken) {
        int resultado = 19;

        if (intToken >= 0 && intToken <=19) {
            resultado = estadosSintactico[estadoActualArriba][intToken];

        }
        return resultado;
    }

    public int getIntToken(String tipo) {
        int resultado = 19;

        if (tipo.equals("ESCRIBIR")) {
            resultado = 1;
        } else if (tipo.equals("FIN")) {
            resultado = 2;
        } else if (tipo.equals("signo de operacion")) {
            resultado = 3;
        } else if (tipo.equals("signo de agrupacion")) {
            resultado = 4;
        } else if (tipo.equals("entero")) {
            resultado = 6;
        } else if (tipo.equals("negativo")) {
            resultado = 7;
        } else if (tipo.equals("INICIAR")) {
            resultado = 8;
        } else if (tipo.equals("ENTONCES")) {
            resultado = 9;
        } else if (tipo.equals("id(valor)")) {
            resultado = 10;
        } else if (tipo.equals("literal")) {
            resultado = 11;
        } else if (tipo.equals("asignacion")) {
            resultado = 12;
        } else if (tipo.equals("VERDADERO")) {
            resultado = 13;
        } else if (tipo.equals("FALSO")) {
            resultado = 14;
        } else if (tipo.equals("SI")) {
            resultado = 15;
        } else if (tipo.equals("id")) {
            resultado = 16;
        } else if (tipo.equals("REPETIR")) {
            resultado = 17;
        } else if (tipo.equals("ACEPTADO")) {
            resultado = 18;
        }
        
        System.out.println("retrono estado" + resultado);
        return resultado;
    }
}
