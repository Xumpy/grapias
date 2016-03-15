/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.model;

import com.google.common.collect.ListMultimap;
import java.util.Map.Entry;
import org.junit.Test;

/**
 *
 * @author nico
 */
public class MenuItemBuilderTest {
    @Test
    public void testBuildCSV(){
        ListMultimap<Integer, MenuItem> csvMenuItems = MenuItemBuilder.buildCSV("/menuTest.csv");
        
        for(Entry entry: csvMenuItems.entries()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
