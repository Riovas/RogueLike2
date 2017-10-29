/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Map extends JTextPane implements ActionListener{
    String mapContent;
    Player player;
    Timer timer;
    Font font;
    public static String loadedMap;
    public static String[][] map;
    public static int height, width;
    public static int viewHeight, viewWidth;
    public final int TOP = 0, BOTTOM = 1, LEFT = 2, RIGHT = 3;
    public static ArrayList<Door> doors;
    MutableAttributeSet style;
    StyledDocument doc; 
    
    
    public Map(String[][] map, int height, int width) {
        this.map = map;
        //initial player view size
        viewHeight = 12;
        viewWidth = 12;
        loadedMap = "map1";
        //map dimensions
        this.height = height;
        this.width = width;
        player = new Player("Harry", 1, 1);
        addKeyListener(player);
        map[player.getY()][player.getX()] = player.character;
        initDoors();
        font = new Font("monospaced", Font.PLAIN, 24);
        setPreferredSize(new Dimension (500, 500));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
                Color.BLUE, Color.BLUE));
        setBackground(Color.black);
        //setForeground(Color.white);
        setFont(font);
        setHighlighter(null);
        mapContent = "";
        style = getInputAttributes();
        doc = getStyledDocument();
        initMap();
        setEditable(false);
        timer = new Timer(50, this);
        timer.setInitialDelay(0);
        timer.start();
    }
    
    //gets any "D" from the map, creates a door object and places in the
    //array list
    public static void initDoors() {
        doors = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(map[i][j].equals("D")) {
                    String[] arr = ReadFile.hmap.get(loadedMap);
                    String line = arr[count];
                    String[] arr2 = line.split("=");
                    doors.add(new Door(j, i, arr2[1]));
                    count++;
                }
            }
        }
    }
    
    public void updateMap() {
        mapContent = "";
        try{
        doc.remove(0, doc.getLength());
        } catch(BadLocationException ex) {
            
        }
                for(int i = 0 + player.playerView[TOP]; i < viewHeight + player.playerView[BOTTOM]; i++) {
            for(int j = 0 + player.playerView[LEFT]; j < viewWidth + player.playerView[RIGHT]; j++) {
                mapContent = map[i][j];
                
                
                switch(mapContent) {
                    case "#":
                        StyleConstants.setForeground(style, Color.GRAY);
                        break;
                    case ".":
                        StyleConstants.setForeground(style, Color.DARK_GRAY);
                        break;
                    case "o":
                        StyleConstants.setForeground(style, Color.GRAY);
                        break;
                    case "-":
                        StyleConstants.setForeground(style, Color.CYAN);
                        break;
                    case "D":
                        StyleConstants.setForeground(style, Color.GREEN.darker());
                        break;
                    case "@":
                        StyleConstants.setForeground(style, Color.ORANGE);
                        break;
                    case "X":
                        StyleConstants.setForeground(style, Color.RED.darker());
                        break;
                    case "K":
                        StyleConstants.setForeground(style, Color.YELLOW.darker());
                        break;
                    case "x":
                        StyleConstants.setForeground(style, Color.MAGENTA);
                        break;
                }
                
                
                try {
                doc.insertString(doc.getLength(), mapContent, style);
            } catch (BadLocationException ex) {
                
            }
                
            }
           try {
                doc.insertString(doc.getLength(), "\n", style);
            } catch (BadLocationException ex) {
                
            }
        }
    }
    
    public void initMap() {
        for(int i = 0 + player.playerView[TOP]; i < viewHeight + player.playerView[BOTTOM]; i++) {
            for(int j = 0 + player.playerView[LEFT]; j < viewWidth + player.playerView[RIGHT]; j++) {
                mapContent = map[i][j];
                
                
                switch(mapContent) {
                    case "#":
                        StyleConstants.setForeground(style, Color.GRAY);
                        break;
                    case ".":
                        StyleConstants.setForeground(style, Color.DARK_GRAY);
                        break;
                    case "o":
                        StyleConstants.setForeground(style, Color.GRAY);
                        break;
                    case "D":
                        StyleConstants.setForeground(style, Color.GREEN.darker());
                        break;
                    case "@":
                        StyleConstants.setForeground(style, Color.ORANGE);
                        break;
                    case "X":
                        StyleConstants.setForeground(style, Color.RED.darker());
                        break;
                    case "K":
                        StyleConstants.setForeground(style, Color.YELLOW.darker());
                        break;
                    case "x":
                        StyleConstants.setForeground(style, Color.MAGENTA);
                        break;
                }
                
                
                try {
                doc.insertString(doc.getLength(), mapContent, style);
            } catch (BadLocationException ex) {
                
            }
                
            }
           try {
                doc.insertString(doc.getLength(), "\n", style);
            } catch (BadLocationException ex) {
                
            }
        }
    }
    
    public static Door getDoor(int x, int y) {
        Door temp = null;
        for (Door door : doors) {
            if(door.x == x && door.y == y) {
                temp = door;
            }
        }
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateMap();
    }
    
}