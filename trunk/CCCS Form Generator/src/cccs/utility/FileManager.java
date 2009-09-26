/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cccs.utility;
import cccs.*;
import java.io.*;
import javax.swing.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author George Hardigg
 */
public class FileManager {

    public static class LoginManager {
        public static class Login implements Serializable{
            private String username;
            private String password;
            private String display;

            public Login(String username, String password){
                this.username = username;
                this.password = password;
                this.display = username;
            }

            private boolean equals(Login login){
                if(this.username.equals(username) &&
                        this.password.equals(password)){
                    return true;
                }

                return false;
            }

            public File getDir(){
                return FileManager.getDir(username);
            }

            public String getDisplay(){
                return display;
            }

            public void setDisplay(String display){
                this.display = display;
            }
        }

        public static Login authenticate(Login login){
            return (Login)FileManager.loadFile(login.getDir(), "login", Login.class, login.password);
        }

        public static boolean add(Login login){
            if(authenticate(login) == null){
                FileManager.saveFile(login.getDir(), "login", login, login.password);
                return true;
            }
            else{
                return false;
            }
        }

        private static void removeRecursively(File dir){
            File file_list[] = dir.listFiles();
            for(int i = 0; i < file_list.length; i ++){
                if(file_list[i].isDirectory()){
                    removeRecursively(file_list[i]);
                }
                else{
                    file_list[i].delete();
                }
            }
            dir.delete();
        }

        public static boolean remove(Login login){
            if(authenticate(login) != null){
                File dir = login.getDir();
                if(dir.exists()){
                    removeRecursively(dir);

                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }

        public static boolean set(Login login){
            Login saved = authenticate(login);
            if(saved != null){
                FileManager.saveFile(login.getDir(), "login", login, login.password);

                return true;
            }
            else{
                return false;
            }
        }
    }


    public static boolean fileExists(String filename){
        return getFile(FormGeneratorApp.home_dir, filename).exists();
    }

    public static File getFile(File dir, String filename){
        if(dir.getName().length() > 1){
            return new File(dir, filename + ".fga");
        }
        else{
            return new File(filename + ".fga");
        }
    }

    public static File getDir(String dir){
        return new File(dir);
    }

    public static void saveFile(LoginManager.Login login, File filename, Object my_object){
        if(!filename.getName().toLowerCase().equals("login")){
            if(LoginManager.authenticate(login) != null){
                saveFile(new File(login.getDir().getName() + "\\" + filename.getParent()), filename.getName(), my_object, login.password);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Reserved filename \"login\" not allowed",
                    "Reserved Filename Error",
                    JOptionPane.ERROR_MESSAGE, null);
        }
    }

    public static Object loadFile(LoginManager.Login login, File filename, Class my_class){
        if(LoginManager.authenticate(login) != null){
            return loadFile(new File(login.getDir().getName() + "\\" + filename.getParent()), filename.getName(), my_class, login.password);
        }
        else{
            return my_class.cast(null);
        }
    }

    private static String padPassword(String password){
        while(password.length() < 16){
            password += Character.MIN_VALUE;
        }

        return password;
    }

    private static void saveFile(File dir, String filename, Object my_object, String password){
        password = padPassword(password);

        try{
            SecretKey secret_key = new SecretKeySpec(password.getBytes(), "AES");

            File file = getFile(dir, filename);
            if(!dir.exists()) dir.mkdir();
            file.setWritable(true);
            if(file.exists()) file.delete();

            file.createNewFile();
            file.setWritable(true);

            // Create Cipher
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, secret_key);

            ObjectOutputStream oos = new ObjectOutputStream(
                    new CipherOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)),
                    aesCipher));
            oos.writeObject(my_object);
            oos.flush();
            oos.close();
            file.setWritable(false);
        }
        //wrong password, or corrupted file
        catch(StreamCorruptedException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static Object loadFile(File dir, String filename, Class my_class, String password){
        password = padPassword(password);

        File file = getFile(dir, filename);
        ObjectInputStream ois = null;

        try{
            SecretKey secret_key = new SecretKeySpec(password.getBytes(), "AES");
            
            if(file.exists() && dir.exists()){
                // Create Cipher
                Cipher aesCipher = Cipher.getInstance("AES");
                aesCipher.init(Cipher.DECRYPT_MODE, secret_key);

                ois = new ObjectInputStream(new CipherInputStream(new BufferedInputStream(new FileInputStream(file)), aesCipher));
                Object my_object = ois.readObject();

                ois.close();
                return my_class.cast(my_object);
            }
        }
        catch(java.security.InvalidKeyException e){
            e.printStackTrace();
        }
        catch(InvalidClassException e){
            //An update has caused this file to be incompatible
            try{
                int option = JOptionPane.showConfirmDialog(null,
                        "Updates to this program have caused this file to be incompatible. Delete outdated file?",
                        "Error Opening File: " + file.getName(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);

                if(option == JOptionPane.YES_OPTION){
                    if(ois != null){
                        ois.close();
                        if(file.exists()) file.delete();
                        if(dir.listFiles().length == 0) dir.delete();
                    }
                }
            }
            catch(Exception e2){
                e2.printStackTrace();
            }
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(StreamCorruptedException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
