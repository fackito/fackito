/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.faker;

import io.bloco.faker.Faker;

import java.lang.reflect.*;
import java.util.concurrent.Callable;

/**
 * The class {@code FakerCallables} provides methods that return {@link Callable} objects
 * that use reflection to stub {@link Faker} values.
 *
 * @author JC Carrillo
 */
public class FakerCallables {

    /**
     * Uses reflection to stub {@link Faker} values.
     *
     * @param fieldName  {@link Faker} field name
     * @param methodName {@link Faker} method name
     * @return
     */
    public static Callable<Object> fakerCallable(String fieldName, String methodName) {
        return () -> {
            try {
                final Field field = Faker.class.getDeclaredField(fieldName);
                final Object object = field.get(new Faker());
                final Method method = object.getClass().getMethod(methodName);
                return method.invoke(object, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        };
    }
}