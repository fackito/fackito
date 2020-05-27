package com.fackito.definition.parser;

import com.fackito.definition.util.URLProvider;
import com.fackito.stubs.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class URLInputStreamProcessorTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_processInputStream_catches_IOException() throws IOException {
        URLProvider urlProvider = mock(URLProvider.class);
        ClassLoader classLoader = User.class.getClassLoader();
        String canonicalName = User.class.getCanonicalName();
        final String name = canonicalName + ".yaml";
        URL url = classLoader.getResource(name);
        when(urlProvider.provide(eq(User.class))).thenReturn(url);
        URLInputStreamProcessor processor = URLInputStreamProcessor.of(urlProvider);
        DefinitionParser<?> parser = mock(DefinitionParser.class);
        when(parser.parse(any(InputStream.class))).thenThrow(IOException.class);

        processor.processInputStream(User.class, parser);
    }
}