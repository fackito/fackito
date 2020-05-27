/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser.yaml;

import com.fackito.definition.parser.DefinitionMapper;
import com.fackito.definition.parser.DefinitionParser;
import com.fackito.definition.parser.URLInputStreamProcessor;
import com.fackito.definition.util.ClassLoaderURLProvider;
import com.fackito.definition.util.URLProvider;

import java.util.Map;

public class DefinitionYamlMapper<T> implements DefinitionMapper<Map<String, Map<String, T>>> {

    private final URLInputStreamProcessor uRLInputStreamProcessor;

    private DefinitionYamlMapper(String fileExtension) {
        URLProvider urlProvider = ClassLoaderURLProvider.of(fileExtension);
        uRLInputStreamProcessor = URLInputStreamProcessor.of(urlProvider);
    }

    public static <T> DefinitionMapper<Map<String, Map<String, T>>> of(String fileExtension) {
        return new DefinitionYamlMapper<>(fileExtension);
    }

    @Override
    public Object map(Class<?> clazz, DefinitionParser<Map<String, Map<String, T>>> parser) {
        return uRLInputStreamProcessor.processInputStream(clazz, parser);
    }
}