package com.github.rxsling;

import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;

public class StylerValueConverterTest {

    @Test
    public void testInteger() throws IOException {
        assertEquals((Integer)0, StylerValueConverter.integer(parse("0px")));
        assertEquals((Integer)5, StylerValueConverter.integer(parse("5px")));
        assertEquals((Integer)10, StylerValueConverter.integer(parse("10")));
    }
    
    private CSSValue parse(String s) throws IOException {
        InputSource source = new InputSource(new StringReader("property:" + s));
        CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
        CSSStyleDeclaration decl = parser.parseStyleDeclaration(source);
        return decl.getPropertyCSSValue("property");
    }

}
