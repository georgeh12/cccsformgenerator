/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.certificate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author ghardigg
 */
public class Counselors {
    private String agencyID = "MD02068";
    private ArrayList<Counselor> counselors = new ArrayList<Counselor>();

    public void changeAgencyID(String agencyID){
        this.agencyID = agencyID;
    }

    public boolean addCounselor(String username, String password){
        Counselor counselor = new Counselor(username, password);
        if(counselors.contains(counselor)){
            return false;
        }
        else{
            counselors.add(counselor);
            return true;
        }
    }

    public boolean removeCounselor(int index){
        if(index >= 0 && index < counselors.size()){
            counselors.remove(index);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean changePassword(int index, String password){
        if(index >= 0 && index < counselors.size()){
            counselors.get(index).changePassword(password);

            return true;
        }
        return false;
    }

    private class Counselor {
        private String username;
        private String password;

        private Counselor(String username, String password){
            this.username = username;
            this.password = password;
        }

        public void changePassword(String password) {
            this.password = password;
        }

        public boolean authenticate(){
            try {
                new URL("https://ccdecert.ustp.usdoj.gov/ccdecert/loginConfirm.do?"
                        + "agencyId=" + agencyID
                        + "&agencyUserId=" + username
                        + "&password=" + password).openStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                return false;
            }

            return true;
        }

        public boolean equals(Counselor counselor){
            return this.username.equals(counselor.username);
        }
    }
}
