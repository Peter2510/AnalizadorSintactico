
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;
import Ventanas.Inicial;
import java.util.ArrayList;
import manejoarchivos.ManejoArchivos;

/**
 *
 * @author GORDILLO G
 */
public class Analizar {
    
    private int numeroLinea;
    private int cantidadLineas = 0;
    private int posicion = 0;
    //                             CHARS ESTADOS
    private int estados[][] = new int[30][30];
    private String linea;
    private int estadoActual =0;
    private String pathMovimientos;
    private String pathErrores;
    private ManejoArchivos guardarMovimientos = new ManejoArchivos();
    private ManejoArchivos errores = new ManejoArchivos();
    private int marcarError =0;
    private int erroresTotales = 0;
    private int numeroDeLineas;
    private boolean ConError=false;
    public static ArrayList<String> ArrayErrores = new ArrayList<String>();
    
    public Analizar(){
        
    }
    
    private void InicializarMatriz(){
          
        //estado inicial 0
        // ------------------------------
        
        // alfabeto
        
        // letra -->0 , numero(0-9) -->1  , SingoPuntuacion -->2 , 
        
        //SignoAgrupacion -->3, signoOperador -->4 , cero --> 5
        
        //comillasSimples -->6, asignacion--> 7, guionBajo-->8
        
        //guionMedio -->9,  simbolosVarios--> 11
        
        // espacio --> 12,  signoMenos --> 13,
        
        // -------------------------
        
        //aceptacion
        // id -->1 , guionMedio --> 2  , EnteroPositivo --> 3 CeroEntero --> 4 
        // puntuacion--> 6, Operador -->7, agrupacion -->8 , signoMenosAgrupacion -->9
        // literal --> 12, asignacion --> 13, simbolosVarios-->14, numeroNegativo -- > 15
        
        
        // 5 y 11 --> error
        
               
        //estados de aceptacion "totales"
        //id --> 1              entero positivo --> 3,4        
        //operador -->7         agrupacion -->  8           literal --> 12
        //asignacion -->13      simbolos --> 14             numero negativo -->15
       
        
        // estado de error general = 10
        
                                                                                                                                                                                                                                                                                                                                                  //EMPIEZAN LETRAS                                
        //matriz[estado][caracter]=estado;                                                                                                                                                                                                                                                                                                        E                     F                       R                       I                       S                       V                       N                       A                       MAYUSCULAS EXCLUSIVAS                                             
        estados[0][0]=1;     estados[0][1]=3;       estados[0][2]=6;       estados[0][3]=8;     estados[0][4]=7;     estados[0][5]=4;       estados[0][6]=11;       estados[0][7]=13;       estados[0][8]=1;        estados[0][9]=2;        estados[0][11]=10;                              estados[0][13]=9;       estados[0][14]=16;            estados[0][15]=18;    estados[0][16]=21;      estados[0][17]=24;      estados[0][18]=25;      estados[0][19]=26;      estados[0][20]=27;      estados[0][21]=10;      estados[0][22]=10;      estados[0][23]=10;         
        estados[1][0]=1;     estados[1][1]=1;       estados[1][2]=10;      estados[1][3]=10;    estados[1][4]=10;    estados[1][5]=1;       estados[1][6]=10;       estados[1][7]=10;       estados[1][8]=1;        estados[1][9]=1;        estados[1][11]=10;                              estados[1][13]=10;      estados[1][14]=10;            estados[1][15]=1;     estados[1][16]=1;       estados[1][17]=1;       estados[1][18]=1;       estados[1][19]=1;       estados[1][20]=1;       estados[1][21]=1;       estados[1][22]=1;       estados[1][23]=1;          
        estados[2][0]=10;    estados[2][1]=10;      estados[2][2]=10;      estados[2][3]=10;    estados[2][4]=10;    estados[2][5]=10;      estados[2][6]=10;       estados[2][7]=10;       estados[2][8]=10;       estados[2][9]=10;       estados[2][11]=10;                              estados[2][13]=10;      estados[2][14]=10;            estados[2][15]=10;    estados[2][16]=10;      estados[2][17]=10;      estados[2][18]=10;      estados[2][19]=10;      estados[2][20]=10;      estados[2][21]=10;      estados[2][22]=10;      estados[2][23]=10;         
        estados[3][0]=10;    estados[3][1]=3;       estados[3][2]=10;      estados[3][3]=10;    estados[3][4]=10;    estados[3][5]=3;       estados[3][6]=10;       estados[3][7]=10;       estados[3][8]=10;       estados[3][9]=10;       estados[3][11]=10;                              estados[3][13]=10;      estados[3][14]=10;            estados[3][15]=10;    estados[3][16]=10;      estados[3][17]=10;      estados[3][18]=10;      estados[3][19]=10;      estados[3][20]=10;      estados[3][21]=10;      estados[3][22]=10;      estados[3][23]=10;         
        estados[4][0]=10;    estados[4][1]=5;       estados[4][2]=10;      estados[4][3]=10;    estados[4][4]=10;    estados[4][5]=5;       estados[4][6]=10;       estados[4][7]=10;       estados[4][8]=10;       estados[4][9]=10;       estados[4][11]=10;                              estados[4][13]=10;      estados[4][14]=10;            estados[4][15]=10;    estados[4][16]=10;      estados[4][17]=10;      estados[4][18]=10;      estados[4][19]=10;      estados[4][20]=10;      estados[4][21]=10;      estados[4][22]=10;      estados[4][23]=10;         
        estados[5][0]=10;    estados[5][1]=10;      estados[5][2]=10;      estados[5][3]=10;    estados[5][4]=10;    estados[5][5]=10;      estados[5][6]=10;       estados[5][7]=10;       estados[5][8]=10;       estados[5][9]=10;       estados[5][11]=10;                              estados[5][13]=10;      estados[5][14]=10;            estados[5][15]=10;    estados[5][16]=10;      estados[5][17]=10;      estados[5][18]=10;      estados[5][19]=10;      estados[5][20]=10;      estados[5][21]=10;      estados[5][22]=10;      estados[5][23]=10;         
        estados[6][0]=10;    estados[6][1]=10;      estados[6][2]=10;      estados[6][3]=10;    estados[6][4]=10;    estados[6][5]=10;      estados[6][6]=10;       estados[6][7]=10;       estados[6][8]=10;       estados[6][9]=10;       estados[6][11]=10;                              estados[6][13]=10;      estados[6][14]=10;            estados[6][15]=10;    estados[6][16]=10;      estados[6][17]=10;      estados[6][18]=10;      estados[6][19]=10;      estados[6][20]=10;      estados[6][21]=10;      estados[6][22]=10;      estados[6][23]=10;         
        estados[7][0]=10;    estados[7][1]=10;      estados[7][2]=10;      estados[7][3]=10;    estados[7][4]=7;     estados[7][5]=10;      estados[7][6]=10;       estados[7][7]=10;       estados[7][8]=10;       estados[7][9]=10;       estados[7][11]=10;                              estados[7][13]=10;      estados[7][14]=10;            estados[7][15]=10;    estados[7][16]=10;      estados[7][17]=10;      estados[7][18]=10;      estados[7][19]=10;      estados[7][20]=10;      estados[7][21]=10;      estados[7][22]=10;      estados[7][23]=10;         
        estados[8][0]=10;    estados[8][1]=10;      estados[8][2]=10;      estados[8][3]=10;    estados[8][4]=10;    estados[8][5]=10;      estados[8][6]=10;       estados[8][7]=10;       estados[8][8]=10;       estados[8][9]=10;       estados[8][11]=10;                              estados[8][13]=10;      estados[8][14]=10;            estados[8][15]=10;    estados[8][16]=10;      estados[8][17]=10;      estados[8][18]=10;      estados[8][19]=10;      estados[8][20]=10;      estados[8][21]=10;      estados[8][22]=10;      estados[8][23]=10;         
        estados[9][0]=10;    estados[9][1]=15;      estados[9][2]=10;      estados[9][3]=10;    estados[9][4]=10;    estados[9][5]=10;      estados[9][6]=10;       estados[9][7]=10;       estados[9][8]=10;       estados[9][9]=10;       estados[9][11]=10;                              estados[9][13]=10;      estados[9][14]=10;            estados[9][15]=10;    estados[9][16]=10;      estados[9][17]=10;      estados[9][18]=10;      estados[9][19]=10;      estados[9][20]=10;      estados[9][21]=10;      estados[9][22]=10;      estados[9][23]=10;         
        estados[10][0]=10;   estados[10][1]=10;     estados[10][2]=10;     estados[10][3]=10;   estados[10][4]=10;   estados[10][5]=10;     estados[10][6]=10;      estados[10][7]=10;      estados[10][8]=10;      estados[10][9]=10;      estados[10][11]=10;                             estados[10][13]=10;     estados[10][14]=10;           estados[10][15]=10;   estados[10][16]=10;     estados[10][17]=10;     estados[10][18]=10;     estados[10][19]=10;     estados[10][20]=10;     estados[10][21]=10;     estados[10][22]=10;     estados[10][23]=10;        
        estados[11][0]=11;   estados[11][1]=11;     estados[11][2]=11;     estados[11][3]=11;   estados[11][4]=11;   estados[11][5]=11;     estados[11][6]=12;      estados[11][7]=11;      estados[11][8]=11;      estados[11][9]=11;      estados[11][11]=11;     estados[11][12]=11;     estados[11][13]=11;     estados[11][14]=11;           estados[11][15]=11;   estados[11][16]=11;     estados[11][17]=11;     estados[11][18]=11;     estados[11][19]=11;     estados[11][20]=11;     estados[11][21]=11;     estados[11][22]=11;     estados[11][23]=11;        
        estados[12][0]=10;   estados[12][1]=10;     estados[12][2]=10;     estados[12][3]=10;   estados[12][4]=10;   estados[12][5]=10;     estados[12][6]=10;      estados[12][7]=10;      estados[12][8]=10;      estados[12][9]=10;      estados[12][11]=10;                             estados[12][13]=10;     estados[12][14]=10;           estados[12][15]=10;   estados[12][16]=10;     estados[12][17]=10;     estados[12][18]=10;     estados[12][19]=10;     estados[12][20]=10;     estados[12][21]=10;     estados[12][22]=10;     estados[12][23]=10;        
        estados[13][0]=10;   estados[13][1]=10;     estados[13][2]=10;     estados[13][3]=10;   estados[13][4]=10;   estados[13][5]=10;     estados[13][6]=10;      estados[13][7]=10;      estados[13][8]=10;      estados[13][9]=10;      estados[13][11]=10;                             estados[13][13]=10;     estados[13][14]=10;           estados[13][15]=10;   estados[13][16]=10;     estados[13][17]=10;     estados[13][18]=10;     estados[13][19]=10;     estados[13][20]=10;     estados[13][21]=10;     estados[13][22]=10;     estados[13][23]=10;        
        estados[14][0]=10;   estados[14][1]=10;     estados[14][2]=10;     estados[14][3]=10;   estados[14][4]=10;   estados[14][5]=10;     estados[14][6]=10;      estados[14][7]=10;      estados[14][8]=10;      estados[14][9]=10;      estados[14][11]=10;                             estados[14][13]=10;     estados[14][14]=10;           estados[14][15]=10;   estados[14][16]=10;     estados[14][17]=10;     estados[14][18]=10;     estados[14][19]=10;     estados[14][20]=10;     estados[14][21]=10;     estados[14][22]=10;     estados[14][23]=10;        
        estados[15][0]=10;   estados[15][1]=15;     estados[15][2]=10;     estados[15][3]=10;   estados[15][4]=10;   estados[15][5]=15;     estados[15][6]=10;      estados[15][7]=10;      estados[15][8]=10;      estados[15][9]=10;      estados[15][11]=10;                             estados[15][13]=10;     estados[15][14]=10;           estados[15][15]=10;   estados[15][16]=10;     estados[15][17]=10;     estados[15][18]=10;     estados[15][19]=10;     estados[15][20]=10;     estados[15][21]=10;     estados[15][22]=10;     estados[15][23]=10;        
        estados[0][10]=10;                                                                                                                                                                                                                                                                                                                        //d               //d               //d               //d               //d               //d               //d               //d               //d               //d               //d                   
        estados[16][0]=10;   estados[16][1]=10;     estados[16][2]=10;     estados[16][3]=10;   estados[16][4]=10;   estados[16][5]=15;     estados[16][6]=10;      estados[16][7]=10;      estados[16][8]=10;      estados[16][9]=10;      estados[16][11]=10;                             estados[16][13]=10;     estados[16][14]=17;           estados[16][15]=10;   estados[16][16]=10;     estados[16][17]=10;     estados[16][18]=10;     estados[16][19]=10;     estados[16][20]=10;     estados[16][21]=10;     estados[16][22]=10;     estados[16][23]=10;        
        estados[17][0]=17;   estados[17][1]=17;     estados[17][2]=17;     estados[17][3]=17;   estados[17][4]=17;   estados[17][5]=17;     estados[17][6]=17;      estados[17][7]=17;      estados[17][8]=17;      estados[17][9]=17;      estados[17][11]=17;      estados[17][12]=17;    estados[17][13]=17;     estados[17][14]=17;           estados[17][15]=17;   estados[17][16]=17;     estados[17][17]=17;     estados[17][18]=17;     estados[17][19]=17;     estados[17][20]=17;     estados[17][21]=17;     estados[17][22]=17;     estados[17][23]=17;        
        //trabajando estadso palabras reservadas                                                                                                                                                                                                                                                                                                  A                                                                                                                                                                                                                                                                     
        estados[18][0]=10;   estados[18][1]=10;     estados[18][2]=10;      estados[18][3]=10;  estados[18][4]=10;  estados[18][5]=10;      estados[18][6]=10;      estados[18][7]=10;      estados[18][8]=10;      estados[18][9]=10;      estados[18][11]=10;                              estados[18][13]=10;   estados[18][14]=10;            estados[18][15]=10;    estados[18][16]=10;    estados[18][17]=10;     estados[18][18]=10;     estados[18][19]=19;      estados[18][20]=10;    estados[18][21]=20;      estados[18][22]=10;    estados[18][23]=10;              
        estados[19][0]=10;   estados[19][1]=10;     estados[19][2]=10;      estados[19][3]=10;  estados[19][4]=10;  estados[19][5]=10;      estados[19][6]=10;      estados[19][7]=10;      estados[19][8]=10;      estados[19][9]=10;      estados[19][11]=10;                              estados[19][13]=10;   estados[19][14]=10;            estados[19][15]=10;    estados[19][16]=10;    estados[19][17]=19;     estados[19][18]=19;     estados[19][19]=10;      estados[19][20]=10;    estados[19][21]=10;      estados[19][22]=10;    estados[19][23]=19;              
        estados[20][0]=10;   estados[20][1]=10;     estados[20][2]=10;      estados[20][3]=10;  estados[20][4]=10;  estados[20][5]=10;      estados[20][6]=10;      estados[20][7]=10;      estados[20][8]=10;      estados[20][9]=10;      estados[20][11]=10;                              estados[20][13]=10;   estados[20][14]=10;            estados[20][15]=20;    estados[20][16]=10;    estados[20][17]=10;     estados[20][18]=10;     estados[20][19]=20;      estados[20][20]=10;    estados[20][21]=20;      estados[20][22]=10;    estados[20][23]=20;              
        estados[21][0]=10;   estados[21][1]=10;     estados[21][2]=10;      estados[21][3]=10;  estados[21][4]=10;  estados[21][5]=10;      estados[21][6]=10;      estados[21][7]=10;      estados[21][8]=10;      estados[21][9]=10;      estados[21][11]=10;                              estados[21][13]=10;   estados[21][14]=10;            estados[21][15]=10;    estados[21][16]=10;    estados[21][17]=10;     estados[21][18]=22;     estados[21][19]=10;      estados[21][20]=10;    estados[21][21]=10;      estados[21][22]=23;    estados[21][23]=10;              
        estados[22][0]=10;   estados[22][1]=10;     estados[22][2]=10;      estados[22][3]=10;  estados[22][4]=10;  estados[22][5]=10;      estados[22][6]=10;      estados[22][7]=10;      estados[22][8]=10;      estados[22][9]=10;      estados[22][11]=10;                              estados[22][13]=10;   estados[22][14]=10;            estados[22][15]=10;    estados[22][16]=10;    estados[22][17]=10;     estados[22][18]=10;     estados[22][19]=10;      estados[22][20]=10;    estados[22][21]=22;      estados[22][22]=10;    estados[22][23]=10;              
        estados[23][0]=10;   estados[23][1]=10;     estados[23][2]=10;      estados[23][3]=10;  estados[23][4]=10;  estados[23][5]=10;      estados[23][6]=10;      estados[23][7]=10;      estados[23][8]=10;      estados[23][9]=10;      estados[23][11]=10;                              estados[23][13]=10;   estados[23][14]=10;            estados[23][15]=10;    estados[23][16]=10;    estados[23][17]=10;     estados[23][18]=10;     estados[23][19]=23;      estados[23][20]=10;    estados[23][21]=10;      estados[23][22]=10;    estados[23][23]=23;              
        estados[24][0]=10;   estados[24][1]=10;     estados[24][2]=10;      estados[24][3]=10;  estados[24][4]=10;  estados[24][5]=10;      estados[24][6]=10;      estados[24][7]=10;      estados[24][8]=10;      estados[24][9]=10;      estados[24][11]=10;                              estados[24][13]=10;   estados[24][14]=10;            estados[24][15]=24;    estados[24][16]=10;    estados[24][17]=24;     estados[24][18]=24;     estados[24][19]=10;      estados[24][20]=10;    estados[24][21]=10;      estados[24][22]=10;    estados[24][23]=24;              
        estados[25][0]=10;   estados[25][1]=10;     estados[25][2]=10;      estados[25][3]=10;  estados[25][4]=10;  estados[25][5]=10;      estados[25][6]=10;      estados[25][7]=10;      estados[25][8]=10;      estados[25][9]=10;      estados[25][11]=10;                              estados[25][13]=10;   estados[25][14]=10;            estados[25][15]=10;    estados[25][16]=10;    estados[25][17]=25;     estados[25][18]=25;     estados[25][19]=10;      estados[25][20]=10;    estados[25][21]=25;      estados[25][22]=25;    estados[25][23]=25;              
        estados[26][0]=10;   estados[26][1]=10;     estados[26][2]=10;      estados[26][3]=10;  estados[26][4]=10;  estados[26][5]=10;      estados[26][6]=10;      estados[26][7]=10;      estados[26][8]=10;      estados[26][9]=10;      estados[26][11]=10;                              estados[26][13]=10;   estados[26][14]=10;            estados[26][15]=10;    estados[26][16]=10;    estados[26][17]=10;     estados[26][18]=26;     estados[26][19]=10;      estados[26][20]=10;    estados[26][21]=10;      estados[26][22]=10;    estados[26][23]=10;              
        estados[27][0]=10;   estados[27][1]=10;     estados[27][2]=10;      estados[27][3]=10;  estados[27][4]=10;  estados[27][5]=10;      estados[27][6]=10;      estados[27][7]=10;      estados[27][8]=10;      estados[27][9]=10;      estados[27][11]=10;                              estados[27][13]=10;   estados[27][14]=10;            estados[27][15]=27;    estados[27][16]=10;    estados[27][17]=27;     estados[27][18]=10;     estados[27][19]=10;      estados[27][20]=10;    estados[27][21]=10;      estados[27][22]=27;    estados[27][23]=27;              
    }
    // se recibe la linea a analizar, el path del archivo de los movimientos, el de los errores, el del archivo del 
    //documento si no hay errores y el del archivo que guarda los tokens si no hay errores.
    public Analizar(String linea, int numeroLinea,String pathMovimientos,String pathErrores, int noLineas) {
        this.numeroDeLineas=noLineas;
        this.linea = linea;
        this.pathMovimientos = pathMovimientos;
        this.cantidadLineas = numeroLinea;
        this.pathErrores = pathErrores;
        this.numeroLinea = numeroLinea;
        InicializarMatriz();
        //System.out.println("posicicon incial: " + posicion);
        guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "\nLinea a analizar: " + linea);
        
