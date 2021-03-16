package Vista;

import Modelo.*;
import Controlador.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class frmNuevoAlquiler extends javax.swing.JFrame {

    DefaultTableModel modeloCargarDatos = new DefaultTableModel();
    ArrayList<DetalleAlquiler> detallePeliculas = new ArrayList<DetalleAlquiler>();
    FondoPanel fondo = new FondoPanel();
    int total = 0;

    public frmNuevoAlquiler() {
        initComponents();
    }

    public frmNuevoAlquiler(Socio s) {
        this.setContentPane(fondo);
        initComponents();
        lblNroSocio.setText(Integer.toString(s.getIdSocio()));
        lblNomApeSocio.setText(s.getNombre());
        lblDniSocio.setText(Integer.toString(s.getDni()));
        tblPeliculasElegidas.setModel(modeloTablas());
        tablaPeliculas();
        comboMetodoPagos();
    }

    //<------ TABLA: FORMATO Y CARGA DE DATOS ------>
    // 1- Modelado de tabla.
    public DefaultTableModel modeloTablas() {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int columnas) {
                if (columnas == 6) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        modelo.setColumnIdentifiers(new String[]{"N°", "Titulo", "Genero", "Copias", "Tipo", "Precio"});
        return modelo;
    }

    // 2 - Determinar tamaño de las celdas de la tabla.
    private void tamañoCeldas(JTable tabla) {
        //Ancho de Columnas y bloqueo de edicion de tamaño
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(1).setResizable(false);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setResizable(false);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setResizable(false);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(4).setResizable(false);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setResizable(false);
        //Alto de filas
        tabla.setRowHeight(30);
    }

    // 3- Rellenar la tabla con la información.
    private void tablaPeliculas() {
        GestorBD gestor = new GestorBD();
        ArrayList<Pelicula> listaPeliculas = gestor.listaPeliculas();
        Precio pr = gestor.consultarPrecio();
        DefaultTableModel modelo = modeloTablas();

        for (Pelicula p : listaPeliculas) {
            int precio = 0;
            if (p.getTipo().equals("Estreno")) {
                precio = pr.getPrecioEstreno();
            } else {
                precio = pr.getPrecioComun();
            }

            modelo.addRow(new Object[]{p.getIdPelicula(), p.getTitulo(), p.getGenero().getGenero(), p.getCantidad(), p.getTipo(), precio});
        }
        tblPeliculas.setModel(modelo);
        tamañoCeldas(tblPeliculas);
    }

    //<------ COMBO BOX ------>
    // 1- Carga de datos en comboBox
    public void comboMetodoPagos() {
        GestorBD gestor = new GestorBD();
        ArrayList<MetodoPago> listaMetPago = gestor.listaMetPago();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (MetodoPago mp : listaMetPago) {
            modelo.addElement(mp.toString());
        }
        cboMetodoPago.setModel(modelo);
    }

    //<------ BOTONES ------>
    // 1- Buscar pelicula.
    private void buscarPelicula() {
        if (txtBuscador.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el dato de la pelicula para continuar", "Campo vacio", JOptionPane.ERROR_MESSAGE);
        } else {
            int idPelicula = 0;
            String titulo = null;
            if (rbtNroPelicula.isSelected()) {
                idPelicula = Integer.parseInt(txtBuscador.getText());
            } else {
                titulo = txtBuscador.getText();
            }

            GestorBD gestor = new GestorBD();
            ArrayList<Pelicula> listaPeliculas = gestor.buscarPelicula(idPelicula, titulo);
            Precio pr = gestor.consultarPrecio();
            DefaultTableModel modelo = modeloTablas();

            for (Pelicula p : listaPeliculas) {
                int precio = 0;
                if (p.getTipo().equals("Estreno")) {
                    precio = pr.getPrecioEstreno();
                } else {
                    precio = pr.getPrecioComun();
                }

                modelo.addRow(new Object[]{p.getIdPelicula(), p.getTitulo(), p.getGenero().getGenero(), p.getCantidad(), p.getTipo(), precio});
            }
            tblPeliculas.setModel(modelo);
            tamañoCeldas(tblPeliculas);
        }
    }

    // 2- Añadir pelicula.
    private void añadirPelicula() {
        int fila = tblPeliculas.getSelectedRow();
        if (fila != -1) {//OBTENER DATOS DE LA TABLA
            TableModel modelo = tblPeliculas.getModel();

            int idPelicula = (int) modelo.getValueAt(fila, 0);
            String titulo = (String) modelo.getValueAt(fila, 1);
            String genero = (String) modelo.getValueAt(fila, 2);
            int cantidad = (int) modelo.getValueAt(fila, 3);
            String tipo = (String) modelo.getValueAt(fila, 4);
            Pelicula p = new Pelicula();
            p.setIdPelicula(idPelicula);
            p.setTitulo(titulo);
            p.setCantidad(cantidad);
            p.setTipo(tipo);

            Genero g = new Genero();
            g.setGenero(genero);
            p.setGenero(g);

            Precio pr = new Precio();

            if (tipo.equals("Estreno")) {
                pr.setPrecioEstreno((int) modelo.getValueAt(fila, 5));
            } else {
                pr.setPrecioComun((int) modelo.getValueAt(fila, 5));
            }

            DetalleAlquiler da = new DetalleAlquiler();
            da.setPelicula(p);
            da.setPrecio(pr);

            modeloCargarDatos = modeloTablas();
            detallePeliculas.add(da);

            int precio = 0;
            for (DetalleAlquiler dal : detallePeliculas) {
                if (dal.getPelicula().getTipo().equals("Común")) {
                    precio = dal.getPrecio().getPrecioComun();
                } else {
                    precio = dal.getPrecio().getPrecioEstreno();
                }
                modeloCargarDatos.addRow(new Object[]{dal.getPelicula().getIdPelicula(), dal.getPelicula().getTitulo(), dal.getPelicula().getGenero().getGenero(), dal.getPelicula().getCantidad(), dal.getPelicula().getTipo(), precio});
            }
            total = total + precio;
            lblTotal.setText(Integer.toString(total));
            tblPeliculasElegidas.setModel(modeloCargarDatos);
            tamañoCeldas(tblPeliculasElegidas);
            txtBuscador.setText(null);
            tablaPeliculas();

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una pelicula.", "Pelicula sin seleccionar", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 3- Quitar pelicula.
    private void quitarPelicula(DefaultTableModel modelo) {

        int fila = tblPeliculasElegidas.getSelectedRow();

        int precio = (int) tblPeliculasElegidas.getValueAt(fila, 5);
        if (fila > -1) {
            modelo.removeRow(fila);
            detallePeliculas.remove(fila);
        }
        total = total - precio;
        lblTotal.setText(Integer.toString(total));
    }

    // 4- Guardar cambios.
    private void guardar() {
        if (lblTotal.getText().isEmpty() || lblTotal.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Debe añadir al menos una pelicula a la lista.", "Lista vacia", JOptionPane.ERROR_MESSAGE);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operación?", "Guardar cambios.", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                GestorBD gestor = new GestorBD();
                //Insertar Alquiler
                int idSocio = Integer.parseInt(lblNroSocio.getText());
                Socio s = new Socio();
                s.setIdSocio(idSocio);

                int idMetodoPago = cboMetodoPago.getSelectedIndex() + 1;
                MetodoPago mp = new MetodoPago();
                mp.setIdMetodoPago(idMetodoPago);

                int total = Integer.parseInt(lblTotal.getText());

                Alquiler a = new Alquiler();
                a.setSocio(s);
                a.setMetodoPago(mp);
                a.setTotal(total);
                gestor.insertarAlquiler(a);
                //-------------------------
                //insertar detalle alquiler
                Alquiler al = gestor.ultimoAlquiler();

                for (DetalleAlquiler da : detallePeliculas) {
                    int idPelicula = da.getPelicula().getIdPelicula();
                    Pelicula p = new Pelicula();
                    p.setIdPelicula(idPelicula);

                    String estado = "Alquilado";

                    Precio pr = gestor.consultarPrecio();

                    DetalleAlquiler DeAl = new DetalleAlquiler(al, p, pr, estado);
                    gestor.insertarDetalleAlquiler(DeAl);
                    dispose();
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        rbtTitulo = new javax.swing.JRadioButton();
        rbtNroPelicula = new javax.swing.JRadioButton();
        txtBuscador = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPeliculasElegidas = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblNroSocio = new javax.swing.JLabel();
        lblNomApeSocio = new javax.swing.JLabel();
        lblDniSocio = new javax.swing.JLabel();
        lblNroSocio1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNroSocio2 = new javax.swing.JLabel();
        lblNroSocio3 = new javax.swing.JLabel();
        lblDescripcion2 = new javax.swing.JLabel();
        cboMetodoPago = new javax.swing.JComboBox<>();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblDescripcion3 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 500));

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
        jScrollPane1.setViewportView(tblPeliculas);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonBuscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtTitulo);
        rbtTitulo.setText("Titulo");

        buttonGroup1.add(rbtNroPelicula);
        rbtNroPelicula.setSelected(true);
        rbtNroPelicula.setText("N° Pelicula");

        jLabel16.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Añadir");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Meiryo UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("¡Realice un nuevo alquiler!");

        lblDescripcion.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcion.setText("Añada las peliculas que desea alquilar.");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonAñadir.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDescripcion)
                        .addGap(0, 247, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(rbtNroPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(37, 37, 37))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbtNroPelicula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(14, 14, 14))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 500));

        tblPeliculasElegidas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPeliculasElegidas);

        jLabel17.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Quitar");

        jLabel18.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Guardar");

        jLabel19.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Salir");

        lblNroSocio.setBackground(new java.awt.Color(0, 0, 0));
        lblNroSocio.setFont(new java.awt.Font("Meiryo UI", 1, 14)); // NOI18N
        lblNroSocio.setForeground(new java.awt.Color(0, 0, 0));
        lblNroSocio.setText("N°");

        lblNomApeSocio.setBackground(new java.awt.Color(0, 0, 0));
        lblNomApeSocio.setFont(new java.awt.Font("Meiryo UI", 1, 14)); // NOI18N
        lblNomApeSocio.setForeground(new java.awt.Color(0, 0, 0));
        lblNomApeSocio.setText("Nombre, Apellido");

        lblDniSocio.setBackground(new java.awt.Color(0, 0, 0));
        lblDniSocio.setFont(new java.awt.Font("Meiryo UI", 1, 14)); // NOI18N
        lblDniSocio.setForeground(new java.awt.Color(0, 0, 0));
        lblDniSocio.setText("DNI");

        lblNroSocio1.setBackground(new java.awt.Color(0, 0, 0));
        lblNroSocio1.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblNroSocio1.setForeground(new java.awt.Color(0, 0, 0));
        lblNroSocio1.setText("Nro Socio:");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Meiryo UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("¡Detalles del alquiler!");

        lblNroSocio2.setBackground(new java.awt.Color(0, 0, 0));
        lblNroSocio2.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblNroSocio2.setForeground(new java.awt.Color(0, 0, 0));
        lblNroSocio2.setText("Socio:");

        lblNroSocio3.setBackground(new java.awt.Color(0, 0, 0));
        lblNroSocio3.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblNroSocio3.setForeground(new java.awt.Color(0, 0, 0));
        lblNroSocio3.setText("DNI:");

        lblDescripcion2.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblDescripcion2.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcion2.setText("Forma de pago:");

        cboMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonSalir.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonGuardar.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblDescripcion3.setFont(new java.awt.Font("Meiryo UI", 1, 12)); // NOI18N
        lblDescripcion3.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcion3.setText("Total: $");

        lblTotal.setBackground(new java.awt.Color(0, 0, 0));
        lblTotal.setFont(new java.awt.Font("Meiryo UI", 1, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal.setText("0");

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/botonEliminar.png"))); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(60, 60, 60))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblNroSocio1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNroSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNroSocio2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNomApeSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblDescripcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblDescripcion3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotal))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblNroSocio3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDniSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNroSocio2)
                        .addComponent(lblNomApeSocio)
                        .addComponent(lblNroSocio3)
                        .addComponent(lblDniSocio))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNroSocio1)
                        .addComponent(lblNroSocio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDescripcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPelicula();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        añadirPelicula();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        quitarPelicula(modeloCargarDatos);
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(frmNuevoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNuevoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNuevoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNuevoAlquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNuevoAlquiler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboMetodoPago;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescripcion2;
    private javax.swing.JLabel lblDescripcion3;
    private javax.swing.JLabel lblDniSocio;
    private javax.swing.JLabel lblNomApeSocio;
    private javax.swing.JLabel lblNroSocio;
    private javax.swing.JLabel lblNroSocio1;
    private javax.swing.JLabel lblNroSocio2;
    private javax.swing.JLabel lblNroSocio3;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JRadioButton rbtNroPelicula;
    private javax.swing.JRadioButton rbtTitulo;
    private javax.swing.JTable tblPeliculas;
    private javax.swing.JTable tblPeliculasElegidas;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
    //<------ IMAGEN DE FONDO ------>
    // 1- Fondo Jframe
    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoPrecios.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
