/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs;
import java.util.*;
import java.io.*;

/**
 *
 * @author George Hardigg
 */
public class DailyLog implements Serializable{
    
    public static class Client implements Serializable{
        private int client_id = 0;

        public Client(int client_id){
            this.client_id = client_id;
        }
        
        public int getClientID(){
            return client_id;
        }

        public String toString(){
            return (client_id != 0 ? Integer.toString(client_id) : "No ID");
        }
    }

    public static class Certificate implements Serializable{
        private Client client;
        private long certificate = 0;

        public Certificate(Client client, long certificate){
            this.client = client;
            this.certificate = certificate;
        }

        public long get(){
            return certificate;
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
            return client.toString();
        }
    }

    public static class Voucher  implements Serializable{
        private Client client;
        private long voucher = 0;

        public Voucher(Client client, long voucher){
            this.client = client;
            this.voucher = voucher;
        }

        public long get(){
            return voucher;
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
            return client.toString();
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

    public DailyLog(){}

    public DailyLog(Calendar calendar){
        this.calendar = calendar;
    }

    public Certificate addCertificate(String client, String certificate){
        Certificate new_certificate = parseCertificate(client, certificate);
        certificates.add(new_certificate);
        return new_certificate;
    }

    public Voucher addVoucher(String client, String voucher){
        Voucher new_voucher = parseVoucher(client, voucher);
        vouchers.add(new_voucher);
        return new_voucher;
    }

    private static Client addClient(String client_id){
        return new Client(Integer.parseInt((client_id.isEmpty() ? "0" : client_id)));
    }

    public static Certificate parseCertificate(String client, String certificate){
        return new Certificate(addClient(client),
                Certificate.parseCertificate((String)certificate));
    }

    public static Voucher parseVoucher(String client, String voucher){
        return new Voucher(addClient(client),
                Voucher.parseVoucher((String)voucher));
    }

}
