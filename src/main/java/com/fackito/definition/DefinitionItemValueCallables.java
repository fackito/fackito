/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.*;

import static com.fackito.faker.FakerCallables.fakerCallable;

/**
 * The class {@code DefinitionItemValueCallables} provides methods that return {@link Callable} objects
 * that stub definition item values.
 *
 * @author JC Carrillo
 */
public class DefinitionItemValueCallables {

    public static final Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");

    /**
     * Creates fake definition item values.
     *
     * @param string parsable pattern for fake values.
     * @return {@link Callable} definition item value.
     */
    public static Callable<Object> definitionItemValueCallable(String string) {
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            return () -> string;
        }
        String fakerString = matcher.group(1);
        final String[] split = fakerString.split("\\.");
        if (split == null || split.length != 2) {
            throw new IllegalArgumentException("Invalid Definition Item Value");
        }
        String fieldName = split[0];
        String methodName = split[1];
        return fakerCallable(fieldName, methodName);
    }

    /**
     * Agnostic {@link Callable}.
     * Creates fake definition item values for {@link String} and {@link List} objects.
     *
     * @param object parsable pattern for fake values.
     * @return {@link Callable} definition item value.
     */
    public static Callable<Object> definitionItemValueCallable(Object object) {
        if (object instanceof String) {
            return definitionItemValueCallable((String) object);
        } else if (object instanceof List) {
            List<Object> list = (List<Object>) object;
            for (int x = 0; x < list.size(); x++) {
                try {
                    list.set(x, definitionItemValueCallable((String) list.get(x)).call());
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }
            return () -> list;
        } else {
            return () -> object;
        }
    }
}