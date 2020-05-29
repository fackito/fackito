package com.fackito.mockito;

import com.fackito.exceptions.base.FackitoInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(JUnit4.class)
public class MethodStubBuilderTest {

    static class ExceptionThrower {

        public void method(Object object) {
            throw new RuntimeException("test");
        }
    }

    @Test(expected = FackitoInitializationException.class)
    public void builds() {
        Method method = Arrays.stream(ExceptionThrower.class.getMethods()).filter(m -> m.getName().equals("method")).findFirst().get();
        MethodStub<Object> methodStub = MethodStubBuilder.newBuilder().build(method);

        methodStub.invoke("");
    }
}