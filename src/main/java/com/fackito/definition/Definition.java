/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

import com.fackito.definition.parser.DefinitionProvider;
import com.fackito.exceptions.base.FackitoInitializationException;

import java.util.*;
import java.util.concurrent.Callable;

import static java.util.Map.Entry;

/**
 * The class {@code Definition} is a representation of a single mock definition.
 *
 * @author JC Carrillo
 */
public class Definition<T> {

    public static final String DEFINITION_NAME_DEFAULT = "default";
    private final DefinitionConfig<T> definitionConfig = DefinitionConfig.of();
    private final Map<String, Callable<Object>> definitionItems = new HashMap<>();

    private Definition(Class<T> classToFake, String definitionName) {
        initialize(classToFake, definitionName);
    }

    public static <T> Definition<T> of(Class<T> classToFake, String definitionName) {
        return new Definition<>(classToFake, definitionName);
    }

    /**
     * Initializes this definition by parsing the YAML definition file and creating key value pairs for
     * each definition item.
     *
     * @param classToFake
     * @param definitionName
     */
    private void initialize(Class<T> classToFake, String definitionName) {
        DefinitionProvider<T> definitionProvider = definitionConfig.getDefinitionProvider();
        final Map<String, T> definition = definitionProvider.getDefinition(classToFake, definitionName);
        if (definition == null) {
            throw new FackitoInitializationException("No definition found for : " + classToFake);
        }
        DefinitionItemValueCallables callables = new DefinitionItemValueCallables();
        for (Entry<String, T> entry : definition.entrySet()) {
            definitionItems.put(entry.getKey(), callables.definitionItemValueCallable(entry.getValue()));
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