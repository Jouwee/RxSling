package com.github.rxsling;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.awt.BorderLayout;

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
