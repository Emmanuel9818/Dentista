package sistemadentista;

import utilerias.Menu;

public class MenuMain extends Menu{
    MenuMain(){
        opciones = new String[3];
        
        opciones[0] = "Pacientes";
        opciones[1] = "Citas";
        opciones[2] = "Salir";
    }
}
