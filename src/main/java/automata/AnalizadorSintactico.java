/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import Ventanas.Inicial;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;
import manejoarchivos.ManejoArchivos;
import patrones.Repetir;
import tablaSimbolos.Id;

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
    private ArrayList<String> erroresSintacticos = new ArrayList<String>();
    private ArrayList<Id> ids = new ArrayList<Id>();
    private boolean entroRepetir = false;
    private String texto = "";
    private int veces;
    private Id id;
    private int primerNum;
    private int segundoNum;
    private int resultado;
    private boolean esSuma;
    private boolean mismoRepetir = false;
    private ArrayList<String> escrbirA = new ArrayList<String>();
    private int cantErrores = 0;
    private int contadorRepetir = 0;
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

        } else {
            Inicial ini = new Inicial(erroresSintacticos);
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
            System.out.println("derive en Y AS");

            AS();
            pila.pop();
            System.out.println("pop desde incial");
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
            System.out.println("eror else inicial fin");
            salidaError("inicial", " entrada de la gramatica", " entrada invalida ");
            get_token();
            cantErrores = 1;
        }

    }

    public void E() {

        pila.push("FIN");
        pila.push(7);
        pila.push("ESCRIBIR");
        System.out.println("derive en FIN MOSTRAR ESCRIBIR");
        String posicion = token.getPosicion();
        String analizado = token.getToken();

        if (pila.peek().equals("ESCRIBIR") && token.getTipo().equals("ESCRIBIR")) {
            System.out.println("escribir con escribir");
            pila.pop();
            get_token();
        } else {
            System.out.println("Error");
            salidaError(posicion, "ESCRIBIR", analizado);
            cantErrores = 1;
            get_token();
        }
        if (pila.peek().equals(7)) {
            Mostrar();
            System.out.println("DERIVE EN LO QUE VIENE EN MOSTRAR Y REDUJE");
        } else {
            System.out.println("Error en reduce E");
            salidaError(posicion, "VALOR NUMERICO ", analizado);
            cantErrores = 1;
        }
        if (pila.peek().equals("FIN")) {
            Fin();
        } else {
            System.out.println("eeror de FINNN");
            salidaError(posicion, "FIN", analizado);
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

            } else if (pila.peek().equals(CE) && token.getTipo().equals("ESCRIBIR")) {
                E();
            } else if (pila.peek().equals(Y) && token.getTipo().equals("id")) {
                AS();
            }

        }
        if (pila.peek().equals("$") && token.getTipo().equals("ACEPTADO")) {
            System.out.println("ACEPTACION PILA CON TOKEN ACPETACION");

        }

    }

    public void Mostrar() {
        String posicion = token.getPosicion();
        String analizado = token.getToken();

        if (pila.peek().equals(7) && token.getTipo().equals("entero") || token.getTipo().equals("negativo")
                || token.getTipo().equals("literal")) {

            if (entroRepetir == true) {
                texto = texto + token.getToken() + "\n";
            } else {
                escrbirA.add(token.getToken());
            }

            pila.pop();
            get_token();
        } else {
            System.out.println("error no se hallo un terminan entero, negativo o literal");
            salidaError(posicion, "VALOR NUMERICO", analizado);
            cantErrores = 1;
        }
    }

    public void Fin() {
        String posicion = token.getPosicion();
        String analizado = token.getToken();

        if (pila.peek().equals("FIN") && token.getTipo().equals("FIN")) {
            pila.pop();
            get_token();

            System.out.println("FIN CON FIN");
            System.out.println("en pila " + pila.peek());
            System.out.println("en token" + token.getToken());

        } else {
            System.out.println("EEROR FIN");
            salidaError(posicion, "FIN", analizado);
            cantErrores = 1;
            get_token();
        }
    }

    public void R() {
        String posicion = token.getPosicion();

        String analizado = token.getToken();

        pila.push("FIN");
        pila.push(CE);
        pila.push("INICIAR");
        pila.push(VECES);
        pila.push("REPETIR");
        entroRepetir = true;

        if (pila.peek().equals("REPETIR") && token.getTipo().equals("REPETIR")) {
            System.out.println("REPETIR con REPETIR");
            pila.pop();
            get_token();
        } else {
            System.out.println("ERROR EN PILA REPETIR Y EL LEXEMA REPETIR");
            salidaError(posicion, "REPETIR", analizado);
            cantErrores = 1;
            get_token();
        }
        if (pila.peek().equals(VECES)) {
            VECES();
        } else {
            System.out.println("error no se hallo un terminal entero o id con valor");
            salidaError(posicion, "VALOR NUMERICO", analizado);
            cantErrores = 1;
        }
        if (pila.peek().equals("INICIAR") && token.getTipo().equals("INICIAR")) {
            System.out.println("INICIAR CON INICIAR");
            pila.pop();
            get_token();
        } else {
            System.out.println("error PILA EN INICIAR Y TOKEN EN OTRO TOKEN NO INCIAR");
            salidaError(posicion, "INICIAR", analizado);
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
            salidaError(posicion, "FIN", analizado);
        }
        if (pila.peek().equals("FIN") && token.getTipo().equals("FIN")) {
            int cot = 0;
            while (cot < veces) {
                escrbirA.add(texto);
                cot++;
            }
            entroRepetir = false;
            Fin();

        } else {
            System.out.println("nO HAY FIN CON FIN");
            salidaError(posicion, "FIN", analizado);
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

            } else if (pila.peek().equals(Y) && token.getTipo().equals(id)) {
                AS();
            }
        }
        if (pila.peek().equals("$") && token.getTipo().equals("ACEPTADO")) {
            System.out.println("ACEPTACION PILA CON TOKEN ACPETACION");
            entroRepetir = false;
        }

        entroRepetir = false;
    }

    public void VECES() {
        String posicion = token.getPosicion();
        String analizado = token.getToken();

        if (pila.peek().equals(VECES) && token.getTipo().equals("entero") || token.getTipo().equals("id")) {
            pila.pop();
            System.out.println("VECES CON VECES");
            //escrbirA.add(token.getToken());}
            veces = Integer.valueOf(token.getToken());
            get_token();
        } else {
            System.out.println("error no se hallo un terminan entero, negativo o literal");
            salidaError(posicion, "VALOR NUMERICO", analizado);
            cantErrores = 1;
        }
    }

    public void AS() {
        pila.push("FIN");
        pila.push(EX);
        pila.push("=");
        pila.push("id");
        id = new Id("", 0);
        if (pila.peek().equals("id") && token.getTipo().equals("id")) {
            id.setNombre(token.getToken());
            System.out.println("id con nomvbre  " + id.getNombre());
            System.out.println("id con id");
            pila.pop();
            get_token();
        } else {
            System.out.println("no viene id en asignacion");
            salidaError(token.getPosicion(), "identificador", token.getTipo());
        }
        if (pila.peek().equals("=") && token.getTipo().equals("asignacion")) {
            System.out.println("igual con asignacion");
            pila.pop();
            get_token();
            System.out.println("AL EVALUAR IGUAL CON ASIGNACION PILA QUEDON EN" + pila.peek());
        } else {
            System.out.println("no viene igual en asignacion");
            salidaError(token.getPosicion(), "SIGNO IGUAL", token.getTipo());
        }
        if (pila.peek().equals(EX) && token.getTipo().equals("negativo") || token.getTipo().equals("entero")) {
            EX();
        } else {
            System.out.println("ERROR EX Y NUMERO");
        }
        if (pila.peek().equals(EX) && token.getTipo().equals("FIN")) {
            pila.pop();
        } else {
            System.out.println("EEROR FIN Y EX");
            salidaError(token.getPosicion(), "FIN", token.getTipo());
        }
        if (pila.peek().equals("FIN") && token.getTipo().equals("FIN")) {
            Fin();

            System.out.println("evaluando termino de fin en pila " + pila.peek() + " token " + token.getToken());
        } else {
            salidaError(token.getPosicion(), "FIN", token.getTipo());
        }
        if (pila.peek().equals(Y) && token.getTipo().equals("ACEPTADO")) {
            pila.pop();
            System.out.println("Y reuce con ACEPTADO");
            System.out.println("pila" + pila.peek());
            System.out.println("token " + token.getToken());
        } else {

            System.out.println("no hay aceptado y Y");
            if (pila.peek().equals(Y) && token.getTipo().equals("REPETIR")) {
                System.out.println("en Y y token escribir");
                R();

            } else if (pila.peek().equals(Y) && token.getTipo().equals("ESCRIBIR")) {
                //System.out.println("ESTA EN Y LUEGO DE FIN Y NO VINO ESCRIBIR");
                E();

            } else if (pila.peek().equals(Y) && token.getTipo().equals("id")) {
                //System.out.println("ESTA EN Y LUEGO DE FIN Y NO VINO ESCRIBIR");
                AS();

            }
        }
        if (pila.peek().equals("$") && token.getTipo().equals("ACEPTADO")) {
            System.out.println("aceptacion totoal");
            System.out.println("ID CREADO CON NOMBRE " + id.getNombre() + " CON VALOR " + id.getValor());
        }
    }

    public void EX() {
        pila.push(SO);
        pila.push(OP);
        System.out.println("entre a EX");
        if (token.getTipo().equals("negativo") || token.getTipo().equals("entero") && pila.peek().equals(OP)) {
            pila.pop();
            pila.push(NO);
            NO();
        }
        if (pila.peek().equals(SO) && token.getTipo().equals("FIN")) {
            pila.pop();
            System.out.println("SO reduce con FIN");
        } else {
            salidaError(token.getPosicion(), "FIN", token.getTipo());
        }
    }

    public void NO() {
        pila.push(OPCION);
        pila.push(SIGNO);
        pila.push(NUMERO);
        if (pila.peek().equals(NUMERO) && token.getTipo().equals("negativo") || token.getTipo().equals("entero")) {
            primerNum = Integer.valueOf(token.getToken());
            get_token();
            pila.pop();

        } else {
            salidaError(token.getPosicion(), "NUMERO", token.getTipo());
        }
        if (pila.peek().equals(SIGNO) && token.getTipo().equals("signo de operacion")) {
            pila.pop();

            if (token.getToken().equals("+")) {
                esSuma = true;
                System.out.println("EVALUE EL SIGNO MAS");
            } else if (token.getToken().equals("*")) {
                esSuma = false;
                System.out.println("EVALUE EL SIGNO POR");
            }
            get_token();
            System.out.println("signo con operador");
            System.out.println("se va para opcion");
            OPCION();

        } else {
            System.out.println("no vino signo de operador en suma o multi");
            salidaError(token.getPosicion(), "SIGNO DE OPERACION", token.getTipo());
        }
        if (pila.peek().equals(NO) && token.getTipo().equals("FIN")) {
            pila.pop();
        } else {
            System.out.println("no hay reduce con fin y NO");
            salidaError(token.getPosicion(), "FIN", token.getTipo());
        }

    }

    public void OPCION() {
        if (pila.peek().equals(OPCION) && token.getTipo().equals("negativo") || token.getTipo().equals("entero")) {
            segundoNum = Integer.valueOf(token.getToken());
            if (esSuma = true) {
                resultado = primerNum + segundoNum;
            } else if (esSuma = false) {
                resultado = primerNum * segundoNum;
            }

            id.setValor(resultado);
            ids.add(id);
            
            System.out.println("n " + id.getNombre() + " v " + id.getValor());

            pila.pop();
            get_token();
        } else {
            salidaError(token.getPosicion(), "NUMERO", token.getTipo());
        }
    }

    public void salidaError(String posicion, String entradaV, String entradaI) {
        erroresSintacticos.add("Error en la posicion " + posicion + " token esperado: " + entradaV + ", token analizado: " + entradaI);
        System.out.println("entre aliad eerror");
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
