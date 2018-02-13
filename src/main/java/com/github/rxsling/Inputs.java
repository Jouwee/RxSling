package com.github.rxsling;

/**
 * Factory for Input types
 */
public class Inputs {

    /**
     * Private constructor
     */
    private Inputs() {
    }
    
    /**
     * Creates a new TextField
     * 
     * @return TextField
     */
    public static TextField text() {
        return new TextField();
    }
    
    /**
     * Creates a new Integer input
     * 
     * @return Numeric Input
     */
    public static NumericInput<Integer> integer() {
        return new NumericInput<>();
    }
    
    /**
     * Create a new input for ranged inputs
     * 
     * @return RangedIntegerInput
     */
    public static RangedIntegerInput rangedInteger() {
        return new RangedIntegerInput();
    }
    
    /**
     * Create a new input for ranged inputs
     * 
     * @param lowerLimit
     * @param upperLimit
     * @return RangedIntegerInput
     */
    public static RangedIntegerInput rangedInteger(int lowerLimit, int upperLimit) {
        return rangedInteger().upperLimit(upperLimit).lowerLimit(upperLimit);
    }
    
}
