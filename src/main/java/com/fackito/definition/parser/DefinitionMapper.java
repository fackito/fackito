/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser;

/**
 * The {@link DefinitionMapper} class maps a {@link Class} to its definition by utilizing a {@link DefinitionParser}.
 *
 * @author JC Carrillo
 */
@FunctionalInterface
public interface DefinitionMapper<T> {

    Object map(Class<?> clazz, DefinitionParser<T> parser);
}