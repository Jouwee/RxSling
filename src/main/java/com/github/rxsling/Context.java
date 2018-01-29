package com.github.rxsling;

/**
 * GUI Context
 */
public class Context {
    
    /** Default context */
    private static Context defaultContext;
    
    /** 
     * Returns the default context
     * 
     * @return Context
     */
    public static Context getDefault() {
        if (defaultContext == null) {
            defaultContext = new Context();
        }
        return defaultContext;
    }
    
}
