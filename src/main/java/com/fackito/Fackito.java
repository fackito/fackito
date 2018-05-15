/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

import java.lang.reflect.Method;
import java.util.*;

import static com.fackito.FackitoFakerFactory.*;
import static com.fackito.FackitoUtil.getFakeableAttribute;
import static org.mockito.Mockito.*;

/**
 * The Fackito library extends the Mockito library by generating fake mocks.
 */
public class Fackito {

    /**
     * Creates a DEFAULT fake mock.
     *
     * @param classToFake
     * @param <T>
     * @return
     */
    public static <T> T fake(Class<T> classToFake) {
        return fake(classToFake, DEFAULT_FAKE);
    }

    /**
     * Creates a fake mock using the name provided.
     *
     * @param classToFake
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T fake(Class<T> classToFake, String name) {
        try {
            final Map<String, String> fakeResources = getFakeData(classToFake, name);
            Method[] fakeMethods = classToFake.getMethods();
            final T fake = mock(classToFake);
            for (Method fakeMethod : fakeMethods) {
                final String fakeMethodName = fakeMethod.getName();
                if (FackitoUtil.isMethodFakeable(fakeMethodName)) {
                    Object fakeValue = fakeResources.get(getFakeableAttribute(fakeMethodName));
                    FackitoFaker fackitoFaker = new FackitoFaker();
                    if (fakeValue instanceof String) {
                        fakeValue = fackitoFaker.getFake((String) fakeValue);
                    } else if (fakeValue instanceof List) {
                        List<Object> list = (List<Object>) fakeValue;
                        for (int x = 0; x < list.size(); x++) {
                            Object item = list.get(x);
                            list.set(x, fackitoFaker.getFake((String) item));
                        }
                    }
                    when(fakeMethod.invoke(fake)).thenReturn(fakeValue);
                }
            }
            return fake;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}