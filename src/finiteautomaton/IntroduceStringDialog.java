/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finiteautomaton;

import java.awt.event.ActionListener;

/**
 *
 * @author DrAP
 */
public class IntroduceStringDialog extends javax.swing.JDialog {

    public IntroduceStringDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        l_string = new javax.swing.JLabel();
        tf_string = new javax.swing.JTextField();
        b_introduce = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        l_string.setText("String:");

        tf_string.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_stringActionPerformed(evt);
            }
        });

        b_introduce.setText("Introduce");
        b_introduce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_introduceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(l_string)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_string))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 301, Short.MAX_VALUE)
                        .addComponent(b_introduce)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_string)
                    .addComponent(tf_string, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_introduce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_stringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_stringActionPerformed
        this.b_introduce.doClick();
    }//GEN-LAST:event_tf_stringActionPerformed

    private void b_introduceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_introduceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_introduceActionPerformed
    public void addConfirmListener(ActionListener listener) {
        this.b_introduce.addActionListener(listener);
    }

    public String getTextFieldText() {
        return this.tf_string.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_introduce;
    private javax.swing.JLabel l_string;
    private javax.swing.JTextField tf_string;
    // End of variables declaration//GEN-END:variables
}
