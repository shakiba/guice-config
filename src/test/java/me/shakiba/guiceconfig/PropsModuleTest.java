package me.shakiba.guiceconfig;

import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

@Test
public class PropsModuleTest extends Assert {

    public void testname() throws Exception {
        InputStream in = PropsModuleTest.class
                .getResourceAsStream("/test.prop");
        Properties prop = new Properties();
        prop.load(in);
        Injector injector = Guice.createInjector(new PropsModule(prop));

        String string = injector.getInstance(Key.get(String.class,
                Names.named("string")));
        assertEquals(string, "hello");

        int number = injector.getInstance(Key.get(Integer.class,
                Names.named("number")));
        assertEquals(number, 6);

        boolean bool = injector.getInstance(Key.get(Boolean.class,
                Names.named("boolean")));
        assertEquals(bool, true);
    }

}