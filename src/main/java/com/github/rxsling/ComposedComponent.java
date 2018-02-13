package com.github.rxsling;

import java.awt.Container;
import javax.swing.JComponent;

/**
 * Composed component
 * 
 * @param <T>
 */
public abstract class ComposedComponent<T extends ComposedComponent> extends JComponent implements DefaultComponent<T>, DefaultContainer<T> {

    public ComposedComponent() {
        super();
        setOpaque(false);
    }
    
    @Override
    public Container getSwingSelf() {
        return this;
    }

}
