/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.model;

import java.util.SortedSet;

/**
 *
 * @author nico
 */
public class MenuItems {
    private SortedSet<MenuItem> menuItems;

    public SortedSet<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(SortedSet<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
