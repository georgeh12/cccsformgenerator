/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.utility;

import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author ghardigg
 */
public class MinimizeToTray extends WindowAdapter {
    private boolean closing = false;
    private Component active_frame = null;

    public void windowClosing(WindowEvent evt){
        if(!closing){
            active_frame = evt.getComponent();
        }
    }
}
