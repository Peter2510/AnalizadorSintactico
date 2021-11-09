/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;
import manejoarchivos.ManejoArchivos;
import patrones.Repetir;

/**
 *
 * @author GORDILLO G
 */
public class AnalizadorSintactico {

    private ManejoArchivos archivo = new ManejoArchivos();
    private String path;
    private ArrayList<Repetir> repeticion = new ArrayList<Repetir>();
    private ArrayList tokens = new ArrayList();
    private Stack pila = new Stack();
    private Token token;
    private int veces;
    private boolean entroRepetir=false;
    Repetir rep;
    private String texto="";
    private boolean mismoRepetir=false;
    private ArrayList<String> escrbirA = new ArrayList<String>();
    private int cantErrores = 0;
    private int contadorRepetir=0;
    private int contador = 0;
    private int Y = 0, E = 2, R = 3, C = 4, EX = 5, AS = 6, MOSTRAR = 7, NUMERO = 8, VECES = 9, CE = 10, OP = 11,
            CONDICION = 12, SIGNO = 13, OPCION = 14, NO = 15, SO = 16, SIGNOIG = 17;

    public AnalizadorSintactico(ArrayList tokens, String[] path) {
        System.out.println("el path enviado es" + path[1]);
        this.tokens = tokens;
        this.path = path + ".txt";
        Token t = new Token("ACEPTADO", "posicion", "$");
        tokens.add(t);
        pila.push("$");
        pila.push(Y);
        secuencia();
        while (!token.getTipo().equals("ACEPTADO")) {
            inicial();
        }
        if (pila.empty()) {
            System.out.println("GRAMATICA ACEPTADA");
        }
        if (cantErrores == 0) {
            archivo.crearArchivo(path[1]);
            int cont = 0;
            while (cont < escrbirA.size()) {
                archivo.AgregarAlArchivo(path[1], escrbirA.get(cont));
                cont++;
            }
         if(contadorRepetir==1){
             for (int i = 0; i < repeticion.size()/2; i++) {
                 int c=0;
                 while(c<repeticion.get(i).getVeces()){
                     archivo.AgregarAlArchivo(path[1], repeticion.get(i).getTexto());
                     c++;
                 }
             }
         }
          
        }else{
            System.out.println("ERRORES SINTACTICOS");
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
            System.out.println("Estructura REPETIR");
            System.out.println("DERIVE EN Y R");
            pila.push(R);
            pila.pop();
            R();
            pila.pop();
            System.out.println("pop desde incial");
            get_token();
        } else if (pila.peek().equals(Y) && token.getTipo().equals("ACEPTADO")) {
            System.out.println("reduce xd");
            get_token();
        } else {
            System.out.println("errr");
            get_token();
            cantErrores = 1;
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
            cantErrores = 1;
            get_token();
        }
        if (pila.peek().equals(7)) {
            Mostrar();
            System.out.println("DERIVE EN LO QUE VIENE EN MOSTRAR Y REDUJE");
        } else {
            System.out.println("Error en reduce E");
            cantErrores = 1;
        }
        if (pila.peek().equals("FIN")) {
            Fin();
        } else {
            System.out.println("eeror de FINNN");
            cantErrores = 1;
            get_token();
        }
        if (pila.peek().equals(Y) && token.getTipo().equals("ACEPTADO")) {
            pila.pop();
            System.out.println("aceptacion con $ se va Y");

        } else {
            if (pila.peek().equals(Y) && token.getTipo().equals("ESCRIBIR")) {
                System.out.println("en Y y token escribir");
                E();

            } else if (pila.peek().equals(Y) && token.getTipo().equals("REPETIR")) {
                //System.out.println("ESTA EN Y LUEGO DE FIN Y NO VINO ESCRIBIR");
                R();

            }else if(pila.peek().equals(CE)&&token.getTipo().equals("ESCRIBIR")){
                E();
            }

        }
        if (pila.peek().equals("$") && token.getTipo().equals("ACEPTADO")) {
            System.out.println("ACEPTACION PILA CON TOKEN ACPETACION");

        }

    }

