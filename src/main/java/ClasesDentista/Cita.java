package ClasesDentista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Cita {
    private int dia;
    private String hora;
    private String telefono;
    
    public Cita(){
    }
    
    public Cita(int dia, String hora, String telefono){
        this.dia = dia;
        this.hora = hora;
        this.telefono = telefono;   
    }
    
    public Boolean guardarEnArchivo( File f ){
    Boolean exito = false;
    try{
      // append = true para agregar sin sobreescribir                   
      FileWriter w = new FileWriter( f, true ); 
      BufferedWriter bw = new BufferedWriter(w);
      PrintWriter salida = new PrintWriter( bw );  
      // Escribimos los datos en archivo
      // Separando los campos por comas
      salida.println( dia + "," + hora + "," + telefono );  
      salida.close();
      bw.close();
      exito = true;
    }catch(IOException e){};
      return exito;
    }
    
    public int getDia(){
        return dia;
    }
    public String getHora(){
        return hora;
    }
    public String getTelefono(){
        return telefono;
    }
    
    public int setDia(int nuevoDia){
        return dia = nuevoDia;
    }
    public String setHora(String nuevaHora){
        return hora = nuevaHora;
    }
    public String setTelefono(String nuevoTel){
        return telefono = nuevoTel;
    }
}
