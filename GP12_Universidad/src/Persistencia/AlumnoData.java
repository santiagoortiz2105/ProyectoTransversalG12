
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
import javax.swing.JOptionPane;

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
    
    public Alumno buscarAlumnoPorDni(int dni) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno WHERE dni=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("dni"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno " + ex.getMessage());
        }

        return alumno;
    }
    
    public void modificarAlumno(Alumno alumno) {

        String sql = "UPDATE alumno SET dni = ? , apellido = ?, nombre = ?, "
                + "fechaNacimiento = ?, estado = ? WHERE  idAlumno = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El alumno no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno " + ex.getMessage());
        }

    }
    
    public void eliminarAlumno(int id) {
        try {
            String sql = "UPDATE alumno SET estado = 0 WHERE idAlumno = ? AND estado = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBoolean(2, true);
            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó el alumno.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
        }
    }
    
    public void activarAlumno(int id) {

        try {
            String sql = "UPDATE alumno SET estado = 1 WHERE idAlumno = ? AND estado = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setBoolean(2, false);
            int fila = ps.executeUpdate();
            ps.close();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se activo el alumno.");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
    }
}
