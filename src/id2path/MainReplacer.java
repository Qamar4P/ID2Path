/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id2path;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author QAMAR
 */
public class MainReplacer {
    HashMap<String, String> resPaths;
    public MainReplacer(){
        resPaths = new HashMap<>();
    }
    
    public boolean collectIDs(BufferedReader resBuff) throws IOException{
    
        String line = "";
        String path = "";
        String id = "";
        while((line=resBuff.readLine())!=null){
            line = line.replace(" ","");
            if(line.equals("publicfinalclassR")){
                break;
            }
        }
        if(line == null) return false;
        
        path = "";
        
        while((line=resBuff.readLine())!=null){
            line = line.replace(" ","");
            if(line.contains("publicstaticfinalclass"))
            {
                path = "R." + line.substring(("publicstaticfinalclass").length()) + ".";
                continue;
            }
            
            if(line.contains("publicstaticfinalint"))
            {
                id = line.substring(line.indexOf('=')+1).replace(";","");
                String name = line.substring(("publicstaticfinalint").length(),line.lastIndexOf('='));
                
                resPaths.put(id, path+name);
                //JOptionPane.showMessageDialog(null, path+name+"\n"+id);
            }
        }
        resBuff.close();
        return true;
    }
    
    public void replaceIDs(BufferedReader targetBuff,String fileName) throws IOException{
        
        LinkedList<String> list = new LinkedList<>();
        String line = "";
        while ((line=targetBuff.readLine())!=null) {            
            list.add(line);
        }
        targetBuff.close();
        
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        Object[] keys = resPaths.keySet().toArray();
        int index = 0;
        while(index < list.size()){
            line = list.get(index);
            int n = 0;
            while(n < keys.length){
                String key = (String)keys[n];
                if(line.contains(key))
                    line = line.replace(key, resPaths.get(key));
                n++;
            }
            output.write(line);
            output.newLine();
            index++;
        }
        output.close();
        
    }
    
}
