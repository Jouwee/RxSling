package com.github.rxsling;

import io.reactivex.Observable;
import java.util.function.Function;

/**
 * Composed input support
 * 
 * @param <V>
 */
public class ComposedInputSupport<V> extends DefaultInputSupport {

    /** Inner input */
    private final GenericInput innerInput;
    /** Filter for values */
    private Function<V, V> valueFilter;

    /**
     * Creates a new composed input support
     * 
     * @param outterInput
     * @param innerInput 
     */
    public ComposedInputSupport(DefaultInput outterInput, GenericInput innerInput) {
        super(outterInput);
        this.innerInput = innerInput;
        this.valueFilter = (value) -> value;
    }
    
    @Override
    public Object value() {
        return innerInput.value();
    }

    @Override
    public GenericInput value(Object value) {
        innerInput.value(valueFilter.apply((V) value));
        return (GenericInput) component;
    }

    @Override
    public Observable valueObservable() {
        return innerInput.valueObservable();
    }

    @Override
    public GenericInput value(Observable value) {
        innerInput.value(value);
        return (GenericInput) component;
    }

    /**
     * Sets a filter function for the value
     * 
     * @param valueFilter 
     */
    public void setValueFilter(Function<V, V> valueFilter) {
        this.valueFilter = valueFilter;
    }

}
