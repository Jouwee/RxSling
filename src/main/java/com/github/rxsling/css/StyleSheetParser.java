package com.github.rxsling.css;

import com.github.rxsling.Component;
import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;
import com.steadystate.css.parser.selectors.IdConditionImpl;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.InputSource;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

/**
 * Cascading StyleSheet parser
 */
public class StyleSheetParser {
    
    /** Style reader */
    private final Reader styleReader;
    /** Component to style */
    private final Component component;
    /** Parser */
    private final CSSOMParser parser;
    /** StyleSheet */
    private final CSSStyleSheet sheet;

    /**
     * Creates a new Cascading StyleSheet parser
     * 
     * @param styleStream
     * @param component 
     * @throws java.io.IOException 
     */
    public StyleSheetParser(Reader styleStream, Component component) throws IOException {
        this.styleReader = styleStream;
        this.component = component;
        parser = new CSSOMParser(new SACParserCSS3());
        sheet = parser.parseStyleSheet(new InputSource(styleReader), null, "");
    }
    
    /**
     * Apply all styles to the component
     * 
     * @throws com.github.rxsling.css.CSSException if the stylesheet could not be parser
     */
    public void applyAllStyles() throws CSSException {
        try {
            parseStyleSheet((appliedStyle) -> {
                component.style(appliedStyle);
            });
        } catch (Exception e) {
            throw new CSSException(e);
        }
    }
    
    /**
     * Parse the StyleSheet, calling the consumer for every style that must be applied
     * 
     * @param consumer 
     */
    private void parseStyleSheet(Consumer<String> consumer) throws Exception {
        CSSRuleList list = sheet.getCssRules();
        for (int i = 0; i < list.getLength(); i++) {
            parseRule(list.item(i), consumer);
            
        }
    }

    /**
     * Parses a rule
     * 
     * @param rule
     * @param consumer 
     */
    private void parseRule(CSSRule rule, Consumer<String> consumer) throws Exception {
        if (rule instanceof CSSStyleRule) {
            parseStyleRule((CSSStyleRule) rule, consumer);
            return;
        }
        throw new CSSException(String.format("%1$s is not a supported CSS rule", rule.getClass()));
    }

    /**
     * Parses a StyleRule
     * 
     * @param rule
     * @param consumer 
     */
    private void parseStyleRule(CSSStyleRule rule, Consumer<String> consumer) throws Exception {
        SelectorList selectors = parser.parseSelectors(new InputSource(new StringReader(rule.getSelectorText())));
            for (int j = 0; j < selectors.getLength(); j++) {
                Selector selector = selectors.item(j);

                System.out.println("  " + selector.toString() + " (" + selector.getClass() + ")");
                if (selector instanceof ConditionalSelector) {
                    ConditionalSelector condSelector = (ConditionalSelector) selector;
                    Condition condition = condSelector.getCondition();
                    parseCondition(condition, rule.getStyle(), consumer);
                }
            }
    }

    private void parseCondition(Condition condition, CSSStyleDeclaration rule, Consumer<String> consumer) throws Exception {
        if (condition instanceof IdConditionImpl) {
            IdConditionImpl attributeCondition = (IdConditionImpl) condition;
            if (component.id().equals(attributeCondition.getValue())) {
                consumer.accept(rule.getCssText());
            }
            return;
        }
        if (condition instanceof AttributeCondition) {
            AttributeCondition attributeCondition = (AttributeCondition) condition;
            System.out.println("  cond: " + attributeCondition.getNamespaceURI() + " " + attributeCondition.getValue());
        }
    }
    
}
