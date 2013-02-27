package me.shakiba.guiceconfig;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;

public class PrefesModule extends AbstractModule {

    private final Preferences node;

    public PrefesModule(Preferences node) {
        this.node = node;
    }

    public static PrefesModule user(String node) {
        return new PrefesModule(Preferences.userRoot().node(node));
    }

    public static PrefesModule system(String node) {
        return new PrefesModule(Preferences.systemRoot().node(node));
    }

    @Override
    protected void configure() {
        try {
            for (final String key : node.keys()) {
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
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public class StringProvider implements Provider<String> {
        private final String key;

        public StringProvider(String key) {
            this.key = key;
        }

        @Override
        public String get() {
            return node.get(key, null);
        }
    }

    public class IntProvider implements Provider<Integer> {
        private final String key;

        public IntProvider(String key) {
            this.key = key;
        }

        @Override
        public Integer get() {
            return node.getInt(key, 0);
        }
    }

    public class LongProvider implements Provider<Long> {
        private final String key;

        public LongProvider(String key) {
            this.key = key;
        }

        @Override
        public Long get() {
            return node.getLong(key, 0);
        }
    }

    public class FloatProvider implements Provider<Float> {
        private final String key;

        public FloatProvider(String key) {
            this.key = key;
        }

        @Override
        public Float get() {
            return node.getFloat(key, 0);
        }
    }

    public class DoubleProvider implements Provider<Double> {
        private final String key;

        public DoubleProvider(String key) {
            this.key = key;
        }

        @Override
        public Double get() {
            return node.getDouble(key, 0);
        }
    }

    public class BooleanProvider implements Provider<Boolean> {
        private final String key;

        public BooleanProvider(String key) {
            this.key = key;
        }

        @Override
        public Boolean get() {
            return node.getBoolean(key, false);
        }
    }
}