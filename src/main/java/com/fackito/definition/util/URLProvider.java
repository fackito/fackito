/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition.util;

import java.net.URL;

/**
 * The {@link URLProvider} class provides a {@link URL} from a {@link Class}.
 *
 * @author JC Carrillo
 */
@FunctionalInterface
public interface URLProvider {

    URL provide(Class<?> clazz);
}