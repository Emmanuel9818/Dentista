package utilerias;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorDeArchivo {
    private final String nombreArchivo;
    
    public LectorDeArchivo( String nombreArchivo ){
       this.nombreArchivo = nombreArchivo;
    }
    public ArrayList<String> LeerArchivo(  ){
       ArrayList<String> lectura = new ArrayList<String>();
       File archivo = null;
    
       FileReader fr = null;
       BufferedReader br = null;
       
       try {// Apertura del archivo y creacion de BufferedReader para poder
             // leer con el metodo readLine()).
          archivo = new File ( nombreArchivo );
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
          // Lectura del archivo
          String linea;
          while((linea=br.readLine())!=null)
            lectura.add(linea);
        }
        catch(IOException e){
        }finally{
          // se cierra el archivo tanto para el caso normal
          //  como para la excepción
          try{                    
            if( null != fr ){   
               fr.close();     
            }                  
          }catch (IOException e2){ 
          }
        } 
       return lectura;
    }    
}