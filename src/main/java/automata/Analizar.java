
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;
import manejoarchivos.ManejoArchivos;

/**
 *
 * @author GORDILLO G
 */
public class Analizar {
    
    private int numeroLinea;
    private int cantidadLineas = 0;
    private int posicion = 0;
    private int estados[][] = new int[16][14];
    private String linea;
    private int estadoActual =0;
    private String pathMovimientos;
    private String pathErrores;
    private ManejoArchivos guardarMovimientos = new ManejoArchivos();
    private ManejoArchivos errores = new ManejoArchivos();
    private int marcarError =0;
    private int erroresTotales = 0;
    
    
    
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
        //id --> 1              entero positivo --> 3,4     puntuacion --> 6   
        //operador -->7         agrupacion -->  8           literal --> 12
        //asignacion -->13      simbolos --> 14             numero negativo -->15
       
        
        // estado de error general = 10
        
        
        //matriz[estado][caracter]=estado;
        estados[0][0]=1;     estados[0][1]=3;       estados[0][2]=6;       estados[0][3]=8;     estados[0][4]=7;     estados[0][5]=4;       estados[0][6]=11;       estados[0][7]=13;       estados[0][8]=1;        estados[0][9]=2;        estados[0][11]=14;                              estados[0][13]=9;
        estados[1][0]=1;     estados[1][1]=1;       estados[1][2]=10;      estados[1][3]=10;    estados[1][4]=10;    estados[1][5]=1;       estados[1][6]=10;       estados[1][7]=10;       estados[1][8]=1;        estados[1][9]=1;        estados[1][11]=10;                              estados[1][13]=10;
        estados[2][0]=10;    estados[2][1]=10;      estados[2][2]=10;      estados[2][3]=10;    estados[2][4]=10;    estados[2][5]=10;      estados[2][6]=10;       estados[2][7]=10;       estados[2][8]=10;       estados[2][9]=10;       estados[2][11]=10;                              estados[2][13]=10;
        estados[3][0]=10;    estados[3][1]=3;       estados[3][2]=10;      estados[3][3]=10;    estados[3][4]=10;    estados[3][5]=3;       estados[3][6]=10;       estados[3][7]=10;       estados[3][8]=10;       estados[3][9]=10;       estados[3][11]=10;                              estados[3][13]=10;
        estados[4][0]=10;    estados[4][1]=5;       estados[4][2]=10;      estados[4][3]=10;    estados[4][4]=10;    estados[4][5]=5;       estados[4][6]=10;       estados[4][7]=10;       estados[4][8]=10;       estados[4][9]=10;       estados[4][11]=10;                              estados[4][13]=10;
        estados[5][0]=10;    estados[5][1]=10;      estados[5][2]=10;      estados[5][3]=10;    estados[5][4]=10;    estados[5][5]=10;      estados[5][6]=10;       estados[5][7]=10;       estados[5][8]=10;       estados[5][9]=10;       estados[5][11]=10;                              estados[5][13]=10;
        estados[6][0]=10;    estados[6][1]=10;      estados[6][2]=10;      estados[6][3]=10;    estados[6][4]=10;    estados[6][5]=10;      estados[6][6]=10;       estados[6][7]=10;       estados[6][8]=10;       estados[6][9]=10;       estados[6][11]=10;                              estados[6][13]=10;
        estados[7][0]=10;    estados[7][1]=10;      estados[7][2]=10;      estados[7][3]=10;    estados[7][4]=7;     estados[7][5]=10;      estados[7][6]=10;       estados[7][7]=10;       estados[7][8]=10;       estados[7][9]=10;       estados[7][11]=10;                              estados[7][13]=10;
        estados[8][0]=10;    estados[8][1]=10;      estados[8][2]=10;      estados[8][3]=10;    estados[8][4]=10;    estados[8][5]=10;      estados[8][6]=10;       estados[8][7]=10;       estados[8][8]=10;       estados[8][9]=10;       estados[8][11]=10;                              estados[8][13]=10;
        estados[9][0]=10;    estados[9][1]=15;      estados[9][2]=10;      estados[9][3]=10;    estados[9][4]=10;    estados[9][5]=10;      estados[9][6]=10;       estados[9][7]=10;       estados[9][8]=10;       estados[9][9]=10;       estados[9][11]=10;                              estados[9][13]=10;
        estados[10][0]=10;   estados[10][1]=10;     estados[10][2]=10;     estados[10][3]=10;   estados[10][4]=10;   estados[10][5]=10;     estados[10][6]=10;      estados[10][7]=10;      estados[10][8]=10;      estados[10][9]=10;      estados[10][11]=10;                             estados[10][13]=10;
        estados[11][0]=11;   estados[11][1]=11;     estados[11][2]=11;     estados[11][3]=11;   estados[11][4]=11;   estados[11][5]=11;     estados[11][6]=12;      estados[11][7]=11;      estados[11][8]=11;      estados[11][9]=11;      estados[11][11]=11;     estados[11][12]=11;     estados[11][13]=11;
        estados[12][0]=10;   estados[12][1]=10;     estados[12][2]=10;     estados[12][3]=10;   estados[12][4]=10;   estados[12][5]=10;     estados[12][6]=10;      estados[12][7]=10;      estados[12][8]=10;      estados[12][9]=10;      estados[12][11]=10;                             estados[12][13]=10;
        estados[13][0]=10;   estados[13][1]=10;     estados[13][2]=10;     estados[13][3]=10;   estados[13][4]=10;   estados[13][5]=10;     estados[13][6]=10;      estados[13][7]=10;      estados[13][8]=10;      estados[13][9]=10;      estados[13][11]=10;                             estados[13][13]=10;
        estados[14][0]=10;   estados[14][1]=10;     estados[14][2]=10;     estados[14][3]=10;   estados[14][4]=10;   estados[14][5]=10;     estados[14][6]=10;      estados[14][7]=10;      estados[14][8]=10;      estados[14][9]=10;      estados[14][11]=10;                             estados[14][13]=10;
        estados[15][0]=10;   estados[15][1]=15;     estados[15][2]=10;     estados[15][3]=10;   estados[15][4]=10;   estados[15][5]=15;     estados[15][6]=10;      estados[15][7]=10;      estados[15][8]=10;      estados[15][9]=10;      estados[15][11]=10;                             estados[15][13]=10;
        
    }
    // se recibe la linea a analizar, el path del archivo de los movimientos, el de los errores, el del archivo del 
    //documento si no hay errores y el del archivo que guarda los tokens si no hay errores.
    public Analizar(String linea, int numeroLinea,String pathMovimientos,String pathErrores) {
        
        this.linea = linea;
        this.pathMovimientos = pathMovimientos;
        this.cantidadLineas = numeroLinea;
        this.pathErrores = pathErrores;
        this.numeroLinea = numeroLinea;
        InicializarMatriz();
        System.out.println("posicicon incial: " + posicion);
        guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "\nLinea a analizar: " + linea);
        while(posicion<linea.length()){
            getToken(linea,numeroLinea);
        }
        
    }

    private void getToken(String linea, int numeroLinea) {
        
        boolean lectura = true;
        String tmp ="";
        estadoActual = 0;
        char tabulacion ='\t';
        
        //validando el texto ingresado
        
        
        while((posicion<linea.length())&&(lectura==true)){
            
                // idea = separar si el caracter es espacio y si no esta en el estado 12
            if ( (Character.isSpaceChar(linea.charAt(posicion)) && estadoActual!=11)  == true || (linea.charAt(posicion)==tabulacion ) == true  ) {
               
                lectura = false;
                System.out.println("Estado actual si encontro espacio" + estadoActual);
            }
            else{
                System.out.println("Intento evaluar el signo con espacio");
                tmp = tmp + String.valueOf(linea.charAt(posicion));
                int estadoTemporal = getSiguienteEstado(estadoActual, getIntCaracter(linea.charAt(posicion)));
                
                guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Estado actual "+ estadoActual + " caracter a leer: " + linea.charAt(posicion)+ " transicion al estado: " + estadoTemporal + " al leer el caracter: " + linea.charAt(posicion));
                          
                estadoActual = estadoTemporal;
                
                                
                if(estadoActual==10||estadoActual==5){
                    
                    errores.AgregarAlArchivo(pathErrores+".txt",tmp +  "\t\t\t\t\t(" + (numeroLinea+1) + "," + (posicion+1) + ")" );
                    guardarMovimientos.AgregarAlArchivo(pathMovimientos + ".txt", "Error en la linea " + (numeroLinea+1) + " en la posicion " + (posicion+1));
                    lectura = false;
                    marcarError=1;
                    erroresTotales = erroresTotales + marcarError;
                    System.out.println("Error en la liena " + (numeroLinea+1) + " en la psocicion " + (posicion+1));
                    
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
        
        
        //Aceptacion estadoFinal = new Aceptacion(tmp,estadoActual,pathMovimientos,posFinal);
         
        if(estadoActual==10||estadoActual==5){
            String tmplectura ="";
         for(int i = posicion; i < linea.length();i++){
                        tmplectura = tmplectura + String.valueOf(linea.charAt(i));
                    }
         //guardarMovimientos.AgregarAlArchivo(pathMovimientos+".txt","\nRECUPERACION DE ERRORES, Se leera: "+ tmplectura);
        } 
        System.out.println("Linea entera " + tmp);
         
    }
    
    
    // moverse entre estados
    private int getSiguienteEstado(int estadoActual, int caracter){
        int resultado = 10;        
        
        if(caracter >=0 && caracter <=13){
            resultado = estados[estadoActual][caracter];
        }
    
        return resultado;
    }
    
    // alfabeto del automata
    private int getIntCaracter(char caracter){
        int resultado = 10;
        
        if(Character.isLetter(caracter)){
            resultado =0;
        } else if(Character.isDigit(caracter)){
            if(caracter!='0'){
                resultado = 1;
            }else{
                resultado=5;
            }    
        } else if(caracter=='.'){
            resultado = 2;
        } else if(caracter==','){
            resultado = 2;
        }else if(caracter==';'){
            resultado = 2;
        } else if(caracter==':'){
            resultado = 2;
        } else if(Character.isMirrored(caracter)){
            resultado =3;
        }else if(caracter == '+'){
            resultado = 4;
        }else if(caracter == '-'){
            resultado = 13;
        }else if(caracter == '*'){
            resultado = 4;
        }else if(caracter == '/'){
            resultado = 4;
        }else if(caracter == '%'){
            resultado = 4;
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
        }
        
        return resultado;
    }
 
    public int getError(){
        return marcarError;
    }
}