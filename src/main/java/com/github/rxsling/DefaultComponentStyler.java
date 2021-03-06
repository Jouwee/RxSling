package com.github.rxsling;

import java.awt.Color;

/**
 * Default component styler
 * 
 * @param <T>
 */
public class DefaultComponentStyler<T extends Component> extends AbstractStyler<T> {

    /**
     * Creates a new default component styler
     * 
     * @param component 
     */
    public DefaultComponentStyler(T component) {
        super(component);
    }

    @Override
    public void setup(RuleMap ruleMap) {
        ruleMap.put("background-color", Color.class, (color) -> {
           getComponent().background(color);
        });
        ruleMap.put("pref-height", Integer.class, (value) -> {
           getComponent().preferredHeight(value);
        });
        ruleMap.put("pref-width", Integer.class, (value) -> {
           getComponent().preferredWidth(value);
        });
    }
    
}