        while(posicion<linea.length()){
            getToken(linea,numeroLinea);
            System.out.println("eT "+erroresTotales);
            System.out.println("e "+marcarError);
        }
        
        
    }

    private void getToken(String linea, int numeroLinea) {
        
        boolean lectura = true;
        String tmp ="";
        estadoActual = 0;
        char tabulacion ='\t';
        
        //validando el texto ingresado
        
        
        while((posicion<linea.length())&&(lectura==true)){
            
                // idea = separar si el caracter es espacio y si no esta en el estado 11
            if ( (Character.isSpaceChar(linea.charAt(posicion)) && estadoActual!=11)  == true || (linea.charAt(posicion)==tabulacion ) == true) {
               
                lectura = false;
               // System.out.println("Estado actual si encontro espacio" + estadoActual);
                
                if(estadoActual==17){
                    lectura=true;
                }else{
                    System.out.println("sigueinte estado");   
                }
                
                
            }
            else{
                
                System.out.println("Intento evaluar el signo con espacio");
                tmp = tmp + String.valueOf(linea.charAt(posicion));
                int estadoTemporal = getSiguienteEstado(estadoActual, getIntCaracter(linea.charAt(posicion)));
               
                
                char comilla = '"';
                          
                guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Estado actual "+ estadoActual + " caracter a leer: " + linea.charAt(posicion)+ " transicion al estado: " + estadoTemporal + " al leer el caracter: " + linea.charAt(posicion));
                          
                estadoActual = estadoTemporal;
                
                
                if(estadoActual==11&&posicion==linea.length()-1){
                    
                    if(linea.charAt(posicion)!=comilla){
                        estadoActual=10;
                        System.out.println("error no se encontro la comilla de ciere");
                        guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "No se encontro la comilla de cierre en la linea " + (numeroLinea+1) + " en la posicion " + (posicion+1));
                    }else{
                        System.out.println("Error comilla");
                    }
                 
                    
                    
                }
                try {
                     //verificar palabras reservadas
                if(posicion==linea.length()-1&&estadoActual==9){
                    estadoActual=10;
                    
                }
                
                
                
                //ESCRIBIR
                if((tmp.length()==8)&&estadoActual==19){
                    System.out.println("entre a evualar escribir");
                    System.out.println(tmp.length());
                    if(tmp.charAt(0)=='E'&&tmp.charAt(1)=='S'&&tmp.charAt(2)=='C'
                            &&tmp.charAt(3)=='R'&&tmp.charAt(4)=='I'&&tmp.charAt(5)=='B'&&
                            tmp.charAt(6)=='I'&&tmp.charAt(7)=='R'&&tmp.length()==8){
                        estadoActual=19;
                        System.out.println("ESCROBOR CORRECTO");
                        System.out.println("-----Termine en el estado ESCRIBIR");
                    }else{
                        System.out.println("Estoy en el estado 19 pero con fallas");
                        System.out.println(tmp);
                        System.out.println("escribir incorecto");
                        System.out.println("estou en" + estadoActual);
                        estadoActual=10;
                        System.out.println("termien en " + estadoActual);
                    }
                }
                //ENTONCES
                if(posicion==linea.length()-1&&estadoActual==20){
                    if(tmp.charAt(0)=='E'&&tmp.charAt(1)=='N'&&tmp.charAt(2)=='T'
                            &&tmp.charAt(3)=='O'&&tmp.charAt(4)=='N'&&tmp.charAt(5)=='C'&&
                            tmp.charAt(6)=='E'&&tmp.charAt(7)=='S'&&tmp.length()==8){
                        estadoActual=20;
                        System.out.println("-----Termine en el estado ENTONCES");
                    }else{
                        System.out.println("Estoy en el estado 20 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                }
                //FIN
                 if(posicion==linea.length()-1&&estadoActual==22){
                    if(tmp.charAt(0)=='F'&&tmp.charAt(1)=='I'&&tmp.charAt(2)=='N'
                            &&tmp.length()==3){
                        System.out.println("--Termine en el estado FIN");
                        estadoActual=22;
                    }else{
                        System.out.println("Estoy en el estado 22 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                }
                //FALSO
                if(posicion==linea.length()-1&&estadoActual==23){
                    if(tmp.charAt(0)=='F'&&tmp.charAt(1)=='A'&&tmp.charAt(2)=='L'
                            &&tmp.charAt(3)=='S'&&tmp.charAt(4)=='O'&&tmp.length()==5){
                        System.out.println("--Termine en el estado FALSO");
                        estadoActual=23;
                    }else{
                        System.out.println("Estoy en el estado 23 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                }//REPETIR
                if(posicion==linea.length()-1&&estadoActual==24){
                    if(tmp.charAt(0)=='R'&&tmp.charAt(1)=='E'&&tmp.charAt(2)=='P'
                            &&tmp.charAt(3)=='E'&&tmp.charAt(4)=='T'&&tmp.charAt(5)=='I'
                            &&tmp.charAt(6)=='R'&&tmp.length()==7){
                        estadoActual=24;
                        System.out.println("--Termine en el estado REPETIR");
                    }else{
                        System.out.println("Estoy en el estado 23 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                }//INICIAR
                if(posicion==linea.length()-1&&estadoActual==25){
                    if(tmp.charAt(0)=='I'&&tmp.charAt(1)=='N'&&tmp.charAt(2)=='I'
                            &&tmp.charAt(3)=='C'&&tmp.charAt(4)=='I'&&tmp.charAt(5)=='A'
                            &&tmp.charAt(6)=='R'&&tmp.length()==7){
                        estadoActual=25;
                        System.out.println("--Termine en el estado INICIAR");
                    }else{
                        System.out.println("Estoy en el estado 23 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                    //SI
                }if(posicion==linea.length()-1&&estadoActual==26){
                    if(tmp.charAt(0)=='S'&&tmp.charAt(1)=='I'&&tmp.length()==2){
                        estadoActual=26;
                        System.out.println("--Termine en el estado SI");
                    }else{
                        System.out.println("Estoy en el estado 23 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                }//VERDADERO
                if(posicion==linea.length()-1&&estadoActual==27){
                    if(tmp.charAt(0)=='V'&&tmp.charAt(1)=='E'&&tmp.charAt(2)=='R'
                       &&tmp.charAt(3)=='D'&&tmp.charAt(4)=='A'&&tmp.charAt(5)=='D'&&tmp.charAt(6)=='E'&&
                            tmp.charAt(7)=='R'&&tmp.charAt(8)=='O'&&tmp.length()==9){
                        estadoActual=27;
                        System.out.println("--Termine en el estado VERDADERO");
                    }else{
                        System.out.println("Estoy en el estado 23 pero con fallas");
                        System.out.println(tmp);
                        estadoActual=10;
                    }
                    
                 }
                
                } catch (Exception e) {
                    estadoActual=10;
                    marcarError=1;
                    erroresTotales = erroresTotales + marcarError;
                    ConError=true;
                    lectura = false;
                    System.out.println("no cumple con tamaño");
                }
               
                if(posicion==linea.length()-1&&estadoActual==18){
                    estadoActual=10;
                }
                
                                
                if(estadoActual==5||estadoActual==10){
                    ConError=true;
                    errores.AgregarAlArchivo(pathErrores+".txt",tmp +  "\t\t\t\t\t(" + (numeroLinea+1) + "," + (posicion+1) + ")" );
                    guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Error en la linea " + (numeroLinea+1) + " en la posicion " + (posicion+1));
                    lectura = false;
                    marcarError=1;
                    erroresTotales = erroresTotales + marcarError;
                    System.out.println("Error en la liena " + (numeroLinea+1) + " en la psocicion " + (posicion+1));
                    ArrayErrores.add("Error en la linea " + (numeroLinea+1) + " en la posición " + (posicion+1)+" con el lexema: "+ tmp);
                    System.out.println("sicie" + ArrayErrores.size());
                    System.out.println("ERROROROROROROROROROR");
                } else{
                    
                    guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Siguiente lectura");
                }
            }
            
            posicion++;
        }
        
        guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Termino en el estado " + estadoActual + " con el lexema " + tmp);
        
        //verificar estado final
        
        String posFila = String.valueOf(posicion-1);
        String posCol = String.valueOf(numeroLinea+1);
        String posFinal = posFila + "," + posCol;
        
        
        Aceptacion estadoFinal = new Aceptacion(tmp,estadoActual,pathMovimientos,posFinal);
        
//        if(estadoActual==11&&posicion==linea.length()||estadoActual==1&&posicion==1){
//            System.out.println("Error, no se reconoce la doble comiila");
//        }
        
         
        if(estadoActual==10||estadoActual==5){
            String tmplectura ="";
         for(int i = posicion; i < linea.length();i++){
                        tmplectura = tmplectura + String.valueOf(linea.charAt(i));
                    }
         guardarMovimientos.AgregarAlArchivo(pathMovimientos+".txt","\nRECUPERACION DE ERRORES, Se leera: "+ tmplectura);
        } 
        System.out.println("Linea entera " + tmp);
        
    }
   
    
    // moverse entre estados
    private int getSiguienteEstado(int estadoActual, int caracter){
        int resultado = 10;        
        
        if(caracter >=0 && caracter <=23){
            resultado = estados[estadoActual][caracter];
        }
    
        return resultado;
    }
    
    // alfabeto del automata
    private int getIntCaracter(char caracter){
        int resultado = 10;
        
        if(Character.isLowerCase(caracter)){
            resultado =0;
        } else if(Character.isDigit(caracter)){
            if(caracter!='0'){
                resultado = 1;
            }else{
                resultado=5;
            }    
        } else if(caracter=='.'){
            resultado = 11;
        } else if(caracter==','){
            resultado = 11;
        }else if(caracter==';'){
            resultado = 11;
        } else if(caracter==':'){
            resultado = 11;
        } else if(Character.isMirrored(caracter)){
            resultado =3;
        }else if(caracter == '+'){
            resultado = 4;
        }else if(caracter == '-'){
            resultado = 13;
        }else if(caracter == '*'){
            resultado = 4;
        }else if(caracter == '/'){
            resultado = 14;
        }else if(caracter == '%'){
            resultado = 11;
        }else if(Character.isSpaceChar(caracter)){
            resultado =12;
        }else if(caracter=='"'){
            resultado=6;
        }else if(caracter=='='){
            resultado=7;
        }else if(caracter=='_'){
            resultado=8;
        }else if(caracter=='—'){
        resultado =9;
        }else if(caracter=='<' || caracter == '>' || caracter== '\''){
            resultado =11;
        }else if(caracter=='E'){
            resultado=15;
        }else if(caracter=='F'){
            resultado=16;
        }else if(caracter=='R'){
            resultado=17;
        }else if(caracter=='I'){
            resultado=18;
        }else if(caracter=='S'){
            resultado=19;
        }else if(caracter=='V'){
            resultado=20;
        }else if(caracter=='N'){
            resultado=21;
        }else if(caracter=='A'){
            resultado=22;
        }else if(Character.isUpperCase(caracter)){
            
            if(caracter!='E'||caracter!='F'||caracter!='R'||caracter!='I'||caracter!='S'
                    ||caracter!='V'||caracter!='N'||caracter!='A'){
                resultado=23;
            }else{
                resultado=10;
            }
            
        }
        
        return resultado;
    }
 
    public int getError(){
        return marcarError;
    }
}