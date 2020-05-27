/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser.yaml;

import com.fackito.definition.parser.DefinitionParser;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

class DefinitionYamlParser<T> implements DefinitionParser<Map<String, Map<String, T>>> {

    private final Yaml yaml;

    private DefinitionYamlParser() {
        yaml = new Yaml();
    }

    public static <T> DefinitionParser<Map<String, Map<String, T>>> of() {
        return new DefinitionYamlParser<>();
    }

    @Override
    public Map<String, Map<String, T>> parse(InputStream in) {
        Iterable<Object> iterableYaml = yaml.loadAll(in);
        Iterator<Object> iterator = iterableYaml.iterator();
        return (Map<String, Map<String, T>>) iterator.next();
    }
}