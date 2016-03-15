/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xumpy.grapias.rest.model;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author nico
 */
public class MenuItem implements Comparable{
    private Integer id;
    private String description;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString(){
        return "id: " + this.id + ", description: " + this.description + ", url: " + this.url;
    }
    
    @Override
    public boolean equals(Object obj) {
        MenuItem menu = (MenuItem)obj;
        
        if(menu.getId().equals(id) && menu.getDescription().equals(description) && menu.getUrl().equals(url)){
            return true;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            append(id).
            append(description).
            append(url).
            toHashCode();
    }

    @Override
    public int compareTo(Object o) {
        MenuItem compareMenuItem = (MenuItem) o;
        return this.id > compareMenuItem.getId() ? 1 : 0;
    }
}
