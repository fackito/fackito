/*
 * Copyright (c) 2018 Fackito
 * This program is made available under the terms of the MIT License.
 */
package com.fackito;

public class FackitoUtil {

    public static boolean isMethodFakeable(String method) {
        return method.startsWith("get") && !method.equals("getClass");
    }

    public static String getFakeableAttribute(String method) {
        String fakeAttributeName = method.replace("get", "");
        return Character.toLowerCase(fakeAttributeName.charAt(0)) + fakeAttributeName.substring(1);
    }
}