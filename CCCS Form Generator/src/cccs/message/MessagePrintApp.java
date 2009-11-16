/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MessagePrintApp.java
 *
 * Created on Sep 7, 2009, 10:26:27 PM
 * @author George Hardigg
 */

package cccs.message;
import cccs.utility.*;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author George
 */
public class MessagePrintApp extends javax.swing.JFrame {
    /** Creates new form MessagePrintApp */
    public MessagePrintApp() {
        initComponents();
    }

    private String formatMailto(String s){
        return s.replace("%", "%25").replace(" ", "%20").replace("\r\n", "%0D%0A").replace(":", "%3A").replace("/", "%2F").replace("-", "%2D").replace(",", "%2C");
    }

    public void email(ArrayList<Message> messages) {
        try{
            for(int i = messages.size() - 1; i >= 0; i--){
                Message message = messages.get(i);
                URI email = new URI("mailto:thoffman@cccs-inc.org"
                        + "?subject=" + formatMailto(printHeader(message))
                        + "&cc=edickerson@cccs-inc.org" + ",ninah@cccs-inc.org" + ",dbooker@cccs-inc.org"
                        + "&body=" + formatMailto(printMessage(message))
                        );
                Desktop.getDesktop().mail(email);

                if(i == messages.size() - 1){
                    JOptionPane.showMessageDialog(this, "Click OK when the first e-mail has finished loading.");
                }
                else{
                    Thread.sleep(250);
                }
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        catch(URISyntaxException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        } finally {
            dispose();
        }
    }

    public void print(ArrayList<Message> messages){
        PrintUtilities.disableDoubleBuffering(this);

        for(int i = 0; i < messages.size(); i += 3){
            if(messages.size() > i){
                jTextField1.setText(printHeader(messages.get(i)));
                jTextArea1.setText(printMessage(messages.get(i)));
            }
            else{
                jTextField1.setText("");
                jTextArea1.setText("");
            }
            if(messages.size() > i + 1){
                jTextField2.setText(printHeader(messages.get(i + 1)));
                jTextArea2.setText(printMessage(messages.get(i + 1)));
            }
            else{
                jTextField2.setText("");
                jTextArea2.setText("");
            }
            if(messages.size() > i + 2){
                jTextField3.setText(printHeader(messages.get(i + 2)));
                jTextArea3.setText(printMessage(messages.get(i + 2)));
            }
            else{
                jTextField3.setText("");
                jTextArea3.setText("");
            }

            jPanel1.setBackground(Color.WHITE);
            jTextField1.setCaretColor(Color.WHITE);
            jTextField1.setBackground(Color.WHITE);
            jTextArea1.setCaretColor(Color.WHITE);
            jTextArea1.setBackground(Color.WHITE);
            jTextField2.setCaretColor(Color.WHITE);
            jTextField2.setBackground(Color.WHITE);
            jTextArea2.setCaretColor(Color.WHITE);
            jTextArea2.setBackground(Color.WHITE);
            jTextField3.setCaretColor(Color.WHITE);
            jTextField3.setBackground(Color.WHITE);
            jTextArea3.setCaretColor(Color.WHITE);
            jTextArea3.setBackground(Color.WHITE);
            validate();

            PrintUtilities.printComponent(jPanel1);

            if(i <= messages.size() - 3)JOptionPane.showMessageDialog(this, "Click OK to continue.");
        }

        PrintUtilities.enableDoubleBuffering(this);
        dispose();
    }

    private String printHeader(Message message){
        return message.getDept().toString() + " Department Callback Form";
    }

    private String printMessage(Message message){
        StringBuffer print = new StringBuffer("");

        print.append("Date: " + message.formatDay() + ", " +
                message.formatDate() + "          Time: " + message.formatTime());

        print.append("          Call from: " + message.getCallFrom());

        if(message.getCreditorInfo() != null){
            Message.CreditorInfo creditor_info = message.getCreditorInfo();
            print.append("\r\nCreditor Name: " + creditor_info.getCreditorName() +
                    "          Representative's Name: " + creditor_info.getRepName() +
                    "\r\nAccount Number: " + creditor_info.getAcctNumber());
        }

        print.append("\r\nClient Number: " + (message.getID() != 0 ? message.getID() : "") + "          Client Name: " +
                message.getName());

        ArrayList<PhoneNumber> contacts = message.getContacts();
        print.append("\r\n");
        for(int i = 0; i < contacts.size(); i ++){
            print.append(contacts.get(i).getType() + ": " + contacts.get(i).toString());
            if(i < contacts.size() - 1) print.append("          ");
        }

        print.append("\r\n\r\nMessage:\r\n" + message.getMessage().toString());

        return print.toString();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setBorder(null);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(6);
        jTextArea1.setTabSize(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setBorder(null);
        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(6);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setBorder(null);
        jTextArea2.setName("jTextArea2"); // NOI18N
        jScrollPane2.setViewportView(jTextArea2);

        jScrollPane3.setBorder(null);
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Tahoma", 0, 14));
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(6);
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setBorder(null);
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane3.setViewportView(jTextArea3);

        jTextField1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(null);
        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(null);
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(null);
        jTextField3.setName("jTextField3"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessagePrintApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
