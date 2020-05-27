package com.fackito.definition;

import com.fackito.exceptions.base.FackitoInitializationException;
import com.fackito.stubs.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DefinitionTest {

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_definition_invalid_class() {
        Definition.of(new Object().getClass(), "default");
    }

    @Test(expected = FackitoInitializationException.class)
    public void test_invalid_definition() {
        Definition.of(User.class, "invalid");
    }

    @Test
    public void test_default_exists() {
        Definition<User> defaultDefinition = Definition.of(User.class, "default");
        boolean exists = defaultDefinition.exists("name");
        Assert.assertTrue(exists);
    }

    @Test
    public void test_default_not_exists() {
        Definition<User> defaultDefinition = Definition.of(User.class, "default");
        boolean exists = defaultDefinition.exists("invalid");
        Assert.assertFalse(exists);
    }

    @Test
    public void tests_default_gets_value() {
        Definition<User> defaultDefinition = Definition.of(User.class, "default");
        Object value = defaultDefinition.getValue("name");
        Assert.assertNotNull(value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tests_default_gets_value_key_not_found() {
        Definition<User> defaultDefinition = Definition.of(User.class, "default");
        defaultDefinition.getValue("invalid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void tests_default_gets_value_internal_failure() {
        Definition<User> defaultDefinition = Definition.of(User.class, "default");
        defaultDefinition.getValue("invalid");
    }
}