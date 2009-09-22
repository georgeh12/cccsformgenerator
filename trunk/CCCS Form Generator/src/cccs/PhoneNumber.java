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
    private int extension = 0;

    public PhoneNumber(){}

    public PhoneNumber(PhoneType type, long number){
        this();
        this.type = type;
        this.number = number;
    }

    public PhoneNumber(PhoneType type, long number, int extension){
        this(type, number);
        this.extension = extension;
    }

    public static boolean isEmpty(String number_string){
        return getDigits(number_string).isEmpty();
    }

    private static String getDigits(String number_string){
        String new_string = "";

        for(int i = 0; i < number_string.length(); i ++){
            if(Character.isDigit(number_string.charAt(i))
                    && number_string.charAt(i) != '-'){
                new_string += number_string.charAt(i);
            }
        }
        return new_string;
    }

    public static long format(String number_string){
        String new_string = "";

        new_string = getDigits(number_string);

        if(new_string.length() == 0){
            new_string = "0";
        }
        else if(new_string.length() > 10){
            new_string = new_string.substring(0, 10);
        }

        return Long.parseLong(new_string);
    }

    public static int formatExt(String number_string){
        String new_string = getDigits(number_string);

        if(new_string.length() == 0){
            new_string = "0";
        }
        else if(new_string.length() > 7){
            new_string = new_string.substring(0, 7);
        }

        return Integer.parseInt(new_string);
    }
    
    public PhoneType getType(){
        return type;
    }

    public String toPhoneString(){
        String number_string = Long.toString(number);

        while(number_string.length() < 10){
            number_string = "0" + number_string;
        }

        return number_string.substring(0, 3) + "-" + number_string.substring(3, 6) +
                "-" + number_string.substring(6);
    }

    public String toExtString(){
        return (extension == 0 ? "" : "" + extension);
    }

    public String toString(){
        return toPhoneString() +
                (toExtString().isEmpty() ? "" : " x" + toExtString());
    }

    public boolean equals(PhoneNumber contact){
        return this.type.equals(contact.type);
    }
}
