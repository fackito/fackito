package com.fackito.faker;

import com.fackito.exceptions.base.FackitoInitializationException;
import com.fackito.exceptions.base.NoFakerFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FakerReflectionAttributesTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void builds_empty() {
        expectedException.expect(NoFakerFoundException.class);
        FakerReflectionAttributes.newBuilder().build("");
    }

    @Test
    public void builds_only_dollarSign() {
        expectedException.expect(NoFakerFoundException.class);
        FakerReflectionAttributes.newBuilder().build("$");
    }

    @Test
    public void builds_only_dollarSign_and_bracket_left() {
        expectedException.expect(NoFakerFoundException.class);
        FakerReflectionAttributes.newBuilder().build("${");
    }

    @Test
    public void builds_missing_content() {
        expectedException.expect(FackitoInitializationException.class);
        expectedException.expectMessage("Invalid Faker attributes");
        FakerReflectionAttributes.newBuilder().build("${}");
    }

    @Test
    public void builds_missing_methodName() {
        expectedException.expect(FackitoInitializationException.class);
        expectedException.expectMessage("Invalid Faker attributes");
        FakerReflectionAttributes.newBuilder().build("${name}");
    }

    @Test
    public void builds_missing_dollarSign() {
        expectedException.expect(NoFakerFoundException.class);
        FakerReflectionAttributes.newBuilder().build("{name.firstName}");
    }

    @Test
    public void builds_missing_bracket_left() {
        expectedException.expect(NoFakerFoundException.class);
        FakerReflectionAttributes.newBuilder().build("$name.firstName}");
    }

    @Test
    public void builds_missing_bracket_right() {
        expectedException.expect(NoFakerFoundException.class);
        FakerReflectionAttributes.newBuilder().build("${name.firstName");
    }

    @Test
    public void builds() {
        FakerReflectionAttributes attributes = FakerReflectionAttributes.newBuilder().build("${name.firstName}");
        Assert.assertEquals("name", attributes.getFieldName());
        Assert.assertEquals("firstName", attributes.getMethodName());
    }
}