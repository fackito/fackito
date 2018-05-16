/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.mockito;

import com.fackito.FackitoUtil;
import com.fackito.definition.Definition;

import java.lang.reflect.*;

import static com.fackito.FackitoUtil.getFakeableAttribute;
import static org.mockito.Mockito.when;

/**
 * The class {@code MockitoStubber} stubs a {@link org.mockito.Mockito} mock with definition items.
 *
 * @author JC Carrillo
 */
public class MockitoStubber {

    public static <T> T stub(T mock, Method[] methods, Definition definitionItems) {
        try {
            for (Method method : methods) {
                final String methodToMockName = method.getName();
                if (FackitoUtil.isMethodFakeable(methodToMockName)) {
                    final String definitionItemName = getFakeableAttribute(methodToMockName);
                    if (definitionItems.exists(definitionItemName)) {
                        final Object value = definitionItems.getValue(definitionItemName);
                        when(method.invoke(mock)).thenReturn(value);
                    }
                }
            }
            return mock;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}