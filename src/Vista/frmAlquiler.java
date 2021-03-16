package Vista;

import Controlador.GestorBD;
import Modelo.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class frmAlquiler extends javax.swing.JFrame {

    private GestorBD gestor = new GestorBD();
    ArrayList<DetalleAlquiler> listaAlqPorSoc = new ArrayList<DetalleAlquiler>();
    ArrayList<Socio> listaSocio = new ArrayList<Socio>();
    FondoPanel fondo = new FondoPanel();

    public frmAlquiler() {
        this.setContentPane(fondo);
        initComponents();
        modeloTabla();
        btnNuevoAlquiler.setEnabled(false);
        btnGuardarCambios.setEnabled(false);
    }

    //<------ TABLA: FORMATO Y CHECKBOX ------>
    // 1 - Modelado de tabla.
    private DefaultTableModel modeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columnas) {
                if (columnas == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        modelo.setColumnIdentifiers(new String[]{"N° Alq.", "Fecha°", "Título", "Tipo", "Estado", "Devuelto"});
        tblPeliculas.setModel(modelo);
        tamañoCeldas(tblPeliculas);
        return modelo;
    }

    // 2 - Determinar tamaño de las celdas de la tabla
    private void tamañoCeldas(JTable tabla) {
        //Ancho de Columnas y bloqueo de edicion de tamaño
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(1).setResizable(false);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setResizable(false);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setResizable(false);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setResizable(false);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(5).setResizable(false);
        //Alto de filas
        tabla.setRowHeight(30);
    }

    // 3 - Añadir checkbox en una columna de la tabla. 
    private boolean agregarCheckBox(String estado) {
        //toma la columna de la tabla.
        TableColumn columna = tblPeliculas.getColumnModel().getColumn(5);
        //dibuja en la columna un checkbox.
        columna.setCellRenderer(tblPeliculas.getDefaultRenderer(Boolean.class));
        //Permite que sea editable.
        columna.setCellEditor(tblPeliculas.getDefaultEditor(Boolean.class));

        if (estado.equals("Devuelto")) {
            return true;
        } else {

            return false;
        }
    }

    // 4- Metodo para conocer si el checkbox dentro de la tabla esta seleccionado.
    private boolean estaSeleccionado(int fila, int columna, JTable tabla) {
        return tabla.getValueAt(fila, columna) != null;
    }

    //<------ BOTONES ------>
    // 1- Boton buscar socios.
    private void botonBuscarSocios() {
        if (txtBuscarSocio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el N° o DNI del socio.", "Campo vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel modelo = modeloTabla();
            int dato = Integer.parseInt(txtBuscarSocio.getText());
            int indice = cboBuscarSocio.getSelectedIndex();

            listaSocio = gestor.buscarSocio(indice, dato);
            for (Socio s : listaSocio) {
                txtIdSocio.setText(Integer.toString(s.getIdSocio()));
                txtNomApeSocio.setText(s.getNombre() + ", " + s.getApellido());
                txtDni.setText(Integer.toString(s.getDni()));
            }
            if (listaSocio.isEmpty()) {
                txtIdSocio.setText(null);
                txtNomApeSocio.setText(null);
                txtDni.setText(null);
                txtBuscarSocio.setText(null);
                JOptionPane.showMessageDialog(null, "Los datos del socio son incorrectos.", "Datos errones", JOptionPane.ERROR_MESSAGE);
            } else {
                listaAlqPorSoc = gestor.listaAlqPorSoc(indice, dato);

                for (DetalleAlquiler da : listaAlqPorSoc) {
                    modelo.addRow(new Object[]{da.getIdDetalleAlquiler(), da.getAlquiler().getFecha(), da.getPelicula().getTitulo(), da.getPelicula().getTipo(), da.getEstado(), agregarCheckBox(da.getEstado())});
                }

                btnNuevoAlquiler.setEnabled(true);
                btnGuardarCambios.setEnabled(true);
            }
        }
    }

    // 2- Boton nuevo alquiler.
    private void botonNuevoAlquiler() {
        if (txtIdSocio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un socio para poder continuar", "Socio inexistente", JOptionPane.ERROR_MESSAGE);
        } else {
            int idSocio = Integer.parseInt(txtIdSocio.getText());
            String nombre = txtNomApeSocio.getText();
            int dni = Integer.parseInt(txtDni.getText());
            Socio s = new Socio();
            s.setIdSocio(idSocio);
            s.setNombre(nombre);
            s.setDni(dni);

            frmNuevoAlquiler ventanaNuevoAlquiler = new frmNuevoAlquiler(s);
            ventanaNuevoAlquiler.setVisible(true);
            dispose();
        }
    }

    // 3- Boton guardar.
    private void guardarCambios() {

        int fila = tblPeliculas.getSelectedRow();
        if (fila != -1) {
            int idDetalleAlquiler = (int) tblPeliculas.getValueAt(fila, 0);
            DetalleAlquiler da = new DetalleAlquiler();
            da.setIdDetalleAlquiler(idDetalleAlquiler);

            if (estaSeleccionado(fila, 5, tblPeliculas)) {
                gestor.modificarDetalleAlquiler(da);
            }
            botonBuscarSocios();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una devolución", "Elemento inexistente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        cboBuscarSocio = new javax.swing.JComboBox<>();
        txtBuscarSocio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtIdSocio = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtNomApeSocio = new javax.swing.JTextField();
        btnNuevoAlquiler = new javax.swing.JButton();
        btnGuardarCambios = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cboBuscarSocio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N° Socio", "DNI" }));

        txtBuscarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarSocioActionPerformed(evt);
            }
        });
        txtBuscarSocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarSocioKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonBuscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtIdSocio.setBackground(new java.awt.Color(204, 204, 204));
        txtIdSocio.setFocusable(false);
        txtIdSocio.setPreferredSize(new java.awt.Dimension(50, 30));

        txtDni.setBackground(new java.awt.Color(204, 204, 204));
        txtDni.setFocusable(false);

        txtNomApeSocio.setBackground(new java.awt.Color(204, 204, 204));
        txtNomApeSocio.setFocusable(false);

        btnNuevoAlquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonNuevaPelicula.png"))); // NOI18N
        btnNuevoAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAlquilerActionPerformed(evt);
            }
        });

        btnGuardarCambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonGuardar.png"))); // NOI18N
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonSalir.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblPeliculas);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Meiryo UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("¡Bienvenido al historial de socios!");

        lblDescripcion.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcion.setText("Ingrese la información del socio para conocer su historial de alquileres.");

        jLabel13.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("N° Socio:");

        jLabel14.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Socio:");

        jLabel15.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("DNI:");

        jLabel16.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nuevo Alquiler");

        jLabel17.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Guardar Cambios");

        jLabel18.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Salir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomApeSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDescripcion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboBuscarSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscarSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(56, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addComponent(btnNuevoAlquiler))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardarCambios)
                        .addGap(9, 9, 9)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnSalir)
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(113, 113, 113))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBuscarSocio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarSocio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomApeSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarCambios)
                            .addComponent(btnSalir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevoAlquiler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        botonBuscarSocios();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAlquilerActionPerformed
        botonNuevoAlquiler();
    }//GEN-LAST:event_btnNuevoAlquilerActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        guardarCambios();
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void txtBuscarSocioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSocioKeyTyped
        // validacion
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null, "Solo puede ingresar valores númericos.", "Valores númericos", JOptionPane.ERROR_MESSAGE);
        }

        if (Character.isWhitespace(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null, "Solo puede ingresar valores númericos.", "Espacios", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_txtBuscarSocioKeyTyped

    private void txtBuscarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarSocioActionPerformed

    }//GEN-LAST:event_txtBuscarSocioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAlquiler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnNuevoAlquiler;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboBuscarSocio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JTable tblPeliculas;
    private javax.swing.JTextField txtBuscarSocio;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtNomApeSocio;
    // End of variables declaration//GEN-END:variables

    //<------ IMAGEN DE FONDO ------>
    // 1- fondo Jframe
    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoAlquiler.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
