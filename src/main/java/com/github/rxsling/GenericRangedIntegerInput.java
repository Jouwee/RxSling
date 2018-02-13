package com.github.rxsling;

import io.reactivex.Observable;

/**
 * Generic ranged integer input
 * 
 * @param <T>
 */
public interface GenericRangedIntegerInput<T extends GenericRangedIntegerInput> extends DefaultInput<Integer, T> {

    /**
     * Sets the lower limit
     * 
     * @param limit
     * @return limit
     */
    public T lowerLimit(int limit);
    
    /**
     * Sets the lower limit
     * 
     * @param limit
     * @return T
     */
    public T lowerLimit(Observable<Integer> limit);
    
    /**
     * Returns the lower limit
     * 
     * @return int
     */
    public int lowerLimit();
    
    /**
     * Returns an observable of the lower limit
     * 
     * @return 
     */
    public Observable<Integer> lowerLimitObservable();

    /**
     * Sets the upper limit
     * 
     * @param limit
     * @return limit
     */
    public T upperLimit(int limit);
    
    /**
     * Sets the upper limit
     * 
     * @param limit
     * @return T
     */
    public T upperLimit(Observable<Integer> limit);
    
    /**
     * Returns the upper limit
     * 
     * @return int
     */
    public int upperLimit();
    
    /**
     * Returns an observable of the upper limit
     * 
     * @return 
     */
    public Observable<Integer> upperLimitObservable();
    
}
