package utilerias;

import java.util.Scanner;

public abstract class Menu {

    public String [] opciones;

    public int opcion(){

        if( opciones.length < 1 ) return 0;

        System.out.println();

        for( int i=0; i<opciones.length; i++ )

            System.out.println( i+1 + ":" + opciones[i] );

        System.out.println();

        int opcion;

        Scanner input = new Scanner( System.in );

        do{

        System.out.println("Escribe la opción deseada:");

        opcion = input.nextInt(); // lee la opción que elige el usuario

        }while( opcion<1 || opcion>opciones.length );

        return opcion;
    }
}

