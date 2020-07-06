package sistemadentista;

import utilerias.Menu;

public class MenuPacientes extends Menu{
    MenuPacientes(){
        opciones = new String[3];
        
        opciones[0] = "Dar de alta a un paciente";
        opciones[1] = "Consultar pacientes";
        opciones[2] = "Regresar";
    }
}
