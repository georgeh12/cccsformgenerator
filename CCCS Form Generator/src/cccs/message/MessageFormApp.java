/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CCCSMessageFormGenerator.java
 *
 * Created on Sep 3, 2009, 9:40:41 AM
 * @author George Hardigg
 */

package cccs.message;
import cccs.utility.FileManager;
import cccs.utility.PhoneNumber;
import cccs.utility.CalendarUtilities;
import cccs.*;
import javax.swing.*;
import java.util.*;
import cccs.message.Message.*;
import cccs.utility.CCCSWindowEventListener;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

/**
 *
 * @author ghardigg
 */
public class MessageFormApp extends CCCSWindowEventListener {
    public class MessageListener implements ActionListener{
        private Message message = null;

        public MessageListener(Message message){
            this.message = message;
        }

        public Message get(){
            return message;
        }

        public void set(Message message){
            this.message = message;
        }

        public void actionPerformed(ActionEvent e) {
            load(message);
        }
    }

    private static ArrayList<String> recipients = null;

    /** Creates new form CCCSMessageFormGenerator */
    public MessageFormApp() {
        super();
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        showCreditorFields(false);

        recipients = (ArrayList<String>)FileManager.loadFile(FormGeneratorApp.login, getFile("config"), ArrayList.class);
        if(recipients == null){
            setDefaultRecipients();
        }
    }

    private static void setDefaultRecipients(){
        setRecipients("thoffman@cccs-inc.org",
                "edickerson@cccs-inc.org,ninah@cccs-inc.org,dbooker@cccs-inc.org",
                "");
    }
    private static void setRecipients(String to, String cc, String bcc){
        recipients = new ArrayList<String>();
        recipients.add(to);
        recipients.add(cc);
        recipients.add(bcc);
        
        FileManager.saveFile(FormGeneratorApp.login, getFile("config"), recipients);
    }
    static String getTo(){
        return recipients.get(0);
    }
    static String getCC(){
        return recipients.get(1);
    }
    static String getBCC(){
        return recipients.get(2);
    }

