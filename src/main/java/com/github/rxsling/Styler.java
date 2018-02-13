/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rxsling;

import java.io.InputStream;

/**
 * Interface for applying styles into components
 * 
 * @param <T>
 */
public interface Styler<T extends Component> {
    
    /**
     * Applies a style to the component
     * 
     * @param style 
     */
    public void apply(String style); 
    
    /**
     * Loads a stylesheet a style to the component
     * 
     * @param stream 
     */
    public void loadStyleSheet(InputStream stream); 
    
    /**
     * Returns the component to be styled
     * 
     * @return T
     */
    public T getComponent();
    
}
