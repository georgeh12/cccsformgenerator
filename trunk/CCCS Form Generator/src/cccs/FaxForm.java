/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs;
import java.io.*;

/**
 *
 * @author George Hardigg
 */
public class FaxForm implements Serializable {
    private String to = "";
    private String from = "";
    private PhoneNumber fax = new PhoneNumber();
    private int pages = 0;
    private String sent_by = "";
    private StringBuffer message = new StringBuffer();

    public FaxForm(String to, String from, PhoneNumber fax, int pages,
            String sent_by, StringBuffer message){
        this.to = to;
        this.from = from;
        this.fax = fax;
        this.pages = pages;
        this.sent_by = sent_by;
        this.message = message;
    }

    public String get_to(){
        return to;
    }

    public String get_from(){
        return from;
    }

    public String get_fax(){
        return fax.toString();
    }

    public String get_pages(){
        return Integer.toString(pages);
    }

    public String get_sent_by(){
        return sent_by;
    }

    public String get_message(){
        return message.toString();
    }
}
