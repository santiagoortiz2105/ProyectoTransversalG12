package Persistencia;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {

    private Connection con = null;
    private AlumnoData alumnoData = new AlumnoData();
    private MateriaData materiaData = new MateriaData();

    public InscripcionData() {
        con = Conexion.getConexion();
    }

    //Guardar Inscripcion 
    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripción guardada con éxito.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar inscripción: " + ex.getMessage());
        }
    }

    //Buscar Inscripcion
    public Inscripcion buscarInscripcion(int id) {
        Inscripcion inscripcion = null;
        String sql = "SELECT * FROM inscripcion WHERE idInscripcion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getDouble("nota"));

                Alumno alumno = alumnoData.buscarAlumno(rs.getInt("idAlumno"));
                Materia materia = materiaData.buscarMateria(rs.getInt("idMateria"));

                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar inscripción: " + e.getMessage());
        }
        return inscripcion;
    }

    //Modificar Inscripcion
    public void modificarInscripcion(Inscripcion inscripcion) {
        String sql = "UPDATE inscripcion SET nota = ?, idAlumno = ?, idMateria = ? WHERE idInscripcion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(3, inscripcion.getMateria().getIdMateria());
            ps.setInt(4, inscripcion.getIdInscripcion());

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Inscripción modificada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar inscripción: " + e.getMessage());
        }
    }
    
    //Eliminar Inscripcion
    public void eliminarInscripcion(int id) {
        String sql = "DELETE FROM inscripcion WHERE idInscripcion = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Inscripción eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar inscripción: " + e.getMessage());
        }
    }

    //Listar la Inscripcion
    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                insc.setNota(rs.getDouble("nota"));

                Alumno alumno = alumnoData.buscarAlumno(rs.getInt("idAlumno"));
                Materia materia = materiaData.buscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alumno);
                insc.setMateria(materia);

                lista.add(insc);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar inscripciones: " + e.getMessage());
        }
        return lista;
    }
    
    //Materias Inscriptas de un alumno 
    public List<Materia> obtenerMateriasInscriptas(int idAlumno) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.idMateria, m.nombre, m.estado FROM materia m "
                + "JOIN inscripcion i ON m.idMateria = i.idMateria "
                + "WHERE i.idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias inscriptas: " + e.getMessage());
        }
        return materias;
    }
    
    //Materias No inscriptas de un alumno 
    public List<Materia> obtenerMateriasNoInscriptas(int idAlumno) {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria NOT IN "
                + "(SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materias no inscriptas: " + e.getMessage());
        }
        return materias;
    }

    //Alumnos inscriptos en una materia 
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT a.idAlumno, a.dni, a.apellido, a.nombre, a.fechaNacimiento, a.estado "
                + "FROM alumno a JOIN inscripcion i ON a.idAlumno = i.idAlumno "
                + "WHERE i.idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumnos por materia: " + e.getMessage());
        }
        return alumnos;
    }

    //Eliminar inscripcion segun alumno y materia 
    public void eliminarInscripcionPorAlumnoYMateria(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Inscripción eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción a eliminar.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la inscripción: " + e.getMessage());
        }
    }
    
    //Actualizar nota
    public void actualizarNota(int idA, int idM, double not){
        try {
            String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? && idMateria=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, not);
            ps.setInt(2, idA);
            ps.setInt(3, idM);
            int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Nota cambiada.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cambiar la nota.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }
    }

}
