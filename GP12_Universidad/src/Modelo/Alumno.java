
package Modelo;
//Integrantes: Manceñido Xenia, Ortiz Lourdes, Ortiz Santiago, Ozan Santiago
import java.time.LocalDate;

public class Alumno {
    private int idAlumno= -1;   // CP, autoincremental
    private int dni;            //Unique
    private String apellido;    //Varchar
    private String nombre;      //Varchar
    private LocalDate fechaNacimiento;    //Date
    private boolean estado;      // Tinyint (true = activo, false = inactivo)
    
    //Constructores 

    public Alumno() {
    }
    

    public Alumno(int dni, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }
    
    //Getters y Setters

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    // --- ToString ---
    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", dni=" + dni +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estado=" + estado +
                '}';
    }
    
}
