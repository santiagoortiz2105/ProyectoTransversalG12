
package Persistencia;
//Integrantes: Manceñido Xenia, Ortiz Lourdes, Ortiz Santiago, Ozan Santiago 
import Modelo.Alumno;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AlumnoData {
    private Connection con;
    
    public AlumnoData() {
        con = Conexion.getConexion();
    }
    
    // Guardar Alumno
    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, alumno.getDni());
        ps.setString(2, alumno.getApellido());
        ps.setString(3, alumno.getNombre());
        ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
        ps.setBoolean(5, alumno.isEstado());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            alumno.setIdAlumno(rs.getInt(1));
        }

        ps.close();
    } catch (SQLException ex) {
        System.out.println("Error al guardar alumno: " + ex.getMessage());
    }
    }
    
    
    // Buscar Alumno por id
    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno(
                    rs.getInt("dni"),
                    rs.getString("apellido"),
                    rs.getString("nombre"),
                    rs.getDate("fechaNacimiento").toLocalDate(),
                    rs.getBoolean("estado")
                );
                alumno.setIdAlumno(rs.getInt("idAlumno"));
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar alumno: " + ex.getMessage());
        }
        return alumno;
    }
     // Obtener todos los alumnos
    public List<Alumno> obtenerAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno(
                    rs.getInt("dni"),
                    rs.getString("apellido"),
                    rs.getString("nombre"),
                    rs.getDate("fechaNacimiento").toLocalDate(),
                    rs.getBoolean("estado")
                );
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener alumnos: " + ex.getMessage());
        }
        return alumnos;
    }
}
