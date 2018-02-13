package com.github.rxsling.css;

/**
 * CSS exception
 */
public class CSSException extends Exception {
    
    /**
     * Creates a new CSS exception
     * 
     * @param text 
     */
    public CSSException(String text) {
        super(text);
    }
    
    /**
     * Creates a new CSS exception
     * 
     * @param cause 
     */
    public CSSException(Throwable cause) {
        super(cause);
    }
    
}
