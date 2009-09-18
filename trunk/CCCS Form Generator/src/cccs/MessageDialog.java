package cccs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordDialog extends JDialog implements ActionListener {

    private JFrame frame = new JFrame("Authenticating");
    private JDialog d = new JDialog();
    private JPasswordField password = new JPasswordField();
    private JButton ok =  new JButton("OK");
    private JButton cancel = new JButton("Cancel");
    private JLabel prompt = new JLabel("Please enter your password:");
    JPanel passPanel = new JPanel();
    JPanel labelInput = new JPanel();
    JPanel buttons = new JPanel();
    private String result;

    PasswordDialog(){
        super();
        passPanel.setLayout(new BorderLayout());
        labelInput.setLayout(new GridLayout(2,1));
        buttons.setLayout(new BorderLayout());

        password.setEchoChar('*');

        ok.addActionListener(this);
        cancel.addActionListener(this);

        labelInput.add(prompt);
        labelInput.add(password);
        buttons.add(ok, BorderLayout.WEST);
        buttons.add(cancel, BorderLayout.EAST);
        passPanel.setPreferredSize(new Dimension(200, 100));
        passPanel.add(labelInput, BorderLayout.NORTH);
        passPanel.add(buttons, BorderLayout.SOUTH);

//        d.add(passPanel);
        d.setResizable(false);
        d.setModal(true);
        d.setDefaultCloseOperation(EXIT_ON_CLOSE);
        

    }

    public void actionPerformed(ActionEvent e){

        char[] tempPassArray = password.getPassword();
        StringBuffer tempPassWhole = new StringBuffer("");

        if(e.getSource() == ok){
            for(int i=0; i<tempPassArray.length; i++){
                tempPassWhole.append(tempPassArray[i]);
            }
            result = tempPassWhole.toString();
        }
        
        else{
            d.dispose();
        }

    }
    
    public String getText(){
        return result;
    }

    public void showDialog(){
        frame.add(passPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
