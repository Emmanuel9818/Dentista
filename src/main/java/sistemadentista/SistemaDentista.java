package sistemadentista;

import ClasesDentista.Cita;
import ClasesDentista.Paciente;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import utilerias.LectorDeArchivo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class SistemaDentista {
    public static void main(String[] args) throws IOException {
        MenuMain menuMain = new MenuMain();
        File fPacientes = new File( "Pacientes.txt" );
        
        while( true ){
            int opcion = menuMain.opcion();
            
            switch( opcion ){
                case 1: pacientes( fPacientes );
                        break;
                case 2: citas();
                        break;
                case 3: return;
            }
        }
    }
//-----------------------------------------------------------------------------    
    private static void pacientes( File fPacientes ) throws IOException{
        MenuPacientes menuPacientes = new MenuPacientes();
        while( true ){
            int opcion = menuPacientes.opcion();
            switch( opcion ){
                case 1: altaPaciente( fPacientes );
                        break;
                case 2: consultarPacientes();
                        break;
                case 3: return;
            }
        }
    }
//-----------------------------------------------------------------------------    
    private static void altaPaciente( File fPacientes ) throws IOException{
        String nombre;
        String telefono;
        Scanner input = new Scanner( System.in );
        
        System.out.println( "Nombre del paciente" );
        nombre = input.nextLine();
        System.out.println( "Telefono" );
        telefono = input.nextLine();
        
        Paciente paciente = new Paciente(nombre, telefono);
        
        if (!pacienteExiste(telefono)){
            if( paciente.guardarEnArchivo(fPacientes)){
                System.out.println("El paciente quedó registrado");
            }
            else
                System.out.println("El paciente no fue registrado");
        }
        else
            System.out.println("El paciente ya estaba registrado");
            
    }
    private static void consultarPacientes(){
        ArrayList<String> pacientes = new ArrayList<String>();
        LectorDeArchivo lectorDeArchivo = new LectorDeArchivo( 
                                                              "Pacientes.txt" );
        pacientes = lectorDeArchivo.LeerArchivo();
        System.out.println("Los pacientes registrados son: ");
        for( int i=0; i< pacientes.size(); i++ ){
            System.out.println(pacientes.get(i));
        }
    }
//-----------------------------------------------------------------------------
    private static ArrayList<Paciente> extraerDatosPaciente(ArrayList<String> 
                                                               pacientesString){
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        Paciente paciente;
        String linea = null;
        String [] tokensLinea = null;
        tokensLinea = new String[8];
        
        for( int i=0; i < pacientesString.size(); i++){
            linea= pacientesString.get(i);
            paciente = new Paciente();
            tokensLinea = linea.split(",");
            paciente.setNombre( tokensLinea[0] );
            paciente.setTelefono( tokensLinea[1] );
            
            pacientes.add(paciente);
        }
        return pacientes;
    }
    private static boolean pacienteExiste( String tel ){
        ArrayList<String> pacientesString = new ArrayList<String>();
        ArrayList<Paciente> paciente = new ArrayList<Paciente>();       
        LectorDeArchivo lectorDeArchivo = new LectorDeArchivo(
							    "Pacientes.txt" );
        pacientesString = lectorDeArchivo.LeerArchivo();       
        paciente = extraerDatosPaciente( pacientesString );
              
        Boolean encontrada = false;
        
        for( int i=0; i < paciente.size() ; i++ ){
            if( tel.equals(paciente.get(i).getTelefono()) ){
                encontrada = true;
            }    
        }
        if( encontrada )
            return true;
        else 
            return false;
    }
//-----------------------------------------------------------------------------    
    private static void citas(){
        MenuCitas menuCitas = new MenuCitas();
        while( true ){
            int opcion = menuCitas.opcion();
            switch( opcion ){
                case 1: altaMesTrabajo( );
                        break;
                case 2: consultarCitasMes();
                        break;
                case 3: agendarCita();
                        break;
                case 4: cancelarCita();
                       break;
                case 5: return;
            }
        }
    }
    private static void altaMesTrabajo(){
        int anio, mes;
        String mesString;
        Scanner input = new Scanner( System.in );
        do{
            System.out.println("Que mes vas a dar de alta (en numero)");
            mes = input.nextInt();
        }
        while(mes < 1 || mes >12);
        mesString = mesLetra( mes );
        System.out.println("De que año (4 digitos)");
        anio = input.nextInt();
        String nombreArchivo = anio + mesString + "Citas.txt";
        
        if( existeMesTrabajo(nombreArchivo) ){
            System.out.println( mesString + " de " + anio + " ya esta dado de "
                                                                      + "alta");
            return; 
        }
        File fMesDeTrabajo = new File( nombreArchivo);
        if( creaArchivoMesTrabajo( fMesDeTrabajo ) )
           System.out.println( "Se creó el mes de trabajo " );
        else
            System.out.println( "El mes de trabajo no se pudo crear" );
    }
    private static void consultarCitasMes(){
        int     anio;
        int     mes;
        String  mesString;
        Scanner input = new Scanner( System.in );
        do{
          System.out.println( "De qué mes quieres consultar"
                                                    + " las citas (en número)");
          mes = input.nextInt();
        }while( mes < 1 || mes > 12 );
        mesString = mesLetra( mes );
        System.out.println( "De que año (4 digitos)? ");
        anio = input.nextInt();
        String enter = input.nextLine();
        String nombreArchivo = anio + mesString + "Citas.txt";
        if( !existeMesTrabajo( nombreArchivo ) ){
            System.out.println( "El mes no está dado de alta" );
            return;
        }
        ArrayList<String> citas = new ArrayList<String>();
        LectorDeArchivo lectorDeArchivo = new LectorDeArchivo( nombreArchivo );
        citas = lectorDeArchivo.LeerArchivo();
        System.out.println("Las citas agendadas en: "+ mesString + " de " 
                                              + anio + " son las siguinetes: ");
        for( int i=0; i< citas.size() ; i++){
            System.out.println(citas.get(i));
        }
    }
    private static void agendarCita(){
        int     anio, mes;
        String  mesString;
        String  telefono;
        Scanner input = new Scanner( System.in );
        do{
            System.out.println( "En qué mes es la cita (en número)? ");
            mes = input.nextInt();
        }while( mes < 1 || mes > 12);
        
        mesString = mesLetra( mes );
        System.out.println( "De que año (4 digitos)? ");
        anio = input.nextInt();
        String enter = input.nextLine();
        String nombreArchivo = anio + mesString + "Citas.txt";
        if( !existeMesTrabajo( nombreArchivo )){
            System.out.println( "El mes no está dado de alta" );   
            return;
        }
        System.out.println( "Teléfono del paciente? ");
        telefono = input.nextLine();
        
        while( telefono.startsWith( " " ) )
        telefono = telefono.substring( 1 );
        
        if(!pacienteExiste(telefono)){
            System.out.println("No existe paciente con ese teléfono");
            return;
        }
        if( registrarCita( nombreArchivo, telefono ))
            System.out.println("La cita quedó agendada");
        else
            System.out.println("La hora ya está ocupada");
    }
    private static void cancelarCita(){
        int     anio, mes, dia;
        String  mesString;
        String  telefono;
        Scanner input = new Scanner( System.in );
        
        do{
            System.out.println( "En qué mes es la cita (en número)? ");
             mes = input.nextInt();
        }while( mes < 1 || mes > 12);
        
        mesString = mesLetra( mes );
        System.out.println( "De que año (4 digitos)? ");
        anio = input.nextInt();
        String enter = input.nextLine();
        String nombreArchivo = anio + mesString + "Citas.txt";
        
        
        if( existeMesTrabajo( nombreArchivo ) ){
            System.out.println( "Telefono del paciente" );
            telefono = input.nextLine();
            if( pacienteExiste(telefono) ){
                System.out.println( "Dia de la cita" );
                dia = input.nextInt();
                String ente = input.nextLine();
                if( borrarCitaDelMes( nombreArchivo, telefono, dia))
                    System.out.println("La cita fue cancelada");
                else
                    System.out.println("El paciente no tiene cita ese día.");
            }
            else{
                System.out.println("El paciente no esta registrado");
            }
        }
        else{
            System.out.println("El mes no está dado de alta");
        }
    }
//-----------------------------------------------------------------------------    
    private static String mesLetra( int mes ){
        switch( mes ){
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
        }
        return null;
    }
    private static boolean creaArchivoMesTrabajo( File fMesDeTrabajo ){
        Boolean exito = false;
        try{
          FileWriter w = new FileWriter( fMesDeTrabajo, true );  
                                                         
          BufferedWriter bw = new BufferedWriter(w);
          PrintWriter salida = new PrintWriter( bw );  
          salida.close();
          bw.close();
          exito = true;
        }catch(IOException e){};
          return exito;
    }
    private static boolean existeMesTrabajo( String nombreArchivo ){
       File archivo = null;
       FileReader fr = null;
       BufferedReader br = null;
       Boolean existe = false;
       try {
          archivo = new File ( nombreArchivo );
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
          existe = true; // Se pudo abrir el archivo
        }
        catch(Exception e){
           //e.printStackTrace();   
        }finally{
          try{                    
            if( null != fr ) 
               fr.close();     
          }catch (Exception e2){ 
            e2.printStackTrace();
          }
        }  
       return existe;
    }
    public static ArrayList<Cita> extraerDatosCitasEnMes( ArrayList<String>
                                                                 citasEnMesStr){
        ArrayList<Cita> citas = new ArrayList<Cita>();
        Cita cita;
        String linea = null;
        String [] tokensLinea = null;
        tokensLinea = new String[8];
        String dia;
        
        for( int i=0; i < citasEnMesStr.size(); i++){
            linea= citasEnMesStr.get(i);
            cita = new Cita();
            tokensLinea = linea.split(",");
            dia = tokensLinea[0];
            cita.setDia( Integer.parseInt(dia) );
            cita.setHora(tokensLinea[1] );
            cita.setTelefono( tokensLinea[2] );
            
            citas.add(cita);
        }
        return citas;
    }
    public static boolean registrarCita(String nombreArchivo, String tel){
        int     dia;    String  hora;
        Scanner input = new Scanner( System.in );
        System.out.println( "Qué dia quieres la cita (en número)? ");
        dia = input.nextInt();
        String enter = input.nextLine();
        File fCitasMes  = new File( nombreArchivo );
        Boolean exito = false;
        Boolean yaTieneCita = false;
        ArrayList<String> citasEnMesStr = new ArrayList<String>();
        ArrayList<Cita> citasEnMes = new ArrayList<Cita>();
        LectorDeArchivo lectorDeArchivo = new LectorDeArchivo( nombreArchivo );
        citasEnMesStr = lectorDeArchivo.LeerArchivo();
        citasEnMes = extraerDatosCitasEnMes( citasEnMesStr );
        
        for( int i=0; i < citasEnMes.size(); i++ ){
           if( citasEnMes.get(i).getTelefono().equals( tel ) && 
                                            dia == citasEnMes.get(i).getDia() )
                 yaTieneCita = true;  
        }
        if( yaTieneCita ){
            System.out.println( "El paciente ya tiene cita este día" );
            return false;
        }
        System.out.println( "A qué hora quieres la cita (hora:minutos)");
        hora = input.nextLine();
        
        if( horaLibre( dia, hora, citasEnMes ) ){
            Cita cita = new Cita( dia, hora, tel );
            exito = cita.guardarEnArchivo( fCitasMes );
            return exito;
        }
        else
            return false;
    }
    public static boolean horaLibre(int dia, String hora, ArrayList<Cita> 
                                                                    citasEnMes){
        boolean exito = true; 
        for( int i=0; i < citasEnMes.size(); i++ ){
           if( citasEnMes.get(i).getDia() == dia && 
                                     citasEnMes.get(i).getHora().equals( hora ))
               exito = false;
        }
        return exito;
    }
    public static boolean borrarCitaDelMes(String nombreArchivo, 
                                                   String telefono, int dia){
        Boolean exito = false;     
        ArrayList<String> pacientesEnCitaStr = new ArrayList<String>();
        ArrayList<Cita> pacientesEnCita = new ArrayList<Cita>();       
        LectorDeArchivo lectorDeArchivo = new LectorDeArchivo( nombreArchivo );
        pacientesEnCitaStr = lectorDeArchivo.LeerArchivo(); 
       
        pacientesEnCita = extraerDatosCitasEnMes( pacientesEnCitaStr );     
        Boolean noTieneCita = false;
        int indice = 0;
       
        for( int i=0; i < pacientesEnCita.size(); i++ ){
           if( pacientesEnCita.get(i).getTelefono().equals( telefono ) && 
                                       dia == pacientesEnCita.get(i).getDia() ){
               noTieneCita = true;
               indice = i;
           }
        }
        if( noTieneCita ){
            pacientesEnCitaStr.remove( indice );
            pacientesEnCita.remove( indice );
            actualizarCitasMes(nombreArchivo, pacientesEnCita);
            exito = true;
            return exito;
        }
        else{
            return exito;
       }
    }
    public static void actualizarCitasMes(String nombreArchivo, ArrayList<Cita>
                                                               pacientesEnCita){
        File file = new File( nombreArchivo );
        File archivo = file;
        file.delete();  
        Cita pacienteCita = new Cita();
       
        for( int i=0; i <  pacientesEnCita.size(); i++ ){
            pacienteCita =  pacientesEnCita.get(i);
            pacienteCita.guardarEnArchivo( archivo );
        }  
    }
//-----------------------------------------------------------------------------    
}

