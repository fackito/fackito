package com.fackito.faker;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;

public class FakerCallableImplTest {

    @Test
    public void builds() {
        FakerReflectionAttributes attributes = FakerReflectionAttributes.newBuilder().build("${name.firstName}");
        Callable<Object> callable = FakerCallableImpl.newBuilder().build(attributes);
        Assert.assertNotNull(callable);
    }
}