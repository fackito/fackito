/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import com.fackito.exceptions.base.NoFakerFoundException;
import com.fackito.faker.FakerCallable;
import com.fackito.faker.FakerReflectionAttributes;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static com.fackito.faker.FakerCallableImpl.*;

/**
 * The class {@code DefinitionItemValueCallables} provides methods that return {@link Callable} objects
 * that stub definition item values.
 *
 * @author JC Carrillo
 */
class DefinitionItemValueCallables {

    /**
     * Agnostic {@link Callable}.
     * Creates fake definition item values for {@link String} and {@link List} objects.
     *
     * @param object parsable pattern for fake values.
     * @return {@link Callable} definition item value.
     */
    public Callable<Object> definitionItemValueCallable(Object object) {
        final Callable<Object> callable;
        if (object instanceof String) {
            callable = definitionItemValueCallable((String) object);
        } else {
            final Object objectToReturn;
            if (object instanceof Collection) {
                objectToReturn = ((Collection<Object>) object).stream()
                        .map(obj -> (String) obj)
                        .map(this::definitionItemValueCallable)
                        .map(call -> (FakerCallable) call)
                        .map(FakerCallable::call)
                        .collect(Collectors.toList());
            } else {
                objectToReturn = object;
            }
            callable = () -> objectToReturn;
        }
        return callable;
    }

    /**
     * Creates fake definition item values.
     *
     * @param string parsable pattern for fake values.
     * @return {@link Callable} definition item value.
     */
    private Callable<Object> definitionItemValueCallable(String string) {
        try {
            return newBuilder().build(FakerReflectionAttributes.newBuilder().build(string));
        } catch (NoFakerFoundException e) {
            return (FakerCallable) () -> string;
        }
    }
}