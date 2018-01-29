/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rxsling;

/**
 * Panel factory
 */
public class Panels {
    
    /**
     * Creates a new panel
     * 
     * @return Panel
     */
    public static Panel create() {
        return new Panel();
    }
    
    /**
     * Button group
     * 
     * @return ButtonGroup
     */
    public static ButtonGroup buttonGroup() {
        return new ButtonGroup();
    }
    
}
