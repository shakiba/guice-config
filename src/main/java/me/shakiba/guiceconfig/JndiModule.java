package me.shakiba.guiceconfig;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import com.google.inject.AbstractModule;
import com.google.inject.jndi.JndiIntegration;
import com.google.inject.name.Names;

public class JndiModule extends AbstractModule {
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void configure() {

        try {
            Context jndi = (Context) new InitialContext()
                    .lookup("java:comp/env");
            // required by JndiIntegration
            bind(Context.class).toInstance(jndi);

            NamingEnumeration<NameClassPair> list = jndi.list("");
            while (list.hasMore()) {
                NameClassPair entry = list.next();
                String name = entry.getName();
                Object object = jndi.lookup(name);
                // TODO: maybe not String
                Class clazz = (object != null ? object.getClass()
                        : String.class);
                bind(clazz).annotatedWith(Names.named(name)).toProvider(
                        JndiIntegration.fromJndi(clazz, name));
                // toInstance(object);
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}