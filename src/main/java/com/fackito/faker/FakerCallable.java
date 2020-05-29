/*
 * Copyright (c) 2020 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.faker;

import com.fackito.exceptions.base.FackitoInitializationException;

import java.util.concurrent.Callable;

/**
 * The {@code FakerCallable} class returns a value from {@code Faker}. If no match is found a
 * {@code FackitoInitializationException} is thrown.
 *
 * @author JC Carrillo
 */
public interface FakerCallable extends Callable<Object> {

    Object call() throws FackitoInitializationException;
}