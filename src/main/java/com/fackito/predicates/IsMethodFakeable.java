/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.predicates;

import java.lang.reflect.Method;
import java.util.function.Predicate;

import static java.util.Optional.*;

public class IsMethodFakeable implements Predicate<Method> {

    private IsMethodFakeable() {
    }

    @Override
    public boolean test(Method method) {
        return ofNullable(method)
                .filter(IsMethodGetter.of().and(IsMethodGetClass.of().negate()))
                .isPresent();
    }

    public static Predicate<Method> of() {
        return new IsMethodFakeable();
    }
}