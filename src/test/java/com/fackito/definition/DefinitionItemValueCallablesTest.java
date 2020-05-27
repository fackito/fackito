package com.fackito.definition;

import com.fackito.exceptions.base.FackitoInitializationException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class DefinitionItemValueCallablesTest {

    @Test
    public void test_allow_empty_string() throws Exception {
        Object emptyValue = "";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        final Object definitionItemValue = callables.definitionItemValueCallable(emptyValue).call();
        assertThat(definitionItemValue, is(notNullValue()));
        assertThat(definitionItemValue, is(instanceOf(String.class)));
        assertThat(definitionItemValue, equalTo(emptyValue));
    }

    @Test
    public void test_allow_empty_list() throws Exception {
        Object emptyValue = Collections.emptyList();
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        final Object definitionItemValue = callables.definitionItemValueCallable(emptyValue).call();
        assertThat(definitionItemValue, is(notNullValue()));
        assertThat(definitionItemValue, is(instanceOf(Collection.class)));
        assertThat(definitionItemValue, equalTo(emptyValue));
    }

    @Test
    public void test_incomplete_key() throws Exception {
        Object invalidDefinitionItemValue = "${";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        final Object definitionItemValue = callables.definitionItemValueCallable(invalidDefinitionItemValue).call();
        assertThat(definitionItemValue, equalTo(invalidDefinitionItemValue));
    }

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_key_no_value() throws Exception {
        Object definitionItemValue = "${}";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        callables.definitionItemValueCallable(definitionItemValue).call();
    }

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_key_missing_method() throws Exception {
        Object definitionItemValue = "${object}";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        Callable<Object> callable = callables.definitionItemValueCallable(definitionItemValue);
        callable.call();
    }

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_faker() throws Throwable {
        Object definitionItemValue = "${object.method}";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        callables.definitionItemValueCallable(definitionItemValue).call();
    }

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_invalid_method() throws Throwable {
        Object definitionItemValue = "${name.method}";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        callables.definitionItemValueCallable(definitionItemValue).call();
    }

    @Test
    public void test_name_firstName() throws Exception {
        Object firsatName = "${name.firstName}";
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        final Object definitionItemValue = callables.definitionItemValueCallable(firsatName).call();
        assertThat(definitionItemValue, is(notNullValue()));
        assertThat(definitionItemValue, is(instanceOf(String.class)));
        assertThat(definitionItemValue, not(equalTo(firsatName)));
    }

    @Test
    public void test_name_firstName_on_list() throws Exception {
        List<?> list = Arrays.asList("${name.firstName}");
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        final Object definitionItemValue = callables.definitionItemValueCallable(list).call();
        assertThat(definitionItemValue, is(notNullValue()));
        assertThat(definitionItemValue, is(instanceOf(List.class)));
        List<?> definitionItemValueList = (List<?>) definitionItemValue;
        assertThat(definitionItemValueList, hasSize(1));
        assertThat(definitionItemValueList.get(0), not(equalTo(list.get(0))));
    }

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_name_x_on_list() throws Exception {
        List<?> list = Arrays.asList("${name.x}");
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        callables.definitionItemValueCallable(list);
    }
}