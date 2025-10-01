
package Vista;
//Integrantes: Manceñido Xenia, Ortiz Lourdes, Ortiz Santiago, Ozan Santiago
import Modelo.Alumno;
import Persistencia.AlumnoData;
import java.time.LocalDate;

public class GP12_Universidad {
    public static void main(String[] args) {
        
        AlumnoData alumnoData = new AlumnoData();

        //Crear un alumno nuevo
        Alumno alu = new Alumno(43839685, "Ortiz", "Luli", LocalDate.of(2001, 11, 22), false);
        alumnoData.guardarAlumno(alu);
        System.out.println("Alumno guardado: " + alu.getIdAlumno());

        // Buscar por id
        Alumno buscado = alumnoData.buscarAlumno(alu.getIdAlumno());
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido());
        }

        // Mostrar todos
        System.out.println("Listado de alumnos:");
        for (Alumno a : alumnoData.obtenerAlumnos()) {
            System.out.println(a.getIdAlumno() + " - " + a.getNombre() + " " + a.getApellido());
        }
    }
}
