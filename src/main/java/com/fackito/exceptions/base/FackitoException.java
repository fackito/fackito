/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.exceptions.base;

import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;

public class FackitoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private StackTraceElement[] unfilteredStackTrace;

    public FackitoException(String message, Throwable t) {
        super(message, t);
        filterStackTrace();
    }

    public FackitoException(String message) {
        super(message);
        filterStackTrace();
    }

    private void filterStackTrace() {
        unfilteredStackTrace = getStackTrace();

        ConditionalStackTraceFilter filter = new ConditionalStackTraceFilter();
        filter.filter(this);
    }

    public StackTraceElement[] getUnfilteredStackTrace() {
        return unfilteredStackTrace;
    }
}