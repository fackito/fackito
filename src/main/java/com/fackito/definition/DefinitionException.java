/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

/**
 * The class {@code DefinitionException} and its subclasses are a form of
 * {@code Exception} that indicates conditions that a reasonable
 * application might want to catch.
 *
 * <p>The class {@code DefinitionException} and any subclasses are <em>checked
 * exceptions</em>. Checked exceptions need to be declared in a
 * method or constructor's {@code throws} clause if they can be thrown
 * by the execution of the method or constructor and propagate outside
 * the method or constructor boundary.
 *
 * @author JC Carrillo
 */
public class DefinitionException extends Exception {

    /**
     * Constructs a new exception with the specified detail message. The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DefinitionException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause. <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public DefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}