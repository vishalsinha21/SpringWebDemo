package org.vs.test;

import org.junit.Before;
import org.junit.Test;
import org.vs.test.HelloWorld;

import static org.junit.Assert.*;

public class HelloWorldTest {

    HelloWorld helloWorld;

    @Before
    public void setUp() throws Exception {
        helloWorld = new HelloWorld();
    }

    @Test
    public void should_say_hello() {
        String name = "Vishal";
        String result = helloWorld.sayHello(name);
        assertEquals("Hello "+name, result);
    }
}