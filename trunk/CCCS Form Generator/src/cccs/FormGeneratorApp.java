/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Start.java
 *
 * Created on Aug 18, 2009, 1:44:47 PM
 * @author George Hardigg
 */

package cccs;
import javax.swing.*;
import java.io.*;
import cccs.FileManager.LoginManager;

/**
 * Defined filetypes: .fga, .ffa, .mfa, .lfa
 *
 * @author ghardigg
 */
public class FormGeneratorApp extends javax.swing.JFrame {
    static public File home_dir = new File("_default");
    static public LoginManager.Login login = null;
    static public String display_name = "";

    /** Creates new form Start */
    public FormGeneratorApp() {
        initComponents();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLoginOptions();
    }

    private void updateLogin(LoginManager.Login login){
        home_dir = login.getDir();
        this.login = login;
        display_name = login.getDisplay();
    }

    private void setLoginOptions(){
        if(login == null){
            jButton1.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            this.setTitle("CCCS Form Generator");
        }
        else{
            jButton1.setVisible(true);
            jButton2.setVisible(true);
            jButton3.setVisible(true);
            this.setTitle("Welcome, " + display_name + "!");
        }

        if(jCheckBox1.isSelected()){
            jButton5.setVisible(true);

            //Edit and delete buttons
            if(login == null){
                jButton6.setVisible(false);
                jButton7.setVisible(false);
            }
            else{
                jButton7.setVisible(true);
                jButton6.setVisible(true);
            }

            //Display name label
            if(login != null){
                jLabel1.setVisible(true);
                jLabel2.setVisible(true);
                jLabel2.setText(display_name);
            }
        }
        else{
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jButton7.setVisible(false);
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Messages");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Faxes");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Daily Log");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Login");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Create New Login");
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Delete Login");
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Show Options");
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton7.setText("Edit Display Name");
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Logged in as:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        MessageFormApp message_form = new MessageFormApp();
        message_form.setLocationRelativeTo(this);
        message_form.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        FaxFormApp fax_form = new FaxFormApp();
        fax_form.setLocationRelativeTo(this);
        fax_form.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    public String getUsername(String message, String title){
        String username = "";
        
        while(username.isEmpty()){
            username = JOptionPane.showInputDialog(this, message, title, JOptionPane.OK_CANCEL_OPTION);

            if(username == null){
                break;
            }
        }

        return username;
    }

    private LoginManager.Login authenticate(){
        String username = "";
        String password = "";

        if((username = getUsername("Enter your username", "Login")) == null){
            return null;
        }
        else{
            while(password.isEmpty()){
                //TODO change to password field
                String input = JOptionPane.showInputDialog(this, "Enter your password", "Login", JOptionPane.OK_CANCEL_OPTION);

                if(input == null){
                    return null;
                }
                else{
                    password = input;
                }
            }

            LoginManager.Login temp_login = LoginManager.authenticate(new LoginManager.Login(username, password));

            if(temp_login == null){
                JOptionPane.showMessageDialog(this, "Username and/or password do not match");
            }
            else{
                return temp_login;
            }
        }
        
        return null;
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LoginManager.Login temp_login = authenticate();
        
        if(temp_login != null){
            updateLogin(temp_login);
            setLoginOptions();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        setLoginOptions();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String new_display_name = "";

        new_display_name = getUsername("Enter your preferred display name", "Edit Display Name");

        if(new_display_name != null){
            login.setDisplay(new_display_name);
            LoginManager.set(login);
            updateLogin(login);
            setLoginOptions();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String username = "";
        String password = "";

        if((username = getUsername("Enter a username", "Create Login")) != null){

            while(password.isEmpty()){
                String input = JOptionPane.showInputDialog(this, "Enter a password", "Create Login", JOptionPane.OK_CANCEL_OPTION);

                if(input == null){
                    return;
                }
                else if(input.length() < 8){
                    JOptionPane.showMessageDialog(this, "Password must be at least 8 characters");
                }
                else{
                    password = input;
                    String verification = JOptionPane.showInputDialog(this, "Re-enter your password", "Create Login", JOptionPane.OK_CANCEL_OPTION);

                    if(verification == null){
                        return;
                    }
                    else if(password.compareTo(verification) != 0){
                        JOptionPane.showMessageDialog(this, "Passwords must match, try again");
                        password = "";
                    }
                }
            }


            LoginManager.Login temp_login = new LoginManager.Login(username, password);
            updateLogin(temp_login);
            LoginManager.add(temp_login);
            setLoginOptions();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "This will permanently delete all files associated with this user. Are you sure?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null)){
            LoginManager.remove(login);
            home_dir = new File("_default");
            login = null;
            display_name = "";
            jCheckBox1.setSelected(false);
            setLoginOptions();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        LogFormApp daily_log_form = new LogFormApp();
        daily_log_form.setLocationRelativeTo(this);
        daily_log_form.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormGeneratorApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}