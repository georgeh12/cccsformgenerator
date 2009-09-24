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
import cccs.utility.PasswordDialog;
import cccs.message.MessageFormApp;
import cccs.fax.FaxFormApp;
import cccs.log.LogFormApp;
import javax.swing.*;
import java.io.*;
import cccs.utility.FileManager.LoginManager;

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

        if(login == null){
            logout();
        }
        else{
            login(login);
        }
    }

    private void login(LoginManager.Login login){
        home_dir = login.getDir();
        this.login = login;
        display_name = login.getDisplay();
        setTitle("Welcome, " + display_name + "!");
        jLabel2.setText(display_name);
        jLabel1.setText("Logged in as:");
        setLoggedIn(true);
    }

    private void logout(){
        home_dir = new File("_default");
        login = null;
        display_name = "";
        setTitle("Welcome!");
        jLabel2.setText("");
        jLabel1.setText("Please login first ^_^");
        setLoggedIn(false);
    }

    private void setLoggedIn(boolean logged_in){
        jMenu2.setVisible(logged_in);
        jButton1.setVisible(logged_in);
        jButton2.setVisible(logged_in);
        jButton3.setVisible(logged_in);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome!");
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Logged in as:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setName("jLabel2"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu1.setText("File");
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem1.setText("Login");
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("New User");
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setName("jMenu2"); // NOI18N

        jMenuItem3.setText("Display Name");
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Delete User");
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addContainerGap())
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
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private String getUsername(String message, String title){
        String username = "";
        
        while(username.isEmpty()){
            username = (String)JOptionPane.showInputDialog(this, message, title, JOptionPane.QUESTION_MESSAGE, null, null, System.getProperty("user.name"));

            if(username == null){
                break;
            }
        }

        return username;
    }

    private String getPassword(String title){
        while(true){
            PasswordDialog dialog1 = new PasswordDialog();
            int input1 = JOptionPane.showConfirmDialog(this, dialog1, "Enter a password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            String original = dialog1.getPassword();

            if(input1 == JOptionPane.CANCEL_OPTION || input1 == JOptionPane.CLOSED_OPTION){
                return null;
            }
            else if(original.length() < 8){
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters");
            }
            else{
                PasswordDialog dialog2 = new PasswordDialog();
                dialog2.setLabel("Re-enter your password");
                int input2 = JOptionPane.showConfirmDialog(this, dialog2, "Create Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                String verification = dialog2.getPassword();

                if(input2 == JOptionPane.CANCEL_OPTION || input2 == JOptionPane.CLOSED_OPTION){
                    return null;
                }
                else if(!original.equals(verification)){
                    JOptionPane.showMessageDialog(this, "Passwords must match, try again");
                }
                else{
                    return original;
                }
            }
        }
    }

    private LoginManager.Login authenticate(){
        String username = "";
        String password = "";

        if((username = getUsername("Enter your username", "Authenticating")) == null){
            return null;
        }
        else{
            while(password.isEmpty()){
                PasswordDialog dialog = new PasswordDialog();
                int input = JOptionPane.showConfirmDialog(this, dialog, "Authenticating", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(input == JOptionPane.OK_OPTION){
                    password = dialog.getPassword();
                }
                else{
                    return null;
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        LogFormApp daily_log_form = new LogFormApp();
        daily_log_form.setLocationRelativeTo(this);
        daily_log_form.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(jMenuItem1.getText().equals("Login")){
            LoginManager.Login temp_login = authenticate();

            if(temp_login != null){
                login(temp_login);
                jMenuItem1.setText("Logout");
            }
        }
        else{
            if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null)){
                logout();
                jMenuItem1.setText("Login");
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String username = "";
        String password = "";
        String title =  "Create New User";

        if((username = getUsername("Enter a username", title)) != null){
            if((password = getPassword(title)) != null){
                LoginManager.Login temp_login = new LoginManager.Login(username, password);
                login(temp_login);
                LoginManager.add(temp_login);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String new_display_name = "";

        new_display_name = getUsername("Enter your preferred display name", "Edit Display Name");

        if(new_display_name != null){
            login.setDisplay(new_display_name);
            LoginManager.set(login);
            login(login);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "This will permanently delete all files associated with this user. Are you sure?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null)){
            LoginManager.remove(login);
            logout();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    // End of variables declaration//GEN-END:variables

}