    private void email(String filename){
        try{
            saveFile(filename);
            File file = FileManager.getFile(new File(FormGeneratorApp.home_dir + "\\" + "Message"), filename);
            if(file.exists()){

                MessagePrintApp print_screen = new MessagePrintApp();
                print_screen.setLocationRelativeTo(this);
                print_screen.setVisible(true);

                ArrayList<Message> messages = loadFile(filename);

                print_screen.email(messages);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void print(String filename){
        try{
            saveFile(filename);
            File file = FileManager.getFile(new File(FormGeneratorApp.home_dir + "\\" + "Message"), filename);
            if(file.exists()){

                MessagePrintApp print_screen = new MessagePrintApp();
                print_screen.setLocationRelativeTo(this);
                print_screen.setVisible(true);

                ArrayList<Message> messages = loadFile(filename);

                print_screen.print(messages);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showCreditorFields(boolean show){
        jLabel3.setVisible(show);
        jTextField4.setVisible(show);
        jLabel4.setVisible(show);
        jTextField5.setVisible(show);
        jLabel5.setVisible(show);
        jTextField6.setVisible(show);
    }

    private int saveMessageFirst(){
        int response = JOptionPane.showOptionDialog(this, "Save this message?", "Not so fast...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(response == JOptionPane.YES_OPTION){
            save();
        }

        return response;
    }

    private int getCurrentIndex(){
        for(int i = 0; i < jMenu1.getItemCount(); i ++){
            if(jMenu1.getItem(i).isSelected()){
                return i;
            }
        }

        return -1;
    }

    private void loadNewForm(){
        load(new Message());

        buttonGroup4.clearSelection();
    }

    private Message save(){
        try{
            if(!PhoneNumber.isEmpty(jTextField3.getText())) jButton1ActionPerformed(null);
            if(jToggleButton1.isSelected()) jToggleButton1.setSelected(false);

            Calendar combined = Calendar.getInstance();

            //set date
            Calendar temp = Calendar.getInstance();
            temp.setTime((Date)((SpinnerDateModel)spinner_month.getModel()).getValue());
            combined.set(Calendar.MONTH, temp.get(Calendar.MONTH));
            temp.setTime((Date)((SpinnerDateModel)spinner_day.getModel()).getValue());
            combined.set(Calendar.DAY_OF_MONTH, temp.get(Calendar.DAY_OF_MONTH));
            temp.setTime((Date)((SpinnerDateModel)spinner_year.getModel()).getValue());
            combined.set(Calendar.YEAR, temp.get(Calendar.YEAR));

            //set time
            temp.setTime((Date)((SpinnerDateModel)spinner_hour.getModel()).getValue());
            combined.set(Calendar.HOUR, temp.get(Calendar.HOUR));
            temp.setTime((Date)((SpinnerDateModel)spinner_minute.getModel()).getValue());
            combined.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
            temp.setTime((Date)((SpinnerDateModel)spinner_ampm.getModel()).getValue());
            combined.set(Calendar.AM_PM, temp.get(Calendar.AM_PM));

            CallFrom call_from = CallFrom.Other;
            CreditorInfo creditor_info = null;
            if(jRadioButton1.isSelected()){
                call_from = CallFrom.Client;
            }
            else if(jRadioButton2.isSelected()){
                call_from = CallFrom.Creditor;
                creditor_info = new CreditorInfo(jTextField4.getText(),
                        jTextField5.getText(), jTextField6.getText());
            }
            else if(jRadioButton3.isSelected()){
                call_from = CallFrom.Coworker;
            }

            Dept dept = Dept.Counseling;
            if(jRadioButton6.isSelected()){
                dept = Dept.Operations;
            }
            else if(jRadioButton7.isSelected()){
                dept = Dept.Housing;
            }

            int id = 0;
            if(!jTextField1.getText().isEmpty()){
                id = Integer.parseInt(jTextField1.getText());
            }

            String name = jTextField2.getText();

            ArrayList<PhoneNumber> contacts = new ArrayList<PhoneNumber>();
            for(int i = 0; i < jComboBox1.getItemCount(); i ++){
                contacts.add((PhoneNumber)jComboBox1.getItemAt(i));
            }

            StringBuffer message = new StringBuffer(jTextArea1.getText());

            Message saved_message = new Message(combined, call_from, dept, name, id,
                    contacts, message, creditor_info);

            if(getCurrentIndex() == -1){
                JRadioButtonMenuItem new_menu_item = new JRadioButtonMenuItem(saved_message.toString());
                jMenu1.add(new_menu_item);
                buttonGroup4.add(new_menu_item);
                new_menu_item.addActionListener(new MessageListener(saved_message));
                new_menu_item.setSelected(true);
            }
            else{
                JMenuItem current = jMenu1.getItem(getCurrentIndex());
                current.setText(saved_message.toString());
                ((MessageListener)(current.getActionListeners()[0])).set(saved_message);
                current.setSelected(true);
            }
            
            return saved_message;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        

        return null;
    }

    private void load(Message message){
        try{
            if(message.getDate() != null){
                spinner_month.getModel().setValue(message.getDate().getTime());
                spinner_hour.getModel().setValue(message.getDate().getTime());
                spinner_day.getModel().setValue(message.getDate().getTime());
                spinner_minute.getModel().setValue(message.getDate().getTime());
                spinner_ampm.getModel().setValue(message.getDate().getTime());
                spinner_year.getModel().setValue(message.getDate().getTime());
            }

            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            switch(message.getCallFrom()){
                case Client:
                    jRadioButton1.setSelected(true);
                    break;
                case Creditor:
                    jRadioButton2.setSelected(true);
                    jTextField4.setText(message.getCreditorInfo().getCreditorName());
                    jTextField5.setText(message.getCreditorInfo().getRepName());
                    jTextField6.setText(message.getCreditorInfo().getAcctNumber());
                    break;
                case Coworker:
                    jRadioButton3.setSelected(true);
                    break;
                case Other:
                    jRadioButton4.setSelected(true);
                    break;
            }

            switch(message.getDept()){
                case Counseling:
                    jRadioButton5.setSelected(true);
                    break;
                case Operations:
                    jRadioButton6.setSelected(true);
                    break;
                case Housing:
                    jRadioButton7.setSelected(true);
                    break;
            }

            if(message.getID() > 0){
                jTextField1.setText(Integer.toString(message.getID()));
            }
            else{
                jTextField1.setText("");
            }

            jTextField2.setText(message.getName());

            jTextField3.setText("");
            jTextField7.setText("");
            jRadioButton8.setSelected(true);
            setPhoneButtons(true);

            jComboBox1.removeAllItems();
            ArrayList<PhoneNumber> contacts = message.getContacts();

            for(int i = 0; i < contacts.size(); i++){
                jComboBox1.addItem(contacts.get(i));
            }

            jTextArea1.setText(message.getMessage().toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static File getFile(String filename){
        return new File("Message\\" + filename);
    }

    private void saveFile(String filename){
        PriorityQueue<Message> sorted_messages = new PriorityQueue<Message>();
        ArrayList<Message> messages = new ArrayList<Message>();

        for(int i = 1; i < jMenu1.getItemCount(); i ++){
            ActionListener[] message_arr = jMenu1.getItem(i).getActionListeners();
            sorted_messages.offer(((MessageListener)message_arr[0]).get());
        }

        while(!sorted_messages.isEmpty()){
            messages.add(sorted_messages.poll());
        }

        FileManager.saveFile(FormGeneratorApp.login, getFile(filename), messages);
    }

    private ArrayList<Message> loadFile(String filename){
        ArrayList<Message> messages;
        
        if((messages = (ArrayList<Message>)(FileManager.loadFile(FormGeneratorApp.login, getFile(filename), ArrayList.class))) != null){
            for(int i = jMenu1.getItemCount() - 1; i > 0; i --){
                jMenu1.remove(i);
            }

            for(int i = 0; i < messages.size(); i ++){
                load(messages.get(i));
                save();
                loadNewForm();
            }
        }
        return messages;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        spinner_month = new javax.swing.JSpinner();
        spinner_hour = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setVisible(false);
        jTextField7 = new javax.swing.JTextField();
        jTextField7.setVisible(false);
        jLabel10 = new javax.swing.JLabel();
        spinner_minute = new javax.swing.JSpinner();
        spinner_ampm = new javax.swing.JSpinner();
        spinner_day = new javax.swing.JSpinner();
        spinner_year = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CCCS Message Form");
        setAlwaysOnTop(true);
        setResizable(false);

        spinner_month.setModel(new javax.swing.SpinnerDateModel());
        spinner_month.setEditor(new javax.swing.JSpinner.DateEditor(spinner_month, "MM"));
        spinner_month.setName("spinner_month"); // NOI18N

        spinner_hour.setModel(new javax.swing.SpinnerDateModel());
        spinner_hour.setEditor(new javax.swing.JSpinner.DateEditor(spinner_hour, "hh"));
        spinner_hour.setName("spinner_hour"); // NOI18N

        jComboBox1.setName("jComboBox1"); // NOI18N

        jButton1.setText("Add");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setNextFocusableComponent(jTextArea1);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Edit");
        jToggleButton1.setName("jToggleButton1"); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jTextField1.setColumns(7);
        jTextField1.setName("jTextField1"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setColumns(10);
        jTextField3.setName("jTextField3"); // NOI18N

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Client");
        jRadioButton1.setName("jRadioButton1"); // NOI18N

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Creditor");
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Co-Worker");
        jRadioButton3.setName("jRadioButton3"); // NOI18N

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Other");
        jRadioButton4.setName("jRadioButton4"); // NOI18N

        jButton2.setText("Delete");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(1);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("Counseling");
        jRadioButton5.setName("jRadioButton5"); // NOI18N

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setText("Operations");
        jRadioButton6.setName("jRadioButton6"); // NOI18N

        buttonGroup2.add(jRadioButton7);
        jRadioButton7.setText("Housing");
        jRadioButton7.setName("jRadioButton7"); // NOI18N

        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setName("jTextField5"); // NOI18N

        jTextField6.setColumns(16);
        jTextField6.setName("jTextField6"); // NOI18N

        buttonGroup3.add(jRadioButton8);
        jRadioButton8.setSelected(true);
        jRadioButton8.setText("Home");
        jRadioButton8.setName("jRadioButton8"); // NOI18N

        buttonGroup3.add(jRadioButton9);
        jRadioButton9.setText("Cell");
        jRadioButton9.setName("jRadioButton9"); // NOI18N

        buttonGroup3.add(jRadioButton10);
        jRadioButton10.setText("Work");
        jRadioButton10.setName("jRadioButton10"); // NOI18N
        jRadioButton10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton10ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Date:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Time:");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Creditor Name:");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Rep Name:");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Account Number:");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Client Number:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Client Name:");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("Callback Number:");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText("extension:");
        jLabel9.setName("jLabel9"); // NOI18N

        jTextField7.setName("jTextField7"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Courier New", 1, 11));
        jLabel10.setText(":");
        jLabel10.setName("jLabel10"); // NOI18N

        spinner_minute.setModel(new javax.swing.SpinnerDateModel());
        spinner_minute.setEditor(new javax.swing.JSpinner.DateEditor(spinner_minute, "mm"));
        spinner_minute.setName("spinner_minute"); // NOI18N

        spinner_ampm.setModel(new javax.swing.SpinnerDateModel());
        spinner_ampm.setEditor(new javax.swing.JSpinner.DateEditor(spinner_ampm, "a"));
        spinner_ampm.setName("spinner_ampm"); // NOI18N

        spinner_day.setModel(new javax.swing.SpinnerDateModel());
        spinner_day.setEditor(new javax.swing.JSpinner.DateEditor(spinner_day, "dd"));
        spinner_day.setName("spinner_day"); // NOI18N

        spinner_year.setModel(new javax.swing.SpinnerDateModel());
        spinner_year.setEditor(new javax.swing.JSpinner.DateEditor(spinner_year, "yyyy"));
        spinner_year.setName("spinner_year"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Courier New", 1, 11));
        jLabel11.setText("/");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Courier New", 1, 11));
        jLabel12.setText("/");
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Courier New", 1, 11));
        jLabel13.setText(" ");
        jLabel13.setName("jLabel13"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu2.setText("Save&Clear");
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu4.setText("File");
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem7.setText("Email");
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem6.setText("Print");
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem3.setText("Save");
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setText("Delete");
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem10.setText("Main Menu");
        jMenuItem10.setName("jMenuItem10"); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Message List");
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem5.setText("New");
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Options");
        jMenu3.setName("jMenu3"); // NOI18N

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Always On Top");
        jCheckBoxMenuItem1.setName("jCheckBoxMenuItem1"); // NOI18N
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem1);

        jMenuItem8.setText("Set E-mail Recipients");
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Reset E-mail Recipients");
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem1.setText("Save Session");
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Open Session");
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Help");
        jMenu5.setName("jMenu5"); // NOI18N
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_ampm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jRadioButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jRadioButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton7))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinner_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinner_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(spinner_day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(spinner_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinner_hour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(spinner_minute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(spinner_ampm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton1))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton8)
                        .addComponent(jRadioButton9)
                        .addComponent(jRadioButton10)
                        .addComponent(jLabel9))
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        if(jRadioButton2.isSelected()){
            showCreditorFields(true);
        }
        else{
            showCreditorFields(false);
        }

        validate();
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void savePhoneNumber(int index){

        PhoneNumber.PhoneType type = PhoneNumber.PhoneType.Home;

        if(jRadioButton9.isSelected()){
            type = PhoneNumber.PhoneType.Cell;
        }
        else if(jRadioButton10.isSelected()){
            type = PhoneNumber.PhoneType.Work;
        }

        try{
            if(index < 0){
                jComboBox1.addItem(new PhoneNumber(type, PhoneNumber.format(jTextField3.getText()), PhoneNumber.formatExt(jTextField7.getText())));
            }
            else{
                jComboBox1.removeItemAt(index);
                jComboBox1.insertItemAt(new PhoneNumber(type, PhoneNumber.format(jTextField3.getText()), PhoneNumber.formatExt(jTextField7.getText())),
                    index);
            }
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        savePhoneNumber(-1);
        clearPhoneFields();
        jComboBox1.setSelectedIndex(jComboBox1.getItemCount() - 1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearPhoneFields(){
        jTextField3.setText("");
        jTextField7.setText("");
        jRadioButton8.setSelected(true);
    }

    private void setPhoneButtons(boolean enabled){
        jComboBox1.setEnabled(enabled);
        jButton1.setEnabled(enabled);
        jButton2.setEnabled(enabled);

        if(enabled){
            jToggleButton1.setText("Edit");
            jTextField3.setText("");
        }
        else{
            jToggleButton1.setText("Save");
        }
    }

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        //pre: normal, post: toggled
        if(jToggleButton1.isSelected()){
            setPhoneButtons(false);
            
            Object selected = jComboBox1.getSelectedItem();
            if(selected != null){
                jTextField3.setText(((PhoneNumber)selected).toPhoneString());
                jTextField7.setText(((PhoneNumber)selected).toExtString());
                if(((PhoneNumber)selected).getType() == PhoneNumber.PhoneType.Cell){
                    jRadioButton9.setSelected(true);
                }
                else if(((PhoneNumber)selected).getType() == PhoneNumber.PhoneType.Work){
                    jRadioButton10.setSelected(true);
                }
                else {
                    jRadioButton8.setSelected(true);
                }
            }
            else{
                jTextField3.setText("");
                jRadioButton8.setSelected(true);
            }
        }
        //pre: toggled, post: normal
        else{
            int index = jComboBox1.getSelectedIndex();
            savePhoneNumber(index);
            jComboBox1.setSelectedIndex(index);
            setPhoneButtons(true);
            clearPhoneFields();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jComboBox1.removeItemAt(jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String filename = JOptionPane.showInputDialog(this, "Enter a filename", "Save Current Session", JOptionPane.OK_CANCEL_OPTION);

        if(filename != null){
            if(filename.isEmpty()){
                saveFile(CalendarUtilities.getFFDateAndTime());
            }
            else{
                saveFile(filename);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String filename = JOptionPane.showInputDialog(this, "Enter a filename", "Load Previous Session", JOptionPane.OK_CANCEL_OPTION);

        if(filename != null){
            if(filename.isEmpty()){
                File dir[] = (new File(FormGeneratorApp.home_dir, getFile("").getName())).listFiles();
                
                if(dir != null){
                    File last = dir[0];

                    for(int i = 1; i < dir.length; i ++){
                        last = (dir[i].lastModified() < last.lastModified() ? dir[i] : last);
                    }

                    if(last != null) loadFile(last.getName());
                }
            }
            else{
                loadFile(filename);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        save();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        int index = getCurrentIndex();
        if(index != -1){
            if(JOptionPane.YES_OPTION == JOptionPane.showOptionDialog(this,
                    "Are you sure you want to delete" + jMenu1.getItem(index).getText() + "?",
                    "Not so fast...",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, null, null)){
                jMenu1.remove(index);
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if(saveMessageFirst() != JOptionPane.CANCEL_OPTION){
            loadNewForm();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        print(CalendarUtilities.getFFDateAndTime());
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        save();

        loadNewForm();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        setAlwaysOnTop(jCheckBoxMenuItem1.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jRadioButton10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton10ItemStateChanged
        if(jRadioButton10.isSelected()){
            jLabel9.setVisible(true);
            jTextField7.setVisible(true);
        }
        else{
            jLabel9.setVisible(false);
            jTextField7.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton10ItemStateChanged

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        email(CalendarUtilities.getFFDateAndTime());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JPanel message = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel label_to = new JLabel("To:");
        layout.addLayoutComponent(label_to, constraints);
        constraints.gridx = 1;
        JTextField field_to = new JTextField();
        field_to.setColumns(20);
        field_to.setText(getTo());
        layout.addLayoutComponent(field_to, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JLabel label_cc = new JLabel("CC:");
        layout.addLayoutComponent(label_cc, constraints);
        constraints.gridx = 1;
        JTextField field_cc = new JTextField();
        field_cc.setColumns(20);
        field_cc.setText(getCC());
        layout.addLayoutComponent(field_cc, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JLabel label_bcc = new JLabel("BCC:");
        layout.addLayoutComponent(label_bcc, constraints);
        constraints.gridx = 1;
        JTextField field_bcc = new JTextField();
        field_bcc.setColumns(20);
        field_bcc.setText(getBCC());
        layout.addLayoutComponent(field_bcc, constraints);

        message.add(label_to);
        message.add(field_to);
        message.add(label_cc);
        message.add(field_cc);
        message.add(label_bcc);
        message.add(field_bcc);
        message.setLayout(layout);

        if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,
                message,
                "Email Targets",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE)){
            setRecipients(field_to.getText(), field_cc.getText(), field_bcc.getText());
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "Are you sure?",
                "Continue?",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)){
            setDefaultRecipients();
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        saveFile(CalendarUtilities.getFFDateAndTime());
        dispose();
        FormGeneratorApp main_menu = new FormGeneratorApp();
        main_menu.setLocationRelativeTo(this);
        main_menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessageFormApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JSpinner spinner_ampm;
    private javax.swing.JSpinner spinner_day;
    private javax.swing.JSpinner spinner_hour;
    private javax.swing.JSpinner spinner_minute;
    private javax.swing.JSpinner spinner_month;
    private javax.swing.JSpinner spinner_year;
    // End of variables declaration//GEN-END:variables

}
