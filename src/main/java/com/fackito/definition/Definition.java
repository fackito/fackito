/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import java.util.*;
import java.util.concurrent.Callable;

import static com.fackito.definition.DefinitionItemValueCallables.definitionItemValueCallable;
import static java.util.Map.Entry;

/**
 * The class {@code Definition} is a representation of a single definition.
 *
 * @author JC Carrillo
 */
public class Definition {

    public static final String DEFINITION_NAME_DEFAULT = "default";
    private final DefinitionParser definitionParser;
    private final Map<String, Callable<Object>> definitionItems = new HashMap();

    public Definition(Class<?> classToFake, String definitionName) {
        definitionParser = new DefinitionYamlParser();
        initialize(classToFake, definitionName);
    }

    /**
     * Initializes this definition by parsing the YAML definition file and creating key value pairs for
     * each definition item.
     *
     * @param classToFake
     * @param definitionName
     */
    private void initialize(Class<?> classToFake, String definitionName) {
        final Map<String, Object> definition = definitionParser.getDefinition(classToFake, definitionName);
        for (Entry<String, Object> entry : definition.entrySet()) {
            final String key = entry.getKey();
            final Callable<Object> value = definitionItemValueCallable(entry.getValue());
            definitionItems.put(key, value);
        }
    }

    /**
     * Returns <tt>true</tt> if a mapping for the specified key exists.
     *
     * @param key the definition item name
     * @return
     */
    public boolean exists(String key) {
        return definitionItems.containsKey(key);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this no mapping for the key exists.
     *
     * @param key the definition item name
     * @return the value of the definition item
     */
    public Object getValue(String key) {
        try {
            return definitionItems.get(key).call();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}