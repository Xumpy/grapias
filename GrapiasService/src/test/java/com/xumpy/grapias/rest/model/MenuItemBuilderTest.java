/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.model;

import com.google.common.collect.Iterables;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import com.xumpy.grapias.exceptions.MenuItemNotFoundException;
import java.io.File;
import java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author nico
 */

@RunWith(MockitoJUnitRunner.class)
public class MenuItemBuilderTest {
    SortedSetMultimap<Integer, MenuItem> allMenuItems 
            = Mockito.mock(SortedSetMultimap.class,AdditionalAnswers.delegatesTo(TreeMultimap.create()));
    
    @InjectMocks MenuItemBuilder menuItemBuilder;
    
    private final MenuItem startMenuItem = new MenuItem();
    
    @Before
    public void setUp(){
        startMenuItem.setId(1);
        startMenuItem.setDescription("Start Menu");
        startMenuItem.setUrl("http://localhost:8080");
        
        menuItemBuilder.build(startMenuItem, new File(MenuItemBuilder.class.getClass().getResource("/menuTest.csv").getFile()));
    }
    
    @Test
    public void testBuildCSV(){
        for(Entry entry: allMenuItems.entries()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    @Test
    public void testEnter() throws MenuItemNotFoundException{
        MenuItems menuItems = menuItemBuilder.enter(startMenuItem);
        
        assertEquals(new Integer(2), Iterables.get(menuItems.getMenuItems(), 0).getId());
        assertEquals(new Integer(6), Iterables.get(menuItems.getMenuItems(), 1).getId());
    }
    
    @Test
    public void testEnter2() throws MenuItemNotFoundException{
        MenuItem menuItemLast = menuItemBuilder.getMenuItem(13);
        MenuItems menuItems = menuItemBuilder.enter(menuItemLast);
        
        assertEquals(new Integer(12), Iterables.get(menuItems.getMenuItems(), 0).getId());
        assertEquals(new Integer(13), Iterables.get(menuItems.getMenuItems(), 1).getId());
    }
    
    @Test
    public void testBack() throws MenuItemNotFoundException{
        MenuItem menuItemPrevious = menuItemBuilder.getMenuItem(12);
        MenuItems menuItems = menuItemBuilder.back(menuItemPrevious);
        
        assertEquals(new Integer(7), Iterables.get(menuItems.getMenuItems(), 0).getId());
        assertEquals(new Integer(8), Iterables.get(menuItems.getMenuItems(), 1).getId());
        assertEquals(new Integer(9), Iterables.get(menuItems.getMenuItems(), 2).getId());
        assertEquals(new Integer(10), Iterables.get(menuItems.getMenuItems(), 3).getId());
        assertEquals(new Integer(11), Iterables.get(menuItems.getMenuItems(), 4).getId());
        
    }
    
    
    @Test
    public void testBack2() throws MenuItemNotFoundException{
        MenuItem menuItem = menuItemBuilder.getMenuItem(5);
        MenuItems menuItems = menuItemBuilder.back(menuItem);
        
        assertEquals(new Integer(2), Iterables.get(menuItems.getMenuItems(), 0).getId());
        assertEquals(new Integer(6), Iterables.get(menuItems.getMenuItems(), 1).getId());
    }

    @Test
    public void testBack3() throws MenuItemNotFoundException{
        MenuItem menuItem = new MenuItem();
        MenuItems menuItems = menuItemBuilder.back(menuItem);
        
        assertEquals(new Integer(2), Iterables.get(menuItems.getMenuItems(), 0).getId());
        assertEquals(new Integer(6), Iterables.get(menuItems.getMenuItems(), 1).getId());
    }
}
