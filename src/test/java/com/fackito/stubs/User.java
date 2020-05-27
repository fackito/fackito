package com.fackito.stubs;

import java.util.*;

public interface User {

    String getPhoneNumber();

    String getName();

    int getAge();

    Map<String, String> getAddress();

    List<String> getRoles();
}