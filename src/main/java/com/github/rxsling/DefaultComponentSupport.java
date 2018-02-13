/*
 * TesteInterface
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package com.github.rxsling;

import com.github.rxsling.events.DragEvent;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * Support for default components
 *
 * @param <T>
 */
public class DefaultComponentSupport<T extends DefaultComponent> implements Component<T> {

    /** Component */
    protected final T component;
    /** Support for drag events */
    private final DragEventSupport dragEventSupport;
    /** Components ID */
    private String id;

    /**
     * Creates the component support
     *
     * @param component
     */
    public DefaultComponentSupport(T component) {
        this.component = component;
        dragEventSupport = new DragEventSupport();
        swingComponent().addMouseListener(dragEventSupport);
        swingComponent().addMouseMotionListener(dragEventSupport);
    }

    @Override
    public T id(String id) {
        this.id = id;
        return component;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public T enabled(boolean enabled) {
        swingComponent().setEnabled(enabled);
        return component;
    }

    @Override
    public T enabled(Observable<Boolean> enabled) {
        enabled.subscribe((bool) -> {
            swingComponent().setEnabled(bool);
        });
        return component;
    }

    @Override
    public boolean enabled() {
        return swingComponent().isEnabled();
    }

    @Override
    public Observable<Boolean> enabledObservable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public T focusable(boolean focusable) {
        swingComponent().setFocusable(focusable);
        return component;
    }

    @Override
    public T focusable(Observable<Boolean> focusable) {
        focusable.subscribe((bool) -> {
            enabled(bool);
        });
        return component;
    }

    @Override
    public boolean focusable() {
        return swingComponent().isFocusable();
    }

    @Override
    public Observable<Boolean> focusableObservable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T background(Color background) {
        swingComponent().setBackground(background);
        return component;
    }

    @Override
    public T background(Observable<Color> background) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color background() {
        return swingComponent().getBackground();
    }

    @Override
    public Observable<Color> backgroundObservable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public T preferredSize(Dimension preferredSize) {
        swingComponent().setPreferredSize(preferredSize);
        return component;
    }

    @Override
    public T preferredSize(Observable<Dimension> preferredSize) {
        preferredSize.subscribe((size) -> {
            preferredSize(size);
        });
        return component;
    }

    @Override
    public Dimension preferredSize() {
        return swingComponent().getPreferredSize();
    }

    @Override
    public Observable<Dimension> preferredSizeObservable() {
        Subject<Dimension> subject = BehaviorSubject.createDefault(preferredSize());
        swingComponent().addPropertyChangeListener("preferredSize", (evt) -> {
            subject.onNext((Dimension)evt.getNewValue());
        });
        return subject;
    }

    @Override
    public T focus() {
        SwingUtilities.invokeLater(() -> swingComponent().requestFocus());
        return component;
    }
    
    @Override
    public T onClick(Consumer<MouseEvent> consumer) {
        swingComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                consumer.accept(e);
            }
        });
        return component;
    }
    
    @Override
    public T onFocusLost(Consumer<FocusEvent> consumer) {
        swingComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                consumer.accept(e);
            }
        });
        return component;
    }
    
    @Override
    public T onDragStart(Consumer<DragEvent> consumer) {
        dragEventSupport.startConsumers.add(consumer);
        return component;
    }
    
    @Override
    public T onDrag(Consumer<DragEvent> consumer) {
        dragEventSupport.dragConsumers.add(consumer);
        return component;
    }
    
    @Override
    public T onDragRelease(Consumer<DragEvent> consumer) {
        dragEventSupport.releaseConsumers.add(consumer);
        return component;
    }

    @Override
    public <K> K as(Class<K> type) {
        throw new UnsupportedOperationException("Not handled by support");
    }

    @Override
    public T style(String style) {
        component.styler().apply(style);
        return component;
    }

    @Override
    public T styleSheet(InputStream sheet) {
        component.styler().loadStyleSheet(sheet);
        return component;
    }
    
    /**
     * Returns the swing component
     *
     * @return JComponent
     */
    protected JComponent swingComponent() {
        return (JComponent) component;
    }
    
    /**
     * Class that bridges AWT mouse events and RxSling Drag events
     */
    private class DragEventSupport implements MouseListener, MouseMotionListener {

        /** Consumers of start events */
        private final List<Consumer<DragEvent>> startConsumers;
        /** Consumers of drag events */
        private final List<Consumer<DragEvent>> dragConsumers;
        /** Consumers of release events */
        private final List<Consumer<DragEvent>> releaseConsumers;
        /** Last AWT evenet */
        private MouseEvent lastEvent = null;

        /**
         * Creates the bridge
         */
        public DragEventSupport() {
            this.startConsumers = new ArrayList<>();
            this.dragConsumers = new ArrayList<>();
            this.releaseConsumers = new ArrayList<>();
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (Consumer<DragEvent> consumer : startConsumers) {
                consumer.accept(new DragEvent(e));
            }
            lastEvent = e;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for (Consumer<DragEvent> consumer : releaseConsumers) {
                consumer.accept(new DragEvent(e, lastEvent));
            }
            lastEvent = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            for (Consumer<DragEvent> consumer : dragConsumers) {
                consumer.accept(new DragEvent(e, lastEvent));
            }
            lastEvent = e;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    
    }

}
