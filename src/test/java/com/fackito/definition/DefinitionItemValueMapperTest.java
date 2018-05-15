package com.fackito.definition;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefinitionItemValueMapperTest {

    @Test
    public void test_allow_empty() throws DefinitionException {
        DefinitionItemValueMapper definitionItemValueMapper = new DefinitionItemValueMapper();
        String emptyValue = "";
        final Object definitionItemValue = definitionItemValueMapper.read(emptyValue);
        assertThat(definitionItemValue, is(notNullValue()));
        assertThat(definitionItemValue, is(instanceOf(String.class)));
        assertThat(definitionItemValue, equalTo(emptyValue));
    }

    @Test(expected = NoSuchFieldException.class)
    public void test_invalid_faker() throws Throwable {
        try {
            DefinitionItemValueMapper definitionItemValueMapper = new DefinitionItemValueMapper();
            String definitionItemValue = "${object.method}";
            definitionItemValueMapper.read(definitionItemValue);
        } catch (DefinitionException e) {
            throw e.getCause();
        }
    }

    @Test(expected = NoSuchMethodException.class)
    public void test_invalid_invalid_method() throws Throwable {
        try {
            DefinitionItemValueMapper definitionItemValueMapper = new DefinitionItemValueMapper();
            String definitionItemValue = "${name.method}";
            definitionItemValueMapper.read(definitionItemValue);
        } catch (DefinitionException e) {
            throw e.getCause();
        }
    }
}