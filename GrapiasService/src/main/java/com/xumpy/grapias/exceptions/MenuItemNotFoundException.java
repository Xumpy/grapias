/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.exceptions;

/**
 *
 * @author nico
 */
public class MenuItemNotFoundException extends Exception{

    public MenuItemNotFoundException() {
        super("Menu Item not found!");
    }
    
}
