package me.shakiba.guiceconfig;

import java.util.Enumeration;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;

public class PropsModule extends AbstractModule {

    private final Properties props;

    public PropsModule(Properties props) {
        this.props = props;
    }

    @Override
    protected void configure() {
        for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            bind(String.class).annotatedWith(Names.named(key)).toProvider(
                    new StringProvider(key));
            // TODO: verify if applicable
            bind(Integer.class).annotatedWith(Names.named(key)).toProvider(
                    new IntProvider(key));
            bind(Long.class).annotatedWith(Names.named(key)).toProvider(
                    new LongProvider(key));
            bind(Float.class).annotatedWith(Names.named(key)).toProvider(
                    new FloatProvider(key));
            bind(Double.class).annotatedWith(Names.named(key)).toProvider(
                    new DoubleProvider(key));
            bind(Boolean.class).annotatedWith(Names.named(key)).toProvider(
                    new BooleanProvider(key));
        }
    }

    public class StringProvider implements Provider<String> {
        private final String key;

        public StringProvider(String key) {
            this.key = key;
        }

        @Override
        public String get() {
            return props.getProperty(key, null);
        }
    }

    public class IntProvider implements Provider<Integer> {
        private final String key;

        public IntProvider(String key) {
            this.key = key;
        }

        @Override
        public Integer get() {
            return Integer.parseInt(props.getProperty(key, "0"));
        }
    }

    public class LongProvider implements Provider<Long> {
        private final String key;

        public LongProvider(String key) {
            this.key = key;
        }

        @Override
        public Long get() {
            return Long.parseLong(props.getProperty(key, "0"));
        }
    }

    public class FloatProvider implements Provider<Float> {
        private final String key;

        public FloatProvider(String key) {
            this.key = key;
        }

        @Override
        public Float get() {
            return Float.parseFloat(props.getProperty(key, "0"));
        }
    }

    public class DoubleProvider implements Provider<Double> {
        private final String key;

        public DoubleProvider(String key) {
            this.key = key;
        }

        @Override
        public Double get() {
            return Double.parseDouble(props.getProperty(key, "0"));
        }
    }

    public class BooleanProvider implements Provider<Boolean> {
        private final String key;

        public BooleanProvider(String key) {
            this.key = key;
        }

        @Override
        public Boolean get() {
            return Boolean.parseBoolean(props.getProperty(key, "false"));
        }
    }
}