/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.utility;

/**
 *
 * @author ghardigg
 */
public class EmailUtilities {
    public static String formatMailto(String s){
        return s.replace("%", "%25").replace(" ", "%20").replace("\r", "%0D").replace("\n", "%0A").replace(":", "%3A").replace("/", "%2F").replace("-", "%2D").replace(",", "%2C").replace("#", "%23");
    }
}
