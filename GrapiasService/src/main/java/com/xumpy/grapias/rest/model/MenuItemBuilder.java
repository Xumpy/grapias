/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MenuItemBuilder {
    private static ListMultimap<Integer, MenuItem> allMenuItems;
    
    public static void build(MenuItem menuItem){
        if (allMenuItems == null){
            
            allMenuItems = ArrayListMultimap.create();
            allMenuItems.put(menuItem.getId(), menuItem);
            
            allMenuItems.putAll(buildCSV("menu.csv"));
        }
    }
    
    public static ListMultimap<Integer, MenuItem> buildCSV(String menuCSV){
    	File file = new File(MenuItemBuilder.class.getClass().getResource(menuCSV).getFile());
        
        String line;
        String cvsSplitBy = ",";
        ListMultimap<Integer, MenuItem> csvMenuItems = ArrayListMultimap.create();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                    String[] csvMenuInfo = line.split(cvsSplitBy);
                    MenuItem menuItem = new MenuItem();
                    menuItem.setId(Integer.parseInt(csvMenuInfo[0]));
                    menuItem.setDescription(csvMenuInfo[2]);
                    menuItem.setUrl("http://localhost:8080/grapias");
                    
                    csvMenuItems.put(Integer.parseInt(csvMenuInfo[1]), menuItem);
            }
        } catch (IOException | NumberFormatException ex){
            ex.printStackTrace();
        }
        
        return csvMenuItems;
    }
    
    public static MenuItems enter(MenuItem menuItem){
        
    }
}
