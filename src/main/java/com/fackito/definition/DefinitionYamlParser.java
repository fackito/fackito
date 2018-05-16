/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * The {@link DefinitionYamlParser} class is a custom YAML {@link DefinitionParser}.
 *
 * @author JC Carrillo
 */
public class DefinitionYamlParser implements DefinitionParser {

    private static final String FILE_EXTENSION = ".yaml";

    @Override
    public Map<String, Object> getDefinition(Class<?> clazz, String key) {
        String filePath = clazz.getCanonicalName() + FILE_EXTENSION;
        Yaml yaml = new Yaml();
        try (InputStream in = clazz.getClassLoader().getResourceAsStream(filePath)) {
            Iterable<Object> iterableYaml = yaml.loadAll(in);
            Map<String, Object> fakes = (Map<String, Object>) iterableYaml.iterator().next();
            return (Map) fakes.get(key);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}