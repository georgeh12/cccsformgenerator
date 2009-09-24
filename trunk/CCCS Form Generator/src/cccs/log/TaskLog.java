/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.log;
import java.util.*;
import java.io.*;

/**
 *
 * @author George Hardigg
 */
public class TaskLog implements Serializable{
    
    public static class Client implements Serializable{
        private String client_name = "";
        private int client_id = 0;

        public Client(String client_name, int client_id){
            this.client_name = client_name;
            this.client_id = client_id;
        }
        
        public String getName(){
            return client_name;
        }

        public String getID(){
            return (client_id != 0 ? Integer.toString(client_id) : noID());
        }

        public static String noID(){
            return "No ID";
        }

        public String toString(){
            return getName() + " (" + getID() + ")";
        }
    }

    public static class Certificate implements Serializable{
        private Client client;
        private long certificate = 0;

        public Certificate(Client client, long certificate){
            this.client = client;
            this.certificate = certificate;
        }

        public Client get(){
            return client;
        }

        public static long parseCertificate(String certificate){
            String digits = "";

            for(int i = 0; i < certificate.length(); i ++){
                if(Character.isDigit(certificate.charAt(i))){
                    digits += certificate.charAt(i);
                }
            }
            
            if(digits.length() > 9){
                digits = digits.substring(digits.length() - 10, digits.length());
            }
            
            return (digits.isEmpty() ? 0 : Long.parseLong(digits));
        }

        public String toString(){
            String certificate_string = Long.toString(certificate);

            while(certificate_string.length() < 9){
                certificate_string = "0" + certificate_string;
            }

            return certificate_string;
        }
    }

    public static class Voucher  implements Serializable{
        private Client client;
        private long voucher = 0;

        public Voucher(Client client, long voucher){
            this.client = client;
            this.voucher = voucher;
        }

        public Client get(){
            return client;
        }

        public static long parseVoucher(String voucher){
            String digits = "";

            for(int i = 0; i < voucher.length(); i ++){
                if(Character.isDigit(voucher.charAt(i))){
                    digits += voucher.charAt(i);
                }
            }

            if(digits.length() > 16){
                digits = digits.substring(digits.length() - 17, digits.length());
            }

            return (digits.isEmpty() ? 0 : Long.parseLong(digits));
        }

        public String toString(){
            String voucher_string = Long.toString(voucher);

            while(voucher_string.length() < 16){
                voucher_string = "0" + voucher_string;
            }

            return voucher_string;
        }
    }

    public static class Calls implements Serializable{
        private int calls = 0;

        public int get(){
            return calls;
        }

        public void inc(){
            calls++;
        }

        public void dec(){
            calls--;
        }
    }

    public Calls calls_in = new Calls();
    public Calls calls_out = new Calls();
    public ArrayList<Certificate> certificates = new ArrayList<Certificate>();
    public ArrayList<Voucher> vouchers = new ArrayList<Voucher>();
    public Calendar calendar = Calendar.getInstance();

    public TaskLog(){}

    public TaskLog(Calendar calendar){
        this.calendar = calendar;
    }

    private static Client addClient(String client_name, String client_id){
        return new Client(client_name,
                Integer.parseInt((client_id.isEmpty() ? "0" : client_id)));
    }

    public static Certificate parseCertificate(String client_name,
            String client_id, String certificate){
        return new Certificate(addClient(client_name, client_id),
                Certificate.parseCertificate((String)certificate));
    }

    public static Voucher parseVoucher(String client_name, String client_id,
            String voucher){
        return new Voucher(addClient(client_name, client_id),
                Voucher.parseVoucher((String)voucher));
    }

    public String toString(){
        StringBuffer log = new StringBuffer();

        log.append("Calls in: " + calls_in.get() + "\r\n");
        log.append("Calls out: " + calls_out.get() + "\r\n");

        log.append("\r\nClient completion: ");
        if(!certificates.isEmpty()) log.append(certificates.get(0).toString());
        for(int i = 1; i < certificates.size(); i ++){
            log.append(", " + certificates.get(i).toString());
        }

        log.append("\r\nCertificates issued: ");
        if(!certificates.isEmpty()) log.append(certificates.get(0).get());
        for(int i = 1; i < certificates.size(); i ++){
            log.append(", " + certificates.get(i).get());
        }

        log.append("\r\n\r\nClients waiving fee: ");
        if(!vouchers.isEmpty()) log.append(vouchers.get(0).toString());
        for(int i = 1; i < vouchers.size(); i ++){
            log.append(", " + vouchers.get(i).toString());
        }

        log.append("\r\nVouchers issued: ");
        if(!vouchers.isEmpty()) log.append(vouchers.get(0).get());
        for(int i = 1; i < vouchers.size(); i ++){
            log.append(", " + vouchers.get(i).get());
        }

        return log.toString();
    }
}
