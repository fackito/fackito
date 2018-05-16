/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import java.util.Map;

/**
 * The {@link DefinitionParser} class provides a {@link Map} of definition items.
 *
 * @author JC Carrillo
 */
public interface DefinitionParser {

    /**
     * Provides a {@link Map} of definition items.
     *
     * @param clazz the class to define
     * @param key   the definition name
     * @return a {@link Map} of definition items.
     */
    Map<String, Object> getDefinition(Class<?> clazz, String key);
}