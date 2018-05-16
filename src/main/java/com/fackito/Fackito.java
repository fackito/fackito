/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

import com.fackito.definition.Definition;

import java.lang.reflect.Method;

import static com.fackito.definition.Definition.DEFINITION_NAME_DEFAULT;
import static com.fackito.mockito.MockitoStubber.stub;
import static org.mockito.Mockito.mock;

/**
 * The Fackito library extends the Mockito library by generating fake mocks.
 *
 * @author JC Carrillo
 */
public class Fackito {

    /**
     * Creates a DEFAULT fake mock.
     *
     * @param classToFake
     * @param <F>
     * @return
     */
    public static <F> F fake(Class<F> classToFake) {
        return fake(classToFake, DEFINITION_NAME_DEFAULT);
    }

    /**
     * Creates a fake mock using the name provided.
     *
     * @param classToFake
     * @param definitionName
     * @param <F>
     * @return
     */
    public static <F> F fake(Class<F> classToFake, String definitionName) {
        F mock = mock(classToFake);
        final Method[] methodsToMock = classToFake.getMethods();
        return stub(mock, methodsToMock, new Definition(classToFake, definitionName));
    }
}