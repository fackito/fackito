/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.mockito;

import com.fackito.definition.Definition;
import com.fackito.predicates.IsMethodFakeable;

import java.lang.reflect.*;
import java.util.function.Predicate;

import static java.lang.Character.*;
import static java.util.Arrays.*;
import static java.util.Optional.*;

/**
 * The class {@code MockitoStubber} stubs a {@link org.mockito.Mockito} mock with definition items.
 *
 * @author JC Carrillo
 */
public class MockitoStubber {

    private static final Predicate<Method> IS_METHOD_FAKEABLE = IsMethodFakeable.of();

    private MockitoStubber() {
    }

    public static <T> T stub(final T mock, final Method[] methods, final Definition<T> definition) {
        stream(methods)
                .filter(IS_METHOD_FAKEABLE)
                .forEach(method ->
                        of(method.getName())
                                .map(name -> name.replace("get", ""))
                                .map(name -> toLowerCase(name.charAt(0)) + name.substring(1))
                                .filter(definition::exists)
                                .map(definition::getValue)
                                .map(value -> WhenStub.newBuilder().build(value, mock, MethodStubBuilder.newBuilder().build(method)))
                                .ifPresent(WhenStub::stub));
        return mock;
    }
}