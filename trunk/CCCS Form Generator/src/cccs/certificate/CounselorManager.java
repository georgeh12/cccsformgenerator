/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CounselorManager.java
 *
 * Created on Dec 23, 2009, 7:41:30 PM
 */

package cccs.certificate;

import cccs.FormGeneratorApp;
import cccs.utility.CCCSWindowEventListener;
import cccs.utility.FileManager;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author ghardigg
 */
public class CounselorManager extends CCCSWindowEventListener {
    private static Counselors counselors = null;

    /** Creates new form CounselorManager */
    public CounselorManager() {
        super();
        initComponents();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        counselors = (Counselors)FileManager.loadFile(FormGeneratorApp.login, getFile("config"), Counselors.class);
        if(counselors == null){
            counselors = new Counselors();
            FileManager.saveFile(FormGeneratorApp.login, getFile("config"), counselors);
        }
    }

    private static File getFile(String filename){
        return new File("Certificate\\" + filename);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setName("jComboBox1"); // NOI18N

        jButton1.setText("New Certificate");
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setText("Cancel Certificate");
        jButton2.setName("jButton2"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText("File");
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem2.setText("Add Counselor");
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Remove Counselor");
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Main Menu");
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("Agency ID");
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Password");
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenu3.setName("jMenu3"); // NOI18N
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, 117, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String username = "";
        String password = "";

        while("".equals(username = JOptionPane.showInputDialog(this, "Enter the username.", "Username", JOptionPane.PLAIN_MESSAGE))){
            if(username == null){
                return;
            }

            while("".equals(password = JOptionPane.showInputDialog(this, "Enter the username.", "Username", JOptionPane.PLAIN_MESSAGE))){
                if(password == null){
                    return;
                }
                
                if(counselors.addCounselor(username, password)){
                    JOptionPane.showMessageDialog(this, "Counselor added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Counselor already exists.", "Duplicate Counselor", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        counselors.removeCounselor(jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        String agencyID = "";

        while("".equals(agencyID = JOptionPane.showInputDialog(this, "Enter the agency ID.", "Agency ID", JOptionPane.PLAIN_MESSAGE))){
            if(agencyID == null){
                return;
            }

            counselors.changeAgencyID(agencyID);
            JOptionPane.showMessageDialog(this, "Updated agency ID to: \"" + agencyID + "\"", "Success", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        String password = "";

        while("".equals(password = JOptionPane.showInputDialog(this, "Enter the password.", "Change Password", JOptionPane.PLAIN_MESSAGE))){
            if(password == null){
                return;
            }

            counselors.changePassword(jComboBox1.getSelectedIndex(), password);
        }
    }//GEN-LAST:event_jMenu2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CounselorManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables

}
