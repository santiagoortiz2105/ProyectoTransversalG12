package Vista;

import Modelo.Alumno;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.MateriaData;
import Persistencia.InscripcionData;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class frmListadodeAlumnosporMateria extends javax.swing.JInternalFrame {
    
  private MateriaData materiaData;
    private InscripcionData inscripcionData;
    private DefaultTableModel modelo;

 
    public frmListadodeAlumnosporMateria() {
        initComponents();
        materiaData = new MateriaData();
        inscripcionData = new InscripcionData();
        modelo = new DefaultTableModel();
        armarCabeceraTabla();
        cargarMaterias();
        
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
      @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        cargarAlumnosPorMateria();
    }
});
    }

    private void armarCabeceraTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha de Nacimiento");
        modelo.addColumn("Estado");
        jTable1.setModel(modelo);
    } 
     private void cargarMaterias() {
         jComboBox1.removeAllItems();
    ArrayList<Materia> materias = (ArrayList<Materia>) materiaData.listarMaterias();
    for (Materia m : materias) {
        jComboBox1.addItem(m.getNombre()); 
    }
    }
    
    private void borrarFilasTabla() {
       int a = modelo.getRowCount() - 1;
       for (int i = a; i >= 0; i--) {
       modelo.removeRow(i);
        }
    }
    
     private void cargarAlumnosPorMateria() {
    borrarFilasTabla();
    String nombreMateria = (String) jComboBox1.getSelectedItem();
    Materia materiaSeleccionada = null;

    for (Materia m : materiaData.listarMaterias()) {
        if (m.getNombre().equals(nombreMateria)) {
            materiaSeleccionada = m;
            break;
        }
    }

    if (materiaSeleccionada != null) {
        ArrayList<Alumno> alumnos = (ArrayList<Alumno>) 
            inscripcionData.obtenerAlumnosPorMateria(materiaSeleccionada.getIdMateria());
        for (Alumno a : alumnos) {
            modelo.addRow(new Object[]{
                a.getIdAlumno(),
                a.getDni(),
                a.getApellido(),
                a.getNombre(),
                a.getFechaNacimiento(),
                a.isEstado() ? "Activo" : "Inactivo"
            });
        }
    }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Listado De Alumnos Por Materias");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Materia:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Dni", "Apellido", "Nombre", "Fecha de Nacimiento", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 31, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
