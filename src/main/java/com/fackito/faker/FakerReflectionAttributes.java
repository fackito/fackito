/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.faker;

import com.fackito.exceptions.base.FackitoInitializationException;
import com.fackito.exceptions.base.NoFakerFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code FakerReflectionAttributes} class contains the reflection attributes to get a value from {@code Faker}
 * using reflection.
 *
 * @author JC Carrillo
 */
public class FakerReflectionAttributes {

    private final String fieldName;
    private final String methodName;

    private FakerReflectionAttributes(String fieldName, String methodName) {
        this.fieldName = fieldName;
        this.methodName = methodName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMethodName() {
        return methodName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        public static final Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");

        public FakerReflectionAttributes build(String string) {
            final Matcher matcher = pattern.matcher(string);
            if (!matcher.find()) {
                throw new NoFakerFoundException();
            }
            final String fakerString = matcher.group(1);
            final String[] split = fakerString.split("\\.");
            if (split.length != 2) {
                throw new FackitoInitializationException("Invalid Faker attributes");
            }
            final String fieldName = split[0];
            final String methodName = split[1];
            return new FakerReflectionAttributes(fieldName, methodName);
        }
    }
}