/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

import io.bloco.faker.Faker;

import java.lang.reflect.*;
import java.util.regex.Pattern;

public class FackitoFaker {

    private static final Faker faker = new Faker();

    public static Object getFake(Object fieldMethodValue) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String fieldMethod = (String) fieldMethodValue;
        if (fieldMethod.startsWith("${")) {
            Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
            java.util.regex.Matcher matcher = pattern.matcher(fieldMethod);
            matcher.find();
            fieldMethod = matcher.group(1);
            final String[] split = fieldMethod.split("\\.");
            String fieldName = split[0];
            String methodName = split[1];
            final Field field = Faker.class.getDeclaredField(fieldName);
            final Object object = field.get(faker);
            final Method method = object.getClass().getMethod(methodName);
            fieldMethodValue = method.invoke(object, null);
        }
        return fieldMethodValue;
    }
}