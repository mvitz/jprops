package de.mvitz.jprops.core.test;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.mvitz.jprops.core.api.PropertiesInjector;
import de.mvitz.jprops.core.api.PropertyProvider;

public class FieldTypeTest {

    @Before
    public void setUp() {
        new PropertiesInjector(new PropertyProvider() {
            @Override
            public Object get(final String key) {
                switch (key) {
                case "stringField":
                    return "foo";
                case "intField":
                case "intWrapperField":
                    return 0;
                case "booleanField":
                case "booleanWrapperField":
                    return false;
                case "enumField":
                    return Enum.INSTANCE;
                default:
                    throw new IllegalArgumentException("No value found for key: " + key);
                }
            }
        }).injectInto(this);
    }

    private String stringField;

    @Test
    public void injectingStringShouldWork() throws Exception {
        assertThat(stringField).isEqualTo("foo");
    }

    private int intField;
    private Integer intWrapperField;

    @Test
    public void injectingIntShouldWork() throws Exception {
        assertThat(intField).as("int").isEqualTo(0);
        assertThat(intWrapperField).as("Integer").isEqualTo(0);
    }

    private boolean booleanField;
    private Boolean booleanWrapperField;

    @Test
    public void injectingBooleanShouldWork() throws Exception {
        assertThat(booleanField).as("boolean").isEqualTo(false);
        assertThat(booleanWrapperField).as("Boolean").isEqualTo(false);
    }

    public static enum Enum {
        INSTANCE;
    }

    private Enum enumField;

    @Test
    public void injectingEnumShouldWork() throws Exception {
        assertThat(enumField).isEqualTo(Enum.INSTANCE);
    }

}
