
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import manejoarchivos.ManejoArchivos;

/**
 *
 * @author GORDILLO G
 */
public class Aceptacion {

    private String infoAceptacion[] = new String[28];
    private ManejoArchivos guardarTokensUnicos = new ManejoArchivos();
    private String pathMovimientos;
    private ManejoArchivos guardarTokensSinError = new ManejoArchivos();
    private ManejoArchivos guardarMovimientos = new ManejoArchivos();
    private ManejoArchivos guardarReporte = new ManejoArchivos();
    private ManejoArchivos reporteTokens = new ManejoArchivos();
    private static ArrayList<String> tokensValidos = new ArrayList<String>();
    private static ArrayList<String> infoTokensValidos = new ArrayList<String>();
    private static ArrayList<String> unicos = new ArrayList<String>();
    private static ArrayList<String> infoUnicos = new ArrayList<String>();
    private static ArrayList<String> infoPosicion = new ArrayList<String>();
    private static ArrayList<String> infoPosicionUnicos = new ArrayList<String>();

    public Aceptacion(String lexema, int estado, String pathMovimientos, String posicion) {

        this.pathMovimientos = pathMovimientos;
        inicializarMatriz();
        guardarTxt(lexema, estado, posicion);
    }

    public Aceptacion(String path1) {
        reporteSinError(path1);
    }
    
    public Aceptacion(String path2,int num){
        Recuento(path2,num);
    }
    

    public Aceptacion() {
        vaciarListas();
    }

    public void inicializarMatriz() {

        //espacio
        infoAceptacion[0] = "espacio";

        //id
        infoAceptacion[1] = "id\t\t";
        //infoAceptacion[2] = "guion medio\t\t";
        
        //entero positivo

        infoAceptacion[3] = "entero\t\t";
        
        //entero positivo
        infoAceptacion[4] = "entero\t\t";
        //decimal

        //infoAceptacion[5] = "decimal\t\t";
        //puntuacion

        //infoAceptacion[6] = "signo de puntuacion";
        //agrupacion

        infoAceptacion[8] = "signo de agrupacion";
        //operador

        infoAceptacion[7] = "signo de operacion";
        
        //literal
        infoAceptacion[12] = "literal";
        
        //asignacion
        infoAceptacion[13] = "asignacion";
        
        infoAceptacion[15] = "negativo";
        //comentario
        infoAceptacion[17] = "comentario";
        
        //ESCRIBIR
        infoAceptacion[19] = "ESCRIBIR";
        
        //ENTONCES
        infoAceptacion[20] = "ENTONCES";
        
        //FIN
        infoAceptacion[22] = "FIN";
        
        //FALSO
        infoAceptacion[23] = "FALSO";
        
        //REPETIR
        infoAceptacion[24] = "REPETIR";
        
        //INICIAR
        infoAceptacion[25] = "INICIAR";
        
        //SI
        infoAceptacion[26] = "SI";
        
        //VERDADERO
        infoAceptacion[27] = "VERDADERO";
        

        infoAceptacion[10] = "error";
        infoAceptacion[5] = "error";

    }

    // se escriben los pasos que va realizando el automata
    public void guardarTxt(String lexema, int estado, String posicion) {

        /* if(estado==4||estado==0 || estado==10){
            guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "El lexema " + lexema + " es un " + infoAceptacion[estado]);
        }else{
            guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "El lexema " + lexema + " es un " + infoAceptacion[estado]+"\n");

            tokensValidos.add(lexema);
            infoTokensValidos.add(infoAceptacion[estado]);
        } */
        if ((estado > 0 && estado < 29) && !(estado == 5)) {
            guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "El lexema " + lexema + " es un " + infoAceptacion[estado] + "\n");
            guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Siguiente lectura");
            tokensValidos.add(lexema);
            infoTokensValidos.add(infoAceptacion[estado]);
            infoPosicion.add(posicion);
            System.out.println("Guarde el lexema" + lexema);

        } else {

            guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "El lexema " + lexema + " es un " + infoAceptacion[estado]);
            guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Siguiente lectura");
        }

    }

    //se escribe en el archvio de texto creado los lexemas que se encontraron, la inforamcion y el numero de veces que aparece
    public void Recuento(String path2,int num) {
        //System.out.println("tamaño en el recuento" + unicos.size());
        
        //System.out.println("Entre al recuento");
        for (int i = 0; i < unicos.size(); i++) {

            guardarTokensUnicos.AgregarAlArchivo(path2 + ".txt", unicos.get(i) + "\t\t\t" + infoUnicos.get(i) + "\t\t\t\t" + Collections.frequency(tokensValidos, unicos.get(i)));
            //System.out.println("veces repetido " + Collections.frequency(tokensValidos, unicos.get(i)));
            //System.out.println("Posicion " + i + " token " + unicos.get(i) + " es un " + infoUnicos.get(i));
            //System.out.println("Recuendo no " + i);
        }
        vaciarListas();
       // System.out.println("num " + num);
    }
    
    public void reporteSinError(String path1){
     // System.out.println("tamaño en el recuento" + unicos.size());
        eliminarDuplicados();
        //System.out.println("Entre al recuento");
        
        for (int i = 0; i < tokensValidos.size(); i++) {

            guardarReporte.AgregarAlArchivo(path1 + ".txt", infoTokensValidos.get(i) + "\t\t"+ tokensValidos.get(i) + "\t\t\t\t"+ infoPosicion.get(i));
            
        }
        
    }

    // se separan los tokens que no estan repetidos 
    public void eliminarDuplicados() {

        for (int i = 0; i < tokensValidos.size(); i++) {
            if (tokensValidos.indexOf(tokensValidos.get(i)) != i && unicos.contains(tokensValidos.get(i))) {
                System.out.println("Repertid");
            } else {
                unicos.add(tokensValidos.get(i));
                infoUnicos.add(infoTokensValidos.get(i));
                infoPosicionUnicos.add(infoPosicion.get(i));
                
            }

        }

    }


    public void vaciarListas() {
        tokensValidos.clear();
        infoTokensValidos.clear();
        unicos.clear();
        infoUnicos.clear();
        infoPosicion.clear();
        infoPosicionUnicos.clear();

    }
    
   
}
