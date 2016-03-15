/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.controller;

import com.xumpy.grapias.exceptions.MenuItemNotFoundException;
import com.xumpy.grapias.rest.interfaces.Grapias;
import com.xumpy.grapias.rest.model.MenuItem;
import com.xumpy.grapias.rest.model.MenuItemBuilder;
import com.xumpy.grapias.rest.model.MenuItems;
import java.io.File;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nico
 */

@RestController
@RequestMapping("/grapias")
@ComponentScan("com.xumpy.grapias")
public abstract class GrapiasCtrl implements Grapias{
    
    @Autowired MenuItemBuilder menuItemBuilder;
    private static Logger log = Logger.getLogger(GrapiasCtrl.class);
    
    @Override
    @RequestMapping(value="/enter", method=RequestMethod.POST)
    public MenuItems enter(@RequestBody MenuItem menuItem) {
        log.info("Enter on object " + menuItem);
        
        menuItemBuilder.setUrl(getUrl());
        menuItemBuilder.build(menuItem, getMappingFile());
        
        startProcess(menuItem);
        
        try {
            return menuItemBuilder.enter(menuItem);
        } catch (MenuItemNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrapiasCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    @RequestMapping(value="/back", method=RequestMethod.POST)
    public MenuItems back(@RequestBody MenuItem menuItem) {
        log.info("back on object " + menuItem);
        try {
            return menuItemBuilder.back(menuItem);
        } catch (MenuItemNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrapiasCtrl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                return enter(menuItemBuilder.getMenuItem(1));
            } catch (MenuItemNotFoundException ex1) {
                java.util.logging.Logger.getLogger(GrapiasCtrl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }
    
    public String getUrl() {
        return "http://localhost:8080/grapias";
    }
    
    abstract public File getMappingFile();
    abstract public void startProcess(MenuItem menuItem);
}
