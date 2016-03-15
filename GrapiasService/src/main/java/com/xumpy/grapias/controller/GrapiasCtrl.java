/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.controller;

import com.xumpy.grapias.rest.interfaces.Grapias;
import com.xumpy.grapias.rest.model.MenuItem;
import com.xumpy.grapias.rest.model.MenuItemBuilder;
import com.xumpy.grapias.rest.model.MenuItems;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
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
public class GrapiasCtrl implements Grapias{
    
    private static Logger log = Logger.getLogger(GrapiasCtrl.class);
    
    @Override
    @RequestMapping(value="/enter", method=RequestMethod.POST)
    public MenuItems enter(@RequestBody MenuItem menuItem) {
        log.info("Enter on object " + menuItem);
        
        MenuItemBuilder.build(menuItem);
        
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setId(2);
        menuItem1.setDescription("Test van nico");
        menuItem1.setUrl("http://localhost:8080/grapias");
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(3);
        menuItem2.setDescription("Test van nico2");
        menuItem2.setUrl("http://localhost:8080/grapias");
        
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        MenuItems returnMenuItems = new MenuItems();
        returnMenuItems.setMenuItems(menuItems);
        
        return returnMenuItems;
    }

    @Override
    @RequestMapping(value="/back", method=RequestMethod.POST)
    public MenuItems back(@RequestBody MenuItem menuItem) {
        log.info("back on object " + menuItem);
        
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setId(1);
        menuItem1.setDescription("Home van nico");
        menuItem1.setUrl("http://localhost:8080/grapias");
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(4);
        menuItem2.setDescription("Home van nico2");
        menuItem2.setUrl("http://localhost:8080/grapias");
        
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        MenuItems returnMenuItems = new MenuItems();
        returnMenuItems.setMenuItems(menuItems);
        
        return returnMenuItems;
    }
}
