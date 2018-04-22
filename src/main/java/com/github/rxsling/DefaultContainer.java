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
    public default T layout(LayoutManager layout) {
        getSwingSelf().setLayout(layout);
        return (T) this;
    }
    
    @Override
    public default T put(Component component) {
        getSwingSelf().add((java.awt.Component) component);
        return (T) this;
    }

    @Override
    public default T put(Component component, Object layoutConstraints) {
        getSwingSelf().add((java.awt.Component) component, layoutConstraints);
        return (T) this;
    }

    @Override
    public default T clear() {
        getSwingSelf().removeAll();
        getSwingSelf().revalidate();
        getSwingSelf().repaint();
        return (T) this;
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
