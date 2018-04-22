/*
 * PrototipoInterface
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.github.rxsling;

import com.github.rxsling.events.DragEvent;
import io.reactivex.Observable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * Interface for a generic component
 *
 * @param <T>
 */
public interface Component<T extends Component> {

    /**
     * Sets the components ID
     *
     * @param id
     * @return T
     */
    public T id(String id);

    /**
     * Returns the components ID
     *
     * @return id
     */
    public String id();

    /**
     * Sets if the component is enabled
     *
     * @param enabled
     * @return T
     */
    public T enabled(boolean enabled);

    /**
     * Sets if the component is enabled
     *
     * @param enabled
     * @return T
     */
    public T enabled(Observable<Boolean> enabled);

    /**
     * Returns if the component is enabled
     *
     * @return boolean
     */
    public boolean enabled();

    /**
     * Returns an observable of the component enabled status
     *
     * @return Observable
     */
    public Observable<Boolean> enabledObservable();

    /**
     * Sets the components hint
     *
     * @param hint
     * @return T
     */
    public T hint(String hint);

    /**
     * Sets the components hint
     *
     * @param hint
     * @return T
     */
    public T hint(Observable<String> hint);

    /**
     * Returns the components hint
     *
     * @return String
     */
    public String hint();

    /**
     * Returns an observable of the component hint
     *
     * @return Observable
     */
    public Observable<String> hintObservable();
    

    /**
     * Sets if the component is focusable
     *
     * @param focusable
     * @return T
     */
    public T focusable(boolean focusable);

    /**
     * Sets if the component is focusable
     *
     * @param focusable
     * @return T
     */
    public T focusable(Observable<Boolean> focusable);

    /**
     * Returns if the component is focusable
     *
     * @return boolean
     */
    public boolean focusable();

    /**
     * Returns an observable of the component focusable status
     *
     * @return Observable
     */
    public Observable<Boolean> focusableObservable();

    /**
     * Sets the background of the component
     *
     * @param background
     * @return T
     */
    public T background(Color background);

    /**
     * Sets the background of the component
     *
     * @param background
     * @return T
     */
    public T background(Observable<Color> background);

    /**
     * Returns the background of the component
     *
     * @return boolean
     */
    public Color background();

    /**
     * Returns an observable of the background of the component
     *
     * @return Observable
     */
    public Observable<Color> backgroundObservable();

    /**
     * Returns the preferred width
     * 
     * @return preferredWidth
     */
    public default int preferredWidth() {        
        return preferredSize().width;
    }

    /**
     * Sets the preferred width
     * 
     * @param width
     * @return T
     */
    public default T preferredWidth(int width) {
        return preferredSize(width, preferredHeight());
    }

    /**
     * Returns the preferred height
     * 
     * @return preferredWidth
     */
    public default int preferredHeight() {        
        return preferredSize().width;
    }

    /**
     * Sets the preferred height
     * 
     * @param height
     * @return T
     */
    public default T preferredHeight(int height) {
        return preferredSize(preferredWidth(), height);
    }
    
    /**
     * Sets the preferred size of the component
     *
     * @param width
     * @param height
     * @return T
     */
    public default T preferredSize(int width, int height) {
        return preferredSize(new Dimension(width, height));
    }

    /**
     * Sets the preferred size of the component
     *
     * @param size
     * @return T
     */
    public T preferredSize(Dimension size);

    /**
     * Sets the preferred size of the component
     *
     * @param size
     * @return T
     */
    public T preferredSize(Observable<Dimension> size);

    /**
     * Returns the preferred size of the component
     *
     * @return Dimension
     */
    public Dimension preferredSize();

    /**
     * Returns an observable of the preferred size of the component
     *
     * @return Dimension
     */
    public Observable<Dimension> preferredSizeObservable();
    
    /**
     * Sets the focus on this component, if focusable
     * 
     * @return T
     */
    public T focus();
        
    /**
     * Consumes click events
     * 
     * @param consumer 
     * @return T
     */
    public T onClick(Consumer<MouseEvent> consumer);
        
    /**
     * Consumes focus lost events
     * 
     * @param consumer 
     * @return T
     */
    public T onFocusLost(Consumer<FocusEvent> consumer);
        
    /**
     * Consumes drag start events
     * 
     * @param consumer 
     * @return T
     */
    public T onDragStart(Consumer<DragEvent> consumer);
    
    /**
     * Consumes drag events
     * 
     * @param consumer 
     * @return T
     */
    public T onDrag(Consumer<DragEvent> consumer);
    
    /**
     * Consumes drag release events
     * 
     * @param consumer 
     * @return T
     */
    public T onDragRelease(Consumer<DragEvent> consumer);
    
    /**
     * Casts this component as another type
     * 
     * @param <K>
     * @param type
     * @return K
     */
    public <K> K as(Class<K> type);
    
    /**
     * Style the component using CSS
     * 
     * @param style
     * @return T
     */
    public T style(String style);
    
    /** 
     * Applies a Style sheet to this component and its children
     * 
     * @param styleSheetStream
     */
    public T styleSheet(InputStream styleSheetStream);

}
