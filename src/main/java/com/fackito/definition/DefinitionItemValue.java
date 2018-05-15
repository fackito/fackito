/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito.definition;

/**
 * POJO
 *
 * @author JC Carrillo
 */
class DefinitionItemValue {

    private String fieldName;
    private String methodName;
    private Object arguments[];

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}