    public void Mostrar() {
        if (pila.peek().equals(7) && token.getTipo().equals("entero") || token.getTipo().equals("negativo")
                || token.getTipo().equals("literal")) {
            pila.pop();
            
            
            if(entroRepetir==true){
                if(contador==1){
                    System.out.println("cambie de texto: " +rep.getTexto()+ " y veces "+ +rep.getVeces());
                rep.setTexto(token.getToken());
                rep.setVeces(veces);
                repeticion.add(rep);
                System.out.println("a txt " +rep.getTexto()+ " y veces "+ +rep.getVeces());
                }else{
                System.out.println("cambie de texto: " +rep.getTexto()+ " y veces "+ +rep.getVeces());
                rep.setTexto(rep.getTexto()+"\n"+token.getToken());
                rep.setVeces(veces);
                repeticion.add(rep);
                System.out.println("a txt " +rep.getTexto()+ " y veces "+ +rep.getVeces());
                }
                
                
            }else{
            System.out.println("MOSTRAR CON MOSTRAR");
            escrbirA.add(token.getToken());
            }
            get_token();
        } else {
            System.out.println("error no se hallo un terminan entero, negativo o literal");
            cantErrores = 1;
        }
    }

    public void Fin() {
        if (pila.peek().equals("FIN") && token.getTipo().equals("FIN")) {
            pila.pop();
            get_token();
           
            System.out.println("FIN CON FIN");
            System.out.println("en pila " + pila.peek());
            System.out.println("en token" + token.getToken());

        } else {
            System.out.println("EEROR FIN");
            cantErrores = 1;
            get_token();
        }
    }

    public void R() {
        pila.push("FIN");
        pila.push(CE);
        pila.push("INICIAR");
        pila.push(VECES);
        pila.push("REPETIR");
        entroRepetir=true;
        rep = new Repetir("",0);
        System.out.println("EN REPETICIN 9393939393 "+ rep.getTexto()+ " veces"+ rep.getVeces());
        contadorRepetir=1;

        if (pila.peek().equals("REPETIR") && token.getTipo().equals("REPETIR")) {
            System.out.println("REPETIR con REPETIR");
            pila.pop();
            get_token();
        } else {
            System.out.println("ERROR EN PILA REPETIR Y EL LEXEMA REPETIR");
            cantErrores = 1;
            get_token();
        }
        if (pila.peek().equals(VECES)) {
            VECES();
        } else {
            System.out.println("error no se hallo un terminal entero o id con valor");
            cantErrores = 1;
        }
        if (pila.peek().equals("INICIAR") && token.getTipo().equals("INICIAR")) {
            System.out.println("INICIAR CON INICIAR");
            pila.pop();
            get_token();
        } else {
            System.out.println("error PILA EN INICIAR Y TOKEN EN OTRO TOKEN NO INCIAR");
            cantErrores = 1;
        }
        if (pila.peek().equals(CE) && token.getTipo().equals("ESCRIBIR")) {
            E();
        } else {
            System.out.println("Error en CE Y ESCRIBIR");
        }
        if (pila.peek().equals(CE) && token.getTipo().equals("FIN")) {
            pila.pop();
            System.out.println("CE CON FIN");
        } else {
            System.out.println("no hay reduce con CE Y FIN");
        }
        if (pila.peek().equals("FIN") && token.getTipo().equals("FIN")) {
            Fin();
            entroRepetir=false;
        } else {
            System.out.println("nO HAY FIN CON FIN");
        }
        if (pila.peek().equals(Y) && token.getTipo().equals("ACEPTADO")) {
            pila.pop();
        } else {

            System.out.println("no hay aceptado y Y");
            if (pila.peek().equals(Y) && token.getTipo().equals("REPETIR")) {
                System.out.println("en Y y token escribir");
                R();

            } else if (pila.peek().equals(Y) && token.getTipo().equals("ESCRIBIR")) {
                //System.out.println("ESTA EN Y LUEGO DE FIN Y NO VINO ESCRIBIR");
                E();

            }
        }
        if (pila.peek().equals("$") && token.getTipo().equals("ACEPTADO")) {
            System.out.println("ACEPTACION PILA CON TOKEN ACPETACION");
        entroRepetir=false;    
        }
        
        entroRepetir=false;
    }

    public void VECES() {
        if (pila.peek().equals(VECES) && token.getTipo().equals("entero") || token.getTipo().equals("id")) {
            pila.pop();
            System.out.println("VECES CON VECES");
            //escrbirA.add(token.getToken());}
            veces=Integer.valueOf(token.getToken());
            get_token();
        } else {
            System.out.println("error no se hallo un terminan entero, negativo o literal");
            cantErrores = 1;
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
