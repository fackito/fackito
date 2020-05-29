/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser;

import java.util.Map;

/**
 * The {@link DefinitionProvider} class provides a representation of the provided {@link Class}.
 *
 * @author JC Carrillo
 */
@FunctionalInterface
public interface DefinitionProvider<T> {

    /**
     * Provides a {@link Map} of definition items.
     *
     * @param clazz the class to define
     * @param name  the definition name
     * @return a {@link Map} of definition items.
     */
    Map<String, T> getDefinition(Class<T> clazz, String name);
}