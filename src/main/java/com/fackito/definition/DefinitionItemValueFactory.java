/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import java.util.regex.*;

/**
 * The class {@code DefinitionItemValueFactory} creates a {@link DefinitionItemValue} from a
 * {@link String}.
 *
 * @author JC Carrillo
 */
class DefinitionItemValueFactory {

    public static final Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");

    /**
     * Creates a {@link DefinitionItemValue}.
     *
     * @param string the {@link String} to create the {@link DefinitionItemValueFactory} from.
     * @return a {@link DefinitionItemValueFactory}
     * @throws DefinitionException
     */
    public DefinitionItemValue create(String string) throws DefinitionException {
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            return null;
        }
        string = matcher.group(1);
        final String[] split = string.split("\\.");
        if (split == null || split.length != 2) {
            throw new DefinitionException("Invalid Definition Item Value");
        }
        String fieldName = split[0];
        String methodName = split[1];
        DefinitionItemValue definitionItemValue = new DefinitionItemValue();
        definitionItemValue.setFieldName(fieldName);
        definitionItemValue.setMethodName(methodName);
        return definitionItemValue;
    }
}