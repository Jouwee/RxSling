/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rxsling;

import javax.swing.JLabel;

/**
 * Label
 */
public class Label extends JLabel implements DefaultLabel<Label> {

    /** Support to default labels */
    private DefaultLabelSupport defaultComponentSupport;

    /**
     * Creates a new label
     */
    public Label() {
        super();
        setOpaque(false);
    }

    @Override
    public DefaultLabelSupport getDefaultLabelSupport() {
        if (defaultComponentSupport == null) {
            defaultComponentSupport = new DefaultLabelSupport(this);
        }
        return defaultComponentSupport;
    }

}