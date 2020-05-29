/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.predicates;

import java.lang.reflect.Method;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;

public class IsMethodGetter implements Predicate<Method> {

    private IsMethodGetter() {
    }

    @Override
    public boolean test(Method method) {
        return ofNullable(method)
                .map(Method::getName)
                .filter(name -> name.startsWith("get"))
                .isPresent();
    }

    public static Predicate<Method> of() {
        return new IsMethodGetter();
    }
}