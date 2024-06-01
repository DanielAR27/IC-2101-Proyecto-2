package ajedrez.interfaz;

import ajedrez.control.Control;
import ajedrez.control.DataVerificator;
import ajedrez.logica.Tablero;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;


public class Ajedrez extends javax.swing.JFrame {
    private boolean gameReady;
    protected Control control;

    private Map <String , javax.swing.JButton> botones;
    
    public Ajedrez() {
        control = new Control();
        botones = new TreeMap<>();
        initComponents();
        loadGame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tablas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        surrenderButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        actualPlayerLabel = new javax.swing.JLabel();
        choosedBoxLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playHistoryTextField = new javax.swing.JTextArea();
        tieButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tablas.setBackground(new java.awt.Color(86, 86, 69));
        Tablas.setForeground(new java.awt.Color(0, 0, 0));
        Tablas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");
        Tablas.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        saveButton.setText("Guardar Partida");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        Tablas.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 120, -1));

        surrenderButton.setText("Rendirse");
        surrenderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surrenderButtonActionPerformed(evt);
            }
        });
        Tablas.add(surrenderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, -1));

        loadButton.setText("Cargar Partida");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        Tablas.add(loadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 130, -1));

        startButton.setText("Empezar Partida");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        Tablas.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        actualPlayerLabel.setForeground(new java.awt.Color(255, 255, 255));
        actualPlayerLabel.setText("actualPlayer");
        Tablas.add(actualPlayerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 445, -1, -1));

        choosedBoxLabel.setForeground(new java.awt.Color(255, 255, 255));
        choosedBoxLabel.setText("choosedBox");
        Tablas.add(choosedBoxLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 445, -1, -1));

        playHistoryTextField.setBackground(new java.awt.Color(222, 195, 150));
        playHistoryTextField.setEditable(false);
        DefaultCaret caret = (DefaultCaret)playHistoryTextField.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        playHistoryTextField.setColumns(20);
        playHistoryTextField.setBorder(new LineBorder(Color.BLACK , 10));
        playHistoryTextField.setForeground(new java.awt.Color(0, 0, 0));
        playHistoryTextField.setRows(5);
        playHistoryTextField.setMinimumSize(new java.awt.Dimension(190, 200));
        playHistoryTextField.setPreferredSize(new java.awt.Dimension(190, 200));
        jScrollPane1.setViewportView(playHistoryTextField);

        Tablas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 340, 440));

        tieButton.setText("Tablas");
        tieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tieButtonActionPerformed(evt);
            }
        });
        Tablas.add(tieButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 130, -1));

        getContentPane().add(Tablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void loadGame(){
        javax.swing.JLabel tableroLabel = new javax.swing.JLabel();
        for (int i = 0, y = 35; i <  8; i++, y+= 45){
            for (int j = 0, x = 45; j < 8; j++, x += 45){
                int rowTop = 8 - i;
                String positionBox = DataVerificator.IntToLetter(j) + rowTop;
                JButton button = new javax.swing.JButton();
                botones.put(positionBox, button);
                //button.setOpaque(false);
                //button.setContentAreaFilled(false);
                button.setPreferredSize(new java.awt.Dimension(45, 45));
                Tablas.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, -1));     
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pingButton(positionBox, botones);
                    }
                });
                }
        }
        setGameStatic();
        tableroLabel.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\ajedrez\\interfaz\\tablero.png"));
        tableroLabel.setText("jLabel1");
        Tablas.add(tableroLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 445, 428));
        tableroLabel.getAccessibleContext().setAccessibleName("");        

    }
    
    private void updateButtonsIcons(){
        //boolean status = gameReady;
        //gameReady = false;
        control.printTablero();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                int rowTop = 8 - i;
                String positionBox = DataVerificator.IntToLetter(j) + rowTop;
                JButton button = botones.get(positionBox);
                button.setIcon(new javax.swing.ImageIcon(control.pathConstructor(i, j)));
            }
        }
        //gameReady = status;
    }
    
    
    
    private void pingButton(String positionBox, Map<String, javax.swing.JButton> buttons){
        if (gameReady){
            int jugadorResultado = control.jugadorJuega(positionBox);

            switch (jugadorResultado) {
                case 0 -> {
                    choosedBoxLabel.setVisible(true);
                    choosedBoxLabel.setText("Casilla seleccionada: " + positionBox);
                }
                case -1 -> JOptionPane.showMessageDialog(this, "La posición no es válida, intente de nuevo.",
                            "Notificación", JOptionPane.ERROR_MESSAGE);
                case 1->{
                        // Se obtiene la casilla que fue consultada primeramente.
                        String firstBox = control.getActualPositionBox();
                        boolean towerAtStart;
                        
                        List<Integer> kingCoords;
                        List<Integer> rookCoords;
                        
                        JButton kingButton;
                        JButton rookButton;
                        
                        JButton oldKingButton = botones.get(firstBox);
                        JButton oldRookButton = botones.get(positionBox);

                        // Si la columna es "E" entonces es por que la primera casilla corresponda al rey.
                        boolean firstIsKing = firstBox.substring(0, 1).equals("E");

                        // Se revisa si la que fue seleccionada primero corresponde al rey.
                        if (firstIsKing){
                            kingCoords = (ArrayList) DataVerificator.boxPositionValues(firstBox);
                            rookCoords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
                            towerAtStart = positionBox.substring(0, 1).equals("A");
                            control.castlePlay(firstBox, positionBox, towerAtStart);                                                          
                        // En caso de no serlo, se manejará la jugada con la otra casilla.
                        }else{
                            kingCoords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
                            rookCoords = (ArrayList) DataVerificator.boxPositionValues(firstBox);
                            towerAtStart = firstBox.substring(0, 1).equals("A");
                            control.castlePlay(positionBox, firstBox, towerAtStart);
                        }
                        
                        // Torre del inicio
                        if(towerAtStart){

                            kingButton = botones.get("C"+firstBox.substring(1));
                            rookButton = botones.get("D"+firstBox.substring(1));

                            kingButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(kingCoords.get(0), kingCoords.get(1) - 2)));
                            rookButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(rookCoords.get(0), rookCoords.get(1) + 3)));
                        // Torre del final                                
                        }else{
                            kingButton = botones.get("G"+firstBox.substring(1));
                            rookButton = botones.get("F"+firstBox.substring(1));

                            kingButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(kingCoords.get(0), kingCoords.get(1) + 2)));
                            rookButton.setIcon(new javax.swing.ImageIcon(control.pathConstructor(rookCoords.get(0), rookCoords.get(1) - 2)));                         
                        }                        
                        
                        
                       oldKingButton.setIcon(null);
                       oldRookButton.setIcon(null);
                       
                        playHistoryTextField.setText(control.getHistorialPlays());
                        actualPlayerLabel.setText("Jugador actual: " + control.getEquipoActual());
                        choosedBoxLabel.setVisible(false);                                 
                } case 2 ->{
                    JButton firstAccessed = getFirstAccessedButton();
                    JButton newestAccessed = botones.get(positionBox);
                    // Se obtienen las coordenadas relativas a la casilla que fue seleccionada primeramente.
                    List <Integer> coords = (ArrayList) DataVerificator.boxPositionValues(positionBox);
                    
                    PromotePawn pp = new PromotePawn(this, true);
                    pp.setLocationRelativeTo(this);
                    pp.setVisible(true);
                    String type = pp.getResult();
                    if(control.promotePlay(positionBox, type)){
                         newestAccessed.setIcon(new javax.swing.ImageIcon(control.pathConstructor(coords.get(0), coords.get(1))));
                        firstAccessed.setIcon(null);                       
                        endGame(control.getEquipoActual());
                    }else{
                        newestAccessed.setIcon(new javax.swing.ImageIcon(control.pathConstructor(coords.get(0), coords.get(1))));
                        firstAccessed.setIcon(null);
                        playHistoryTextField.setText(control.getHistorialPlays());
                        actualPlayerLabel.setText("Jugador actual: " + control.getEquipoActual());
                        choosedBoxLabel.setVisible(false);                        
                    }
                }
                default -> {
                    // Se obtiene la casilla que fue consultada primeramente.
                    String firstBox = control.getActualPositionBox();
                    // Se obtiene el botón que fue consultado primeramente.
                    JButton firstAccessed = getFirstAccessedButton();
                    // Obtiene el botón acessado recientemente.
                    JButton newestAccessed = botones.get(positionBox);
                    // Se obtienen las coordenadas relativas a la casilla que fue seleccionada primeramente.
                    List <Integer> coords = (ArrayList) DataVerificator.boxPositionValues(firstBox);
                    // Al nuevo botón se le asigna la imagen del viejo.
                    newestAccessed.setIcon(new javax.swing.ImageIcon(control.pathConstructor(coords.get(0), coords.get(1))));
                    // Al viejo se le asigna que no tenga imagen.
                    firstAccessed.setIcon(null); 
                    if(control.changePlayer(positionBox)){
                        endGame(control.getEquipoActual());
                    }else{
                        playHistoryTextField.setText(control.getHistorialPlays());
                        actualPlayerLabel.setText("Jugador actual: " + control.getEquipoActual());
                        choosedBoxLabel.setVisible(false);
                    }
                }
            }
        }
    }
 
    private JButton getFirstAccessedButton(){
        String firstBox = control.getActualPositionBox();
        JButton firstAccessed = new javax.swing.JButton();
        // Obtiene el primer botón acessado.
        for (String actualBox: botones.keySet()){
            if (actualBox.equals(firstBox)){
                firstAccessed = botones.get(actualBox);
                break;
            }
        }
        return firstAccessed;
    }    
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
            SaveMatch sm = new SaveMatch(this, true, control);
            sm.setLocationRelativeTo(this);
            sm.setVisible(true);        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
            // Se abre la ventana.
            LoadMatch lm = new LoadMatch(this, true);
            // Se guarda la instancia en el momento actual.
            Control controlActual = control;
            // Se abre el JDialog para obtener un cambio en el control.
            lm.setLocationRelativeTo(this);
            lm.setVisible(true);
            // Si el archivo cambió entonces se van a actualizar los atributos, de no ser así se mantienen igual.
            if (control != controlActual){
                setGameViewable();
                
                String actualBox = control.getActualPositionBox();
                if (!actualBox.equals("")){
                    choosedBoxLabel.setVisible(true);
                    choosedBoxLabel.setText("Casilla seleccionada: " + actualBox);
                }else{
                    System.out.println("No info.");
                }

                playHistoryTextField.setText(control.getHistorialPlays());
                updateButtonsIcons();
            }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        setGameViewable();
    }//GEN-LAST:event_startButtonActionPerformed

    private void surrenderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surrenderButtonActionPerformed
            String equipoActual = control.getEquipoActual();
            int result = JOptionPane.showConfirmDialog(this, "¿Está seguro el jugador del equipo " + control.getEquipoActual()
                    + " de rendirse?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (result == JOptionPane.YES_OPTION){
                String equipoContrario = equipoActual.equals("blanco")? "negro":"blanco";
                endGame(equipoContrario);
            }
    }//GEN-LAST:event_surrenderButtonActionPerformed

    private void tieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tieButtonActionPerformed
            int result = JOptionPane.showConfirmDialog(this, "¿Está seguro de empatar la partida? ", 
                    "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION)
                playAgain();
    }//GEN-LAST:event_tieButtonActionPerformed

    private void endGame(String winner){
            JOptionPane.showMessageDialog(this, "El jugador del equipo " + winner + " ha ganado la partida.", 
                    "Notificación", JOptionPane.INFORMATION_MESSAGE);
            playAgain();
    }
    
    private void playAgain(){
             int result = JOptionPane.showConfirmDialog(this, "¿Desea volver a jugar otra partida?",
                     "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             if (result == JOptionPane.YES_OPTION){
                 control.reiniciarJuego();
                 setGameStatic();
             }else{
                 dispose();
             }        
    }
    
    
    
    private void setGameStatic(){
        gameReady = false;
        updateButtonsIcons();
        loadButton.setVisible(true);
        surrenderButton.setVisible(false);
        tieButton.setVisible(false);
        startButton.setVisible(true);
        actualPlayerLabel.setVisible(false);
        choosedBoxLabel.setVisible(false);
        playHistoryTextField.setText("");
        saveButton.setVisible(false);
    }
    
    private void setGameViewable(){
        gameReady = true;
        startButton.setVisible(false);
        loadButton.setVisible(false);
        surrenderButton.setVisible(true);
        tieButton.setVisible(true);
        saveButton.setVisible(true);
        actualPlayerLabel.setVisible(true);
        actualPlayerLabel.setText("Jugador actual: " + control.getEquipoActual());      
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
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajedrez().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Tablas;
    private javax.swing.JLabel actualPlayerLabel;
    private javax.swing.JLabel choosedBoxLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JTextArea playHistoryTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton surrenderButton;
    private javax.swing.JButton tieButton;
    // End of variables declaration//GEN-END:variables
}
