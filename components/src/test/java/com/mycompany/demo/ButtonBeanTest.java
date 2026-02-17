package com.mycompany.demo;

import com.adeptum.adeptumfaces.ButtonBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ButtonBeanTest {
    @Test
    void testGetButtonModel() {
        
    }

    @Test
    void testOnClick() {

    }
    
    @Test
    void buttonBeanCreatesEnabledButton() {
        ButtonBean bean = new ButtonBean();

        assertEquals("Click me", bean.getButtonModel().getLabel());
        assertFalse(bean.getButtonModel().isDisabled());
    }
}
