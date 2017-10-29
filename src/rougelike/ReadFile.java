/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile {
    
    BufferedReader br = null;
    String fileName;
    public int height, width;
    public String[][] map;
    public static HashMap<String, String[]> hmap = new HashMap<>();
    
    public ReadFile(String fileName) {
        this.fileName = fileName;  
    }
    
    public void readFromFile() throws IOException {
        File file = new File(fileName);
        try {
           br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist");
        }
        String st, temp;
        ArrayList<String> arr = new ArrayList<>();
        try {
            while((st=br.readLine()) != null){
                arr.add(st);
            }
            //gets size of arraylist to determine height/length
            height = arr.size();
            
            //gets width 
            temp = arr.get(0);
            
            String[] tempArr;
            
            tempArr = temp.split(",");
            width = tempArr.length;
            map = new String[height][width];
            
            for(int i = 0; i < arr.size(); i++) {
                temp = arr.get(i);
                tempArr = temp.split(",");
                for(int j = 0; j < tempArr.length; j++) {     
                    map[i][j] = tempArr[j];
                }
            }
        } catch (IOException ex) {
            
        } finally {
            br.close();
        }
        
    }
    
    public void readConfig() throws IOException {
        File file = new File("src/resources/config.txt");
        try {
           br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist");
        }
        
        String st = "";
        String line = "";
        String[] mapName, doors;
        
        try {
            while((st=br.readLine()) != null){
                mapName = st.split(":");
                line = mapName[1];
                doors = line.split(",");
                hmap.put(mapName[0], doors);
                mapName = null;
                doors = null;
            }

        } catch (IOException ex) {
            
        } finally {
            br.close();
       }
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}