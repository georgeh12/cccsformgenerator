/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.message;
import cccs.utility.CalendarUtilities;
import cccs.utility.PhoneNumber;
import cccs.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author George Hardigg
 */
public class Message implements Serializable, Comparable {
    public enum CallFrom{
        Client, Creditor, Coworker, Other;

        public String toString(){
            if(this.equals(Client)){
                return "Client";
            }
            else if(this.equals(Creditor)){
                return "Creditor";
            }
            else if(this.equals(Coworker)){
                return "Coworker";
            }
            else if(this.equals(Other)){
                return "Other";
            }

            return "";
        }
    }
    public enum Dept {
        Counseling, Operations, Housing;

        public String toString(){
            if(this.equals(Counseling)){
                return "Counseling";
            }
            else if(this.equals(Operations)){
                return "Operations";
            }
            else if(this.equals(Housing)){
                return "Housing";
            }

            return "";
        }
    }

    public static class CreditorInfo implements Serializable{
        private String creditor_name = "";
        private String rep_name = "";
        private String acct_number = "";

        public CreditorInfo(String creditor_name, String rep_name, String acct_number){
            this.creditor_name = creditor_name;
            this.rep_name = rep_name;
            this.acct_number = acct_number;
        }

        public String getCreditorName(){
            return creditor_name;
        }

        public String getRepName(){
            return rep_name;
        }

        public String getAcctNumber(){
            return acct_number;
        }
    }

    private Calendar date = null;
    private CallFrom call_from = CallFrom.Client;
    private Dept dept = Dept.Counseling;
    private String name = "";
    private int id = 0;
    private ArrayList<PhoneNumber> contacts = new ArrayList<PhoneNumber>();
    private StringBuffer message = new StringBuffer();
    private CreditorInfo creditor_info = null;

    public Message(){}

    public Message(Calendar date, CallFrom call_from, Dept dept, String name,
            int id, ArrayList<PhoneNumber> contacts, StringBuffer message,
            CreditorInfo creditor_info){
            this.date = date;
            this.call_from = call_from;
            this.dept = dept;
            this.name = name;
            this.id = id;
            this.contacts = contacts;
            this.message = message;
            this.creditor_info = creditor_info;
            this.date.set(Calendar.SECOND, 0);
            this.date.set(Calendar.MILLISECOND, 0);
    }

    public Calendar getDate(){
        return date;
    }

    public String formatDay(){
        return CalendarUtilities.formatDay(date);
    }

    public String formatDate(){
        return CalendarUtilities.formatDate(date);
    }

    public String formatTime(){
        return CalendarUtilities.formatTime(date);
    }

    public CallFrom getCallFrom(){
        return call_from;
    }

    public Dept getDept(){
        return dept;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public ArrayList<PhoneNumber> getContacts(){
        return contacts;
    }

    public StringBuffer getMessage(){
        return message;
    }

    public CreditorInfo getCreditorInfo(){
        return creditor_info;
    }

    @Override
    public String toString(){
        return name + " @ " + formatDay() + ", " + formatDate() + " " +
                formatTime();
    }

    public int compareTo(Object o) {
        if(o.getClass() != Message.class){
            return 0;
        }
        else{
            return date.compareTo(((Message)o).date);
        }
    }
}
