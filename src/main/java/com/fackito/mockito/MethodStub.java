/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.mockito;

@FunctionalInterface
public interface MethodStub<T> {

    Object invoke(T t);
}