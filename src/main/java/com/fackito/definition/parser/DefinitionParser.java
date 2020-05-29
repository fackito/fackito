/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * The {@link DefinitionParser} class parses an {@link InputStream}.
 *
 * @author JC Carrillo
 */
@FunctionalInterface
public interface DefinitionParser<T> {

    /**
     * Provides a {@link Map} of definition items.
     *
     * @param inputStream
     * @return
     */
    T parse(InputStream inputStream) throws IOException;
}