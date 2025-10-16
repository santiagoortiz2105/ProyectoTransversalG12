package Vista;

import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.InscripcionData;
import Persistencia.MateriaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmCargaNotas extends javax.swing.JFrame {
    private List<Alumno> listaA;
    private DefaultTableModel tabla;
    private static AlumnoData alumnoData;
    private static MateriaData materiaData;
    private static InscripcionData inscripData;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmCargaNotas.class.getName());

    public frmCargaNotas() {
        initComponents();
        initComponents();
        tabla = new DefaultTableModel();
        alumnoData = new AlumnoData();
        inscripData = new InscripcionData();
        materiaData = new MateriaData();
        crearTabla();
        listaA = (ArrayList<Alumno>) alumnoData.obtenerAlumnos();
        agregarAlumno();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNotas = new javax.swing.JLabel();
        lbAlumno = new javax.swing.JLabel();
        jComboAlumno = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLista = new javax.swing.JTable();
        bGuardar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbNotas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbNotas.setText("Carga de Notas");

        lbAlumno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbAlumno.setText("Alumno");

        jComboAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboAlumnoActionPerformed(evt);
            }
        });

        jLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Materia", "Nota"
            }
        ));
        jScrollPane2.setViewportView(jLista);

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbAlumno)
                                .addGap(18, 18, 18)
                                .addComponent(jComboAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(lbNotas)))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(bGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36))))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbNotas)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAlumno)
                    .addComponent(jComboAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bGuardar)
                    .addComponent(bSalir))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jComboAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboAlumnoActionPerformed
        borrarTabla();
        String selectedItem = jComboAlumno.getSelectedItem().toString();
        String[] parts = selectedItem.split(" ");
        int id = Integer.parseInt(parts[0]);
        List<Inscripcion> lisa = new ArrayList<>();
        System.out.println("LISTA!!!!!!!!!!: "+ lisa);
        Materia mat;
        String fila[] = new String[3];
        lisa = inscripData.obtenerInscripcionesPorAlumno(id);
        System.out.println("ID: "+id);
        System.out.println("LISTA NUEVA!!!!!!!!!!: "+ lisa);
        for (int i = 0; i < lisa.size(); i++) {
            fila[0] = lisa.get(i).getMateria().getIdMateria() + "";
            mat = materiaData.buscarMateria(lisa.get(i).getMateria().getIdMateria());
            fila[1] = mat.getNombre();
            fila[2] = lisa.get(i).getNota() + "";
            System.out.println("Filas!!! "+ fila);
            tabla.addRow(fila);
        }
        jLista.setModel(tabla);
    }//GEN-LAST:event_jComboAlumnoActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        try{
            String selectedItem = jComboAlumno.getSelectedItem().toString();
            String[] parts = selectedItem.split(" ");
            int idA = Integer.parseInt(parts[0]);
            int filaSelec = jLista.getSelectedRow();
            String idMatString = (String) tabla.getValueAt(filaSelec, 0);
            int idMat = Integer.parseInt(idMatString);
            int filaSelec2 = jLista.getSelectedRow();
            String idMatString2 = (String) tabla.getValueAt(filaSelec2, 2);
            double nota = Double.parseDouble(idMatString2);
            inscripData.actualizarNota(idA, idMat, nota);
            borrarTabla();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR: Falto dar ENTER "+ex.getMessage());
        }
    }//GEN-LAST:event_bGuardarActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frmCargaNotas().setVisible(true));
    }
    
    public void crearTabla() {
        ArrayList<Object> columnas = new ArrayList<Object>();
        columnas.add("ID");
        columnas.add("nombre");
        columnas.add("nota");
        for (Object iter : columnas) {
            tabla.addColumn(iter);
        }
        jLista.setModel(tabla);
    }

    public void borrarTabla() {
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tabla.removeRow(i);
        }
    }

    public void agregarAlumno() {
        Alumno lista = new Alumno();
        for (Iterator<Alumno> it = listaA.iterator(); it.hasNext();) {
            lista = it.next();
            jComboAlumno.addItem(lista.getIdAlumno() + " " + lista.getApellido() + " " + lista.getNombre() + " " + lista.getDni());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton bSalir;
    private javax.swing.JComboBox<String> jComboAlumno;
    private javax.swing.JTable jLista;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAlumno;
    private javax.swing.JLabel lbNotas;
    // End of variables declaration//GEN-END:variables
}
