/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

import com.fackito.definition.Definition;

import static com.fackito.definition.Definition.DEFINITION_NAME_DEFAULT;
import static com.fackito.mockito.MockitoStubber.stub;
import static org.mockito.Mockito.mock;

/**
 * The Fackito library extends the Mockito library by generating fake (pre-stubbed) mocks.
 *
 * @author JC Carrillo
 */
public class Fackito {

    private Fackito() {
    }

    /**
     * Creates a default fake mock.
     *
     * @param classToFake class or interface to fake
     * @return fake object
     * @see com.fackito.definition.Definition#DEFINITION_NAME_DEFAULT
     */
    public static <T> T fake(Class<T> classToFake) {
        return fake(classToFake, DEFINITION_NAME_DEFAULT);
    }

    /**
     * Creates a fake mock using the definition name provided.
     *
     * @param classToFake    class or interface to fake
     * @param definitionName definition name to use
     * @return fake object
     */
    public static <T> T fake(Class<T> classToFake, String definitionName) {
        return stub(mock(classToFake), classToFake.getMethods(), Definition.of(classToFake, definitionName));
    }
}