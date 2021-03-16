package Vista;

import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class frmIndex extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();

    public frmIndex() {
        this.setContentPane(fondo);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        mnuSocios = new javax.swing.JMenu();
        mnuNuevoSocio = new javax.swing.JMenuItem();
        mnuListaSocios = new javax.swing.JMenuItem();
        mnuPeliculas = new javax.swing.JMenu();
        mnuNuevaPelicula = new javax.swing.JMenuItem();
        mnuListaPelicula = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuPrecios = new javax.swing.JMenuItem();
        menuMovimientos = new javax.swing.JMenu();
        menuAlquiler = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuContacto = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido");
        setIconImage(getIconImage());
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        jMenuBar1.setForeground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(500, 50));

        menuArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoArchivos.png"))); // NOI18N
        menuArchivo.setText("Archivos");
        menuArchivo.setPreferredSize(new java.awt.Dimension(110, 45));

        mnuSocios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoSocios.png"))); // NOI18N
        mnuSocios.setText("Socios");
        mnuSocios.setPreferredSize(new java.awt.Dimension(140, 40));

        mnuNuevoSocio.setText("Nuevo Socio");
        mnuNuevoSocio.setPreferredSize(new java.awt.Dimension(200, 40));
        mnuNuevoSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNuevoSocioActionPerformed(evt);
            }
        });
        mnuSocios.add(mnuNuevoSocio);

        mnuListaSocios.setText("Lista Socios");
        mnuListaSocios.setPreferredSize(new java.awt.Dimension(200, 40));
        mnuListaSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuListaSociosActionPerformed(evt);
            }
        });
        mnuSocios.add(mnuListaSocios);

        menuArchivo.add(mnuSocios);

        mnuPeliculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoPeliculas.png"))); // NOI18N
        mnuPeliculas.setText("Peliculas");
        mnuPeliculas.setPreferredSize(new java.awt.Dimension(140, 40));

        mnuNuevaPelicula.setText("Nueva Pelicula");
        mnuNuevaPelicula.setPreferredSize(new java.awt.Dimension(200, 40));
        mnuNuevaPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNuevaPeliculaActionPerformed(evt);
            }
        });
        mnuPeliculas.add(mnuNuevaPelicula);

        mnuListaPelicula.setText("Lista Peliculas");
        mnuListaPelicula.setPreferredSize(new java.awt.Dimension(200, 40));
        mnuListaPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuListaPeliculaActionPerformed(evt);
            }
        });
        mnuPeliculas.add(mnuListaPelicula);

        menuArchivo.add(mnuPeliculas);
        menuArchivo.add(jSeparator1);

        menuPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoPrecios.png"))); // NOI18N
        menuPrecios.setText("Precios");
        menuPrecios.setPreferredSize(new java.awt.Dimension(140, 40));
        menuPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPreciosActionPerformed(evt);
            }
        });
        menuArchivo.add(menuPrecios);

        jMenuBar1.add(menuArchivo);

        menuMovimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoMovimientos.png"))); // NOI18N
        menuMovimientos.setText("Movimientos");
        menuMovimientos.setPreferredSize(new java.awt.Dimension(130, 45));

        menuAlquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoAlquiler.png"))); // NOI18N
        menuAlquiler.setText("Alquiler");
        menuAlquiler.setPreferredSize(new java.awt.Dimension(140, 40));
        menuAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAlquilerActionPerformed(evt);
            }
        });
        menuMovimientos.add(menuAlquiler);

        jMenuBar1.add(menuMovimientos);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoAyuda.png"))); // NOI18N
        jMenu1.setText("Ayuda");
        jMenu1.setPreferredSize(new java.awt.Dimension(110, 45));

        menuContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoContacto.png"))); // NOI18N
        menuContacto.setText("Contacto");
        menuContacto.setPreferredSize(new java.awt.Dimension(140, 40));
        menuContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuContactoActionPerformed(evt);
            }
        });
        jMenu1.add(menuContacto);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPreciosActionPerformed
        frmPrecios ventanaPrecios = new frmPrecios();
        ventanaPrecios.setVisible(true);
    }//GEN-LAST:event_menuPreciosActionPerformed

    private void menuAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAlquilerActionPerformed
        frmAlquiler ventanaNuevoAlquiler = new frmAlquiler();
        ventanaNuevoAlquiler.setVisible(true);
    }//GEN-LAST:event_menuAlquilerActionPerformed

    private void mnuListaSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuListaSociosActionPerformed
        frmListaSocios ventanaListaSocios = new frmListaSocios();
        ventanaListaSocios.setVisible(true);
    }//GEN-LAST:event_mnuListaSociosActionPerformed

    private void mnuNuevoSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNuevoSocioActionPerformed
        frmSocios ventanaSocio = new frmSocios();
        ventanaSocio.setVisible(true);
    }//GEN-LAST:event_mnuNuevoSocioActionPerformed

    private void mnuListaPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuListaPeliculaActionPerformed
        frmListaPeliculas ventana = new frmListaPeliculas();
        ventana.setVisible(true);
    }//GEN-LAST:event_mnuListaPeliculaActionPerformed

    private void mnuNuevaPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNuevaPeliculaActionPerformed
        frmPelicula ventana = new frmPelicula();
        ventana.setVisible(true);
    }//GEN-LAST:event_mnuNuevaPeliculaActionPerformed

    private void menuContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuContactoActionPerformed
        frmContacto ventana = new frmContacto();
        ventana.setVisible(true);
    }//GEN-LAST:event_menuContactoActionPerformed

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
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem menuAlquiler;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuContacto;
    private javax.swing.JMenu menuMovimientos;
    private javax.swing.JMenuItem menuPrecios;
    private javax.swing.JMenuItem mnuListaPelicula;
    private javax.swing.JMenuItem mnuListaSocios;
    private javax.swing.JMenuItem mnuNuevaPelicula;
    private javax.swing.JMenuItem mnuNuevoSocio;
    private javax.swing.JMenu mnuPeliculas;
    private javax.swing.JMenu mnuSocios;
    // End of variables declaration//GEN-END:variables

    //<------ IMAGEN DE FONDO ------>
    // 1- fondo Jframe
    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoIndex.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }

    // 1- icono barra superior
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/Iconos/iconoIndex.png"));

        return retValue;
    }
}
