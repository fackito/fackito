/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import io.bloco.faker.Faker;

import java.lang.reflect.*;

/**
 * The class {@code DefinitionItemValueMapper} uses Reflection to map a {@link String}
 * to a {@link Faker} result.
 *
 * @author JC Carrillo
 */
public class DefinitionItemValueMapper {

    private final Faker faker;

    public DefinitionItemValueMapper() {
        faker = new Faker();
    }

    /**
     * Reads a {@link String} to provide a {@link Faker} result.
     *
     * @param string the {@link Faker} definition.
     * @return a {@link Faker} result.
     * @throws DefinitionException
     */
    public Object read(String string) throws DefinitionException {
        DefinitionItemValueFactory definitionItemValueFactory = new DefinitionItemValueFactory();
        try {
            final DefinitionItemValue definitionItemValue = definitionItemValueFactory.create(string);
            if (definitionItemValue == null) {
                return string;
            }
            final Field field = Faker.class.getDeclaredField(definitionItemValue.getFieldName());
            final Object object = field.get(faker);
            final Method method = object.getClass().getMethod(definitionItemValue.getMethodName());
            return method.invoke(object, null);
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new DefinitionException(e.getMessage(), e);
        }
    }
}