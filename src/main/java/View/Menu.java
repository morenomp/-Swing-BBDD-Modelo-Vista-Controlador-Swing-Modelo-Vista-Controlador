package View;

//NETBEANS
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
    
//PROYECTO
import static Controller.Controlador.*;
import DAO_Controller.DAOSQL;
import Model.*;
import Excepcion.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
        
        //Cambiar color de fondo
        getContentPane().setBackground(new Color(0, 24, 78));
        
        setIconImage(new ImageIcon(getClass().getResource("/imgs/inicio.png")).getImage());
        
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        crearciudadano = new javax.swing.JButton();
        crearplaneta = new javax.swing.JButton();
        baja = new javax.swing.JButton();
        modificar = new javax.swing.JToggleButton();
        buscar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        setMaximumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(859, 640));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 24, 78));
        jPanel2.setMinimumSize(new java.awt.Dimension(1060, 600));
        jPanel2.setPreferredSize(new java.awt.Dimension(860, 620));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 24, 78));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        crearciudadano.setBackground(new java.awt.Color(255, 250, 250));
        crearciudadano.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        crearciudadano.setForeground(new java.awt.Color(51, 51, 51));
        crearciudadano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/crearSer 2.jpg"))); // NOI18N
        crearciudadano.setToolTipText("");
        crearciudadano.setBorder(null);
        crearciudadano.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crearciudadano.setMaximumSize(new java.awt.Dimension(0, 0));
        crearciudadano.setMinimumSize(new java.awt.Dimension(0, 0));
        crearciudadano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearciudadanoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(crearciudadano, gridBagConstraints);

        crearplaneta.setBackground(new java.awt.Color(255, 250, 250));
        crearplaneta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        crearplaneta.setForeground(new java.awt.Color(51, 51, 51));
        crearplaneta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/crearPlaneta 2.jpg"))); // NOI18N
        crearplaneta.setBorder(null);
        crearplaneta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crearplaneta.setMinimumSize(new java.awt.Dimension(100, 25));
        crearplaneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearplanetaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(crearplaneta, gridBagConstraints);

        baja.setBackground(new java.awt.Color(255, 250, 250));
        baja.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        baja.setForeground(new java.awt.Color(51, 51, 51));
        baja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/eliminarSer 2.jpg"))); // NOI18N
        baja.setToolTipText("");
        baja.setBorder(null);
        baja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        baja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(25, 5, 25, 5);
        jPanel1.add(baja, gridBagConstraints);

        modificar.setBackground(new java.awt.Color(255, 250, 250));
        modificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modificar.setForeground(new java.awt.Color(51, 51, 51));
        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/modificar 2.jpg"))); // NOI18N
        modificar.setBorder(null);
        modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(modificar, gridBagConstraints);

        buscar.setBackground(new java.awt.Color(255, 250, 250));
        buscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buscar.setForeground(new java.awt.Color(51, 51, 51));
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar 2.jpg"))); // NOI18N
        buscar.setBorder(null);
        buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(buscar, gridBagConstraints);

        salir.setBackground(new java.awt.Color(255, 250, 250));
        salir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        salir.setForeground(new java.awt.Color(51, 51, 51));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/salir 2.jpg"))); // NOI18N
        salir.setBorder(null);
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(salir, gridBagConstraints);

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 860, 320));

        jLabel4.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/startucom.jpg"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 220));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/final startucom.jpg"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 860, 60));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

        DAOSQL daoBuscar = new DAOSQL();
        System.out.println("[Menu] Entra a Buscar");

        try {
            System.out.println("[Menu] Entra a try");

            if (daoBuscar.getCiudadano()) {

                System.out.println("[Menu] Entra a if");

                Buscar buscar = new Buscar(this, true);
                System.out.println("[Menu] Entra a new Buscar");
                buscar.setLocationRelativeTo(null);
                System.out.println("[Menu] Entra a setLocationRelativeTo");
                buscar.setVisible(true);
                System.out.println("[Menu] Entra a setVisible");

            } else {
                System.out.println("[Menu] Entra a else");
                JOptionPane.showMessageDialog(this, "No hay ningun ciudadano creado",
                    "No existen ciudadanos", JOptionPane.ERROR_MESSAGE);
                System.out.println("[Menu] Entra a showMessageDialog");
            }
        } catch (DAO_Excep ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed

        DAOSQL daoModify = new DAOSQL();
        System.out.println("[Menu] Entra a Modificar");

        try {
            if (daoModify.getCiudadano()) {

                BuscarModificar modificar = new BuscarModificar(this, true);
                modificar.setLocationRelativeTo(null);
                modificar.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(this, "No hay ningun ciudadano creado",
                    "No existen ciudadanos", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DAO_Excep ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modificarActionPerformed

    private void bajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaActionPerformed

        DAOSQL daoBaja = new DAOSQL();
        System.out.println("[Menu] Entra a Baja");

        try {
            //Le pasaremos readPlanet, para que nos verifique con isEmpty
            //si existen planetas creados
            if (daoBaja.readPlanet().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay ningun planeta creado",
                    "No existen planetas", JOptionPane.ERROR_MESSAGE);
            } else {
                Baja baja = new Baja(this, true);
                baja.setLocationRelativeTo(null);
                baja.setVisible(true);
            }
        } catch (DAO_Excep ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bajaActionPerformed

    private void crearplanetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearplanetaActionPerformed
        CrearPlaneta crearplaneta = new CrearPlaneta(this, true);
        crearplaneta.setLocationRelativeTo(null);
        crearplaneta.setVisible(true);
    }//GEN-LAST:event_crearplanetaActionPerformed

    private void crearciudadanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearciudadanoActionPerformed

        DAOSQL daoCrearC = new DAOSQL();
        System.out.println("[Menu] Entra a CrearCiudadano");

        try {
            //Le pasaremos readPlanet, para que nos verifique con isEmpty
            //si existen planetas creados
            if (daoCrearC.readPlanet().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay ningun planeta creado",
                    "No existen planetas", JOptionPane.ERROR_MESSAGE);
            } else {
                CrearCiudadano crearciudadano = new CrearCiudadano(this, true);
                crearciudadano.setLocationRelativeTo(null);
                crearciudadano.setVisible(true);
            }
        } catch (DAO_Excep ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_crearciudadanoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_salirActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baja;
    private javax.swing.JButton buscar;
    private javax.swing.JButton crearciudadano;
    private javax.swing.JButton crearplaneta;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton modificar;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
