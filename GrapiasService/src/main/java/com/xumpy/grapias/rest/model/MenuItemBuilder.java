/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SortedSetMultimap;
import com.xumpy.grapias.exceptions.MenuItemNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuItemBuilder {
    @Autowired
    private SortedSetMultimap<Integer, MenuItem> allMenuItems;
    
    private String url;
    
    public void build(MenuItem menuItem, File csvFile){
        if (allMenuItems.size() == 0){
            allMenuItems.put(0, menuItem);
            
            allMenuItems.putAll(buildCSV(csvFile));
        }
    }
    
    public MenuItem getMenuItem(Integer menuId) throws MenuItemNotFoundException{
        for(Entry<Integer, MenuItem> entry: allMenuItems.entries()){
            if(entry.getValue().getId().equals(menuId)){
                return entry.getValue();
            }
        }
        throw new MenuItemNotFoundException();
    }
    
    public MenuItem previousMenuItem(MenuItem menuItem) throws MenuItemNotFoundException{
        for(Entry<Integer, MenuItem> entry: allMenuItems.entries()){
            if(entry.getValue().getId().equals(menuItem.getId())){
                return getMenuItem(entry.getKey());
            }
        }
        throw new MenuItemNotFoundException();
    }
    
    public ListMultimap<Integer, MenuItem> buildCSV(File menuCsv){
    	String line;
        String cvsSplitBy = ",";
        ListMultimap<Integer, MenuItem> csvMenuItems = ArrayListMultimap.create();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(menuCsv));
            while ((line = br.readLine()) != null) {
                    String[] csvMenuInfo = line.split(cvsSplitBy);
                    MenuItem menuItem = new MenuItem();
                    menuItem.setId(Integer.parseInt(csvMenuInfo[0]));
                    menuItem.setDescription(csvMenuInfo[2]);
                    menuItem.setUrl(url);
                    
                    csvMenuItems.put(Integer.parseInt(csvMenuInfo[1]), menuItem);
            }
        } catch (IOException | NumberFormatException ex){
            ex.printStackTrace();
        }
        
        return csvMenuItems;
    }
    
    public MenuItems enter(MenuItem menuItem) throws MenuItemNotFoundException{
        MenuItems menuItems = new MenuItems();
        menuItems.setMenuItems(allMenuItems.get(menuItem.getId()));
        
        if(menuItems.getMenuItems().size() == 0){
            return enter(previousMenuItem(menuItem));
        }
        
        return menuItems;
    }
    
    public MenuItems back(MenuItem menuItem) throws MenuItemNotFoundException{
        try{
            return enter(previousMenuItem(previousMenuItem(menuItem)));
        } catch(MenuItemNotFoundException ex){}
        return enter(getMenuItem(1));
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
