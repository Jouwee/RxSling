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
        invokeOnEDTAndRevalidate(() -> getSwingSelf().setLayout(layout));
    }
    
    @Override
    public default void put(Component component) {
        invokeOnEDTAndRevalidate(() -> 
                getSwingSelf().add((java.awt.Component) component));
    }

    @Override
    public default void put(Component component, Object layoutConstraints) {
        invokeOnEDTAndRevalidate(() -> 
                getSwingSelf().add((java.awt.Component) component, layoutConstraints));
    }

    @Override
    public default void clear() {
        invokeOnEDTAndRevalidate(() -> getSwingSelf().removeAll());
    }
    
    /**
     * Invokes the runnable on EDT and then revalidates
     * 
     * @param r 
     */
    public default void invokeOnEDTAndRevalidate(Runnable r) {
        SwingUtilities.invokeLater(() -> {
            r.run();
            getSwingSelf().revalidate();
            getSwingSelf().repaint();
        });
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
