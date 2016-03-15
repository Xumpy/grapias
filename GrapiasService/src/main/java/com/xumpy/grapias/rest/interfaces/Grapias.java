/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.interfaces;

import com.xumpy.grapias.rest.model.MenuItem;
import com.xumpy.grapias.rest.model.MenuItems;

/**
 *
 * @author nico
 */
public interface Grapias {
    public MenuItems enter(MenuItem menuItem);
    public MenuItems back(MenuItem menuItem);
}
