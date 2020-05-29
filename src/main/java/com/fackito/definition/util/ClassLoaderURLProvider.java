/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.util;

import com.fackito.exceptions.base.FackitoInitializationException;

import java.net.URL;

public class ClassLoaderURLProvider implements URLProvider {

    private final String fileExtension;

    private ClassLoaderURLProvider(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public static ClassLoaderURLProvider of(String fileExtension) {
        return new ClassLoaderURLProvider(fileExtension);
    }

    @Override
    public URL provide(Class<?> c) {
        final ClassLoader classLoader = c.getClassLoader();
        if (classLoader == null) {
            throw new FackitoInitializationException("Invalid class: " + c);
        }
        String canonicalName = c.getCanonicalName();
        final String name = canonicalName + fileExtension;
        URL url = classLoader.getResource(name);
        if (url == null) {
            throw new FackitoInitializationException("Invalid resource: " + c.getName());
        }
        return url;
    }
}