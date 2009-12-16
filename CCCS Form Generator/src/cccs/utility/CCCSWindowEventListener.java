/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.utility;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ghardigg
 */
public class CCCSWindowEventListener extends javax.swing.JFrame implements WindowListener {
    private boolean close = false;

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE)){
            System.exit(0);
        }
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}
