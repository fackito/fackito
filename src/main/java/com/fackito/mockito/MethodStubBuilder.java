/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.mockito;

import com.fackito.exceptions.base.FackitoInitializationException;

import java.lang.reflect.Method;

class MethodStubBuilder<T> {

    private MethodStubBuilder() {
    }

    public static <T> MethodStubBuilder<T> newBuilder() {
        return new MethodStubBuilder<>();
    }

    public MethodStub<T> build(Method method) {
        return t -> {
            try {
                return method.invoke(t);
            } catch (Exception e) {
                throw new FackitoInitializationException(e.getMessage(), e);
            }
        };
    }
}