/*
 * TesteInterface
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.github.rxsling;

import java.awt.Container;
import javax.swing.JPanel;

/**
 * Panel
 */
public class Panel extends JPanel implements DefaultComponent<Panel>, DefaultContainer<Panel> {

    /** Suporte to default components */
    private DefaultComponentSupport defaultComponentSupport;

    /**
     * Creates a new panel
     */
    public Panel() {
        super();
        setOpaque(false);
    }
    
    @Override
    public DefaultComponentSupport getDefaultComponentSupport() {
        if (defaultComponentSupport == null) {
            defaultComponentSupport = new DefaultComponentSupport(this);
        }
        return defaultComponentSupport;
    }

    @Override
    public Container getSwingSelf() {
        return this;
    }

}
