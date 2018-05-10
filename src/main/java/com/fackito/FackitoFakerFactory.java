/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class FackitoFakerFactory {

    public static final String DEFAULT_FAKE = "default";
    private static final String FILE_EXTENSION = ".yaml";

    public static Map<String, String> getFakeData(Class<?> classToFake, String name) throws IOException {
        String filePath = classToFake.getCanonicalName() + FILE_EXTENSION;
        Yaml yaml = new Yaml();
        InputStream in = classToFake.getClassLoader().getResourceAsStream(filePath);
        Iterable<Object> iterableYaml = yaml.loadAll(in);
        Map<String, Object> fakes = (Map<String, Object>) iterableYaml.iterator().next();
        in.close();
        return (Map) fakes.get(name);
    }
}