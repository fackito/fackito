package com.fackito.mockito;

import com.fackito.stubs.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(JUnit4.class)
public class WhenStubTest {

    @Test
    public void test_stubs() {
        User mock = Mockito.mock(User.class);
        final Method[] methodsToMock = User.class.getMethods();
        Method method = Arrays.stream(methodsToMock).filter(m -> m.getName().equals("getName")).findFirst().get();
        MethodStub<User> methodStub = MethodStubBuilder.<User>newBuilder().build(method);
        WhenStub<User> whenStub = WhenStub.newBuilder().build("name", mock, methodStub);
        whenStub.stub();
        String mockedName = mock.getName();
        Assert.assertEquals("name", mockedName);
    }
}