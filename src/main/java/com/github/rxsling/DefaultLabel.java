/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rxsling;

import com.github.rxsling.enums.HAlign;
import io.reactivex.Observable;
import javax.swing.Icon;

/**
 * Default label
 * 
 * @param <T>
 */
public interface DefaultLabel<T extends DefaultLabel> extends DefaultComponent<T>, GenericLabel<T> {

    @Override
    public default T text(String selected) {
        return getDefaultLabelSupport().text(selected);
    }

    @Override
    public default T text(Observable selected) {
        return (T) getDefaultLabelSupport().text(selected);
    }

    @Override
    public default String text() {
        return getDefaultLabelSupport().text();
    }

    @Override
    public default Observable textObservable() {
        return getDefaultLabelSupport().textObservable();
    }
    
    @Override
    public default T icon(Icon icon) {
        return getDefaultLabelSupport().icon(icon);
    }

    @Override
    public default T icon(Observable icon) {
        return (T) getDefaultLabelSupport().icon(icon);
    }

    @Override
    public default Icon icon() {
        return getDefaultLabelSupport().icon();
    }

    @Override
    public default Observable iconObservable() {
        return getDefaultLabelSupport().iconObservable();
    }
    
    @Override
    public default T horizontalAlign(HAlign align) {
        return getDefaultLabelSupport().horizontalAlign(align);
    }

    @Override
    public default T horizontalAlign(Observable align) {
        return (T) getDefaultLabelSupport().horizontalAlign(align);
    }

    @Override
    public default HAlign horizontalAlign() {
        return getDefaultLabelSupport().horizontalAlign();
    }

    @Override
    public default Observable horizontalAlignObservable() {
        return getDefaultLabelSupport().horizontalAlignObservable();
    }

    @Override
    public default DefaultComponentSupport<T> getDefaultComponentSupport() {
        return (DefaultComponentSupport<T>) getDefaultLabelSupport();
    }

    /**
     * Returns the checkbox support
     *
     * @return DefaultLabelSupport
     */
    public DefaultLabelSupport<T> getDefaultLabelSupport();
    
}
