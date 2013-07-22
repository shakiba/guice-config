package me.shakiba.guiceconfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
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

    public PropsModule(File file) throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        this.props = properties;
    }

    public PropsModule(InputStream in) throws IOException {
        Properties properties = new Properties();
        properties.load(in);
        this.props = properties;
    }

    public PropsModule(Reader reader) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        this.props = properties;
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
            String value = props.getProperty(key, "0");
            if (value.isEmpty()) {
                value = "0";
            }
            return Integer.parseInt(value);
        }
    }

    public class LongProvider implements Provider<Long> {
        private final String key;

        public LongProvider(String key) {
            this.key = key;
        }

        @Override
        public Long get() {
            String value = props.getProperty(key, "0");
            if (value.isEmpty()) {
                value = "0";
            }
            return Long.parseLong(value);
        }
    }

    public class FloatProvider implements Provider<Float> {
        private final String key;

        public FloatProvider(String key) {
            this.key = key;
        }

        @Override
        public Float get() {
            String value = props.getProperty(key, "0");
            if (value.isEmpty()) {
                value = "0";
            }
            return Float.parseFloat(value);
        }
    }

    public class DoubleProvider implements Provider<Double> {
        private final String key;

        public DoubleProvider(String key) {
            this.key = key;
        }

        @Override
        public Double get() {
            String value = props.getProperty(key, "0");
            if (value.isEmpty()) {
                value = "0";
            }
            return Double.parseDouble(value);
        }
    }

    public class BooleanProvider implements Provider<Boolean> {
        private final String key;

        public BooleanProvider(String key) {
            this.key = key;
        }

        @Override
        public Boolean get() {
            String value = props.getProperty(key, "0");
            if (value.isEmpty()) {
                value = "false";
            }
            return Boolean.parseBoolean(value);
        }
    }
}