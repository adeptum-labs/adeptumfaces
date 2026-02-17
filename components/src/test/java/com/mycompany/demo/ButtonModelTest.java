package com.mycompany.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ButtonModelTest {
    @Test
    void testGetLabel() {

    }

    @Test
    void testIsDisabled() {

    }
    
    @Test
    void buttonModelStoresStateCorrectly() {
        ButtonModel model = new ButtonModel("Save", true);

        assertEquals("Save", model.getLabel());
        assertTrue(model.isDisabled());
    }
}