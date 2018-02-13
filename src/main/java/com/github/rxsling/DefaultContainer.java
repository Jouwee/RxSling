/*
 * TesteInterface
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.github.rxsling;

import java.awt.LayoutManager;
import javax.swing.SwingUtilities;

/**
 * Default container
 * 
 * @param <T>
 */
public interface DefaultContainer<T extends DefaultContainer> extends Container<T> {

    @Override
    public default void layout(LayoutManager layout) {
        getSwingSelf().setLayout(layout);
    }
    
    @Override
    public default void put(Component component) {
        getSwingSelf().add((java.awt.Component) component);
    }

    @Override
    public default void put(Component component, Object layoutConstraints) {
        getSwingSelf().add((java.awt.Component) component, layoutConstraints);
    }

    @Override
    public default void clear() {
        getSwingSelf().removeAll();
        getSwingSelf().revalidate();
        getSwingSelf().repaint();
    }

    @Override
    public default Component<Component> findId(String id) {
        for (java.awt.Component component : getSwingSelf().getComponents()) {
            if (component == null) {
                continue;
            }
            if (component instanceof Component) {
                Component converted = (Component)component;
                if (id.equals(converted.id())) {
                    return converted;
                }
            }
            if (component instanceof DefaultContainer) {
                Component<Component> result = ((DefaultContainer)component).findId(id);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Returns the Swing version of itself
     *
     * @return Container
     */
    public java.awt.Container getSwingSelf();

}
