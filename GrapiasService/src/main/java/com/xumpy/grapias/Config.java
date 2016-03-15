/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias;

import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import com.xumpy.grapias.rest.model.MenuItem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author nico
 */
@Component
public class Config {
    @Bean
    public SortedSetMultimap<Integer, MenuItem> allMenuItems(){
        SortedSetMultimap<Integer, MenuItem> allMenuItems = TreeMultimap.create();
        
        return allMenuItems;
    }
}
