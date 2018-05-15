/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

import com.fackito.definition.*;

public class FackitoFaker {

    private final DefinitionItemValueMapper definitionItemValueMapper;

    public FackitoFaker() {
        definitionItemValueMapper = new DefinitionItemValueMapper();
    }

    public Object getFake(String string) throws DefinitionException {
        return definitionItemValueMapper.read(string);
    }
}