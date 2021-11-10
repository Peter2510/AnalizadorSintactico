/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoarchivos;


import Ventanas.Inicial;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author GORDILLO G
 */
public class ManejoArchivos {

    private String texto = "";
    private String lectura;
    private int seleccion;

    public void crearArchivo(String nombreArchivo) {

        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            //System.out.println("Archivo creado");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void escribirArchivo(String nombreArchivo, String contenido) {

        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.println(contenido);
            salida.close();
            // System.out.println("Se escribio correctamente");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void AgregarAlArchivo(String nombreArchivo, String contenido) {

        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(contenido);
            salida.close();
            //System.out.println("Se ha agregado contenido correctamente");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void leerArchivo() {

        try {
            JFileChooser fc = new JFileChooser();

            seleccion = fc.showOpenDialog(fc);

            if (seleccion == JFileChooser.APPROVE_OPTION) {

                File fichero = fc.getSelectedFile();

                fichero.getAbsolutePath();

                if (fichero.exists()) {

                    try {

                        BufferedReader entrada = new BufferedReader(new FileReader(fichero));

                        String tmp = "";
                        String bfRead;

                        while ((bfRead = entrada.readLine()) != null) {

                            tmp = tmp + bfRead;

                        }

                        texto = tmp;

                        entrada.close();

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace(System.out);
                    } catch (NullPointerException e) {
                        System.out.println("Wu");
                    } catch (NumberFormatException ex) {

                    } catch (IOException ex) {
                        ex.printStackTrace(System.out);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no existe");
                }

            }
        } catch (Exception e) {
        }

    }

    public String getLectura() {
        return texto;
    }

    public String guardarArchivo(String lectura) {
        String path = "";
        JFileChooser ventanaSeleccionar = new JFileChooser();

        if (ventanaSeleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            File archivo;
            archivo = ventanaSeleccionar.getSelectedFile();

            crearArchivo(archivo.getAbsolutePath() + ".txt");
            AgregarAlArchivo(archivo.getAbsolutePath() + ".txt", lectura);
            path = archivo.getAbsolutePath();
            // System.out.println("Archivo creado en " + archivo.getAbsolutePath());
            JOptionPane.showMessageDialog(null, "Se guardo correctamente tu archivo " + archivo.getName());
            //System.out.println("Nombre del archivo" + archivo.getName());
        }else{
            JOptionPane.showMessageDialog(null, "Archivo no guardado");
        }
        return path;
    }

    public void leerArchivoLinea() {

        try {
            JFileChooser fc = new JFileChooser();

            seleccion = fc.showOpenDialog(fc);

            if (seleccion == JFileChooser.APPROVE_OPTION) {

                File fichero = fc.getSelectedFile();

                if (fichero.exists()) {

                    try {

                        BufferedReader entrada = new BufferedReader(new FileReader(fichero));

                        String linea = entrada.readLine();

                        while (linea != null) {

                            Inicial.txt.append(linea + "\n");
                            linea = entrada.readLine();
                        }

                        entrada.close();

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace(System.out);
                    } catch (NullPointerException e) {
                        System.out.println("Wu");
                    } catch (NumberFormatException ex) {

                    } catch (IOException ex) {
                        ex.printStackTrace(System.out);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no existe");
                }

            }
        } catch (Exception e) {
            
        }

    }   
    
    public String[] pedirPaht() {
        String path = "";
        String nombre="";
        JFileChooser ventanaSeleccionar = new JFileChooser();
        String[] dato = new String[2];

        if (ventanaSeleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            File archivo;
            archivo = ventanaSeleccionar.getSelectedFile();
            nombre= archivo.getName();
            path = archivo.getAbsolutePath()+".txt";
            dato[0]=nombre;
            dato[1]=path;
            // System.out.println("Archivo creado en " + archivo.getAbsolutePath());

            //System.out.println("Nombre del archivo" + archivo.getName());
        }
        return dato;
    }

}
