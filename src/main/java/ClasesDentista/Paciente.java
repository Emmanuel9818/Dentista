package ClasesDentista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Paciente {
    private String nombre;
    private String telefono;
    
    public Paciente(){
    }
    
    public Paciente(String nombre, String telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public Boolean guardarEnArchivo( File f){
     Boolean exito = false;
     try{
         FileWriter w = new FileWriter( f, true ); 
         BufferedWriter bw = new BufferedWriter(w);
         PrintWriter salida = new PrintWriter( bw );  
         salida.println( nombre + "," + telefono ); 
         salida.close();
         bw.close();
         exito = true;
     }catch(IOException e){};
        return exito;   
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getTelefono(){
        return telefono;
    }
    
    public String setNombre(String nuevoNombre){
        return nombre = nuevoNombre;
    }
    public String setTelefono(String nuevoTel){
        return telefono = nuevoTel;
    }
}
