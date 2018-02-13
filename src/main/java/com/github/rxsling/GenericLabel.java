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
 * Generic label
 * 
 * @param <T>
 */
public interface GenericLabel<T extends GenericLabel> extends Component<T> {
    
    /**
     * Sets the label text
     *
     * @param selected
     * @return T
     */
    public T text(String selected);

    /**
     * Sets the label text
     *
     * @param selected
     * @return T
     */
    public T text(Observable<String> selected);

    /**
     * Returns the label text
     *
     * @return String
     */
    public String text();

    /**
     * Returns an observable of the label text
     *
     * @return Observable
     */
    public Observable<String> textObservable();
    
    /**
     * Sets the icon
     *
     * @param selected
     * @return T
     */
    public T icon(Icon selected);

    /**
     * Sets the icon
     *
     * @param icon
     * @return T
     */
    public T icon(Observable<Icon> icon);

    /**
     * Returns the icon
     *
     * @return String
     */
    public Icon icon();

    /**
     * Returns an observable of the icon
     *
     * @return Observable
     */
    public Observable<Icon> iconObservable();
    
    /**
     * Sets the horizontal alignment
     *
     * @param selected
     * @return T
     */
    public T horizontalAlign(HAlign selected);

    /**
     * Sets the horizontal alignment
     *
     * @param icon
     * @return T
     */
    public T horizontalAlign(Observable<HAlign> icon);

    /**
     * Returns the horizontal alignment
     *
     * @return HAlign
     */
    public HAlign horizontalAlign();

    /**
     * Returns an observable of the horizontal alignment
     *
     * @return Observable
     */
    public Observable<HAlign> horizontalAlignObservable();
    
    
    
}
