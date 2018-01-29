package com.github.rxsling;

import io.reactivex.Observable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * Ranged integer input
 */
public class RangedIntegerInput extends ComposedComponent<RangedIntegerInput> implements DefaultInput<Integer, RangedIntegerInput> {

    /** Default input support */
    private final DefaultInputSupport inputSupport;

    /**
     * Creates the ranged input
     */
    public RangedIntegerInput() {
        super();
        NumericInput valueInput = Inputs.integer();
        this.inputSupport = new DefaultInputSupport(this) {
            @Override
            public Object value() {
                return valueInput.value();
            }

            @Override
            public GenericInput value(Object value) {
                valueInput.value(value);
                return (GenericInput) component;
            }

            @Override
            public Observable valueObservable() {
                return valueInput.valueObservable();
            }

            @Override
            public GenericInput value(Observable value) {
                valueInput.value(value);
                return (GenericInput) component;
            }
        };
        setLayout(new BorderLayout());
        
        setBorder(BorderFactory.createLineBorder(Color.RED));
        
        Panel outOfFocusView = Panels.create();
        outOfFocusView.setLayout(new BorderLayout());
        Panel focusedView = Panels.create();
        focusedView.setLayout(new BorderLayout());

        outOfFocusView.put(Labels.create().text(valueObservable().map((v) -> v.toString())).onClick((evt) -> {
            SwingUtilities.invokeLater(() -> {
                remove(outOfFocusView);
                put(focusedView);
                revalidate();
                repaint();
                findId("input").as(JComponent.class).requestFocus();
            });
        }));

        focusedView.put(Inputs.integer().id("input").value(valueObservable()).subscribeValue((value) -> value((Integer)value)));
        
        focusedView.findId("input").as(JComponent.class).addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                remove(focusedView);
                put(outOfFocusView);
                revalidate();
                repaint();
            }
        });
        
//        add(Buttons.create(), BorderLayout.WEST);
//        put(valueInput);
        put(outOfFocusView);
//        add(Buttons.create(), BorderLayout.EAST);
    }
    
    @Override
    public DefaultInputSupport<Integer, RangedIntegerInput> getDefaultInputSupport() {
        return inputSupport;
    }
    
}
