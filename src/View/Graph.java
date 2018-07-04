package View;

import PlotController.Moderator;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Graph extends javax.swing.JFrame {

    public Graph() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainGraph = new javax.swing.JPanel();
        pnlGraph = new javax.swing.JPanel();
        txtEq = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GraphPlotter");
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        mainGraph.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Graph", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12))); // NOI18N

        pnlGraph.setBackground(new java.awt.Color(255, 255, 255));
        pnlGraph.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        pnlGraph.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlGraphMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnlGraphMouseMoved(evt);
            }
        });
        pnlGraph.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlGraphMouseWheelMoved(evt);
            }
        });
        pnlGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlGraphMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlGraphMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlGraphMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlGraphLayout = new javax.swing.GroupLayout(pnlGraph);
        pnlGraph.setLayout(pnlGraphLayout);
        pnlGraphLayout.setHorizontalGroup(
            pnlGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        pnlGraphLayout.setVerticalGroup(
            pnlGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );

        txtEq.setText("y-abs(x)");
        txtEq.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equation : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12))); // NOI18N
        txtEq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEqKeyPressed(evt);
            }
        });

        jButton1.setText("Plot");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setText("= 0");

        javax.swing.GroupLayout mainGraphLayout = new javax.swing.GroupLayout(mainGraph);
        mainGraph.setLayout(mainGraphLayout);
        mainGraphLayout.setHorizontalGroup(
            mainGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainGraphLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pnlGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainGraphLayout.createSequentialGroup()
                        .addComponent(txtEq, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        mainGraphLayout.setVerticalGroup(
            mainGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainGraphLayout.createSequentialGroup()
                .addGroup(mainGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainGraphLayout.createSequentialGroup()
                        .addGroup(mainGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEq, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 37, Short.MAX_VALUE))
        );

        pnlGraph.getAccessibleContext().setAccessibleName("pnlGraph");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainGraph.getAccessibleContext().setAccessibleName("mainGraph");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Moderator.initPlotter(txtEq.getText());
    }//GEN-LAST:event_formWindowOpened

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Moderator.initPlotter(txtEq.getText());
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtEqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEqKeyPressed

    }//GEN-LAST:event_txtEqKeyPressed

    private void pnlGraphMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGraphMouseReleased
        Moderator.left_mouse_released(evt);
    }//GEN-LAST:event_pnlGraphMouseReleased

    private void pnlGraphMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGraphMousePressed
        Moderator.left_mouse_pressed(evt);
    }//GEN-LAST:event_pnlGraphMousePressed

    private void pnlGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGraphMouseClicked
        Moderator.mouse_click(evt);
    }//GEN-LAST:event_pnlGraphMouseClicked

    private void pnlGraphMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlGraphMouseWheelMoved
        Moderator.mouse_wheele(evt);
    }//GEN-LAST:event_pnlGraphMouseWheelMoved

    private void pnlGraphMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGraphMouseMoved

    }//GEN-LAST:event_pnlGraphMouseMoved

    private void pnlGraphMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGraphMouseDragged
        Moderator.left_mouse_dragged(evt);
    }//GEN-LAST:event_pnlGraphMouseDragged

    public JTextField getTxtPlot(){
        return this.txtEq;
    }
    public JLabel getlblPlot(){
        return this.lblStatus;
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Graph mainGraph=new Graph();
                Moderator.addPanel(mainGraph.getGraphPanel());
                mainGraph.setVisible(true);
            }
        });
    }
    public javax.swing.JPanel getGraphPanel(){
        pnlGraph.getGraphics().drawLine(0,0,100,100);
        return pnlGraph;
    }
    public class pnlGraph extends JPanel{
        @Override
        public void paintComponents(Graphics g){
            super.paintComponent(g);
            int w=this.getWidth();
            int h=this.getHeight();
            g.drawLine(0, 0, w, h);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel mainGraph;
    private javax.swing.JPanel pnlGraph;
    private javax.swing.JTextField txtEq;
    // End of variables declaration//GEN-END:variables
}
