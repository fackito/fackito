/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.parser;

import com.fackito.definition.util.URLProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * The {@link URLInputStreamProcessor} parses a {@link URL}'s {@link InputStream} using a {@link DefinitionParser}.
 *
 * @author JC Carrillo
 */
public class URLInputStreamProcessor {

    private final URLProvider urlProvider;

    private URLInputStreamProcessor(URLProvider urlProvider) {
        this.urlProvider = urlProvider;
    }

    public static URLInputStreamProcessor of(URLProvider urlProvider) {
        return new URLInputStreamProcessor(urlProvider);
    }

    public Object processInputStream(Class<?> clazz, DefinitionParser<?> parser) {
        URL url = urlProvider.provide(clazz);
        try (InputStream in = url.openStream()) {
            return parser.parse(in);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}