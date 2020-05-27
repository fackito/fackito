/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.exceptions.base;

public class FackitoInitializationException extends FackitoException {

    private static final long serialVersionUID = 1L;

    public FackitoInitializationException(String message) {
        super(message);
    }

    public FackitoInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}