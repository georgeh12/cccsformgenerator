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
public class PhoneNumber implements Serializable {
    public enum PhoneType {
        Home, Cell, Work, Fax;
        public String toString(){
            if(this.equals(Home)){
                return "Home";
            }
            else if(this.equals(Cell)){
                return "Cell";
            }
            else if(this.equals(Work)){
                return "Work";
            }
            else if(this.equals(Fax)){
                return "Work";
            }

            return "";
        }
    }
    private PhoneType type = PhoneType.Home;
    private long number = 0;

    public PhoneNumber(){}

    public PhoneNumber(PhoneType type, long number){
        this.type = type;
        this.number = number;
    }

    public static long format(String number_string){
        String new_string = "";

        for(int i = 0; i < number_string.length(); i ++){
            if(Character.isDigit(number_string.charAt(i))
                    && number_string.charAt(i) != '-'){
                new_string += number_string.charAt(i);
            }
        }

        if(new_string.length() == 0){
            new_string = "0";
        }
        else if(new_string.length() > 10){
            new_string = new_string.substring(0, 10);
        }

        return Long.parseLong(new_string);
    }
    
    public PhoneType getType(){
        return type;
    }

    public String toString(){
        String number_string = Long.toString(number);

        while(number_string.length() < 10){
            number_string = "0" + number_string;
        }

        return number_string.substring(0, 3) + "-" + number_string.substring(3, 6) +
                "-" + number_string.substring(6);
    }
    
    public void set(PhoneType type){
        this.type = type;
    }

    public void set(long number){
        this.number = number;
    }

    public boolean equals(PhoneNumber contact){
        return this.type.compareTo(contact.type) == 0;
    }
}
