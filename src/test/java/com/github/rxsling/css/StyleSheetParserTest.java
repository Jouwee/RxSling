package com.github.rxsling.css;

import com.github.rxsling.Inputs;
import com.github.rxsling.TextField;
import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests of the style sheet parser
 */
public class StyleSheetParserTest {
    
    @Test
    public void testIdSelector() throws CSSException, IOException {
        TextField field = Inputs.text().id("myField");
        new StyleSheetParser(new StringReader("#myField { background-color: #FF0000 }"), field).applyAllStyles();
        assertEquals(Color.RED, field.background());
    }
    
}
