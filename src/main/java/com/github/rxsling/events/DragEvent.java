package com.github.rxsling.events;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Drag event
 */
public class DragEvent {
    
    /** Returns the point where the event occured */
    private final Point point;
    /** Previous point of the drag */
    private final Point previousPoint;
    
    /**
     * Creates a new drag event
     * 
     * @param awtEvent 
     */
    public DragEvent(MouseEvent awtEvent) {
        this(awtEvent, null);
    }
    
    /**
     * Creates a new drag event
     * 
     * @param awtEvent 
     * @param lastMouseEvent 
     */
    public DragEvent(MouseEvent awtEvent, MouseEvent lastMouseEvent) {
        this.point = awtEvent.getPoint();
        if (lastMouseEvent != null) {
            previousPoint = lastMouseEvent.getPoint();
        } else {
            previousPoint = point;
        }
    }

    /**
     * Returns the point in the component where the event happened
     * 
     * @return Point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Returns the previous point in the component where the event happened
     * 
     * @return Point
     */
    public Point getPreviousPoint() {
        return previousPoint;
    }
    
}
