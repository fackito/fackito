/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser.yaml;

import com.fackito.definition.parser.DefinitionMapper;
import com.fackito.definition.parser.DefinitionParser;
import com.fackito.definition.parser.DefinitionProvider;

import java.util.Map;

public class DefinitionYamlProvider<T> implements DefinitionProvider<T> {

    private static final String FILE_EXTENSION = ".yaml";
    private final DefinitionParser<Map<String, Map<String, T>>> parser;
    private final DefinitionMapper<Map<String, Map<String, T>>> mapper;

    private DefinitionYamlProvider() {
        parser = DefinitionYamlParser.of();
        mapper = DefinitionYamlMapper.of(FILE_EXTENSION);
    }

    public static <T> DefinitionProvider<T> of() {
        return new DefinitionYamlProvider<>();
    }

    @Override
    public Map<String, T> getDefinition(Class<T> clazz, String name) {
        Map<String, Map<String, T>> fakes = (Map<String, Map<String, T>>) mapper.map(clazz, this.parser);
        return fakes.get(name);
    }
}