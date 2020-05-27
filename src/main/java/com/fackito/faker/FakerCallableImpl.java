/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.faker;

import com.fackito.exceptions.base.FackitoInitializationException;
import io.bloco.faker.Faker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class FakerCallableImpl implements FakerCallable {

    private final String fieldName;
    private final String methodName;

    private FakerCallableImpl(String fieldName, String methodName) {
        this.fieldName = fieldName;
        this.methodName = methodName;
    }

    @Override
    public Object call() throws FackitoInitializationException {
        try {
            final Field field = Faker.class.getDeclaredField(fieldName);
            final Object object = field.get(new Faker());
            final Method method = object.getClass().getMethod(methodName);
            return method.invoke(object, null);
        } catch (Exception e) {
            throw new FackitoInitializationException(e.getMessage(), e);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        public Callable<Object> build(FakerReflectionAttributes attributes) {
            return new FakerCallableImpl(attributes.getFieldName(), attributes.getMethodName());
        }
    }
}