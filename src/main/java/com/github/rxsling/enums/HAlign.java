package com.github.rxsling.enums;

import javax.swing.SwingConstants;

/**
 * Horizontal alignment
 */
public enum HAlign {
    
    LEFT(SwingConstants.LEFT),
    RIGHT(SwingConstants.RIGHT), 
    CENTER(SwingConstants.CENTER);

    /** AWT equivalent alignment */
    private final  int awtEquivalent;

    /**
     * Creates a new enum
     * @param awtEquivalent 
     */
    private HAlign(int awtEquivalent) {
        this.awtEquivalent = awtEquivalent;
    }
    
    /**
     * Returns the enum based on the AWT constant
     * 
     * @param horizontalAlignment
     * @return HAlign
     */
    public static HAlign fromAwt(int horizontalAlignment) {
        for (HAlign value : values()) {
            if (value.getAwt() == horizontalAlignment) {
                return value;
            }
        }
        return null;
    }
    
    /**
     * Returns the equivalent AWT constant
     * 
     * @return int
     */
    public int getAwt() {
        return awtEquivalent;
    }
}
