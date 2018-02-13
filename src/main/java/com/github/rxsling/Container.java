/*
 * TesteInterface
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.github.rxsling;

import java.awt.LayoutManager;

/**
 * Container
 * 
 * @param <T>
 */
public interface Container<T extends Container> extends Component<T> {

    /**
     * Changes the layout
     * 
     * @param layout 
     */
    public void layout(LayoutManager layout);
    
    /**
     * Adds a component
     *
     * @param component
     */
    public void put(Component component);

    /**
     * Adds a component
     *
     * @param component
     * @param layoutConstraints
     */
    public void put(Component component, Object layoutConstraints);

        
    /**
     * Removes all components
     */
    public void clear();
    
        
    /**
     * Replaces all components with the one specified
     *
     * @param component
     */
    public default void replaceAllWith(Component component) {
        clear();
        put(component);
    }
    
    /**
     * Returns a component by it's id
     *
     * @param id
     * @return Component
     */
    public Component findId(String id);

}
