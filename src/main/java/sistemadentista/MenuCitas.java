package sistemadentista;

import utilerias.Menu;

public class MenuCitas  extends Menu{
    MenuCitas(){
        opciones = new String[5];
        
        opciones[0] = "Dar de alta a un mes de trabajo";
        opciones[1] = "Consultar las citas de un mes";
        opciones[2] = "Agendar una cita";
        opciones[3] = "Cancelar una cita";
        opciones[4] = "Regresar";
    }
}
