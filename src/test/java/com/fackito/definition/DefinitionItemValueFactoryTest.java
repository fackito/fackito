package com.fackito.definition;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefinitionItemValueFactoryTest {

    @Test
    public void test_allow_empty() throws DefinitionException {
        DefinitionItemValueFactory definitionItemValueFactory = new DefinitionItemValueFactory();
        String emptyValue = "";
        final Object definitionItemValue = definitionItemValueFactory.create(emptyValue);
        assertThat(definitionItemValue, is(nullValue()));
    }

    @Test
    public void test_incomplete_key() throws DefinitionException {
        DefinitionItemValueFactory definitionItemValueFactory = new DefinitionItemValueFactory();
        String invalidDefinitionItemValue = "${";
        final Object definitionItemValue = definitionItemValueFactory.create(invalidDefinitionItemValue);
        assertThat(definitionItemValue, is(nullValue()));
    }

    @Test(expected = DefinitionException.class)
    public void test_invalid_key_no_value() throws DefinitionException {
        DefinitionItemValueFactory definitionItemValueFactory = new DefinitionItemValueFactory();
        String definitionItemValue = "${}";
        definitionItemValueFactory.create(definitionItemValue);
    }

    @Test(expected = DefinitionException.class)
    public void test_invalid_key_missing_method() throws DefinitionException {
        DefinitionItemValueFactory definitionItemValueFactory = new DefinitionItemValueFactory();
        String definitionItemValue = "${object}";
        definitionItemValueFactory.create(definitionItemValue);
    }
}