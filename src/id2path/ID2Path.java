/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id2path;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.omg.CORBA.Environment;

/**
 *
 * @author QAMAR
 */
public class ID2Path {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            File[] files = null;
            File file = new File("");
            if(args.length>0){
                files = new File[]{new File(args[0])};
            }          
            else{
                files = (new File(file.getAbsolutePath())).listFiles();
            }
            String result= "The IDs in following files are successfully changed to R.type.resouce form!\n";
            MainReplacer m = new MainReplacer();
            BufferedReader idsReader = new BufferedReader(new FileReader(new File("R.java")));
            if(!m.collectIDs(idsReader)){
                JOptionPane.showMessageDialog(null, "Sorry!\nCan't collect IDs beause wrong R.java file.","ID2Path by qamar4p@gmail.com",2);
                result= "The IDs in zero files are successfully changed to R.type.resouce form!\n";
            }
            
            for(int i=0;i<files.length;i++){
                if(files[i].getName().toLowerCase().endsWith(".java") & !files[i].getName().toLowerCase().equals("r.java")){
                    result += "\n"+files[i].getName();
                    BufferedReader targetReader = new BufferedReader(new FileReader(files[i]));
                    m.replaceIDs(targetReader,files[i].getAbsolutePath());
                }
            }
            JOptionPane.showMessageDialog(null,result,"Thank you for using qamar4p@gmail.com app!",1);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage()+"\n\nPlease keep R.java file in same directory with ID2Path.jar","ID2Path by qamar4p@gmail.com",2);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ID2Path by qamar4p@gmail.com",2);
        }
    }
    
}
