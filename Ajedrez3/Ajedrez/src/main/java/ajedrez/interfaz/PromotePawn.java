package ajedrez.interfaz;

import javax.swing.WindowConstants;

public class PromotePawn extends javax.swing.JDialog {
    private String promoteResult;
    /**
     * Creates new form PromotePawn
     */
    
    // Constructor
    public PromotePawn(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Evita cerrar la ventana.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        queenButton = new javax.swing.JButton();
        rookButton = new javax.swing.JButton();
        horseButton = new javax.swing.JButton();
        bishopButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Promoción");
        setResizable(false);

        queenButton.setText("Reina");
        queenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queenButtonActionPerformed(evt);
            }
        });

        rookButton.setText("Torre");
        rookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rookButtonActionPerformed(evt);
            }
        });

        horseButton.setText("Caballo");
        horseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horseButtonActionPerformed(evt);
            }
        });

        bishopButton.setText("Alfil");
        bishopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bishopButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Elija una pieza para ser promovido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bishopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(queenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(queenButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rookButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bishopButton)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Queen Button Action: Guarda el resultado como reina y cierra la ventana.
    private void queenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queenButtonActionPerformed
        promoteResult = "Q";
        dispose();
    }//GEN-LAST:event_queenButtonActionPerformed
    
    // Rook Button Action: Guarda el resultado como torre y cierra la ventana.
    private void rookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rookButtonActionPerformed
        promoteResult = "T";
        dispose();
    }//GEN-LAST:event_rookButtonActionPerformed
    
    // Horse Button Action: Guarda el resultado como caballo y cierra la ventana.
    private void horseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horseButtonActionPerformed
        promoteResult = "C";
        dispose();
    }//GEN-LAST:event_horseButtonActionPerformed
    
    // Alfil Button Action: Guarda el resultado como torre y cierra la ventana.
    private void bishopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bishopButtonActionPerformed
        promoteResult = "A";
        dispose();
    }//GEN-LAST:event_bishopButtonActionPerformed
    
    // Get Result: Obtiene el resultado de la promoción del peón.
   public String getResult(){
       return promoteResult;
   }
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
            java.util.logging.Logger.getLogger(PromotePawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PromotePawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PromotePawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PromotePawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PromotePawn dialog = new PromotePawn(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bishopButton;
    private javax.swing.JButton horseButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton queenButton;
    private javax.swing.JButton rookButton;
    // End of variables declaration//GEN-END:variables
}
