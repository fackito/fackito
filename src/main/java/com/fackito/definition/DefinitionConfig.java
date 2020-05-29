/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import com.fackito.definition.parser.DefinitionProvider;
import com.fackito.definition.parser.yaml.DefinitionYamlProvider;

public class DefinitionConfig<T> {

    private DefinitionProvider<T> definitionProvider;

    private DefinitionConfig() {
        definitionProvider = DefinitionYamlProvider.of();
    }

    public static <T> DefinitionConfig<T> of() {
        return new DefinitionConfig<>();
    }

    public DefinitionProvider<T> getDefinitionProvider() {
        return definitionProvider;
    }
}