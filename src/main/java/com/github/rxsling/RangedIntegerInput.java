package com.github.rxsling;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 * Ranged integer input
 */
public class RangedIntegerInput extends ComposedComponent<RangedIntegerInput> implements GenericRangedIntegerInput<RangedIntegerInput> {

    /** Default input support */
    private final ComposedInputSupport<Integer> inputSupport;
    /** Lower limit */
    private int lowerLimit;
    /** Upper limit */
    private int upperLimit;

    /**
     * Creates the ranged input
     */
    public RangedIntegerInput() {
        super();
        lowerLimit = Integer.MIN_VALUE;
        upperLimit = Integer.MAX_VALUE;
        NumericInput valueInput = Inputs.integer();
        this.inputSupport = new ComposedInputSupport<>(this, valueInput);
        this.inputSupport.setValueFilter((value) -> Math.min(upperLimit, Math.max(lowerLimit, value)));
        layout(new BorderLayout());
        Label label = Labels.create().text(valueObservable().map((val) -> String.valueOf(val)));
        label.onDrag((e) -> {
            value(value() + (e.getPoint().x - e.getPreviousPoint().x));
        });
        label.onClick((evt) -> {
            replaceAllWith(valueInput);
            valueInput.focus();
        });
        valueInput.onFocusLost((evt) -> {
            replaceAllWith(label);
        });
        put(label);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Rectangle b = g.getClip().getBounds();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g3 = (Graphics2D) g2d.create();
        double pct = 0;
        try {
            pct = (double)(value() - lowerLimit) / (double)(upperLimit - lowerLimit);
        } catch (ArithmeticException e) {
        }
        g3.setClip(b.x, b.y, (int) (b.width * pct), b.height);
        g3.setColor(new Color(0x505050));
        g3.fillRoundRect(b.x, b.y, b.width - 1, b.height - 1, 5, 5);
        g3.dispose();
        g2d.setColor(new Color(0x303030));
        g2d.drawRoundRect(b.x, b.y, b.width - 1, b.height - 1, 5, 5);
        g2d.translate(2, 0);
        paintComponents(g2d);
        g2d.dispose();
    }
    
    @Override
    public RangedIntegerInput lowerLimit(int limit) {
        int oldValue = this.lowerLimit;
        this.lowerLimit = limit;
        firePropertyChange("lowerLimit", oldValue, lowerLimit);
        return this;
    }

    @Override
    public RangedIntegerInput lowerLimit(Observable<Integer> limit) {
        limit.subscribe((value) -> {
            lowerLimit(value);
        });
        return this;
    }

    @Override
    public int lowerLimit() {
        return this.lowerLimit;
    }

    @Override
    public Observable<Integer> lowerLimitObservable() {
        Subject<Integer> subject = BehaviorSubject.createDefault(lowerLimit());
        addPropertyChangeListener("lowerLimit", (evt) -> {
            subject.onNext((Integer)evt.getNewValue());
        });
        return subject;
    }

    @Override
    public RangedIntegerInput upperLimit(int limit) {
        int oldValue = this.upperLimit;
        this.upperLimit = limit;
        firePropertyChange("upperLimit", oldValue, upperLimit);
        return this;
    }

    @Override
    public RangedIntegerInput upperLimit(Observable<Integer> limit) {
        limit.subscribe((value) -> {
            upperLimit(value);
        });
        return this;
    }

    @Override
    public int upperLimit() {
        return this.upperLimit;
    }

    @Override
    public Observable<Integer> upperLimitObservable() {
        Subject<Integer> subject = BehaviorSubject.createDefault(upperLimit());
        addPropertyChangeListener("upperLimit", (evt) -> {
            subject.onNext((Integer)evt.getNewValue());
        });
        return subject;
    }
    
    @Override
    public DefaultInputSupport<Integer, RangedIntegerInput> getDefaultInputSupport() {
        return inputSupport;
    }
    
}
