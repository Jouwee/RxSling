/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rxsling;

import com.github.rxsling.enums.HAlign;
import javax.swing.Icon;

/**
 * Label factory
 */
public class Labels {
    
    /**
     * Creates a new label
     * 
     * @param text
     * @return Label
     */
    public static Label create(String text) {
        return create().text(text);
    }
    
    /**
     * Creates a new label
     * 
     * @param text
     * @param horizontalAlign
     * @return Label
     */
    public static Label create(String text, HAlign horizontalAlign) {
        return create().text(text).horizontalAlign(horizontalAlign);
    }
    
    /**
     * Creates a new label
     * 
     * @param icon
     * @return Label
     */
    public static Label create(Icon icon) {
        return create().icon(icon);
    }
    
    /**
     * Creates a new label
     * 
     * @return Label
     */
    public static Label create() {
        return new Label();
    }
    
}
