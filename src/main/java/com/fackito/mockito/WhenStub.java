/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.mockito;

import static org.mockito.Mockito.when;

class WhenStub<T> {

    private final Object value;
    private final T mock;
    private final MethodStub<T> method;

    private WhenStub(Object value, T mock, MethodStub<T> method) {
        this.value = value;
        this.mock = mock;
        this.method = method;
    }

    public void stub() {
        when(method.invoke(mock)).thenReturn(value);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        public Builder() {
        }

        public <T> WhenStub<T> build(Object value, T mock, MethodStub<T> method) {
            return new WhenStub<>(value, mock, method);
        }
    }
}