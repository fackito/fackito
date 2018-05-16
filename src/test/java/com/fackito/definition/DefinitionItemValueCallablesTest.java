package com.fackito.definition;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DefinitionItemValueCallablesTest {

    @Test
    public void test_allow_empty() throws Exception {
        String emptyValue = "";
        final Object definitionItemValue = DefinitionItemValueCallables.definitionItemValueCallable(emptyValue).call();
        assertThat(definitionItemValue, is(notNullValue()));
        assertThat(definitionItemValue, is(instanceOf(String.class)));
        assertThat(definitionItemValue, equalTo(emptyValue));
    }

    @Test
    public void test_incomplete_key() throws Exception {
        String invalidDefinitionItemValue = "${";
        final Object definitionItemValue = DefinitionItemValueCallables.definitionItemValueCallable(invalidDefinitionItemValue).call();
        assertThat(definitionItemValue, equalTo(invalidDefinitionItemValue));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_key_no_value() throws Exception {
        String definitionItemValue = "${}";
        DefinitionItemValueCallables.definitionItemValueCallable(definitionItemValue).call();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_key_missing_method() throws Exception {
        String definitionItemValue = "${object}";
        DefinitionItemValueCallables.definitionItemValueCallable(definitionItemValue).call();
    }

    @Test(expected = NoSuchFieldException.class)
    public void test_invalid_faker() throws Throwable {
        try {
            String definitionItemValue = "${object.method}";
            DefinitionItemValueCallables.definitionItemValueCallable(definitionItemValue).call();
        } catch (IllegalArgumentException e) {
            throw e.getCause();
        }
    }

    @Test(expected = NoSuchMethodException.class)
    public void test_invalid_invalid_method() throws Throwable {
        try {
            String definitionItemValue = "${name.method}";
            DefinitionItemValueCallables.definitionItemValueCallable(definitionItemValue).call();
        } catch (IllegalArgumentException e) {
            throw e.getCause();
        }
    }
}