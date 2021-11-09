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
public class AnalizadorSintactico {

    ArrayList tokens = new ArrayList();
    Stack pila = new Stack();
    Token token;
    int contador = 0;
    int Y = 0, E = 2, R = 3, C = 4, EX = 5, AS = 6, MOSTRAR = 7, NUMERO = 8, VECES = 9, CE = 10, OP = 11,
            CONDICION = 12, SIGNO = 13, OPCION = 14, NO = 15, SO = 16, SIGNOIG = 17;

    public AnalizadorSintactico(ArrayList tokens) {
        this.tokens = tokens;
        Token t = new Token("ACEPTADO", "posicion", "$");
        tokens.add(t);
        pila.push("$");
        pila.push(Y);
        secuencia();
        while (!token.getTipo().equals("ACEPTADO")) {
            inicial();
        }if(pila.empty()){
            System.out.println("GRAMATICA ACEPTADA");
        }

    }

    public void get_token() {

        if (contador < tokens.size()) {
            token = (Token) tokens.get(contador);
            contador++;
        } else {
            System.out.println("Error GET TOKEN");
        }
    }

    public void secuencia() {
        get_token();

    }

    public void inicial() {

        

        if (pila.peek().equals(Y) && token.getTipo().equals("ESCRIBIR")) {
            System.out.println("estructura ESCRIBIR");
            System.out.println("derive en Y E");
            pila.push(2);
            pila.pop();
            E();     
            pila.pop();
            System.out.println("pop desde incial");
            get_token();
            
        } else if (pila.peek().equals(Y) && (token.getToken().equals("("))) {
            System.out.println("Estructura EXPRESION");
            get_token();
        } else if (pila.peek().equals(Y) && (token.getTipo().equals("entero") || token.getTipo().equals("negativo"))) {
            System.out.println("Estructura EXPRESION");
            get_token();
        } else if (pila.peek().equals(Y) && token.getToken().equals("(")) {
            System.out.println("Estructura EXPRESION");
            get_token();
        } else if (pila.peek().equals(Y) && token.getTipo().equals("SI")) {
            System.out.println("Estructura si");
            get_token();
        } else if (pila.peek().equals(Y) && token.getTipo().equals("id")) {
            System.out.println("Estructura ASIGNACION");
            get_token();
        } else if (pila.peek().equals(Y) && token.getTipo().equals("REPETIR")) {
            System.out.println("Estructura ASIGNACION");
            get_token();
        } else if (pila.peek().equals(Y) && token.getTipo().equals("ACEPTADO")) {
            System.out.println("reduce xd");
            get_token();
        } else {
            System.out.println("errr");
            get_token();
        }

    }

    public void E() {
        
        pila.push("FIN");
        pila.push(7);
        pila.push("ESCRIBIR");
        System.out.println("derive en FIN MOSTRAR ESCRIBIR");

        if (pila.peek().equals("ESCRIBIR") && token.getTipo().equals("ESCRIBIR")) {
            System.out.println("escribir con escribir");
            pila.pop();
            get_token();
        } else {
            System.out.println("Error");
            get_token();
        }
        if (pila.peek().equals(7)) {
            Mostrar();
            System.out.println("DERIVE EN LO QUE VIENE EN MOSTRAR Y REDUJE");
        }else{
            System.out.println("Error en reduce E");
        }if(pila.peek().equals("FIN")){
         Fin();
        }else{
            System.out.println("eeror de FINNN");
            get_token();
        }if(pila.peek().equals(Y)&&token.getTipo().equals("ACEPTADO")){
            pila.pop();
             System.out.println("aceptacion con $ se va Y");
         
        }else{
                if(pila.peek().equals(Y)&&token.getTipo().equals("ESCRIBIR")){
               System.out.println("en Y y token escribir");
               E(); 
                    
                }else{
                   System.out.println("ESTA EN Y LUEGO DE FIN Y NO VINO ESCRIBIR");
                   
                }
            
        }if(pila.peek().equals("$")&&token.getTipo().equals("ACEPTADO")){
            System.out.println("ACEPTACION PILA CON TOKEN ACPETACION");
            
            
        }

    }
    
    public void Mostrar(){
        if(pila.peek().equals(7)&&token.getTipo().equals("entero")|| token.getTipo().equals("negativo")
                || token.getTipo().equals("literal")){
            pila.pop();
            System.out.println("MOSTRAR CON MOSTRAR");
            get_token();
        }else{
            System.out.println("error no se hallo un terminan entero, negativo o literal");
        }
    }
    public void Fin(){
        if(pila.peek().equals("FIN")&&token.getTipo().equals("FIN")){
            pila.pop();
            get_token();
            System.out.println("FIN CON FIN");
            System.out.println("en pila "+pila.peek());
            System.out.println("en token" + token.getToken());
            
        }else{
            System.out.println("EEROR FIN");
            get_token();
        }
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
